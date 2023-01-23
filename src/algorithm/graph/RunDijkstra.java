package algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;



public class RunDijkstra {
	public static void main(String[] args) {
		
		//각 노드간 연결관계를 나타내는 그래프 생성
		Map<String, ArrayList<Node>> graph = new HashMap<String, ArrayList<Node>>();
		graph.put("A", new ArrayList<Node>(Arrays.asList(new Node("B", 8), new Node("C", 1), new Node("D", 2))));
		graph.put("B", new ArrayList<Node>());
		graph.put("C", new ArrayList<Node>(Arrays.asList(new Node("B", 5), new Node("D", 2))));
		graph.put("D", new ArrayList<Node>(Arrays.asList(new Node("E", 3), new Node("F", 5))));
		graph.put("E", new ArrayList<Node>(Arrays.asList(new Node("F", 1))));
		graph.put("F", new ArrayList<Node>(Arrays.asList(new Node("A", 5))));
		
		//각 노드의 최단경로 구하기
		Map<String, Integer> result = dijkstra(graph, "A");
		
		System.out.println("A노드에서 각 노드별 최단경로 = " + result);
	}
	
	private static Map<String, Integer> dijkstra(Map<String, ArrayList<Node>> graph, String beginNode) {
		
		Map<String, Integer> result = new HashMap<String, Integer>(); //특정 노드에서 각 노드의 최단거리를 저장하는 map
		Queue<Node> que = new PriorityQueue<Node>(); //탐색 순서 (최단거리 순)
		
		//사용할 변수
		Node stndNode;
		int currDistance, toDistance, totDistance;
		String currNode, toNode;
		ArrayList<Node> adjacentNodes;
		
		//초기화
		//1 반환할 결과에 각 node를 key로 배치, value는 int 최댓값
		for(String key : graph.keySet()) {
			result.put(key, Integer.MAX_VALUE);
		}
		
		//2 priority que 시작 노드 배치
		result.put(beginNode, 0);
		Node node = new Node(beginNode, result.get(beginNode));
		que.add(node);
		
		//다익스트라 알고리즘
		while(que.size() > 0) {
			//출발 기준 node
			stndNode = que.poll(); //최단거리 순
			currNode = stndNode.node;
			currDistance = stndNode.distance;
			
			if(result.get(currNode) < currDistance) { //이미 최단경로가 존재하기 때문에 현재의 경우는 의미없으니 pass
				continue;
			}
			
			//인접노드들
			adjacentNodes = graph.get(currNode);
			
			for(Node adjacentNode : adjacentNodes) {
				toNode = adjacentNode.node;
				toDistance = adjacentNode.distance;
				
				totDistance = currDistance + toDistance;
				
				if(totDistance < result.get(toNode)) {
					result.put(toNode, totDistance);
					que.add(new Node(toNode, totDistance)); //최단경로인 경우를 기준으로 다시 탐색하기 위해서 추가
				}
			}
		}
		
		return result;
	}
}

//getter, setter 사용x
class Node implements Comparable<Node> {
	public String node; 
	public int distance;
	
	public Node(String node, int distance) {
		this.node = node;
		this.distance = distance;
	}
	
	//객체데이터의 정렬기준 정의
	@Override
	public int compareTo(Node compareNode) {
		if(this.distance < compareNode.distance) {
			return 1;
		} else if(this.distance > compareNode.distance) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return "node : " + this.node + ", distance : " + this.distance;
	}
}
