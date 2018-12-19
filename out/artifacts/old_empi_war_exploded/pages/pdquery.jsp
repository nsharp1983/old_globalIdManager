<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ats.apixpdq.web.beans.PixManagerBean"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<script language="JavaScript"> 
		if (window == top) 
			location.href ="<%=path%>/mainform.jsp"; 
		</script> 
		<script type="text/javascript" src="scripts/ats.js"></script>
		<script type="text/javascript" src="scripts/jquery.js"></script>
		 <script type="text/javascript" src="scripts/My97DatePicker/WdatePicker.js"></script>

		<script>

function PdqQuery() {
	try {
		$("#isPage").val("0");
		if($("#hospitalId").val() + $("#lname").val() + $("#pinyinCode").val() + $("#phone").val() + $("#cardNo").val() + $("#identityCard").val() + $("#dob").val() + $("#address").val()!="")
		{
			return true;
		}else{
			alert("请输入查询条件");
			return false;
		}
	} catch (ex) {
		alert(ex);
	}
}

function PdqFirstQuery() {
	try {
		var pageNo=parseInt($("#pageIndexId").val());
		if(pageNo!=1&&pageNo<=parseInt("<s:property value='count'/>")){
			document.getElementById("pageIndexId").value = 1;
			$("#isPage").val("1");
			$$('PdqForm').submit();
		}
	} catch (ex) {
		alert(ex);
	}
}
//最后一页
function PdqLastQuery() {
	try {
		var pageNo=parseInt($("#pageIndexId").val());
		if(pageNo!=parseInt("<s:property value='count'/>")&&pageNo>0){
			document.getElementById("pageIndexId").value = "<s:property value='count'/>";
			$("#isPage").val("1");
			$$('PdqForm').submit();
		}
	} catch (ex) {
		alert(ex);
	}
}
//上一页
function PdqUpQuery() {
	try {
		var pageNo=parseInt($("#pageIndexId").val())-1;
		if(pageNo>0){
			document.getElementById("pageIndexId").value = "<s:property value='pageIndex-1'/>";	
			$("#isPage").val("1");
			$$('PdqForm').submit();
		}
	} catch (ex) {
		alert(ex);
	}
}
//下一页
function PdqNextQuery() {
	try {
		var pageNo=parseInt($("#pageIndexId").val())+1;
		if(pageNo>1&&pageNo<=parseInt("<s:property value='count'/>")){
			document.getElementById("pageIndexId").value = "<s:property value='pageIndex+1'/>";
			$("#isPage").val("1");
			$$('PdqForm').submit();
		}
	} catch (ex) {
		alert(ex);
	}
}

var country="<s:property  escape='false' value='pm.country'/>";
var state="<s:property  escape='false' value='pm.state'/>";
var city="<s:property  escape='false' value='pm.city'/>";

</script>
<style>
.box .center .pix_search input{ width:100px;}
.box .center .pix_search select{ width:90px;}
.box .center .pix_search td{ padding-left:0px;}

</style>
<link rel="stylesheet" href="css/style.css" type="text/css"></link>
	</head>
	<body style="background-color:#f2f2f2;" >
		 <div class="box">
    <!-- <div class="l_hand"><span></span></div>-->
     <div class="center">
       
 <!-- 表单 -->     
       <form id="PdqForm" name="PdqForm" method="post" onsubmit="return PdqQuery();" action="patient_PdqQuery.action?pm.atype=PdqQuery">

<!-- 页码 -->
				<input type="hidden" id="pageIndexId" name="pageIndex" value="<s:property value='pageIndex'/>" />
