package com.algorithm.graph;

import java.util.*;

public class Graph_Adj_List_impl_distance_plus_vertex_state {

    public static class EdgeState {
        public Integer i_vertex_destination;
        public Integer distance;

        public EdgeState (Integer i_vertex_destination, Integer distance) {
            this.i_vertex_destination = i_vertex_destination;
            this.distance = distance;
        }
    }

    private List<EdgeState>[] adj_list;
    private char[] vertex_states;
    private Integer[] vertex_states_indegrees;

    public Graph_Adj_List_impl_distance_plus_vertex_state (int vertex_num) {
        this.adj_list = new List[vertex_num];
        this.vertex_states = new char[vertex_num];
        this.vertex_states_indegrees = new Integer[vertex_num];

        // initialize
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src++ ) {
            this.adj_list[i_vertex_src] = new LinkedList<>();
        }

        char c = 'A';
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src++ ) {
            vertex_states[i_vertex_src] = (char) (c + i_vertex_src);
        }

        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src++ ) {
            vertex_states_indegrees[i_vertex_src] = 0;
        }
    }

    public void add_edge (int i_vertex_src, int i_vertex_dest, int distance) {
        List<EdgeState> adj_list_src = adj_list[i_vertex_src];
        adj_list_src.add(new EdgeState(i_vertex_dest, distance));
    }

    public EdgeState get_edge (int i_vertex_src, int i_vertex_dest) {
        List<EdgeState> adj_list_src = adj_list[i_vertex_src];

        for ( int i = 0 ; i < adj_list_src.size() ; i++ ) {
            if ( adj_list_src.get(i).i_vertex_destination == i_vertex_dest ) {
                return adj_list_src.get(i);
            }
        }

        return null;
    }

    public void remove_edge (int i_vertex_src, int i_vertex_dest) {
        List<EdgeState> adj_list_src = adj_list[i_vertex_src];

        for ( int i = 0 ; i < adj_list_src.size() ; i++ ) {
            if ( adj_list_src.get(i).i_vertex_destination == i_vertex_dest ) {
                adj_list_src.remove(i);
            }
        }
    }

    public void print_adj_matrix () {
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src++ ) {
            System.out.printf("[%c] ", vertex_states[i_vertex_src]);

            List<EdgeState> adj_list_src = adj_list[i_vertex_src];
            for ( int i = 0 ; i < adj_list_src.size() ; i++ ) {
                System.out.printf("-> [%c,%d] ", vertex_states[adj_list_src.get(i).i_vertex_destination],
                                  adj_list_src.get(i).distance
                );
            }
            System.out.println();
        }
    }

    public void traverse_bfs (int i_vertex_src) {
        System.out.print("\nbfs: ");

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i_vertex_src);

        Set<Integer> visited = new HashSet<>();

        while ( true ) {
            if ( queue.isEmpty() ) {
                break;
            }

            Integer i_vertex_now = queue.poll();
            if ( visited.contains(i_vertex_now) ) {
                continue;
            }
            visited.add(i_vertex_now);

            System.out.printf("%c ", vertex_states[i_vertex_now]);

            List<EdgeState> neighbors = adj_list[i_vertex_now];
            for ( int i = 0 ; i < neighbors.size() ; i++ ) {
                queue.offer(neighbors.get(i).i_vertex_destination);
            }
        }
    }

    public void traverse_dfs (int i_vertex_src) {
        System.out.print("\ndfs: ");
        Set<Integer> visited = new HashSet<>();
        traverse_dfs_recursion(i_vertex_src, visited);
    }

    private void traverse_dfs_recursion (int i_vertex_now, Set<Integer> visited) {
        if ( visited.contains(i_vertex_now) ) {
            return;
        }
        visited.add(i_vertex_now);

        // 前序遍歷
        //        System.out.printf("%c ", vertex_states[i_vertex_now]);

        List<EdgeState> neighbors = adj_list[i_vertex_now];

        for ( int i = 0 ; i < neighbors.size() ; i++ ) {
            traverse_dfs_recursion(neighbors.get(i).i_vertex_destination, visited);
        }

        // 後序遍歷
        System.out.printf("%c ", vertex_states[i_vertex_now]);
    }

    public void topological_sort_dfs () {
        System.out.printf("\ntopological_sort (dfs): ");

        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src++ ) {
            if ( visited.contains(i_vertex_src) ) {
                continue;
            }
            topological_sort_dfs_recursion(i_vertex_src, visited, stack);
        }

        while ( true ) {
            if ( stack.isEmpty() ) {
                break;
            }
            Integer i_vertex = stack.pop();
            System.out.printf("%c ", vertex_states[i_vertex]);
        }
    }

    private void topological_sort_dfs_recursion (int i_vertex_src, Set<Integer> visited, Stack<Integer> stack) {
        if ( visited.contains(i_vertex_src) ) {
            return;
        }
        visited.add(i_vertex_src);

        List<EdgeState> neighbors = adj_list[i_vertex_src];

        for ( int i = 0 ; i < neighbors.size() ; i++ ) {
            EdgeState neighbor = neighbors.get(i);
            topological_sort_dfs_recursion(neighbor.i_vertex_destination, visited, stack);
        }

        stack.push(i_vertex_src);
    }

    public void topological_sort_indegree () {
        System.out.printf("\ntopological_sort (indegree): ");

        // 1. calculate in-degree count
        Set<Integer> visited = new HashSet<>();
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src++ ) {
            if ( visited.contains(i_vertex_src) ) continue;

            calculate_indegree(i_vertex_src, visited);
        }

        // 2. find vertex with 0 in-degree count
        Queue<Integer> queue = new LinkedList<>();
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src++ ) {
            if ( vertex_states_indegrees[i_vertex_src] == 0 ) {
                queue.offer(i_vertex_src);
            }
        }

        // 3. repeat 2. + update in-degree count
        while ( true ) {
            if ( queue.isEmpty() ) break;

            Integer i_vertex = queue.poll();
            System.out.printf("%c ", vertex_states[i_vertex]);

            List<EdgeState> neighbors = adj_list[i_vertex];
            for ( int i = 0 ; i < neighbors.size() ; i++ ) {
                EdgeState neighbor = neighbors.get(i);

                // main logic
                vertex_states_indegrees[neighbor.i_vertex_destination]--;

                if ( vertex_states_indegrees[neighbor.i_vertex_destination] == 0 ) {
                    queue.offer(neighbor.i_vertex_destination);
                }
            }
        }
    }

    private void calculate_indegree (int i_vertex_src, Set<Integer> visited) {
        if ( visited.contains(i_vertex_src) ) return;
        visited.add(i_vertex_src);

        List<EdgeState> neighbors = adj_list[i_vertex_src];
        for ( int i = 0 ; i < neighbors.size() ; i++ ) {
            EdgeState neighbor = neighbors.get(i);

            // main logic
            vertex_states_indegrees[neighbor.i_vertex_destination]++;

            calculate_indegree(neighbor.i_vertex_destination, visited);
        }
    }

    public static void main (String[] args) {
        Graph_Adj_List_impl_distance_plus_vertex_state graph_adj_list_impl_distance_plus_vertex_state =
                new Graph_Adj_List_impl_distance_plus_vertex_state(5);

        graph_adj_list_impl_distance_plus_vertex_state.add_edge(0, 1, 1);
        graph_adj_list_impl_distance_plus_vertex_state.add_edge(0, 2, 3);

        graph_adj_list_impl_distance_plus_vertex_state.add_edge(1, 4, 7);

        graph_adj_list_impl_distance_plus_vertex_state.add_edge(2, 3, 2);
        graph_adj_list_impl_distance_plus_vertex_state.add_edge(2, 4, 2);

        graph_adj_list_impl_distance_plus_vertex_state.add_edge(3, 4, 9);

        graph_adj_list_impl_distance_plus_vertex_state.add_edge(4, 3, 9);

        graph_adj_list_impl_distance_plus_vertex_state.print_adj_matrix();

        graph_adj_list_impl_distance_plus_vertex_state.traverse_bfs(0);
        graph_adj_list_impl_distance_plus_vertex_state.traverse_dfs(0);

        graph_adj_list_impl_distance_plus_vertex_state =
                new Graph_Adj_List_impl_distance_plus_vertex_state(6);
        graph_adj_list_impl_distance_plus_vertex_state.add_edge(0, 1, 1);
        graph_adj_list_impl_distance_plus_vertex_state.add_edge(0, 2, 1);

        graph_adj_list_impl_distance_plus_vertex_state.add_edge(1, 4, 1);

        graph_adj_list_impl_distance_plus_vertex_state.add_edge(3, 2, 1);
        graph_adj_list_impl_distance_plus_vertex_state.add_edge(3, 4, 1);
        graph_adj_list_impl_distance_plus_vertex_state.add_edge(3, 5, 1);

        graph_adj_list_impl_distance_plus_vertex_state.add_edge(5, 4, 1);

        graph_adj_list_impl_distance_plus_vertex_state.topological_sort_dfs();
        graph_adj_list_impl_distance_plus_vertex_state.topological_sort_indegree();
    }
}
