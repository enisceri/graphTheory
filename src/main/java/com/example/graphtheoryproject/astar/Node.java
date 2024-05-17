package com.example.graphtheoryproject.astar;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int column;
    private int row;
    private NODE_TYPE nodeType;
    private List<Node> neighbourList;
    private int gCost;
    private int hCost;
    private int fCost;
    private Node parent;

    public Node(int column, int row){
        this.column = column;
        this.row = row;

        setNeighbourList(new ArrayList<>());
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public NODE_TYPE getNodeType() {
        return nodeType;
    }

    public void setNodeType(NODE_TYPE nodeType) {
        this.nodeType = nodeType;
    }

    public List<Node> getNeighbourList() {
        return neighbourList;
    }

    public void setNeighbourList(List<Node> neighbourList) {
        this.neighbourList = neighbourList;
    }

    public int getgCost() {
        return gCost;
    }

    public void setgCost(int gCost) {
        this.gCost = gCost;
    }

    public int gethCost() {
        return hCost;
    }

    public void sethCost(int hCost) {
        this.hCost = hCost;
    }

    public int getfCost() {
        return fCost;
    }

    public void setfCost(int fCost) {
        this.fCost = fCost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
