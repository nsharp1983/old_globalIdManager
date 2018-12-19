<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ats.aexchange.datamodel.PatientIdentifier"%>
<%@page import="com.ats.apixpdq.web.beans.PixManagerBean"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<script type="text/javascript">
			var path="<%=request.getContextPath() %>";
			if (window == top) 
			location.href =path+"/mainform.jsp"; 
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />


	
		<script type="text/javascript" src="scripts/ats.js"></script>
		<script type="text/javascript" src="scripts/jquery.js"></script>
		 <script type="text/javascript" src="scripts/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="scripts/jquery.validate.js"></script>
		<script type="text/javascript" src="scripts/jquery.metadata.js"></script>
		 <script type="text/javascript" src="scripts/oecpshowerror.js"></script>
		 <script type="text/javascript">
		 
		 
			$(document).ready(function(){
				$('#PixForm').validate({
					showErrors: showError
				});
			});
			
	

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

function PixFirstQuery() {
	try {
		var pageNo=parseInt($("#pageIndexId").val());
		if(pageNo!=1&&pageNo<=parseInt("<s:property value='count'/>")){
			document.getElementById("pageIndexId").value = 1;
			$$('PixForm').submit();
		}
	} catch (ex) {
		alert(ex);
	}
}
//最后一页
function PixLastQuery() {
	try {
		var pageNo=parseInt($("#pageIndexId").val());
		if(pageNo!=parseInt("<s:property value='count'/>")&&pageNo>0){
			document.getElementById("pageIndexId").value = "<s:property value='count'/>";
			$$('PixForm').submit();
		}
	} catch (ex) {
		alert(ex);
	}
}
//上一页
function PixUpQuery() {
	try {
		var pageNo=parseInt($("#pageIndexId").val())-1;
		if(pageNo>0){
			document.getElementById("pageIndexId").value = "<s:property value='pageIndex-1'/>";
			$$('PixForm').submit();
		}
	} catch (ex) {
		alert(ex);
	}
}
//下一页
function PixNextQuery() {
	try {
		var pageNo=parseInt($("#pageIndexId").val())+1;
		if(pageNo>1&&pageNo<=parseInt("<s:property value='count'/>")){
			document.getElementById("pageIndexId").value = "<s:property value='pageIndex+1'/>";
			$$('PixForm').submit();
		}
	} catch (ex) {
		alert(ex);
	}
}
</script>
<link rel="stylesheet" href="css/style.css" type="text/css"></link>
	</head>
	<body style="background-color:#f2f2f2;">
		<div class="box">
    <!-- <div class="l_hand"><span></span></div>-->
     <div class="center">
      
<!-- 表单 -->
      <form id="PixForm" name="PixForm" method="post" action="patient_PixQuery.action?pm.atype=PixQuery">
<!-- 隐藏区域 	 页码 -->
		<input type="hidden" id="pageIndexId" name="pageIndex" value="<s:property value='pageIndex'/>" />
	      <div class="pix_search" >
	        <table >
	          <tr>
	            <td width="80px;">发布机构：</td>
	            <td width="200px;">
	            	<select  name="pm.systemid" id="systemid" style="width:150px;">
	            		<s:iterator value="#request.PixManagerBean.assigninglist.keySet()" var="key">
						<option <s:if test="#key==#request.pm.systemid">selected="selected"</s:if>><s:property value="#key"/></option>
					</s:iterator>
	            	</select>
	            </td>
	            <td width="80px;">病人ID:</td>															
	            <td width="200px;"><input id="pid"  onfocus="javascript:this.value='';" type="text" class="required" name="pm.localid" value="<s:property value='#request.pm.localid' />"/></td>
	            <td width="200px;"><input type="submit" value="查询" /></td>
	          </tr>
	        </table>
	      </div>
	   	</form>
      
      <div class="search_list">
        <div class="s_button">
           <div class="rd" onclick="PixLastQuery()"></div>
			<div class="rr" onclick="PixNextQuery()"></div>
			<div class="ll" onclick="PixUpQuery()"></div>
			<div class="ld" onclick="PixFirstQuery()"></div>
        </div>
        <div class="s_list">
          <table width="100%" class="list_table" >
             <tr height="31" align="center" style="background-color:#b1c7df;">
               <td width="30"></td>
               <td width="300">ID</td>
               <td width="150">ID颁发机构名称</td>
               <td width="220">ID颁发机构标识</td>
               <td width="40">类型</td>            
               <td  >编辑</td>
             </tr>
             <s:iterator value="#request.beanList" var="patient" status="i">
					<tr height="30" align="center">
               			<td><input type="checkbox" /></td>
						<td>
							<s:property value="#patient.id"/>
						</td>
						<td>
							<s:property value="#patient.getAssigningAuthority().getNamespaceId()"/>
						</td>
						<td>
							<s:property value="#patient.getAssigningAuthority().getUniversalId()"/>
						</td>
						<td>
							<s:property value="#patient.getAssigningAuthority().getUniversalIdType()"/>
						</td>					
						<td >
							
						</td>
             </tr>
           </s:iterator>
             <s:if test="#request.beanList!=null&&#request.beanList.size()<=0">
						<tr height="30" align="center">
							<td colspan="7" align="center">
								没有查询到相关记录！！
							</td>
						</tr>
					</s:if>
           </table>
        </div>
        <div class="s_button">
          <div class="rd" onclick="PixLastQuery()"></div>
			<div class="rr" onclick="PixNextQuery()"></div>
			<div class="ll" onclick="PixUpQuery()"></div>
			<div class="ld" onclick="PixFirstQuery()"></div>
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