<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<form class="bodySearch" method="post">
    <input type="text" name="searchBox" placeholder="Tìm kiếm video">
    <button name="videoSearchDayDu" formaction="TrangDayDu">Tìm kiếm</button>
</form>
<div class="bodyComponent">
	<div class="ytVideo">
		<iframe src="${Video.getLink()}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
	</div>
	<div class="ytInfor">
		<div class="ytTitle">
			<span>${Video.getTitle()}</span>
		</div>
		<form method="post" class="ytInteract">
        	<div class="like">
                 <span class="numberOfLike">${LikeNumber}</span>
                 <button value="${Video.getID()}" name="likeIDButton"><img src="${LikeButton}"></button>
            </div>
            <div class="share">
                 <span class="numberOfShare">${ShareNumber}</span>
                 <button value="${Video.getID()}" name="shareIDButton" name="share"><img src="views/material/icon/share.png"></button>
            </div>
                 <div class="view">
                 <span class="numberOfView">${Video.getViews()} lượt xem</span>
            </div>
        </form>
	</div>
	<div class="ytDescription">
		<span>${Video.getDescription()}</span>
	</div>
</div>