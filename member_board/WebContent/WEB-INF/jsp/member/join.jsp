<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp" />
<style>
.msg {
	height: 20px;
}
</style>
</head>

<body>
	<jsp:include page="../common/header.jsp" />
	<main class="login p-5">
	<form method="post" class="form-group">
		<fieldset>
			<legend>회원가입</legend>
			<label for="id">아이디</label> <input type="text" name="id" id="id"
				class="form-control">
			<p class="msg text-danger"></p>

			<label for="pwd">비밀번호</label> <input type="password" name="pwd"
				id="pwd" class="form-control">
			<p class="msg"></p>

			<label for="pwd">비밀번호 확인</label> <input type="password" id="pwdCk"
				class="form-control">
			<p class="msg"></p>

			<label for="email">이메일</label> <input type="text" name="email"
				id="email" class="form-control">
			<p class="msg"></p>

			<label for="name">이름</label> <input type="text" name="name" id="name"
				class="form-control">
			<p class="msg"></p>

			<button class="btn btn-primary" id="btnJoin">회원가입</button>
		</fieldset>
	</form>
	</main>
	<script>
	$(function() {
		$("#btnJoin").click(function() {
			var id = $("#id").val();
			if(id) {
				$.ajax("idValid?id="+id, {
					success : function(data) {
						if(data/1) { // 가능
							$("#id").next().text("");
						}
						else { // 불가능
							$("#id").next().text("이미 가입된 회원입니다")
						}
					}
				})
			}
			
			//event.preventDefault();
		})
	});
	/* $(function () {
		if(login.value.length <4 || id.value.length >12) {
			alert("아이디는 4~12자 이내로 입력");
			id.select();
			return;
		} 
		if(passWord.value.length <4) {
			alert("비밀번호는 4자 이상입력");
			passWord.select();
			return;
		}
		submit();
	}) */
	
	</script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>