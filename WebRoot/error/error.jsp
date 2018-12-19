<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/error.css" rel="stylesheet" type="text/css"/>
<title>400</title>

</head>

<body  style="background-color:#f9f9f9;">
　<div class="box">
  <div class="logo"><a href="javascript:history.go(-1);"><img src="images/error.png" /></a></div>
  <div class="text"><h2><s:property value="#request.msg"/></h2>请点击图标返回.
  </div>
  </div>
</body>
</html>
