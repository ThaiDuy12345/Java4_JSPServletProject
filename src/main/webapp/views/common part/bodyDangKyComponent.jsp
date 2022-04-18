<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bodyComponent">
	<div class="bodyDangNhapTitle">
		<span>Đăng ký</span>
	</div>
	<form class="bodyQuenMatKhauForm" method="post">
		<div class="emailInput">
			<input type="text" name="emailInput" placeholder="Email">
		</div>
		<div class="usernameInput">
			<input type="text" name="fullNameInput" placeholder="FullName">
		</div>
		<div class="passwordInput">
			<input type="password" name="passwordInput" placeholder="Password">
		</div>
		<div class="confirmPasswordInput">
			<input type="password" name="confirmPasswordInput" placeholder="Confirm Password">
		</div>
		<div class="dangNhapButton">
			<div>
				<button name="dangKyButton">Đăng ký</button>
			</div>
		</div>
	</form>
	<div class="result">
		<span>${ketQua}</span>
	</div>
</div>