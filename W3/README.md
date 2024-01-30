# 3주차
### 1. Spring Boot 개발 환경 구축
- Test 실행 화면

  ![3주차 개발환경 실행결과](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/fb13db5b-ce70-49d3-9723-bb16c450e5d5)

<br><br>

### 2. 통계 API(SW활용현황)을 위한 DB, TABLE 생성 
- requestInfo Table

  2023-01-01 ~ 2023-12-31 1250개의 랜덤한 info 생성
  
  ```sql
  SET @requestID = 0;
  SET @userID = 0;
  
  INSERT INTO statistc.requestInfo (requestID, requestCode, userID, createDate)
  SELECT 
      @requestID := (@requestID + 1) AS requestID,
      CASE ROUND(RAND())
          WHEN 0 THEN 'L'
          ELSE 'O'
      END AS requestCode,
      LPAD(@userID := (@userID % 31) + 1, 4, '0') AS userID,
      DATE_FORMAT(DATE_ADD('2023-01-01 00:00:00', INTERVAL FLOOR(RAND() * ((365*24*60) - 1 + 1)) MINUTE), '%Y%m%d%H%i') AS createDate
  FROM 
      (SELECT @requestID := 0, @userID := 0) r,
      -- 이 부분을 수정하여 원하는 만큼의 데이터를 생성
      (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5) t1,
      (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5) t2,
      (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5) t3,
      (SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5) t4,
      (SELECT 1 UNION ALL SELECT 2) t5;
  ```

  - [requestInfo Table 결과](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/DB%ED%85%8C%EC%9D%B4%EB%B8%94/requestinfo.csv)

  <br>

- user Table
  
  32명의 user 생성
  
  ```sql
  SET @userID = 0;

  INSERT INTO statistc.user (userID, HR_ORGAN, USERNAME)
  SELECT 
      LPAD(@userID := (@userID + 1), 4, '0') AS userID,
      CASE FLOOR(RAND() * 5)
          WHEN 0 THEN 'Sales'
          WHEN 1 THEN 'Marketing'
          WHEN 2 THEN 'HR'
          WHEN 3 THEN 'Development'
          ELSE 'Support'
      END AS HR_ORGAN,
      CONCAT('User', LPAD(@userID, 3, '0')) AS USERNAME
  FROM 
      (SELECT @userID := 0) r,
      (SELECT 1 UNION ALL SELECT 2) t1,
      (SELECT 1 UNION ALL SELECT 2) t2,
      (SELECT 1 UNION ALL SELECT 2) t3,
      (SELECT 1 UNION ALL SELECT 2) t4,
      (SELECT 1 UNION ALL SELECT 2) t5
  ```

  - [user Table 결과](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/DB%ED%85%8C%EC%9D%B4%EB%B8%94/user.csv)
  
  <br>
  
- hoilydays Table

  공공포털 API -> DB 저장

  - [실행 코드](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/holiday_data_updater.py)
  - [hoildays Table 결과](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/DB%ED%85%8C%EC%9D%B4%EB%B8%94/holidays.xlsx)
  
  <br>

### 3. [20년도 로그인 수 API] 스프링부트, mybatis, mariadb 연동
- API Test [20년도 로그인수 API]
  
  ![20년도 로그인 수](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/cface7e2-bb99-4d6f-b47d-4f351b52cee1) 
  
  <br>

### 4. SW활용 현황 통계 API 구축을 위한 SQL 작성
- 월별 접속자 수
  
  ```sql
  SET @startDate = '2023-01-01'; # 조회 시작일
  SET @endDate = '2023-08-31'; # 조회 종료일
  
  SELECT 
    DATE_FORMAT(STR_TO_DATE(createDate, '%Y%m%d'), '%Y-%m') AS month, COUNT(*) AS visitorCount
  FROM 
    statistc.requestinfo
  WHERE 
    DATE_FORMAT(STR_TO_DATE(createDate, '%Y%m%d'), '%Y-%m-%d') BETWEEN 
      IFNULL(@startDate, DATE_FORMAT(STR_TO_DATE((SELECT MIN(createDate) FROM statistc.requestinfo), '%Y%m%d'), '%Y-%m-%d')) AND  # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y-%m-%d'))                                                                            # 종료일 NULL일 때 현재 날짜
  GROUP BY 
    month
  ORDER BY 
    month;
  ```
  
  - [월별 접속자 수 SQL 결과](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/SQL%EA%B2%B0%EA%B3%BC/%EC%9B%94%EB%B3%84%20%EC%A0%91%EC%86%8D%EC%9E%90%20%EC%88%98.csv)

  <br>

