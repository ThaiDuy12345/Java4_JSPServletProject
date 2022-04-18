<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="bodyTitle">
                <div>Các video được đánh giá nhiều lượt xem nhất</div>
            </div>
            <hr/>
            <form class="bodyComponent" method="post">
            <c:forEach items="${Videos}" var="Video">
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
                </c:forEach>
                </form>