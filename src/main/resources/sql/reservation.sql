--예약 테이블 생성 쿼리
CREATE TABLE tm_reservation(
    r_number NUMBER(10) PRIMARY KEY NOT NULL,
    s_date DATE,
    e_date DATE,
    usernumber NUMBER(5) CONSTRAINT USER_RESERVATION_FK REFERENCES tm_users(usernumber) ON DELETE SET NULL,
    c_id NUMBER(5) CONSTRAINT USER_CAMP_FK REFERENCES tm_campingzone(idx) ON DELETE SET NULL
    --결제 상태 컬럼 추가해야 함
);

--데이터 조회
SELECT * 
FROM tm_reservation
ORDER BY r_number;