- 일자별 접속자 수

  ```sql
  SET @startDate = '2023-01-01'; # 조회 시작일
  SET @endDate = '2023-08-31'; # 조회 종료일
  
  SELECT 
    DATE_FORMAT(STR_TO_DATE(createDate, '%Y%m%d'), '%Y-%m-%d') AS date, COUNT(*) AS visitorCount
  FROM 
    statistc.requestinfo
  WHERE 
    DATE_FORMAT(STR_TO_DATE(createDate, '%Y%m%d'), '%Y-%m-%d') BETWEEN 
      IFNULL(@startDate, DATE_FORMAT(STR_TO_DATE((SELECT MIN(createDate) FROM statistc.requestinfo), '%Y%m%d'), '%Y-%m-%d')) AND  # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y-%m-%d'))                                                                            # 종료일 NULL일 때 현재 날짜
  GROUP BY 
    date
  ORDER BY 
    date;
  ```
  
  - [일자별 접속자 수 SQL 결과](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/SQL%EA%B2%B0%EA%B3%BC/%EC%9D%BC%EC%9E%90%EB%B3%84%20%EC%A0%91%EC%86%8D%EC%9E%90%20%EC%88%98.csv)
  
  <br>

- 평균 하루 로그인 수

  ```sql
  SET @startDate = '2023-01-01'; # 조회 시작일
  SET @endDate = '2023-08-31'; # 조회 종료일
  
  SELECT 
    COUNT(*) / DATEDIFF(
       IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y-%m-%d')),
      IFNULL(@startDate, DATE_FORMAT(STR_TO_DATE((SELECT MIN(createDate) FROM statistc.requestinfo), '%Y%m%d'), '%Y-%m-%d'))
    ) AS averageLogin
  FROM 
    statistc.requestinfo
  WHERE 
    DATE_FORMAT(STR_TO_DATE(createDate, '%Y%m%d'), '%Y-%m-%d') BETWEEN 
      IFNULL(@startDate, DATE_FORMAT(STR_TO_DATE((SELECT MIN(createDate) FROM statistc.requestinfo), '%Y%m%d'), '%Y-%m-%d')) AND  # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y-%m-%d'))                                                                            # 종료일 NULL일 때 현재 날짜
  ```
  - [평균 하루 로그인 수 SQL 결과](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/SQL%EA%B2%B0%EA%B3%BC/%ED%8F%89%EA%B7%A0%20%ED%95%98%EB%A3%A8%20%EB%A1%9C%EA%B7%B8%EC%9D%B8%20%EC%88%98.csv)
  
  <br>

- 휴일을 제외한 로그인 수
  
  ```sql
  SET @startDate = '2023-01-01'; # 조회 시작일
  SET @endDate = '2023-08-31'; # 조회 종료일

  SELECT 
    COUNT(*) AS visitorCount
  FROM 
    statistc.requestInfo
  WHERE 
    WEEKDAY(STR_TO_DATE(createDate, '%Y%m%d')) < 5 AND        # 토요일, 일요일 제외
    DATE_FORMAT(STR_TO_DATE(createDate, '%Y%m%d'), '%Y-%m-%d') BETWEEN 
      IFNULL(@startDate, DATE_FORMAT(STR_TO_DATE((SELECT MIN(createDate) FROM statistc.requestinfo), '%Y%m%d'), '%Y-%m-%d')) AND  # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y-%m-%d'))  AND                                                                       # 종료일 NULL일 때 현재 날짜
    DATE_FORMAT(STR_TO_DATE(createDate, '%Y%m%d'), '%Y-%m-%d') NOT IN (   # 공휴일 DB조회
      SELECT DATE_FORMAT(holiday_dt, '%Y-%m-%d') 
      FROM holidays
      WHERE is_holiday = 'Y'
    );
  ```

  - [휴일을 제외한 로그인 수 SQL 결과](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/SQL%EA%B2%B0%EA%B3%BC/%ED%9C%B4%EC%9D%BC%EC%9D%84%20%EC%A0%9C%EC%99%B8%ED%95%9C%20%EB%A1%9C%EA%B7%B8%EC%9D%B8%20%EC%88%98.csv)

  <br>
  
- 부서별 월별 로그인 수
  
  ```sql
  SET @startDate = '2023-01-01'; # 조회 시작일
  SET @endDate = '2023-08-31'; # 조회 종료일
  
  SELECT 
    ur.HR_ORGAN AS department,
    DATE_FORMAT(STR_TO_DATE(createDate, '%Y%m%d'), '%Y-%m') AS month, 
    COUNT(*) AS loginCount
  FROM 
    statistc.requestinfo ri
  JOIN
    statistc.user ur
  ON
    ri.userID = ur.userID
  WHERE
    DATE_FORMAT(STR_TO_DATE(ri.createDate, '%Y%m%d'), '%Y-%m-%d') BETWEEN
      IFNULL(@startDate, DATE_FORMAT(STR_TO_DATE((SELECT MIN(createDate) FROM statistc.requestinfo), '%Y%m%d'), '%Y-%m-%d')) AND  # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y-%m-%d'))  AND                                                                       # 종료일 NULL일 때 현재 날짜
    ur.HR_ORGAN in (IFNULL(@department, ur.HR_ORGAN))
  GROUP BY 
    month, department
  ORDER BY 
    month, department;
  ```

  - [부서별 월별 로그인 수 SQL 결과](https://github.com/jh990714/Comento_Bootcamp/blob/main/W3/SQL%EA%B2%B0%EA%B3%BC/%EB%B6%80%EC%84%9C%EB%B3%84%20%EC%9B%94%EB%B3%84%20%EB%A1%9C%EA%B7%B8%EC%9D%B8%20%EC%88%98.csv)
