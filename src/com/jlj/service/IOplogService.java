package com.jlj.service;

import java.util.List;

import com.jlj.model.Oplog;

public interface IOplogService {

	//添加对象
	public abstract void add(Oplog oplog) throws Exception;

	//删除对象
	public abstract void delete(Oplog oplog);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Oplog oplog);

	//获取所有对象
	public abstract List<Oplog> getOplogs();

	//加载一个id的对象
	public abstract Oplog loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getConditionTotalCount(int uid, String opevent, String startdate, String enddate);

	//后台管理-获取符合条件的记录
	public abstract List<Oplog> queryConditionList(int uid, String opevent, String startdate, String enddate, int page,
			int size);
	
	//后台管理-获取总记录数
	public abstract int getTotalCount();

	//后台管理-获取符合条件的记录
	public abstract List<Oplog> queryList(int page,
			int size);

	public abstract int getSigidTotalCount(String ipAddress);

	public abstract List<Oplog> querySigidList(String ipAddress, int page, int size);

}