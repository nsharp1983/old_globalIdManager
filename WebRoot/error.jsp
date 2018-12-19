<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>登录失败</title>
<link href="css/loginstyle.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-color:#f2f2f2;">
 
<div id="login_page">
<div id="header"></div>
<form method="post" action="j_security_check">
<div id="center">
    <div class="login">
       <div class="login">
    <div class="login-box">
      <div class="login-box-2">
          <div style="height:190px; width:100%;"></div>
          <div class="vip-box">
          <div style="color:#d40006; font-size:12px; padding-left:80px;">认证失败</div>
            <div class="vip-box_field">
            <label>用户名</label>
            <input name="j_username" type="text" maxlength="32" class="login-text" onfocus="javascript:this.value='';"/>
            <label>密&nbsp;&nbsp;&nbsp;码</label>
            <input name="j_password" type="password" maxlength="32" class="login-text" onfocus="javascript:this.value='';"/>
            <input type="submit" class="stuff" value="" />
            </div>
            <div class="vip-box_field" style="text-align:center;padding-left:35px;"><span class="vip-box_field" style="text-align:center;padding-left:35px;"><span class="vip-box_field" style="text-align:center;padding-left:35px;"><span class="vip-box_field" style="text-align:center;padding-left:35px;">         
            </span></span></span></div>
          </div>
          </div>
    </div>
    </div>
</form>
</div>
</div>
</body>
</html>
