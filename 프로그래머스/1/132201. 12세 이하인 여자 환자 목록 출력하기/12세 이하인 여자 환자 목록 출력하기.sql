-- 코드를 입력하세요
# 전화번호 없으면 NONE으로 출력 , 나이 내림차순 , 나이가 같으면 환자이름 오름차순 
SELECT PT_NAME,PT_NO, GEND_CD, AGE, IFNULL(TLNO,'NONE') AS TLNO FROM PATIENT 
WHERE AGE <=12 AND GEND_CD = 'W'
ORDER BY AGE DESC, PT_NAME ASC;
