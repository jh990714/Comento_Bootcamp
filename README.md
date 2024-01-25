# SW개발 직무부트캠프

## 1주차
  1. JDK 1.8설치
  2. Eclipse, Spring 다운로드 및 설치
  3. 톰캣 설정
  4. Hello World 출력
  5. mariaDB, mySql WorkBench 설치 및 샘플 DB 구축
  6. 스프링, Mariadb, MyBatis 연동, 데이터 조회

### 개발 환경
  * `Java 8`
  * `Jdk 1.8`
  * IDE : `Eclipse 2021-03`
  * STS : `Spring Tools 3 Add-On for Spring Tools 4 3.9.22.RELEASE`
  * WAS : `Tomcat 9.0`
  * DB : `MariaDB`

### 중간 Quiz
  MovieVO.java 
  
  ```Java
  public class MovieVO {
    private String movie_name; // 영화이름
    private String director; // 감독
    private String type; // 장르
    
    public String getMovie_name() {
      return movie_name;
    }
    public void setMovie_name(String movie_name) {
      this.movie_name = movie_name;
    }
    public String getDirector() {
      return director;
    }
    public void setDirector(String director) {
      this.director = director;
    }
    public String getType() {
      return type;
    }
    public void setType(String type) {
      this.type = type;
    }
  }
  ```

### 실행 결과
![1주차 실행결과](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/9483459a-ee71-4192-8639-d1b5ed54771a)

<br/><br/>

## 2주차
  ### 과제 

<br/><br/>

## 3주차
#### 1. Spring Boot 개발 환경 구축
- Test 실행 화면

  ![3주차 개발환경 실행결과](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/fb13db5b-ce70-49d3-9723-bb16c450e5d5)

<br><br>

#### 2. 통계 API(SW활용현황)을 위한 DB, TABLE 생성 
- requestInfo Table

  ```sql
  SELECT * FROM statistc.requestinfo;
  ```
  ![image](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/6236e3cd-cc1a-4379-80ab-72285465163d)

  <br>

- user Table  
  
  ```sql
  SELECT * FROM statistc.user;
  ```
  ![image](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/d4200fb5-5442-41e4-8eae-997859ae9f7c)

  <br>

#### 3. [20년도 로그인 수 API] 스프링부트, mybatis, mariadb 연동
- API Test [20년도 로그인수 API]
  
  ![20년도 로그인 수](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/cface7e2-bb99-4d6f-b47d-4f351b52cee1) 
  
  <br>

#### 4. SW활용 현황 통계 API 구축을 위한 SQL 작성
- 월별 접속자 수
  
  ```sql
  SET @startDate = '20210101'; # 조회 시작일
  SET @endDate = '20210831'; # 조회 종료일
  
  SELECT 
    DATE_FORMAT(left(createDate, 8), '%Y-%m') AS month, COUNT(*) AS visitor_count
  FROM 
    statistc.requestinfo
  WHERE 
    createDate BETWEEN 
      IFNULL(@startDate, left((SELECT MIN(createDate) FROM statistc.requestinfo), 8)) AND # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y%m'))                                        # 종료일 NULL일 때 현재 날짜
  GROUP BY 
    month
  ORDER BY 
    month;
  ```
  ![image](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/2c709afb-e102-4acb-be90-8d03ed5c5204)

  <br>

- 일자별 접속자 수

  ```sql
  SET @startDate = '20210101'; # 조회 시작일
  SET @endDate = '20210831'; # 조회 종료일
  
  SELECT 
    DATE_FORMAT(left(createDate, 8), '%Y-%m-%d') AS date, COUNT(*) AS visitor_count
  FROM 
    statistc.requestinfo
  WHERE 
    createDate BETWEEN 
      IFNULL(@startDate, left((SELECT MIN(createDate) FROM statistc.requestinfo), 8)) AND # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y%m'))                                        # 종료일 NULL일 때 현재 날짜
  GROUP BY 
    date
  ORDER BY 
    date;
  ```
  ![image](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/4499864e-508a-4041-95e9-00b69796ff4c)
  
  <br>

- 평균 하루 로그인 수

  ```sql
  SET @startDate = '20210101'; # 조회 시작일
  SET @endDate = '20210831'; # 조회 종료일
  
  SELECT 
    COUNT(*) / DATEDIFF(
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y%m%d')),
      IFNULL(@startDate, left((SELECT MIN(createDate) FROM statistc.requestinfo), 8))
    ) AS average_visitor
  FROM 
    statistc.requestinfo
  WHERE 
    createDate BETWEEN 
      IFNULL(@startDate, left((SELECT MIN(createDate) FROM statistc.requestinfo), 8)) AND # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y%m'));                                       # 종료일 NULL일 때 현재 날짜
  ```
  ![image](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/24bdb697-1b4e-46e2-9d15-d058e723cbcb)
  
  <br>

- 휴일을 제외한 로그인 수

- 부서별 월별 로그인 수
  
  ```sql
  SET @startDate = '20210101'; # 조회 시작일
  SET @endDate = '20210831'; # 조회 종료일
  
  SELECT 
    ur.HR_ORGAN AS department,
    DATE_FORMAT(left(ri.createDate, 8), '%Y-%m') AS month, 
    COUNT(*) AS login_count
  FROM 
    statistc.requestinfo ri
  JOIN
    statistc.user ur
  ON
    ri.userID = ur.userID
  WHERE
    ri.createDate BETWEEN
      IFNULL(@startDate, left((SELECT MIN(createDate) FROM statistc.requestinfo), 8)) AND # 시작일 NULL일 때 데이터의 가장 처음 날짜
      IFNULL(@endDate, DATE_FORMAT(NOW(), '%Y%m'))										# 종료일 NULL일 때 현재 날짜
  GROUP BY 
    department, month
  ORDER BY 
    department, month;
  ```
  ![image](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/231ecd3b-0036-4924-af49-50934c1ac549)
