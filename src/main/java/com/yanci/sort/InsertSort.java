package com.yanci.sort;

import java.util.Arrays;

public class InsertSort {
	
	public static void main(String[] args) {
		int[] arr = {1, 4,77, 3,5,0,12};
		
		for(int i =1; i<arr.length; i++) {
			for(int j=i;j>0;j--) {
				int tem = arr[j-1];
				if(arr[j] < tem) {
					arr[j-1] = arr[j];
					arr[j] = tem;
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));

	}

}
