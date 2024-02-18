-- 코드를 입력하세요
-- 아이디, 생물 종, 보호 시작일, 보호 시작 시 상태, 이름, 성별 및 중성화 여부
-- 아이디, 생물 종, 입양일, 이름, 성별 및 중성화 여부 (X)
-- 보호소 들어올 땐 중성화 X이지만 입양될땐 중성화가 된 동물 
-- 중성화 X: INTACT , 중성화 O: SPAYED OR NEUTERED 


SELECT I.ANIMAL_ID,I.ANIMAL_TYPE,I.NAME
FROM ANIMAL_INS I 
JOIN ANIMAL_OUTS O
ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.SEX_UPON_INTAKE LIKE '%Intact%' 
AND (O.SEX_UPON_OUTCOME LIKE '%Spayed%' OR O.SEX_UPON_OUTCOME LIKE '%Neutered%')
ORDER BY I.ANIMAL_ID;