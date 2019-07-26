package org.jsoft.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public abstract class BaseAction {

	protected void writeJSON(HttpServletResponse response, Object obj) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(JSONObject.fromObject(obj).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
