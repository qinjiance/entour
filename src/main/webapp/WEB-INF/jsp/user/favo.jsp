<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 收藏夹</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/user.css" />
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
					<c:set var="left" value="4" />
					<%@include file="./common/left.jspf"%>
				</div>
				<div data-options="region:'center',border:false,noheader:true" 
					style="padding:0 0 0 20px;">
					<div class="easyui-panel" data-options="border:true,noheader:true">
						<div class="rightD">
							<div id="msg" class="noposition">${sysMsg}</div>
							<p class="title">我的收藏</p>
							<div class="content">
								<p class="header">
									<span class="name">路线</span>
									<span class="time">收藏时间</span>
									<span class="de">删除</span>
								</p>
								<c:forEach items="${favoriteRoutes}" var="favo">
									<p class="item">
										<span class="name"><a href="${ctx}/route?areaId=${favo.areaId}&routeId=${favo.routeId}">${favo.routeName}</a></span>
										<span class="time"><fmt:formatDate value="${favo.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
										<span class="de"><a href="${ctx}/user/favo/rm?favoId=${favo.id}" onclick="return window.confirm('确定删除？');">删除</a></span>
									</p>
								</c:forEach>
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
		$(document).ready(function(){

		});
	</script>
</body>
</html>
