package algorithm.sort;

import java.util.ArrayList;

public class RunMergeSort {
	public static void main(String[] args) {
		//의식의 흐름
		//Step1
		//분할정복 -> 매개변수에 들어온 리스트를 분할(이분할 left,right) -> 길이가 1까지 -> 재귀로 구현
		//		   필요한 변수 - middlepoint : 이분할 지점
		//		   완료조건 - size가 1일때 return
		//Step2
		//병합 & 정렬 -> while반복문으로 하나의 return 타입 리스트에 정렬시키면 담아낸다
		//			  각 left, right list의 idx를 나타내는 point필요
		//			  Case1 - 크기비교를 통해 그대로 넣음(담아야할 각 리스트의 데이터 수 보다 point가 작을 때)
		//			  Case2 - left 리스트에 데이터가 남은 경우 -> 그대로 left를 모두 넣음(이미 정렬된 상태니깐)
		//			  Case3 - right 리스트가 남은 경우 -> case2와 동일하게 처리 
		
		
		//random list
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i = 1; i <101; i++) {
			list.add((int)(Math.random()*100));
		}
		
		System.out.println("list = " + list);
		
		//병합정렬(merge sort)
		SortingObj sort = new SortingObj();
		ArrayList<Integer> sortedList = sort.mergeSort(list);
		
		System.out.println("sorted list = " + sortedList);
	}

}


class SortingObj {
	
	public ArrayList<Integer> mergeSort(ArrayList<Integer> inputList) {
		
		int listSize = inputList.size();
		
		//Step1 재귀 완료조건
		if(listSize <= 1) {
			return inputList;
		}
		
		//Step1 분할정복->재귀 (left, right 이분할)
		//middle-point
		int middlePoint = listSize / 2;
		//분할(재귀)
		ArrayList<Integer> leftList = mergeSort(new ArrayList<Integer>(inputList.subList(0, middlePoint)));
		ArrayList<Integer> rightList = mergeSort(new ArrayList<Integer>(inputList.subList(middlePoint, listSize)));		
		
		return mergeFunc(leftList, rightList);
	}
	
	private ArrayList<Integer> mergeFunc(ArrayList<Integer> leftList, ArrayList<Integer> rightList) {
		
		ArrayList<Integer> mergedList = new ArrayList<Integer>();
		int leftSize = leftList.size();
		int rightSize = rightList.size();
		
		
		//Step2 병합 & 정렬(오름차순)
		//point
		int leftPoint = 0;
		int rightPoint = 0;
		
		//병합
		//Case1 - 두 리스트에 병합할 대상이 존재할 때
		while(leftSize > leftPoint && rightSize > rightPoint) {
			int rightListValue = rightList.get(rightPoint);
			int leftListValue = leftList.get(leftPoint);
			
			//오름차순 정렬
			if(leftListValue > rightListValue) {
				mergedList.add(rightListValue);
				rightPoint ++;
			} else {
				mergedList.add(leftListValue);
				leftPoint ++;
			}
		}
		
		//Case2 - left에만 데이터가 남았을 때
		while(leftSize > leftPoint) {
			mergedList.add(leftList.get(leftPoint));
			leftPoint ++;
		}
		
		//Case3 - right에만 데이터가 남았을 때
		while(rightSize > rightPoint) {
			mergedList.add(rightList.get(rightPoint));
			rightPoint ++;
		}
		
		return mergedList;
	}
}