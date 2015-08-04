package com.jlj.service;

import java.util.List;

import com.jlj.model.Greenconflict;

public interface IGreenconflictService {

	//添加对象
	public abstract void add(Greenconflict greenconflict) throws Exception;

	//删除对象
	public abstract void delete(Greenconflict greenconflict);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Greenconflict greenconflict);

	//获取所有对象
	public abstract List<Greenconflict> getGreenconflicts();

	//加载一个id的对象
	public abstract Greenconflict loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Greenconflict> queryList(int con, String convalue,
			int page, int size);

	public abstract List<Greenconflict> loadBySid(int id);

	public abstract void updateGreenByCondition(int isct, String name, int gid);

	public abstract List<Greenconflict> getGreensByIpAddress(String sigIp);

	public abstract List<Greenconflict> getGreensBySigNumber(String sigNumber);

}