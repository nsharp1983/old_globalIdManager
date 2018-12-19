<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
session.invalidate();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="3;url=."/> 
<link href="css/error.css" rel="stylesheet" type="text/css"/>
<title>请重新登录</title> 

</head>

<body>
　<div class="box">
  <div class="logo"><a href="."><img src="images/error.png" /></a></div>
  <div class="text"><h2>您已退出登录</h2>请点击图标返回.
  </div>
  </div>
</body>
</html>
