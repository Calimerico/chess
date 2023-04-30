package com.chess.chess.openingparser;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String data;
    private Node parent;
    private List<Node> children;

    public Node(String data, Node parent) {
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public Node getParent() {
        return parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        if (parent != null) {
            return parent + data;
        } else {
            return data;
        }
    }
}
