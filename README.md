# TM_Camping

## 1. About Us
### Members
김다현 김용원 최희두

### Contribution
김다현 : 회원가입, 로그인, UIㆍUX 제작, git관리

김용원 : 예약·결제

최희두 : 공공 api 수집

## 2.Project
◦ 주제요약 : 캠핑 관련 사이트 · 회원가입·로그인, 공공API 활용, 예약·결제시스템

◦ 목 적 : DB활용, TABLE 설계, 자료 저장, GIT 사용

◦ 주제선정 사유 : Covid-19 종식으로 인한 야외활동 증가와 캠핑 편의제공 목적

### 문제정의 및 요구사항 

- 회원가입 : 소셜 로그인 기능을 동반할 것 회원가입 간에 중복 아이디를 확인 기능
- 로그인 : 비밀번호는 암호화 되어 관리 기능 로그인간에 복호화하여 비밀번호 일치여부를 확인 로그인을 하면 세션을 유지하고 로그아웃 기능 추가 로그인을 하였을때 화면과 로그아웃을 하였을 때 , 표현하는 화면이 구분
- 공공api : 공공api를 실시간 호출하여 화면에 출력하는 기능
- 공공api를 실시간 호출하여 DataBase에 저장하는 기능
- 공공api를 호출하여 저장하 DataBase를 출력하는 기능
- 공공api를 저장한 값을 재처리하여 출력하는 기능
- 예약/결제 : 달력을 통해서 선택한 날짜를 DataBase에 저장하는 기능
- 저장한 값과 로그인한 회원정보, 캠핑장 세부정보를 호출하는 기능
- 호출한 정보를 조합하여 실제 결제 과정
- 결제가 완료되면 알림톡으로 결과를 전송하는 기능
- 회원가입 입력 : 회원번호, 회원 아이디, 비밀번호, 이름, 성별, 이 메일, 전화번호
- 회원가입 출력 : 알림창 “회원가입이 완료되었습니다.”
- 로그인 입력 : 아이디, 비밀번호
- 로그인 출력 : 알림창 “로그인이 완료되었습니다.” 세션유지 상태
- 캠핑장 정보 페이지 : 선택한 지역의 캠핑장 정보 출력
- 예약 페이지 : 날짜 선택 창 출력 - 결제 페이지 : toss 결제 기능 수행
  
### 사용기술
![image](https://github.com/KimDaH1/TM_Camping/assets/129045969/e070b9d4-8c63-4f84-8155-aa84ea090248)

### 프로그램 설계(ERD)

![image](https://github.com/KimDaH1/TM_Camping/assets/129045969/44a9886c-ae81-44c4-9bf7-6e7ea49690de)


## 3. Screen
### 화면 구성

![image](https://github.com/KimDaH1/TM_Camping/assets/129045969/8f0f7e2a-8010-458a-97f3-8bb270e70eca)

---

![image](https://github.com/KimDaH1/TM_Camping/assets/129045969/5963f254-507c-428b-a6a0-e9b3d23099a4)

---
## 4. 느낀점 및 보완사항

### 느낀점
DB 테이블 설계의 중요성을 느끼게
되었다.
그리고 팀 프로젝트에서 코드
이력관리를 하는 건 너무 어려운 것
같다. 마지막으로 좋은 조원들을만나
프로젝트를 잘 마무리할 수 있게 되어서
다행이라고 생각한다.

### 보완사항

◦ 소셜 로그인 간 JSP의 한계로 많은
정보를 불러올 수 없는 점

◦ toss결제 API활용간 취소ᆞ환불 절차
구현이 미흡한 점

◦ 활용된 저장자료와 공공API 비교간
오류를 수정하지 못한 점