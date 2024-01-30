package com.jang.boot_setting.dao;

import java.util.HashMap;
import java.util.List;

import com.jang.boot_setting.dto.ParamRequest;

// import com.jang.boot_setting.dto.StatisticDto;
 
public interface  StatisticMapper {
	public HashMap<String, Object> selectYearLogin(String year);
    public List<HashMap<String, Object>> monthlyVisitor(ParamRequest param); // 월별 접속자 수 조회
    public List<HashMap<String, Object>> dailyVisitor(ParamRequest param); // 일별 접속자 수 조회
    public HashMap<String, Object> dailyAverageLogin(ParamRequest param); // 평균 하루 로그인 수 조회
    public List<HashMap<String, Object>> monthlyLoginDepartment(ParamRequest param); // 부서별 월별 로그인 수 조회
    public HashMap<String, Object> nonHolidayLogin(ParamRequest param); // 휴일을 제외한 로그인 수 조회
}