<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bodyComponent">
	<div class="bodyDangNhapTitle">
		<span>Đăng nhập</span>
	</div>
	<form class="bodyDangNhapForm" method="post">
		<div class="emailInput">
			<input type="text" name="emailInput" placeholder="Email">
		</div>
		<div class="passwordInput">
			<input type="password" name="passwordInput" placeholder="Password">
		</div>
		<div class="dangNhapButton">
			<div>
				<button name="dangNhapButton">Đăng nhập</button>
				<button formaction="QuenMatKhau">Quên mật khẩu</button>
			</div>
		</div>
	</form>
	<div class="result">
		<span>${ketQuaDangNhap}</span>
	</div>
</div>