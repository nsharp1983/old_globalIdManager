package com.ats.controller;

import com.ats.aempi.model.Person;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import com.ats.util.JsonValueProcessor2;
import java.util.Date;

public class TestBeanToStr {

    public static void main(String[] args) {
        Person person=new Person();
        person.setBirthTime(new Date());
        JsonConfig jsonConfig=new JsonConfig();
//        jsonConfig.registerJsonValueProcessor(Person.class,Date.class,new JsonValueProcessor2());
        jsonConfig.setJavaPropertyFilter(new PropertyFilter() {
            @Override
            public boolean apply(Object object, String key, Object value) {
                boolean result=true;
                if(null==value){
                   result=false;
                }
                return result;
//                return false;
            }
        });
        JSONObject jsonObject=JSONObject.fromObject(person,jsonConfig);
        String jsonStr=jsonObject.toString();
        System.out.println(jsonStr);
    }
}
