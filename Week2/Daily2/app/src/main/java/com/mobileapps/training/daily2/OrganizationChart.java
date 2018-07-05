package com.mobileapps.training.daily2;

import java.util.ArrayList;
import java.util.List;

public class OrganizationChart {

    public static void main(String[] args) {

        List<String> employeesList = new ArrayList<>();
        employeesList.add("B2,E5,F6");
        employeesList.add("A1,B2,C3,D4");
        employeesList.add("D4,G7,I9");
        employeesList.add("G7,H8");

        String topManager = findStringWithTopManager(employeesList);
        //System.out.println("String containing top manager: "+topManager);
        assignEmployees(employeesList, topManager);
        //System.out.println(employeesList);
        topManager = topManager.replace(",","\n\t");
        String finalChart = formatEmployees(employeesList,topManager);
        System.out.println("Final chart\n" + finalChart);
    }

    private static String formatEmployees(List<String> employeesList, String topManager) {
        String finalFormat=topManager;
        for(int i = 0; i<employeesList.size();i++){
            String [] employees = employeesList.get(i).split(",");
            if(topManager.contains(employees[0]) && !topManager.startsWith(employees[0])){
                String addToFinal="";
                int j = 0;
                while(j < employees.length){
                    addToFinal+=employees[j]+"\n\t\t";
                    j++;
                }
                addToFinal = addToFinal.substring(0,addToFinal.lastIndexOf("\n"));
                finalFormat = finalFormat.replace(employees[0],addToFinal);
            }
        }
        return finalFormat;
    }

    private static void assignEmployees(List<String> employeesList, String topManager) {
        for(int i=0;i<employeesList.size();i++){
            String[] employees = employeesList.get(i).split(",");
            if(topManager.contains(employees[0]))
            {
                continue;
            }else{ //Only strings not in topManager will get assign to others
                int j = 0;
                String addToFinal="";
                while(j<employees.length){ //Final format
                    addToFinal += employees[j] +","+ "\t";
                    j++;
                }
                addToFinal = addToFinal.substring(0,addToFinal.lastIndexOf(","));
                for(int k = 0;k<employeesList.size();k++){
                    if(i != k){
                        if(employeesList.get(k).contains(employees[0])){
                            String replaceStr = employeesList.get(k);
                            replaceStr = replaceStr.replace(employees[0], addToFinal);
                            employeesList.set(k, replaceStr);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static String findStringWithTopManager(List<String> employeesList) {
        String top = null;
        for(int i=0;i<employeesList.size();i++){
            String [] strManager = employeesList.get(i).split(",");
            for(int j=0;j<employeesList.size();j++){
                if(i != j){
                    if(employeesList.get(j).contains(strManager[0])){
                        top = null;
                        break;
                    }else{
                        top=employeesList.get(i);
                        continue;
                    }
                }
            }

            if(top!=null){
                return top;
            }
        }
        return top;
    }
}
