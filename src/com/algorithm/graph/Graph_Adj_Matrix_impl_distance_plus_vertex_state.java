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
    }
}
