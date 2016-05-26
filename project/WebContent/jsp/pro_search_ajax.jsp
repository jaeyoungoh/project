<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script>
$.ajax({
    type:"GET",
    url : "${path}/test.do",
    dataType : 'json',
    error : function() {
        alert("Error Occured");
    },
    success : function(data) {
    	
    	alert(data);

    }
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>12342143</h1>


</body>
</html>