-- 코드를 입력하세요
# SELECT * from book;

# -- SELECT * FROM AUTHOR;

# -- 카테고리가 경제인 도서들의 도서 ID, 저자명, 출판일(오름차)

SELECT a.book_id,b.author_name,DATE_FORMAT(a.published_Date,'%Y-%m-%d') as PUBLISHED_DATE
FROM BOOK A
JOIN AUTHOR B 
ON A.author_id = B.author_id
where a.category = '경제'
ORDER BY PUBLISHED_DATE;

