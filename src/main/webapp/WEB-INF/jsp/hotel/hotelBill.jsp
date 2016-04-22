<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 订单详情</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/bookDet.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="0" />
	<%@include file="../common/header.jspf"%>

	<form id="inp" action="">
	<div class="bgbody">
		<div class="xbody">
				<div class="myleft">
					<div class="easyui-panel" data-options="border:false" style="margin:0;padding:10px;background-color: #fff;">
						<c:forEach items="${rooms}" var="room" varStatus="vs">
							<div class="room">
								<div class="lab"><span>客房${room.roomId}:&nbsp;</span>${room.adultNum}位成人,&nbsp;${(room.childNum==0)?'':(room.childNum)}${(room.childNum==0)?'':'儿童,&nbsp;'}
									<c:if test="${! empty room.childAges}">
									<span>
									<c:forEach items="${room.childAges}" var="age" varStatus="agevs">
										${(agevs.index>0)?", ":""}${age}
									</c:forEach>
									</span>
									</c:if>
									${fn:split(room.bedding,",")[1]}张床
								</div>
								<c:if test="${(room.boadBase!=null)||(! empty room.supplements)}">
									<div class="colu">
										<p>
											<c:if test="${room.boadBase!=null}">
												<span>${room.boadBase.name}(<span class="fee">${(room.boadBase.price=='0'||room.boadBase.price=='0.00')?'免费':'预付'}${(room.boadBase.price=='0'||room.boadBase.price=='0.00')?'':(origCurrency.symbol)}${(room.boadBase.price=='0'||room.boadBase.price=='0.00')?'':(room.boadBase.price)}</span>)</span>
											</c:if>
										</p>
										<p>
											<c:forEach items="${room.supplements}" var="supp" varStatus="suppvs">
												<span>${supp.suppName}(<span class="fee">${(supp.suppChargeType==1||supp.publishPrice=='0'||supp.publishPrice=='0.00')?'免费':((supp.suppChargeType==2)?'预付':'到付')}${(supp.suppChargeType==1||supp.publishPrice=='0'||supp.publishPrice=='0.00')?'':(origCurrency.symbol)}${(supp.suppChargeType==1||supp.publishPrice=='0'||supp.publishPrice=='0.00')?'':(supp.publishPrice)}</span>)</span>
											</c:forEach>
										</p>
									</div>
								</c:if>
								<p class="inp"><span>联系人姓名<span>*</span>&nbsp;</span>${room.contactPassenger.lastname} ${room.contactPassenger.firstname}</p>
								<p class="inp"><span>联系人手机<span>*</span>&nbsp;</span>${room.contactPassenger.mobilephone}</p>
							</div> 
							<c:if test="${vs.last==false}">
								<hr />
							</c:if>
						</c:forEach>
					</div>
					<div class="easyui-panel" data-options="border:false" style="margin:20px 0;padding:10px;background-color: #fff;">
						<div class="til">
							<p class="titt">确认电子邮件</p>
							<p class="tip">请输入希望接收确认信息的电子邮件地址。</p>
						</div>
						<hr />
						<div class="eml">
							<p class="inp"><span for="email">电子邮箱<span>*</span>&nbsp;</span>${hotel.confirmationEmail}</p>
						</div>
					</div>
				</div>
				<div class="myright">
					<div class="easyui-panel" data-options="border:false" style="margin:0 0 20px 0;padding:10px;background-color: inherit;">
						<div class="hot">
							<p class="htname">${hotel.hotelName}</p>
							<p class="htaddr">${hotel.hotelAddress},&nbsp;${hotel.hotelCountry}</p>
						</div>
						<div class="htdes">
							<p><span>${hotel.roomNum}间客房:&nbsp;</span>${hotel.roomType}</p>
							<p><span>入住:&nbsp;</span><fmt:formatDate value="${hotel.checkIn}" pattern="yyyy-MM-dd" /></p>
							<p><span>退房:&nbsp;</span><fmt:formatDate value="${hotel.checkOut}" pattern="yyyy-MM-dd" /></p>
						</div>
						<c:forEach items="${rooms}" var="room" varStatus="vs">
							<div class="htrp roomSum">
								<p><span>客房${room.roomId}:&nbsp;</span>${room.adultNum}位成人${(room.childNum==0)?'':',&nbsp;'}${(room.childNum==0)?'':(room.childNum)}${(room.childNum==0)?'':'位儿童'}</p>
								<div class="occup">
									<p class="pric"><span class="ite">客房价</span><span class="iteprice">${origCurrency.symbol}<span class="ppp">${room.occuPrice}</span></span></p>
									<c:if test="${room.boadBase!=null}">
										<p class="pric bob"><span class="ite">${room.boadBase.name}</span><span class="iteprice">${origCurrency.symbol}<span class="ppp">${room.boadBase.price}</span></span></p>
									</c:if>
									<c:forEach items="${room.supplements}" var="supp" varStatus="suppvs">
										<p class="pric supp"><span class="ite">${supp.suppName}</span><span class="iteprice">${(supp.suppChargeType==3)?'到店支付':''}${origCurrency.symbol}<span class="ppp">${supp.publishPrice}</span></span></p>
									</c:forEach>
								</div>
							</div>
						</c:forEach>
						<hr />
						<div class="htrp">
							<p class="pric"><span class="itett">预付总计</span><span class="itepricett ">${origCurrency.symbol}<span class="yufutt ppp">${hotel.price}</span></span></p>
							<p class="pric"><span class="itett">到付总计</span><span class="itepricett ">${origCurrency.symbol}<span class="daofutt ppp">${hotel.priceAtproperty}</span></span></p>
						</div>
						<hr />
						<c:choose>
							<c:when test="${hotel.payStatus==0}">
								<div class="til">
									<p class="titt">付款方式</p>
									<p class="tip">请选择您的付款方式后进行支付。</p>
								</div>
								<div class="pay">
									<span class="on"><img src="${ctx}<fmt:message key="static.resources.host"/>/img/alipay.png" alt="alipay" /></span>
								</div>
								<div class="opp">下单付款</div> 
							</c:when>
							<c:when test="${hotel.payStatus==1}">
								<c:choose>
									<c:when test="${(hotel.chargeStatus==0)||(hotel.chargeStatus==2)}"><span class="stu">订单处理中</span></c:when>
									<c:when test="${hotel.chargeStatus==1}"><span class="stu">预定成功</span></c:when>
									<c:otherwise><span class="stu">预定失败，请联系客服</span></c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise><span class="stu">付款失败</span></c:otherwise>
						</c:choose>
					</div>
				</div>
		</div>
	</div>
	</form>
	
	<div id="topay" title="去支付" class="easyui-dialog" data-options="buttons:'#bb',modal:true,width:400,height:200,closed:true,draggable:false,resizable:false" style="text-align: center;font-size: 18px;">
		支付总金额<span class="mon"></span>，将按照当前汇率折合为人民币通过支付宝支付，请确认
	</div>
	<div id="bb">
		<a class="gopay" href="#" target="_blank" style="border: 1px solid rgb(149,184,231);padding:5px;">确定</a>
	</div>
	
    <div class="pageLoading" style="display:none;">
    	<img src="${ctx}<fmt:message key="static.resources.host"/>/img/laoding1.gif" />
    	<div class="close" ></div>
    </div>
    
	<div id="paying" title="正在支付" class="easyui-dialog" data-options="buttons:'#bb1',modal:true,width:400,height:200,closed:true,draggable:false,resizable:false" style="text-align: center;font-size: 18px;">
		<p>请在新页面支付</p>
		<p>付款完成前请不要关闭此窗口</p>
	</div>
    <div id="bb1">
        <a href="javascript:void(0)" class="easyui-linkbutton payed">完成支付</a>
        <a href="javascript:void(0)" class="easyui-linkbutton nopay">取消支付</a>
    </div>
    
	<div id="error" title="预定提示" class="easyui-dialog" data-options="buttons:'#bb2',modal:true,width:400,height:200,closed:true,draggable:false,resizable:false" style="text-align: center;font-size: 18px;">
		<p class="msg1">出错了</p>
	</div>
    <div id="bb2">
        <a href="javascript:void(0)" class="easyui-linkbutton errorclose">确定</a>
    </div>
    
	<form id="hotelPrepay" action="${ctx}/hotelRepay">
		<input type="hidden" name="orderId" value="${hotel.id}" />
	</form>

	<!-- footer -->
	<%@include file="../common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="../common/scripts.jspf"%>
	<script type="text/javascript"
		src="${ctx}<fmt:message key="static.resources.host"/>/js/myfocus/myfocus-2.0.4.min.js"></script>
	
	<script type="text/javascript">
		function checkResult() {
			var orderId = $("input[name=orderId]").val();
			if (orderId == '') {
				return false;
			}
			$.ajax({
			    type: 'get',
			    url: '${ctx}queryCurrUserOrderStatus',
			    data: {
					orderId : orderId
				},
			    success: function(ret) {
			    	if (ret.code != 0) {
						$("#error .msg1").html(ret.message);
						$('#error').dialog({
							closed:false,
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
					} else {
						ret = ret.result;
						if (ret.payStatus == '99') {
							$("#error .msg1").html("支付失败，请稍后再试！");
							$('#error').dialog({
								closed:false,
								style:{
									right:'',
									top:document.body.scrollTop+document.documentElement.scrollTop,
									bottom:''
								}
							});
						} else if (ret.payStatus == '1') {
							if (ret.chargeStatus == '2') {
								$("#error .msg1").html("订单正在处理中，请稍后到账户内查询详情。");
								$('#error').dialog({
									closed:false,
									style:{
										right:'',
										top:document.body.scrollTop+document.documentElement.scrollTop,
										bottom:''
									}
								});
							} else if (ret.chargeStatus == '1') {
								$("#error .msg1").html("恭喜您，预定成功。");
								$('#error').dialog({
									closed:false,
									style:{
										right:'',
										top:document.body.scrollTop+document.documentElement.scrollTop,
										bottom:''
									}
								});
							} else if (ret.chargeStatus == '99') {
								$("#error .msg1").html("预定失败，请联系客服！");
								$('#error').dialog({
									closed:false,
									style:{
										right:'',
										top:document.body.scrollTop+document.documentElement.scrollTop,
										bottom:''
									}
								});
							} else {
								$("#error .msg1").html("订单正在处理中，请稍后到账户内查询详情。");
								$('#error').dialog({
									closed:false,
									style:{
										right:'',
										top:document.body.scrollTop+document.documentElement.scrollTop,
										bottom:''
									}
								});
							}
						} else {
							$("#error .msg1").html("由于网络原因暂无法确认订单状态，请稍后到账户内查询详情。");
							$('#error').dialog({
								closed:false,
								style:{
									right:'',
									top:document.body.scrollTop+document.documentElement.scrollTop,
									bottom:''
								}
							});
						}
					}
			    }
			});
		}
		function prepay(payTypeId){
			if(!$("#inp").form("validate")){
				return;
			}
			if(!payTypeId){
				return 
			}
			var orderId = $("input[name=orderId]").val();
			if(!orderId){
				return;
			}
			var data = {
					payTypeId : payTypeId,
					orderId : orderId
			};
			openLoading();
			$.ajax({
			    type: 'get',
			    url: '${ctx}hotelRepay',
			    data: data,
			    success: function(ret) {
			    	if(ret.code==0){
			    		$("input[name=orderId]").val(ret.result.orderId);
			    		$("#topay .mon").html('${origCurrency.symbol}${hotel.price}');
						$("#bb a").attr("href",ret.result.payUri);
						$('#topay').dialog({
							closed:false,
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
					}else{
						$.messager.show({
							title:'消息提示',
							msg:ret.message,
							timeout:0,
							showType:'slide',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
					}
			    },
			    complete: function(XHR, TS){
			    	closeLoading();
			    }
			});
		}
		function openLoading() {
			closeLoading();
			$(".pageLoading").jqueryDialog({
				"cancelbtn" : ".close"
			});
		}
		function closeLoading() {
			$("#myDialogbox").hide();
		}
		$(document).ready(function(){
			$(".ppp").each(function(i,e){
				var ppp = $(this).html();
				ppp = (Number(ppp)/100).toFixed(2);
				$(this).html(ppp);
			});
			$(".opp").click(function(){
				prepay(1);
			});
			$(".gopay").click(function(){
				$('#topay').dialog('close');
				$('#paying').dialog({
					closed:false,
					style:{
						right:'',
						top:document.body.scrollTop+document.documentElement.scrollTop,
						bottom:''
					}
				});
			});
			$(".nopay").click(function(){
				window.location.reload();
			});
			$(".payed").click(function(){
				$('#paying').dialog('close');
				checkResult();
			});
			$(".errorclose").click(function(){
				$('#error').dialog('close');
				window.location.href = '${ctx}/hotelOrder';
			});
		});
	</script>
</body>
</html>

