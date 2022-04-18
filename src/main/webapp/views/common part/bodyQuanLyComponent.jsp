<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="bodyComponent">
	<form class="buttonComponent" method="post">
		<button name="restartQuanLyNoiDung">Restart</button>
	</form>
	<div id="mainComponent">
		<div class="thongKe">
			<div class="thongKeComponent">
				<span class="title">Tổng số lượt like</span>
				<span class="value" id="likeNumber">${like}</span>
			</div>
			<div class="thongKeComponent">
				<span class="title">Tổng số lượt share</span>
				<span class="value" id="shareNumber">${share}</span>
			</div>
			<div class="thongKeComponent">
				<span class="title">Tổng số lượt xem</span>
				<span class="value" id="viewNumber">${view}</span>
			</div>
		</div>
		<form class="video" method="get">
			<c:forEach items="${list}" var="Video">
				<button name="clickOnID_main" value="${Video.getID()}" >${Video.getTitle()}</button>
			</c:forEach>
		</form>
	</div>
	<form id="userComponent" method="get">
		<div class="table">
			<c:forEach items="${list1}" var="User">
				<button name="clickOnID_user" onclick="changeD(1)" value="${User.getID()}" >${User.getFullName()}</button>
			</c:forEach>
		</div>
		<div class="interactButton">
			<div class="interactComponent"><span>ID:</span><input name="userID" type="number" value="${User.getID()}"></div>
			<div class="interactComponent"><span>Email:</span><input name="userEmail" type="text" value="${User.getEmail()}"></div>
			<div class="interactComponent"><span>FullName:</span><input name="userFullName" type="text" value="${User.getFullName()}"></div>
			<div class="interactComponent"><span>Password:</span><input name="userPassword" type="text" value="${User.getPassword()}"></div>
			<div class="interactComponent"><span>Admin:</span><input name="userAdmin" type="checkbox" checked= "${User.isAdmin()}" value="${User.isAdmin()}"></div>
		</div>
	</form>
	<form id="videoComponent">
		<div class="table">
			<c:forEach items="${list}" var="Video">
				<button name="clickOnID_video" onclick="changeD(2)" value="${Video.getID()}" >${Video.getTitle()}</button>
			</c:forEach>
		</div>
		<div class="interactButton">
			<div class="interactComponent"><input></div>
			<div class="interactComponent"><input></div>
			<div class="interactComponent"><input></div>
			<div class="interactComponent"><input></div>
			<div class="interactComponent"><input></div>
		</div>
	</form>
</div>
<div id="bodyCategory" class="bodyCategory" onmouseover="categoryPress()" onmouseout="categoryOut()">
	<div class="mainCategoryTitle">
		<button id="mainButton" onclick="changeQuanLyComponent(this)" value="main" class="displayButton" onmouseover="categoryButtonPress(this)" onmouseout="categoryButtonOut(this)">Thống kê chính</button>
	</div>
	<div class="userCategoryTitle">
		<button id="userButton" onclick="changeQuanLyComponent(this)" value="user" class="displayButton" onmouseover="categoryButtonPress(this)" onmouseout="categoryButtonOut(this)">Quản lý nội dung người dùng</button>
	</div>
	<div class="videoCategoryTitle">
		<button id="videoButton" onclick="changeQuanLyComponent(this)" value="video" class="displayButton" onmouseover="categoryButtonPress(this)" onmouseout="categoryButtonOut(this)">Quản lý nội dung video</button>	
	</div>
</div>