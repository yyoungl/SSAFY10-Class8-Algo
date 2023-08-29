# SSAFY10-Class8-Algo
.. 실험

## 문제 내고 제출하기

📁 [날짜_출처_문제번호] ex) 20230728_B_2557

- 출처: 백준(B), 프로그래머스(P), SWEA(S)
  └ 문제 담당자는 md 파일에 문제 링크/설명 적기
  └ 문제 풀고 이름*날짜*출처\_문제번호.java 로 커밋

## 원격 저장소 추가

개인 토큰 생성. 클래식으로 해야함.
첫 push에서 이름과 비밀번호를 요구할 때 입력해야 하는 비밀번호는 깃헙 토큰비밀번호다. 깃헙 토큰은 클래식으로 만들어야한다.

### 문제출제

금주 `수요일`까지 차주 풀 문제를 정한다. 매주 난이도를 고려하여 3~5문제를 결정한다.
금주 토요일까지 각자 문제폴더`(20230728_B_2557)`, `20230728_B_2557.md`를 개인 브랜치에 올린다.
일요일에 깃 담당자는 출제 문제를 메인 브랜치에 합병한다.

### 문제출제 순서

개인 브랜치로 이동한다.
개인 브랜치에서 main 브랜치를 pull 한다. (git pull origin main)
문제폴더(20230728_B_2557), `20230728_B_2557.md`를 생성한다.
md 파일에는 에는 문제 주소를 넣는다.
개인 브랜치에서 add . => commit => push 한다.
깃 담당자의 일요일 합병 순서

메인 브랜치에서 브랜치 별로 합병한다.

```bash
git merge feature/~~~
git push
```

### 문제 풀기

개인 브랜치로 이동한다.
main 브랜치를 pull한다. (git pull origin main)
정답이 들어있는 (gubeom_20230728_B_2557.java) 파일을 올린다.
개인 브랜치를 add . => commit => push 한다.
정답 합병

문제 풀이가 있는 날 오전에 깃 담당자가 합병한다.
메인 브랜치에서 브랜치 별로 합병한다.

```bash
git merge feature/~~~
git push
```
