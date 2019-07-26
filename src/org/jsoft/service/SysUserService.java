package org.jsoft.service;

import java.util.List;

import javax.annotation.Resource;

import org.jsoft.dao.DaoSupport;
import org.jsoft.model.SysUser;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

	@Resource(name="daoSupport")
	private DaoSupport dao;
	
	public SysUser login(SysUser sysUser) throws Exception {
		return (SysUser) dao.findOne("sysUserMapper.query", sysUser);
	}
	
	@SuppressWarnings("unchecked")
	public List<SysUser> list(SysUser sysUser) throws Exception{
		return dao.findList("sysUserMapper.list", sysUser);
	}

}
