-- ȸ������ ���̺� ���� ����1
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

--���̺� ��ü ����
drop table tm_users;

--���̺� ����
DELETE tm_users;

--ȸ������ Ȯ��
select * from tm_users;

-- ���̺� �����ϰ� ������ Ȯ���ϴ� �������� �μ�Ʈ ����
INSERT INTO TM_USERS (usernumber, userID, userPassword, userName, userGender, userEmail, userPhone)
values((SELECT NVL(MAX(usernumber),0)+1 from TM_USERS), 'chlgmle','chlgmle','chlgmle','chlgmle','chlgmle','chlgmle');

INSERT INTO TM_USERS (usernumber, userID, userPassword, userName, userGender, userEmail, userPhone)
values((SELECT NVL(MAX(usernumber),0)+1 from TM_users), ?, ?, ?, ?, ?, ?);

select * from TM_USERS;
