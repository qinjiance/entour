<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 修改密码</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/chPwd.css" />
</head>
<body>
	<!-- header -->
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody" style="padding: 20px 0;">
			<div class="easyui-layout" style="width: 100%; height: 400px;">
				<div data-options="region:'west',border:true,split:false,title:'用户中心'"
					style="width: 200px; padding: 10px 5px;">
					<!-- left -->
					<c:set var="left" value="1" />
					<%@include file="./common/left.jspf"%>
				</div>
				<div data-options="region:'center',collapsible:false,border:false,noheader:true,title:'修改密码'" 
					style="padding:10px 60px 20px 60px;">
					<div class="easyui-panel" data-options="border:true,noheader:true" style="width:500px;">
						<div style="padding:10px 60px 20px 60px;">
							<form class="inputForm" action="${ctx}/user/modifyPwd" method="post">
						    	<div id="msg" class="noposition">${sysMsg}</div>
						    	<table cellpadding="5"">
						    		<tr>
						    			<td>原密码:</td>
						    			<td><input class="easyui-validatebox textbox" type="password" name="password" 
						    				required="required" validType="length[6,20]"  /></td>
						    		</tr>
						    		<tr>
						    			<td>新密码:</td>
						    			<td><input class="easyui-validatebox textbox" type="password" name="newPassword" 
						    				required="required" validType="length[6,20]" /></td>
						    		</tr>
						    		<tr>
						    			<td>重复密码:</td>
						    			<td><input class="easyui-validatebox textbox" type="password" name="rePassword" 
						    				required="required" validType="length[6,20]" /></td>
						    		</tr>
						    		
						    		<tr>
						    			<td colspan="2">
											<div class="chPwd_li chPwd_li2">
									        	<div class="chPwd_mx">
									            	<div id="cao" class="chPwd_line chPwd_qd1"></div>
									            	<div class="chPwd_font">
									                    <span class="chPwd_c1">弱</span>
									                    <span class="chPwd_c2">中</span>
									                    <span class="chPwd_c3">强</span>
									                </div>
									            </div>
									        </div>
							        	</td>
							        </tr>
						    	</table>
						    </form>
						    <div style="text-align:center;padding:5px">
						    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交修改</a>
						    </div>
					    </div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<%@include file="../common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="../common/scripts.jspf"%>
	<script type="text/javascript">
		function submitForm(){
			submitMsg("");
			if($('.inputForm').form('validate')){
				if($('input[name=newPassword]').val()!=$('input[name=rePassword]').val()){
					submitMsg("两次密码输入不一致");
					return false;
				}
				$('.inputForm').submit();
			}
		}
		function submitMsg(msg){
	        $('#msg').html(msg);
		}
		$(document).ready(function(){
			$(document).on("keydown", function(event) {
				var key = event.which;
				if (key == 13) {
					event.preventDefault();
					submitForm();
				}
			});
			$('input[name=rePassword]').blur(function(){
					if(this.value!=$('input[name=newPassword]').val()){
						submitMsg("两次密码输入不一致");
					}else{
						submitMsg("");
					}
			});
			$('input[name=newPassword]').keyup(function(){
				var i = getPasswordStrength(this.value);
				if(i == 2){
					$('#cao').removeClass().addClass("chPwd_line chPwd_qd2");
				}else if(i > 2){
					$('#cao').removeClass().addClass("chPwd_line chPwd_qd3");
				}else{
					$('#cao').removeClass().addClass("chPwd_line chPwd_qd1");
				}
			});
		});
	</script>
</body>
</html>

