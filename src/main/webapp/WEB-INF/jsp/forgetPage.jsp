<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="./common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title"/> - 忘记密码</title>
<!-- meta -->
<%@include file="./common/meta.jspf"%>
<!-- css links -->
<%@include file="./common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/forget.css" />
</head>
<body>
	<!-- header -->
	<%@include file="./common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody">
			<div id="forget" class="left">
				<div class="forget_from">
					<h2 class="forget_title">忘记密码</h2>
					<div class="forget_li"><label class="forget_nm">　　邮箱</label><input type="text" name="email" value="" class="forget_input pub_abso" /><label class="forget_ps"><span>请输入邮箱</span></label></div>
					
					<div class="forget_li"><label class="forget_nm">　验证码</label><input type="text" name="captcha" value="" class="forget_input forget_width pub_abso" />　<input type="button" value="邮件验证码" class="forget_yzm pub_abso" /><label class="forget_ps"><span>请输入验证码</span></label></div>
					
					<div class="forget_li"><label class="forget_nm">　新密码</label><input type="password" name="newPassword" class="forget_input pub_abso" /><label class="forget_ps"><span>请输入新密码</span></label> </div>
   
   					<div class="forget_li"><label class="forget_nm">确认密码</label><input type="password" name="rePassword" class="forget_input pub_abso" /><label class="forget_ps"><span class="forget_err"></span></label></div>
				
					<div class="forget_li forget_li2">
			        	<div class="forget_mx">
			            	<div id="cao" class="forget_line forget_qd1"></div>
			            	<div class="forget_font">
			                    <span class="forget_c1">弱</span>
			                    <span class="forget_c2">中</span>
			                    <span class="forget_c3">强</span>
			                </div>
			            </div>
			        </div>
			     </div>
				<div id="msg"></div>
				<div class="forget_btn">
					<a href="javascript:void(0)" class="forget_submit">修改密码</a>
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
					$('.forget_submit').click();
				}
			});
			$('input[name=email]').blur(function(){
				var email = this.value;
				if (!isEmail(email)){
					showMsg('input[name=email]',"邮箱不符合规则");
					return false;
				}
                submitMsg(''); 
				$.getJSON("${ctx}/checkEmail",{email:email},function(r){
					if(r.code=="2"){
						showMsg('input[name=email]',"");
					}else{
						showMsg('input[name=email]', 
								r.message + '<a href="${ctx}/regPage?email=' 
										+ email + '">注册?</a>');
					}
				})
			})
			$('.forget_yzm.pub_abso').click(function(){
				var email = $('input[name=email]').val();
				var d = new Date();
		        d.setSeconds(d.getSeconds() + 60);
				$(this).fadeTo(100,0.5);
                submitMsg(''); 
				$.getJSON("${ctx}/sendForgetCaptcha",{email:email},function(ret){
		            if(ret.code=="0"){
		            	$('.forget_yzm.pub_abso').tinyTimer({
		                     to: d,
		                     format:'成功，0%s秒重发',
		                     onEnd:function(val){
			            		$('.forget_yzm.pub_abso').fadeTo(10,1).val("重获验证码");
		                     }
		                 });
		            }else{
		            	$('.forget_yzm.pub_abso').fadeTo(10,1).val("重获验证码");
		                submitMsg(ret.message); 
		            }   
		        })  
			})
			$('input[name=newPassword]').keyup(function(){
				var i = getPasswordStrength(this.value);
				if(i == 2){
					$('#cao').removeClass().addClass("forget_line forget_qd2");
				}else if(i > 2){
					$('#cao').removeClass().addClass("forget_line forget_qd3");
				}else{
					$('#cao').removeClass().addClass("forget_line forget_qd1");
				}
			});
			$('input[name=captcha]').blur(function(){
				if (!isPwd(this.value)){
					showMsg('input[name=captcha]',"请输入验证码");
				}else{
					showMsg('input[name=captcha]',"");
				}
			});
			$('input[name=newPassword]').blur(function(){
					if (!isPwd(this.value)){
						showMsg('input[name=newPassword]',"6～20位密码");
					}else{
						showMsg('input[name=newPassword]',"");
					}
			});
			$('input[name=rePassword]').blur(function(){
					if(this.value!=$('input[name=newPassword]').val()){
						showMsg('input[name=rePassword]',"两次密码输入不一致");
					}else{
						showMsg('input[name=rePassword]',"");
					}
			});

			$('.forget_submit').click(function(){
				if($('input[name=newPassword]').val()!=$('input[name=rePassword]').val()){
					submitMsg("两次密码输入不一致");
					return false
				}
                submitMsg(''); 
				$.post("${ctx}/chForgetPwd",{email:$('input[name=email]').val(),
						 newPassword:$('input[name=newPassword]').val(),
						 rePassword:$('input[name=rePassword]').val(),
						 captcha:$('input[name=captcha]').val(),
						},function(ret){
					if(ret.code==0){
						var email = $('input[name=email]').val();
						submitMsg('新密码修改成功，3秒后跳转至' 
								+ '<a href="${ctx}/loginPage?email=' 
								+ email + '">登录</a>页面...');
						setTimeout(function(){
							location.href = '${ctx}/loginPage?email=' + email;
						},3000);
					}else if(ret.code==-1){
						showMsg('input[name=email]',ret.result.email);
						showMsg('input[name=newPassword]',ret.result.newPassword);
						showMsg('input[name=rePassword]',ret.result.rePassword);
						showMsg('input[name=captcha]',ret.result.captcha);
					}else{
						submitMsg(ret.message);
					}
				})
			})
		});

		function showMsg(input,errorMsg){//error success
			var tip = $(input).siblings("label[class=forget_ps]").children();
			tip.removeClass("forget_ok forget_err").html("").show();
			if(!errorMsg || errorMsg.length==0){
				tip.addClass("forget_ok");
			}else{
				tip.addClass("forget_err").html(errorMsg).show();
			}
		}
		function submitMsg(msg){
	        $('#msg').html(msg);
		}
	</script>
</body>
</html>

