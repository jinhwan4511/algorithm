package algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RunDFS {
	public static void main(String[] args) {
		//그래프 구현
		HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
		
		graph.put("A", new ArrayList<String>(Arrays.asList("B", "C")));
		graph.put("B", new ArrayList<String>(Arrays.asList("A", "D")));
		graph.put("C", new ArrayList<String>(Arrays.asList("A", "G", "H", "I")));
		graph.put("D", new ArrayList<String>(Arrays.asList("B", "E", "F")));
		graph.put("E", new ArrayList<String>(Arrays.asList("D")));
		graph.put("F", new ArrayList<String>(Arrays.asList("D")));
		graph.put("G", new ArrayList<String>(Arrays.asList("C")));
		graph.put("H", new ArrayList<String>(Arrays.asList("C")));
		graph.put("I", new ArrayList<String>(Arrays.asList("C", "J")));
		graph.put("J", new ArrayList<String>(Arrays.asList("I")));
		
		//DFS 호출
		String beginNode = "A";
		ArrayList<String> searchedListByList = dfSearchByList(graph, beginNode);
		ArrayList<String> searchedListByStack = dfSearchByStack(graph, beginNode);
		System.out.println("깊이우선탐색 (List) = " + searchedListByList); //ArrayList 사용
		System.out.println("깊이우선탐색 (Stack) = " + searchedListByStack); //Stack 사용
	}
	
	//ArrayList 사용
	public static ArrayList<String> dfSearchByList(Map<String, ArrayList<String>> graph, String beginNode) {
		//깊이우선탐색에 필요한 자료구조
		ArrayList<String> visited = new ArrayList<String>(); //그래프에서 탐색한 노드
		ArrayList<String> neededVisit = new ArrayList<String>(); //탐색할 노드 => Stack 구현
		
		//초기값
		neededVisit.add(beginNode);
		
		//Stack을 사용하여 한 갈래를 끝까지 꺼내서 탐색 => 깊이 우선
		//ArrayList로 Stack이 동작하는것과 동일하게 구현
		while(neededVisit.size() > 0) {
			//pop
			String popNode = neededVisit.remove(neededVisit.size()-1);
			
			if(!visited.contains(popNode)) {
				visited.add(popNode);
				neededVisit.addAll(graph.get(popNode));
			}
		}
		
		return visited;
	}
	
	//Stack 사용
	public static ArrayList<String> dfSearchByStack(Map<String, ArrayList<String>> graph, String beginNode) {
		//깊이우선탐색에 필요한 자료구조
		ArrayList<String> visited = new ArrayList<String>(); //그래프에서 탐색한 노드
		Stack<String> neededVisit = new Stack<String>(); //탐색할 노드 => Stack 구현
		
		//초기값
		neededVisit.add(beginNode);
		
		//Stack을 사용하여 한 갈래를 끝까지 꺼내서 탐색 => 깊이 우선
		while(neededVisit.size() > 0) {
			//pop
			String popNode = neededVisit.pop();
			
			if(!visited.contains(popNode)) {
				visited.add(popNode);
				neededVisit.addAll(graph.get(popNode));
			}
		}
		
		return visited;
	}

}
