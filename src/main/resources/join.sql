-- 회원정보 테이블 정보 생성1
CREATE table TM_USERS
(
 usernumber number(5),
 userid varchar2(40) PRIMARY KEY ,
 userpassword varchar2(40),
 username varchar2(40),
 usergender CHAR(1),
 useremail varchar2(40),
 userphone varchar2(40)
 );

--테이블 자체 삭제
drop table tm_users;

--테이블 삭제
DELETE tm_users;

--회원정보 확인
select * from tm_users;

-- 테이블 생성하고 들어가는지 확인하는 목적으로 인서트 구문
INSERT INTO TM_USERS (usernumber, userID, userPassword, userName, userGender, userEmail, userPhone)
values((SELECT NVL(MAX(usernumber),0)+1 from TM_USERS), 'chlgmle','chlgmle','chlgmle','chlgmle','chlgmle','chlgmle');

INSERT INTO TM_USERS (usernumber, userID, userPassword, userName, userGender, userEmail, userPhone)
values((SELECT NVL(MAX(usernumber),0)+1 from TM_users), ?, ?, ?, ?, ?, ?);

select * from TM_USERS;
