package org.jsoft.service;

import java.util.List;

import javax.annotation.Resource;

import org.jsoft.dao.DaoSupport;
import org.jsoft.model.Status;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("all")
	public List<Status> list(Status status) throws Exception{
		return dao.findList("statusMapper.list", status);
	}
	
}
