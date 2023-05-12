--결제 테이블 생성 쿼리
-- 1.주문번호, 2.결제금액, 3.결제타입, 4.유저번호, 5.예약번호
CREATE TABLE tm_order(
    orderno NUMBER(10), --결제번호, 시퀀스
    o_number VARCHAR2(25), --주문번호(랜덤생성번호)
    amount NUMBER(8), --결제금액    
    order_date VARCHAR2(20) DEFAULT SYSDATE, --결제시간
    --order_item VARCHAR2(30), --결제품목 (삭제 : 예약번호에서 캠핑장명을 불러온다.)
    paytype VARCHAR2(30), --결제타입
    --회원번호(FK)
    usernumber NUMBER(5) CONSTRAINT USER_ORDER_FK REFERENCES tm_users(usernumber) ON DELETE SET NULL,
    r_number NUMBER(10) CONSTRAINT RESERV_ORDER_FK REFERENCES tm_reservation(r_number) ON DELETE SET NULL,
    CONSTRAINT ORDER_NUMBER_PK PRIMARY KEY(orderno, o_number)
);

--데이터 조회
select * from tm_order;

--데이터 입력 테스트
insert into tm_order
    values( (select NVL(MAX(orderno) ,0)+1 from tm_order),
             'testno',
             1000,
             to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'),
             '금성캠핑장',
             '토스페이',
             1);