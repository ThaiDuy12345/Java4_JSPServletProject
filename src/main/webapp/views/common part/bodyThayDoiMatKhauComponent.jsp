<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bodyComponent">
	<div class="bodyDangNhapTitle">
		<span>Thay đổi mật khẩu</span>
	</div>
	<form class="bodyThayDoiMatKhauForm" method="post">
		<div class="emailInput">
			<input type="text" name="oldPasswordInput" placeholder="Old Password">
		</div>
		<div class="passwordInput">
			<input type="password" name="newPasswordInput" placeholder="New Password">
		</div>
		<div class="confirmpasswordInput">
			<input type="password" name="newPasswordConfirmInput" placeholder="New Password Confirm">
		</div>
		<div class="dangNhapButton">
			<div>
				<button name="thayDoiMatKhauButton">Thay đổi mật khẩu</button>
			</div>
		</div>
	</form>
	<div class="result">
		<span>${ketQua}</span>
	</div>
</div>