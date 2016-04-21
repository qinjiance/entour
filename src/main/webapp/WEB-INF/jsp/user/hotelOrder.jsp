<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 酒店订单</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/hb.css" />
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
					<c:set var="left" value="2" />
					<%@include file="./common/left.jspf"%>
				</div>
				<div data-options="region:'center',border:false,noheader:true" 
					style="padding:0 0 0 20px;">
					<div class="easyui-panel" data-options="border:true,noheader:true">
						<div class="rightD">
							<div id="msg" class="noposition">${sysMsg}</div>
							<p class="title">我的酒店订单</p>
							<div class="content">
								<p class="header">
									<span>订单号</span>
									<span class="time">时间</span>
									<span class="hotel">酒店</span>
									<span>预付</span>
									<span>状态</span>
								</p>
								<c:forEach items="${holtels}" var="bh">
									<p class="item">
										<span>${bh.id}</span>
										<span class="time"><fmt:formatDate value="${bh.checkIn}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${bh.checkOut}" pattern="yyyy-MM-dd" /></span>
										<span class="hotel"><a href="${ctx}/hotelOrderDet?orderId=${bh.id}">${bh.hotelName} - ${bh.roomType} (${bh.roomNum}间)</a></span>
										<span>￥${bh.payPrice}</span>
										<span>
											<c:choose>
												<c:when test="${bh.payStatus==0}">未付款</c:when>
												<c:when test="${bh.payStatus==1}">
													<c:choose>
														<c:when test="${(bh.chargeStatus==0)||(bh.chargeStatus==2)}">订单处理中</c:when>
														<c:when test="${bh.chargeStatus==1}">预定成功</c:when>
														<c:otherwise>预定失败，请联系客服</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>付款失败</c:otherwise>
											</c:choose>
										</span>
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

