package com.mobileapps.training.testfriday;

public class BinarySearchTree {
    Node rootNode;

    public BinarySearchTree() {
        this.rootNode = null;
    }

    void add(int key){
        rootNode = insertNode(rootNode,key);
    }

    private Node insertNode(Node rootNode, int key) {
        if(rootNode==null){
            rootNode =  new Node(key);
            return rootNode;
        }

        //Insert a new node in one of both pathsSS
        if(key< rootNode.key){
            rootNode.leftPath = insertNode(rootNode.leftPath,key);
        }else if (key > rootNode.key){
            rootNode.rightPath = insertNode(rootNode.rightPath,key);
        }
        return rootNode;
    }
}

class Node{
    int key;
    Node leftPath, rightPath;

    public Node(int key){
        this.key =key;
        this.leftPath=null;
        this.rightPath=null;
    }
}
