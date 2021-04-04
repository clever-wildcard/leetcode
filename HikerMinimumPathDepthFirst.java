package com.example.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HikerMinimumPathDepthFirst {

        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            if (row == 1 && col == 1) return 0; // you're entire map consists of grid[0][0] so it'll cost you 0 effort to hike.
            UnionFind unionFind = new UnionFind(heights); //  Do all the stuff we just went over in UnionFind constructor.
            List<Edge> edgeList = unionFind.edgeList; // Prep to use the edgeList we just made.
            Collections.sort(edgeList, (e1, e2) -> e1.difference - e2.difference); // Sort our edgeList from smallest to largest edge size (named 'difference' in the UnionFind class.)

            for (int i = 0; i < edgeList.size(); i++) { // For every edge in our edgeList:
                int x = edgeList.get(i).x; // get the node 'x' value;
                int y = edgeList.get(i).y;  // get the node 'y' value:
                unionFind.union(x, y); //
                if (unionFind.find(0) == unionFind.find(row * col - 1)) return edgeList.get(i).difference;
            }
            return -1;
        }
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        List<Edge> edgeList;

        public UnionFind(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            // instantiate UnionFind fields
            parent = new int[row * col];
            edgeList = new ArrayList<>();
            rank = new int[row * col];
            // iterate over all cells, filling edgeList and parent
            for (int currentRow = 0; currentRow < row; currentRow++) {
                for (int currentCol = 0; currentCol < col; currentCol++) { // For every cell except the cells in row zero or in column zero, add two values to edgeList.
                    if (currentRow > 0) { // edgeList Value One: Append to edgeList an edge for the cell in the horizontalTowardsZero direction. To do this you:
                        edgeList.add(new Edge(currentRow * col + currentCol, // Calculate the node 'x' value using current row value;
                                (currentRow - 1) * col + currentCol, // Calculate the node 'y' value using row - 1;
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow - 1][currentCol])) // Calculate the edge value as the absolute difference between our current cell's value and the value of the cell next to us in the horizontalTowardsZero direction
                        );
                    }
                    //[2, 3]; x = 9; y = 6
                    // [1, 4]; x = 8; y = 4
                    //[4, 1]; x = 8; y = 4

                    if (currentCol > 0) { // edgeList Value Two: Append to edgeList an edge for the cell in the verticalTowardsZero direction. To do this, you:
                        edgeList.add(new Edge(currentRow * col + currentCol, // Calculate the node 'x' value using the current column value;
                                currentRow * col + currentCol - 1, // Calculate the node 'y' value using column - 1;
                                Math.abs(heights[currentRow][currentCol] - heights[currentRow][currentCol - 1])) // Calculate the edge value as the absolute difference between the value of our current cell and the value of cell in verticalTowardsZero direction.
                        );
                    }
                    //[2, 3]; x = 9; y = 8
                    // [1, 4]; x = 8; y = 7
                    //[4, 1]; x = 8; y = 4

                    // Append a value to array parent, for which we're assigning a value to an index of the same value.
                    parent[currentRow * col + currentCol] = currentRow * col + currentCol;
                    //[2, 3]; parent[9] = 9
                    // [1, 4]; parent[8] = 8
                    //[4, 1]; parent[8] = 8
                }
            }
        }

        int find(int x) { // find(3)
            if (parent[x] != x) parent[x] = find(parent[x]); // if (parent[3] != 3 (and the zeroth time, it totally does, so this evaluates to false)
            return parent[x]; // return parent[x], which is actually currently still having the same value as x.
        }

        void union(int x, int y) {
            int parentX = find(x); // zeroth run - this evaluates to x
            int parentY = find(y); // zeroth run - this evaluates to y
            if (parentX != parentY) {
                if (rank[parentX] > rank[parentY]) parent[parentY] = parentX;
                else if (rank[parentX] < rank[parentY]) parent[parentX] = parentY;
                else {
                    parent[parentY] = parentX;
                    rank[parentX] += 1;
                }
            }
        }
    }

    class Edge {
        int x;
        int y;
        int difference;

        Edge(int x, int y, int difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }

}
