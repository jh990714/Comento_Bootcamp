<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>[1차] 개발환경 세팅</title>
</head>
<body>
    <h1>[1차] 개발환경 세팅 - 장준혁</h1>
 
    <table>
        <thead>
            <tr>
                <th>영화이름</th>
                <th>감독</th>
                <th>장르</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${movieList}" var="movie">
                <tr>
                    <td>${movie.movie_name}</td>
                    <td>${movie.director}</td>
                    <td>${movie.type}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 
 
</body>
</html>