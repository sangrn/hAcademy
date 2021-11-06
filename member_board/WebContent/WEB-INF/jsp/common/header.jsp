<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<div>
		<div>
			<a href="<%=request.getContextPath()%>/index.html">
			<div class="img-box">
					<img src="/images/logo.gif">
				</div></a>
			<p>ㅋㅋ ㅎㅎ ㅈㅅ!</p>
		</div>
	</div>
	<nav>
		<ul>
			<li><a href="#">공지사항</a></li>
			<li><a href="${pageContext.request.contextPath}/board/list">자유게시판</a></li>
			<li><a href="${pageContext.request.contextPath}/gallery/list">갤러리</a></li>
		</ul>
	</nav>
</header>
