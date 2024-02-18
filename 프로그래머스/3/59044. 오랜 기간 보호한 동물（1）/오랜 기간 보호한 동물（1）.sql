-- 코드를 입력하세요
-- 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부
-- 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부 (X)
-- INS의 보호 시작일보다 OUTS에 입양일이 더 빠른 아이디랑 이름 조회 
--  가장 오래 보호소에 있었던 동물 3마리: ASC LIMIT 3 
-- 입양을 못가다 = OUTS엔 없고 INS엔 있다 .
SELECT I.NAME,I.DATETIME
FROM ANIMAL_INS I 
LEFT OUTER JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE O.ANIMAL_ID IS NULL
 ORDER BY I.DATETIME ASC 
 LIMIT 3
;