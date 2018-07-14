package com.mobileapps.training.testfriday;

import java.util.ArrayList;
import java.util.List;

public class ListOfSubstrings {

    public static void main(String[] args) {
        String string = "abcd";
        List<String> list = collectSubstrings(string);
        System.out.println(list);
    }

    private static List<String> collectSubstrings(String string) throws ArrayIndexOutOfBoundsException{
        List<String> collect = new ArrayList<>();
        int skip;
            for (int i = 0; i <string.length() ; i++) {
                    skip=string.length()-i;
                while (skip > 0) {
                    String val = string.substring(i,(string.length()-skip)+1);
                    collect.add(val);
                    skip--;
                }
            }
        return collect;
    }
}
