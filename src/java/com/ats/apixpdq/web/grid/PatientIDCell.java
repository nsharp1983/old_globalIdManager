package com.ats.apixpdq.web.grid;

import java.util.List;

import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.renderer.HtmlCellRendererImpl;
import com.ats.aexchange.datamodel.PatientIdentifier;

import com.ats.apixpdq.web.beans.PixManagerBean;

/**
 * @author Reddy
 *
 */
public class PatientIDCell extends HtmlCellRendererImpl {

	  PixManagerBean bean = null;
	  @Override
	  public Object render(Object item, int rowcount) {
		String property = getColumn().getProperty();
		Object value = getCellEditor().getValue(item, property, rowcount);
		Object bean = getCellEditor().getValue(item, "bean", rowcount);
		 
		HtmlBuilder html = new HtmlBuilder();
		html.td(2);
		html.width(getColumn().getWidth());
		if (property.equalsIgnoreCase("nameString")) {
			html.style(" text-decoration: underline");
			
		} else {
			html.style(getStyle());
		}		
		html.styleClass(getStyleClass());
		
		html.close();
		html.span();
		html.close();
		String hid =null;

		if (bean instanceof PixManagerBean) {
			 PixManagerBean pixbean = (PixManagerBean) bean;
			 List<PatientIdentifier> pidlist = pixbean.getPidlist();
			 hid =pixbean.getNameString()+pixbean.getDob()+pixbean.getFullAddress();
			 hid = hid.replaceAll(",", "");
			 hid = hid.replaceAll("/","" );	 
		String patientIDTooltipDiv = com.ats.apixpdq.web.grid.Tooltip.getPatientIDTooltipDiv(pidlist,"domTT_"+hid);
		html.append(patientIDTooltipDiv);
		}

		html.append("<a onMouseOver=\"domTT_activate(this, event, 'caption','List of matched Patient IDs','content', document.getElementById('domTT_"+hid+"') , 'fade', 'both', 'fadeMax', 87, 'position', 'absolute', 'delay', 0);\">");
		
		if (value != null) {
			html.append(value.toString());
		}
		html.append("</a>");
		html.spanEnd();
		html.tdEnd();
		
		return html.toString();
	}
}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jmesa.view.editor.CellEditor#getValue(java.lang.Object,
	 *      java.lang.String, int)
	 * 
	 * 
	 * public Object getValue(Object item, String property, int rowcount) {
	 * //bean=(PixManagerBean)model.getCurrentCollectionBean();
	 * //model.getCurrentCollectionBean(); Object value =
	 * ItemUtils.getItemValue(item, property); HtmlBuilder html = new
	 * HtmlBuilder();
	 * //html.a().href().quote().append("http://www.whitehouse.gov/history/presidents/").quote().close();
	 * html.span();
	 * 
	 * html.append(value); html.aEnd(); html.spanEnd(); html.onmouseover("");
	 * return html.toString(); } }
	 */
//text-overflow: ellipsis; /* IE only */
//overflow: hidden; 
//white-space: nowrap;
