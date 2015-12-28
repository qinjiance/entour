<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../common/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><fmt:message key="webapp.title" /> - 当地玩乐</title>
<!-- meta -->
<%@include file="../common/meta.jspf"%>
<!-- css links -->
<%@include file="../common/links.jspf"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}<fmt:message key="static.resources.host"/>/css/acts.css" />
</head>
<body>
	<!-- header -->
	<c:set var="navi" value="2" />
	<%@include file="../common/header.jspf"%>

	<div class="bgbody">
		<div class="loading">
			<img src="${ctx}<fmt:message key="static.resources.host"/>/img/loading.gif" alt="loading" />
			<h1>正在为您搜索活动，请稍后。。。</h1>
		</div>
		<div class="xbody" style="display:none;">
				<div class="myleft">
					<div class="easyui-panel" data-options="width:270,border:true,title:'活动星级'">
						<div class="choose">
							<p><a class="stt stt5 ${searchStars==5?'on':''}" href="javascript:changeStart(5,'stt5');">5星级(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt4 ${searchStars==4?'on':''}" href="javascript:changeStart(4,'stt4');">4星级(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt3 ${searchStars==3?'on':''}" href="javascript:changeStart(3,'stt3');">3星级(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt2 ${searchStars==2?'on':''}" href="javascript:changeStart(2,'stt2');">2星级(<span class="starsLevel">0</span>)</a></p>
							<p><a class="stt stt1 ${searchStars==1?'on':''}" href="javascript:changeStart(1,'stt1');">1星级(<span class="starsLevel">0</span>)</a></p>
					    </div>
					</div>
					<div class="easyui-panel" data-options="style:{margin:'10px 0'},width:270,border:true,title:'活动分类'">
						<div id="cate" class="choose">
						
					    </div>
					</div>
				</div>
				<p id="cateP" style="display:none;"><a class="ccc" href="">(<span class="hotelCat">0</span>)</a></p>
				<div class="myright">
					<div id="searchCond" class="easyui-panel" data-options="collapsible:true,collapsed:true,width:850,border:true,title:'目的地：${desLabel}，日期：${from} - ${to} 【更改搜索条件】'" style="padding:5px;">
						<div class="conditions">
							<div class="condition">
								<label>目的地</label>
								<div id="depart">
									<input id="des" class="easyui-textbox" type="text" placeholder="城市" value="${desLabel}" style="width:600px;" />
								</div>
						    </div>
						    <div class="condition">
								<label>日期</label>
								<div id="vi">
									<span>从</span>
									<input id="from" type="text" value="${from}"
										class="easyui-datebox" data-options="onSelect: onSelectFrom,editable:false" />
									 |
									<span>到</span>
									<input id="to" type="text"  value="${to}"
										class="easyui-datebox" data-options="onSelect: onSelectTo,editable:false" />
									|
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
	
	<div id="hotelTemplate" style="display: none;">
		<div class="bref">
			<div class="small"><a class="actDet" href="${ctx}/" target="_blank">
				<img class="thumbImg" src="" alt="Picture" /></a></div>
			<div class="content">
				<a class="label actDet" href="${ctx}/" target="_blank"><span class="hotelName"></span><span class="starLevel"></span></a>
				<p class="cat"></p>
				<p class="brandName"></p>
				<p class="address"></p>
			</div>
			<div class="price">
				<a class="actDet" href="${ctx}/" target="_blank"><p class="detail">立即预定</p></a>
			</div>
		</div>
	</div>
	
	<form id="getActs" action="${ctx}/searchActs">
		<input type="hidden" name="desLabel" value="${desLabel}" />
		<input type="hidden" name="desValue" value="${desValue}" />
		<input type="hidden" name="from" value="${from}" />
		<input type="hidden" name="to" value="${to}" />
		<input type="hidden" name="searchStars" value="${searchStars}" />
		<input type="hidden" name="searchCategory" value="${searchCategory}" />
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
		getActs();
	}
	function changeCata(val,e){
		$("input[name=searchCategory]").val(val);
		$('.ccc').removeClass("on");
		$("."+e).addClass("on");
		getActs();
	}
	function getActs(){
		$(".loading").show();
		$(".xbody").hide();
		$.ajax({
		    type: 'get',
		    url: '${ctx}/getActs',
		    dataType: 'json',
		    data: $("#getActs").serialize(),
		    success: function(ret) {
		    	if(ret.code==0){
					var rets=ret.result;
					var actCats;
					var stars;
					var categoryMap;
					if(rets){
						actCats=ret.result.actVos;
						stars=ret.result.starList;
						categoryMap=ret.result.categoryMap;
					}
					var div=$(".routeList");
					div.empty();
					if(!rets||!actCats||actCats.length<=0){
						div.html($("#noHotel").clone(true).show());
					}else{
						$.each(actCats,function(i,ee){
							$.each(ee.activityVos,function(i,e){
								var hotelDiv=$("#hotelTemplate").clone(true);
								hotelDiv.attr("id","");
								hotelDiv.find(".thumbImg").attr("src",e.thumb);
								hotelDiv.find(".actDet").attr("href","${ctx}/actDet?activityId="
										+e.id+"&from="+$("input[name=from]").val()
										+"&to="+$("input[name=to]").val());
								hotelDiv.find(".hotelName").html(e.name);
								hotelDiv.find(".starLevel").addClass(e.stars+"stars");
								hotelDiv.find(".brandName").html(e.address+", "+e.country);
								hotelDiv.find(".address").html(e.desc);
								hotelDiv.find(".cat").html(ee.name);
								hotelDiv.show();
								div.append(hotelDiv);
							});
						});
						$(".starsLevel").each(function(i,e){
							$(e).html(stars[4-i]);
						});
						$("#cate").empty();
						var i=0;
						for(var key in categoryMap){  
							i++;
							var p = $("#cateP").clone(true);
							p.attr("id","");
							p.find("a").addClass("ccc"+i);
							p.find("a").attr("href","javascript:changeCata('"+key+"','ccc"+i+"');");
							p.find("a").prepend(key);
							p.find(".hotelCat").html(categoryMap[key]);
							if($("input[name=searchCategory]").val()== key){
								p.find("a").addClass("on");
							}
							p.show();
							$("#cate").append(p);  
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
		var c = $('#to').datebox('calendar');
		c.calendar({validator: function(date){
			date=fmtDate(date);
			from=fmtDate(from);
			if (date>=from){return true;}
			else {return false;}
		}});
		$("input[name=from]").val($('#from').datebox('getValue'));
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
		$("input[name=to]").val($('#to').datebox('getValue'));
	}
	function fmtDate(date){
		date.setHours(0);
		date.setMinutes(0);
		date.setSeconds(0);
		date.setMilliseconds(0);
		return date;
	}
	$(document).ready(function(){
		
		getActs();
		
		$("select").change(function(){
			$("input[name="+$(this).attr('name')+"]").val($(this).val());
		});
		var now=fmtDate(new Date());
		var from=fmtDate(new Date());
		var to=fmtDate(new Date());
		to.setDate(now.getDate()+2);
		if('${from}'){
			from = new Date('${from}'.replace(/-/g,"/"));
		}
		if('${to}'){
		    to = new Date('${to}'.replace(/-/g,"/"));
		}
		$('#from').datebox('setValue', from.getFullYear()+"-"+(from.getMonth()+1)+"-"+from.getDate());
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
			if (date>=from){return true;}
			else {return false;}
		}});
		$("#des").autocomplete("/getActDes",{
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
		
		$('#submitSearch').click(function(){
			$("#getActs").submit();
		});
	});
	</script>
</body>
</html>

