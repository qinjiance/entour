<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 账号信息</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
</head>
<body>
	<!-- header -->
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody" style="padding: 20px 0;">
			<div class="easyui-layout" style="width: 100%; height: 400px;">
				<div data-options="region:'west',collapsible:false,border:true,split:false,title:'用户中心'"
					style="width: 200px; padding: 10px 5px;">
					<!-- left -->
					<c:set var="left" value="0" />
					<%@include file="./common/left.jspf"%>
				</div>
				<div data-options="region:'center',border:false,noheader:true,title:'账号信息'" 
					style="padding:10px 60px 20px 60px;">
					<div class="easyui-panel" data-options="border:true,noheader:true" style="width:500px;">
						<div style="padding:10px 60px 20px 60px;">
							<form class="inputForm" action="${ctx}/user/editInfo" method="post">
						    	<div id="msg" class="noposition">${sysMsg}</div>
						    	<table cellpadding="5"">
						    		<tr>
						    			<td>邮箱:</td>
						    			<td>${user.email}</td>
						    		</tr>
						    		<tr>
						    			<td>昵称:</td>
						    			<td><input class="easyui-validatebox textbox" type="text" name="nickname" 
						    				value="${user.nickname}" required="required" validType="length[1,15]" /></td>
						    		</tr>
						    		<tr>
						    			<td>手机号:</td>
						    			<td><input class="easyui-validatebox textbox" type="text" name="mobile" 
						    				value="${user.mobile}" required="required" validType="length[1,15]" /></td>
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
			if($('.inputForm').form('validate')){
				$('.inputForm').submit();
			}
		}
		$(document).ready(function(){
			$(document).on("keydown", function(event) {
				var key = event.which;
				if (key == 13) {
					event.preventDefault();
					submitForm();
				}
			});
		});
	</script>
</body>
</html>

