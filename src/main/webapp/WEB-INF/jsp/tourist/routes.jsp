<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 路线选择</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/routes.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="${areaId!=null?areaId:'0'}" />
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
							<p><a class="${departureId==departure.id?'on':''}" href="${ctx}/pickRoute?areaId=${departure.areaId}&departureId=${departure.id}">${departure.name}</a></p>
							</c:forEach>
					    </div>
					</div>
					<div class="easyui-panel" data-options="style:{margin:'10px 0'},border:true,title:'热门景点'">
						<div class="choose" style="padding: 5px 20px;">
							<c:forEach items="${views}" var="view">
							<p><a class="${viewId==view.id?'on':''}" href="${ctx}/pickRoute?areaId=${view.areaId}&viewId=${view.id}">${view.name}</a></p>
							</c:forEach>
					    </div>
					</div>
				</div>
				<div class="myright">
					<div class="easyui-panel" data-options="collapsible:true,border:true,title:'路线筛选'" style="padding:5px;">
						<div class="conditions">
							<div class="condition">
								<label>出发地</label>
								<div id="depart">
									<p class="all">全部</p>
									<c:forEach items="${departures}" var="departure">
									<p class="${departure.id}" departureId="${departure.id}">${departure.name}</p>
									</c:forEach>
								</div>
						    </div>
						    <div class="condition">
								<label>途经景点</label>
								<div id="vi">
									<p class="all">全部</p>
									<c:forEach items="${views}" var="view">
									<p class="${view.id}" viewId="${view.id}">${view.name}</p>
									</c:forEach>
								</div>
						    </div>
						    <div class="condition">
								<label>旅行时间</label>
								<div id="days">
									<p class="all right">全部</p>
									<p class="1-2" minDays='1' maxDays='2'>1-2天</p>
									<p class="3-4" minDays='3' maxDays='4'>3-4天</p>
									<p class="5-6" minDays='5' maxDays='6'>5-6天</p>
									<p class="7-8" minDays='7' maxDays='8'>7-8天</p>
									<p class="9-10" minDays='9' maxDays='10'>9-10天</p>
									<p class="11-" minDays='11'>11天以上</p>
									<p class="other" style="display: none;"></p>
								</div>
						    </div>
						    <div class="condition">
								<label>出发时间</label>
								<div id="departDate">
									<p class="all">全部</p>
									<p class="一" departDate='一'>周一</p>
									<p class="二" departDate='二'>周二</p>
									<p class="三" departDate='三'>周三</p>
									<p class="四" departDate='四'>周四</p>
									<p class="五" departDate='五'>周五</p>
									<p class="六" departDate='六'>周六</p>
									<p class="日" departDate='日'>周日</p>
									<em style="color:red;">(可多选)</em>
								</div>
						    </div>
						    <div class="buttons">
								<a id="submitSearch" href="javascript:void(0)" class="easyui-linkbutton">查找路线</a>
								<a id="resetSearch" href="javascript:void(0)" class="easyui-linkbutton">重置条件</a>
						    </div>
					    </div>
					</div>
					<div class="easyui-panel" data-options="noheader:true,border:true" style="padding:5px;">
						<div class="pagins">
							<div class="easyui-pagination" data-options="showRefresh:false,
									showPageList:false,
									total:${pages.totalCount},
									pageNumber:${pages.page},
									pageSize:${pages.limit},
									layout:['first','prev','links','next','last'],
									links:7,
									displayMsg:'显示{from}到{to}，共{total}条路线',
									onSelectPage: onPageChange"></div>
						</div>
						<div class="routeList">
							<c:choose>
								<c:when test="${tourRoutes==null||fn:length(tourRoutes)<=0}">
									<div><span class="label">暂无路线，敬请期待...</span></div>
								</c:when>
								<c:otherwise>
									<c:forEach items="${tourRoutes}" var="tourRoute">
									<div>
										<a class="label" href="${ctx}/route?areaId=${tourRoute.area.id}&routeId=${tourRoute.id}">${tourRoute.name}</a>
										<div class="bref">
											<div class="small"><a href="${ctx}/route?areaId=${tourRoute.area.id}&routeId=${tourRoute.id}">
												<img src="<fmt:message key="img.resources.host"/>/${(tourRoute.smallPhotoes==null)?'':(tourRoute.smallPhotoes[0].relativePath)}" alt="小图" /></a></div>
											<div class="content">
												<p>旅行时间：${tourRoute.days}天</p>
												<p>出团日期：${tourRoute.departureDate}</p>
												<p>出发地点：${tourRoute.departure==null?'':(tourRoute.departure.name)}</p>
												<p>结束地点：${tourRoute.arrival==null?'':(tourRoute.arrival.name)}</p>
												<p>经典景点：<c:forEach items="${tourRoute.views}" var="view" varStatus="st">${view.name}${st.last?'':','}</c:forEach></p>
											</div>
											<div class="price">
												<p><span class="display">￥${tourRoute.price}</span>/人</p>
												<a class="addFavo" routeId="${tourRoute.id}" href="javascript:void(0);"><p class="collect">加入收藏</p></a>
												<a href="${ctx}/route?areaId=${tourRoute.area.id}&routeId=${tourRoute.id}"><p class="detail">查看详情</p></a>
											</div>
										</div>
									</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="pagins">
							<div class="easyui-pagination" data-options="showRefresh:false,
									showPageList:false,
									total:${pages.totalCount},
									pageNumber:${pages.page},
									pageSize:${pages.limit},
									layout:['first','prev','links','next','last'],
									links:7,
									displayMsg:'显示{from}到{to}，共{total}条路线',
									onSelectPage: onPageChange"></div>
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
		function onPageChange(pageNumber, pageSize){
			$(this).pagination('loading');
			var href = getHref() + "&page=" + pageNumber;
			location.href = href;
		}
		function select(groupId,itemCls,otherName){
			var item = $("#"+groupId+" ."+itemCls);
			if(item.length==0){
				item = $("#"+groupId+" .other");
				item.css('display','inline-block');
				item.text(otherName);
			}
			item.addClass("on");
		}
		function unSelect(groupId,itemCls){
			$("#"+groupId+" ."+itemCls).removeClass("on");
			$("#"+groupId+" .other").removeClass("on").css('display','none').text('');
		}
		function unSelectAll(groupId){
			$("#"+groupId+" p").removeClass("on");
			$("#"+groupId+" .other").removeClass("on").css('display','none').text('');
		}
		function resetSearch(){
			unSelectAll('depart');
			unSelectAll('vi');
			unSelectAll('days');
			unSelectAll('departDate');
			select('depart','all');
			select('vi','all');
			select('days','all');
			select('departDate','all');
			departureId='';
			viewId='';
			minDays='';
			maxDays='';
			departureDates=[];
		}
		function getHref(){
			var params = [];
			if(departureId){
				params.push('departureId='+departureId);
			}
			if(viewId){
				params.push('viewId='+viewId);
			}
			if(minDays){
				params.push('minDays='+minDays);
			}
			if(maxDays){
				params.push('maxDays='+maxDays);
			}
			if(departureDates&&departureDates.length>0){
				var param = '';
				$(departureDates).each(function(i,e){
					if(i>0){
						param += '&';
					}
					param += 'departureDates='+e;
				});
				params.push(param);
			}
			var href = '${ctx}/pickRoute?areaId=${areaId}';
			if(params&&params.length>0){
				$(params).each(function(i,e){
						href += '&' + e;
				});
			}
			return encodeURI(href);
		}
		var departureId='${departureId}';
		var viewId='${viewId}';
		var minDays='${minDays}';
		var maxDays='${maxDays}';
		var departureDates=JSON.parse('${(departureDates!=null&&departureDates!='')?departureDates:'[]'}');
		$(document).ready(function(){
			select('depart',departureId?departureId:'all');
			select('vi',viewId?viewId:'all');
			select('days',(minDays?(minDays+'-'+(maxDays?maxDays:'')):'all'),(minDays?(minDays+(maxDays?('-'+maxDays+'天'):'天以上')):''));
			if(!departureDates||departureDates.length==0){
				select('departDate','all');
			}else{
				$(departureDates).each(function(i,e){
					select('departDate',e);
				});
			}
			
			$('#submitSearch').click(function(){
				location.href = getHref();
			});
			$('#resetSearch').click(function(){
				resetSearch();
			});
			$('#depart p').click(function(){
				departureId=$(this).attr('departureId');
				unSelectAll('depart');
				select('depart',departureId?departureId:'all');
			});
			$('#vi p').click(function(){
				viewId=$(this).attr('viewId');
				unSelectAll('vi');
				select('vi',viewId?viewId:'all');
			});
			$('#days p').click(function(){
				minDays=$(this).attr('minDays');
				maxDays=$(this).attr('maxDays');
				unSelectAll('days');
				select('days',(minDays?(minDays+'-'+(maxDays?maxDays:'')):'all'),(minDays?(minDays+(maxDays?('-'+maxDays+'天'):'天以上')):''));
			});
			$('#departDate p').click(function(){
				var isOn = $(this).hasClass('on');
				if(isOn){
					var currDate = $(this).attr('departDate');
					$(departureDates).each(function(i,e){
						if(e==currDate){
							departureDates.splice(i,1);
						}
					});
				}else{
					var currDate = $(this).attr('departDate');
					if(currDate){
						departureDates.push(currDate);
					}else{
						departureDates=[];
					}
				}
				unSelectAll('departDate');
				if(!departureDates||departureDates.length==0){
					select('departDate','all');
				}else{
					$(departureDates).each(function(i,e){
						select('departDate',e);
					});
				}
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
		});
	</script>
</body>
</html>

