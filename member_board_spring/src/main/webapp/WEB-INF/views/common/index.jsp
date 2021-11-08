<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<jsp:include page="head.jsp" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<main class="index">
	<section>
		<h1>해위^^</h1>
		<article>
			<div class="slider">
				<img alt="daisies"
					src="${pageContext.request.contextPath}/resources/images/daisies.jpg">
				<img alt="plant"
					src="${pageContext.request.contextPath}/resources/images/plant.jpg"> 
				<img alt="succulents"
					src="${pageContext.request.contextPath}/resources/images/succulents.jpg">
			</div>
		</article>

		<h4 class="display-6">자유게시판</h4>
		<ul class="row justify-content-center">
			<c:forEach items="${list}" var="b">
				<li class="col-5 m-2"><a href="board/detail?bno=${b.bno}">
						<div>
							<h4 class="text-truncate small">
								<c:out value="${b.title}" escapeXml="true" />
							</h4>
							<p class="text-truncate text-muted small">
								<c:out value="${b.content}" escapeXml="true" />
							</p>
						</div>
				</a></li>
			</c:forEach>
		</ul>
	</section>
	<aside>
		<c:choose>
			<c:when test="${empty member}">
				<form action="login">
					<input type="submit" value="로그인">
				</form>
				<p>
					<a href="join">회원가입</a> <a href="#">ID/PW찾기</a>
				</p>
			</c:when>
			<c:otherwise>
				<p>${member.name}님환영합니다.</p>
				<p>
					<a href="join">정보수정</a> <a href="logout">로그아웃</a>
				</p>
			</c:otherwise>
		</c:choose>
	</aside>
	</main>
	<script>
		$(function() {
			$(".slider").bxSlider({
				mode : 'fade',
				pager : false,
				controls : false,
				auto : true
			});
		})
	</script>
	<jsp:include page="footer.jsp" />
</body>
</html>
