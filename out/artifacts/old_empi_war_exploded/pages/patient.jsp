<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ats.apixpdq.web.beans.PixManagerBean"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>病人信息</title>
		<script language="JavaScript"> 
		if (window == top) 
			location.href ="<%=path%>/mainform.jsp"; 
		</script> 

	
		
		<script type="text/javascript" src="scripts/ats.js"></script>
		<script type="text/javascript" src="scripts/jquery.js"></script>
		 <script type="text/javascript" src="scripts/My97DatePicker/WdatePicker.js"></script>
		<script>
			//Hansen
			function clearForm()
			{
				try
				{
					$$('lName').value="";
				}
				catch(ex)
				{
					alert(ex);
				}
			}
			
			//Hansen
			function submitForm()
			{
				try
				{
					
					$$('RegForm').submit();
				}
				catch(ex)
				{
					alert(ex);
				}
			}
			
			
			//Hansen
			/**
			var outer;
			$(document).ready(function() {
				outer = $("body").layout( {
					center__paneSelector : "#outCenter",
					west__paneSelector : "#outWest",
					west__size : 260
				});
			});*/
		</script>
	<link rel="stylesheet" href="css/style.css" type="text/css"></link>
	<style>
	.box{ width:800px; margin:0 auto;}
	.box input{ height:20px; width:300px; border:1px solid #dad9d9;}
	.box select{ height:24px;  width:305px; border:1px solid #dad9d9;}
	.mg_left{ float:left; width:240px; padding:0px 30px 0px 40px;}
	.box .l_hand{ width:800px;}
	.mg_right{ float:right;border-left:1px solid #CCCCCC; background-color: #f6f6f6; width:480px; }
	.box td{ font-size:12px; font-weight:normal; font-family:Arial, Helvetica, sans-serif;height: 26px;}
	
	</style>
	</head>
	<body style="background-color:#f2f2f2;">
		 <div class="box" >
     <div class="center"  style="border:1px solid #CCCCCC; background-color:#F5F5f5; margin-top:5px;">
     <div class="mg_left" style="padding-top:10px;">
        <div><img src="images/patient_2.png" /></div>
     </div>
    <div class="mg_right" style="background-color: #fafafa;">
     <form id="RegForm" name="RegForm" action="patient_updatePation.action" method="post">
     <div>
     	<table class="table_top_1" border="0"  cellspacing="0" cellpadding="0" bgcolor="#90c7ff" style="width:100%;">
           <tr bgcolor="#b1c7df" >
              <td width="20"></td>
              <td style="color:#5d594c; padding-left:8px;height:30px; font-weight:bold;">病人基本信息</td>
           </tr>
        </table>
     </div>
     
      <table width="100%" align="center" style=" margin:0 atuo;" cellspacing="0" cellpadding="0" border="0" >
      <tr style="height: 10px;"><td colspan="7" style="height: 10px;"></td></tr>
        <tr >
          <td width="30"></td>
          <td width="80" >发布机构:</td>
          <td colspan="5"><s:property value="#request.patient.pidlist.get(0).getAssigningAuthority().getNamespaceId()"/></td>
        </tr>
        <tr >
          <td ></td>          
          <td >姓名:</td>  
           <td  colspan="4"><s:property value='#request.patient.nameString'/></td>       
          
        </tr>
         
        <tr >
         <td ></td>
          <td >性别:</td>
          <td width="100"><s:property value="#request.patient.gender"/></td>
          <td width="80">生日:</td>
          <td ><s:property value='#request.patient.time()'/></td>
          <td></td>
        </tr>
   
		 <tr >
          <td ></td>          
          <td >婚姻:</td>  
          <td><s:property value="#request.patient.maritalStatus"/></td>  
         
        </tr>
         <tr >
          <td></td>
          <td >联系电话:</td>
          <td colspan="5"><s:property value="#request.patient.phone"/></td>
        </tr>

       <tr >
          <td></td>         
          <td >身份证号码:</td>
          <td colspan="5"><s:property value="#request.patient.identityCard"/></td>
        </tr>
        <tr >
          <td></td>
          
          <td>医保卡号码:</td>
          <td colspan="5"><s:property value="#request.patient.insuranceCard"/></td>
        </tr>
         
        <tr >
          <td></td>
          
          <td >社保卡号码:</td>
          <td colspan="5"><s:property value="#request.patient.socialCard"/></td>
        </tr>       
       
        <tr >
          <td></td>
          
          <td>地址:</td>
          <td colspan="5"><s:property value='#request.patient.fullAddress'/></td>
        </tr>
       <tr>
         <td colspan="7">
            <div class="vip_box" style="width:100%;"  >   <!--最外框-->
            
<!-- 单位信息 --> 
              <table class="table_1" style="width:100%; margin:10px 0px 5px 0px;" cellpadding="0" cellspacing="0">
                <tr>
                      <td>
                          <table class="table_top_1" border="0"  cellspacing="0" cellpadding="0" bgcolor="#90c7ff" style="width:100%;">
                              <tr bgcolor="#b1c7df"> 
                                 <td width="20"><img src="<%=path %>/images/2.gif"  name="img2"  style="cursor: hand; padding-left:10px;"></td>
                                 <td style="color:#5d594c;font-size:12px;height:30px;  padding-left:8px; font-weight:bold;">工作单位相关信息</td>
                              </tr>
                           </table>                       
                          <table id="table2"  cellspacing="2" cellpadding="0" border="0"  align="center" style="width:100%; ">
                          	<tr style="height: 10px;"><td colspan="6" style="height: 10px;"></td></tr>
                          	<tr >
                               	<td width="27"></td>
					          <td >公司名称:</td>
					          <td colspan="4"><s:property value='#request.patient.companyName'/></td>
					        </tr>
					        <tr>
					          <td  width="27"></td>
					          <td width="110" >公司电话:</td>
					          <td width="80"><s:property value='#request.patient.companyPhone'/></td>
					          <td  >公司邮箱:</td>
					          <td><s:property value='#request.patient.companyEmail'/></td>
					          <td colspan="2"></td>
					        </tr> 
					        <tr >
		
					          <td ></td>
					          <td >公司地址:</td>
					          <td colspan="4"><s:property value='#request.patient.companyAdderss'/></td>
					        </tr>
                          </table>
                    </td>
                </tr>
           </table> 
           
 <!-- 联系人信息 -->  
              <table class="table_1" style="width:100%;position:relative; margin-bottom: 5px;"  cellpadding="0" cellspacing="0">
                <tr>
                      <td>
                          <table class="table_top_1" border="0"  cellspacing="0" cellpadding="0" bgcolor="#90c7ff" style="width:100%;">
                              <tr bgcolor="#b1c7df">
                                 
                                 <td width="20"><img src="<%=path %>/images/2.gif" width="12"  name="aaa"  style="cursor: hand; padding-left:10px;"></td>
                                 <td style="color:#5d594c;font-size:12px; height:30px; padding-left:8px; font-weight:bold;">联系人相关信息</td>
                              </tr>
                        </table>
                           <table id="xxx"  cellspacing="2" cellpadding="0"  style="width:100%; ">
                           <tr style="height: 10px;"><td colspan="6" style="height: 10px;"></td></tr>
					         <tr >
					          <td  width="27"></td>
					          <td width="110">联系人姓名:</td>
					          <td width="80"><s:property value='#request.patient.relationName'/></td>
					          <td>与联系人关系:</td>
					          <td colspan="2"><s:property value='#request.patient.relationShip'/></td>
					        </tr>
					        <tr >
					          <td></td>
					          <td >联系人手机号码:</td>
					          <td colspan="4"><s:property value="#request.patient.relationPhone"/></td>
					        </tr>
					       <tr >
					          <td></td>
					          <td>联系人家庭电话:</td>
					          <td  colspan="4"><s:property value="#request.patient.relationHomePhone"/></td>
					        </tr>
					        <tr >
					          <td></td>
					          <td>联系人地址:</td>
					          <td colspan="4"><s:property value="#request.patient.relationAdress"/></td>
					        </tr>
                          </table>
                    </td>
                </tr>
           </table>  
           
           <!-- 其他信息 --> 
	 <table class="table_1" style="width:100%;position:relative; margin-bottom: 5px;" cellspacing="0" cellpadding="0" border="0">
                <tr>
                      <td>
                          <table class="table_top_1" border="0"  cellspacing="0" cellpadding="0" bgcolor="#90c7ff" style="width:100%;">
                              <tr bgcolor="#b1c7df">
                                 
                                 <td width="20"><img src="<%=path %>/images/2.gif" width="12"  name="img3"  style="cursor: hand; padding-left:10px;"></td>
                                 <td style="color:#5d594c;font-size:12px; height:30px; padding-left:8px; font-weight:bold;">其他相关信息</td>
                              </tr>
                           </table>
                           <table id="table3" border="0" cellspacing="2" cellpadding="0" style="width:100%; ">
                            <tr style="height: 10px;"><td colspan="6" style="height: 10px;"></td></tr>
                             <tr >
					          <td  width="27"></td>
					          <td  width="110">国籍:</td>
					          <td width="80"><s:property value='#request.patient.citizenShip'/></td>
					          <td>籍贯:</td>
					          <td colspan="2"><s:property value='#request.patient.nativePlace'/></td>
					        </tr>
					        <tr >
					          <td></td>
					          <td>学历:</td>
					          <td  colspan="4"><s:property value="#request.patient.education"/></td>
					        </tr>
					       <tr >
					          <td></td>
					          <td>邮箱:</td>
					          <td colspan="4"><s:property value='#request.patient.email'/></td>
					        </tr>
                          </table>
                    </td>
                </tr>
           </table>  
       </div> 	
	
         
         </td>
       </tr>
        <tr>
         <td colspan="7">
	         <div class="ps">
		   		
			</div>
         </td>
        </tr>
        <tr>
        	<td colspan="7">
        	<div class="l_buttom">
		        <div class="buttom_left"></div>
		     	<div class="buttom_box" style="width:80px;"><div onclick="javascript:window.history.back();">返回</div></div>
		        <div class="buttom_right"></div>
		        <div style="clear:both"></div>
		     </div>
        	</td>
        </tr>
      </table>
   
   
	</form>
  </div>

      <div style="clear:both"></div>
    </div>
     	
     
     
 </div>
	</body>
		
</html>
