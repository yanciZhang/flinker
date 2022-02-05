package com.yanci.sort;

import java.util.Arrays;

public class ShellSort {
	
	public static void main(String[] args) {
		
		int[] arr = {1, 4, 77, 3, 5, 0, 12, 3, 5};
		shellSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void shellSort(int[] arr) {
		int len = arr.length;
		for(int s = len/2; s > 0; s /=2) {
			for(int i = s; i < len; i++) {
				int j = i;
				while(j-s >=0 && arr[j] < arr[j - s]) {
					int tem = arr[j];
					arr[j] = arr[j - s];
					arr[j - s] = tem;
					j = j -s;
				}
			}
		}
	}

}
