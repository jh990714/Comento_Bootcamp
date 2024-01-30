package com.jang.boot_setting.service;
 
import java.util.HashMap;

import com.jang.boot_setting.dto.ParamRequest;
 
public interface StatisticService {
    public HashMap<String,Object> yearloginNum (String year);
    
    
    public HashMap<String, Object> monthlyVisitorCount (ParamRequest param); // 월별 접속자 수 조회
    public HashMap<String, Object> dailyVisitorCount (ParamRequest param); // 일별 접속자 수 조회
    public HashMap<String, Object> dailyAverageLoginCount(ParamRequest param); // 평균 하루 로그인 수 조회
    public HashMap<String, Object> monthlyLoginDepartmentCount (ParamRequest param); // 부서별 월별 로그인 수 조회
    public HashMap<String, Object> nonHolidayLoginCount (ParamRequest param); // 휴일을 제외한 로그인 수 조회
}