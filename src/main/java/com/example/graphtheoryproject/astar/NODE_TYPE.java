package com.example.graphtheoryproject.astar;

public enum NODE_TYPE {
    START(0),
    GOAL(1),
    OBSTACLE(2),
    NOT_VISITED(3),
    VISITED(4);

    private int nodeType;

    NODE_TYPE(int nodeType){
        this.nodeType = nodeType;
    }
}
