<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 酒店</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/route.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="0" />
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody">
			<c:choose>
				<c:when test="${holtelDet!=null}">
					<div class="easyui-panel" data-options="border:false" style="margin:20px 0;padding:5px;background-color: inherit;">
						<div class="til">
							<p class="label"><span class="hotelName">${holtelDet.hotelName}</span><span class="starLevel"></span></p>
							<p class="cat">${holtelDet.hotelCatalog}</p>
							<p class="brandName">${holtelDet.brandName}</p>
							<p class="address">${holtelDet.address}, ${holtelDet.country}</p>
						</div>
						<div class="powerpoint">
							<div id="myFocus"><!--焦点图盒子-->
							  <div class="loading"></div><!--载入画面(可删除)-->
							  <div class="pic"><!--图片列表-->
							  	<ul>
							  		<c:choose>
							  			<c:when test="${(holtelDet.imgs!=null) && (fn:length(holtelDet.imgs)>0)}">
										  	<c:forEach items="${holtelDet.imgs}" var="img">
									        <li><a href="javascript:void(0)"><img src="${img}" thumb="" alt="img" text="" /></a></li>
											</c:forEach>
							  			</c:when>
							  			<c:otherwise>
							  				<li><a href="javascript:void(0)"><img src="${ctx}<fmt:message key="static.resources.host"/>/img/" thumb="" alt="暂无图片" text="" /></a></li>
							  			</c:otherwise>
							  		</c:choose>
							  	</ul>
							  </div>
							</div>
						</div>
						<div class="abstract">
							<p class="pri"><em class="currency">${holtelDet.currency}</em><span class="display">${holtelDet.minAverPublishPrice}</span><em>起</em></p>
							<p class="lab">酒店介绍：</p>
							<p>${holtelDet.shortDesc}</p>
							<p class="lab">便利设施:</p>
							<p>
								<c:forEach items="${holtelDet.amenities}" var="amen">
									${amen}&nbsp;
								</c:forEach>
							</p>
							<p class="lab">周围交通:</p>
							<c:forEach items="${holtelDet.refPoints}" var="refPoint">
								<p>${refPoint}</p>
							</c:forEach>
						</div> 
					</div>
					<div class="easyui-panel" data-options="border:true,title:'选择房间'" style="margin-bottom:10px;padding:0;">
						<div class="he">
							<div>房型</div>
							<div>选项</div>
							<div>每晚均价</div>
							<div>操作</div>
						</div>
						<div class="rts">
							<c:forEach items="${holtelDet.roomTypes}" var="roomType" varStatus="rtvs">
								<div class="rt">
									<div class="colu">
										<p class="rtname">${roomType.name}(${roomType.roomTypeCategory})</p>
										<p class="rtbed">${roomType.hasBed}张${(roomType.bedType==1)?'单':((roomType.bedType==2)?'双':(roomType.bedType))}人床</p>
										<p class="rtgues">可容纳${roomType.maxGuests}位住客</p>
										<p>(最多${roomType.maxChild}名儿童)</p>
										<p>
											<c:forEach items="${roomType.facilities}" var="fac">
												${fac}&nbsp;
											</c:forEach>
										</p>
									</div>
									<div class="colu">
										<p>
											<c:forEach items="${roomType.supplements}" var="supp" varStatus="idx">
												<span>${(supp.publishPrice=='0'||supp.publishPrice=='0.00')?'免费':''}${supp.suppName}(<span class="fee">${(supp.suppIsMandatory==true)?'含':'可选'}</span>)</span>
											</c:forEach>
										</p>
										<p>
											<c:forEach items="${roomType.boardbases}" var="bob" varStatus="idx">
												<span>${(bob.bbPublishPrice=='0'||bob.bbPublishPrice=='0.00')?'免费':''}${bob.bbName}(<span class="fee">可选</span>)</span>
											</c:forEach>
										</p>
									</div>
									<div class="colu">
										<p class="pricc"><em class="currency">${holtelDet.currency}</em><span class="display">${roomType.avrNightPublishPrice}</span><em>(含税)/间</em></p>
									</div>
									<div class="colu">
									    <a class="opp" target="_blank" href="${ctx}/hotelPreorder?hotelId=${hotelId}&checkIn=${checkIn}&checkOut=${checkOut}&roomInfo=${roomInfo}&hotelRoomTypeId=${roomType.hotelRoomTypeId}">预定</a> 
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="easyui-tabs" data-options="plain:true" style="margin-bottom: 20px;">
					    <div title="酒店简介" style="padding:5px;">
					        <p>${holtelDet.longDesc}</p>
					        
							<p class="lab">详细地址:</p>
							<p>${holtelDet.country}</p>
							<p>${holtelDet.state}</p>
							<p>${holtelDet.city}</p>
							<p>${holtelDet.location}</p>
							<p>${holtelDet.address}</p>
							<p>${holtelDet.zip}</p>
							<p>tel: ${holtelDet.hotelPhone}</p>
							
							<p class="lab">周围交通:</p>
							<c:forEach items="${holtelDet.refPoints}" var="refPoint">
								<p>${refPoint}</p>
							</c:forEach>
					    </div>
					    <div title="预定声明" data-options="iconCls:'icon-edit'" style="padding:5px;">
					        <p>${holtelDet.voucherRemark}</p>
					    </div>
					    <div title="酒店设施" style="padding:5px;">
					        <c:forEach items="${holtelDet.amenities}" var="amen">
								<p>${amen}</p>
							</c:forEach>
					    </div>
					</div>
				</c:when>
				<c:otherwise>
					抱歉，酒店已下架，请重新搜索...
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<!-- footer -->
	<%@include file="../common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="../common/scripts.jspf"%>
	<script type="text/javascript"
		src="${ctx}<fmt:message key="static.resources.host"/>/js/myfocus/myfocus-2.0.4.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//设置图集
			myFocus.set({
				id:'myFocus',//ID
				pattern:'mF_fancy'//风格
			});
		});
	</script>
</body>
</html>

