package com.example.graphtheoryproject.astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AStar {
    private final int numberOfColumns = 8;
    private final int numberOfRows = 8;
    private Node[][] nodes;
    private Node currentNode;
    private Node startNode;
    private Node goalNode;
    private List<Node> openList;
    private List<Node> closeList;
    private List<Node> pathList;

    public void init(){
        try {
            openList = new ArrayList<>();
            closeList = new ArrayList<>();
            pathList = new ArrayList<>();

            nodes = new Node[numberOfColumns][numberOfRows];

            for(int column = 0; column < numberOfColumns; column++){
                for(int row = 0; row < numberOfRows; row++){
                    nodes[column][row] = new Node(column, row);
                    nodes[column][row].setNodeType(NODE_TYPE.NOT_VISITED);
                }
            }


            for(int column = 0; column < numberOfColumns; column++){
                for(int row = 0; row < numberOfRows; row++){
                    createNeighbourList(nodes[column][row]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private boolean isInRange(int column, int row){
        if(column >= 0 && column < numberOfColumns && row >= 0 && row < numberOfRows
        && nodes[column][row].getNodeType() != NODE_TYPE.OBSTACLE){
            return true;
        }

        return false;
    }

    private void createNeighbourList(Node node){
        int column = node.getColumn();
        int row = node.getRow();


        List<Node> neighbourList = new ArrayList<>();

        if(isInRange(column, row - 1)){
            neighbourList.add(nodes[column][row - 1]);
        }

        if(isInRange(column + 1, row - 1)){
            neighbourList.add(nodes[column + 1][row - 1]);
        }

        if(isInRange(column + 1, row)){
            neighbourList.add(nodes[column + 1][row]);
        }

        if(isInRange(column + 1, row + 1)){
            neighbourList.add(nodes[column + 1][row + 1]);
        }

        if(isInRange(column, row + 1)){
            neighbourList.add(nodes[column][row + 1]);
        }

        if(isInRange(column - 1, row + 1)){
            neighbourList.add(nodes[column - 1][row + 1]);
        }

        if(isInRange(column - 1, row)){
            neighbourList.add(nodes[column - 1][row]);
        }

        if(isInRange(column - 1, row - 1)){
            neighbourList.add(nodes[column - 1][row - 1]);
        }

        node.setNeighbourList(neighbourList);
    }

    public void start(int column, int row){
        nodes[column][row].setNodeType(NODE_TYPE.START);
        startNode = nodes[column][row];
        currentNode = startNode;

        openList.add(currentNode);

    }

    public void goal(int column, int row){
        nodes[column][row].setNodeType(NODE_TYPE.GOAL);
        goalNode = nodes[column][row];

        for(Node node : currentNode.getNeighbourList()){
            calculateFCost(node);
        }
    }

    public void obstacle(int column, int row){
        nodes[column][row].setNodeType(NODE_TYPE.OBSTACLE);
    }

    private int calculateGCost(Node node){
        int gCost = currentNode.getgCost() + getDistance(currentNode, node);
        node.setgCost(gCost);
        return gCost;
    }

    private int calculateHCost(Node node){
        int hCost = getDistance(node, goalNode);
        node.sethCost(hCost);
        return hCost;
    }

    private int getDistance(Node nodeA, Node nodeB){
        int distanceX = Math.abs(nodeA.getColumn() - nodeB.getColumn());
        int distanceY = Math.abs(nodeA.getRow() - nodeB.getRow());

        int distance = 0;

        if(distanceX > distanceY)
            distance = 14 * distanceY + 10 * (distanceX - distanceY);
        else
            distance = 14 * distanceX + 10 * (distanceY - distanceX);

        return distance;

    }

    private void calculateFCost(Node node){
        if(node.getNodeType() == NODE_TYPE.NOT_VISITED){
            node.setNodeType(NODE_TYPE.VISITED);
            node.setgCost(calculateGCost(node));
            node.sethCost(calculateHCost(node));
            node.setfCost(node.getgCost() + node.gethCost());
        }
    }

    public void run(){
        while(openList.size() > 0){



            currentNode = openList.get(0);
            for(int i = 1; i < openList.size(); i++){
                if(openList.get(i).getfCost() < currentNode.getfCost() ||
                        (openList.get(i).getfCost() == currentNode.getfCost()) &&
                                openList.get(i).gethCost() < currentNode.gethCost()){
                    currentNode = openList.get(i);
                }
            }

            openList.remove(currentNode);
            closeList.add(currentNode);

            if(currentNode == goalNode){
                return;
            }

            for(Node neighbour : currentNode.getNeighbourList()){
                if(closeList.contains(neighbour)){
                    continue;
                }

                calculateFCost(neighbour);

                int newMovementCost = currentNode.getgCost() + getDistance(neighbour, currentNode);
                if(neighbour.getParent() == null)
                    neighbour.setParent(currentNode);

                if(newMovementCost < neighbour.getgCost() || !openList.contains(neighbour)){
                    neighbour.setgCost(newMovementCost);
                    neighbour.sethCost(getDistance(neighbour, goalNode));
                    neighbour.setfCost(neighbour.getgCost() + neighbour.gethCost());

                    if(!closeList.contains(neighbour) && !openList.contains(neighbour)
                            && neighbour.getNodeType() != NODE_TYPE.OBSTACLE) {
                        openList.add(neighbour);
                    }
                }
            }
        }
    }

    public List<Node> findPath(){
        while (currentNode != startNode){
            pathList.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(pathList);

        return pathList;
    }
}
