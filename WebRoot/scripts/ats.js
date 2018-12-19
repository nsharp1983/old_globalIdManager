//Atlas Tiger Common JavaScript
//Hansen 2011-11-04

//是否为IE浏览器
function isIE()
{
	return (window.navigator.userAgent.indexOf("MSIE")>=1?true:false);
}

//是否为FF浏览器
function isFF()
{
	return (window.navigator.userAgent.indexOf("Firefox")>=1?true:false);
}

//不支持浏览器
function ExplorerNotSupport()
{
	alert("十分抱歉，暂时不支持您的浏览器版本，请换用IE或火狐浏览器");	
}

//获取异常并显示错误
function DbgJS(func)
{
	try
	{
		func();
	}
	catch(ex)
	{
		alert(ex);
	}
}

//获取根据Id或Name获取控件
function $$(id)
{
	return (document.getElementById(id)||document.getElementsByName(id));
}

//是否是调试状态
function IsDebuging()
{
	return true;
}