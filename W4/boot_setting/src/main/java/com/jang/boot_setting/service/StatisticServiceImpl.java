package com.jang.boot_setting.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.jang.boot_setting.dao.StatisticMapper;
import com.jang.boot_setting.dto.ParamRequest;
 
@Service
public class StatisticServiceImpl implements StatisticService {
    
    
    @Autowired
    private StatisticMapper uMapper;
    
    @Override
    public HashMap<String, Object> yearloginNum (String year) {
        // TODO Auto-generated method stub
        HashMap<String, Object> retVal = new HashMap<String,Object>();
         
        retVal = uMapper.selectYearLogin(year);
        retVal.put("year", year);
        retVal.put("is_success", true);
        
        return retVal;
    }
    
    // 월별 접속자 수 조회
    @Override
    public HashMap<String, Object> monthlyVisitorCount(ParamRequest param) {
    	HashMap<String, Object> retVal = new HashMap<>();
        List<HashMap<String, Object>> result = uMapper.monthlyVisitor(param);     
        
        
        // totalCount 계산
        int totalCount = 0;
        for (HashMap<String, Object> map : result) {
        	totalCount += ((Number) map.get("visitorCount")).intValue();
            
        }
        
        HashMap<String, Object> info = new HashMap<>();
        info.put("details", result);
        info.put("totalCount", totalCount);
        
        retVal.put("returnCode", 200);
        retVal.put("returnMessage", "Success");
        retVal.put("param", param);
        retVal.put("info", info);  
        
        return retVal;
    }
    
    // 일별 접속자 수 조회
    @Override
    public HashMap<String, Object> dailyVisitorCount (ParamRequest param) {
    	HashMap<String, Object> retVal = new HashMap<>();
        List<HashMap<String, Object>> result = uMapper.dailyVisitor(param);     
        
     // totalCount 계산
        int totalCount = 0;
        for (HashMap<String, Object> map : result) {
        	totalCount += ((Number) map.get("visitorCount")).intValue();
            
        }
        
        HashMap<String, Object> info = new HashMap<>();
        info.put("details", result);
        info.put("totalCount", totalCount);
        
        retVal.put("returnCode", 200);
        retVal.put("returnMessage", "Success");
        retVal.put("param", param);
        retVal.put("info", info); 
        
        return retVal;
    }
    
    // 평균 하루 로그인 수 조회
    @Override
    public HashMap<String, Object> dailyAverageLoginCount(ParamRequest param) {
    	HashMap<String, Object> retVal = new HashMap<String,Object>();
    	HashMap<String, Object> result = uMapper.dailyAverageLogin(param);
    	
    	retVal.put("returnCode", 200);
    	retVal.put("returnMessage", "Success");
    	retVal.put("param", param);
    	retVal.put("info", result);
    	         
        return retVal;
    }
    
    // 부서별 월별 로그인 수 조회
    @Override
    public HashMap<String, Object> monthlyLoginDepartmentCount (ParamRequest param) {
    	HashMap<String, Object> retVal = new HashMap<String,Object>();
        List<HashMap<String, Object>> result = uMapper.monthlyLoginDepartment(param);
        
        Map<String, List<HashMap<String, Object>>> info = new HashMap<>();
        for (HashMap<String, Object> entry : result) {
            String department = (String) entry.get("department");
            if (!info.containsKey(department)) {
                info.put(department, new ArrayList<>());
            }
            info.get(department).add(entry);
        }
        
        retVal.put("returnCode", 200);
        retVal.put("returnMessage", "Success");
        retVal.put("param", param);
        retVal.put("info", info);
        
        return retVal;
    }
    
    // 휴일을 제외한 로그인 수 조회
    @Override
    public HashMap<String, Object> nonHolidayLoginCount (ParamRequest param) {
    	HashMap<String, Object> retVal = new HashMap<String,Object>();
        HashMap<String, Object> result = uMapper.nonHolidayLogin(param);
        
        retVal.put("returnCode", 200);
        retVal.put("returnMessage", "Success");
        retVal.put("param", param);
        retVal.put("info", result);
        
        return retVal;
    }
}