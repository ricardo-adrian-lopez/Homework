package com.mobileapps.training.testfriday;

import java.util.Arrays;

public class SortArray {

    public static void main(String[] args) {
        int [] unsorted = {2,8,9,3,4,3,2,6,6,2,4,9,8};

        for(int i = 0; i<unsorted.length; i++){
            for(int j = i+1;j<unsorted.length; j++){
                if(unsorted[i]>unsorted[j]){
                    int aux = unsorted[i];
                    unsorted[i]= unsorted[j];
                    unsorted[j]=aux;
                }
            }
        }
        System.out.println(Arrays.toString(unsorted));
    }
}
