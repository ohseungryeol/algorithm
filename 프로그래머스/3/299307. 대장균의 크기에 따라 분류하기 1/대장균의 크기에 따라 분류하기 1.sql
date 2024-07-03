-- 코드를 작성해주세요



--  크기가 100 이하라면 'LOW', 100 초과 1000 이하라면 'MEDIUM', 1000 초과라면 'HIGH' 라고 분류합니다. 

SELECT ID, CASE WHEN SIZE_OF_COLONY <=100 THEN 'LOW'
                WHEN SIZE_OF_COLONY >100 AND SIZE_OF_COLONY <=1000 THEN 'MEDIUM'
                WHEN SIZE_OF_COLONY >1000 THEN 'HIGH'
                END AS SIZE
FROM ECOLI_DATA
ORDER BY ID;