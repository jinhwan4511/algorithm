package algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//최소신장트리 - 크루스칼 알고리즘
public class RunKruskal {
	public static void main(String[] args) {
		//초기 그래프 정보
		//노드 리스트
		ArrayList<String> nodes = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
		//간선 리스트
		ArrayList<Edge> edges = new ArrayList<Edge>();
	    edges.add(new Edge(7, "A", "B"));
	    edges.add(new Edge(5, "A", "D"));
	    edges.add(new Edge(7, "B", "A"));
	    edges.add(new Edge(8, "B", "C"));
	    edges.add(new Edge(9, "B", "D"));
	    edges.add(new Edge(7, "B", "E"));
	    edges.add(new Edge(8, "C", "B"));
	    edges.add(new Edge(5, "C", "E"));
	    edges.add(new Edge(5, "D", "A"));
	    edges.add(new Edge(9, "D", "B"));
	    edges.add(new Edge(7, "D", "E"));
	    edges.add(new Edge(6, "D", "F"));
	    edges.add(new Edge(7, "E", "B"));
	    edges.add(new Edge(5, "E", "C"));
	    edges.add(new Edge(7, "E", "D"));
	    edges.add(new Edge(8, "E", "F"));
	    edges.add(new Edge(9, "E", "G"));
	    edges.add(new Edge(6, "F", "D"));
	    edges.add(new Edge(8, "F", "E"));
	    edges.add(new Edge(11, "F", "G"));
	    edges.add(new Edge(9, "G", "E"));
	    edges.add(new Edge(11, "G", "F"));
		
	    Kruskal kruskal = new Kruskal();
	    ArrayList<Edge> result = kruskal.doKruskal(nodes, edges);
	    System.out.println("result = " + result);
	}

}

class Kruskal {
	//union-find 알고리즘을 위한 root, rank
	Map<String, String> parentMap = new HashMap<String, String>();
	Map<String, Integer> rankMap = new HashMap<String, Integer>();
	
	/*
	 * 최소신장트리 구하기
	 */
	public ArrayList<Edge> doKruskal(ArrayList<String> nodes, ArrayList<Edge> edges) {
		ArrayList<Edge> mst = new ArrayList<Edge>();
		
		Edge currEdge;
		String nodeA, nodeB;
		
		//1 초기화(각 노드를 root로 분리)
		for(int i = 0; i < nodes.size(); i++) {
			makeSet(nodes.get(i));
		}
		
		//2 간선 weight 기준 정렬
		Collections.sort(edges);
		
		//3 union-find : 간선을 연결해도 될 지 결정(사이클이 형성되면 안되기 때문에 두 노드의 root를 비교)
		for(int i = 0; i < edges.size(); i++) {
			currEdge = edges.get(i);
			nodeA = currEdge.nodeA;
			nodeB = currEdge.nodeB;
			
			if(find(nodeA) != find(nodeB)) {
				union(nodeA, nodeB);
				mst.add(currEdge);
			}
		}
		
		return mst;
	}
	
	
	/*
	 * union-find 알고리즘을 위한 메서드
	 * root 구하기
	 * path-compression 기법 적용하여 root 밑으로 노드 rank 설정(트리의 탐색 높이 낮추기)
	 */
	private String find(String node) {
		if(parentMap.get(node) != node) {
			parentMap.put(node, find(parentMap.get(node))); //재귀 : 계속 부모를 추적하여 root 구하기 & path-compression rank 재설정
		}
		
		return parentMap.get(node);
	}
	
	/*
	 * union-find 알고리즘을 위한 메서드
	 * 
	 */
	private void union(String nodeA, String nodeB) {
		
		//union by rank 기법
		if(rankMap.get(nodeA) > rankMap.get(nodeB)) {
			parentMap.put(nodeB, nodeA);
		} else if(rankMap.get(nodeA) < rankMap.get(nodeB)) {
			parentMap.put(nodeA, nodeB);
		} else {
			rankMap.put(nodeB, rankMap.get(nodeB)+1);
			parentMap.put(nodeA, nodeB);
		}
		
	}
	
	/*
	 * union-find 알고리즘의 원소 초기화
	 */
	private void makeSet(String node) {
		parentMap.put(node, node);
		rankMap.put(node, 0);
	}
	
}


//노드가 아닌 간선을 중심으로 객체 생성
class Edge implements Comparable<Edge>{
	int weight; // 거리(가중치)
	String nodeA; //간선이 연결하는 노드 A
	String nodeB; //간선이 연결하는 노드 B
	
	public Edge(int weight, String nodeA, String nodeB) {
		this.weight = weight;
		this.nodeA = nodeA;
		this.nodeB = nodeB;
	}
	
	//객체데이터의 정렬 대상 세팅(가중치 오름차순)
	@Override
	public int compareTo(Edge edge) {
		if(this.weight < edge.weight) {
			return -1;
		} else if(this.weight > edge.weight) {
			return 1;
		} else {
			return 0;
		}
	}
	
	@Override
	public String toString() {
		return "[" + this.weight + ", " + this.nodeA + ", " + this.nodeB + "]";
	}
}

