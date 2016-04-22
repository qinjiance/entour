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
	href="${ctx}<fmt:message key="static.resources.host"/>/css/book.css" />
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
						<div class="til">
							<p class="titt">旅客信息</p>
							<p class="tip">请输入办理入住手续的住客姓名。年龄必须为 18 岁或以上。</p>
							<p class="tipp">联系人姓名只能使用英文。</p>
						</div>
						<hr />
						<c:forEach items="${holtelDetPrice.roomType.roomPrices}" var="room" varStatus="vs">
							<div class="room" roomId="${room.roomId}">
								<div class="lab"><span>客房${room.roomId}:&nbsp;</span>${room.adultNum}位成人,&nbsp;${(room.childNum==0)?'':(room.childNum)}${(room.childNum==0)?'':'儿童,&nbsp;'}
									<select name="bedding">
									<c:forEach items="${room.occupancyVos}" var="occup" varStatus="occvs">
										<option ${(occvs.index==0)?'checked="checked"':''} value="${occup.occuId}">${occup.bedNum}张${(occup.bedType==1)?'单':((occup.bedType==2)?'双':(occup.bedType))}人床</option>
									</c:forEach>
									</select>
								</div>
								<c:forEach items="${room.occupancyVos}" var="occup" varStatus="occvs">
									<div class="colu" occPrice="${occup.occuPubPrice}" occId="${occup.occuId}" 
										bedding="${occup.bedding}" style="${(occvs.index==0)?'':'display:none;'}">
										<p>
											<c:forEach items="${occup.boardbases}" var="bob" varStatus="bobvs">
												<input id="bob${bobvs.index}" name="bob" value="${bob.bbId}" bobPrice="${bob.bbPublishPrice}"
													type="radio" ${(bob.bbPublishPrice=='0'||bob.bbPublishPrice=='0.00')?'checked="checked"':''} />
												<label for="bob${bobvs.index}">${bob.bbName}(<span class="fee">${(bob.bbPublishPrice=='0'||bob.bbPublishPrice=='0.00')?'免费':'预付'}${(bob.bbPublishPrice=='0'||bob.bbPublishPrice=='0.00')?'':(holtelDetPrice.currencySymbol)}${(bob.bbPublishPrice=='0'||bob.bbPublishPrice=='0.00')?'':(bob.bbPublishPrice)}</span>)</label>
											</c:forEach>
										</p>
										<p>
											<c:forEach items="${occup.supplements}" var="supp" varStatus="suppvs">
												<input id="supp${suppvs.index}" name="supp" value="${supp.suppId}" suppPrice="${supp.publishPrice}" suppChargeType="supp.suppChargeType"
													type="checkbox" ${(supp.suppIsMandatory==true)?'checked="checked" disabled="disabled"':((supp.suppChargeType==1||supp.publishPrice=='0'||supp.publishPrice=='0.00')?'checked="checked"':'')} />
												<label for="supp${suppvs.index}">${supp.suppName}(<span class="fee">${(supp.suppChargeType==1||supp.publishPrice=='0'||supp.publishPrice=='0.00')?'免费':((supp.suppChargeType==2)?'预付':'到付')}${(supp.suppChargeType==1||supp.publishPrice=='0'||supp.publishPrice=='0.00')?'':(holtelDetPrice.currencySymbol)}${(supp.suppChargeType==1||supp.publishPrice=='0'||supp.publishPrice=='0.00')?'':(supp.publishPrice)}</span>)</label>
											</c:forEach>
										</p>
									</div>
								</c:forEach>
								<p class="inp"><label for="lastname">联系人姓<span>*</span>&nbsp;</label><input placeholder="请输入英文姓名" id="lastname" name="lastname" class="easyui-validatebox" data-options="required:true,validType:'alphabet'" style="width:150px" />
									<label for="firstname">联系人名<span>*</span>&nbsp;</label><input placeholder="请输入英文姓名" id="firstname" name="firstname" class="easyui-validatebox" data-options="required:true,validType:'alphabet'" style="width:150px" /></p>
								<p class="inp"><label for="phone">联系人手机<span>*</span>&nbsp;</label>
									<select name="ccode">
	                                    <%@include file="../common/phonecountry.jspf"%>
									</select>
									-
									<input placeholder="方便酒店与您联系" id="phone" name="phone" class="easyui-validatebox" data-options="required:true,validType:'number'" style="width:150px" />
								</p>
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
							<p class="inp"><label for="email">电子邮箱<span>*</span>&nbsp;</label><input placeholder="请输入电子邮箱地址" id="email" name="email" class="easyui-validatebox" data-options="required:true,validType:'email'" style="width:300px" /></p>
						</div>
					</div>
				</div>
				<div class="myright">
					<div class="easyui-panel" data-options="border:false" style="margin:0 0 20px 0;padding:10px;background-color: inherit;">
						<div class="hot">
							<img src="${holtelDetPrice.thumb}" alt="${holtelDetPrice.hotelName}" />
							<p class="htname">${holtelDetPrice.hotelName}</p>
							<p class="stars ${holtelDetPrice.starsLevel}stars"></p>
							<p class="htaddr">${holtelDetPrice.address},&nbsp;${holtelDetPrice.country}</p>
						</div>
						<div class="htdes">
							<p><span>${fn:length(holtelDetPrice.roomType.roomPrices)}间客房:&nbsp;</span>${holtelDetPrice.roomType.name}</p>
							<p><span>入住:&nbsp;</span>${checkIn}</p>
							<p><span>退房:&nbsp;</span>${checkOut}</p>
							<p>共${holtelDetPrice.roomType.nights}晚</p>
						</div>
						<c:forEach items="${holtelDetPrice.roomType.roomPrices}" var="room" varStatus="vs">
							<div class="htrp roomSum" roomId="${room.roomId}">
								<p><span>客房${room.roomId}:&nbsp;</span>${room.adultNum}位成人${(room.childNum==0)?'':',&nbsp;'}${(room.childNum==0)?'':(room.childNum)}${(room.childNum==0)?'':'位儿童'}</p>
								<c:forEach items="${room.occupancyVos}" var="occup" varStatus="occvs">
									<div class="occup" occId="${occup.occuId}" occPrice="${occup.occuPubPrice}" style="${(occvs.index==0)?'':'display:none;'}">
										<c:forEach items="${occup.priceBreakdown}" var="pribre" varStatus="prvs">
											<p class="pric"><span class="ite">第${prvs.index+1}晚</span><span class="iteprice">${holtelDetPrice.currencySymbol}${pribre}</span></p>
										</c:forEach>
										<p class="pric"><span class="iteprice">(含税${holtelDetPrice.currencySymbol}${occup.taxPubPrice})</span></p>
										<c:forEach items="${occup.boardbases}" var="bob" varStatus="bobvs">
											<p class="pric bob" bbId="${bob.bbId}" style="${(bob.bbPublishPrice=='0'||bob.bbPublishPrice=='0.00')?'':'display:none;'}">
												<span class="ite">${bob.bbName}</span><span class="iteprice">${holtelDetPrice.currencySymbol}${bob.bbPublishPrice}</span>
											</p>
										</c:forEach>
										<c:forEach items="${occup.supplements}" var="supp" varStatus="suppvs">
											<p class="pric supp" suppId="${supp.suppId}" ctype="${supp.suppChargeType}" style="${(supp.suppIsMandatory==true||supp.suppChargeType==1||supp.publishPrice=='0'||supp.publishPrice=='0.00')?'':'display:none;'}">
												<span class="ite">${supp.suppName}</span><span class="iteprice">${(supp.suppChargeType==3)?'到店支付':''}${holtelDetPrice.currencySymbol}${supp.publishPrice}</span>
											</p>
										</c:forEach>
										<p class="pric"><span class="itett">预付小计</span><span class="itepricett ">${holtelDetPrice.currencySymbol}<span class="yufu"> -</span></span></p>
										<p class="pric"><span class="itett">到付小计</span><span class="itepricett ">${holtelDetPrice.currencySymbol}<span class="daofu"> -</span></span></p>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
						<hr />
						<div class="htrp">
							<p class="pric"><span class="itett">预付总计</span><span class="itepricett ">${holtelDetPrice.currencySymbol}<span class="yufutt"> -</span></span></p>
							<p class="pric"><span class="itett">到付总计</span><span class="itepricett ">${holtelDetPrice.currencySymbol}<span class="daofutt"> -</span></span></p>
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
    
	<form id="hotelPrepay" action="${ctx}/hotelPrepay">
		<input type="hidden" name="orderId" value="${orderId}" />
		<input type="hidden" name="hotelId" value="${hotelId}" />
		<input type="hidden" name="checkIn" value="${checkIn}" />
		<input type="hidden" name="checkOut" value="${checkOut}" />
		<input type="hidden" name="roomInfo" value="${roomInfo}" />
		<input type="hidden" name="hotelRoomTypeId" value="${hotelRoomTypeId}" />
		<input type="hidden" name="hotelBookRoomInfosStr" value="" />
		<input type="hidden" name="bookCurrency" value="${holtelDetPrice.currency}" />
		<input type="hidden" name="totalYufu" value="" />
		<input type="hidden" name="totalDaofu" value="" />
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
			sum();
			var confirmEmail = $("input[name=email]").val();
			if(!confirmEmail){
				return;
			}
			if(!payTypeId){
				return 
			}
			var hotelId = $("input[name=hotelId]").val();
			if(!hotelId){
				return;
			}
			var checkIn = $("input[name=checkIn]").val();
			if(!checkIn){
				return;
			}
			var checkOut = $("input[name=checkOut]").val();
			if(!checkOut){
				return;
			}
			var roomInfo = $("input[name=roomInfo]").val();
			if(!roomInfo){
				return;
			}
			var hotelRoomTypeId = $("input[name=hotelRoomTypeId]").val();
			if(!hotelRoomTypeId){
				return;
			}
			var totalYufu = $("input[name=totalYufu]").val();
			if(!totalYufu.trim()){
				return;
			}
			var totalDaofu = $("input[name=totalDaofu]").val();
			if(!totalDaofu.trim()){
				return;
			}
			var bookCurrency = $("input[name=bookCurrency]").val();
			if(!bookCurrency.trim()){
				return;
			}
			var hotelBookRoomInfosStr = $("input[name=hotelBookRoomInfosStr]").val();
			if(!hotelBookRoomInfosStr){
				return;
			}
			var data = {
					hotelId : hotelId,
					checkIn : checkIn,
					checkOut : checkOut,
					hotelBookRoomInfosStr : hotelBookRoomInfosStr,
					roomInfo : roomInfo,
					hotelRoomTypeId : hotelRoomTypeId,
					confirmEmail : confirmEmail,
					payTypeId : payTypeId,
					totalDaofu : totalDaofu,
					totalYufu : totalYufu,
					bookCurrency : bookCurrency
			};
			openLoading();
			$.ajax({
			    type: 'get',
			    url: '${ctx}hotelPrepay',
			    data: data,
			    success: function(ret) {
			    	if(ret.code==0){
			    		$("input[name=orderId]").val(ret.result.orderId);
			    		$("#topay .mon").html('${holtelDetPrice.currencySymbol}'+$(".yufutt").html());
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
		function sum(){
			var sum=0;
			var daofuSum=0;
			var hotelBookRoomInfos=[];
			$(".room").each(function(i,e){
				var hotelBookRoomInfo={};
				var suppIds=[];
				var roomId=$(this).attr('roomId');
				var occuId=$(this).find("select[name=bedding]").val();
				var occuPrice=Number($(this).find(".colu[occId='"+occuId+"']").attr("occPrice"));
				var daofuPrice=0;
				$(this).find("input[name=bob]").each(function(i,e){
					if($(e).prop("checked")){
						occuPrice+=Number($(e).attr("bobPrice"));
						hotelBookRoomInfo.bbId=$(e).val();
					}
				});
				$(this).find("input[name=supp]").each(function(i,e){
					if($(e).prop("checked")){
						suppIds.push($(e).val());
						if($(e).attr("suppChargeType")==2){
							occuPrice+=Number($(e).attr("suppPrice"));
						}else if($(e).attr("suppChargeType")==3){
							daofuPrice+=Number($(e).attr("suppPrice"));
						}
					}
				});
				$(".roomSum[roomId="+roomId+"] .occup[occId='"+occuId+"'] .yufu").html(occuPrice.toFixed(2));
				$(".roomSum[roomId="+roomId+"] .occup[occId='"+occuId+"'] .daofu").html(daofuPrice.toFixed(2));
				sum+=occuPrice;
				daofuSum+=daofuPrice;

				hotelBookRoomInfo.roomId=roomId;
				hotelBookRoomInfo.occuId=occuId;
				hotelBookRoomInfo.yufu=(Number(occuPrice)*100).toFixed(0);
				hotelBookRoomInfo.daofu=(Number(daofuPrice)*100).toFixed(0);
				hotelBookRoomInfo.suppIds=suppIds;
				hotelBookRoomInfo.firstname=$(this).find("input[name=firstname]").val();
				hotelBookRoomInfo.lastname=$(this).find("input[name=lastname]").val();
				hotelBookRoomInfo.phonenumber="("+$(this).find("select[name=ccode]").val()+")"+$(this).find("input[name=phone]").val();
				hotelBookRoomInfos.push(hotelBookRoomInfo);
			});
			$("input[name=hotelBookRoomInfosStr]").val(stringToHex(JSON.stringify(hotelBookRoomInfos)));
			
			$(".yufutt").html(sum.toFixed(2));
			$("input[name=totalYufu]").val((Number(sum)*100).toFixed(0));
			$(".daofutt").html(daofuSum.toFixed(2));
			$("input[name=totalDaofu]").val((Number(daofuSum)*100).toFixed(0));
		}
		$(document).ready(function(){
			var mytotalstar=5;
			$(".stars").each(function(index, element) {
		       var classs=$(this).get(0).className;
			   var classArr=String(classs).split(" ");
			   var starNum=0,i;
			   var myhalf=false;
			   for( i=0;i<classArr.length;i++){
				  	if(classArr[i].indexOf("stars")>0){
					     if(classArr[i].indexOf(".")>0)myhalf=true;
						starNum=parseInt(classArr[i]);
					     break;
					  }
				   }
				var myhtml="";
				var myemptyNum=mytotalstar-starNum;
				for(i=0;i<starNum;i++){
					myhtml+='<em class="full"></em>';
					}
				if(starNum){
					myhtml+='<em class="half"></em>';
					myemptyNum--;
					}
				for(i=0;i<myemptyNum;i++){
					myhtml+='<em class="empty"></em>';
					}
				$(this).html(myhtml);
			});
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
			    }
			});
			$("select[name=bedding]").change(function(){
				var roomId=$(this).closest(".room").attr("roomId");
				var occuId=$(this).val();
				$(".room[roomId="+roomId+"] .colu").hide();
				$(".room[roomId="+roomId+"] .colu[occId='"+occuId+"']").show();
				$(".roomSum[roomId="+roomId+"] .occup").hide();
				$(".roomSum[roomId="+roomId+"] .occup[occId='"+occuId+"']").show();
				sum();
			});
			$("input[name=bob]").change(function(){
				var roomId=$(this).closest(".room").attr("roomId");
				var occuId=$(this).closest(".colu").attr("occId");
				var bbId=$(this).val();
				if($(this).prop('checked')){
					$(".roomSum[roomId="+roomId+"] .occup[occId='"+occuId+"'] .bob[bbId="+bbId+"]").show();
				}else{
					$(".roomSum[roomId="+roomId+"] .occup[occId='"+occuId+"'] .bob[bbId="+bbId+"]").hide();
				}
				sum();
			});
			$("input[name=supp]").change(function(){
				var roomId=$(this).closest(".room").attr("roomId");
				var occuId=$(this).closest(".colu").attr("occId");
				var suppId=$(this).val();
				if($(this).prop('checked')){
					$(".roomSum[roomId="+roomId+"] .occup[occId='"+occuId+"'] .supp[suppId="+suppId+"]").show();
				}else{
					$(".roomSum[roomId="+roomId+"] .occup[occId='"+occuId+"'] .supp[suppId="+suppId+"]").hide();
				}
				sum();
			});
			sum();
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
				$('#paying').dialog("close");
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

