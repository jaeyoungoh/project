<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript">
var s_obj = new Object();
s_obj.searchQuery = $('#searchQuery').val();
s_obj.s_type = $('#s_type').val();

$(function() {
	$("#s_call").click(function() {
		var str = $("#f_search").serialize();
     $.ajax({
           type:"GET",
           url:"${path}/project.do?code=search_plist",
           contentType: "application/json; charset=utf-8",
           data :s_obj,
           dataType:"json", // 옵션이므로 JSON으로 받을게 아니면 안써도 됨
           success : function(data) {
        	   alert("성공");
        	   var receivedData = [];

               $.each(data.jsonArray, function(index) {
                   $.each(data.jsonArray[index], function(key, value) {
                       var point = [];

                           point.push(key);
                           point.push(value);
                           receivedData.push(point);
							alert("receivedData.push(point)");
                       }); 
               });
           },
           error : function(xhr, status, error) {
                 alert("에러발생");
           }
    	 });
	});
});
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 리스트</title>
</head>
<body>

<div class="pro_add"><a href="${path}/project.do?code=search_u_id" >프로젝트 추가</a></div>
<form name = "f_search" method="GET" action="">
<div class="pro_search">
<select id=s_type name="s_type">
			<option value="유형">유형</option>
			<option value="우선순위">우선순위</option>
			<option value="제목">제목</option>
			<option value="담당자">담당자</option>
			<option value="번호">프로젝트번호</option>
</select>

<input type="text" id="searchQuery" name="searchQuery"/>
&nbsp;&nbsp;<a href="#구현 중" id="s_call" >검색</a></div>
</form>
<br>
	<table>
		<tr>
			<th>번호</th>
			<th>유형</th>
			<th>우선순위</th>
			<th>제목</th>
			<th>진행도</th>
			<th>상위프로젝트</th>
			<th>담당자</th>
			<th>시작기간</th>
			<th>마감기간</th>
		</tr>
		<c:forEach items="${list}" var="list">
			<c:choose>
				<c:when test="${list.p_use_yn eq 'Y'}">
					<tr>
						<td>${list.p_id}</td>
						<td>${list.p_type}</td>
						<td>${list.p_priority}</td>
						<td><a href="${path}/project.do?code=view&p_id=${list.p_id}">${list.p_subject}</a></td>
						<td>${list.p_progress}</td>
						<td>${list.p_upper}</td>
						<td>${list.u_id}</td>
						<td>${list.p_sdate}</td>
						<td>${list.p_edate}</td>
					</tr>
				</c:when>
				<c:when test="${list eq null}">
					<td colspan="9" align="center"> 검색된 결과가 없습니다.</td>
				</c:when>
				
				<c:otherwise>
					<tr>
						<td colspan="9" align="center">"${list.p_subject}"는 삭제된 프로젝트 입니다.</td>
					</tr>
					
				</c:otherwise>
			</c:choose>
		</c:forEach>

	</table>
	


</body>
</html>