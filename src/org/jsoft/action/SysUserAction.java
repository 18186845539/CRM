package org.jsoft.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoft.model.SysUser;
import org.jsoft.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sysUserAction")
public class SysUserAction extends BaseAction{

	@Resource
	private SysUserService service;
	
	@RequestMapping("toLogin")
	public String toLogin(){
		return "login";
	}
	
	@RequestMapping("login")
	public void login(HttpServletRequest request, HttpServletResponse response){
		Map<String, String> result = new HashMap<String, String>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		SysUser sysUser = new SysUser();
		sysUser.setPassword(password);
		sysUser.setLoginName(username);
		try {
			SysUser user = service.login(sysUser);
			if(user != null){
				request.getSession().setAttribute("USER", user);
				result.put("code", "1");
				result.put("msg", "登录成功！");
			}else{
				result.put("code", "0");
				result.put("msg", "登录失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", "0");
			result.put("msg", "登录失败！");
		}
		this.writeJSON(response, result);
	}
	
	
}
