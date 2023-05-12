--예약 테이블 생성 쿼리
CREATE TABLE tm_reservation(
    r_number NUMBER(10) PRIMARY KEY NOT NULL,
    s_date DATE,
    e_date DATE
);

SELECT * 
FROM tm_reservation;

insert into tm_reservation
values( (select NVL( MAX(r_number),0)+1 from tm_reservation ) , '2023-01-01', '2023-01-03');