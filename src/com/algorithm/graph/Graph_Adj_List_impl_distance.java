package com.algorithm.graph;

import java.util.LinkedList;
import java.util.List;

public class Graph_Adj_List_impl_distance {

    public static class EdgeState {
        public Integer i_vertex_destination;
        public Integer distance;

        public EdgeState (Integer i_vertex_destination, Integer distance) {
            this.i_vertex_destination = i_vertex_destination;
            this.distance = distance;
        }
    }

    private List<EdgeState>[] adj_list;

    public Graph_Adj_List_impl_distance (int vertex_num) {
        this.adj_list = new List[vertex_num];

        // initializa
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src ++ ) {
            this.adj_list[i_vertex_src] = new LinkedList<>();
        }
    }

    public void add_edge (int i_vertex_src, int i_vertex_dest, int distance) {
        List<EdgeState> adj_list_src = adj_list[i_vertex_src];
        adj_list_src.add(new EdgeState(i_vertex_dest, distance));
    }

    public EdgeState get_edge (int i_vertex_src, int i_vertex_dest) {
        List<EdgeState> adj_list_src = adj_list[i_vertex_src];

        for ( int i = 0 ; i < adj_list_src.size() ; i ++ ) {
            if ( adj_list_src.get(i).i_vertex_destination == i_vertex_dest ) {
                return adj_list_src.get(i);
            }
        }

        return null;
    }

    public void remove_edge (int i_vertex_src, int i_vertex_dest) {
        List<EdgeState> adj_list_src = adj_list[i_vertex_src];

        for ( int i = 0 ; i < adj_list_src.size() ; i ++ ) {
            if ( adj_list_src.get(i).i_vertex_destination == i_vertex_dest ) {
                adj_list_src.remove(i);
            }
        }
    }

    public void print_adj_matrix () {
        for ( int i_vertex_src = 0 ; i_vertex_src < adj_list.length ; i_vertex_src ++ ) {
            System.out.printf("[%d] ", i_vertex_src);

            List<EdgeState> adj_list_src = adj_list[i_vertex_src];
            for ( int i = 0 ; i < adj_list_src.size() ; i ++ ) {
                System.out.printf("-> [%d,%d] ", adj_list_src.get(i).i_vertex_destination, adj_list_src.get(i).distance);
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        Graph_Adj_List_impl_distance graph_adj_list_impl_distance = new Graph_Adj_List_impl_distance(5);

        graph_adj_list_impl_distance.add_edge(0, 1, 1);
        graph_adj_list_impl_distance.add_edge(0, 2, 3);

        graph_adj_list_impl_distance.add_edge(1, 4, 7);

        graph_adj_list_impl_distance.add_edge(2, 3, 2);
        graph_adj_list_impl_distance.add_edge(2, 4, 2);

        graph_adj_list_impl_distance.add_edge(3, 4, 9);

        graph_adj_list_impl_distance.add_edge(4, 3, 9);

        graph_adj_list_impl_distance.print_adj_matrix();
    }
}
