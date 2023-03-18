package main_package;

import java.util.HashMap;
import java.util.Iterator;

public class Main {
	
	public static void swap(int x, int y) {
		int temp = x;
		x = y;
		y = temp;
		return;
	}
	public static void removeIf(String[] arr,String a,int i) {
		for(++i; i < arr.length ; i++) {
			if(arr[i].compareTo(a)==0) {
				arr[i]="";
			}
		}
	}
	public static void main(String[] args) {
		String strToChk = "parola ciao schermo luce ciao radice "
				+ "schermo parola ciao ciao ciao luce radice";
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

}
