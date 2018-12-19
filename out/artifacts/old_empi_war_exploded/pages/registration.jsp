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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-5" />
		<title>病人注册</title>
		
		<script type="text/javascript">
			var path="<%=path %>";
			if (window == top) 
				location.href ="<%=path%>/mainform.jsp"; 
			var country="<s:property  escape='false' value='pm.country'/>";
			var state="<s:property  escape='false' value='pm.state'/>";
			var city="<s:property  escape='false' value='pm.city'/>";
								
		</script>

<style>
.box{ width:800px; margin:0 auto;}
.box td{ font-size:12px; font-weight:bold;}
.box input{ height:20px; font-size:14px; width:315px; border:1px solid #dad9d9;}
.box select{ height:24px; font-size:14px; width:320px; border:1px solid #dad9d9;}
.mg_left{ float:left; width:240px; padding:0px 30px 0px 40px;}
.box .l_hand{ width:800px;}
.mg_right{ float:right;border-left:1px solid #CCCCCC; background-color: #f6f6f6; width:480px; }
.l_b_s{  float:left;height:100%; width:99px; font-weight:bold; display:block; text-align:center; border-right:#9d9b9b solid 1px;}
.l_b_s{filter:Alpha(opacity=30);-moz-opacity:0.30;opacity:0.30;zoom:1;}
</style>
		<script type="text/javascript" src="<%=path %>/scripts/ats.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.metadata.js"></script>
		 <script type="text/javascript" src="<%=path %>/scripts/My97DatePicker/WdatePicker.js"></script>
		 <script type="text/javascript" src="<%=path %>/scripts/adderss.js"></script>
		 <script type="text/javascript" src="<%=path %>/scripts/oecpshowerror.js"></script>
		<script language="JavaScript" type="text/javascript" >
		
		var regisMsg="<s:property value='#request.RegistMsg'/>";
		
		if(regisMsg!=""&&regisMsg!=null){
			alert("<%=request.getAttribute("RegistMsg") %>");
		}
		$.validator.addMethod("addersss", function(value, element) {
		    return $("#country").val() !== '国家' && $("#state").val() !== '省份、州'&&$("#city").val() !== '地级市、县';
		}, "请选择国家、省份、地区市");
		$(document).ready(function(){
			$('#RegForm').validate({
				showErrors: showErrors,	//使用自定义的错误显示方法
				groups: {addersss: "pm.country pm.state pm.city" },
			    "pm.country": "addersss",
			    "pm.state": "addersss",
			    "pm.city":"addersss"
			   
			});
		});
		
		
			
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
	function hve_display(t_id,i_id){//显示隐藏程序
		var t_id;//表格ID
		var i_id;//图片IDa
		var on_img="images/1.gif";//打开时图片
		var off_img="images/2.gif";//隐藏时图片
		if (t_id.style.display == "none") {//如果为隐藏状态
			t_id.style.display="";//切换为显示状态
			i_id.src=on_img;}//换图
		else{//否则
			t_id.style.display="none";//切换为隐藏状态
			i_id.src=off_img;
		}//换图
	}
</script>
	<link rel="stylesheet" href="<%=path %>/css/style.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=path %>/css/oecpvalidate.css" type="text/css"></link></head>
	<body style="background-color:#f2f2f2;">
	 <div class="box" >
     <div class="center"  style="border:1px solid #CCCCCC; background-color:#F5F5f5; margin-top:5px;">
     <div class="mg_left" style="padding-top:10px; " align="center">
        <div><img src="<%=path %>/images/patient_1.png" /></div>
     </div>
     <div class="mg_right" style="background-color: #fafafa;">
     <form id="RegForm" name="RegForm" action="patient_Regist.action" method="post">
     <div>
     	<table class="table_top_1" border="0" height="30" cellspacing="0" cellpadding="0" bgcolor="#90c7ff" style="width:100%;">
           <tr bgcolor="#b1c7df">
              <td width="20"></td>
              <td style="color:#5d594c;font-size:12px; padding-left:8px; font-weight:bold;">病人基本信息</td>
           </tr>
        </table>
     </div>
     
      <table width="100%" align="center" style=" margin:0 atuo;" cellspacing="0" cellpadding="0" border="0" >
        <tr><td height="10" colspan="7"></td></tr>
        <tr height="20">
          <td width="30"></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td width="360" colspan="5">发布机构:</td>
        </tr>
         <tr height="30">
          <td></td>
          <td colspan="6">
<!-- 隐藏域 -->          
          <input id="atype" name="pm.atype" value="Save" type="hidden"/>
          	<select id="systemid" name="pm.systemid" />
          			<s:iterator value="#request.PixManagerBean.assigninglist.keySet()" var="key">
						<option ><s:property value="#key"/></option>
					</s:iterator>
          	</select>
          </td>
        </tr>
        <tr height="20">
          <td ></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td colspan="5">病人ID:</td>
        </tr>
         <tr height="30">
          <td></td>
          <td colspan="6"><input  id="localid" name="pm.localid"   class="{required:true,messages:{required:'请输入病人ID！！'}}"  type="text" size="18"/></td>
          
        </tr>
        <tr height="20">
          <td ></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td style="width:20px;">姓名:</td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td colspan="3">出生日期:</td>
        </tr>
         <tr height="30">
          <td></td>
          <td colspan="3" ><input  style="width:120px;" type="text" class="required" id="lname" name="pm.lname" /></td>
          <td colspan="3"><input style="width:175px;" name="pm.dob" class="required"  id="dob"  size="36"  type="text" onFocus="WdatePicker({lang:'zh-cn',maxDate:'%y-%M-%d'})"  onkeydown="return false;" oncontextmenu="return false;" onpaste="return false;"/></td>
        </tr>
        <tr height="20">
          <td></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td >性别:</td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td >婚姻:</td>
         
        </tr>
        <tr>
        	<td></td>
        	<td colspan="2">
        		<select id="gender" name="pm.gender" style="width:125px;">
					<option value="M" <s:if test="#request.patient.gender==M">selected="selected"</s:if> >男</option>
					<option value="F" <s:if test="#request.patient.gender==F">selected="selected"</s:if> >女</option>
					<option value="O" <s:if test="#request.patient.gender==O">selected="selected"</s:if> >其他</option>
				</select>
        	</td>       	
        	 <td  ></td>
        	 <td colspan="2" >
	  		 
		  		<select  name="pm.maritalStatus" style="width:125px;">
          			<s:iterator value="#request.maritalList" var="marital">
						<option value="<s:property value='#marital.maritalStatusCode'/>"><s:property value="#marital.comments"/></option>
					</s:iterator>
				</select>
		  		</td>
          
        </tr>
		 
        
         <tr height="20">
          <td></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td colspan="5">联系电话:</td>
        </tr>
        <tr height="30">
          <td></td>
          <td colspan="6"><input type="text" class="{required:true,isTel:true}"   name="pm.homePhone" size="18"/></td>
        </tr>
       <tr height="20">
          <td></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td colspan="5">身份证号码:</td>
        </tr>
         <tr height="30">
          <td></td>
          <td colspan="6"><input  class="{isIdCardNo:true,required:true}"   type="text" name="pm.identityCard" size="18"/></td>
        </tr>
        <tr height="20">
          <td></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td colspan="5">医保卡号码:</td>
        </tr>
         <tr height="30">
          <td></td>
          <td colspan="6"><input class="required"  type="text" name="pm.insuranceCard" size="18"/></td>
        </tr>
        <tr height="20">
          <td></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td colspan="5">社保卡号码:</td>
        </tr>
         <tr height="30">
          <td></td>
          <td colspan="6"><input  class="required" name="pm.socialCard" type="text" size="18"/></td>
        </tr>
        
       
       
        <tr height="20">
          <td></td>
          <td width="5" style="color:#FF0000;" align="right">*</td>
          <td colspan="5">地址:</td>
        </tr>
         <tr height="30">
          <td></td>
          <td colspan="6"><input class="required"  id="address" name="pm.address"   type="text" size="18"/></td>
        </tr>

       <tr>
         <td colspan="7">
            <div class="vip_box" style="width:100%;"  >   <!--最外框-->
            
<!-- 单位信息 --> 
              <table class="table_1" style="width:100%; margin:10px 0px 5px 0px;" cellpadding="0" cellspacing="0" disabled="true">
                <tr>
                      <td>
                          <table class="table_top_1" border="0" height="20" cellspacing="0" cellpadding="0" bgcolor="#90c7ff" style="width:100%;">
                              <tr bgcolor="#90c7ff">
                                 
                                 <td width="20"><img src="<%=path %>/images/2.gif"  name="img2" onClick="hve_display(table2,img2)" style="cursor: hand; padding-left:10px;"/></td>
                                 <td style="color:#5d594c;font-size:12px; padding-left:8px; font-weight:bold;">工作单位相关信息</td>
                              </tr>
                           </table>                       
                          <table id="table2"  cellspacing="2" cellpadding="0" border="0"  align="center" style="width:100%; display:none;">
                          	<tr height="20">
                               	<td width="27"></td>
					          <td colspan="5">公司名称:</td>
					        </tr>
					         <tr height="30">
					         	<td width="27"></td>
					          <td colspan="5">
					          	<input  name="pm.companyName"  type="text" size="18"/>
							</td>
							
					        </tr>

					         <tr height="20">
					          <td  width="27"></td>
					          <td width="110" >公司电话:</td>
					          <td  colspan="4">公司邮箱:</td>
					        </tr>
					         <tr height="30">
					         <td width="27"></td>
					          <td colspan="1"><input style="width:95px;" type="text" name="pm.companyPhone"  /></td>
					          <td colspan="4"><input style="width:205px;"  class="email" type="text"  name="pm.companyEmail"  /></td>
					        </tr>
					        
					        
					        <tr height="20">
		
					          <td ></td>
					          <td colspan="5">公司地址:</td>
					        </tr>
					         <tr height="30">
					          <td></td>
					          <td colspan="5"><input  id="localid" name="pm.companyAdderss"   type="text" size="18"/></td>
					        </tr>
                          	
                          
                          </table>
                    </td>
                </tr>
           </table> 
           
 <!-- 联系人信息 -->  
              <table class="table_1" style="width:100%;position:relative; margin-bottom: 5px;"  cellpadding="0" cellspacing="0" disabled="true">
                <tr>
                      <td>
                          <table class="table_top_1" border="0" height="20" cellspacing="0" cellpadding="0" bgcolor="#90c7ff" style="width:100%;">
                              <tr bgcolor="#90c7ff">
                                 
                                 <td width="20"><img src="<%=path %>/images/2.gif" width="12" height="20" name="aaa" onClick="hve_display(xxx,aaa)" style="cursor: hand; padding-left:10px;"/></td>
                                 <td style="color:#5d594c;font-size:12px; padding-left:8px; font-weight:bold;">联系人相关信息</td>
                              </tr>
                        </table>
                           <table id="xxx"  cellspacing="2" cellpadding="0"  style="width:100%; display:none;">
                             
						   
					         <tr height="20">
					          <td  width="27"></td>
					          <td width="110">联系人姓名:</td>
					          <td colspan="4">与联系人关系:</td>
					        </tr>
					         <tr height="30">
					         <td width="27"></td>
					          <td colspan="1"><input style="width:95px;" type="text"  name="pm.relationName"  /></td>
					          <td colspan="4">
					          		<select name="pm.relationShip" style="width:205px;">
					          			<s:iterator value="#request.relationshipList" var="relation">
					          				<option value="<s:property value='#relation.relationshipTypeCd'/>"><s:property value="#relation.relationshipTypeName"/></option>
					          			</s:iterator>
					          		</select>
					          </td>
					        </tr>
					        <tr height="20">
					          <td></td>
					          <td colspan="5">联系人手机号码:</td>
					        </tr>
					        <tr height="30">
					          <td></td>
					          <td colspan="5"><input  type="text" name="pm.relationPhone" size="18"/></td>
					        </tr>
					       <tr height="20">
					          <td></td>
					          <td colspan="5">联系人家庭电话:</td>
					        </tr>
					         <tr height="30">
					          <td></td>
					          <td colspan="5"><input type="text" name="pm.relationHomePhone" size="18"/></td>
					        </tr>
					        <tr height="20">
					          <td></td>
					          <td colspan="5">联系人地址:</td>
					        </tr>
					         <tr height="30">
					          <td></td>
					          <td colspan="5"><input type="text" name="pm.relationAdress" size="18"/></td>
					        </tr>
					      
                          </table>
                    </td>
                </tr>
           </table>  
           
           <!-- 其他信息 --> 
	 <table class="table_1" style="width:100%;position:relative; margin-bottom: 5px;" cellspacing="0" cellpadding="0" border="0" disabled="true">
                <tr>
                      <td>
                          <table class="table_top_1" border="0" height="20" cellspacing="0" cellpadding="0" bgcolor="#90c7ff" style="width:100%;">
                              <tr bgcolor="#90c7ff">
                                 
                                 <td width="20"><img src="<%=path %>/images/2.gif" width="12" height="20" name="img3" onClick="hve_display(table3,img3)" style="cursor: hand; padding-left:10px;"/></td>
                                 <td style="color:#5d594c;font-size:12px; padding-left:8px; font-weight:bold;">其他相关信息</td>
                              </tr>
                           </table>
                           <table id="table3" border="0" cellspacing="2" cellpadding="0" style="width:100%; display:none;">
                             <tr height="20">
					          <td  width="27"></td>
					          <td  colspan="5" width="110">国籍:</td>
					        </tr>
					         <tr height="30">
					         <td width="27"></td>
					          <td colspan="5">
					          		<select name="pm.citizenShip">
					          			<s:iterator value="#request.nationalityList" var="nationality">
											<option value="<s:property value='#nationality.nationalityCd'/>"><s:property value="#nationality.nationalityName"/></option>
										</s:iterator>
					          		</select>
					          </td>
					        </tr>
					       <tr height="20">
					          <td></td>
					          <td colspan="5">邮箱:</td>
					        </tr>
					         <tr height="30">
					          <td></td>
					          <td colspan="5"><input   type="text" name="pm.email" size="18"/></td>
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
           <div class="l_buttom">
            <div class="buttom_left"></div>
     <div class="buttom_box">
           <div  class="l_b_s"><input type="submit" style="width:100px;border: none;height: 34px;"  value="注册" class="l_b_s"/></div>
           <div class="l_b_c"  onclick="clearForm();">重置</div>
       </div>
       <div class="buttom_right"></div>
       <div style="clear:both"></div>
     </div>
     </td>
        </tr>
        <tr>
         <td colspan="7">
         <div class="ps">
   		<!-- PIX服务器IP地址:<s:property value="#request.serverport"/> -->
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
