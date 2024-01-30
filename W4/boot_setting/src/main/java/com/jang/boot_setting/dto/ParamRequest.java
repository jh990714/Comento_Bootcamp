package com.jang.boot_setting.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

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