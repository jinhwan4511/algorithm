package algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RunBFS {
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
		
		//BFS 호출
		String beginNode = "A";
		ArrayList<String> searchedListByList = bfSearchByList(graph, beginNode);
		ArrayList<String> searchedListByQue = bfSearchByQue(graph, beginNode);
		System.out.println("너비우선탐색 (List) = " + searchedListByList); //ArrayList 사용
		System.out.println("너비우선탐색 (Que) = " + searchedListByQue); //Queue 사용
	}
	
	//BFS(ArrayList로 구현)
	public static ArrayList<String> bfSearchByList(Map<String, ArrayList<String>> graph, String beginNode) {
		//너비우선탐색에 필요한 자료구조
		ArrayList<String> visited = new ArrayList<String>(); //그래프에서 탐색한 노드
		ArrayList<String> neededVisit = new ArrayList<String>(); //탐색할 노드 => Queue 구현
		
		//초기값
		neededVisit.add(beginNode);

		
		//queue를 사용하여 인접한 노드들을 우선적으로 꺼내서 탐색 => 동일 level 탐색(너비 우선)
		//ArrayList로 que가 동작하는것과 동일하게 구현
		while(neededVisit.size() > 0) {
			//pop
			String popNode = neededVisit.remove(0);
			
			if(!visited.contains(popNode)) {
				visited.add(popNode);
				neededVisit.addAll(graph.get(popNode));
			}
		}
		
		return visited;
	}
	
	//BFS(Queue로 구현)
	public static ArrayList<String> bfSearchByQue(Map<String, ArrayList<String>> graph, String beginNode) {
		//너비우선탐색에 필요한 자료구조
		ArrayList<String> visited = new ArrayList<String>(); //그래프에서 탐색한 노드
		Queue<String> neededVisit = new LinkedList<String>(); //탐색할 노드 => Queue 구현
		
		//초기값
		neededVisit.add(beginNode);

		
		//queue를 사용하여 인접한 노드들을 우선적으로 꺼내서 탐색 => 동일 level 탐색(너비 우선)
		while(neededVisit.size() > 0) {
			//pop
			String popNode = neededVisit.remove();
			
			if(!visited.contains(popNode)) {
				visited.add(popNode);
				neededVisit.addAll(graph.get(popNode));
			}
		}
		
		return visited;
	}
}
