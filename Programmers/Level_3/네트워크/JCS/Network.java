package Programmers;

import java.util.*;
/*
컴퓨터의 개수 N,
연결에 대한 정보가 담긴 2차원 배열 computers가 매개변수로 주어질 때,
네트워크의 개수를 return
Union Find 알고리즘
네트워크 대표 index -> 해당 네트워크의 가장 작은 노드번호
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 1]]

parent 초기 => [0,1,2]
0 노드 검사 후 => [0,0,2]
1 노드 검사 후 => [0,0,2]
2 노드 검사 후 => [0,0,2]

[[1, 1, 0],
 [1, 1, 1],
 [0, 1, 1]]
0 노드 검사 후 => [0,0,2]
1 노드 검사 후 => [0,0,0]
2 노드 검사 후 => [0,0,0]
*/
class Network {
    static int [] parent ;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        parent = new int[n];
        // nodes 배열 초기화 //
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }

        for(int i=0; i<n; i++) {
            int cur_node = i;
            for(int j=cur_node + 1; j<n; j++) {
                if(computers[cur_node][j] == 1) {
                    // 연결되어 있다면 부모를 찾는다
                    int parent1 = find(cur_node);
                    int parent2 = find(j);
                    // 부모가 같지 않다면 한 네트워크로 연결시켜준다.
                    if(parent1 != parent2) {
                        union(parent1,parent2);
                    }
                }
            }
        }

        for(int i=0;i<n;i++) {
            if(i==parent[i]) {
                answer++;
            }
        }

        return answer;
    }
    static private void union(int node1, int node2) {
        if(node1 <= node2) {
            parent[node2] = node1;
            return;
        }
        parent[node1] = node2;
        return;
    }
    static private int find(int node) {
        while(parent[node] != node) {
            node = parent[node];
        }
        return node;
    }
}