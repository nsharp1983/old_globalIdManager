function Cal_DateAdd(interval, number, date) {
	number = parseInt(number);
	if (typeof (date) == "string") {
		var date = new Date(date.split("-")[0], date.split("-")[1], date.split("-")[2])
	}
	if (typeof (date) == "object") {
		var date = date
	}
	switch (interval) {
	case "y":
		return new Date(date.getFullYear() + number, date.getMonth(), date.getDate());
		break;
	case "m":
		return new Date(date.getFullYear(), date.getMonth() + number, Cal_CheckDate(date.getFullYear(), date.getMonth() + number, date.getDate()));
		break;
	case "d":
		return new Date(date.getFullYear(), date.getMonth(), date.getDate() + number);
		break;
	case "w":
		return new Date(date.getFullYear(), date.getMonth(), 7 * number + date.getDate());
		break;
	}
}

function Cal_CheckDate(year, month, date) {
	var enddate = [ "31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31" ];
	var returnDate = "";
	if (year % 4 == 0) {
		enddate[1] = "29"
	}
	if (date > enddate[month]) {
		returnDate = enddate[month]
	} else {
		returnDate = date
	}
	return returnDate;
}

function Cal_WeekDay(date) {
	var theDate;
	if (typeof (date) == "string") {
		theDate = new Date(date.split("-")[0], date.split("-")[1], date.split("-")[2]);
	}
	if (typeof (date) == "object") {
		theDate = date
	}
	return theDate.getDay();
}

function Cal_Add0(str){
	if(str==null)return str;
	str = str+"";
	if(str.length==1)
	{
		return "0"+str;
	}
	else
	{
		return str;	
	}
}

function Cal_Calender() {
	var lis = "";
	var style = "";
	style += "<style type='text/css'>";
	style += ".calender { width:170px; height:auto; font-size:12px; margin-right:14px; background:url(calenderbg.gif) no-repeat right center #fff; border:1px solid #397EAE; padding:1px}";
	style += ".calender ul {list-style-type:none; margin:0; padding:0;}";
	style += ".calender .day { background-color:#EDF5FF; height:20px;}";
	style += ".calender .day li,.calender .date li{ float:left; width:14%; height:20px; line-height:20px; text-align:center}";
	style += ".calender li a { text-decoration:none; font-family:Tahoma; font-size:11px; color:#333}";
	style += ".calender li a:hover { color:#f30; text-decoration:underline}";
	style += ".calender li a.hasArticle {font-weight:bold; color:#f60 !important}";
	style += ".lastMonthDate, .nextMonthDate {color:#bbb;font-size:11px}";
	style += ".selectThisYear a, .selectThisMonth a{text-decoration:none; margin:0 2px; color:#000; font-weight:bold}";
	style += ".calender .LastMonth, .calender .NextMonth{ text-decoration:none; color:#000; font-size:18px; font-weight:bold; line-height:16px;}";
	style += ".calender .LastMonth { float:left;}";
	style += ".calender .NextMonth { float:right;}";
	style += ".calenderBody {clear:both}";
	style += ".calenderTitle {text-align:center;height:20px; line-height:20px; clear:both}";
	style += ".today { background-color:#ffffaa;border:1px solid #f60; padding:2px}";
	style += ".today a { color:#f30; }";
	style += ".calenderBottom {clear:both; border-top:1px solid #ddd; padding: 3px 0; text-align:left}";
	style += ".calenderBottom a {text-decoration:none; margin:2px !important; font-weight:bold; color:#000}";
	style += ".calenderBottom a.closeCalender{float:right}";
	style += ".closeCalenderBox {float:right; border:1px solid #000; background:#fff; font-size:9px; width:11px; height:11px; line-height:11px; text-align:center;overflow:hidden; font-weight:normal !important}";
	style += "</style>";

	var now;
	if (typeof (arguments[0]) == "string") {
		selectDate = arguments[0].split("-");
		var year = selectDate[0];
		var month = parseInt(selectDate[1]) - 1 + "";
		var date = selectDate[2];
		now = new Date(year, month, date);
	} else if (typeof (arguments[0]) == "object") {
		now = arguments[0];
	}
	var lastMonthEndDate = Cal_DateAdd("d", "-1", now.getFullYear() + "-" + now.getMonth() + "-01").getDate();
	var lastMonthDate = Cal_WeekDay(now.getFullYear() + "-" + now.getMonth() + "-01");
	var thisMonthLastDate = Cal_DateAdd("d", "-1", now.getFullYear() + "-" + (parseInt(now.getMonth()) + 1).toString() + "-01");
	var thisMonthEndDate = thisMonthLastDate.getDate();
	var thisMonthEndDay = thisMonthLastDate.getDay();
	var todayObj = new Date();
	today = todayObj.getFullYear() + "-" + todayObj.getMonth() + "-" + todayObj.getDate();

	for (i = 0; i < lastMonthDate; i++) {
		lis = "<li class='lastMonthDate'>" + lastMonthEndDate + "</li>" + lis;
		lastMonthEndDate--;
	}
	for (i = 1; i <= thisMonthEndDate; i++) {

		if (today == now.getFullYear() + "-" + now.getMonth() + "-" + i) {
			var todayString = now.getFullYear() + "-" + Cal_Add0((parseInt(now.getMonth()) + 1).toString()) + "-" + Cal_Add0(i);
			lis += "<li><a href=javascript:void(0) class='today' onclick='Cal_SelectThisDay(this)' title='" + now.getFullYear() + "-"
					+ Cal_Add0(parseInt(now.getMonth()) + 1) + "-" + Cal_Add0(i) + "'>" + i + "</a></li>";
		} else {
			lis += "<li><a href=javascript:void(0) onclick='Cal_SelectThisDay(this)' title='" + now.getFullYear() + "-" + Cal_Add0((parseInt(now.getMonth()) + 1)) + "-" + Cal_Add0(i)
					+ "'>" + i + "</a></li>";
		}

	}
	var j = 1;
	for (i = thisMonthEndDay; i < 6; i++) {
		lis += "<li class='nextMonthDate'>" + j + "</li>";
		j++;
	}
	lis += style;

	var CalenderTitle = "<a href='javascript:void(0)' class='NextMonth' onclick=Cal_Calender(Cal_DateAdd('m',1,'" + now.getFullYear() + "-" + now.getMonth()
			+ "-" + now.getDate() + "'),this) title='下个月'>&raquo;</a>";
	CalenderTitle += "<a href='javascript:void(0)' class='LastMonth' onclick=Cal_Calender(Cal_DateAdd('m',-1,'" + now.getFullYear() + "-" + now.getMonth() + "-"
			+ now.getDate() + "'),this) title='上个月'>&laquo;</a>";
	CalenderTitle += "<span class='selectThisYear'><a href='javascript:void(0)' onclick='Cal_SelectYear(this)' title='点击这里选择年份' >"
			+ now.getFullYear()
			+ "</a></span>年<span class='selectThisMonth'><a href='javascript:void(0)' onclick='Cal_SelectMonth(this)' title='点击这里选择月份'>"
			+ (parseInt(now.getMonth()) + 1).toString() + "</a></span>月";

	if (arguments.length > 1) {
		arguments[1].parentNode.parentNode.getElementsByTagName("ul")[1].innerHTML = lis;
		arguments[1].parentNode.innerHTML = CalenderTitle;
	} else {
		var CalenderBox = style
				+ "<div class='calender'><div class='calenderTitle'>"
				+ CalenderTitle
				+ "</div><div class='calenderBody'><ul class='day'><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul><ul class='date' id='thisMonthDate'>"
				+ lis
				+ "</ul></div><div class='calenderBottom'><a href='javascript:void(0)' class='closeCalender' onclick='Cal_CloseCalender(this)'>关闭</a><span><span><a href=javascript:void(0) onclick='Cal_SelectThisDay(this)' title='"
				+ todayString + "'>今天</a></span></span></div></div>";
		return CalenderBox;
	}
}

