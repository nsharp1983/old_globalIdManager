<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.LinkedList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.ats.apixpdq.web.beans.ConfigBean"%>
<%@ page import="com.ats.aexchange.actorconfig.IActorDescription"%>

<%
	ConfigBean cb = (ConfigBean) request.getAttribute("ConfiBean");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>配置</title>

		<link href="css/subview_layout.css" rel="stylesheet" type="text/css" />
		<link href="css/aconfig.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="scripts/ats.js"></script>
		<script type="text/javascript" src="scripts/jquery.js"></script>
		<script type="text/javascript" src="scripts/jquery.layout.js"></script>
		<script>
			
			//请不要随便修改
			function mousemoveCfg(ffevent)
		    {
		    	try
		    	{
		    		var evt= ffevent||window.event;
				    
			    	if(isIE())
			    	{
			    		$$("cfgbrowse").style.posTop = evt.y-$$("cfgbrowse").offsetHeight/2;
						$$("cfgbrowse").style.posLeft = evt.x-$$("cfgbrowse").offsetWidth/2;
			        }
			    	else if(isFF())
			    	{
			    		$$("cfgbrowse").style.top = evt.clientY-$$("cfgbrowse").offsetHeight/2 + "px";
						$$("cfgbrowse").style.left = evt.clientX-$$("cfgbrowse").offsetWidth/2 + "px";
			    	}
			    	else
		    		{
		    			ExplorerNotSupport();
		    		}
		    	}
		    	catch(ex)
		    	{
		    		if(IsDebuging())alert(ex);
		    	}
		    }
			
			
			//请不要随便修改
			function mousemoveCfgHide(ffevent)
		    {
		    	try
		    	{
		    		var evt= ffevent||window.event;
				    
			    	if(isIE())
			    	{
						$$("cfgbrowse").style.posLeft = 0;
			        }
			    	else if(isFF())
			    	{
						$$("cfgbrowse").style.left = "0px";
			    	}
			    	else
		    		{
		    			ExplorerNotSupport();
		    		}
		    	}
		    	catch(ex)
		    	{
		    		if(IsDebuging())alert(ex);
		    	}
		    }
			
			
			//请不要随便修改
			function mousemoveLog(ffevent)
		    {
		    	try
		    	{
		    		var evt= ffevent||window.event;
				    
			    	if(isIE())
			    	{
			    		$$("logbrowse").style.posTop = evt.y-$$("logbrowse").offsetHeight/2;
						$$("logbrowse").style.posLeft = evt.x-$$("logbrowse").offsetWidth/2;
			        }
			    	else if(isFF())
			    	{
			    		$$("logbrowse").style.top = evt.clientY-$$("logbrowse").offsetHeight/2 + "px";
						$$("logbrowse").style.left = evt.clientX-$$("logbrowse").offsetWidth/2 + "px";
			    	}
			    	else
		    		{
		    			ExplorerNotSupport();
		    		}
		    	}
		    	catch(ex)
		    	{
		    		if(IsDebuging())alert(ex);
		    	}
		    }
			    
		
		    function selectOneFile(fileCtrl,pathTextId)
		    {
				try
				{
			    	if(isIE())
			    	{
			    		$$(pathTextId).value = fileCtrl.value;
			    	}
			    	else if(isFF())
			    	{
			    		try
			    		{
							netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
						} 
						catch (e)
						{
							alert(e);
						}
			    		$$(pathTextId).value = fileCtrl.value;
			    		//$$(pathTextId).value = fileCtrl.files.item(0).getAsDataURL();
			    	}
			    	else
			    	{
			    		ExplorerNotSupport();
			    	}
		    	}
				catch(ex)
				{
					if(IsDebuging())alert(ex);
				}
		    }
		    
			//Hansen
			//生成布局
			var outer;
			$(document).ready(function() {
				outer = $("body").layout( {
					center__paneSelector : "#outCenter",
					west__paneSelector : "#outWest",
					west__size : 260
				});
			});
			</script>
	</head>

	<body style="position: relative; height: 100%; overflow: hidden; margin: 0px; padding: 0px; border: medium none;">

		<div id="outWest" class="ui-layout-west ui-layout-pane ui-layout-pane-west" style="background-color: #F6F6F6;overflow-x:hidden;">
			<div class="box" style="width: 260px; overflow: hidden; overflow-x: hidden;background-color: #F6F6F6;">
				<div class="left">
					<div class="l_hand">
						<div style=" background-image:url(images/cfg_red.png); background-repeat:no-repeat; height:30px; width:18px;float:left; margin-top:2px; margin-left:10px;"></div>
						<span style="float:left; padding-left:10px;">服务配置</span>
					</div>
					<div class="l_seach">
						<div class="l_s_left">
							选用配置文件:
						</div>
						<div class="l_r_right">
							<input id="configFile" name="configFile" type="text" value="<%if (cb.getLogfile() != null){%>"<%=cb.getLogfile()%>"<%}else{%>""<%}%>"/>
						</div>
						<div style="clear: both"></div>
					</div>
					<div class="l_s_ps"></div>
					<div class="l_buttom" style="padding-left:65px">
						<div class="buttom_left"></div>
						<div class="buttom_box" style="width: 164px;">
							<div class="l_b_f" style="width: 80px;" onmouseover="mousemoveCfg(event);">
								选择
							</div>
							<div class="l_b_c" style="width: 80px;" onclick="" onmouseover="mousemoveCfgHide(event);">
								加载
							</div>
							<input type="file" id="cfgbrowse" name="cfgbrowse" size="1" onchange="selectOneFile(this,'configFile');" style="position:absolute;filter:alpha(opacity=00);opacity:0.0;width:30px;"/>
						</div>
						<div class="buttom_right"></div>
						<div style="clear: both"></div>
					</div>
				</div>

				<div class="left" style="height:160px">
					<div class="l_seach">
						<div class="l_s_left">
							 选用日志文件:
						</div>
						<div class="l_r_right">
							<input id="logfile" name="logfile" type="text" value=<%if (cb.getConfigFile() != null){%>"<%=cb.getConfigFile()%>"<%}else{%>""<%}%>/>
						</div>
						<div style="clear: both"></div>
					</div>
					<div class="l_s_ps"></div>
					<div class="l_buttom" style="width: 100px; padding-left: 150px;">
						<div class="buttom_left"></div>
						<div class="buttom_box" style="width: 80px;">
							<div class="l_b_f" style="border: 0; width: 80px;" onmouseover="mousemoveLog(event);">
								选择
							</div>
							<input type="file" id="logbrowse" name="logbrowse" size="1" onchange="selectOneFile('logbrowse','logfile');" style="position:absolute;filter:alpha(opacity=00);opacity: 0.0;width:30px;"/>
						</div>
						<div class="buttom_right"></div>
						<div style="clear: both"></div>
					</div>
				</div>

				<div class="left" style="height: 60px; ">
					<div class="l_buttom" style="padding: 12px 6px; margin: 0 auto; width: 240px;">
						<div class="buttom_left"></div>
						<div class="buttom_box" style="width: 230px;">
							<div class="l_b_f" style="width: 114px;">
								启动服务
							</div>
							<div class="l_b_c" style="width: 114px;">
								停止服务
							</div>
						</div>
						<div class="buttom_right"></div>
						<div style="clear: both"></div>
					</div>
				</div>
			</div>
		</div>

		<div id="outCenter" class="ui-layout-center ui-layout-pane ui-layout-pane-center">
			<form name="ConfigBean" method="post" action="Config.do">
				<div class="right" style="width:98%;background-color: #F6F6F6;">
					<%
					List l = new LinkedList();
					if (cb.getActors() != null) {
						for (int x = 0; x < cb.getActors().length; x++) {
							l.add(cb.getActors()[x]);
						}
					}
					List lActors = (List) request.getAttribute("ActorList");
					if (lActors != null && lActors.size() > 0) {
						String sType = ((IActorDescription) lActors.get(0)).getType();
					%>
					<div class="r_box">
						<div class="r_hand">
							<span><%=sType%></span>
						</div>
					<%
						for (int x = 0; x < lActors.size(); x++) {
							IActorDescription iad = (IActorDescription) lActors.get(x);
							if (!sType.equals(iad.getType())) {
								sType = iad.getType();
					%>
					</div>
					<div class="r_box" style="padding-top: 15px">
						<div class="r_hand">
							<span><%=sType%></span>
						</div>
						<div class="l_seach">
							<div class="l_s_left">
								<input type="checkbox" name="actors" value="<%=iad.getName()%>" <%if (l.contains(iad.getName())) {%> checked="checked"<%}%>/>
								<%=iad.getDescription()%>
							</div>
						</div>
					<%
							}
							else
							{
					%>

						<div class="l_seach">
							<div class="l_s_left">
								<input type="checkbox" name="actors" value="<%=iad.getName()%>" <%if (l.contains(iad.getName())) {%> checked="checked"<%}%>/>
								<%=iad.getDescription()%>
							</div>
						</div>
						
					<%
							}
						}
					%>
					</div>
					<%
						}
					%>
				</div>
			</form>
		</div>
	</body>
</html>