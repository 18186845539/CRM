package org.jsoft.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mym
 * ��request���������в���ȫ����ȡ��������װ��map��
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RequestTool {
	
	public static Map getParameterMap(HttpServletRequest request){
		Map<String,Object> returnMap = new HashMap(); 
		Enumeration<String> names=request.getParameterNames();
		while(names.hasMoreElements()){
			String name=names.nextElement();
			String value=request.getParameter(name);
			String[] values=request.getParameterValues(name);
			if(values.length==1){
				returnMap.put(name, value);
			}else{
				returnMap.put(name, values);
			}
		}
		return returnMap;
	}
	
	
}
