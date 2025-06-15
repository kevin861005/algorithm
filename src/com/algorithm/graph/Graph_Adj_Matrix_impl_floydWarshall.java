package com.algorithm.graph;

import java.util.*;

public class Graph_Adj_Matrix_impl_floydWarshall {
    private Integer[][] adj_matrix;
    private char[] vertex_states;

    int MAX_DISTANCE = Integer.MAX_VALUE;

    public Graph_Adj_Matrix_impl_floydWarshall (int vertex_num) {
        this.adj_matrix = new Integer[vertex_num][vertex_num];
        this.vertex_states = new char[vertex_num];

        // initializa
        for (int row = 0 ; row < adj_matrix.length ; row ++ ) {
            for ( int col = 0 ; col < adj_matrix[row].length ; col ++ ) {
                this.adj_matrix[row][col] = MAX_DISTANCE;
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

    public void print_adj_matrix () {

        System.out.print("  ");
        for ( int col = 0 ; col < adj_matrix[0].length ; col ++ ) {
            System.out.print(vertex_states[col] + " ");
        }
        System.out.println();

        for (int row = 0 ; row < adj_matrix.length ; row ++ ) {
            System.out.print(vertex_states[row] + " ");

            for ( int col = 0 ; col < adj_matrix[row].length ; col ++ ) {
                if ( adj_matrix[row][col] == MAX_DISTANCE ) {
                    System.out.printf("∞" + " ");
                }
                else {
                    System.out.printf(adj_matrix[row][col] + " ");
                }
            }
            System.out.println();
        }
    }

    public void find_shortest_path_floyd_warshall () {

        int vertex_num = adj_matrix.length;

        // initialize
        // assuming the best path is using no middle node in the every beginning
        int[][] shortest_path = new int[vertex_num][vertex_num];
        for (int i = 0; i < vertex_num; i++) {
            for (int j = 0; j < vertex_num; j++) {
                shortest_path[i][j] = adj_matrix[i][j];
            }
        }

        // main logic - try every vertex as a middle node for each src to dst
        for (int i_mid = 0; i_mid < vertex_num; i_mid++) {
            for (int i_start = 0; i_start < vertex_num; i_start++) {
                for ( int i_dst = 0; i_dst < vertex_num; i_dst++ ) {
                    if ( shortest_path[i_start][i_mid] == MAX_DISTANCE || shortest_path[i_mid][i_dst] == MAX_DISTANCE ) continue;

                    int distance_min = shortest_path[i_start][i_dst];
                    int distance_now = shortest_path[i_start][i_mid] + shortest_path[i_mid][i_dst];

                    if ( distance_now < distance_min ) {
                        shortest_path[i_start][i_dst] = distance_now;
                    }
                }
            }
        }

        // check for negative cycle
        boolean is_negative_cycle_detected = false;
        for (int i_mid = 0; i_mid < vertex_num; i_mid++) {
            for (int i_start = 0; i_start < vertex_num; i_start++) {
                for ( int i_dst = 0; i_dst < vertex_num; i_dst++ ) {
                    if ( shortest_path[i_start][i_mid] == MAX_DISTANCE || shortest_path[i_mid][i_dst] == MAX_DISTANCE ) continue;

                    int distance_min = shortest_path[i_start][i_dst];
                    int distance_now = shortest_path[i_start][i_mid] + shortest_path[i_mid][i_dst];

                    if ( distance_now < distance_min ) {
                        is_negative_cycle_detected =  true;
                    }
                }
            }
        }

        if ( is_negative_cycle_detected ) {
            System.out.println("negative cycle detected, cannot find shortest path");
            return;
        }

       // print results
        for ( int i_vertex_from = 0; i_vertex_from < vertex_num; i_vertex_from++ ) {
            print_shortest_path(i_vertex_from, shortest_path[i_vertex_from]);
            System.out.println("=================================================");
        }
    }

    private void print_shortest_path (int i_vertex_from, int[] shortest_paths) {
        System.out.print("  ");
        for ( int i = 0 ; i < adj_matrix[0].length ; i++ ) {
            System.out.printf("%3c", vertex_states[i]);
        }
        System.out.println();

        System.out.print(vertex_states[i_vertex_from] + " ");
        for ( int i = 0 ; i < shortest_paths.length ; i++ ) {
            if ( shortest_paths[i] == MAX_DISTANCE ) {
                System.out.printf("%3c", '∞');
            }
            else {
                System.out.printf("%3d", shortest_paths[i]);
            }
        }
        System.out.println();
    }

    public static void main (String[] args) {
//        Graph_Adj_Matrix_impl_floydWarshall graph = get_graph();
//        Graph_Adj_Matrix_impl_floydWarshall graph = get_graph_with_negative_edges();
        Graph_Adj_Matrix_impl_floydWarshall graph = get_graph_with_negative_cycle();

        graph.print_adj_matrix();
        graph.find_shortest_path_floyd_warshall();
    }

    private static Graph_Adj_Matrix_impl_floydWarshall get_graph () {
        Graph_Adj_Matrix_impl_floydWarshall graph = new Graph_Adj_Matrix_impl_floydWarshall(6);

        graph.set_edge(0, 0, 0);
        graph.set_edge(0, 1, 4);
        graph.set_edge(0, 2, 2);

        graph.set_edge(1, 1, 0);
        graph.set_edge(1, 0, 4);
        graph.set_edge(1, 2, 1);
        graph.set_edge(1, 3, 5);

        graph.set_edge(2, 2, 0);
        graph.set_edge(2, 0, 2);
        graph.set_edge(2, 1, 1);
        graph.set_edge(2, 3, 8);
        graph.set_edge(2, 4, 9);

        graph.set_edge(3, 3, 0);
        graph.set_edge(3, 1, 5);
        graph.set_edge(3, 2, 8);
        graph.set_edge(3, 4, 2);
        graph.set_edge(3, 5, 6);

        graph.set_edge(4, 4, 0);
        graph.set_edge(4, 3, 2);
        graph.set_edge(4, 2, 9);
        graph.set_edge(4, 5, 5);

        graph.set_edge(5, 5, 0);
        graph.set_edge(5, 4, 5);
        graph.set_edge(5, 3, 6);

        return graph;
    }

    private static Graph_Adj_Matrix_impl_floydWarshall get_graph_with_negative_edges () {
        Graph_Adj_Matrix_impl_floydWarshall graph = new Graph_Adj_Matrix_impl_floydWarshall(5);

        graph.set_edge(0, 0, 0);
        graph.set_edge(0, 4, -5);

        graph.set_edge(1, 1, 0);
        graph.set_edge(1, 0, 5);
        graph.set_edge(1, 2, 2);
        graph.set_edge(1, 3, 9);

        graph.set_edge(2, 2, 0);
        graph.set_edge(2, 3, 1);

        graph.set_edge(3, 3, 0);

        graph.set_edge(4, 4, 0);
        graph.set_edge(4, 3, 2);

        return graph;
    }

    private static Graph_Adj_Matrix_impl_floydWarshall get_graph_with_negative_cycle () {
        Graph_Adj_Matrix_impl_floydWarshall graph = new Graph_Adj_Matrix_impl_floydWarshall(6);

        graph.set_edge(0, 0, 0);
        graph.set_edge(0, 4, -5);

        graph.set_edge(1, 1, 0);
        graph.set_edge(1, 0, 5);
        graph.set_edge(1, 2, 2);
        graph.set_edge(1, 3, 9);

        graph.set_edge(2, 2, 0);
        graph.set_edge(2, 3, 1);

        graph.set_edge(3, 3, 0);

        graph.set_edge(4, 4, 0);
        graph.set_edge(4, 3, 2);
        graph.set_edge(4, 5, 1);

        graph.set_edge(5, 5, 0);
        graph.set_edge(5, 4, -2);

        return graph;
    }
}
