--예약 테이블 생성 쿼리
select * from tm_reservation;
drop table tm_reservation CASCADE CONSTRAINTS;
CREATE TABLE tm_reservation(
    r_number NUMBER(10) PRIMARY KEY NOT NULL,
    s_date DATE,
    e_date DATE,
    usernumber NUMBER(5) CONSTRAINT USER_RESERVATION_FK REFERENCES tm_users(usernumber) ON DELETE SET NULL,
    c_id NUMBER(5) CONSTRAINT USER_CAMP_FK REFERENCES tm_campingzone(idx) ON DELETE SET NULL,
    r_state VARCHAR2(10) DEFAULT '예약됨' --예약 상태(예약됨, 취소됨, 결제됨)
);
--날짜 중복체크 & INSERT하는 PL/SQL문
CREATE OR REPLACE PROCEDURE date_duplicate_check_test
(   
--    v_r_number tm.reservation.r_number%type,    
    v_s_date IN tm_reservation.s_date%type,
    v_e_date IN tm_reservation.e_date%type,
    v_usernumber IN tm_reservation.usernumber%type,
    v_c_id IN tm_reservation.c_id%type,
    v_r_state IN tm_reservation.r_state%type,
    v_result OUT NUMBER
    --v_count OUT NUMBER
)
IS
  v_overlap_count NUMBER;
BEGIN
  SELECT COUNT(*)
  INTO v_overlap_count
  FROM tm_reservation
  WHERE c_id = v_c_id
    AND (
            (v_s_date >= s_date AND v_s_date <= e_date) -- 입력 시작 날짜가 기존 예약 기간에 포함되는 경우
            OR (v_e_date >= s_date AND v_e_date <= e_date) -- 입력 종료 날짜가 기존 예약 기간에 포함되는 경우
            OR (v_s_date <= s_date AND v_e_date >= e_date) -- 입력 기간이 기존 예약 기간을 포함하는 경우
    )   
    AND r_state <> '취소됨';
  
  IF v_overlap_count > 0 THEN
    -- 겹치는 예약이 존재함    
    v_result :=1;
    
    ELSE
    -- 겹치는 예약이 존재하지 않음
        v_result :=0;
        INSERT INTO tm_reservation(r_number, s_date, e_date, usernumber, c_id, r_state)
        VALUES( (select NVL(MAX(r_number),0)+1 from tm_reservation)                
            ,v_s_date
            ,v_e_date
            ,v_usernumber
            ,v_c_id
            ,v_r_state);
    
        COMMIT;
    END IF;
END;
/

--데이터 조회
SELECT *
FROM tm_reservation
ORDER BY r_number;

--데이터 조회( 결제 테이블과 연계, 결제되었으면 조회가 안되는 경우)
SELECT r.r_number, s_date, e_date, usernumber, c_id, NVL(o_state, '미결제')
FROM tm_reservation r
    LEFT OUTER JOIN tm_order o
    ON r.r_number = o.r_number
WHERE usernumber = 1
    AND o.o_state IS NULL
ORDER BY r.r_number;

--유저와 캠핑 테이블 없이 테스트하려고 만드는 테이블
CREATE TABLE tm_reservation(
    r_number NUMBER(10) PRIMARY KEY NOT NULL,
    s_date DATE,
    e_date DATE,
    usernumber NUMBER(5),-- CONSTRAINT USER_RESERVATION_FK REFERENCES tm_users(usernumber) ON DELETE SET NULL,
    c_id NUMBER(5),-- CONSTRAINT USER_CAMP_FK REFERENCES tm_campingzone(idx) ON DELETE SET NULL,
    r_state VARCHAR2(10) DEFAULT '예약됨' --예약 상태(예약됨, 취소됨, 결제됨)
);
