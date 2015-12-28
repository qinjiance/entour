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
	href="${ctx}<fmt:message key="static.resources.host"/>/css/routes.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="0" />
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="loading">
			<img src="${ctx}<fmt:message key="static.resources.host"/>/img/loading.gif" alt="loading" />
			<h1>正在为您搜索酒店，请稍后。。。</h1>
		</div>
		<div class="xbody" style="display:none;">
				<div class="myleft">
					<div class="easyui-panel" data-options="width:270,border:true,title:'酒店星级'">
						<div class="choose">
							<p><a class="stt stt5 ${searchStars==5?'on':''}" href="javascript:changeStart(5,'stt5');">5星级(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt4 ${searchStars==4?'on':''}" href="javascript:changeStart(4,'stt4');">4星级(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt3 ${searchStars==3?'on':''}" href="javascript:changeStart(3,'stt3');">3星级(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt2 ${searchStars==2?'on':''}" href="javascript:changeStart(2,'stt2');">2星级(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt1 ${searchStars==1?'on':''}" href="javascript:changeStart(1,'stt1');">1星级(<span class="starsLevel">0</span>)</a></p>
					    </div>
					</div>
					<div class="easyui-panel" data-options="style:{margin:'10px 0'},width:270,border:true,title:'每晚价格'">
						<div class="choose">
							<p><a class="cpp cpp5 ${searchPrices==600?'on':''}" href="javascript:changePriceLev(600,'cpp5');">低于¥600(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp4 ${searchPrices==1200?'on':''}" href="javascript:changePriceLev(1200,'cpp4');">低于¥601~1200(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp3 ${searchPrices==1500?'on':''}" href="javascript:changePriceLev(1500,'cpp3');">¥1201~1500(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp2 ${searchPrices==2500?'on':''}" href="javascript:changePriceLev(2500,'cpp2');">¥1501~2500(<span class="prices">0</span>)</a></p>
							<p><a class="cpp cpp1 ${searchPrices==10000000?'on':''}" href="javascript:changePriceLev(10000000,'cpp1');">高于¥2501(<span class="prices">0</span>)</a></p>
					    </div>
					</div>
					<div class="easyui-panel" data-options="style:{margin:'10px 0'},width:270,border:true,title:'酒店类型'">
						<div class="choose">
							<p><a class="ccc ccc5 ${searchHotelCat=='deluxe'?'on':''}" href="javascript:changeCata('deluxe','ccc5');">豪华酒店(<span class="hotelCat">0</span>)</a></p>
							<p><a class="ccc ccc4 ${searchHotelCat=='first class'?'on':''}" href="javascript:changeCata('first class','ccc4');">高级酒店(<span class="hotelCat">0</span>)</a></p>
							<p><a class="ccc ccc3 ${searchHotelCat=='moderate'?'on':''}" href="javascript:changeCata('moderate','ccc3');">中级酒店(<span class="hotelCat">0</span>)</a></p>
							<p><a class="ccc ccc2 ${searchHotelCat=='department'?'on':''}" href="javascript:changeCata('department','ccc2');">公寓(<span class="hotelCat">0</span>)</a></p>
							<p><a class="ccc ccc1 ${searchHotelCat=='other'?'on':''}" href="javascript:changeCata('other','ccc1');">其他(<span class="hotelCat">0</span>)</a></p>
					    </div>
					</div>
				</div>
				<div class="myright">
					<div id="searchCond" class="easyui-panel" data-options="collapsible:true,collapsed:true,width:850,border:true,title:'目的地：${desLabel}，日期：${checkIn} - ${checkOut} 【更改搜索条件】'" style="padding:5px;">
						<div class="conditions">
							<div class="condition">
								<label>目的地</label>
								<div id="depart">
									<input id="des" class="easyui-textbox" type="text" placeholder="目的地、机场、火车站、地标或具体地址" value="${desLabel}" style="width:600px;" />
								</div>
						    </div>
						    <div class="condition">
								<label>入住信息</label>
								<div id="vi">
									<span>入住</span>
									<input id="checkIn" type="text" value="${checkIn}"
										class="easyui-datebox" data-options="onSelect: onSelectFrom,editable:false" />
									 |
									<span>退房</span>
									<input id="checkOut" type="text"  value="${checkOut}"
										class="easyui-datebox" data-options="onSelect: onSelectTo,editable:false" />
									|
									<span>房间</span>
									<select name="roomNum">
										<option selected="selected" value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</div>
						    </div>
						    <div class="selCo">
							    <div class="selRoo condition">
									<label>客房 1</label>
									<div  class="selDiv">
										<span>成人(18+)</span>
										<select name="adultNum">
											<option value="1">1</option>
											<option selected="selected" value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
										</select>
										|
										<span>儿童(0-17)</span>
										<select name="chilNum">
											<option selected="selected" value="0">0</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
										</select>
									</div>
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
	
	<div id="selClone" class="selRooMore condition" style="display:none;">
		<label class="ind">客房1</label>
		<div class="selDiv">
			<span>成人(18+)</span>
			<select name="adultNum">
				<option value="1">1</option>
				<option selected="selected" value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
			</select>
			|
			<span>儿童(0-17)</span>
			<select name="chilNum">
				<option selected="selected" value="0">0</option>
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
			</select>
		</div>
    </div>
    <span id="ageClone" class="ageDiv" style="display: none;">
    	|
    	<span class="lab">儿童年龄</span>
		<select name="chilAge">
			<option selected="selected" value="1">&lt;=1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
		</select>
    </span>
	
	<div id="noHotel" style="display: none;"><span class="label">暂无路线，敬请期待...</span></div>
	
	<div id="hotelTemplate" style="display: none;">
		<div class="bref">
			<div class="small"><a class="hotelDet" href="${ctx}/" target="_blank">
				<img class="thumbImg" src="" alt="Picture" /></a></div>
			<div class="content">
				<a class="label hotelDet" href="${ctx}/" target="_blank"><span class="hotelName"></span><span class="starLevel"></span></a>
				<p class="cat"></p>
				<p class="brandName"></p>
				<p class="address"></p>
				<p class="roomTypes"></p>
				<p class="supplements"></p>
				<p class="boardbases"></p>
			</div>
			<div class="price">
				<p><em class="currency"></em><span class="display"></span><em>起</em></p>
				<a class="hotelDet" href="${ctx}/" target="_blank"><p class="detail">立即预定</p></a>
			</div>
		</div>
	</div>
	
	<form id="getHotels" action="${ctx}/searchHotels">
		<input type="hidden" name="desLabel" value="${desLabel}" />
		<input type="hidden" name="desValue" value="${desValue}" />
		<input type="hidden" name="checkIn" value="${checkIn}" />
		<input type="hidden" name="checkOut" value="${checkOut}" />
		<input type="hidden" name="roomInfo" value="${roomInfo}" />
		<input type="hidden" name="searchStars" value="${searchStars}" />
		<input type="hidden" name="searchPrices" value="${searchPrices}" />
		<input type="hidden" name="searchHotelCat" value="${searchHotelCat}" />
	</form>
	
	<!-- footer -->
	<%@include file="../common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="../common/scripts.jspf"%>
	
	<script type="text/javascript">
	function changeStart(val,e){
		$("input[name=searchStars]").val(val);
		$('.stt').removeClass("on");
		$("."+e).addClass("on");
		getHotels();
	}
	function changePriceLev(val,e){
		$("input[name=searchPrices]").val(val);
		$('.cpp').removeClass("on");
		$("."+e).addClass("on");
		getHotels();
	}
	function changeCata(val,e){
		$("input[name=searchHotelCat]").val(val);
		$('.ccc').removeClass("on");
		$("."+e).addClass("on");
		getHotels();
	}
	function getRoomInfo(){
		var roomInfo=$(".selRoo select[name=adultNum] option:checked").val()+"-"
		+$(".selRoo select[name=chilNum] option:checked").val();
		$(".selRoo select[name=chilAge]").each(function(i,e){
			roomInfo+="-"+$(e).find("option:checked").val();
		});
		$("div.conditions .selRooMore").each(function(i,e){
			roomInfo+=","+$(e).find("select[name=adultNum] option:checked").val()+"-"
			+$(e).find("select[name=chilNum] option:checked").val();
			$(e).find("select[name=chilAge]").each(function(i,e){
				roomInfo+="-"+$(e).find("option:checked").val();
			});
		});
		//console.log(roomInfo);
		return roomInfo;
	}
	function initRoomInfo(){
		var roomInfo=$("input[name='roomInfo']").val();
		if(roomInfo){
			var infos=roomInfo.split(",");
			$("select[name=roomNum]").val(infos.length).change();
			var rooms=$(".selRoo,.selRooMore");
			$.each(infos,function(i,e){
				var cons=e.split("-");
				var room=$(rooms[i]);
				if (cons.length >= 2) {
					room.find("select[name=adultNum]").val(cons[0]).change();
					room.find("select[name=chilNum]").val(cons[1]).change();
					if (cons.length > 2 && cons[1] > 0) {
						var ages=room.find(".ageDiv");
						for (var j = 2; j < cons.length; j++) {
							$(ages[j-2]).find("select[name=chilAge]").val(cons[j]).change();
						}
					}
				}
			});
		}
	}
	function getHotels(){
		$("input[name='roomInfo']").val(getRoomInfo());
		$(".loading").show();
		$(".xbody").hide();
		$.ajax({
		    type: 'get',
		    url: '${ctx}/getHotels',
		    dataType: 'json',
		    data: $("#getHotels").serialize(),
		    success: function(ret) {
		    	if(ret.code==0){
					var rets=ret.result;
					var hotels;
					var stars;
					var prices;
					var hotelCats;
					if(rets){
						hotels=ret.result.hotelvos;
						stars=ret.result.starsCata;
						prices=ret.result.pricesCata;
						hotelCats=ret.result.hotelTypeCata;
					}
					var div=$(".routeList");
					div.empty();
					if(!rets||!hotels||hotels.length<=0){
						div.html($("#noHotel").clone(true).show());
					}else{
						$.each(hotels,function(i,e){
							var hotelDiv=$("#hotelTemplate").clone(true);
							hotelDiv.attr("id","");
							hotelDiv.find(".thumbImg").attr("src",e.thumb);
							hotelDiv.find(".hotelDet").attr("href","${ctx}/hotelDet?hotelId="
									+e.hotelId+"&checkIn="+$("input[name=checkIn]").val()
									+"&checkOut="+$("input[name=checkOut]").val()
									+"&roomInfo="+$("input[name=roomInfo]").val());
							hotelDiv.find(".hotelName").html(e.hotelName);
							hotelDiv.find(".starLevel").addClass(e.starsLevel+"stars");
							hotelDiv.find(".brandName").html(e.brandName);
							hotelDiv.find(".address").html(e.address);
							hotelDiv.find(".roomTypes").html(e.roomTypes);
							hotelDiv.find(".supplements").html(e.supplements);
							hotelDiv.find(".boardbases").html(e.boardbases);
							hotelDiv.find(".currency").html(e.currency);
							hotelDiv.find(".cat").html(e.hotelCatalog);
							hotelDiv.find(".price .display").html(e.minAverPublishPrice);
							hotelDiv.show();
							div.append(hotelDiv);
						});
						$(".starsLevel").each(function(i,e){
							$(e).html(stars[4-i]);
						});
						$(".prices").each(function(i,e){
							$(e).html(prices[4-i]);
						});
						$(".hotelCat").each(function(i,e){
							$(e).html(hotelCats[4-i]);
						});
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
		var c = $('#checkOut').datebox('calendar');
		c.calendar({validator: function(date){
			date=fmtDate(date);
			from=fmtDate(from);
			if (date>from){return true;}
			else {return false;}
		}});
		$("input[name=checkIn]").val($('#checkIn').datebox('getValue'));
	}
	function onSelectTo(to){
		var c = $('#checkIn').datebox('calendar');
		c.calendar({validator: function(date){
			var now=fmtDate(new Date());
			date=fmtDate(date);
			to=fmtDate(to);
			if (date>=now&&date<to){return true;}
			else {return false;}
		}});
		$("input[name=checkOut]").val($('#checkOut').datebox('getValue'));
	}
	function fmtDate(date){
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		date.setMilliseconds(0);
		return date;
	}
	$(document).ready(function(){
		$("select[name=chilNum]").change(function(){
			var value=$(this).find("option:checked").val();
			if(value=='0'){
				$(this).closest(".selDiv").find(".ageDiv").remove();
			}
			var rooMList=$(this).closest(".selDiv").find(".ageDiv");
			var size=0;
			if(rooMList){
				size=rooMList.length;
			}
			if(value>size){
				for(var i=size;i<value;i++){
					var it=$("#ageClone").clone(true).show().attr("id","");
					it.find(".lab").html("儿童 "+(i+1)+"年龄");
					$(this).closest(".selDiv").append(it);
				}
			}else{
				rooMList.each(function(i,e){
					if(i>=value){
						$(e).remove();
					}
				});
			}
		});
		$("select[name=roomNum]").change(function(){
			var value=$(this).find("option:checked").val();
			var rooMList=$("div.conditions .selRooMore");
			var size=0;
			if(rooMList){
				size=rooMList.length;
			}
			if(value>size){
				for(var i=size+1;i<value;i++){
					var it = $("#selClone").clone(true).show().attr("id","");
					it.find(".ind").html("客房 "+(i+1));
					$(".selCo").append(it);
				}
			}else{
				rooMList.each(function(i,e){
					if(i>=(value-1)){
						$(e).remove();
					}
				});
			}
		});
		
		initRoomInfo();
		
		getHotels();
		
		$("select").change(function(){
			$("input[name="+$(this).attr('name')+"]").val($(this).val());
		});
		var now=fmtDate(new Date());
		var from=fmtDate(new Date());
		var to=fmtDate(new Date());
		to.setDate(now.getDate()+2);
		if('${checkIn}'){
			from = new Date('${checkIn}'.replace(/-/g,"/"));
		}
		if('${checkOut}'){
		    to = new Date('${checkOut}'.replace(/-/g,"/"));
		}
		$('#checkIn').datebox('setValue', from.getFullYear()+"-"+(from.getMonth()+1)+"-"+from.getDate());
		$('#checkOut').datebox('setValue', to.getFullYear()+"-"+(to.getMonth()+1)+"-"+to.getDate());
		var ci = $('#checkIn').datebox('calendar');
		ci.calendar({validator: function(date){
			date=fmtDate(date);
			if (date>=now&&date<to){return true;}
			else {return false;}
		}});
		var co = $('#checkOut').datebox('calendar');
		co.calendar({validator: function(date){
			date=fmtDate(date);
			if (date>from){return true;}
			else {return false;}
		}});
		$("#des").autocomplete("/getDes",{
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
			  $("input[name=desLabel]").val(row.label);
			  $("input[name=desValue]").val(row.value);//通过result函数可进对数据进行其他操作
		});
		
		$('#submitSearch').click(function(){
			$("input[name='roomInfo']").val(getRoomInfo());
			$("#getHotels").submit();
		});
	});
	</script>
</body>
</html>

