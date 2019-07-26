<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%pageContext.setAttribute("path", request.getContextPath()); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
<form id="f" action="">
	<body bgcolor="#F3F3F3">
		<br>
        <img alt="logo" src="${pageContext.request.contextPath}/static/image/1.png" style="width:250px;height:150px;margin-left:765px;margin-top:0px" float="left"> 
		<div style="background:#FFFFFF;width:500px;height:570px;position:absolute;left:630px;top:160px">
			<form action="./login" method="post">
				<br>
				<h3 style="color:#ff0000;font-size:35px"><center>用户登录</center></h3>
				<center><img alt="用户名" src="${pageContext.request.contextPath}/static/image/username.jpg" style="vertical-align:bottom" /><input type="text" name="username" placeholder="用户名/邮箱/手机号" size="50px" style="height:50px;vertical-align:bottom" /></center>
				<br>
				<center><img alt="密码" src="${pageContext.request.contextPath}/static/image/password.jpg" style="vertical-align:bottom" /><input type="password" name="password" placeholder="密码" size="50px" style="height:50px;vertical-align:bottom" /></center><br><br>
			     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			     <label><input type="checkbox" name="noExpire" class="pc" value="1">
								<!-- react-text: 19 -->下次自动登录<!-- /react-text --></label>
								<a href="zhaohuimima.html" style="font-size:15px;color:#ffffmd;
					float:right;position:absolute;right:80px;top:302px;border-radius:12px">忘记密码了?</a>
								<br> <br> <br> 
				<center>
					<input type="button" value="登录" onclick="location.href='huanyingjiemian2.html'" 
						style="width:400px;height:40px;background:#0000ff;color:#ffffff;border:none;position:absolute;left:50px;top:380px;border-radius:12px" />
						<br>
							<input type="button" value="微信登录" onclick="location.href='huanyingjiemian2.html'" 
						style="width:400px;height:40px;background:#ffffcc;color:#000000;border:none;position:absolute;left:50px;top:450px;border-radius:12px" />
						
					 </center>
				<br><br><br><br>
				<p style="position:absolute;left:150px;top:490px;">还没有伙伴办公账号？</p>
				<a href="zhaohuimima.html" style="font-size:15px;color:#ffffmd;
					float:right;position:absolute;right:125px;top:508px;border-radius:12px">立即注册</a>
				
			</form>
		</div>
		<a href="https://www.huoban.com" target="_blank" style="position:absolute;left:800px;top:800px">由伙伴办公提供技术支持</a>
	</body>
</form>
<span id="sp"></span>
</body>
<script type="text/javascript" src="${path }/static/js/jquery-1.10.1.js"></script>
<script type="text/javascript">
$(function(){
	$("#btn").click(function(){
		$.ajax({
			url : "${path}/sysUserAction/login",
			type : "post",
			data : $("#f").serialize(),
			dataType : "json",
			success : function(msg){
				if(1 == msg.code){
					location.href = "${path}/customerAction/toList";
				}else{
					$("#sp").html(msg.msg);
				}
			}
		});
	});
});
</script>
</html>