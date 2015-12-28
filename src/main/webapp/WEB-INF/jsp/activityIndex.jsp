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
	href="${ctx}<fmt:message key="static.resources.host"/>/css/activityIndex.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="2" />
	<%@include file="./common/header.jspf"%>

	<div class="bgbody">
		<div class="indexBody">
			<div class="easyui-panel" data-options="noheader:true,border:false">
				<div class="search">
					<div class="dialog">
						<h1>搜索游览观光活动</h1>
						<div class="formConta">
							<form action="">
								<div class="des">
									<p class="lab">目的地</p>
									<input id="des" type="text" name="des" placeholder="城市" />
									<span class="icon"></span>
								</div>
								<input id="desLabel" type="hidden" name="desLabel" />
								<input id="desValue" type="hidden" name="desValue" />
								<div class="ci">
									<p class="lab">从</p>
									<input id="from" type="text" name="from"  
										class="easyui-datebox" data-options="onSelect: onSelectFrom,editable:false" />
									<span class="icon"></span>
								</div>
								<div class="co">
									<p class="lab">到</p>
									<input id="to" type="text" name="to" 
										class="easyui-datebox" data-options="onSelect: onSelectTo,editable:false" />
									<span class="icon"></span>
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
			var c = $('#to').datebox('calendar');
			c.calendar({validator: function(date){
				date=fmtDate(date);
				from=fmtDate(from);
				if (date>=from){return true;}
				else {return false;}
			}});
		}
		function onSelectTo(to){
			var c = $('#from').datebox('calendar');
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
			$('#from').datebox('setValue', now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDate());
			$('#to').datebox('setValue', to.getFullYear()+"-"+(to.getMonth()+1)+"-"+to.getDate());
			var ci = $('#from').datebox('calendar');
			ci.calendar({validator: function(date){
				date=fmtDate(date);
				if (date>=now&&date<=to){return true;}
				else {return false;}
			}});
			var co = $('#to').datebox('calendar');
			co.calendar({validator: function(date){
				date=fmtDate(date);
				if (date>=now){return true;}
				else {return false;}
			}});
			$("button[type=submit]").click(function(){
				var href="${ctx}/searchActs?desLabel="+$("#desLabel").val()
						+"&desValue="+$("#desValue").val()
						+"&from="+$('#from').datebox('getValue')
						+"&to="+$('#to').datebox('getValue');
				window.location.href=href;
				return false;
			});
			$("#des").autocomplete("${ctx}/getActDes",{
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

