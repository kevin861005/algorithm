package com.algorithm.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 貪婪法實作
 * 利用迷宮的概念，找出成本最低的路線
 */
public class Greedy_Maze_Cost {
    Integer[][] maze;           // cost lookup table
    PriorityQueue<Node> nodePriorityQueue;     // explored nodes (sort by cost)
    Integer[][] maze_best;      // unexplored nodes (null) + confirmed nodes

    static class Node {
        Integer row;
        Integer col;
        Integer cost;

        public Node(Integer row, Integer col) {
            this.row = row;
            this.col = col;
        }
    }

    static class MyComp implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            return node1.cost.compareTo(node2.cost); // low -> high
        }
    }

    public Greedy_Maze_Cost(Integer[][] maze) {
        this.maze = maze;
        this.nodePriorityQueue = new PriorityQueue<>(20, new MyComp());
        this.maze_best = new Integer[this.maze.length][this.maze[0].length];
    }

    public int go_maze(int row_start, int col_start, int row_target, int col_target) {
        Node start = new Node(row_start, col_start);
        start.cost = this.maze[row_start][col_start];
        Node target = new Node(row_target, col_target);

        return go_maze(start, target);
    }

    public int go_maze(Node start, Node target) {

        /** initialization **/
        nodePriorityQueue.add(start);

        while(true) {
            if ( nodePriorityQueue.isEmpty() ) break;

            /** pick the node with lowest cost **/
            Node now = nodePriorityQueue.poll();

            if (this.maze_best[now.row][now.col] != null) continue;

            /** confirm its min cost **/
            this.maze_best[now.row][now.col] = now.cost;

            /** explore next node **/
            if (now.row - 1 >= 0) {
                Node up = new Node(now.row - 1, now.col);
                up.cost = now.cost + this.maze[up.row][up.col];
                nodePriorityQueue.add(up);
            }
            if (now.row + 1 < maze.length) {
                Node down = new Node(now.row + 1, now.col);
                down.cost = now.cost + this.maze[down.row][down.col];
                nodePriorityQueue.add(down);
            }
            if (now.col - 1 >= 0) {
                Node left = new Node(now.row, now.col - 1);
                left.cost = now.cost + this.maze[left.row][left.col];
                nodePriorityQueue.add(left);
            }
            if (now.col + 1 < maze[0].length) {
                Node right = new Node(now.row, now.col + 1);
                right.cost = now.cost + this.maze[right.row][right.col];
                nodePriorityQueue.add(right);
            }

        }

        return this.maze_best[target.row][target.col];
    }

    public static void main(String[] args) {

        Integer[][] maze = new Integer[][] {
                {1,3,1,2,9},
                {7,3,4,9,9},
                {1,7,5,5,3},
                {2,3,4,2,5},
                };

        Greedy_Maze_Cost gmc = new Greedy_Maze_Cost(maze);

        int row_start = 0; int col_start = 0;
        int row_target = 3; int col_target = 4;

        int cost_min = gmc.go_maze(row_start, col_start, row_target, col_target);

        System.out.println();
    }
}
