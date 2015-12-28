<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 活动</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/act.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="2" />
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody">
			<c:choose>
				<c:when test="${actDet!=null}">
					<div class="easyui-panel" data-options="border:false" style="margin:20px 0;padding:5px;background-color: inherit;">
						<div class="powerpoint">
							<div id="myFocus"><!--焦点图盒子-->
							  <div class="loading"></div><!--载入画面(可删除)-->
							  <div class="pic"><!--图片列表-->
							  	<ul>
							  		<c:choose>
							  			<c:when test="${(actDet.imgs!=null) && (fn:length(actDet.imgs)>0)}">
										  	<c:forEach items="${actDet.imgs}" var="img">
									        <li><a href="javascript:void(0)"><img src="${img}" thumb="" alt="" text="" /></a></li>
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
							<div class="til">
								<p class="label"><span class="hotelName">${actDet.activityName}</span><span class="starLevel ${actDet.stars}Stars"></span></p>
								<p class="cat">${actDet.categoryName}</p>
								<p class="address">${actDet.address}, ${actDet.country}</p>
								<p>tel: ${actDet.phone}</p>
							</div>
							<p class="lab">活动介绍：</p>
							<p>${actDet.activityDesc}</p>
						</div> 
					</div>
					<div class="easyui-panel" data-options="border:true,title:'选择项目'" style="margin-bottom:10px;padding:0;">
						<div class="he">
							<div>项目</div>
							<div>时间</div>
							<div>价格</div>
							<div>操作</div>
						</div>
						<div class="rts">
							<c:forEach items="${actDet.activityOptionVos}" var="option" varStatus="rtvs">
								<div class="rt">
									<div class="colu">
										<p class="rtname">${option.name}</p>
									</div>
									<div class="colu">
										<p class="dates">
											选择出行时间
											<select id="date" name="date">
												<c:forEach items="${option.availabilitiesVos}" var="ava">
													<c:forEach items="${ava.dates}" var="dat">
														<option value="${dat}">${dat}</option>
													</c:forEach>
												</c:forEach>
											</select>
										</p>
									</div>
									<div class="colu">
									<c:if test="${(option.type=='PerPerson')}">
										<c:forEach items="${option.availabilitiesVos}" var="ava">
											<c:forEach items="${ava.dates}" var="dat" varStatus="datst">
												<div date="${dat}" class="pickk" style="${datst.index==0?'':'display:none'}">
													<div class="pricc" >
														<div>
															<p>
																成人(${actDet.maxChildAge}+)
																<select id="adult" name="adult">
																	<c:forEach var="an" begin="1" end="${ava.maxAdults}">
																		<option value="${an}">${an}</option>
																	</c:forEach>
																</select>
															</p>
															<p>
																<span><em class="currency">${actDet.currency}</em><span class="display">${ava.adultPrice}</span><em>/人</em></span>
															</p>
														</div>
														<c:if test="${ava.maxChildren!=0}">
															<div>
																<p>
																	儿童(${actDet.minChildAge}-${actDet.maxChildAge})
																	<select id="child" name="child">
																		<c:forEach var="cn" begin="0" end="${ava.maxChildren}">
																			<option value="${cn}">${cn}</option>
																		</c:forEach>
																	</select>
																</p>
																<p>
																	<span><em class="currency">${actDet.currency}</em><span class="display">${ava.childPrice}</span><em>/人</em></span>
																</p>
															</div>
														</c:if>
													</div>
												</div>
											</c:forEach>
										</c:forEach>
									</c:if>
									<c:if test="${(option.type=='PerUnit')}">
										<c:forEach items="${option.availabilitiesVos}" var="ava">
											<c:forEach items="${ava.dates}" var="dat" varStatus="datst">
												<div date="${dat}" class="pickk" style="${datst.index==0?'':'display:none'}">
													<div class="pricc">
														<div>
															<p>
																项目
																<select id="unit" name="unit">
																	<c:forEach var="an" begin="1" end="${ava.maxUnits}">
																		<option value="${an}">${an}</option>
																	</c:forEach>
																</select>
															</p>
															<p>
																<span><em class="currency">${actDet.currency}</em><span class="display">${ava.unitPrice}</span><em>/项</em></span>
															</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</c:forEach>
									</c:if>
									</div>
									<div class="colu">
									    <a class="opp" opName="${option.name}" target="_blank" sr="${ctx}/arcPreorder?arcId=${activityId}&optionId=${option.id}">预定</a> 
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="easyui-tabs" data-options="plain:true" style="margin-bottom: 20px;">
					    <div title="详细说明" style="padding:5px;">
							<c:forEach items="${actDet.descFrags}" var="frag">
								<pre class="fregD">${frag.desc}</pre>
							</c:forEach>
					    </div>
					    <div title="预定声明" data-options="iconCls:'icon-edit'" style="padding:5px;">
					        <pre class="fregD">${actDet.voucherRemarks}</pre>
					    </div>
					</div>
				</c:when>
				<c:otherwise>
					抱歉，活动已下架，请重新搜索...
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
			$("select[name=date]").change(function(){
				var date = $(this).val();
				$(this).closest(".rt").find(".pickk").hide();
				$(this).closest(".rt").find(".pickk[date="+date+"]").show();
			});
			$(".opp").click(function(){
				var optionName = $(this).attr("opName");
				var date = $(this).closest(".rt").find("select[name=date]").val();
				var adult = '0';
				var child = '0';
				var unit = '0';
				if($(this).closest(".rt").find(".pickk[date="+date+"]").find("select[name=adult]").val()){
					adult = $(this).closest(".rt").find(".pickk[date="+date+"]").find("select[name=adult]").val();
				}
				if($(this).closest(".rt").find(".pickk[date="+date+"]").find("select[name=child]").val()){
					child = $(this).closest(".rt").find(".pickk[date="+date+"]").find("select[name=child]").val();
				}
				if($(this).closest(".rt").find(".pickk[date="+date+"]").find("select[name=unit]").val()){
					unit = $(this).closest(".rt").find(".pickk[date="+date+"]").find("select[name=unit]").val();
				}
				var href = $(this).attr("sr")+'&optionName='+encodeURIComponent(optionName)+'&date='+date+'&adult='+adult+'&child='+child+'&unit='+unit;
				$(this).attr("href",href);
				return true;
			});
		});
	</script>
</body>
</html>

