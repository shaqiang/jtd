package com.jlj.util;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jlj.model.Oplog;
import com.jlj.model.Usero;
import com.jlj.service.IOplogService;


@Component("logInterceptor")
@Scope("prototype")
public class LogInterceptor {
	
	private IOplogService oplogService;
	private Oplog oplog;
	public void addLog(String loginip,String optype,String opevent,String signumber,Usero usero)
	{
		oplog = new Oplog();
		oplog.setLoginip(loginip);
		oplog.setOptype(optype);
		oplog.setOpevent(opevent);
		oplog.setSignumber(signumber);
		oplog.setUsero(usero);
		oplog.setOptime(new Date());
		
		
		
		try {
			oplogService.add(oplog);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public IOplogService getOplogService() {
		return oplogService;
	}
	@Resource
	public void setOplogService(IOplogService oplogService) {
		this.oplogService = oplogService;
	}

	public Oplog getOplog() {
		return oplog;
	}

	public void setOplog(Oplog oplog) {
		this.oplog = oplog;
	}
	
	
	
	
}