package P10608;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class breadthFirstSearch {
	
	static class Vertex {
		
		boolean connected = false;
		LinkedList<Vertex> adjList = new LinkedList<Vertex>();
	}

	static int bfsToFindGroup(Vertex source) {
		
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(source);
		
		int groupSize = 0;
		while (!queue.isEmpty()) {
			Vertex v = queue.poll();

			if (!v.connected) {
				groupSize++;
				v.connected = true;
				for (Vertex w : v.adjList) {
					queue.add(w);
				}
			}
		}
		return groupSize;
	}
	
	public static void main(String[] args) {
		
		Scanner scnr = new Scanner(System.in);
		int numTestCases = scnr.nextInt();
		int currentTestCase = 1;
		
		while (currentTestCase <= numTestCases) {
			
			int numCitizens = scnr.nextInt();
			int numPairs = scnr.nextInt();
			
			//initialize all citizens as vertices
			Vertex[] list = new Vertex[numCitizens + 1];
			
			for (int i = 1; i <= numCitizens; i++) {
				list[i] = new Vertex();
			}

			// Initialize edges
			for (int j = 0; j < numPairs; j++) {
				
				int friend1 = scnr.nextInt();
				int friend2 = scnr.nextInt();
				
				//update adjacency lists
				list[friend1].adjList.add(list[friend2]);
				list[friend2].adjList.add(list[friend1]);
				
			}
			
			int largest = findLargestGroup(list);
			
			System.out.println(largest);
			
			currentTestCase++;
		}
	}
	
	static int findLargestGroup(Vertex[] givenList) {
		int maxGroup = -1;
		
		for (int m = 1; m < givenList.length; m++) {
			if (!givenList[m].connected) {
				maxGroup = Math.max(maxGroup, bfsToFindGroup(givenList[m]));
			}
		}
		return maxGroup;
	}
}


