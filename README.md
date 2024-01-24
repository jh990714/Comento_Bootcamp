# SW개발 직무부트캠프

## 1주차
#### 1. JDK 1.8설치
#### 2. Eclipse, Spring 다운로드 및 설치
#### 3. 톰캣 설정
#### 4. Hello World 출력
#### 5. mariaDB, mySql WorkBench 설치 및 샘플 DB 구축
#### 6. 스프링, Mariadb, MyBatis 연동, 데이터 조회

### 개발 환경
* `Java 8`
* `Jdk 1.8`
* IDE : `Eclipse 2021-03`
* STS : `Spring Tools 3 Add-On for Spring Tools 4 3.9.22.RELEASE`
* WAS : `Tomcat 9.0`
* DB : `MariaDB`

### 중간 Quiz
#### MovieVO.java 
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

  
#### 2. 통계 API(SW활용현황)을 위한 DB, TABLE 생성 
- requestInfo Table

![requestinfo_table](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/aa11fe36-c981-4f6e-a877-79ab7beafee3)


- user Table

![user_table](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/ac06c88d-c4d2-4134-bf9a-41c0009bd866)

#### 3. [20년도 로그인 수 API] 스프링부트, mybatis, mariadb 연동
- API Test [20년도 로그인수 API]
  
![20년도 로그인 수](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/cface7e2-bb99-4d6f-b47d-4f351b52cee1)

#### 4. SW활용 현황 통계 API 구축을 위한 SQL 작성
- 월별 접속자 수

![월별 로그인 수 sql](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/6fdcfe19-1da6-4d57-9d9f-11f8ba467c84)

- 일자별 접속자 수

![일자별 접속자 수 sql](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/9cbc21c4-8594-4eec-8bfc-7b005d9702ee)

- 평균 하루 로그인 수

![평균 하루 로그인 수 sql](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/598ca9bd-ebe4-42ba-9e07-8a812be2b83d)

- 휴일을 제외한 로그인 수

- 부서별 월별 로그인 수

![부서별 월별 로그인 수](https://github.com/jh990714/Comento_Bootcamp/assets/144774186/70a658f4-f0d3-4804-b2ec-6bab87f3cc30)



