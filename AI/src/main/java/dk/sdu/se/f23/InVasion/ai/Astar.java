package dk.sdu.se.f23.InVasion.ai;

import dk.sdu.se.f23.InVasion.common.data.Point;
import dk.sdu.se.f23.InVasion.common.data.World;
import dk.sdu.se.f23.InVasion.commonenemy.AIType;
import dk.sdu.se.f23.InVasion.commonenemy.services.ActionService;

import java.util.*;

public class Astar implements ActionService {
    private List<Point> points = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> grid;
    private PriorityQueue<Node> openList;
    private HashSet<Node> closedList;

    private int startI, startJ, endI, endJ;

    public Astar() {
    }

    private static class Node {
        int x, y, f, g, h;
        Node parent;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public void findPath() {
        Node startNode = new Node(startI, startJ);
        startNode.f = 0;
        startNode.g = 0;
        startNode.h = 0;
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedList.add(currentNode);

            if (currentNode.x == endI && currentNode.y == endJ) {
                printPath(currentNode);
                return;
            }

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int newX = currentNode.x + i;
                    int newY = currentNode.y + j;

                    if (!isValid(newX, newY) || !isAccessible(newX, newY)) {
                        continue;
                    }

                    Node childNode = new Node(newX, newY);
                    childNode.g = currentNode.g + 1;
                    double weight = 1;
                    childNode.h = Math.abs(newX - endI) + (int) (weight * Math.abs(newY - endJ));
                    childNode.f = childNode.g + childNode.h;
                    childNode.parent = currentNode;

                    if (nodeInOpenList(childNode) && childNode.g >= currentNode.g) {
                        continue;
                    }
                    if (!nodeInClosedList(childNode) || childNode.g < currentNode.g) {
                        openList.add(childNode);
                    }
                }
            }
        }
    }


    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < grid.size() && y < grid.get(0).size();
    }

    private boolean isAccessible(int x, int y) {
        return grid.get(x).get(y) == 2;  // 2 indicates the cell is walkable.
    }

    private boolean nodeInOpenList(Node node) {
        return openList.stream().anyMatch(n -> n.x == node.x && n.y == node.y);
    }

    private boolean nodeInClosedList(Node node) {
        return closedList.stream().anyMatch(n -> n.x == node.x && n.y == node.y);
    }

    private void printPath(Node node) {
        if (node == null) return;
        printPath(node.parent);
        points.add(new Point(node.x, node.y));
    }


    @Override
    public List<Point> calculate(World world) {
        if (!points.isEmpty()) {
            return points;
        }
        this.startI = world.getInitState().getX();
        this.startJ = world.getInitState().getY();
        this.endI = world.getGoalState().getX();
        this.endJ = world.getGoalState().getY();

        this.openList = new PriorityQueue<>(Comparator.comparingInt(node -> node.f));

        this.closedList = new HashSet<>();
        this.grid = world.getWorldMask();
        this.findPath();
        return points;
    }

    @Override
    public AIType getAiType() {
        return AIType.A_STAR;
    }
}