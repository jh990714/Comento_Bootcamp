# 1주차
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

