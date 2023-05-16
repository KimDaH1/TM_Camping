--결제 테이블 생성 쿼리
-- 1.주문번호, 2.결제금액, 3.결제타입, 4.예약번호, 5.결제상태
CREATE TABLE tm_order(
    pay_no NUMBER(10) PRIMARY KEY NOT NULL, --결제번호, 시퀀스
    order_no VARCHAR2(25), --주문번호(랜덤생성번호)
    amount NUMBER(8), --결제금액    
    order_date VARCHAR2(20) DEFAULT SYSDATE, --결제시간
    --order_item VARCHAR2(30), --결제품목 (삭제 : 예약번호에서 캠핑장명을 불러온다.)
    paytype VARCHAR2(30), --결제타입
    --회원번호(FK)
    userid VARCHAR2(40), --CONSTRAINT USER_ORDER_FK REFERENCES tm_users(usernumber) ON DELETE SET NULL,
    r_number NUMBER(10) CONSTRAINT RESERV_ORDER_FK REFERENCES tm_reservation(r_number) ON DELETE SET NULL,
    o_state VARCHAR2(10)
    --CONSTRAINT ORDER_NUMBER_PK PRIMARY KEY(orderno, o_number)
);
--데이터 조회
select * from tm_order;

-- 테스트 테이블 생성(유저와 캠핑 테이블 연관X)
CREATE TABLE tm_order(
    orderno NUMBER(10), --결제번호, 시퀀스
    o_number VARCHAR2(25), --주문번호(랜덤생성번호)
    amount NUMBER(8), --결제금액    
    order_date VARCHAR2(20) DEFAULT SYSDATE, --결제시간
    --order_item VARCHAR2(30), --결제품목 (삭제 : 예약번호에서 캠핑장명을 불러온다.)
    paytype VARCHAR2(30), --결제타입
    --회원번호(FK), 예약 테이블에 회원번호 컬럼이 있어서 삭제하기로 결정
    usernumber NUMBER(5) CONSTRAINT USER_ORDER_FK REFERENCES tm_users(usernumber) ON DELETE SET NULL,
    r_number NUMBER(10) CONSTRAINT RESERV_ORDER_FK REFERENCES tm_reservation(r_number) ON DELETE SET NULL,
    o_state VARCHAR2(10) DEFAULT '미결제'
    --CONSTRAINT ORDER_NUMBER_PK PRIMARY KEY(orderno, o_number)
);