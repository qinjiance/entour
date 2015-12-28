<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 租车</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/cars.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="1" />
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="loading">
			<img src="${ctx}<fmt:message key="static.resources.host"/>/img/loading.gif" alt="loading" />
			<h1>正在为您搜索租车，请稍后。。。</h1>
		</div>
		<div class="xbody" style="display:none;">
				<div class="myleft">
					<div class="easyui-panel" data-options="width:270,border:true,title:'车型分类'">
						<div class="choose">
							<p><a class="stt stt4 ${searchType=='Car'?'on':''}" href="javascript:changeType('Car','stt4');">轿车(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt3 ${searchType=='SUV'?'on':''}" href="javascript:changeType('SUV','stt3');">SUV(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt2 ${searchType=='Van'?'on':''}" href="javascript:changeType('Van','stt2');">MPV(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt1 ${searchType=='Convertible'?'on':''}" href="javascript:changeType('Convertible','stt1');">跑车/敞篷车(<span class="starsLevel">0</span>)</a></p>
					    </div>
					</div>
					<div class="easyui-panel" data-options="style:{margin:'10px 0'},width:270,border:true,title:'车款大小'">
						<div class="choose">
							<p><a class="cpp cpp7 ${searchCat=='Mini'?'on':''}" href="javascript:changeCat('Mini','cpp7');">小型(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp6 ${searchCat=='Compact'?'on':''}" href="javascript:changeCat('Compact','cpp6');">紧凑型(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp5 ${searchCat=='Economy'?'on':''}" href="javascript:changeCat('Economy','cpp5');">经济型(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp4 ${searchCat=='Standard'?'on':''}" href="javascript:changeCat('Standard','cpp4');">标准型(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp3 ${searchCat=='Full Size'?'on':''}" href="javascript:changeCat('Full Size','cpp3');">全尺寸型(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp2 ${searchCat=='Luxury'?'on':''}" href="javascript:changeCat('Luxury','cpp2');">豪华型(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp1 ${searchCat=='Premium'?'on':''}" href="javascript:changeCat('Premium','cpp1');">中大型(<span class="prices">0</span>)</a></p>
					    </div>
					</div>
					<div class="easyui-panel" data-options="style:{margin:'10px 0'},width:270,border:true,title:'租车公司'">
						<div id="comp" class="choose">
							
					    </div>
					</div>
				</div>
				<p id="companyP" style="display:none;"><a class="ccc" href="">(<span class="hotelCat">0</span>)</a></p>
				<div class="myright">
					<div id="searchCond" class="easyui-panel" data-options="collapsible:true,collapsed:true,width:850,border:true,title:'取车：${pickUpStation}，还车：${dropOffStation}，日期：${pickUpDate} - ${dropOffDate} 【更改搜索条件】'" style="padding:5px;">
						<div class="conditions">
							<div class="condition">
								<label>地点</label>
								<div id="depart">
									<span>取车</span>
									<input id="pickUp" name="pickUp" type="text" class="easyui-textbox" placeholder="机场、城市或地址" value="${pickUpLabel}" style="width:270px;" />
									<span>还车</span>
									<input id="dropOff" name="dropOff" type="text" class="easyui-textbox" placeholder="与取车地点相同" value="${dropOffLabel}" style="width:270px;" />
								</div>
						    </div>
						    <div class="condition">
								<label>时间</label>
								<div id="vi">
									<span>取车</span>
									<input id="pickUpDate" type="text" value="${pickUpDate}"
										class="easyui-datebox" data-options="onSelect: onSelectFrom,editable:false" />
									<select name="pickUpHour">
										<%@include file="../common/hour.jspf"%>
									</select>
									 |
									<span>还车</span>
									<input id="dropOffDate" type="text"  value="${dropOffDate}"
										class="easyui-datebox" data-options="onSelect: onSelectTo,editable:false" />
									<select name="dropOffHour">
										<%@include file="../common/hour.jspf"%>
									</select>
									 |
									<span>驾驶者年龄</span>
									<select name="driverAge">
										<%@include file="../common/driverage.jspf"%>
									</select>
								</div>
						    </div>
						    <div class="buttons">
								<a id="submitSearch" href="javascript:void(0)" class="easyui-linkbutton">重新搜索</a>
						    </div>
					    </div>
					</div>
					<div class="easyui-panel" data-options="width:850,noheader:true,border:true" style="padding:5px;margin-top: 10px;">
						<div class="routeList">
								
						</div>
					</div>
				</div>
		</div>
	</div>
	
	<div id="noHotel" style="display: none;"><span class="label">暂无路线，敬请期待...</span></div>
	
	<div id="carTemp" style="display: none;">
		<div class="bref">
			<div class="small"><a class="carDet" href="${ctx}/" target="_blank">
				<img class="thumbImg" src="" alt="Picture" /></a></div>
			<div class="content">
				<a class="label carDet" href="${ctx}/" target="_blank"><span class="hotelName"></span></a>
				<p class="brandName" style="color:#ffcb00"><span>租车公司: </span></p>
				<p class="cat"><span>车型分类: </span></p>
				<p class="address"><span>车款大小: </span></p>
				<div class="icons">
					<div class="icon pax" title="乘客">
						<p class="ico"></p>
						<p class="tex"></p>
					</div>
					<div class="icon lug" title="行李">
						<p class="ico"></p>
						<p class="tex"></p>
					</div>
					<div class="icon door" title="车门">
						<p class="ico"></p>
						<p class="tex"></p>
					</div>
					<div class="icon ac" title="空调">
						<p class="ico"></p>
						<p class="tex"></p>
					</div>
					<div class="icon tran" title="变速箱">
						<p class="ico"></p>
						<p class="tex"></p>
					</div>
				</div>
			</div>
			<div class="price">
				<p><em class="currency"></em><span class="display"></span><em>起</em></p>
				<a class="carDet" href="${ctx}/" target="_blank"><p class="detail">立即预定</p></a>
			</div>
		</div>
	</div>
	
	<form id="getCars" action="${ctx}/searchCars">
		<input type="hidden" name="pickUpLabel" value="${pickUpLabel}" />
		<input type="hidden" name="pickUpValue" value="${pickUpValue}" />
		<input type="hidden" name="dropOffLabel" value="${dropOffLabel}" />
		<input type="hidden" name="dropOffValue" value="${dropOffValue}" />
		<input type="hidden" name="pickUpDate" value="${pickUpDate}" />
		<input type="hidden" name="dropOffDate" value="${dropOffDate}" />
		<input type="hidden" name="pickUpHour" value="${pickUpHour}" />
		<input type="hidden" name="dropOffHour" value="${dropOffHour}" />
		<input type="hidden" name="driverAge" value="${driverAge}" />
		<input type="hidden" name="searchType" value="${searchType}" />
		<input type="hidden" name="searchClass" value="${searchClass}" />
		<input type="hidden" name="searchCompany" value="${searchCompany}" />
	</form>
	
	<!-- footer -->
	<%@include file="../common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="../common/scripts.jspf"%>
	
	<script type="text/javascript">
	function changeType(val,e){
		$("input[name=searchType]").val(val);
		$('.stt').removeClass("on");
		$("."+e).addClass("on");
		getCars();
	}
	function changeCat(val,e){
		$("input[name=searchClass]").val(val);
		$('.cpp').removeClass("on");
		$("."+e).addClass("on");
		getCars();
	}
	function changeCata(val,e){
		$("input[name=searchCompany]").val(val);
		$('.ccc').removeClass("on");
		$("."+e).addClass("on");
		getCars();
	}
	function initSelect(){
		var pickUpHour=$("input[name='pickUpHour']").val();
		var dropOffHour=$("input[name='dropOffHour']").val();
		var driverAge=$("input[name='driverAge']").val();
		$("select[name=pickUpHour]").val(pickUpHour).change();
		$("select[name=dropOffHour]").val(dropOffHour).change();
		$("select[name=driverAge]").val(driverAge).change();
	}
	function getCars(){
		$(".loading").show();
		$(".xbody").hide();
		$.ajax({
		    type: 'get',
		    url: '${ctx}/getCars',
		    dataType: 'json',
		    data: $("#getCars").serialize(),
		    success: function(ret) {
		    	if(ret.code==0){
					var rets=ret.result;
					var cars;
					var typesCata;
					var catalogCata;
					var companyCata;
					if(rets){
						typesCata=ret.result.typesCata;
						catalogCata=ret.result.catalogCata;
						companyCata=ret.result.companyCata;
						cars=ret.result.carVos;
					}
					var div=$(".routeList");
					div.empty();
					if(!rets||!cars||cars.length<=0){
						div.html($("#noHotel").clone(true).show());
					}else{
						$.each(cars,function(i,e){
							var carDiv=$("#carTemp").clone(true);
							carDiv.attr("id","");
							carDiv.find(".thumbImg").attr("src",e.thumb);
							carDiv.find(".carDet").attr("href","${ctx}/carDet?productId="+encodeURIComponent(e.productId)
									+"&pickUpStationId="+e.pickUpStationId
									+"&dropOffStationId="+e.dropOffStationId  
									+"&pickUpDate="+$("input[name=pickUpDate]").val()
									+"&pickUpHour="+$("input[name=pickUpHour]").val()
									+"&dropOffDate="+$("input[name=dropOffDate]").val()
									+"&dropOffHour="+$("input[name=dropOffHour]").val()
									+"&driverAge="+$("input[name=driverAge]").val());
							carDiv.find(".hotelName").html(e.carName);
							carDiv.find(".brandName").append(e.carCompanyName);
							carDiv.find(".address").append(e.carClass);
							carDiv.find(".cat").append(e.carType);
							carDiv.find(".currency").html(e.currency);
							carDiv.find(".price .display").html(e.minPrice);
							carDiv.find(".icons .icon.pax .tex").html(e.maxPassengers);
							carDiv.find(".icons .icon.lug .tex").html(e.luggageLarge+e.luggageSmall);
							carDiv.find(".icons .icon.door .tex").html(e.doors);
							carDiv.find(".icons .icon.tran .tex").html(e.transmission);
							carDiv.find(".icons .icon.ac .tex").html((e.ac==true)?'AC':'No AC');
							carDiv.show();
							div.append(carDiv);
						});
						$(".starsLevel").each(function(i,e){
							$(e).html(typesCata[3-i]);
						});
						$(".prices").each(function(i,e){
							$(e).html(catalogCata[6-i]);
						});
						$("#comp").empty();
						for(var key in companyCata){  
							var p = $("#companyP").clone(true);
							p.attr("id","");
							p.find("a").addClass("ccc"+key);
							p.find("a").attr("href","javascript:changeCata('"+key+"','ccc"+key+"');");
							p.find("a").prepend(key);
							p.find(".hotelCat").html(companyCata[key]);
							if($("input[name=searchCompany]").val()== key){
								p.find("a").addClass("on");
							}
							p.show();
							$("#comp").append(p);  
						}  
					}
				}else{
					$.messager.show({
						title:'消息提示',
						msg:ret.message,
						timeout:1500,
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
		    	$("#searchCond").panel('collapse');
		    	$(".loading").hide();
		    	$(".xbody").show();
		    }
		});
	}
	function onSelectFrom(from){
		var c = $('#dropOffDate').datebox('calendar');
		c.calendar({validator: function(date){
			date=fmtDate(date);
			from=fmtDate(from);
			if (date>=from){return true;}
			else {return false;}
		}});
		$("input[name=pickUpDate]").val($('#pickUpDate').datebox('getValue'));
	}
	function onSelectTo(to){
		var c = $('#pickUpDate').datebox('calendar');
		c.calendar({validator: function(date){
			var now=fmtDate(new Date());
			date=fmtDate(date);
			to=fmtDate(to);
			if (date>=now&&date<=to){return true;}
			else {return false;}
		}});
		$("input[name=dropOffDate]").val($('#dropOffDate').datebox('getValue'));
	}
	function fmtDate(date){
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		date.setMilliseconds(0);
		return date;
	}
	$(document).ready(function(){
		initSelect();
		
		getCars();
		
		$("select").change(function(){
			$("input[name="+$(this).attr('name')+"]").val($(this).val());
		});
		var now=fmtDate(new Date());
		var from=fmtDate(new Date());
		var to=fmtDate(new Date());
		to.setDate(now.getDate()+2);
		if('${pickUpDate}'){
			from = new Date('${pickUpDate}'.replace(/-/g,"/"));
		}
		if('${dropOffDate}'){
		    to = new Date('${dropOffDate}'.replace(/-/g,"/"));
		}
		$('#pickUpDate').datebox('setValue', from.getFullYear()+"-"+(from.getMonth()+1)+"-"+from.getDate());
		$('#dropOffDate').datebox('setValue', to.getFullYear()+"-"+(to.getMonth()+1)+"-"+to.getDate());
		var ci = $('#pickUpDate').datebox('calendar');
		ci.calendar({validator: function(date){
			date=fmtDate(date);
			if (date>=now&&date<=to){return true;}
			else {return false;}
		}});
		var co = $('#dropOffDate').datebox('calendar');
		co.calendar({validator: function(date){
			date=fmtDate(date);
			if (date>=from){return true;}
			else {return false;}
		}});
		$("#pickUp,#dropOff").autocomplete("/getCarDes",{
			minChars: 2,
			max: 10,
			autoFill: false,
			mustMatch: false,
			matchContains: true,
			selectFirst: true,
			cacheLength:100,
			matchSubset:false,
            dataType:"json",
			delay:400,
			parse: function(data) {  
			     var rows = [];  
			     if(!data){
			    	 return rows;
			     }
			     for(var i=0; i<data.length; i++){  
			      rows[rows.length] = {   
			        data:data[i],   
			        value:data[i].label,   
			        result:data[i].label   
			        };   
			      }  
			   return rows;  
			},  
			formatItem: function(row, i, total) {
				return row.label;
			}
		}).result(function(event,row,formatted){
			  $("input[name="+$(this).attr("name")+"Label]").val(row.label);
			  $("input[name="+$(this).attr("name")+"Value]").val(row.value);//通过result函数可进对数据进行其他操作
		});
		
		$('#submitSearch').click(function(){
			$("#getCars").submit();
		});
	});
	</script>
</body>
</html>

