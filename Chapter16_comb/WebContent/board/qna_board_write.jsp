<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#registForm {
	width: 500px;
	height: 610px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

.td_left {
	width: 150px;
	background: orange;
}

.td_right {
	width: 300px;
	background: skyblue;
}

#commandCell {
	text-align: center;
}

body, div, ul, li {
	margin: 0;
	padding: 0;
}

body {
	front-size: 12px;
	font-family: "맑은 고딕", arial
}

ul {
	list-style: none;
}

a {
	color: #000;
	text-decoration: none
}

.gnb {
	background-color: #0099ff;
	/*  width: 720px;*/
	height: 36px;
	margin: auto;
	margin-top: 0;
	display: flex;
	justify-content: center;
}

gnb>ul {
	text-align: center;
	height: 36px;
	background: #0099ff;
	padding: 0;
}

.gnb>ul>li {
	display: inline-block;
	width: 102px;
	height: 36px;
	margin-top: 0;
	position: relative;
}

.gnb>ul>li>a {
	display: block;
	width: 100%;
	height: 100%;
	font: bold 13px/30px "맑은 고딕", arial;
	text-align: center;
	color: #fff;
	background: #0099ff;
}

.gnb ul li a:hover {
	color: #000;
	background: #0099ff;
}

.gnb ul ul {
	display: none;
}

.gnb ul li:hover ul {
	display: block;
}

.gnb li li {
	width: 100px;
	height: 36px;
	background-color: #47a9f4;
	text-align: center;
	float: left;
}

.gnb li li a {
	display: block;
	width: 100%;
	height: 100%;
	font: bold 12px/25px "맑은 고딕", arial;
	margin-top: 5px;
}

.gnb li li a:hover {
	color: white;
	background: none;
}
</style>
</head>
<body>
	<div class="gnb">
		<ul>
			<li><a href="#">쇼핑</a>
				<ul>
					<li><a href="/Chapter16_comb/dogList.dog">상품 목록 보기</a></li>
					<li><a href="/Chapter16_comb/dogCartList.dog">장바구니 보기</a></li>
					<li><a href="/Chapter16_comb/dogRegistForm.dog">상품등록하기</a></li>
					<li><a href="#">서브메뉴4</a></li>
				</ul></li>
			<li><a href="#">회원관리</a>
				<ul>
					<li><a href="/Chapter16_comb/memberJoin.me">회원 가입</a></li>
					<li><a href="/Chapter16_comb/memberListAction.me">회원목록보기</a></li>
					<li><a href="#">서브메뉴3</a></li>
				</ul></li>
			<li><a href="#">게시판</a>
				<ul>
					<li><a
						href="${pageContext.request.contextPath }/boardWriteForm.bo">게시판
							글쓰기</a></li>
					<li><a href="${pageContext.request.contextPath }/boardList.bo">게시판
							목록 보기</a></li>
					<li><a href="#">서브메뉴3</a></li>
					<li><a href="#">서브메뉴4</a></li>
				</ul></li>
			<li><a href="#">주메뉴 5</a>
				<ul>
					<li><a href="#">서브메뉴1</a></li>
					<li><a href="#">서브메뉴2</a></li>
					<li><a href="#">서브메뉴3</a></li>
					<li><a href="#">서브메뉴4</a></li>
				</ul></li>
			<li><c:choose>
					<c:when test="${id eq null }">
						<a href="${pageContext.request.contextPath }/memberLogin.me">로그인</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath }/memberLogout.me">로그아웃</a>
					</c:otherwise>
				</c:choose>
		</ul>
	</div>
	<!-- 게시판 등록 -->
	<section id="writeForm">
		<h2>게시판글등록</h2>
		<form action="boardWritePro.bo" method="post"
			enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<td class="td_left"><label for="board_name">글쓴이</label></td>
					<td class="td_right"><input type="text" name="board_name"
						id="board_name" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_pass">비밀번호</label></td>
					<td class="td_right"><input name="board_pass" type="password"
						id="board_pass" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_subject">제목</label></td>
					<td class="td_right"><input name="board_subject" type="text"
						id="board_subject" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_content">내용</label></td>
					<td><textarea id="board_content" name="board_content"
							cols="40" rows="15" required="required"></textarea></td>
				</tr>
				<tr>
					<td class="td_left"><label for="board_file">파일 첨부</label></td>
					<td class="td_right"><input name="board_file" type="file"
						id="board_file" required="required" /></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기" />
			</section>
		</form>
	</section>
	<!-- 게시판 등록 -->
</body>
</html>