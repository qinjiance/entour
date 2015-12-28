function isMobile(s){
	var patrn=/^(1\d{10})|(\d+(\-\d+)*)$/;
	return patrn.test(s);
}

function isEmail(s){
	var patrn=/([\w|-]+[\.?\w|-]*@[\w|-]+\.[\w|-]+)(\.?[\w|-]*)(\.?[\w|-]*)/i;
	return patrn.test(s);
}

function isPwd(s){
	var patrn=/^.{6,20}$/;
	return patrn.test(s);
}

function isQQ(s){
	var patrn=/^[^0][1-9]{4,15}$/;
	return patrn.test(s);
}

function isPostCode(s){
	var patrn=/^[0-9]{6}$/;
	return patrn.test(s);
}


function isChinese(s){
    var pattern = /^[\u4E00-\u9FA5]{2,5}$/i;
    return pattern.test(s);
}

function getPasswordStrength(password) {
	var hasNum = false;
	var hasUpperLetter = false;
	var hasLowerLetter = false;
	for(var i=0;i<password.length;i++) {
		var chr = password.charCodeAt(i); 
		if(chr>=48 && chr<=57) {//鏁板瓧
			hasNum = true;
		}
		if( (chr>=65 && chr<=90 )//澶у啓瀛楁瘝
				|| (chr>=91 && chr<=96 )//鐗规畩绗﹀彿
				|| (chr>=33 && chr<=47 )//鐗规畩绗﹀彿
				|| (chr>=58 && chr<=64 )//鐗规畩绗﹀彿
				||(chr>=123 && chr<=126 )//鐗规畩绗﹀彿
				) {
			hasUpperLetter = true;
		}
		if(chr>=97 && chr<=122) {//灏忓啓瀛楁瘝
			hasLowerLetter = true;
		}
	}
	var count = 0; if(hasNum) { count ++; }
	if(hasUpperLetter) { count ++; }
	if(hasLowerLetter) { count ++; }
	if(count==3 && password.length <9)  { count --; }
	return count;
}

function checkIdcard(idcardx){
	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"} 

	var idcard = idcardx.toUpperCase();
	var Y,JYM;
	var S,M;
	var idcard_array = new Array();
	idcard_array = idcard.split("");
//	地区检验
	if(area[parseInt(idcard.substr(0,2))]==null) return 4;
//	身份号码位数及格式检验
	switch(idcard.length){
	case 15:
		if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
		} else {
			ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
		}
		if(ereg.test(idcard)) return 0;
		else return 2;
		break;
	case 18:
//		18位身份号码检测
//		出生日期的合法性检查 
//		闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
//		平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
		if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
			ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
		} else {
			ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
		}
		if(ereg.test(idcard)){//测试出生日期的合法性
//			计算校验位
			S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
			+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
			+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
			+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
			+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
			+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
			+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
			+ parseInt(idcard_array[7]) * 1 
			+ parseInt(idcard_array[8]) * 6
			+ parseInt(idcard_array[9]) * 3 ;
			Y = S % 11;
			M = "F";
			JYM = "10X98765432";
			M = JYM.substr(Y,1);//判断校验位
			if(M == idcard_array[17]) return 0; //检测ID的校验位
			else return 3;
		}
		else return 2;
		break;
	default:
		return 1;
	break;
	}
}

function getDateString(date){
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	month = month<10?('0'+month):month;
	var day = date.getDate();
	day = day<10?('0'+day):day;
	var hour = date.getHours();
	hour = hour<10?('0'+hour):hour;
	var minute = date.getMinutes();
	minute = minute<10?('0'+minute):minute;
	var sec = date.getSeconds();
	sec = sec<10?('0'+sec):sec;
	return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+sec;
}