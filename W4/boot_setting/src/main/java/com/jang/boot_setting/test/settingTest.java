package com.jang.boot_setting.test;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jang.boot_setting.dto.ParamRequest;
import com.jang.boot_setting.service.StatisticService;
 
 
 
@Controller
public class settingTest {
    
 
    @Autowired
    private StatisticService service;
    
    @ResponseBody 
    @RequestMapping(value = {"/monthly_visitor", "/daily_visitor", "/daily_average_login", "/monthly_login_department", "/non_holiday_login"})
    public Map<String, Object> sqlVisitor(HttpSession session, HttpServletRequest request, @Valid ParamRequest param) throws Exception{ 
    	String requestUrl = (String)request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
    	
    	if(requestUrl.equals("/monthly_visitor")) { // 월별 접속자 수 조회
    		return service.monthlyVisitorCount(param);
    	}
    	else if(requestUrl.equals("/daily_visitor")) { // 일별 접속자 수 조회
    		return service.dailyVisitorCount(param);
    	}
    	else if(requestUrl.equals("/daily_average_login")) { // 평균 하루 로그인 수 조회
    		return service.dailyAverageLoginCount(param);
    	}
    	else if(requestUrl.equals("/monthly_login_department")) { // 부서별 월별 로그인 수 조회
    		return service.monthlyLoginDepartmentCount(param);
    	}
    	else if(requestUrl.equals("/non_holiday_login")) { // 휴일을 제외한 로그인 수 조회
    		return service.nonHolidayLoginCount(param);
    	}
    	return null;
	    
    }

    
    @ResponseBody
    @RequestMapping("/sqlyear_statistic")
    public Map<String, Object> sqlyearloginNum(String year) throws Exception{  
        return service.yearloginNum(year);
    }
    
    
    @RequestMapping("/test") 
    public ModelAndView test() throws Exception{ 
        ModelAndView mav = new ModelAndView("test"); 
        mav.addObject("name", "devfunpj"); 
        List<String> resultList = new ArrayList<String>(); 
        resultList.add("!!!HELLO WORLD!!!"); 
        resultList.add("설정 TEST!!!"); 
        resultList.add("설정 TEST!!!"); 
        resultList.add("설정 TEST!!!!!"); 
        resultList.add("설정 TEST!!!!!!"); 
        mav.addObject("list", resultList); 
        return mav; 
    }
 
}