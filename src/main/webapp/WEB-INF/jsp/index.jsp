<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="./common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:wb="http://open.weibo.com/wb">
<head>
<title><fmt:message key="webapp.title" /> - 首页</title>
<!-- meta -->
<%@include file="./common/meta.jspf"%>
<!-- css links -->
<%@include file="./common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/index.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="0" />
	<%@include file="./common/header.jspf"%>

	<div class="bgbody">
		<div class="indexBody">
			<div class="easyui-panel" data-options="noheader:true,border:false">
				<div class="search">
					<div class="dialog">
						<h1>搜索酒店</h1>
						<div class="formConta">
							<form action="">
								<div class="des">
									<p class="lab">前往</p>
									<input id="des" type="text" name="des" placeholder="目的地、机场、火车站、地标或具体地址" />
									<span class="icon"></span>
								</div>
								<input id="desLabel" type="hidden" name="desLabel" />
								<input id="desValue" type="hidden" name="desValue" />
								<div class="ci">
									<p class="lab">入住</p>
									<input id="checkIn" type="text" name="checkIn"  
										class="easyui-datebox" data-options="onSelect: onSelectFrom,editable:false" />
									<span class="icon"></span>
								</div>
								<div class="co">
									<p class="lab">退房</p>
									<input id="checkOut" type="text" name="checkOut" 
										class="easyui-datebox" data-options="onSelect: onSelectTo,editable:false" />
									<span class="icon"></span>
								</div>
								<div class="sell1" >
									<p class="lab">房间</p>
									<select name="roomNum">
										<option selected="selected" value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
									</select>
								</div>
								<div class="selCo">
									<div class="selRoo selDiv">
										<div class="selltil  sell" >
											客房 1
										</div>
										<div class="  sell" >
											<p class="lab">成人(18+)</p>
											<select name="adultNum">
												<option value="1">1</option>
												<option selected="selected" value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
											</select>
										</div>
										<div class="  sell">
											<p class="lab">儿童(0-17)</p>
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
								<div class="btn" style="clear:both;">
									<button type="submit"><span class="btn-label">搜索</span></button>
								</div>
							</form>
						</div>
					</div>
			    </div>
			</div>
		</div>
	</div>
	
	<div id="selClone" class="selRooMore selDiv" style="display:none;">
		<div class="ind selltil sell" >
			客房
		</div>
		<div class="sell" >
			<p class="lab">成人(18+)</p>
			<select name="adultNum">
				<option value="1">1</option>
				<option selected="selected" value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
			</select>
		</div>
		<div class=" sell">
			<p class="lab">儿童(0-17)</p>
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
	<div id="ageClone" class="ageDiv  sell" style="display: none;">
		<p class="lab">儿童年龄</p>
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
	</div>
		
	<!-- footer -->
	<%@include file="./common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="./common/scripts.jspf"%>
	
	<script type="text/javascript">
		function onSelectFrom(from){
			var c = $('#checkOut').datebox('calendar');
			c.calendar({validator: function(date){
				date=fmtDate(date);
				from=fmtDate(from);
				if (date>from){return true;}
				else {return false;}
			}});
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
		}
		function fmtDate(date){
			date.setHours(0);
			date.setMinutes(0);
			date.setSeconds(0);
			date.setMilliseconds(0);
			return date;
		}
		$(document).ready(function(){
			var now=fmtDate(new Date());
			var to=fmtDate(new Date());
			to.setDate(now.getDate()+2);
			$('#checkIn').datebox('setValue', now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
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
				if (date>now){return true;}
				else {return false;}
			}});
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
				var rooMList=$("div.formConta .selRooMore");
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
			$("button[type=submit]").click(function(){
				var roomInfo=$(".selRoo select[name=adultNum] option:checked").val()+"-"
					+$(".selRoo select[name=chilNum] option:checked").val();
				$(".selRoo select[name=chilAge]").each(function(i,e){
					roomInfo+="-"+$(e).find("option:checked").val();
				});
				$("div.formConta .selRooMore").each(function(i,e){
					roomInfo+=","+$(e).find("select[name=adultNum] option:checked").val()+"-"
					+$(e).find("select[name=chilNum] option:checked").val();
					$(e).find("select[name=chilAge]").each(function(i,e){
						roomInfo+="-"+$(e).find("option:checked").val();
					});
				});
				
				var href="${ctx}/searchHotels?desLabel="+$("#desLabel").val()
						+"&desValue="+$("#desValue").val()+"&checkIn="+$('#checkIn').datebox('getValue')
						+"&checkOut="+$('#checkOut').datebox('getValue')+"&roomInfo="+roomInfo;
				//console.log(href);
				window.location.href=href;
				return false;
			});
			$("#des").autocomplete("${ctx}/getDes",{
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
				  $("#desLabel").val(row.label);
				  $("#desValue").val(row.value);//通过result函数可进对数据进行其他操作
			});
		});
	</script>
</body>
</html>

