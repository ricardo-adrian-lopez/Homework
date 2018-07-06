package com.mobileapps.training.testfriday;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomArrayList<E> extends ArrayList<E> {

    private Object [] elements;
    private int initialCapacity;

    private int size;

    public CustomArrayList(int initialCapacity){
        if(initialCapacity>=0){
            this.elements =  new Object[initialCapacity];
            this.initialCapacity = initialCapacity;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean add(E e) {
        checkCapacity(elements.length);
        elements[size++] = e;
        return true;
    }

    private void checkCapacity(int length) {
        if(length>0 && length == initialCapacity){
            length = elements.length *2;
        }else if (elements.length==0){
            length = 10;
        }

        growCapacity(length);
    }

    private void growCapacity(int length) {
        elements = Arrays.copyOf(elements,length);
    }

    public int getSize() {
        return size;
    }
}
