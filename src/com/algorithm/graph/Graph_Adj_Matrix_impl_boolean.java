package com.algorithm.graph;

/**
 * 利用 boolean 二維陣列表示有無相連
 * 這邊沒有加入 distance 概念
 */
public class Graph_Adj_Matrix_impl_boolean {
    private boolean[][] adj_matrix;

    public Graph_Adj_Matrix_impl_boolean (int vertex_num) {
        this.adj_matrix = new boolean[vertex_num][vertex_num];

        // initializa
        for (int row = 0 ; row < adj_matrix.length ; row ++ ) {
            for ( int col = 0 ; col < adj_matrix[row].length ; col ++ ) {
                this.adj_matrix[row][col] = false;  // 假設目前 vertex 都沒有相連
            }
        }
    }

    public void set_edge (int i_vertex_src, int i_vertex_dest, boolean is_exist) {
        adj_matrix[i_vertex_src][i_vertex_dest] = is_exist;
    }

    public boolean get_edge (int i_vertex_src, int i_vertex_dest) {
        return adj_matrix[i_vertex_src][i_vertex_dest];
    }

    public void print_adj_matrix () {
        for (int row = 0 ; row < adj_matrix.length ; row ++ ) {
            for ( int col = 0 ; col < adj_matrix[row].length ; col ++ ) {
                if ( adj_matrix[row][col] ) {
                    System.out.printf("T" + " ");
                }
                else {
                    System.out.printf("F" + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        Graph_Adj_Matrix_impl_boolean graph_adj_matrix_impl_boolean = new Graph_Adj_Matrix_impl_boolean(5);

        graph_adj_matrix_impl_boolean.set_edge(0, 0, true);
        graph_adj_matrix_impl_boolean.set_edge(0, 1, true);
        graph_adj_matrix_impl_boolean.set_edge(0, 2, true);

        graph_adj_matrix_impl_boolean.set_edge(1, 1, true);
        graph_adj_matrix_impl_boolean.set_edge(1, 4, true);

        graph_adj_matrix_impl_boolean.set_edge(2, 2, true);
        graph_adj_matrix_impl_boolean.set_edge(2, 3, true);
        graph_adj_matrix_impl_boolean.set_edge(2, 4, true);

        graph_adj_matrix_impl_boolean.set_edge(3, 3, true);
        graph_adj_matrix_impl_boolean.set_edge(3, 4, true);

        graph_adj_matrix_impl_boolean.set_edge(4, 3, true);
        graph_adj_matrix_impl_boolean.set_edge(4, 4, true);

        graph_adj_matrix_impl_boolean.print_adj_matrix();
    }
}
