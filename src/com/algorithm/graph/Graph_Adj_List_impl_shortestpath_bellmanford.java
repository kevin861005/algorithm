package com.algorithm.graph;

import java.util.*;

public class Graph_Adj_List_impl_shortestpath_bellmanford {

    public static class EdgeState {
        public Integer i_vertex_src;
        public Integer i_vertex_destination;
        public Integer distance;

        public EdgeState (Integer i_vertex_src, Integer i_vertex_destination, Integer distance) {
            this.i_vertex_src = i_vertex_src;
            this.i_vertex_destination = i_vertex_destination;
            this.distance = distance;
        }
    }

    private List<EdgeState>[] adj_list;
    private char[] vertex_states;
    private Integer[] vertex_states_indegrees;

    public Graph_Adj_List_impl_shortestpath_bellmanford (int vertex_num) {
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
        adj_list_src.add(new EdgeState(i_vertex_src, i_vertex_dest, distance));
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

    public void find_shortest_path_bellmanford (int i_vertex_start) {
        int n = adj_list.length;    // 總節點數
        List<EdgeState> edges_all = collect_adges();

        // initialize
        int[] shortest_path = new int[n];   // 記錄到每個節點最短路徑是多少
        for ( int i = 0 ; i < shortest_path.length ; i++ ) {
            shortest_path[i] = Integer.MAX_VALUE;   // 用最大代表還不能到達該節點
        }
        shortest_path[i_vertex_start] = 0;  // 開始節點到自己本身距離是 0

        int[] shortest_path_previous_vertex = new int[n];   // 記錄每個節點是從哪個節點過來的
        for ( int i = 0 ; i < shortest_path_previous_vertex.length ; i++ ) {
            shortest_path_previous_vertex[i] = -1;  // 代表當前節點還未找到來源
        }

        // main login
        for ( int round = 0 ; round < n - 1 ; round++ ) {   // 跑 n - 1 次
            for (int j = 0 ; j < edges_all.size() ; j++) {
                EdgeState edge = edges_all.get(j);

                if ( shortest_path[edge.i_vertex_src] == Integer.MAX_VALUE ) continue;  // 代表沒有來源可以到當下節點

                int distance_min = shortest_path[edge.i_vertex_destination];
                int distance_now = shortest_path[edge.i_vertex_src] + edge.distance;
                if ( distance_now < distance_min ) {
                    shortest_path[edge.i_vertex_destination] = distance_now;
                    shortest_path_previous_vertex[edge.i_vertex_destination] = edge.i_vertex_src;
                }
            }
        }

        // check negative cycle
        boolean is_negative_cycle_detected = false;
        for ( int i = 0 ; i < edges_all.size() ; i++ ) {
            EdgeState edge = edges_all.get(i);

            if ( shortest_path[edge.i_vertex_src] == Integer.MAX_VALUE ) continue;

            int distance_min = shortest_path[edge.i_vertex_destination];
            int distance_now = shortest_path[edge.i_vertex_src] + edge.distance;
            if ( distance_now < distance_min ) {
                System.out.println("Negative cycle detected, cannot find shortest path");

                is_negative_cycle_detected = true;
                break;
            }
        }

        if ( is_negative_cycle_detected ) return;

        print_shortest_path(i_vertex_start, shortest_path);
        print_shortest_path_route(i_vertex_start, shortest_path_previous_vertex);
    }

    private void print_shortest_path_route (int i_vertex_from, int[] shortest_path_previous_vertexes) {
        System.out.print("  ");
        for ( int i = 0 ; i < adj_list.length ; i++ ) {
            System.out.printf("%3c", vertex_states[i]);
        }
        System.out.println();

        System.out.print(vertex_states[i_vertex_from] + " ");
        for (  int i = 0 ; i < shortest_path_previous_vertexes.length ; i++ ) {
            int shortest_path_previous_vertex = shortest_path_previous_vertexes[i];
            if ( shortest_path_previous_vertex >= 0 ) {
                System.out.printf("%3c", vertex_states[shortest_path_previous_vertex]);
            }
            else {
                System.out.printf("%3c", '.');
            }
        }
        System.out.println();
    }

    private void print_shortest_path (int i_vertex_from, int[] shortest_paths) {
        System.out.print("  ");
        for ( int i = 0 ; i < adj_list.length ; i++ ) {
            System.out.printf("%3c", vertex_states[i]);
        }
        System.out.println();

        System.out.print(vertex_states[i_vertex_from] + " ");
        for (  int i = 0 ; i < shortest_paths.length ; i++ ) {
            int shortest_path = shortest_paths[i];
            if ( shortest_path == Integer.MAX_VALUE ) {
                System.out.printf("%3c", '∞');
            }
            else {
                System.out.printf("%3d", shortest_path);
            }
        }
        System.out.println();
    }

    private List<EdgeState> collect_adges () {
        List<EdgeState> edges_all = new ArrayList<>();

        for ( int i = 0 ; i < adj_list.length ; i++ ) {
            List<EdgeState> edges = adj_list[i];
            edges_all.addAll(edges);
        }

        return edges_all;
    }

    public void print_edges () {
        for ( int i = 0 ; i < adj_list.length ; i++ ) {
            System.out.printf("[%c] ", vertex_states[i]);

            List<EdgeState> edges = adj_list[i];
            for ( int j = 0 ; j < edges.size() ; j++ ) {
                EdgeState edge = edges.get(j);
                System.out.printf("-> [%c, %d]", vertex_states[edge.i_vertex_destination], edge.distance);
            }
            System.out.println();
        }
    }

    private static Graph_Adj_List_impl_shortestpath_bellmanford get_graph () {
        Graph_Adj_List_impl_shortestpath_bellmanford graph = new Graph_Adj_List_impl_shortestpath_bellmanford(5);

        graph.add_edge(0, 0, 0);
        graph.add_edge(0, 4, -5);

        graph.add_edge(1, 1, 0);
        graph.add_edge(1, 0, 5);
        graph.add_edge(1, 2, 2);
        graph.add_edge(1, 2, 3);
        graph.add_edge(1, 3, 9);

        graph.add_edge(2, 2, 0);
        graph.add_edge(2, 3, 1);

        graph.add_edge(3, 3, 0);

        graph.add_edge(4, 4, 0);
        graph.add_edge(4, 3, 2);

        return graph;
    }

    private static Graph_Adj_List_impl_shortestpath_bellmanford get_graph_with_negative_cycle () {
        Graph_Adj_List_impl_shortestpath_bellmanford graph = new Graph_Adj_List_impl_shortestpath_bellmanford(6);

        graph.add_edge(0, 0, 0);
        graph.add_edge(0, 4, -5);

        graph.add_edge(1, 1, 0);
        graph.add_edge(1, 0, 5);
        graph.add_edge(1, 2, 2);
        graph.add_edge(1, 2, 3);
        graph.add_edge(1, 3, 9);

        graph.add_edge(2, 2, 0);
        graph.add_edge(2, 3, 1);

        graph.add_edge(3, 3, 0);

        graph.add_edge(4, 4, 0);
        graph.add_edge(4, 3, 2);
        graph.add_edge(4, 5, 1);

        graph.add_edge(5, 5, 0);
        graph.add_edge(5, 4, -2);

        return graph;
    }

    public static void main (String[] args) {
//        Graph_Adj_List_impl_shortestpath_bellmanford graph = get_graph();
//        graph.print_edges();
//        graph.find_shortest_path_bellmanford(1);
//        graph.find_shortest_path_bellmanford(0);

        Graph_Adj_List_impl_shortestpath_bellmanford graph_with_negative_cycle = get_graph_with_negative_cycle();
        graph_with_negative_cycle.print_edges();
        graph_with_negative_cycle.find_shortest_path_bellmanford(1);
    }
}
