package com.mobileapps.training.testfriday;

import java.util.HashMap;
import java.util.Map;

public class EncodingProblem {

    private static final String [] alpahabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    public static void main(String[] args) {
        String result = "Errors in strategy cannot be correct through tactical maneuvers";
        String countLetters =  "Hello there! Apple!";
        result = f(result);
        Map<String,Integer> map = g(countLetters);
        //Errlrh rm hgrzgegy xzmmlg ye xlrrexg ghrlugh gzxgrxzl mzmeueerh
        System.out.println(result);

        // {a=1, b=0, c=0, d=0, e=4, f=0, g=0, h=2, i=0, j=0, k=0, l=3, m=0, n=0, o=1, p=2, q=0, r=1, s=0, t=1, u=0, v=0, w=0, x=0, y=0, z=0}
        System.out.println(map);
    }

    public static String f(String val){
        for(int i = 0; i <val.length();i++){
            int cont = 1;
            for(int j = 0; j<alpahabet.length;j++){
                if(Character.toString(val.charAt(i)).equals(alpahabet[j])){
                    val = val.replace(Character.toString(val.charAt(i)),alpahabet[alpahabet.length-cont]);
                    break;
                }
                cont++;
            }
        }
        return val;
    }

    public static Map<String,Integer> g(String val){
        val =  val.toLowerCase();
        Map<String,Integer> map = new HashMap<>();
        for(int j = 0; j < alpahabet.length; j++){
            int cont=0;
            for(int i = 0;i<val.length();i++){
                if(Character.toString(val.charAt(i)).equals(alpahabet[j])){
                    cont++;
                }
            }
            map.put(alpahabet[j],cont);
        }
        return map;
    }
}
