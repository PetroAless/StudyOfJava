package main_package;

import java.util.HashMap;
import java.util.Iterator;

public class Main {


	public int fibo(int n){
		if(n==0) {
			return 0;
		} else if (n==1) {
			return 1;
		} else {
			return fibo(n - 1) + fibo(n - 2);
		}

	}

	public static void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
		return;
	}
	public static void printStringRepetitionInAString(String strToChk){

		HashMap <String,Integer>resS = new HashMap<String,Integer>();
		String S = "";
		boolean check = false;
		String[] strArr=strToChk.split(" ",0);
		for(int i = 0;i < strArr.length;i++) {
			resS.put(strArr[i],1);
		}

		for(int i = 0;i < strArr.length;i++) {
			if(strArr[i].compareTo("")==0) {
				continue;
			}
			S=strArr[i];

			for(int j = i+1;j < strArr.length;j++) {
				if((S.compareTo(strArr[j]))==0) {
					resS.replace(S,resS.get(S)+1);
				}
			}
			removeIf(strArr,S,i);
		}
		System.out.println(resS);
	}
	public static void removeIf(String[] arr,String a,int i) {
		for(++i; i < arr.length ; i++) {
			if(arr[i].compareTo(a)==0) {
				arr[i]="";
			}
		}
	}
	public static void main(String[] args) {
		Main m = new Main();

	}

}
