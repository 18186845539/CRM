package org.jsoft.service;

import java.util.List;

import javax.annotation.Resource;

import org.jsoft.dao.DaoSupport;
import org.jsoft.model.Customer;
import org.jsoft.util.StatusConstants;
import org.jsoft.util.UUIDUtil;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("all")
	public List<Customer> list(Customer customer) throws Exception{
		List<Customer> result = dao.findList("customerMapper.list", customer);
		for(Customer c : result){
			c.setSourceName(StatusConstants.sourceMap.get(c.getSource()));
			c.setStatusName(StatusConstants.statusMap.get(c.getStatus()));
		}
		return result;
	}

	public void add(Customer customer) throws Exception {
		dao.save("customerMapper.save", customer);
	}

	public void batchAdd(List<Customer> customerList) throws Exception {
		for(int i = 0; i < customerList.size(); i++){
			Customer c = customerList.get(i);
			c.setId(UUIDUtil.getUUID());
			add(c);
		}
	}
	
}
