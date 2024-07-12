-- 코드를 입력하세요

-- Half Table
-- 출하번호, 맛 , 상반기 아이스크림 총 주문량 

-- July 테이블 
-- 출하번호, 맛, 7월 총 주문량 

-- G: 7월 총주문량 + 상반기 총주문량 top3 맛을 조회 

SELECT A.FLAVOR
FROM FIRST_HALF A
JOIN JULY B
ON A.FLAVOR = B.FLAVOR
GROUP BY A.FLAVOR
ORDER BY SUM (A.TOTAL_ORDER + B.TOTAL_ORDER) DESC
LIMIT 3;