<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${path}/css/jquery-ui.css">
<script type="text/javascript">
function formsubject(){
	var form1 = document.form_padd;
	form1.submit();
}
</script>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>프로젝트 추가</title>
</head>
<body>

	<form action="${path}/project.do" method="post" name="form_padd">
	타입 :
	<select name="p_type">
		<option value="결함">결함</option>
		<option value="테스트">테스크</option>
		<option value="새기능">새기능</option>
		<option value="지원">지원</option>
		<option value="요구사항">요구사항</option>
		<option value="이슈">이슈</option>
		<option value="리스크">리스크</option>
		<option value="산출물">산출물</option>
	</select>
	<div>
		제목 : <input type="text" name="p_subject" />
	</div>
	<div>
		입력 내용 : <br>
		<textarea rows="30" cols="40" name="p_contents"></textarea>
		
	</div>
	<div>
		담당자 : <select name="u_id">
				<c:forEach var="list" items="${list}">
		 		<option value="${list}">${list}</option>
				</c:forEach>
		</select>
	</div>
	<div>
		우선순위 : <select name="p_priority">
			<option value="즉시">즉시</option>
			<option value="긴급">긴급</option>
			<option value="높음">높음</option>
			<option value="보통">보통</option>
			<option value="낮음">낮음</option>
		</select>
	</div>
	<div>
		진척도 : <select name="p_progress" >
			<option value="0">0</option>
			<option value="10">10</option>
			<option value="20">20</option>
			<option value="30">30</option>
			<option value="40">40</option>
			<option value="50">50</option>
			<option value="60">60</option>
			<option value="70">70</option>
			<option value="80">80</option>
			<option value="90">90</option>
			<option value="100">100</option>
		</select>
	</div>
	<div>
		상위 프로젝트 설정 : <input type="text" name="p_upper" />
	</div>
	<div>
		하위 프로젝트 설정 : <input type="text" name="p_depth" />
	</div>
	<div>
		<script>
			$(function() {
				$("#datepicker").datepicker();
				$("#datepicker1").datepicker();
			});
		</script>

		시작 날짜 : <input type="text" name="p_sdate" id="datepicker" />
	</div>
	<div>
		마감 날짜 : <input type="text" name="p_edate" id="datepicker1" />
	</div>
	<input type="hidden" name="code" value="add">
	</form>
	
	<div class="pro_add"><a href="#" onclick="formsubject(); return false;" >확인</a></div>
	<a href="#" onclick="javascript:history.back()" >취소</a>

	
	

</body>
</html>