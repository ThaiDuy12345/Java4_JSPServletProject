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
            <%@include file="/views/common part/headerCustomer.jsp" %>
        </div>
        <div class="body">
		    <div class="bodyTitle">
                <div>Các video được đánh giá nhiều lượt xem nhất</div>
            </div>
            <div class="bodyComponent">
                <div class="bodyItemComponent">
                    <div class="bodyItemComponentImg">
                        <img src="/views/material/img/exit the earth's atmosphere.png">
                    </div>
                </div>
                <div class="bodyItemComponentInfor">
                    <div class="poster">
                        <img src="views/material/img/exit the earth's atmosphere.png">
                    </div>
                    <div class="rightPoster">
                        <div class="name">
                            <span>Camellia - exit the earth's atmosphere!!!</span>
                        </div>
                        <form method="post" class="bodyItemComponentInteract">
                            <div class="like">
                                <button><img src=""></button>
                                <div class="numberOfLike"></div>
                            </div>
                            <div class="view">
                                <div class="numberOfView">90</div>
                            </div>
                            <div class="share">
                                <button><img src=""></button>
                                <div class="numberOfShare"></div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="bodyItemComponent">

            </div>
            <div class="bodyItemComponent">

            </div>
            <div class="bodyItemComponent">

            </div>
            <div class="bodyItemComponent">

            </div>
            <div class="bodyItemComponent">

            </div>
            <form class="moreButton">
                <button>Xem thêm</button>
            </form>
        </div>
        <div class="footer">
            <%@include file="/views/common part/footer.jsp" %>
        </div>
    </div>
</body>
</html>