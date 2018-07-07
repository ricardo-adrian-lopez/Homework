package com.mobileapps.training.testfriday;

public class LinkedList {

    LinkedNode head;
    public LinkedList(String data){
        head = new LinkedNode(data);
    }

    public void add(String data){
        while(head.next!=null){
            head=head.next;
        }
        head.next = new LinkedNode(data);
    }
}

class LinkedNode{

    String data;
    LinkedNode next;

    public LinkedNode(String data){
        this.data=data;
    }
}


