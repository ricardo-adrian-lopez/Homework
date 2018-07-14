package com.mobileapps.training.testfriday;

public class StringCopies {

    public static void main(String[] args) {
        String s = "catcowcat";
        System.out.println("Copies: " + strCopies(s,"cat",2));
        System.out.println("Copies: " + strCopies(s,"cow",2));
        System.out.println("Copies: " + strCopies(s,"cow",1));

    }

    private static boolean strCopies(String s, String sub, int numberOfCopies){
        int n = findCopies(s,sub,0);
        return n== numberOfCopies;
    }

    private static int findCopies(String s, String sub, int n) {
        if(s.contains(sub)){
            s = s.substring(s.indexOf(sub)+sub.length() );
            n++;
            n=findCopies(s,sub,n);
        }else{
            return n;
        }
        return n;
    }

}
