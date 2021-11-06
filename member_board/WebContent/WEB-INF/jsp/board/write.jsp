<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp" />
<style>
.detail table {
	width: 80%;
	margin: 40px auto;
	border-collapse: collapse;
}

.detail td {
	padding: 8px;
	border-top: 3px solid #bbb;
	border-bottom: 3px solid #bbb;
}

.detail textarea {
	width: 100%;
	height: 300px
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<main class="detail">
	<form method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td><input type="file" name="file1"></td>
			</tr>
			<tr>
				<td><input type="file" name="file2"></td>
			</tr>
			<tr>
				<td><input type="file" name="file3"></td>
			</tr>
			<tr>
				<td><textarea name="content"></textarea></td>
			</tr>
			<tr>
				<td><button>작성</button></td>
			</tr>
		</table>
	</form>
	</main>
	<jsp:include page="../common/footer.jsp" />
	<c:out value=""></c:out>
</body>
</html>