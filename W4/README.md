# 4주차

### 추가 사항
- 전역 예외처리
  ```Java
  @ControllerAdvice
  public class GlobalExceptionHandler {
    // 데이터 바인딩 과정 예외
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<Object> handleBindException(BindException e){
        Map<String, Object> error = new HashMap<>();
        error.put("returnCode", 400);
        error.put("returnMessage", "데이터 형식이 잘못되었습니다. ex) startDate=20230101&endDate=20230801");
  
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  
    // 예외 추가 가능
  }
  ```
<br>

- DTO

  ```Java
  
  public class ParamRequest {
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate startDate; // 시작 날짜
  
    @DateTimeFormat(pattern = "yyyyMMdd")
    private LocalDate endDate; // 종료 날짜
  	
    private String department; // 부서명
    
    public LocalDate getStartDate() {
      return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
      this.startDate = startDate;
    }
    
    public LocalDate getEndDate() {
      return endDate;
    }
    
    public void setEndDate(LocalDate endDate) {
      this.endDate = endDate;
    }
    
    public String getDepartment() {
      return department;
    }
    
    public void setDepartment(String department) {
      this.department = department;
    }
  
  }
  ```

<br>

### 1. SQL(5가지)를 중심으로 API를 개발
  
- 월별 접속자 수 조회 API
 
   요청 URL : `/monthly_visitor?startDate={startDate}&endDate={endDate}`

  - [월별 접속자 수 조회 API 결과1](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/monthly_visitor.json)
  
  - [월별 접속자 수 조회 API 결과2](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/monthly_visitor%20(1).json)

  <br>

- 일자별 접속자 수 조회 API

  요청 URL : `/daily_visitor?startDate={startDate}&endDate={endDate}`

  - [일자별 수 조회 API 결과1](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/daily_visitor.json)
  
  - [일자별 수 조회 API 결과2](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/daily_visitor%20(1).json)

  <br>
  
- 평균 하루 로그인 수 조회 API

  요청 URL : `/daily_average_login?startDate={startDate}&endDate={endDate}`

  - [평균 하루 로그인 수 조회 API 결과1](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/daily_average_login.json)
  
  - [평균 하루 로그인 수 조회 API 결과2](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/daily_average_login%20(1).json)

  <br>
  
- 휴일을 제외한 로그인 수 조회 API

  요청 URL : `/non_holiday_login?startDate={startDate}&endDate={endDate}`

  - [휴일을 제외한 로그인 수 조회 API 결과1](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/non_holiday_login.json)
  
  - [휴일을 제외한 로그인 수 조회 API 결과2](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/non_holiday_login%20(1).json)

  <br>

- 부서별 월별 로그인 수 조회 API

  요청 URL : `/monthly_login_department?startDate={startDate}&endDate={endDate}&department={department}`

  - [부서별 월별 로그인 수 조회 API 결과1](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/monthly_login_department.json)
  
  - [부서별 월별 로그인 수 조회 API 결과2](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B2%B0%EA%B3%BC/monthly_login_department%20(1).json)

<br><br>

### 2. API가이드 문서를 보완
- API 가이드 문서

  - [API 가이드 문서](https://github.com/jh990714/Comento_Bootcamp/blob/main/W4/API%EA%B0%80%EC%9D%B4%EB%93%9C%20%EB%AC%B8%EC%84%9C/API%EA%B0%80%EC%9D%B4%EB%93%9C%EB%AC%B8%EC%84%9C_%EC%9E%A5%EC%A4%80%ED%98%81.pdf)

