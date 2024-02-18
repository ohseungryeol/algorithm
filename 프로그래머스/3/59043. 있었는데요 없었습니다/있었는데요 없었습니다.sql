-- 코드를 입력하세요
-- 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부
-- 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부 (X)
-- INS의 보호 시작일보다 OUTS에 입양일이 더 빠른 아이디랑 이름 조회 

SELECT I.ANIMAL_ID, I.NAME FROM ANIMAL_INS AS I 
JOIN ANIMAL_OUTS AS O ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.DATETIME >O.DATETIME
ORDER BY I.DATETIME ASC;