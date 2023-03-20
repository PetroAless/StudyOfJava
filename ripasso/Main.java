package main_package.ripasso;

import java.util.HashMap;



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
	public static void removeIf(String[] arr,String a,int i) {
		for(++i; i < arr.length ; i++) {
			if(arr[i].compareTo(a)==0) {
				arr[i]="";
			}
		}
	}
	public static void fill(HashMap <String,Integer>toFill, String[]filler) {
		for(int i = 0;i < filler.length;i++) {
			toFill.put(filler[i],1);
		}
	}
	public static void checkIfThereIsRepetition(String str) {
		HashMap <String,Integer>resS = new HashMap<String,Integer>();
		String S;
		String[] strArr=str.split(" ",0);
		fill(resS,strArr);

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

	public static void main(String[] args) {
		/*String strToChk = "parola ciao schermo luce ciao radice "
				+ "schermo parola ciao ciao ciao luce radice";
		checkIfThereIsRepetition(strToChk);*/
		
		
		
		
	}
}
