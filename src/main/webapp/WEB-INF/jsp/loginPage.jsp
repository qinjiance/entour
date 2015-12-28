<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="./common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 登录</title>
<!-- meta -->
<%@include file="./common/meta.jspf"%>
<!-- css links -->
<%@include file="./common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/login.css" />
</head>
<body>
	<!-- header -->
	<%@include file="./common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody">
			<div id="loginLogo" class="left">
				<img class="left" 
					src="${ctx}<fmt:message key="static.resources.host"/>/img/login_logo_01.png" />
			</div>
			<div id="login" class="left">
				<div class="login_from">
					<h2 class="login_title">登录</h2>
					<div class="login_li"><label class="login_nm">　　邮箱</label><input type="text" name="email" value="${email}" class="login_input pub_abso" /></div>
					
					<div class="login_li"><label class="login_nm">登录密码</label><input type="password" name="password" class="login_input pub_abso" /></div>
					
					<div class="login_li"><label class="login_nm">　记住我</label><input type="checkbox" name="autoLogin" class="pub_abso" /><a class="login_forget" href="${ctx}/forgetPage">忘记密码?</a></div>
				</div>
				<div id="msg"></div>
				<div class="login_btn">
					<a href="javascript:void(0)" class="login_submit">登录</a>
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<%@include file="./common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="./common/scripts.jspf"%>
	
	<script type="text/javascript">
		$(document).ready(function(){ 
			$(document).on("keydown", function(event) {
				var key = event.which;
				if (key == 13) {
					event.preventDefault();
					$('.login_submit').click();
				}
			});

			$('.login_submit').click(function(){
				if(!$.trim($('input[name=email]').val())||!$.trim($('input[name=password]').val())){
					submitMsg("请输入用户名和密码");
					return false
				}
                submitMsg(''); 
				$.post("${ctx}/login",{email:$('input[name=email]').val(),
						 password:$('input[name=password]').val(),
						 autoLogin:$('input[name=autoLogin]')[0].checked?'1':'0',
				},function(ret){
					if(ret.code==0){
						location.href="${ctx}/"
					}else{
						submitMsg(ret.message);
					}
				})
			})
		});

		function submitMsg(msg){
	        $('#msg').html(msg);
		}
	</script>
</body>
</html>

