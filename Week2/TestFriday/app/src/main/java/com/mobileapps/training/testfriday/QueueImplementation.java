package com.mobileapps.training.testfriday;

import java.util.Stack;

public class QueueImplementation<E> {

    private Stack<E> inStack = new Stack<E>();
    private Stack<E> outStack =  new Stack<>();

    public void queue(E item){
        inStack.push(item);
    }

    public E dequeue(){
        if(outStack.size()==0){
            while(inStack.size()>0){
                outStack.push(inStack.pop());
            }
        }

        return outStack.pop();
    }
}
