<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - ${route.name}</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/calendar.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/route.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="${areaId}" />
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody">
				<div class="myleft">
					<div class="easyui-panel" data-options="border:true,title:'快速查找'">
						<div class="choose" style="padding: 5px 20px;">
							<c:forEach items="${fastSearches}" var="fastSearch">
							<p><a class="${fastSearchId==fastSearch.id?'on':''}" href="${ctx}/pickRoute?areaId=${fastSearch.areaId}&minDays=${fastSearch.minDays}&maxDays=${fastSearch.maxDays}&fastSearchId=${fastSearch.id}">${fastSearch.name}</a></p>
							</c:forEach>
					    </div>
					</div>
					<div class="easyui-panel" data-options="style:{margin:'10px 0'},border:true,title:'出发城市'">
						<div class="choose" style="padding: 5px 20px;">
							<c:forEach items="${departures}" var="departure">
							<p><a class="${departureId==departure.id?'on':''}" href="${ctx}/pickRoute?areaId=${areaId}&departureId=${departure.id}">${departure.name}</a></p>
							</c:forEach>
					    </div>
					</div>
					<div class="easyui-panel" data-options="style:{margin:'10px 0'},border:true,title:'热门景点'">
						<div class="choose" style="padding: 5px 20px;">
							<c:forEach items="${views}" var="view">
							<p><a class="${viewId==view.id?'on':''}" href="${ctx}/pickRoute?areaId=${areaId}&viewId=${view.id}">${view.name}</a></p>
							</c:forEach>
					    </div>
					</div>
				</div>
				<div class="myright">
					<c:choose>
						<c:when test="${route!=null}">
							<div class="easyui-panel" data-options="border:true,title:'${route.name}'" style="margin-bottom:10px;padding:5px;">
								<div class="powerpoint">
									<div id="myFocus"><!--焦点图盒子-->
									  <div class="loading"></div><!--载入画面(可删除)-->
									  <div class="pic"><!--图片列表-->
									  	<ul>
									  		<c:choose>
									  			<c:when test="${(route.middlePhotoes!=null) && (fn:length(route.middlePhotoes)>0)}">
												  	<c:forEach items="${route.middlePhotoes}" var="middlePhoto">
											        <li><a href="javascript:void(0)"><img 
											        	src="<fmt:message key="img.resources.host"/>/${middlePhoto.relativePath}" 
											        	thumb="" alt="${route.name}" text="" /></a></li>
													</c:forEach>
									  			</c:when>
									  			<c:otherwise>
									  				<li><a href="javascript:void(0)"><img 
											        	src="<fmt:message key="img.resources.host"/>/" 
											        	thumb="" alt="暂无图片" text="" /></a></li>
									  			</c:otherwise>
									  		</c:choose>
									  	</ul>
									  </div>
									</div>
								</div>
								<div class="abstract">
									<p>旅行时间：${route.days}天</p>
									<p>出团日期：${route.departureDate}</p>
									<p>出发地点：${route.departure==null?'':(route.departure.name)}</p>
									<p>结束地点：${route.arrival==null?'':(route.arrival.name)}</p>
									<p class="pri"><span class="price">￥${route.price}</span>/人</p>
									<p class="share">社区分享组件</p>
								</div> 
							</div>
							<div class="easyui-panel" data-options="border:true,title:'我要报名'" style="margin-bottom:10px;padding:5px;">
								<div class="pick">
									<p><em class="step">1</em>请选择出发日期</p>
									<p><em></em><span>出团日期：${route.departureDate}</span></p>
									<p><em></em><input style="cursor:pointer;" readonly="readonly" type="text" id="departureDay" /><span id="dateImg"></span></p>
									<p><em class="step">2</em>报名人数：(输入1-100)<input id="adultsNum" maxlength="3" style="width:30px;" class="easyui-numberbox" value="1" data-options="min:1,max:100" />人</p>
								</div>
								<div class="sum">
									<p class="pri">共<span class="price">￥<span id="totalPrice">${route.price}</span></span></p>
									<p><em title="每20积分可在下次订购时抵价1元" class="easyui-tooltip" data-options="position:'top'">?</em>订购可以得积分：<span class="point" id="totalPoints">${route.price}</span></p>
									<p><em title="在发团前，只要发现本站内的相同路线有更低价格，我们承诺100%退还差价" class="easyui-tooltip" data-options="position:'bottom'">?</em>最低价格保证</p>
								</div>
								<div class="opera">
									<a class="addFavo" routeId="${route.id}" href="javascript:void(0);"><p class="collect">加入收藏</p></a>
									<a class="addCart" routeId="${route.id}" href="javascript:void(0);"><p class="cart">加购物车</p></a>
									<a href="javascript:void(0);"><p class="pay">立即订购</p></a>
								</div>
							</div>
							<div class="easyui-panel" data-options="border:true,title:'精彩游记'" style="margin-bottom:10px;padding:5px;">
								<div class="travels">
									<c:forEach items="${travels}" var="travel" varStatus="st">
									<p><a href="${ctx}/travel?id=${travel.id}">${travel.title}</a></p>
									</c:forEach>
								</div>
							</div>
							<div class="easyui-tabs" data-options="plain:true">
							    <div title="行程明细" style="padding:5px;">
							        ${route.description}
							    </div>
							    <div title="价格说明" style="padding:5px;">
							        ${route.priceDetail}
							    </div>
							    <div title="注意事项" style="padding:5px;">
							        ${route.extra}
							    </div>
							    <div title="用户评价" data-options="iconCls:'icon-edit'" style="padding:5px;">
							                暂无
							    </div>
							    <div title="问题咨询" data-options="iconCls:'icon-help'" style="padding:5px;">
							                暂无
							    </div>
							</div>
						</c:when>
						<c:otherwise>
							本路线已结束，敬请期待下一期的开始...
						</c:otherwise>
					</c:choose>
				</div>
		</div>
	</div>

	<!-- footer -->
	<%@include file="../common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="../common/scripts.jspf"%>
	<script type="text/javascript"
		src="${ctx}<fmt:message key="static.resources.host"/>/js/jquery-ui-1.10.3.custom.js"></script>
	<script type="text/javascript"
		src="${ctx}<fmt:message key="static.resources.host"/>/js/myfocus/myfocus-2.0.4.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#departureDay").datepicker({
				changeMonth: true,
				changeYear: true,
				monthNames: [ "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" ],
				monthNamesShort: [ "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" ],
				dayNamesMin: [ "日", "一", "二", "三", "四", "五", "六"],
				dayNamesShort: [ "日", "一", "二", "三", "四", "五", "六"],
				dayNames: [ "日", "一", "二", "三", "四", "五", "六" ],
				myspdayarr:'${route.noDepartureDate}',
				yearRange:"1900:"+(new Date()).getFullYear()+"",
				altField: '#departureDay', 
				altFormat: 'yy-mm-dd, 周DD'});
			$("#dateImg").click(function(){
				$("#departureDay").datepicker("show");
			});
			$("#adultsNum").bind('blur keyup',function(){
				var value = $.trim($(this).val());
				var total = value?(Number(value)*${route.price}):'0';
				$("#totalPrice").text(total);
				$("#totalPoints").text(total);
			});
			$(".addFavo").click(function(){
				var routeId = $(this).attr("routeId");
				$.getJSON("${ctx}/user/favo/add",{routeId:routeId},function(ret){
					if(ret.code==0){
						$.messager.show({
							title:'消息提示',
							msg:'收藏成功！',
							timeout:1500,
							showType:'slide',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
					}else{
						$.messager.show({
							title:'消息提示',
							msg:ret.msg,
							timeout:1500,
							showType:'slide',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
					}
				});
			});
			$(".addCart").click(function(){
				var depDate = $("#departureDay").datepicker("getDate");
				if(!depDate){
					alert('请选择出发日期');
					return;
				}
				var personNumber = $("#adultsNum").val();
				if(!personNumber){
					alert('请填写报名人数');
					return;
				}
				var departureDate = getDateString(depDate);
				var routeId = $(this).attr("routeId");
				$.getJSON("${ctx}/user/cart/add",
					{routeId:routeId,departureDate:departureDate,personNumber:personNumber},
				function(ret){
					if(ret.code==0){
						$.messager.show({
							title:'消息提示',
							msg:'加入购物车成功！',
							timeout:1500,
							showType:'slide',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
					}else{
						$.messager.show({
							title:'消息提示',
							msg:ret.msg,
							timeout:1500,
							showType:'slide',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
					}
				});
			});
			//设置图集
			myFocus.set({
				id:'myFocus',//ID
				pattern:'mF_fancy'//风格
			});
		});
	</script>
</body>
</html>

