package com.jlj.service;

import java.util.List;

import com.jlj.model.Sigsystime;

public interface ISigsystimeService {

	//添加对象
	public abstract void add(Sigsystime sigsystime) throws Exception;

	//删除对象
	public abstract void delete(Sigsystime sigsystime);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Sigsystime sigsystime);

	//获取所有对象
	public abstract List<Sigsystime> getSigsystimes();

	//加载一个id的对象
	public abstract Sigsystime loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Sigsystime> queryList(int con, String convalue,
			int page, int size);

	public abstract Sigsystime loadByMkid(long mkid);

}