<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="./common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title"/> - 注册</title>
<!-- meta -->
<%@include file="./common/meta.jspf"%>
<!-- css links -->
<%@include file="./common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/reg.css" />
</head>
<body>
	<!-- header -->
	<%@include file="./common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody">
			<div id="regLogo" class="left">
				<img class="left" 
					src="${ctx}<fmt:message key="static.resources.host"/>/img/reg_logo_01.png" />
			</div>
			<div id="reg" class="left">
				<div class="reg_from">
					<h2 class="reg_title">注册</h2>
					<div class="reg_li"><label class="reg_nm">　　邮箱</label><input type="text" name="email" value="${email}" class="reg_input pub_abso" /><label class="reg_ps"><span>请输入邮箱</span></label></div>
					
					<div class="reg_li"><label class="reg_nm">　手机号</label><input type="text" name="mobile" value="" class="reg_input pub_abso" /><label class="reg_ps"><span>请输入手机号</span></label></div>

					<div class="reg_li"><label class="reg_nm">　验证码</label><input type="text" name="captcha" value="" class="reg_input reg_width pub_abso" />　<input type="button" value="邮件验证码" class="reg_yzm pub_abso" /><label class="reg_ps"><span>请输入验证码</span></label></div>
					
					<div class="reg_li"><label class="reg_nm">登录密码</label><input type="password" name="password" class="reg_input pub_abso" /><label class="reg_ps"><span>请输入密码</span></label> </div>

					<div class="reg_li"><label class="reg_nm">确认密码</label><input type="password" name="rePassword" class="reg_input pub_abso" /><label class="reg_ps"><span class="reg_err"></span></label></div>

					<div class="reg_li reg_li2">
			        	<div class="reg_mx">
			            	<div id="cao" class="reg_line reg_qd1"></div>
			            	<div class="reg_font">
			                    <span class="reg_c1">弱</span>
			                    <span class="reg_c2">中</span>
			                    <span class="reg_c3">强</span>
			                </div>
			            </div>
			        </div>
					<div style="padding: 20px 130px;">
						<a href="javascript:void(0)"
							target="_blank">《<fmt:message key="webapp.title"/>用户协议》</a>
					</div>
				</div>
				<div id="msg"></div>
				<div class="reg_btn">
					<a href="javascript:void(0)" class="reg_submit">接受协议并注册</a>
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
					$('.reg_submit').click();
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
					if(r.code=="0"){
						showMsg('input[name=email]',"");
					}else{
						showMsg('input[name=email]', 
								r.message + '<a href="${ctx}/loginPage?email=' 
										+ email + '">登录?</a>');
					}
				})
			})
			$('input[name=mobile]').blur(function(){
				if (!isMobile(this.value)){
					showMsg('input[name=mobile]',"手机号不符合规则");
					return false;
				}
                submitMsg(''); 
				showMsg('input[name=mobile]',"");
			})
			
			$('.reg_yzm.pub_abso').click(function(){
				var email = $('input[name=email]').val();
				var d = new Date();
		        d.setSeconds(d.getSeconds() + 60);
				$(this).fadeTo(100,0.5);
                submitMsg(''); 
				$.getJSON("${ctx}/sendRegCaptcha",{email:email},function(ret){
		            if(ret.code=="0"){
		            	$('.reg_yzm.pub_abso').tinyTimer({
		                     to: d,
		                     format:'成功，0%s秒重发',
		                     onEnd:function(val){
			            		$('.reg_yzm.pub_abso').fadeTo(10,1).val("重获验证码");
		                     }
		                 });
		            }else{
		            	$('.reg_yzm.pub_abso').fadeTo(10,1).val("重获验证码");
		                submitMsg(ret.message); 
		            }   
		        })  
			})
			$('input[name=password]').keyup(function(){
				var i = getPasswordStrength(this.value);
				if(i == 2){
					$('#cao').removeClass().addClass("reg_line reg_qd2");
				}else if(i > 2){
					$('#cao').removeClass().addClass("reg_line reg_qd3");
				}else{
					$('#cao').removeClass().addClass("reg_line reg_qd1");
				}
			});
			$('input[name=captcha]').blur(function(){
				if (!isPwd(this.value)){
					showMsg('input[name=captcha]',"请输入验证码");
				}else{
					showMsg('input[name=captcha]',"");
				}
			});
			$('input[name=password]').blur(function(){
					if (!isPwd(this.value)){
						showMsg('input[name=password]',"6～20位密码");
					}else{
						showMsg('input[name=password]',"");
					}
			});
			$('input[name=rePassword]').blur(function(){
					if(this.value!=$('input[name=password]').val()){
						showMsg('input[name=rePassword]',"两次密码输入不一致");
					}else{
						showMsg('input[name=rePassword]',"");
					}
			});

			$('.reg_submit').click(function(){
				if($('input[name=password]').val()!=$('input[name=rePassword]').val()){
					submitMsg("两次密码输入不一致");
					return false
				}
                submitMsg(''); 
				$.post("${ctx}/reg",{email:$('input[name=email]').val(),
						 mobile:$('input[name=mobile]').val(),
						 password:$('input[name=password]').val(),
						 rePassword:$('input[name=rePassword]').val(),
						 captcha:$('input[name=captcha]').val(),
						},function(ret){
					if(ret.code==0){
						location.href="${ctx}/"
					}else if(ret.code==-1){
						showMsg('input[name=email]',ret.result.email);
						showMsg('input[name=mobile]',ret.result.mobile);
						showMsg('input[name=password]',ret.result.password);
						showMsg('input[name=rePassword]',ret.result.rePassword);
						showMsg('input[name=captcha]',ret.result.captcha);
					}else{
						submitMsg(ret.message);
					}
				})
			})
		});

		function showMsg(input,errorMsg){//error success
			var tip = $(input).siblings("label[class=reg_ps]").children();
			tip.removeClass("reg_ok reg_err").html("").show();
			if(!errorMsg || errorMsg.length==0){
				tip.addClass("reg_ok");
			}else{
				tip.addClass("reg_err").html(errorMsg).show();
			}
		}
		function submitMsg(msg){
	        $('#msg').html(msg);
		}
	</script>
</body>
</html>

