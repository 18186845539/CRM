package org.jsoft.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoft.model.Customer;
import org.jsoft.model.SysUser;
import org.jsoft.service.CustomerService;
import org.jsoft.service.SysUserService;
import org.jsoft.util.FileUtil;
import org.jsoft.util.StatusConstants;
import org.jsoft.util.UUIDUtil;
import org.jsoft.util.XlsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
@RequestMapping("customerAction")
public class CustomerAction extends BaseAction{

	@Resource
	private CustomerService service;
	@Resource
	private SysUserService sysUserService;
	
	@RequestMapping("toList")
	public String toList(){
		return "customerList";
	}
	
	@RequestMapping("toEdit")
	public String toEdit(){
		return "customerEdit";
	}
	
	@RequestMapping("toBatchAdd")
	public String toBatchAdd(){
		return "customerBatchAdd";
	}
	
	@RequestMapping("list")
	public void list(HttpServletRequest request, HttpServletResponse response){
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			List<Customer> result = service.list(new Customer());
			resultMap.put("code", "1");
			resultMap.put("list", result);
			this.writeJSON(response, resultMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("initEdit")
	public void initEdit(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", 1);
		result.put("sourceList", StatusConstants.sourceList);
		result.put("statusList", StatusConstants.statusList);
		try {
			List<SysUser> sysUserList = sysUserService.list(new SysUser());
			result.put("sysUserList", sysUserList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.writeJSON(response, result);
	}
	
	@RequestMapping("add")
	public void add(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> result = new HashMap<String, Object>();
		String name = request.getParameter("name");
		String source = request.getParameter("source");
		String status = request.getParameter("status");
		String sysUserId = request.getParameter("sysUserId");
		String telTime = request.getParameter("telTime");
		String estimateTime = request.getParameter("estimateTime");
		String moneyStr = request.getParameter("money");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String remark = request.getParameter("remark");
		Customer customer = new Customer();
		customer.setId(UUIDUtil.getUUID());
		customer.setName(name);
		customer.setSource(source);
		customer.setStatus(status);
		customer.setSysUserId(sysUserId);
		customer.setTelTime(telTime);
		customer.setEstimateTime(estimateTime);
		customer.setMoney(new Double(moneyStr));
		customer.setAddress(address);
		customer.setPhone(phone);
		customer.setRemark(remark);
		try {
			service.add(customer);
			result.put("code", 1);
			result.put("message", "添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 0);
			result.put("message", "添加失败！");
		}
		this.writeJSON(response, result);
	}
	
//	@RequestMapping("batchAdd")
//	public String batchAdd(HttpServletRequest request, HttpServletResponse response){
//		String basePath = "D:/upload/";
//		try {
//			String xlsPath = basePath + FileUtil.upload(basePath, request);
//			List<Customer> customerList = XlsUtil.xls(xlsPath);
//			service.batchAdd(customerList);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "customerList";
//	}
	@RequestMapping("batchAdd")
	public String batchAdd(@RequestParam("file") CommonsMultipartFile file)
			throws Exception {
		String basePath = "D:/upload/";
		File newFile = new File(basePath + file.getOriginalFilename());
		file.transferTo(newFile);
		List<Customer> customerList = XlsUtil.xls(basePath + file.getOriginalFilename());
		service.batchAdd(customerList);
		return "customerList";
	}
   
	
}
