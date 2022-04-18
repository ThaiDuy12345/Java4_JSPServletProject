<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Trang Chủ</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="views/Css/TrangChuCSS.css">
</head>
<body>
<div class="container">
    <div class="header">
        <%@include file="/views/common part/header.jsp" %>
    </div>
    <div class="body">
		<%@include file="/views/common part/bodyComponent.jsp" %>
        <form class="moreButton">
            <button formaction="TrangDayDu">Xem thêm</button>
        </form>
    </div>
    <div class="footer">
        <%@include file="/views/common part/footer.jsp" %>
    </div>
</div>
</body>
</html>