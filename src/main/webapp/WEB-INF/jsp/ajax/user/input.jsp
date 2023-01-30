<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.6.3.min.js"
  integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
  crossorigin="anonymous"></script>
</head>
<body>

	<label>이름</label> <input type="text" name="name" id="nameInput"><br>
	<label>생년월일</label> <input type="text" name="yyyymmdd" id="yyyymmddInput"><br>
	<label>이메일</label> <input type="text" name="email" id="emailInput">
	<button type="button" id="duplicateBtn">중복확인</button>
	<br>
	<button type="button" id="saveBtn">저장</button>

	
	<script>
		$(document).ready(function() {
			
			$("#duplicateBtn").on("click", function(){
				let email = $("#emailInput").val();
				
				if(email == ""){
					alert("이메일을 입력하세요");
					return;
				}
				//유효성 검사
				//중복체크를 했는지
					if(!isChecked){
						alert("중복여부를 체크해주세요");
						return;
					}
				//중복 되었는지
				$.ajax({
					type:"get"
					,url:"/ajax/user/is_duplicate"
					,data:{"email":email}
					,success:function(data){
						if(data.is_duplicate){
							//중복상태
							alert("중복되었습니다")
						}else{
							//중복되지 않은 상태
							alert("사용 가능합니다")
						}
					}
					, error:function(){
						alert("중복확인 에러")
					}
				});
			});
			
			$("#saveBtn").on("click", function() {
				let name = $("#nameInput").val();
				let yyyymmdd = $("#yyyymmddInput").val();
				let email = $("#emailInput").val();
				
				if(name ==""){
					alert("이름을 입력하세요");
					return;
				}
				if(yyyymmdd ==""){
					alert("생년월일 입력하세요");
					return;
				}
				if(email ==""){
					alert("이메일을 입력하세요");
					return;
				}
				
				$.ajax({
					// request 옵션
					type:"get"
					, url:"/ajax/user/add"
					, data:{"name":name, "yyyymmdd":yyyymmdd, "email":email}
				
					//response 옵션
					, success:function(data) {
						//{"result": "success"} {"result": "fail"}
						if(data.result == "success") {
							// 리스트 페이지로 이동
							location.href="/ajax/user/list";
						} else {
							alert("저장실패");
						}
					}
					, error:function() {
						alert("저장 에러!");
					}
				});
				
				
			}); 			
			
			/*
			$("#saveForm").on("submit", function() {
				let name = $("#nameInput").val();
				
				if(name == "") {
					alert("이름을 입력하세요");
					return false;
				}
				
			});
			
			*/
			
		});
	</script>

</body>
</html>