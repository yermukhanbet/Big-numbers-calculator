package big_numbers;

import java.util.Iterator;

import data_structures.LinkedList;

// This is where you will write your code for manipulating the big number
// as described in the assignment. 
//
// This class will include the getFirstDigit(), getLastDigit(), addFirstDigit(), addLastDigit()
// methods, as well as the setNegative(), getNegative(), length() methods.
//
// You will also need to write stringToBigNumber() and toString() methods.


public class BigNumber implements Comparable<BigNumber>{
	public LinkedList <Integer> number;
	private boolean isNegative=false;
	public BigNumber() {
		number = new LinkedList<Integer>();
	}
	public int getFirstDigit() {
		return number.peekFirst();
	}
	

	public int getLastDigit() {
		return number.peekLast();
	}
	public void addFirstDigit(int value) {
		number.addFirst(value);
	}
	public void addLastDigit(int value) {
		number.addLast(value);
	}
	public void setNegative(boolean b) {
		this.isNegative=(b==true);
	}
	public boolean getNegative() {
		return isNegative;
	}
	public int length() {
		return number.size();
		}
	public void stringToBigNumber(String s) { 
		for(char c:s.toCharArray()) {
			
			addLastDigit(c-'0');  	//convert string to List
		}
	}
	public  String   toString() {
		StringBuilder answer= new StringBuilder();
			for(int value:number) {
				answer.append(value);
				
			}
			return answer.toString();
		}
	@Override
	public int compareTo(BigNumber o) {
		Iterator<Integer> iterThis = this.number.iterator();
		Iterator<Integer> iterOther = o.number.iterator();
		int answ = 0;
		while (iterThis.hasNext() && iterOther.hasNext()) {
			int numA = iterThis.next();
			int numB = iterOther.next();
			if (numA == numB) continue;
			answ = (numA < numB)?-1:1;
			break;
		}
		return answ;
	}
	}
	


