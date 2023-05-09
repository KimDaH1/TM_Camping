--결제 테이블 생성 쿼리
CREATE TABLE tm_order(    
    o_number VARCHAR2(25) PRIMARY KEY, --주문번호    
    amount NUMBER(8), --결제금액    
    order_date DATE DEFAULT SYSDATE, --결제시간
    order_item VARCHAR2(30), --결제품목
    --회원번호(FK)
    usernumber NUMBER(5) CONSTRAINT USER_ORDER_FK REFERENCES tm_users(usernumber)
);

SELECT *
FROM tm_order
ORDER BY 1;

--입력 테스트
INSERT INTO tm_order(o_number, amount, order_item, usernumber) VALUES( 7, 100, '휴먼캠핑장', 1);

DELETE FROM tm_order WHERE order_item IS NULL;