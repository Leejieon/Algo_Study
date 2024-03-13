import java.util.*;

/*
같은 티켓이 있을 수 있고
왕복 티켓이 있을 수 있다.
*/
class Solution {
    static class Ticket {
        String from;
        String to;
        public Ticket(String from, String to) {
            this.from = from;
            this.to = to;
        }
        
        public boolean equals(Object o) {
            if(!(o instanceof Ticket)) {
                return false;
            }
            Ticket t = (Ticket) o;
            return from.equals(t.from) && to.equals(t.to);
        }
        
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }
    public Map<Ticket, Integer> ticketMap = new HashMap<>();
    public Map<String, List<String>> graph = new HashMap<>();
    public int cnt = 0;
    public List<String> answer = new ArrayList<>();
    //[["ICN", "JFK"], ["ICN", "AAD"], ["JFK", "ICN"]] 
    //["ICN", "JFK", "ICN", "AAD"]
    
    public List<String> solution(String[][] tickets) {
        
        cnt = tickets.length;
        for(String [] ticket : tickets) {
            Ticket t = new Ticket(ticket[0], ticket[1]);
            if(!graph.containsKey(ticket[0])) {
                graph.put(ticket[0], new ArrayList<>());
            }
            graph.get(ticket[0]).add(ticket[1]);
            ticketMap.put(t, ticketMap.getOrDefault(t,0)+1);
        }
        
        recur(0,"ICN",new ArrayList(), new HashMap<>(ticketMap));
        
        return answer;
    }
    
    public void recur (int depth, String now, List<String> path, Map<Ticket, Integer> map) {
        
        path.add(now);
        
        if(depth == cnt) {
            if(isFirst(path)) {
                answer = new ArrayList<>(path);
                return;
            }
        }
        
        if(!graph.containsKey(now)) {
            return;
        }
        
        for(String dest : graph.get(now)) {
            Ticket curTicket = new Ticket(now, dest);
            if(map.get(curTicket) > 0) {
                map.put(curTicket, map.get(curTicket) - 1);
                recur(depth+1, dest, new ArrayList<>(path), new HashMap<>(map));
                map.put(curTicket, map.get(curTicket) + 1);
            }
        }

    }
    
    public boolean isFirst(List<String> path) {
        if(answer.isEmpty()) {
            return true;
        }
        
        for(int i=0; i<path.size(); i++) {
            if(path.get(i).compareTo(answer.get(i)) < 0) {
                return true;
            }
            
            if(path.get(i).compareTo(answer.get(i)) > 0) {
                return false;
            }
        }
        return false;
    }
}