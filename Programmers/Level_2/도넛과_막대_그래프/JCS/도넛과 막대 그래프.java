import java.util.*;
/**
edge의 갯수 -> 1,000,000 개
*/
class Solution {
    static class EdgeInfo {
        List<Integer> inDegree = new ArrayList<>();
        List<Integer> outDegree = new ArrayList<>();
        public EdgeInfo() {}
        
        public void addIndegree(int from) {
            this.inDegree.add(from);
        }
        
        public void addOutdegree(int to) {
            this.outDegree.add(to);
        }
    }
    
    static final int donut_idx = 1;
    static final int stick_idx = 2;
    static final int eight_idx = 3;
    
    public int[] solution(int[][] edges) {
        
        Map<Integer, EdgeInfo> graph = new HashMap<>();
        int [] answer = new int[4];
        for(int [] edge: edges) {
            if(!graph.containsKey(edge[0])) {
                graph.put(edge[0], new EdgeInfo());
            }
            if(!graph.containsKey(edge[1])) {
                graph.put(edge[1], new EdgeInfo());
            }
            graph.get(edge[0]).addOutdegree(edge[1]);
            graph.get(edge[1]).addIndegree(edge[0]);
        }
        
        int createdNode = findCreatedNode(graph);
        answer[0] = createdNode;
        
        for(int start : graph.get(createdNode).outDegree) {
            Set<Integer> visit = new HashSet<>();
            visit.add(start);
            answer[findComponent(graph, start, visit)]++;
        }
        
        return answer;
    }
        
  
    public int findComponent(Map<Integer, EdgeInfo> graph, int cur, Set<Integer> visit) {

        for(int node : graph.get(cur).outDegree) {
           if(graph.get(node).inDegree.size()>=2 && graph.get(node).outDegree.size()>=2) {
               return eight_idx;
           }
           if(visit.contains(node)) {
               return donut_idx;
           }
            visit.add(node);
            return findComponent(graph, node, visit);
        }
        return stick_idx;

    } 
    
    
    public int findCreatedNode(Map<Integer, EdgeInfo> graph) {
        int createdNode = -1;
        for(int node : graph.keySet()) {
            if(graph.get(node).inDegree.size() == 0 && graph.get(node).outDegree.size() >= 2) {
                createdNode = node;
                break;
            }
        }
        return createdNode;
    }
}