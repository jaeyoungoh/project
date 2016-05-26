<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
function formsubject(url){
	var form1 = document.d_form;
	form1.submit(url);
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${pDto.p_subject}#${pDto.p_id}</title>
</head>
<body>
	<div id="content">
		<div class="subject"></div>
		<div class="value">${pDto.p_subject}#${pDto.p_id}</div>
		<a class="icon icon-edit" href="${path}/project.do?code=mod&p_id=${pDto.p_id}" >편집</a>
		<div class="value">${pDto.p_contents}</div>
	
		<a href="#" name="preview" class="m_contextual">뒤로</a>
		<a href="#" name="nextview" class="m_contextual">다음</a>
		
		<div class="contextual">상태 :</div>
		<div class="value">${pDto.p_type}</div>
		<div class="contextual">우선순위 :</div>
		<div class="value">${pDto.p_priority}</div>
		<div class="contextual">담당자 ID :</div>
		<div class="value">${pDto.u_id}</div>
		<div class="contextual">이름 :</div>
		<div class="value">${uDto.u_name}</div>
		<div class="contextual">E-mail :</div>
		<div class="value">${uDto.u_email}</div>
		<div class="contextual">시작 시간 :</div>
		<div class="vlaue">${pDto.p_sdate}</div>
		<div class="contextual">완료 기한 :</div>
		<div class="value">${pDto.p_edate}</div>
		<div class="contextual">진척도 :</div>
		<div class="value">${pDto.p_progress}</div>
		<div class="contextual">하위 프로젝트 :</div>
		<div class="value">${pDto.p_depth}</div>
		<div class="contextual">상위 프로젝트 :</div>
		<div>${pDto.p_upper}</div>
	</div>
	<br>
	<a href="${path}/project.do?code=list" class="icon icon-list">목록</a>
		<form name="d_form" method="post" action="${path}/project.do?code=del">
		<input type="hidden" name="p_id" value="${pDto.p_id}">
	</form>
	<a href="#" onclick="formsubject(); return false;" class="icon icon-del">삭제</a>

</body>
</html>