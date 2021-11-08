<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp" />
</head>

<body>
	<jsp:include page="../common/header.jsp" />
	<main class="login">
	<form method="post">
		<fieldset>
			<legend>로그인</legend>
			<h4>
				<label for="id">아이디</label>
			</h4>
			<input type="text" name="id" id="id">
			<h4>
				<label for="pwd">비밀번호</label>
			</h4>
			<input type="password" name="pwd" id="pwd">
			<p>
				<label><input type="checkbox" name="savedId" id="savedId">
					아이디 저장</label>
			</p>
			<p>
				<button class="btn btn-outline-warning mt-1">로그인</button>
			</p>
		</fieldset>
	</form>
	<h3>${param.msg}</h3>
	</main>
	<jsp:include page="../common/footer.jsp" />
	<script>
		$(function() {
			if ($.cookie("savedId")) {
				$("#id").val($.cookie("savedId"));
				$("#savedId").prop("checked", true);
			}
		});
// 		function XSSCheck(str, level) {
// 			if (level == undefined || level == 0) {
// 				str = str.replace(/\<|\>|\"|\'|\%|\;|\(|\)|\&|\+|\-/g, "");
// 			} else if (level != undefined && level == 1) {
// 				str = str.replace(/\</g, "&lt;");
// 				str = str.replace(/\>/g, "&gt;");
// 			}
// 			return str;
// 		}
	</script>
</body>
</html>
