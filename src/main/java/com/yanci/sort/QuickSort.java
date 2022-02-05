package com.yanci.sort;

import java.util.Arrays;

public class QuickSort {
	
	public static void main(String[] args) {
		int[] arr = {1, 4, 77, 3, 5, 0, 12, 3, 5};
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
		
		byte b = 10, b2 = 20;
		byte bb = b++;
		System.out.println(bb);
	}

	public static void quickSort(int[] arr, int start, int end) {
		if(start >= end) {
			return;
		}
		int l = start;
		int r = end;
		int key = arr[l];
		while(l < r) {
			while(l < r && arr[r] >= key) {
				r--;
			}
			arr[l] = arr[r];
			while(l < r && arr[l] <= key) {
				l++;
			}
			arr[r] = arr[l];
		}
		
		arr[l] = key;
		quickSort(arr, start, l - 1);
		quickSort(arr, l + 1, end);
		
	}
}
