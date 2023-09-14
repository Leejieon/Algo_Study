package Programmers;

import java.util.*;
class Solution {
    /*
    Ax + By + C = 0
    A, B, C 에 대한 정보가 담긴 배열 line
    line 길이 2 <= 길이 <= 1,000
    -100,000 <= A, B, C <= 100,000
    
    교점의 존재 유무 확인
    -> Ax + By + E = 0 과 Cx + Dy + F = 0 이 있을 때
    AD - BC = 0 인 경우 교점 X
    아니라면 교점 좌표 ((BF-ED) / (AD-BC) , (EC-AF) / (AD-BC))
    
    정수 교점 좌표 저장
    
    반복
    x좌표 max , y 좌표 max 값 구하기
    
    결과 출력하기
    */
    public String[] solution(int[][] line) {
        List<Point> pointList = new ArrayList<>();

        for(int i=0; i < line.length-1; i++) {
            long A = line[i][0];
            long B = line[i][1];
            long E = line[i][2];
            for(int j = i+1; j < line.length; j++) {
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];
                long flag = (A*D) - (B*C);
                //교점이 존재하면
                if(flag != 0) {
                    long x = ((B*F) - (E*D));
                    long y = ((E*C) - (A*F));
                    if(x % flag == 0 && y % flag == 0) {
                        pointList.add(new Point(x/flag, y/flag));
                    }
                }
            }
        }


        Collections.sort(pointList, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if(p1.col == p2.col) {
                    //오름차순
                    return Long.valueOf(p1.row - p2.row).intValue();
                }
                return Long.valueOf(p2.col - p1.col).intValue();
            }
        });
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        for(Point p : pointList) {
            if(minX > p.row) {
                minX = p.row;
            }
            if(maxX < p.row) {
                maxX = p.row;
            }
            if(minY > p.col) {
                minY = p.col;
            }
            if(maxY < p.col) {
                maxY = p.col;
            }
        }
        int cnt =0;
        StringBuilder sb = new StringBuilder();
        for(long i = maxY; i >= minY ; i--) {
            for(long j= minX; j <= maxX; j++) {
                if(cnt < pointList.size() && pointList.get(cnt).equals(j,i)) {
                    sb.append("*");
                    while(cnt < pointList.size() && pointList.get(cnt).equals(j,i)) {
                        cnt++;
                    }

                }
                else {
                    sb.append(".");
                }
            }
            sb.append(" ");
        }
        return sb.toString().split(" ");
    }


    static class Point {
        long row;
        long col;
        public Point(long row, long col) {
            this.row = row;
            this.col = col;
        }
        public boolean equals(long x, long y) {
            return this.row == x && this.col == y;
        }
    }
}