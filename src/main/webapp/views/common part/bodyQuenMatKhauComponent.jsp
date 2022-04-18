<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bodyComponent">
	<div class="bodyDangNhapTitle">
		<span>Quên mật khẩu</span>
	</div>
	<form class="bodyQuenMatKhauForm" method="post">
		<div class="emailInput">
			<input type="text" name="emailInput" placeholder="Email">
		</div>
		<div class="dangNhapButton">
			<div>
				<button name="khoiPhucMatKhauButton">Khôi phục mật khẩu</button>
			</div>
		</div>
	</form>
	<div class="result">
		<span>${ketQua}</span>
	</div>
</div>