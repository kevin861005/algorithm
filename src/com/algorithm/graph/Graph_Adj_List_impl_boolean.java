package com.algorithm.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 利用 list 實作
 * 這邊沒有加入 distance 概念
 */
public class Graph_Adj_List_impl_boolean {
    private List<Integer>[] adj_list;

    public Graph_Adj_List_impl_boolean (int vertex_num) {
        this.adj_list = new List[vertex_num];

        // initializa
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src ++ ) {
            this.adj_list[i_vertex_src] = new LinkedList<>();
        }
    }

    public void add_edge (int i_vertex_src, int i_vertex_dest) {
        List<Integer> adj_list_src = adj_list[i_vertex_src];
        adj_list_src.add(i_vertex_dest);
    }

    public Integer get_edge (int i_vertex_src, int i_vertex_dest) {
        List<Integer> adj_list_src = adj_list[i_vertex_src];

        for ( int i = 0 ; i < adj_list_src.size() ; i ++ ) {
            if ( adj_list_src.get(i) == i_vertex_dest ) {
                return adj_list_src.get(i);
            }
        }

        return null;
    }

    public void remove_edge (int i_vertex_src, int i_vertex_dest) {
        List<Integer> adj_list_src = adj_list[i_vertex_src];

        for ( int i = 0 ; i < adj_list_src.size() ; i ++ ) {
            if ( adj_list_src.get(i) == i_vertex_dest ) {
                adj_list_src.remove(i);
            }
        }
    }

    public void print_adj_matrix () {
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src ++ ) {
            System.out.printf("[%d] ", i_vertex_src);

            List<Integer> adj_list_src = adj_list[i_vertex_src];
            for ( int i = 0 ; i < adj_list_src.size() ; i ++ ) {
                System.out.printf("-> %d ", adj_list_src.get(i));
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        Graph_Adj_List_impl_boolean graph_adj_list_impl_boolean = new Graph_Adj_List_impl_boolean(5);

        graph_adj_list_impl_boolean.add_edge(0, 1);
        graph_adj_list_impl_boolean.add_edge(0, 2);

        graph_adj_list_impl_boolean.add_edge(1, 4);

        graph_adj_list_impl_boolean.add_edge(2, 3);
        graph_adj_list_impl_boolean.add_edge(2, 4);

        graph_adj_list_impl_boolean.add_edge(3, 4);

        graph_adj_list_impl_boolean.add_edge(4, 3);

        graph_adj_list_impl_boolean.print_adj_matrix();
    }
}
