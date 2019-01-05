<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>MainForm</title>
		
		<style type="text/css">
		body {background: #f2f2f2; font-family:Arial, Helvetica, sans-serif; font-size:12px; margin:0px;}
		a {color: #FFF; text-decoration: none;/*防止滤镜下链接失效*/position:relative;}
		ul { list-style:none;}
		#all {width: 100%; background: url(img/hand_hand.png) repeat-x;}
		#banner { height:40px;}
		.hand_list{ background:url(img/hand_list.png) repeat-x;}
		#nav {width: 950px; background:url(img/hand_list.png) repeat-x; height:30px; margin:0 auto; line-height:30px;}
		#nav ul li {float:left; text-align: center; height:28px; width:100px;}
		.inactive { background:none; margin-left: 2px; margin-right:2px; height:28px; width:100px; font-size:14px; font-weight:bold;}
		.inactive a {color: #686f7f;}
		.active {background:#ffffff !important; margin-left: 2px; margin-right:2px; height:26px; width:100px; font-size:14px; font-weight:bold; border-left:1px solid #b9cbe1; border-right:1px solid #b9cbe1;}
		.active a {color:#686f7f; background:#ffffff}
		</style>

		<script type="text/javascript" language="javascript" src="js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="scripts/jquery.js"></script>
		<script type="text/javascript" src="scripts/ats.js"></script>
		<script type="text/javascript">
		
		var oplist = new Array('regist', 'pixquery', 'pdqquery', 'about', 'exit');
		
		$(document).ready(function() 
		{
			$('#nav').find("a").click(function() 
			{
				var id = $(this).attr('id');
				
				$.each(oplist, function(i, n) 
				{
				$('#'+n).attr('class', 'inactive');
			});
		$(this).parents("li").attr('class', 'active');
		});
		});
		
		$(function() {
			var $div_li = $("div.hand_list ul li");
			$div_li.click(function() {
				
				$(this).addClass("steat").siblings().removeClass("steat");
				
				var index = $div_li.index(this);

				if(index==0)
				{
					$$('iframe_content').src = "patient_Regist.action";
					
				}
				else if(index==1)
				{
					$$('iframe_content').src = "patient_PixQuery.action";

				}
				else if(index==2)
				{
					$$('iframe_content').src = "patient_PdqQuery.action";
				}
				
				else if(index==3)
				{
					$$('iframe_content').src = "About.action";
				}
				else if(index==4)
				{
					LogOut();
				}
			}).hover(function() {
				$(this).addClass("hover");
			}, function() {
				$(this).removeClass("hover");
			})
		});
		
		//登出
		function LogOut()
		{
			if(confirm("您确信要退出登录吗？"))
			{
				window.location.href='logout.jsp';
			}
		}
		
		//调整控件高度
		function AdjustOneObjectHeight(ctrlid)
		{
			var FrameHeight = document.documentElement.clientHeight;

			var ctrl = document.getElementById(ctrlid);
			if(ctrl!=null)
			{
				ctrl.height = FrameHeight-30;

			}
		}
		
		//调整iframe高度
		function AdjustObjectsHeight()
		{
			try
			{
				AdjustOneObjectHeight("iframe_content");
			}
			catch(e)
			{

			}
		}
		
		//页面载入完毕，兼容IE
		$(document).ready(
			function() {
				AdjustObjectsHeight();
				
			//窗口大小改变时，设置对应iframe高度
			window.onresize=function(){
				AdjustObjectsHeight();
			}
				
			});
		</script>
	</head>

	<body>
		<div id="all" style="overflow: hidden;" scroll="no" style="background-color:#f2f2f2;">
		  <div id="banner" style="padding:20px 0 0 100px;"></div>
			<div class="hand_list">
				<div id="nav">
					<ul>
					<li class="inactive" id="regist"><a target="_self" >病人注册</a></li>
        			<li class="inactive" id="pixquery" style="display:none"><a target="_self" >PIX查询</a></li>
            		<li class="active" id="pdqquery"><a target="_self">病人查询</a></li>
            		<li class="inactive" id="about" style="display:none"><a target="_self">关于</a></li>
            		<li class="inactive" id="exit" style="display:none"><a  target="_self" >退出</a></li>
        			</ul>
				</div>
			</div>
			<div class="box_body">
				<iframe id="iframe_content" src="patient_PdqQuery.action" frameborder="0" height="100%" width="100%"
						marginheight="0" marginwidth="0">
				</iframe>
			</div>
			<div style="height: 40px; margin-bottom: 40px;">&nbsp;</div>
		</div>

	</body>
</html>
