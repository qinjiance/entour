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
	href="${ctx}<fmt:message key="static.resources.host"/>/css/carIndex.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="1" />
	<%@include file="./common/header.jspf"%>

	<div class="bgbody">
		<div class="indexBody">
			<div class="easyui-panel" data-options="noheader:true,border:false">
				<div class="search">
					<div class="dialog">
						<h1>搜索租车</h1>
						<div class="formConta">
							<form action="">
								<div class="des">
									<p class="lab">取车地点</p>
									<input id="pickUp" type="text" name="pickUp" placeholder="机场、城市或地址" />
									<span class="icon"></span>
								</div>
								<div class="des">
									<p class="lab">还车地点</p>
									<input id="dropOff" type="text" name="dropOff" placeholder="与取车地点相同" />
									<span class="icon"></span>
								</div>
								<input id="pickUpLabel" type="hidden" name="pickUpLabel" />
								<input id="pickUpValue" type="hidden" name="pickUpValue" />
								<input id="dropOffLabel" type="hidden" name="dropOffLabel" />
								<input id="dropOffValue" type="hidden" name="dropOffValue" />
								<div class="ci">
									<p class="lab">取车日期</p>
									<input id="pickUpDate" type="text" name="pickUpDate"  
										class="easyui-datebox" data-options="onSelect: onSelectFrom,editable:false" />
									<span class="icon"></span>
								</div>
								<div class="sell1" >
									<p class="lab">取车时间</p>
									<select id="pickUpHour" name="pickUpHour">
										<%@include file="./common/hour.jspf"%>
									</select>
								</div>
								<div class="co">
									<p class="lab">还车日期</p>
									<input id="dropOffDate" type="text" name="dropOffDate" 
										class="easyui-datebox" data-options="onSelect: onSelectTo,editable:false" />
									<span class="icon"></span>
								</div>
								<div class="sell1" >
									<p class="lab">还车时间</p>
									<select id="dropOffHour" name="dropOffHour">
										<%@include file="./common/hour.jspf"%>
									</select>
								</div>
								<div class="  sell" >
									<p class="lab">驾驶者年龄</p>
									<select id="driverAge" name="driverAge">
										<%@include file="./common/driverage.jspf"%>
									</select>
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
	
	<!-- footer -->
	<%@include file="./common/footer.jspf"%>

	<!-- js scripts -->
	<%@include file="./common/scripts.jspf"%>
	
	<script type="text/javascript">
		function onSelectFrom(from){
			var c = $('#dropOffDate').datebox('calendar');
			c.calendar({validator: function(date){
				date=fmtDate(date);
				from=fmtDate(from);
				if (date>=from){return true;}
				else {return false;}
			}});
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
			$('#pickUpDate').datebox('setValue', now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
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
				if (date>=now){return true;}
				else {return false;}
			}});
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
				
				var href="${ctx}/searchCars?pickUpLabel="+$("#pickUpLabel").val()
						+"&pickUpValue="+$("#pickUpValue").val()
						+"&dropOffLabel="+$("#dropOffLabel").val()
						+"&dropOffValue="+$("#dropOffValue").val()
						+"&pickUpDate="+$('#pickUpDate').datebox('getValue')
						+"&dropOffDate="+$('#dropOffDate').datebox('getValue')
						+"&pickUpHour="+$("#pickUpHour").val()
						+"&dropOffHour="+$("#dropOffHour").val()
						+"&driverAge="+$("#driverAge").val();
				window.location.href=href;
				return false;
			});
			$("#pickUp,#dropOff").autocomplete("${ctx}/getCarDes",{
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
				  $("#"+$(this).attr("name")+"Label").val(row.label);
				  $("#"+$(this).attr("name")+"Value").val(row.value);//通过result函数可进对数据进行其他操作
			});
		});
	</script>
</body>
</html>

