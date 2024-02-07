import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    static double answer = 0;
    static Point[] stars;
    static boolean[] visit;
    static ArrayList<Node> nodes;
    static int[] parent;
    static int cnt;
    public static void main(String[] args) throws IOException {
        input();
        setDistance();
        kruskal();
        answer = Math.round(answer * 100) /100.0;
        System.out.println(answer);
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        stars = new Point[n];
        parent = new int[n];
        nodes = new ArrayList<>();
        double x, y;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Double.parseDouble(st.nextToken());
            y = Double.parseDouble(st.nextToken());
            stars[i] = new Point(i, x, y);
            parent[i] = i;
        }
    }

    public static void setDistance() {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                nodes.add(new Node(i, j, carDistance(stars[i], stars[j])));
            }
        }
        nodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Main.Node o1, Main.Node o2) {
                return (int) (o1.dist - o2.dist);
            }
        });
    }
    public static void union(Node node) {
        int startParent = find(node.from);
        int endParent = find(node.to);

        if(startParent == endParent) //부모가 같으면 연결
            return;
        else if(startParent<endParent) {
            parent[endParent] = startParent;
        } else {
            parent[startParent] = endParent;
        }
        answer+= node.dist;
        cnt++;
    }
    public static int find(int p) {
        if(parent[p]==p)
            return p;
        return parent[p]= find(parent[p]);
    }
    public static void kruskal() {
        for(int i=0;i<nodes.size();i++) {
            if(cnt==n-1)
                break;
            union(nodes.get(i));
        }
    }

    public static double carDistance(Point a, Point b) {

        double distance = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        return distance;
    }

    static class Point {
        int idx;
        double x, y;

        public Point(int idx, double x, double y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        int from, to;
        double dist;

        public Node(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }
}