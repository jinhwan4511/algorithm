package algorithm.sort;

import java.util.ArrayList;

public class RunQuickSort {
	public static void main(String[] args) {
		/*
		 * 의식의 흐름
		 * Step 1
		 * 분할 & 정렬 -> pivot(기준점)을 이용하여 크기비교를 통해 좌우로 이분할(더 작으면 좌, 더 크면 우) -> 재귀 구현
		 * 			   완료조건 - 분할할 리스트의 길이가 1일때 return
		 * 
		 * Step 2 
		 * 병합 -> 좌 + pivot + 우의 순서로 합쳐준다 
		 */
		
		
		//random list
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 1; i <101; i++) {
			list.add((int)(Math.random()*100));
		}
		
		System.out.println("list = " + list);
		
		//퀵정렬(quick sort)
		ArrayList<Integer> sortedList = quickSort(list);
		
		System.out.println("sorted list = " + sortedList);
	}
	
	public static ArrayList<Integer> quickSort(ArrayList<Integer> inputList) {
		int size = inputList.size();
		
		//완료조건
		if(size <= 1) {
			return inputList;
		}
		
		//pivot
		int pivot = inputList.get(0);
		//분할할 데이터를 담을 리스트
		ArrayList<Integer> leftList = new ArrayList<Integer>();
		ArrayList<Integer> rightList = new ArrayList<Integer>();
		
		//Step1
		//정렬 & 분할
		for(int i = 1; i < size; i++) {
			if(pivot > inputList.get(i)) {
				leftList.add(inputList.get(i));
			} else {
				rightList.add(inputList.get(i));
			}
		}
		
		//Step2
		//병합
		ArrayList<Integer> mergedList = new ArrayList<Integer>();
		mergedList.addAll(quickSort(leftList));
		mergedList.add(pivot);
		mergedList.addAll(quickSort(rightList));
		
		return mergedList;
	}

}
