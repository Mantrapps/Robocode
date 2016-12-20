package access;

import java.util.ArrayList;

public class Tree {
	public class Node {
        private String data;
        private Node parent;
        private ArrayList<Node> children;
    }
	private Node root;

    public Tree(String rootData) {
        root = new Node();
        root.data = rootData;
        root.children = new ArrayList<Node>();
    }
}
