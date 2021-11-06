<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 보내기</title>
</head>

<body>
	<form action="mailSend" method="post">
		<h1>메일 보내기</h1>
		<table>
			<tr>
				<td>보내는 사람 메일 :</td>
				<td><input type="text" name="sender"></td>
			</tr>
			<tr>
				<td>받는사람 메일 :</td>
				<td><input type="text" name="receiver"></td>
			</tr>
			<tr>
				<td>제목 :</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>내용:</td>
				<td><textarea name="content" cols=40 raws=20></textarea></td>
			</tr>
			<!-- 보낸 시간 받는시간 생각할것   -->
			<tr>
				<td align="center" colspan="2"><input type="submit" value="보내기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>