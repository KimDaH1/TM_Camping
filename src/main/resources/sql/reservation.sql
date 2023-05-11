--예약 테이블 생성 쿼리
CREATE TABLE tm_reservation(
    r_number NUMBER(10) PRIMARY KEY NOT NULL,
    s_date DATE,
    e_date DATE
);

--데이터 조회
SELECT * 
FROM tm_reservation
ORDER BY r_number;