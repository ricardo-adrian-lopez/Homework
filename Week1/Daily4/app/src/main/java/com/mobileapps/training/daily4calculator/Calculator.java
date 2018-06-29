package com.mobileapps.training.daily4calculator;

public class Calculator {

    private Double numberOne;
    private Double numberTwo;
    private String operand;

    public Double getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(String numberOne) {
        this.numberOne = Double.valueOf(numberOne);
    }

    public Double getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(String numberTwo) {
        this.numberTwo = Double.valueOf(numberTwo);
    }

    public String getOperand() {
        return operand;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }

    @Override
    public String toString() {
        if(numberOne==null || numberOne==0){
            return "0";
        }else if( (operand==null || "".equals(operand))  && (numberTwo==null || numberTwo == 0)){
            return String.format("%.0f",numberOne);
        }else if (operand!=null && !"".equals(operand) && (numberTwo==null || numberTwo==0)){
            return String.format("%.0f",numberOne)+operand;
        }else{
            return String.format("%.0f",numberOne)+operand+String.format("%.0f",numberTwo);
        }
    }

    public String getResult(){
        if( (numberOne!=null) && (operand!=null && !"".equals(operand)) && (numberTwo!=null && numberTwo!=null)){
            switch (operand){
                case "x": return String.valueOf(numberOne*numberTwo);
                case "-": return String.valueOf(numberOne-numberTwo);
                case "+": return String.valueOf(numberOne+numberTwo);
                case "/": return String.valueOf(numberOne/numberTwo);
                default:return "0";
            }
        }else {
            return this.toString();
        }
    }
}
