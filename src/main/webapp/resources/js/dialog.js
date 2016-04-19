$.fn.jqueryDialog=function(opt){
	$("#myDialogbox #inbox .child").hide();
	
	var myopts = $.extend({
			"imgautosize":false,
			"cancelbtn":"#myDialogbox #mask",
		},opt);
	$this=$(this);
	var myDialog=$("#myDialogbox");
	if(myDialog.length>0){
		$("#myDialogbox").show();
		$(this).show();
		}
	else{
		$(this).show();
		var mystr='<div id="myDialogbox" style="width:100%; height:100%; position:fixed; top:0;left:0; z-index:500;">';
		mystr+='<div id="mask" style="filter:alpha(opacity=80); -moz-opacity:0.8; opacity:0.8;width:100%; height:100%; position:absolute; top:0;left:0; background:#000;">';
		mystr+='</div><div id="inbox" style="position:absolute; top:50%;left:50%;"></div></div>';
		$("body").append(mystr);
		
		}
	$(this).addClass("child");
	$("#myDialogbox #inbox").append($(this));
	var mydefaultimg=$(this).find("img").eq(0);
	var mydefaultsize=[mydefaultimg.width(),mydefaultimg.height()]	;
	var vpx, vpy;
	if (self.innerHeight) {
		vpx = self.innerWidth;
		vpy = self.innerHeight;
	} else if (document.documentElement && document.documentElement.clientHeight) {
		vpx = document.documentElement.clientWidth;
		vpy = document.documentElement.clientHeight;
	} else if (document.body) {
		vpx = document.body.clientWidth;
		vpy = document.body.clientHeight;
	}
	if(myopts.imgautosize){
		if(myopts.imgfixsize){
			$(this).find("img").css({"width":myopts.imgfixsize[0]+"px","height":myopts.imgfixsize[1]+"px"});
		}
		else{
		 	var mystagesize=[vpx,vpy];
			var myscale1=mystagesize[0]/mydefaultsize[0];
			var myscale2=mystagesize[1]/mydefaultsize[1];
			var myminscale=Math.min(myscale1,myscale2,1);
			$(this).find("img").css({"width":mydefaultsize[0]*myminscale+"px",
									 "height":mydefaultsize[1]*myminscale+"px"});
			}
	}
	var targetHeight=$(this).height();
	var targetWidth=$(this).width();
	$("#myDialogbox #inbox").css({"margin-left":(-targetWidth/2+"px")
								,"margin-top":(-targetHeight/2+"px")});
	$("#myDialogbox #mask").unbind("click");
	var closebox=(function(x){
			function refun(e){x.hide();$("#myDialogbox").hide();
			$(myopts.cancelbtn).unbind("click");
			$this.find("img").css({"width":"auto",
									 "height":"auto"});
			}
			return refun;
			})($(this));
	$(myopts.cancelbtn).off("click");
	if(myopts.cancelbtn){
		$(myopts.cancelbtn).on("click",closebox);
		}
	}