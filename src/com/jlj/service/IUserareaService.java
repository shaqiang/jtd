package com.jlj.service;

import java.util.List;

import com.jlj.model.Userarea;

public interface IUserareaService {

	//添加对象
	public abstract void add(Userarea userarea) throws Exception;

	//删除对象
	public abstract void delete(Userarea userarea);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Userarea userarea);

	//获取所有对象
	public abstract List<Userarea> getUserareas();

	//加载一个id的对象
	public abstract Userarea loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Userarea> queryList(int con, String convalue,
			int page, int size);

	public abstract int getConditionTotalCount(int userid, String uareaname);

	public abstract int getPageCount(int totalCount, int size);

	public abstract List<Userarea> queryConditionList(int userid,
			String uareaname, int page, int size);

	public abstract int getConditionTotalCount(String uareaname);

	public abstract List<Userarea> queryConditionList(String uareaname,
			int page, int size);

	public abstract List<Userarea> queryList(Integer id);

	public abstract Userarea getUserByAreaname(String areaname);

}