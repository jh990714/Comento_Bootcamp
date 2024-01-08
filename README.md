# [1차] 개발환경 세팅


## 개발 환경
* Java 8
* Jdk 1.8
* IDE : Eclipse 21.03
* STS : Spring Tools 3 Add-On for Spring Tools 4 3.9.22.RELEASE
* WAS : Tomcat 9.0
* DB : MariaDB

## 중간 Quiz
MovieVO.java

    public class MovieVO {
    	private String movie_name; // 영화 제목
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
