-- ȸ������ ���̺� ���� ����1
CREATE table TM_USERS
(
 usernumber number(5) PRIMARY KEY,
 userid varchar2(40),
 userpassword varchar2(40),
 username varchar2(40),
 usergender CHAR(1) CHECK(GENDER IN ('m,'w')),
 useremail varchar2(40),
 userphone varchar2(40)
);

-- ���̺� �����ϰ� ������ Ȯ���ϴ� �������� �μ�Ʈ ����
INSERT INTO TM_USERS (usernumber, userID, userPassword, userName, userGender, userEmail, userPhone)
values((SELECT NVL(MAX(usernumber),0)+1 from TM_USERS), 'chlgmle','chlgmle','chlgmle','chlgmle','chlgmle','chlgmle');

INSERT INTO TM_USERS (usernumber, userID, userPassword, userName, userGender, userEmail, userPhone)
values((SELECT NVL(MAX(usernumber),0)+1 from TM_users), ?, ?, ?, ?, ?, ?);

select * from TM_USERS;
