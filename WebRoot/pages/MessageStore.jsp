

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<link rel="stylesheet" href="<c:url value='/css/jmesa.css'/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/css/table.css'/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/css/domTT.css'/>" type="text/css" />
<script type="text/javascript" src="<c:url value='/scripts/jmesa.js'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/scripts/domLib.js"/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value="/scripts/domTT.js"/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/messagestore.js'/>"></script>
<!--script type="text/javascript" src="<c:url value='/scripts/grid.js'/>"></script>  -->



<input type="hidden" id="contextRoot" name="contextRoot" value="<%=request.getContextPath() %>">
<table class="TableTS" cellpadding="0" cellspacing="0">
 	<thead class="TableTS" align="left">
		<th class="TableTS">Message Log - SearchCriteria:</th>
	</thead>
	<tr><td><div class="Table">
       <html:form action="MessageStore.do" method="post">
  		<table class="TableMS">
			
		<tr>
			<td align="center">IP</td>
			<td align="left"><html:text property="ip"  size="18" maxlength="80" /></td>
			<td align="center">MessageId</td>
			<td align="left"><html:text property="messageId" size="18" maxlength="80" /></td>
			<td align="center">ErrorMessage</td>
			<td align="left"><html:text property="errorMessage" size="18" maxlength="255" /></td>
			<td align="center">SendingFacility</td>
			<td align="left"><html:text property="sendingFacility" size="18" maxlength="80" /></td>
			
		</tr>		
		<tr>
			<td align="center">SendingAppl</td>
			<td align="left"><html:text property="sendingApplication" size="18"	maxlength="80" /></td>
			<td align="center">ReceivingFacility</td>
			<td align="left"><html:text property="receivingFacility" size="18" maxlength="80" /></td>		
			<td align="center">ReceivingAppl</td>
			<td align="left"><html:text property="receivingApplication" size="18" maxlength="80" /></td>		
			<td align="center">Message Date</td>
			<td align="left"><html:text property="messageDate" value="MM/DD/YYYY" size="18"/></td>	
			
		</tr>
		<tr width="100%">
			<td align="right"><html:submit property="action" value="Search" ></html:submit></td>
        </tr>
 </table>
<table class = "TableJMESA" cellpadding="0" cellspacing="0">
<tr><td>
 <div id="tabletag">
 
   <jmesa:tableFacade id="tag" 
                       items="${personlist}"             
                       maxRows="10"
                       editable="false"
                       stateAttr="restore" var="bean" rowFilter="" >
                       <jmesa:htmlTable width="100%">              
                               <jmesa:htmlRow uniqueProperty="messageId"> 
                                      <jmesa:htmlColumn title="IP" property="ip" width="40px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>
                                      <jmesa:htmlColumn title="MessageId" property="messageId" width="100px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell" />
                                      <jmesa:htmlColumn title="Error<br/>Message" property="errorMessage" width="110px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell" /> 
                                      <jmesa:htmlColumn title="Sending<br/>Facility" property="sendingFacility" width="150px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>
                                      <jmesa:htmlColumn title="Sending<br/>Application" property="sendingApplication" width="100px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>
                                      <jmesa:htmlColumn title="Receiving<br/>Facility" property="receivingFacility" width="150px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>
                                      <jmesa:htmlColumn title="Receiving<br/>Application" property="receivingApplication" width="100px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>
                                      <jmesa:htmlColumn title="MessageDate" property="messageDate" pattern="yyyy-MM-ddHH:mm:SS.sssz" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>
                                      <jmesa:htmlColumn title="Event" property="triggerEvent" width="60px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>
                                      <jmesa:htmlColumn title="Input<br/>Message" property="inMessage" width="100px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>                                                                                                                                                      
									  <jmesa:htmlColumn  title="Output<br/>Message" property="outMessage" width="100px" cellRenderer="com.ats.apixpdq.web.grid.OverflowCell"/>
									  
                               </jmesa:htmlRow>
                       </jmesa:htmlTable> 
               </jmesa:tableFacade>
</div></td></tr>
</table>
  </html:form></div>
  </td></tr>
</table>
<script>
	dispLogDetails(); 
</script>