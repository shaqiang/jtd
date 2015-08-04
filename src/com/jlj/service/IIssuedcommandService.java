package com.jlj.service;

import java.util.List;

import com.jlj.model.Issuedcommand;

public interface IIssuedcommandService {

	//添加对象
	public abstract void add(Issuedcommand issuedcommand) throws Exception;

	//删除对象
	public abstract void delete(Issuedcommand issuedcommand);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Issuedcommand issuedcommand);

	//获取所有对象
	public abstract List<Issuedcommand> getIssuedcommands();

	//加载一个id的对象
	public abstract Issuedcommand loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Issuedcommand> queryList(int con, String convalue,
			int page, int size);

	public abstract Issuedcommand queryIssuedcommandByIpAddress(String ipAddress);

	public abstract List<Issuedcommand> getAllIssuedcommands();

	public abstract Issuedcommand loadBySigid(Integer id);

	public abstract void updateObjectById(String datastr, Integer id);

	public abstract Issuedcommand loadBySigidAndNumber(int sigid, int number);

	

}