<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="bodyComponent">
	<form class="bodyComponent" method="post">
		<button name="videoClick" value="${Video.getID()}" formaction="TrangChiTiet" class="bodyItemComponentInfor">
             <div class="poster">
                   <img src="${Video.getPoster()}">
             </div>
             <div class="rightPoster">
                   <div class="name">
                         <span>${Video.getTitle()}</span>
                   </div>
                   <div class="description">
                         <span>${Video.getDescription()}</span>
                   </div>
                   <div class="Interact">
                         <div class="view">
                               <span class="numberOfView">${Video.getViews()} lượt xem</span>
                         </div>
                   </div>
             </div>
         </button>
         <div id="Share" class="bodyItemComponentInfor">
         	<div class="ShareItem">
         		<input type="text" name="sEmail" placeholder="Email người gửi" value="${User.getEmail()}">
         	</div>
         	<div class="ShareItem">
         		<input type="password" placeholder="Mật khẩu email người gửi" name="sPassword">
         	</div>
         	<div class="ShareItem">
         		<input type="text" placeholder="Email người nhận" name="rEmail">
         	</div>
         	<div class="ShareItem">
         		<input type="text" placeholder="Tiêu đề" name="sTitle" value="New video notification from ${User.getFullName()}">
         	</div>
         	<div class="ShareItem">
         		<input type="text" name="sDescription" placeholder="Nội dung gửi" value="Mời bạn xem video ${Video.getTitle()} tại: ${Video.getLink()}">
         	</div>
         	<div class="ShareItem">
         		<button name="sendEmailButton">Gửi email</button>
         	</div>
         </div>
	</form>
	<div class="result">
		<span>${ketQua}</span>
	</div>
</div>