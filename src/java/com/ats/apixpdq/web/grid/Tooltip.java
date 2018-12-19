package com.ats.apixpdq.web.grid;

import java.util.List;

import com.ats.aexchange.datamodel.Identifier;
import com.ats.aexchange.datamodel.PatientIdentifier;

public class Tooltip {
	

	public static final String getPatientIDTooltipDiv(List<PatientIdentifier> pids,String divId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<div style=\"display:none\" id=\"tooltips\">");
		buffer.append("<div id=\"");
		buffer.append(divId);
		buffer.append("\">");
		
		buffer.append("<table class=\"pidTooltip\" border=\"1\" cellspacing=\"0\">");
		//buffer.append("<thead><th align=\"center\"> List of Matched Patient IDs </th></thead>");
		buffer.append("<tr><td>");
		buffer.append("<table class=\"demographicTooltip\" colspan=\"2\" border=\"1\" cellspacing=\"0\" style=\" font-family: Verdana, Helvetica; font-size: 12px;\">");
		buffer.append("<tr style=\" font-weight: bold;\"><td align=\"center\">Patient ID </td><td align=\"center\">Assigning Authority</td></tr>");
		
		if(pids != null){
			for(PatientIdentifier pid: pids){
			Identifier id = pid.getAssigningAuthority();
			String assigningAuth = id.getNamespaceId()+id.getUniversalId()+id.getUniversalIdType(); 
				buffer.append("<tr><td>"+pid.getId()+"</td><td>"+assigningAuth+"</td></tr>");
			
			}
		}
		buffer.append("</table>");
		buffer.append("</td></tr>");
	
		buffer.append("</table></div></div>");
		return buffer.toString();
	}
}
