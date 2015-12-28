<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 付款</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/actbook.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="2" />
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody">
				<div class="myleft">
					<div class="easyui-panel" data-options="border:false" style="margin:0;padding:10px;background-color: #fff;">
						<div class="til">
							<p class="titt">活动信息</p>
							<p class="tipp">联系人姓名只能使用英文。</p>
						</div>
						<hr />
						<c:forEach items="${preBook.activityPreInfoVos}" var="option" varStatus="vs">
							<div class="option" optionId="${option.optionId}">
								<div class="lab"><span>${optionName}:&nbsp;${option.date},&nbsp;</span>${(adult==0)?'':(adult)}${(adult==0)?'':'成人,&nbsp;'}${(child==0)?'':(child)}${(child==0)?'':'儿童,&nbsp;'}${(unit==0)?'':(unit)}${(unit==0)?'':'项目,&nbsp;'}
								</div>
								<c:forEach items="${option.activityAdditions}" var="actAdd" varStatus="vrst">
									<p class="inp"><label for="option${vs.index}add${vrst.index}">${actAdd.type}<span>*</span>&nbsp;</label>
									<c:choose>
										<c:when test="${actAdd.category=='list'}">
												<select id="option${vs.index}add${vrst.index}" name="${actAdd.typeId}">
													<c:forEach items="${actAdd.valueList}" var="val">
				                                    	<option value="${val}">${val}</option>
													</c:forEach>
												</select>
										</c:when>
										<c:when test="${actAdd.category=='bool'}">
												<select id="option${vs.index}add${vrst.index}" name="${actAdd.typeId}">
			                                    	<option value="true">是</option>
			                                    	<option value="false">否</option>
												</select>
										</c:when>
										<c:when test="${actAdd.category=='num'}">
											<input id="option${vs.index}add${vrst.index}" name="${actAdd.typeId}" class="easyui-validatebox" data-options="required:true,validType:'number'" style="width:150px" />
										</c:when>
										<c:when test="${actAdd.category=='numrange'}">
											<input id="option${vs.index}add${vrst.index}" name="${actAdd.typeId}" class="easyui-validatebox" data-options="required:true,validType:'numrange[${actAdd.minVal},${actAdd.maxVal}]'" style="width:150px" />
										</c:when>
										<c:when test="${actAdd.category=='text'}">
											<input id="option${vs.index}add${vrst.index}" name="${actAdd.typeId}" class="easyui-validatebox" data-options="required:true" style="width:250px" />
										</c:when>
									</c:choose>
									</p>
								</c:forEach>
								<c:forEach items="${option.passengerInfos}" var="pass" varStatus="past">
									<div class="pax">
										<p class="inp">${(pass.isMainContact==true)?'主要联系人':'旅客'}信息-${pass.typeName}${pass.seqNumber}:</p>
										<c:if test="${pass.needFirstName==true}">
											<p class="inp"><label for="option${vs.index}firstname">姓氏<span>*</span>&nbsp;</label>
											<input id="option${vs.index}firstname" name="firstname" class="easyui-validatebox" data-options="required:true,validType:'alphabet'" style="width:150px" /></p>
										</c:if>
										<c:if test="${pass.needLastName==true}">
											<p class="inp"><label for="option${vs.index}lastname">名字<span>*</span>&nbsp;</label>
											<input id="option${vs.index}lastname" name="lastname" class="easyui-validatebox" data-options="required:true,validType:'alphabet'" style="width:150px" /></p>
										</c:if>
										<c:if test="${pass.needMobilePhone==true}">
											<p class="inp"><label for="option${vs.index}mobilephone">手机号<span>*</span>&nbsp;</label>
											<select name="ccode">
			                                    <%@include file="../common/phonecountry.jspf"%>
											</select>
											-
											<input id="option${vs.index}mobilephone" name="mobilephone" class="easyui-validatebox" data-options="required:true,validType:'number'" style="width:150px" /></p>
										</c:if>
										<c:forEach items="${pass.additions}" var="passAdd" varStatus="vrst">
											<p class="inp"><label for="option${vs.index}passAdd${vrst.index}">${passAdd.type}<span>*</span>&nbsp;</label>
											<c:choose>
												<c:when test="${passAdd.category=='list'}">
														<select id="option${vs.index}passAdd${vrst.index}" name="${passAdd.typeId}">
															<c:forEach items="${passAdd.valueList}" var="val">
						                                    	<option value="${val}">${val}</option>
															</c:forEach>
														</select>
												</c:when>
												<c:when test="${passAdd.category=='bool'}">
														<select id="option${vs.index}passAdd${vrst.index}" name="${passAdd.typeId}">
					                                    	<option value="true">是</option>
					                                    	<option value="false">否</option>
														</select>
												</c:when>
												<c:when test="${passAdd.category=='num'}">
													<input id="option${vs.index}passAdd${vrst.index}" name="${passAdd.typeId}" class="easyui-validatebox" data-options="required:true,validType:'number'" style="width:150px" />
												</c:when>
												<c:when test="${passAdd.category=='numrange'}">
													<input id="option${vs.index}passAdd${vrst.index}" name="${passAdd.typeId}" class="easyui-validatebox" data-options="required:true,validType:'numrange[${passAdd.minVal},${passAdd.maxVal}]'" style="width:150px" />
												</c:when>
												<c:when test="${passAdd.category=='text'}">
													<input id="option${vs.index}passAdd${vrst.index}" name="${passAdd.typeId}" class="easyui-validatebox" data-options="required:true" style="width:250px" />
												</c:when>
											</c:choose>
											</p>
										</c:forEach>
									</div>
								</c:forEach>
							</div> 
							<hr />
						</c:forEach>
						
					</div>
					<div class="easyui-panel" data-options="border:false" style="margin:20px 0;padding:10px;background-color: #fff;">
						<div class="til">
							<p class="titt">确认电子邮件</p>
							<p class="tip">请输入希望接收确认信息的电子邮件地址。</p>
						</div>
						<hr />
						<div class="eml">
							<p class="inp"><label for="email">电子邮箱<span>*</span>&nbsp;</label><input placeholder="请输入电子邮箱地址" id="email" name="email" class="easyui-validatebox" data-options="required:true,validType:'email'" style="width:300px" /></p>
						</div>
					</div>
					<div class="easyui-panel" data-options="border:false" style="margin:20px 0;padding:10px;background-color: #fff;">
						<div class="til">
							<p class="titt">预定声明</p>
						</div>
						<hr />
						<div class="rul">
							<pre class="tip">${preBook.voucherRemark}</pre>
						</div>
					</div>
				</div>
				<div class="myright">
					<div class="easyui-panel" data-options="border:false" style="margin:0 0 20px 0;padding:10px;background-color: inherit;">
						<div class="hot">
							<p class="htname">${preBook.activityName}</p>
							<p class="starLevel ${preBook.stars}stars"></p>
							<p class="htaddr">${preBook.address},&nbsp;${preBook.country}</p>
							<p>tel: ${preBook.phone}</p>
						</div>
						<c:forEach items="${preBook.activityPreInfoVos}" var="option" varStatus="vs">
							<div class="htrp optionSum" optionId="${option.optionId}">
								<p><span>${optionName}:&nbsp;</span>${option.date},&nbsp;${(adult==0)?'':(adult)}${(adult==0)?'':'成人,&nbsp;'}${(child==0)?'':(child)}${(child==0)?'':'儿童,&nbsp;'}${(unit==0)?'':(unit)}${(unit==0)?'':'项目,&nbsp;'}</p>
								<c:forEach items="${option.priceBreakDown}" var="break" varStatus="bst">
									<p class="pric"><span class="ite">${break.name}</span><span class="iteprice">${break.num}&nbsp;x&nbsp;${preBook.currency}${break.amount}</span></p>
								</c:forEach>
								<p class="pric"><span class="itett">小计</span><span class="itepricett ">${preBook.currency}<span class="yufu">${option.priceSum}</span></span></p>
								<p class="pric"><span class="itepricett">(含税${preBook.currency}${option.taxSum})</span></p>
							</div>
						</c:forEach>
						<hr />
						<div class="htrp">
							<p class="pric"><span class="itett">总计</span><span class="itepricett ">${preBook.currency}<span class="yufutt">${preBook.totalPrice}</span></span></p>
						</div>
						<hr />
						<div class="til">
							<p class="titt">付款方式</p>
							<p class="tip">请选择您的付款方式后进行支付。</p>
						</div>
						<div class="pay">
							<span class="on"><img src="${ctx}<fmt:message key="static.resources.host"/>/img/alipay.png" alt="alipay" /></span>
						</div>
						<div class="opp">下单付款</div> 
					</div>
				</div>
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
			$.extend($.fn.validatebox.defaults.rules, {
			    alphabet: {
			        validator: function(value){
			            return /^[',\-\.]*[a-zA-Z\u00C0-\u00FF]+[a-zA-Z\u00C0-\u00FF',\-\.]*$/.test(value);
			        },
			        message: 'Please enter alphabets.'
			    },
			    number: {
			        validator: function(value){
			            return /^[0-9]+$/.test(value);
			        },
			        message: 'Please enter numbers.'
			    },
			    numrange:{
			    	validator: function(value,param){
			            return /^[0-9]+$/.test(value) && value>=param[0] && value<=param[1];
			        },
			        message: 'Please enter numbers between {0} and {1}.'
			    }
			});
		});
	</script>
</body>
</html>

