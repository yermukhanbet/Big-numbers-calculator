package big_numbers;

import java.util.Iterator;

import data_structures.LinkedList.IteratorHelper;

// This is where you will write the calculator functions
//
// This class only has one public method:


public class Calculator {
	public Calculator() {
		
	}
    
	public String calculate(String number1, String operation, String number2) {
		BigNumber n1=new BigNumber(),n2=new BigNumber();
		if(number1.charAt(0)=='-') {    // remove minus sign if present and shorten the string to push to linked list
			n1.setNegative(true);
			  number1=number1.substring(1);
			}
		if(number2.charAt(0)=='-'){
			n2.setNegative(true);
			number2=number2.substring(1);
			
			}
		// convert to big number class
		n1.stringToBigNumber(number1);
		
		n2.stringToBigNumber(number2);
		int diff=n1.length()-n2.length();  // make length equal 
		if(diff>0) {
			for(int i=0;i<diff;i++) {
				n2.addFirstDigit(0);
			}
		}
		else if(diff<0) {
			for(int i=0;i<Math.abs(diff);i++) {
				n1.addFirstDigit(0);
			}
		}
		switch (operation) {
		case "+":
			return ADD(n1,n2);
		case "-":
			return SUB(n1,n2);
		}
		
		
		return null;
	}
	private String ADD(BigNumber num1, BigNumber num2) {
		if(num1.getNegative() && num2.getNegative()) {
			return "-"+addition(num1,num2);
		}
		else if(num1.getNegative() && !num2.getNegative()) {
			num1.setNegative(false);
			return SUB(num2,num1);
		}
		else if(!num1.getNegative() && num2.getNegative()) {
			num2.setNegative(false);
			return SUB(num1, num2);
		}
		return addition(num1, num2);
	}
	private String SUB(BigNumber num1, BigNumber num2) {
		
		if(!num1.getNegative() &&! num2.getNegative()){
			if(num1.compareTo(num2)<0) {
				return "-"+ substraction(num2,num1);
			}
			else if(num1.compareTo(num2)>0) {
				return substraction(num1, num2);
			}
			else return "0";
		}
		else if(num1.getNegative() && num2.getNegative()) {
			num1.setNegative(false);
			num2.setNegative(false);
			return SUB(num2,num1);
		}
		else if(num1.getNegative() && !num2.getNegative()) {
			num1.setNegative(false);
			return "-"+ADD(num1, num2);
		}
		else {
			return ADD(num1,num2);
		}
	}
	
	private String addition(BigNumber num1, BigNumber num2) {
		int carry =0;  
		StringBuilder answer= new StringBuilder();// for the answer to easily modify
		while(num1.length()!=0) {
			
			int n1=num1.number.removeLast();  // get last numbers and  perform addition on them 
			int n2=num2.number.removeLast();
			int pushValue=n1+n2+carry;
			if(pushValue>9) 
				carry=1;
				
			else carry=0; 
			pushValue=pushValue%10;
			answer.insert(0,pushValue);
		}
		if(carry==1) {
			answer.insert(0, 1);
		}
		return answer.toString();
	}
	private String substraction(BigNumber number1, BigNumber number2) {
		int carry=0;
		StringBuilder answer=new StringBuilder();
		while(number1.length()!=0 && number2.length()!=0) {
			
			int n1=number1.number.removeLast()-carry;
			int n2=number2.number.removeLast();
			if(n1<n2) {
				n1=n1+10;
				carry=1;
			}
			else carry=0;
			int pushValue=n1-n2;
			answer.insert(0,pushValue);
		}
		return answer.toString();
		
		
	}

}
