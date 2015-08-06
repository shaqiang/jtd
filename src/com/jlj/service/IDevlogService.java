package com.jlj.service;

import java.util.List;

import com.jlj.model.Devlog;

public interface IDevlogService {

	//添加对象
	public abstract void add(Devlog devlog) throws Exception;

	//删除对象
	public abstract void delete(Devlog devlog);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Devlog devlog);

	//获取所有对象
	public abstract List<Devlog> getDevlogs();

	//加载一个id的对象
	public abstract Devlog loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount();

	//后台管理-获取符合条件的记录
	public abstract List<Devlog> queryList(int page, int size);

	public abstract int getConditionTotalCount(int sigid, String devevent,
			String startdate, String enddate);

	public abstract List<Devlog> queryConditionList(int sigid, String devevent,
			String startdate, String enddate, int page, int size);

	public abstract int getSigTotalCount(String ipAddress, int iserror);

	public abstract List<Devlog> querySigList(String ipAddress, int iserror, int page,
			int size);

	public abstract void deleteAllBySigid(int iserror, int sigid);

}