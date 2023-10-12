import java.util.*;

class Solution {

    static List<Point> list = new ArrayList<>();

    public List<String> solution(int[][] line) {
        List<String> answer = new ArrayList<>();

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        // 직선들의 모든 교점 찾기
        for(int i = 0; i < line.length - 1; i++){
            for(int j = i + 1; j < line.length; j++){
                Point inter = findIntersectionPoint(line[i], line[j]);
                if(checkInteger(inter.y) && checkInteger(inter.x)){
                    // 찾은 교점들 중 x, y 좌표의 min, max 값 찾기
                    minX = Math.min((long)inter.x, minX);
                    maxX = Math.max((long)inter.x , maxX);

                    minY = Math.min((long)inter.y, minY);
                    maxY = Math.max((long)inter.y , maxY);

                    list.add(inter);
                }
            }
        }

        // 찾은 좌표를 토대로 결과값 만들기
        for(long i = maxY; i >= minY; i--){
            String map = "";
            for(long j = minX; j < maxX + 1; j++){
                if(isIn(i, j)){
                    map += "*";
                }
                else{
                    map += ".";
                }
            }
            answer.add(map);
        }

        return answer;
    }

    public static Point findIntersectionPoint(int[] line1, int[] line2){
        double a = line1[0];
        double b = line1[1];
        double e = line1[2];

        double c = line2[0];
        double d = line2[1];
        double f = line2[2];

        double y = (e*c - a*f)/(a*d - b*c);
        double x = (b*f - e*d)/(a*d - b*c);

        return new Point(x, y);
    }

    public static boolean checkInteger(double number){
        if(number - (long)number == 0.0)
            return true;

        return false;
    }

    public static boolean isIn(long y, long x){
        for(Point p : list){
            if((long)p.x == x && (long)p.y == y)
                return true;
        }

        return false;
    }

    static class Point {
        double x;
        double y;

        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }


}