package algorithm.search;

import java.util.Arrays;

public class RunBinarySearch {
	public static void main(String[] args) {
		//random array
		int[] tmpArr = new int[10];
	
		for(int i = 0; i < tmpArr.length; i++) {
			tmpArr[i] = (int)(Math.random()*100);
		}
		
		//이진탐색을 위한 정렬
		Arrays.sort(tmpArr);
		
		System.out.println("tmpArr = " + Arrays.toString(tmpArr));
		
		int target = 7;
		//binary search by loop
		System.out.println(target + "이 존재하나요? : " + binarySearchByLoop(tmpArr, target));
		//binary search by recursive function
		System.out.println(target + "이 존재하나요? : " + binarySearchByRecursion(tmpArr, target));
	}
	
	//반복문을 활용한 이진탐색
	public static boolean binarySearchByLoop(int[] inputArr, int target) {
		//의식의 흐름
		//Step1 반복해서 2등분하여 범위를 좁혀 탐색
		//중간인덱스의 값과 target을 비교
		//case1 탐색 완료 => return
		//case2 target이 더 작을 때
		//case3 target이 더 클때
		//case4 target이 없을 때
		//필요한 변수 : 최소, 최대, 중간idx
		//wile문 조건 : 최소 idx <= 최대idx
		
		int maxIdx = inputArr.length-1;
		int minIdx = 0;
		
		int cnt = 0;
		
		//불필요한 반복제거(1개원소일 때)
		//maxIdx == minIdx
		if(inputArr.length == 1) {
			cnt++;
			int tmpValue = inputArr[0];
			if(tmpValue == target) {
				return true;
			} else {
				return false;
			}
		}
		
		//이진탐색
		while(minIdx <= maxIdx) {
			cnt++;
			int midIdx = minIdx + maxIdx / 2;
			
			//case1 더 작을 경우
			if(inputArr[midIdx] > target) {
				maxIdx = midIdx - 1;
			
			//case2 더 클 경우	
			} else if(inputArr[midIdx] < target) {
				minIdx = midIdx + 1;
			
			//case3 같을 경우
			} else {
				System.out.println("search count = " + cnt);
				return true;
			}
		}
		//case4 탐색 대상이 없을 경우
		System.out.println("search count = " + cnt);
		return false;
	}
	
	public static boolean binarySearchByRecursion(int[] inputArr, int target) {
		/*
		 * 의식의 흐름
		 * Step1 재귀를 사용하여 2분할 구현
		 * 재귀종료조건 : 1개의 원소까지 2분할
		 * case는 위의 반복문으로 구현한것과 동일
		 */
		
		//완료조건
		if(inputArr.length == 1) {
			if(inputArr[0] == target) {
				return true;
			} else {
				return false;
			}

		}
		
		//재귀구현
		int midIdx = (inputArr.length-1) / 2;
		
		//case1 탐색완료
		if(inputArr[midIdx] == target) {
			return true;
			
		} else {
			//case2 더 작을 때
			if(inputArr[midIdx] > target) {
				int[] tmpArr = Arrays.copyOfRange(inputArr, 0, midIdx);
				return binarySearchByRecursion(tmpArr, target);
				
			//case3 더 클 때	
			} else {
				int[] tmpArr = Arrays.copyOfRange(inputArr, midIdx+1, inputArr.length);
				return binarySearchByRecursion(tmpArr, target);	
			}
		}
		
	}

}