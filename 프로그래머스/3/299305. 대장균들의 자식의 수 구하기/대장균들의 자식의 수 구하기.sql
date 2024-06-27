-- 코드를 작성해주세요

# select * from ecoli_data;
-- 개체 ID, 부모 ID, 개체 크기, 날짜, 형질 
-- 최초 대장균 id =null , 
-- A: 개체의 ID와 자식의 수를 출력하는 sql 작성

-- parentId가 자신이라면 자식의 수를 ++한다. 
 
# 1 1
# 2 2
# 3 0
# 4 1
# 5 0
# 6 0
# select a.id,b.child_count 
# from 
# ecoli_data as a
# join
# (select count(*) as child_count,parent_id
# from ecoli_data
# group by parent_id
# ) as b
# on a.id = b.parent_id;

# select count(*) as child_count,parent_id
# from ecoli_data
# group by parent_id
SELECT a.ID, IFNULL(b.CHILD_COUNT, 0) AS CHILD_COUNT
FROM ecoli_data AS a
LEFT JOIN (
    SELECT COUNT(*) AS CHILD_COUNT, parent_id
    FROM ecoli_data
    GROUP BY parent_id
) AS b ON a.id = b.parent_id
ORDER BY A.ID;


