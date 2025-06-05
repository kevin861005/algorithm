package com.algorithm.graph;

import java.util.*;

public class Graph_Adj_Matrix_impl_distance_plus_vertex_state {
    private Integer[][] adj_matrix;
    private char[] vertex_states;

    public Graph_Adj_Matrix_impl_distance_plus_vertex_state (int vertex_num) {
        this.adj_matrix = new Integer[vertex_num][vertex_num];
        this.vertex_states = new char[vertex_num];

        // initializa
        for (int row = 0 ; row < adj_matrix.length ; row ++ ) {
            for ( int col = 0 ; col < adj_matrix[row].length ; col ++ ) {
                this.adj_matrix[row][col] = Integer.MIN_VALUE;
            }
        }

        char c = 'A';
        for ( int i = 0 ; i < vertex_num ; i++ ) {
            vertex_states[i] = (char) (c + i);
        }
    }

    public void set_edge (int i_vertex_src, int i_vertex_dest, Integer distance) {
        adj_matrix[i_vertex_src][i_vertex_dest] = distance;
    }

    public Integer get_edge (int i_vertex_src, int i_vertex_dest) {
        return adj_matrix[i_vertex_src][i_vertex_dest];
    }

    public void print_adj_matrix () {

        System.out.print("  ");
        for ( int col = 0 ; col < adj_matrix[0].length ; col ++ ) {
            System.out.print(vertex_states[col] + " ");
        }
        System.out.println();

        for (int row = 0 ; row < adj_matrix.length ; row ++ ) {
            System.out.print(vertex_states[row] + " ");

            for ( int col = 0 ; col < adj_matrix[row].length ; col ++ ) {
                if ( adj_matrix[row][col] == Integer.MIN_VALUE ) {
                    System.out.printf("∞" + " ");
                }
                else {
                    System.out.printf(adj_matrix[row][col] + " ");
                }
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
            if ( visited.contains(i_vertex_now) ) continue;
            visited.add(i_vertex_now);

            System.out.print(vertex_states[i_vertex_now] + " ");

            for ( int i = 0 ; i < adj_matrix[i_vertex_now].length ; i ++ ) {
                if ( adj_matrix[i_vertex_now][i] != Integer.MIN_VALUE ) {   // 代表有相連
                    queue.offer(i);
                }
            }
        }
    }

    public void traverse_dfs (int i_vertex_src) {
        System.out.print("\ndfs: ");
        Set<Integer> visited = new HashSet<>();
        traverse_dfs_recursion(i_vertex_src, visited);

        System.out.println();
    }

    private void traverse_dfs_recursion (int i_vertex_now, Set<Integer> visited) {
        if ( visited.contains(i_vertex_now) ) return;
        visited.add(i_vertex_now);

        // 前序遍歷
        System.out.print(vertex_states[i_vertex_now] + " ");

        Integer[] neighbors = adj_matrix[i_vertex_now];

        for ( int i = 0 ; i < neighbors.length ; i++ ) {
            int distance = neighbors[i];
            if ( distance == Integer.MIN_VALUE ) continue;

            traverse_dfs_recursion(i, visited);
        }

        // 後序遍歷
//        System.out.print(vertex_states[i_vertex_now] + " ");
    }

    class Path {
        int i_vertex_from;
        int i_vertex_to;
        int distance;

        public Path (int i_vertex_from, int i_vertex_to, int distance) {
            this.i_vertex_from = i_vertex_from;
            this.i_vertex_to = i_vertex_to;
            this.distance = distance;
        }
    }

    class MyComparator implements Comparator<Path> {
        @Override
        public int compare(Path o1, Path o2) {
            return o1.distance - o2.distance;
        }
    }

    public void find_shortest_path_djikstra (int i_vertex_start) {

        // initialize
        PriorityQueue<Path> priorityQueue = new PriorityQueue<>(new  MyComparator());
        priorityQueue.offer(new Path(-1, i_vertex_start, 0));   // 自己 -> 自己
        Set<Integer> visited = new HashSet<>();

        int num_of_vertex = adj_matrix.length;
        int[] shortest_path = new int[num_of_vertex];
        int[] shortest_path_prev_vertex = new int[num_of_vertex];

        while ( true ) {
            if ( priorityQueue.isEmpty() ) {
                break;
            }

            // pick the shortest path out
            Path path = priorityQueue.poll();
            if ( visited.contains(path.i_vertex_to) ) {
                continue;
            }
            visited.add(path.i_vertex_to);

            // update the shortest path
            shortest_path[path.i_vertex_to] =  path.distance;
            shortest_path_prev_vertex[path.i_vertex_to] = path.i_vertex_from;

            // change perspective
            int i_vertex_from = path.i_vertex_to;
            int distance_so_far = path.distance;

            // explore neighbors
            Integer[] neighbors = adj_matrix[i_vertex_from];
            for ( int i = 0 ; i < neighbors.length ; i++ ) {
                int distance_to_add =  neighbors[i];
                if ( distance_to_add == Integer.MIN_VALUE ) {
                    continue;
                }

                int distance_to = distance_so_far + distance_to_add;
                priorityQueue.offer(new Path(i_vertex_from, i, distance_to));
            }
        }

        print_shortest_path(i_vertex_start, shortest_path);
        print_shortest_path_route(i_vertex_start, shortest_path_prev_vertex);
    }

    private void print_shortest_path_route (int i_vertex_from, int[] shortest_path_prev_vertexes) {
        System.out.print(" ");
        for ( int i = 0 ; i < adj_matrix[0].length ; i++ ) {
            System.out.printf("%3c", vertex_states[i]);
        }
        System.out.println();

        System.out.print(vertex_states[i_vertex_from] + "  ");
        for ( int i = 0 ; i < shortest_path_prev_vertexes.length ; i++ ) {
            int shortest_path_prev_vertex =  shortest_path_prev_vertexes[i];
            if ( shortest_path_prev_vertex >= 0 ) {
                System.out.printf("%3c", vertex_states[shortest_path_prev_vertex]);
            }
            else {
                System.out.printf("%2c", '.');
            }
        }
        System.out.println();
    }

    private void print_shortest_path (int i_vertex_from, int[] shortest_paths) {
        System.out.print(" ");
        for ( int i = 0 ; i < adj_matrix[0].length ; i++ ) {
            System.out.printf("%3c", vertex_states[i]);
        }
        System.out.println();

        System.out.print(vertex_states[i_vertex_from] + " ");
        for ( int i = 0 ; i < shortest_paths.length ; i++ ) {
            System.out.printf("%3d", shortest_paths[i]);
        }
        System.out.println();
    }



    public static void main (String[] args) {
        Graph_Adj_Matrix_impl_distance_plus_vertex_state
                graph_adj_matrix_impl_distance_plus_vertex_state = new Graph_Adj_Matrix_impl_distance_plus_vertex_state(5);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(0, 0, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(0, 1, 1);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(0, 2, 3);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(1, 1, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(1, 4, 7);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(2, 2, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(2, 3, 2);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(2, 4, 2);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(3, 3, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(3, 4, 9);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(4, 3, 9);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(4, 4, 0);

        graph_adj_matrix_impl_distance_plus_vertex_state.print_adj_matrix();

        graph_adj_matrix_impl_distance_plus_vertex_state.traverse_bfs(0);

        graph_adj_matrix_impl_distance_plus_vertex_state.traverse_dfs(0);

        graph_adj_matrix_impl_distance_plus_vertex_state = new Graph_Adj_Matrix_impl_distance_plus_vertex_state(6);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(0, 0, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(0, 1, 4);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(0, 2, 2);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(1, 1, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(1, 0, 4);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(1, 2, 1);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(1, 3, 5);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(2, 2, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(2, 0, 2);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(2, 1, 1);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(2, 3, 8);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(2, 4, 9);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(3, 3, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(3, 1, 5);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(3, 2, 8);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(3, 4, 2);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(3, 5, 6);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(4, 4, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(4, 3, 2);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(4, 2, 9);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(4, 5, 5);

        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(5, 5, 0);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(5, 4, 5);
        graph_adj_matrix_impl_distance_plus_vertex_state.set_edge(5, 3, 6);

        graph_adj_matrix_impl_distance_plus_vertex_state.find_shortest_path_djikstra(0);
    }
}
