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

	<h3>Ajax 테스트</h3>
	
	<div id="text"></div>
	
	<script>
		$.ajax({
			type:"get"
			, url:"/ajax/request"
			//, data:{}
			, success: function(data) {
				alert(data.name);
				$("#text").text(data.age);
			}
			, error:function() {
				
			}
		});
	
	
	
	</script>

</body>
</html>