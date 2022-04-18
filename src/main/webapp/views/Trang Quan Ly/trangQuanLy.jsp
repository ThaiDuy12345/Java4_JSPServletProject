<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Trang Chá»§</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="views/Css/TrangChuCSS.css">
    <link rel="stylesheet" href="views/Css/TrangChuDayDuCSS.css">
    <link rel="stylesheet" href="views/Css/TrangQuanLyCSS.css">
    <script src="views/Javascript/TrangChuJS.js"></script>
</head>
<body>
<div onLoad="loadDisplay(${number})" class="container">
    <div class="header">
        <%@include file="/views/common part/headerTrangDayDuAdmin.jsp" %>
    </div>
    <div class="body">
		<%@include file="/views/common part/bodyQuanLyComponent.jsp" %>
    </div>
    <div class="footer">
        <%@include file="/views/common part/footer.jsp" %>
    </div>
</div>
</body>
</html>