function Cal_SelectThisDay(d) {
	var boxObj = d.parentNode.parentNode.parentNode.parentNode.parentNode;
	boxObj.targetObj.value = d.title;
	boxObj.parentNode.removeChild(boxObj);
}

function Cal_CloseCalender(d) {
	var boxObj = d.parentNode.parentNode.parentNode;
	boxObj.parentNode.removeChild(boxObj);
}

function Cal_SelectYear(obj) {
	var opt = "";
	var thisYear = obj.innerHTML;
	for (i = 1920; i <= 2020; i++) {
		if (i == thisYear) {
			opt += "<option value=" + i + " selected>" + i + "</option>";
		} else {
			opt += "<option value=" + i + ">" + i + "</option>";
		}
	}
	opt = "<select onblur='Cal_SelectThisYear(this)' onchange='Cal_SelectThisYear(this)' style='font-size:11px'>" + opt + "</select>";
	obj.parentNode.innerHTML = opt;
}

function Cal_SelectThisYear(obj) {
	Cal_Calender(obj.value + "-" + obj.parentNode.parentNode.getElementsByTagName("span")[1].getElementsByTagName("a")[0].innerHTML + "-1", obj.parentNode);
}

function Cal_SelectMonth(obj) {
	var opt = "";
	var thisMonth = obj.innerHTML;
	for (i = 1; i <= 12; i++) {
		if (i == thisMonth) {
			opt += "<option value=" + i + " selected>" + i + "</option>";
		} else {
			opt += "<option value=" + i + ">" + i + "</option>";
		}
	}
	opt = "<select onblur='Cal_SelectThisMonth(this)' onchange='Cal_SelectThisMonth(this)' style='font-size:11px'>" + opt + "</select>";
	obj.parentNode.innerHTML = opt;
}

function Cal_SelectThisMonth(obj) {
	Cal_Calender(obj.parentNode.parentNode.getElementsByTagName("span")[0].getElementsByTagName("a")[0].innerHTML + "-" + obj.value + "-1", obj.parentNode);
}

function Cal_SetDate(inputObj) {
	try
	{
		var calenderObj = document.createElement("span");
		calenderObj.innerHTML = Cal_Calender(new Date());
		calenderObj.style.position = "absolute";
		calenderObj.targetObj = inputObj;
		inputObj.parentNode.insertBefore(calenderObj, inputObj.nextSibling);
	}
	catch(ex)
	{
		alert(ex);
	}
}
