package com.ats.util;

import com.ats.aexchange.utils.DateUtil;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Date;

public class JsonValueProcessor2  implements JsonValueProcessor {


    public JsonValueProcessor2(){

    }


    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return o;
    }

    @Override
    public Object processObjectValue(String s, Object object, JsonConfig jsonConfig) {
        Object result=object;
        if (null!=object&&object instanceof Date){
            Date date= (Date) object;
            result =  DateUtil.formatDateTime(date);
        }
        return result;
    }




}