<!-- 标识是否查询还是分页，分页不再查 -->
				<input type="hidden" id="isPage" name="isPage" value="0"/>
       <div class="pix_search">
      
        <table width="100%" >
          <tr>
          <td colspan="7">机构类型:
          		<select id="systemid" name="pm.systemid" style="width:269px;"/>
          			<s:iterator value="#request.PixManagerBean.assigninglist.keySet()" var="key">
						<option <s:if test="pm.systemid==#key">selected="selected"</s:if> ><s:property value="#key"/></option>
					</s:iterator>
          	</select>
          </td>
           
            <td>&nbsp;&nbsp;</td>
            <td colspan="2" align="right" style="padding-right: 4px;"><input type="submit" value="查询" style="width:60px;" /></td>
          </tr>

          <tr>
          	<td width="50">住&nbsp;&nbsp;院&nbsp;&nbsp;号:</td>
            <td >
            	<input type="text" style="width: 70px;" name="pm.hospitalId" id="hospitalId" value="<s:property value='pm.hospitalId'/>"/>
            </td>
			<td width="30">卡&nbsp;&nbsp;&nbsp;&nbsp;号:</td>
            <td >
            	<input type="text" style="width:140px;" name="cardNo" id="cardNo" value="<s:property value='pm.insuranceCard'/>"/>           	
            </td>
            <td>卡类型:</td>
            <td>
				<select  name="cardType">
					<option value="0">医保号</option>
					<option value="1">社保号</option>
				</select>
			</td>
            <td>证件号:</td>
            <td ><input type="text"  name="identityCard" id="identityCard" value="<s:property value='pm.identityCard'/>"/></td>
            <td >证件类型:</td>
            <td>
            	
            	<select  name="identitrType">
            		<option value="0">身份证</option>
            		
            	</select>
            </td>
          </tr>
          <tr>
          	 <td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</td>
            <td><input type="text" size="12" name="pm.lname" id="lname" style="width: 70px;" value="<s:property value='pm.lname'/>"/></td>
            
            <td colspan="8">拼音码:
            	<input type="text" name="pm.pinyinCode"  id="pinyinCode" style="width: 60px;" value="<s:property value='pm.pinyinCode'/>"/>
           		 联系电话:
           		 <input type="text" name="pm.phone"  id="phone" style="width: 77px;" value="<s:property value='pm.phone'/>"/>
           		出生日期:
            	<input name="pm.dob" id="dob" style="width:128px;" size="36" value="<s:property value='pm.dob'/>" type="text" onfocus="WdatePicker({lang:'zh-cn',maxDate:'%y-%M-%d'})"  onkeydown="return false;" oncontextmenu="return false;" onpaste="return false;"/>      
           		 家庭地址:&nbsp;<input type="text" id="address" name="pm.address"  style="width:130px;" value="<s:property value='pm.address'/>"/></td>
          </tr>

          </table></div>

      </form>
      
<!-- 查询结果 -->
      <div class="search_list">
        <div class="s_button">
           <div class="rd" onclick="PdqLastQuery()"></div>
			<div class="rr" onclick="PdqNextQuery()" ></div>
			<div class="ll" onclick="PdqUpQuery()"></div>
			<div class="ld" onclick="PdqFirstQuery()"></div>
        </div>
        <div class="s_list">
          <table width="100%" class="list_table" >
             <tr height="31" align="center" style="background-color:#b1c7df;">
               <td width="30">序号</td>
               <td width="80">病人ID</td>
               <td width="80">姓名</td>
               <td width="80">出生日期</td>
               <td width="40">性别</td>
               <td width="100">身份证号</td>
               <td>地址</td>
               <td width="100">编辑</td>
             </tr>
			<s:iterator value="#request.beanList" var="patient" status="i">
					<tr height="30" align="center" bgcolor="#EEEEEE">
						<td>
						<s:property value="#i.getCount()"/>
						</td>
						<td><s:property value="#patient.hospitalId"/></td>
						<td>
							<s:property value="#patient.nameString"/>
						</td>
						<td>
							<s:property value="#patient.time()"/>
						</td>
						<td>
							<s:property value="#patient.gender"/>
						</td>
						<td>
							<s:property value="#patient.identityCard"/>
						</td>
						<td>
							<s:property value="#patient.fullAddress"/>
						</td>
						<td>
						
							<div class="see_box" style="border:1px soild red;">
								
				                   <div class="see" style="text-align:left; width:45px;height: 14px; cursor: pointer; float: left;"><a title="查看" href="<%=path %>/patient_patientInfo.action?pm.atype=select&&personIndex=<s:property value="#i.getIndex()"/>">
				                   		<img src="<%=path %>/images/clear.png" border="0" width="40px" height="14px"/></a>
				                   	</div>
				                   <div class="see_a" style="text-align:left;width:45px;height: 14px;cursor: pointer; float: left;"><a title="编辑" href="<%=path %>/patient_patientInfo.action?pm.atype=update&&&&personIndex=<s:property value="#i.getIndex()"/>">
				                   		<img src="<%=path %>/images/clear.png" border="0"  width="40px" height="14px"></img></a>
				                   	</div>	          
				                 </div>
							  
						</td>
             	</tr>
			</s:iterator>
           
             <s:if test="#request.beanList!=null&&#request.beanList.size()<=0">
				<tr height="30" align="center">
					<td colspan="8" align="center">
						没有查询到相关记录！！
					</td>
				</tr>
			</s:if>
          
           </table>
        </div>
        <div class="s_button">
            <div class="rd" onclick="PdqLastQuery()"></div>
			<div class="rr" onclick="PdqNextQuery()" ></div>
			<div class="ll" onclick="PdqUpQuery()"></div>
			<div class="ld" onclick="PdqFirstQuery()"></div>
           <div style="float:right; width:580px; text-align:center; padding-top:5px; font-weight:bold; font-size:12px;">
             		 <s:if test="#request.beanList!=null&&#request.beanList.size()>0">
						第<s:property value="#request.pageIndex" />页/共<s:property value="count" />页
					</s:if>
           </div>
        </div>
      </div>
    </div>
     
 </div>
	</body>
	
</html>
