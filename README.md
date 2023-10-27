## **📃 스터디 규칙**

1. 매주 목요일 오후 6시 대면 회의를 1회 진행한다. (장소와 시간은 변경될 수 있음)
2. 문제 풀이 기한은  ~ 다음주 회의 전까지이다.
3. 매주 4개의 문제를 풀이
4. 본인 코드 설명은 템플릿을 참고하여 `README.md`에 기록
5. 다른 사람의 코드를 보고 코드 리뷰할 것이 있으면 코멘트 남기기.

## **❌ 스터디 패널티❌**

1. 문제 고의적으로 문제를 풀지 않을 시,  5천원
2. 스터디 고의적으로 참여하지 않을 시,  5천원

## **📕 문제집**

<details>
<summary>1주차 ~ 10주차</summary>
<br>
  
||날짜|알고리즘|출처|문제1|문제2|문제3|문제4|
|--|--|--|----|--|--|--|--|
|**1주차**|09.11 ~ 09.14|백트래킹(Backtracking)|백준,프로그래머스 |[가르침](https://www.acmicpc.net/problem/1062)|[좋은수열](https://www.acmicpc.net/problem/2661)|[연산자 끼워넣기(3)](https://www.acmicpc.net/problem/15659)|[교점에 별 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/87377)|
|**2주차**|09.14 ~ 09.21|그래프(Graph)|백준,프로그래머스 |[벽 부수고 이동하기](https://www.acmicpc.net/problem/2206)|[모양 만들기](https://www.acmicpc.net/problem/16932)|[일요일 아침의 데이트](https://www.acmicpc.net/problem/1445)|[네트워크](https://school.programmers.co.kr/learn/courses/30/lessons/43162)|
|**3주차**|09.25 ~ 10.05|다이나믹 프로그래밍(DP)|백준,프로그래머스 |[받아쓰기](https://www.acmicpc.net/problem/20542)|[양팔저울](https://www.acmicpc.net/problem/2629)|[구간 나누기](https://www.acmicpc.net/problem/2228)|[N으로 표현](https://school.programmers.co.kr/learn/courses/30/lessons/42895)|
|**4주차**|10.05 ~ 10.12|구현(Implementation)|백준,프로그래머스 |[모노미노도미노2](https://www.acmicpc.net/problem/20061)|[경사로](https://www.acmicpc.net/problem/14890)|[큐빙](https://www.acmicpc.net/problem/5373)|[메뉴 리뉴얼](https://school.programmers.co.kr/learn/courses/30/lessons/72411)|
|**5주차**|10.12 ~ 10.19|최소 스패닝 트리(MST)|백준,프로그래머스 |[최소 스패닝 트리](https://www.acmicpc.net/problem/1197) | [연애 혁명](https://www.acmicpc.net/problem/27498) | [행성 연결](https://www.acmicpc.net/problem/16398)|[합승 택시 요금](https://school.programmers.co.kr/learn/courses/30/lessons/72413) |
|**6주차**|10.18 ~ 10.26|랜덤 문제|프로그래머스|[이모티콘 할인행사](https://school.programmers.co.kr/learn/courses/30/lessons/150368) |[양궁대회](https://school.programmers.co.kr/learn/courses/30/lessons/92342) |[N-Queen](https://school.programmers.co.kr/learn/courses/30/lessons/12952) |[불량 사용자](https://school.programmers.co.kr/learn/courses/30/lessons/64064) |
|**7주차**|10.27 ~ 11.02|랜덤 문제|프로그래머스|[순위 검색](https://school.programmers.co.kr/learn/courses/30/lessons/72412) |[표현 가능한 이진트리](https://school.programmers.co.kr/learn/courses/30/lessons/150367) |[보석 쇼핑](https://school.programmers.co.kr/learn/courses/30/lessons/67258) |[경주로 건설](https://school.programmers.co.kr/learn/courses/30/lessons/67259) |

</details>
<br>    

## **🗂 폴더 구조**

[문제 출처] / [알고리즘명] / [문제 제목] / [이름]

문제제목 폴더는 문제번호_문제이름(띄어쓰기는 _ 로 표시)으로 한다.

EX : 11053_가장_긴_증가하는_부분_수열

## **🔎 깃허브 사용법**

### **전체적인 흐름**

1. 매주 대면 회의에서 문제 선정 직후, 한 사람이 **main 브랜치에 새로운 문제 폴더를 생성**한다.
2. main 브랜치에서 본인 이름으로 **각자 브랜치를 생성**한다. (처음 한 번만 하면 됨)
3. 본인 브랜치에서 첫 `add` , `commit`, `push` 후 깃허브 페이지에서 **PR을 생성**한다. (레포지토리에서 `Compare & pull request` 버튼 클릭) 
4. 한 번 생성한 PR은 일주일간 유효하며, 다음 회의 시작 시 스터디원들과 함께 `merge`한다.

### **Pull Request**

- PR 제목은 `[해당 주]-[본인 이름]`으로 한다.예시: `week1-kcm4112`
- merge base가 main임을 확인한다.
- Assignees에 본인을 태그하고, Labels에 해당 주에 사용하는 알고리즘을 태그한다.
- 덧붙일 코멘트가 있다면 자유롭게 작성한다.

### **Commit Convention**

- 새로운 문제 파일 추가 시: `Create [문제 번호] [문제 제목]`
    
    `Create 1003 피보나치 함수`
    
- 기존 코드 수정 시: `Modify [문제 번호] [문제 제목]`
    
    `Modify 1003 피보나치 함수`

### **코드 리뷰**

PR에 직접 코멘트를 남겨도 좋고, 코드 일부분에다 리뷰를 해도 된다.
