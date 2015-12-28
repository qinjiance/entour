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
	href="${ctx}<fmt:message key="static.resources.host"/>/css/carbook.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="1" />
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="xbody">
				<div class="myleft">
					<div class="easyui-panel" data-options="border:false" style="margin:0;padding:10px;background-color: #fff;">
						<div class="til">
							<p class="titt">驾驶者信息</p>
							<p class="tip">驾驶者年龄必须为21岁或以上。</p>
							<p class="tipp">联系人姓名只能使用英文。</p>
						</div>
						<hr />
						<div class="room">
							<div class="lab"><span>${carDet.carName}，</span>套餐:&nbsp;
								<select name="bedding">
								<c:forEach items="${carDet.carProgramVos}" var="prog" varStatus="occvs">
									<option ${(occvs.index==0)?'checked="checked"':''} value="${prog.id}" price="${prog.price}" daofu="${prog.mandatoryFees}">${prog.name}&nbsp;(${carDet.currency}${prog.price})</option>
								</c:forEach>
								</select>
							</div>
							<c:forEach items="${carDet.carProgramVos}" var="prog" varStatus="occvs">
								<div class="colu" progId="${prog.id}" style="${(occvs.index==0)?'':'display:none;'}">
									<c:forEach items="${prog.componentMap}" var="com">
										<p title="${com.value}">●&nbsp;${com.key}</p>
									</c:forEach>
								</div>
							</c:forEach>
							<p class="inp"><label for="firstname">驾驶者姓氏<span>*</span>&nbsp;</label><input placeholder="请输入英文姓名" id="firstname" name="firstname" class="easyui-validatebox" data-options="required:true,validType:'alphabet'" style="width:150px" /> 
								<label for="lastname">驾驶者名字<span>*</span>&nbsp;</label><input placeholder="请输入英文姓名" id="lastname" name="lastname" class="easyui-validatebox" data-options="required:true,validType:'alphabet'" style="width:150px" /></p>
							<p class="inp"><label for="phone">联系人手机<span>*</span>&nbsp;</label>
								<select name="ccode">
                                    <%@include file="../common/phonecountry.jspf"%>
								</select>
								-
								<input placeholder="方便租车公司与您联系" id="phone" name="phone" class="easyui-validatebox" data-options="required:true,validType:'number'" style="width:150px" />
							</p><p class="inp"><label for="driverAge">驾驶者年龄<span>*</span>&nbsp;</label>
								<select id="driverAge" name="driverAge">
                                    <%@include file="../common/driverage.jspf"%>
								</select>
							</p></p><p class="inp"><label for="flightNumber">航班号&nbsp;</label>
								<input placeholder="方便租车公司接机" id="flightNumber" name="flightNumber" class="easyui-validatebox" style="width:100px" />
							</p>
						</div> 
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
							<p class="titt">规则和限制</p>
						</div>
						<hr />
						<c:forEach items="${carDet.carRuleVos}" var="rule" varStatus="ruSt">
							<c:if test="${ruSt.index<10}">
								<div class="rul">
									<p class="titt">${rule.title}</p>
									<p class="tip">${rule.rule}</p>
								</div>
							</c:if>
							<c:if test="${ruSt.index>=10}">
								<div class="rul mor">
									<p class="titt">${rule.title}</p>
									<p class="tip">${rule.rule}</p>
								</div>
							</c:if>
						</c:forEach>
						<c:if test="${fn:length(carDet.carRuleVos) > 10}">
							<a class="morDi">查看所有∨</a>
						</c:if>
					</div>
				</div>
				<div class="myright">
					<div class="easyui-panel" data-options="border:false" style="margin:0 0 20px 0;padding:10px;background-color: inherit;">
						<div class="hot">
							<img class="compaImg" src="${carDet.carCompanyLogoUrl}" alt="${carDet.carCompanyName}" />
							<img class="carImg" src="${carDet.thumb}" alt="${carDet.carName}" />
							<p class="carname">${carDet.carName}</p>
							<p class="cartype">车型分类:&nbsp;${carDet.carType}</p>
						</div>
						<div class="htdes">
							<p><span>取车地点:&nbsp;</span>${carDet.pickUpStation.name}</p>
							<p><span>取车日期:&nbsp;</span>${pickUpDate}&nbsp;${pickUpHour}:00</p>
							<p><span>还车地点:&nbsp;</span>${carDet.dropOffStation.name}</p>
							<p><span>还车日期:&nbsp;</span>${dropOffDate}&nbsp;${dropOffHour}:00</p>
						</div>
						<div class="htrp roomSum">
							<c:forEach items="${carDet.carProgramVos}" var="prog" varStatus="occvs">
								<div class="occup" progId="${prog.id}" occPrice="${prog.price}" style="${(occvs.index==0)?'':'display:none;'}">
									<p class="pric"><span class="ite">${prog.name}</span><span class="iteprice">${carDet.currency}${prog.price}</span></p>
									<p class="pric"><span class="iteprice">(含税${carDet.currency}${prog.tax})</span></p>
									<p class="pric"><span class="ite">到店支付</span><span class="iteprice">${carDet.currency}${prog.mandatoryFees}</span></p>
								</div>
							</c:forEach>
						</div>
						<div class="htrp">
							<p class="pric"><span class="itett">预付总计</span><span class="itepricett ">${holtelDetPrice.currency}<span class="yufutt"> -</span></span></p>
							<p class="pric"><span class="itett">到付总计</span><span class="itepricett ">${holtelDetPrice.currency}<span class="daofutt"> -</span></span></p>
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
		function sum(){
			var sum=0;
			var daofuSum=0;
			sum+=Number($("select[name=bedding] option[checked=checked]").attr("price"));
			if($("select[name=bedding] option[checked=checked]").attr("daofu")){
				daofuSum+=Number($("select[name=bedding] option[checked=checked]").attr("daofu"));
			}
			$(".yufutt").html(sum.toFixed(2));
			$(".daofutt").html(daofuSum.toFixed(2));
		}
		var isClick=false;
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
			    }
			});
			$("select[name=bedding]").change(function(){
				var progId=$(this).val();
				$(".colu").hide();
				$(".colu[progId='"+progId+"']").show();
				$(".occup").hide();
				$(".occup[progId='"+progId+"']").show();
				sum();
			});
			sum();
			if($(".morDi")){
				$(".morDi").click(function(){
					if(isClick){
						isClick=false;
						$(".rul.mor").hide();
						$(this).html("查看所有∨");
					}else{
						isClick=true;
						$(".rul.mor").show();
						$(this).html("隐藏部分∧");
					}
				});
			}
		});
	</script>
</body>
</html>

