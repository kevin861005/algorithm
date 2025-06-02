package com.algorithm.graph;

public class Graph_Adj_Matrix_impl_distance {
    private Integer[][] adj_matrix;

    public Graph_Adj_Matrix_impl_distance (int vertex_num) {
        this.adj_matrix = new Integer[vertex_num][vertex_num];

        // initializa
        for (int row = 0 ; row < adj_matrix.length ; row ++ ) {
            for ( int col = 0 ; col < adj_matrix[row].length ; col ++ ) {
                this.adj_matrix[row][col] = Integer.MIN_VALUE;
            }
        }
    }

    public void set_edge (int i_vertex_src, int i_vertex_dest, Integer distance) {
        adj_matrix[i_vertex_src][i_vertex_dest] = distance;
    }

    public Integer get_edge (int i_vertex_src, int i_vertex_dest) {
        return adj_matrix[i_vertex_src][i_vertex_dest];
    }

    public void print_adj_matrix () {
        for (int row = 0 ; row < adj_matrix.length ; row ++ ) {
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

    public static void main (String[] args) {
        Graph_Adj_Matrix_impl_distance graph_adj_matrix_impl_boolean = new Graph_Adj_Matrix_impl_distance(5);

        graph_adj_matrix_impl_boolean.set_edge(0, 0, 0);
        graph_adj_matrix_impl_boolean.set_edge(0, 1, 1);
        graph_adj_matrix_impl_boolean.set_edge(0, 2, 3);

        graph_adj_matrix_impl_boolean.set_edge(1, 1, 0);
        graph_adj_matrix_impl_boolean.set_edge(1, 4, 7);

        graph_adj_matrix_impl_boolean.set_edge(2, 2, 0);
        graph_adj_matrix_impl_boolean.set_edge(2, 3, 2);
        graph_adj_matrix_impl_boolean.set_edge(2, 4, 2);

        graph_adj_matrix_impl_boolean.set_edge(3, 3, 0);
        graph_adj_matrix_impl_boolean.set_edge(3, 4, 9);

        graph_adj_matrix_impl_boolean.set_edge(4, 3, 9);
        graph_adj_matrix_impl_boolean.set_edge(4, 4, 0);

        graph_adj_matrix_impl_boolean.print_adj_matrix();
    }
}
