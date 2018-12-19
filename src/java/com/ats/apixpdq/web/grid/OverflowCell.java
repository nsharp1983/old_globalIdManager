package com.ats.apixpdq.web.grid;

import org.apache.commons.lang.StringUtils;
import org.jmesa.util.ItemUtils;
import org.jmesa.view.html.HtmlBuilder;
import org.jmesa.view.html.renderer.HtmlCellRendererImpl;

public class OverflowCell extends HtmlCellRendererImpl  {
	/** the css style that is used for overflow cells */
	static public String STYLE_OVERFLOW = "overflow";
	
	/** the javascript that is added to a mouseover of a overflow cell */
	static public String JS_MOUSEOVER_OVERFLOW = "overflowTooltip(this, event);";

	@Override
	public Object render(Object item, int rowcount) {
		String property = getColumn().getProperty();
		
		HtmlBuilder html = new HtmlBuilder();		
		html.td(2);
		
		if (StringUtils.isNotEmpty(getStyleClass())) {
			html.styleClass(getStyleClass() + " "+STYLE_OVERFLOW);
		}
		else {
			html.styleClass(STYLE_OVERFLOW);
			//html.style(" text-overflow: ellipsis; overflow: hidden;	white-space: nowrap; ");
		}
		html.style(getStyle());
		if (StringUtils.isNotEmpty( getColumn().getWidth())) {
			html.width(getColumn().getWidth());
		}
		html.onmouseover(JS_MOUSEOVER_OVERFLOW);
		html.close();
		Object value = ItemUtils.getItemValue(item, property);
		if (value != null && StringUtils.isNotEmpty(value.toString())) {
			html.span();			
			html.close();
			html.append(value);
			html.spanEnd();
		} 
		else {
			html.nbsp();
		}
	
		html.tdEnd();

		return html.toString();
	}

	/*@Override
	public Object getValue(Object item, String property, int rowcount) {
		HtmlBuilder html = new HtmlBuilder();		
		html.td(2);
		if (StringUtils.isNotEmpty(getStyleClass())) {
			html.styleClass(getStyleClass() + " "+STYLE_OVERFLOW);
		}
		else {
			//html.styleClass(STYLE_OVERFLOW);
			html.style(" text-overflow: ellipsis; overflow: hidden;	white-space: nowrap; ");
		//}
		//html.style(getStyle());
		
		if (StringUtils.isNotEmpty( getColumn().getWidth())) {
			html.width(getColumn().getWidth());
		}
		html.onmouseover(JS_MOUSEOVER_OVERFLOW);

		html.close();
		Object value = ItemUtils.getItemValue(item, property);
		if (value != null && StringUtils.isNotEmpty(value.toString())) {
			html.span();			
			html.close();
			html.append(value);
			html.spanEnd();
		} 
		else {
			html.nbsp();
		}

		html.tdEnd();

		return html.toString();
	}*/
}
