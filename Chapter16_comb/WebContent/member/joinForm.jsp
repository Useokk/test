<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	margin: auto;
	margin-top: 10px;
	width: 700px;
	height: 400px;
	border: 1px solid gray;
	text-align: center;
}

.td_title {
	font-weight: bold;
	font-size: x-large;
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
	<form name="joinform" action="joinProcess.jsp" method="post">
		<table border=1>
			<tr>
				<td colspan="2" class="td_title">회원 가입 페이지</td>
			</tr>
			<tr>
				<td><label for="id">아이디 : </label></td>
				<td align="left"><input type="text" name="id" id="id" /> <input
					type="button" name="idCheck" id="idCheck" value="아이디 중복확인"
					onclick="window.open('idCheck.jsp?openInit=true','','width=300, height=200')" /></td>

			</tr>
			<tr>
				<td><label for="pass">비밀번호 : </label></td>
				<td align="left"><input type="password" name="pass" id="pass" /></td>
			</tr>
			<tr>
				<td><label for="pass">비밀번호 확인: </label></td>
				<td align="left"><input type="password" name="passCheck"
					id="passCheck" /></td>
			</tr>
			<tr>
				<td><label for="name">이름 : </label></td>
				<td align="left"><input type="text" name="name" id="name" /></td>
			</tr>
			<tr>
				<td><label for="age">나이 : </label></td>
				<td align="left"><input type="text" name="age" id="age" /></td>
			</tr>
			<tr>
				<td><label for="gender1"></label>성별 :</td>
				<td align="left"><input type="radio" name="gender" value="남"
					checked id="gender1" />남자 <input type="radio" name="gender"
					value="여" id="gender2" />여자</td>
			</tr>
			<tr>
				<td><label for="email">이메일 주소 : </label></td>
				<td align="left"><input type="text" name="email" id="email" /></td>
			</tr>
			<tr>
				<td><label for="zip">우편번호 : </label></td>
				<td align="left"><input type="text" name="zip" id="zip" /> <input
					type="button" name="search" id="search" value="검색"
					onclick="window.open('zip.jsp','','width=800,height=600')" /></td>

			</tr>
			<tr>
				<td><label for="address">주소 : </label></td>
				<td align="left"><input type="text" name="address" id="address" /></td>
			</tr>
			<tr>
				<td><label for="address2">주소상세 : </label></td>
				<td align="left"><input type="text" name="address2"
					id="address2" /></td>
			</tr>
			<tr>
				<td colspan="2"><a
					href="javascript:formCheck(document.joinform);">회원 가입</a>&nbsp;&nbsp;
					<a href="javascript:joinform.reset()">다시작성</a></td>
			</tr>
		</table>
	</form>
	<script>
		var chkId = false;
		var idcheck;

		function formCheck(f) {
			if (!chkId || idcheck != f.id.value.trim()) {
				alert("아이디 중복확인을 하세요!!!!");
				return false;
			}
			if (f.id.value.trim() == "") {
				alert("아이디를 입력하시오");
				f.id.value = "";
				f.id.focus();
				return false;
			}
			if (f.pass.value.trim() == "") {
				alert("비밀번호를 입력하시오");
				f.pass.value = "";
				f.pass.focus();
				return false;
			}
			if (f.pass.value.trim() != f.passCheck.value.trim()) {
				alert("비밀번호가 일치하지 않습니다.");
				f.pass.value = "";
				f.passCheck.value = "";
				f.pass.focus();
				return false;
			}
			if (f.name.value.trim() == "") {
				alert("이름을 입력하시오");
				f.name.value = "";
				f.name.focus();
				return false;
			}
			if (f.age.value.trim() == "") {
				alert("나이를 입력하시오");
				f.age.value = "";
				f.age.focus();
				return false;
			}
			if (f.email.value.trim() == "") {
				alert("이메일을 입력하시오");
				f.email.value = "";
				f.email.focus();
				return false;
			}
			if (isNaN(f.age.value)) {
				alert("숫자만 입력하세요.");
				f.age.value = "";
				f.age.focus();
				return false;
			}

			f.submit();
		}
	</script>
</body>
</html>