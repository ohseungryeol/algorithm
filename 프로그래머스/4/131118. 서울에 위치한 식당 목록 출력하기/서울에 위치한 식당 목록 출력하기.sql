-- 코드를 입력하세요
/* 
식당 정보 : REST_INF
리뷰 장보:  REST_REVIEW

WHEN: 서울(ADDRESS) 에 위치한 식당, 리뷰 평균점수(소수 세번쨰 반올림)
식당 ID, 식당 

서울의 위치한 식당들의 리뷰 평균점수!!!!
*/
SELECT A.REST_ID, A.REST_NAME, A.FOOD_TYPE, A.FAVORITES, A.ADDRESS, ROUND(AVG(B.REVIEW_SCORE),2) AS SCORE
FROM REST_INFO AS A
JOIN REST_REVIEW AS B
ON A.REST_ID = B.REST_ID

GROUP BY A.ADDRESS
HAVING A.ADDRESS LIKE '서울%'
ORDER BY SCORE DESC, A.FAVORITES DESC;

# SELECT * FROM
# REST_INFO A
# LEFT OUTER JOIN REST_REVIEW B
# ON A.REST_ID = B.REST_ID
# WHERE A.ADDRESS LIKE '서울%'
# GROUP BY A.REST_ID;


                                                                    