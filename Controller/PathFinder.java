package Controller;

import Model.Cell;
import Model.Map;

import java.util.*;

import static java.util.List.of;

public class PathFinder {
    public static List<Cell> findPath(Cell start, Cell target, Map gameMap) {
        boolean[][] pathableMap = new boolean[gameMap.getLength()][gameMap.getWidth()];
        for (int i = 0; i < gameMap.getLength(); i++) {
            for (int j = 0; j < gameMap.getWidth(); j++) {
                if (gameMap.getCells()[i][j].getMaterial().equals("lowWater") || gameMap.getCells()[i][j].getMaterial().equals("sea")
                        || gameMap.getCells()[i][j].getMaterial().equals("jolge") || gameMap.getCells()[i][j].getBuilding().getType().equals("wall"))
                    pathableMap[i][j] = false;
                else pathableMap[i][j] = true;
            }
        }
        final PriorityQueue<Node> open = new PriorityQueue<>();
        final Set<Node> closed = new HashSet<>();
        final Node[][] nodeMap = new Node[gameMap.getLength()][gameMap.getWidth()];
        Node current;

        for (int i = 0; i < nodeMap.length; i++) {
            for (int j = 0; j < nodeMap[0].length; j++) {
                int heuristic = Math.abs(i - target.getX()) + Math.abs(j - target.getY()); //distance
                Node node = new Node(10, heuristic, i, j);

                if (!pathableMap[i][j]) {//!gameMap.isCellAvailable(i, j)
                    closed.add(node);
                }

                nodeMap[i][j] = node;
            }
        }

        Node startNode = nodeMap[start.getX()][start.getY()];
        Node targetNode = nodeMap[target.getX()][target.getY()];

        open.add(startNode);

        do {
            //if the cell we are checking is the target node
            current = open.poll();
            closed.add(current);

            if (current.equals(targetNode)) {
                return extractPath(current);
            }
            //else check neighbours
            for (int i = current.getGridX() - 1; i < current.getGridX() + 2; i++) {
                for (int j = current.getGridY() - 1; j < current.getGridY() + 2; j++) {
                    if ((i >= 0 && i <= gameMap.getLength()) && (j >= 0 && j <= gameMap.getWidth())) {//gameMap.gridWithinBounds(i, j)
                        Node neighbor = nodeMap[i][j];

                        if (closed.contains(neighbor)) {
                            continue;
                        }

                        int calculatedCost = neighbor.heuristic + neighbor.moveCost + current.totalCost;

                        if (calculatedCost < neighbor.heuristic || !open.contains(neighbor)) {
                            neighbor.totalCost = calculatedCost;
                            neighbor.parent = current;

                            if (!open.contains(neighbor)) {
                                open.add(neighbor);
                            }
                        }
                    }
                }
            }
        } while (!open.isEmpty());
        //target is unreachable
        return of(start);
    }

    private static List<Cell> extractPath(Node current) {
        List<Cell> path = new ArrayList<Cell>();
        while (current.parent != null) {
            Cell cell = new Cell(current.getGridX(), current.getGridY(), "land");
            path.add(cell);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private static class Node implements Comparable<Node> {
        private int moveCost;
        private int heuristic;
        private int gridX;
        private int gridY;
        private int totalCost;
        private Node parent;

        public Node(int moveCost, int heuristic, int gridX, int gridY) {
            this.moveCost = moveCost;
            this.heuristic = heuristic;
            this.gridX = gridX;
            this.gridY = gridY;
        }

        @Override
        public int compareTo(Node that) {
            return Integer.compare(this.totalCost, that.totalCost);
        }

        public int getGridX() {
            return gridX;
        }

        public int getGridY() {
            return gridY;
        }
    }
}
