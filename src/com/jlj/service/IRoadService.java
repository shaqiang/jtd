package com.jlj.service;

import java.util.List;

import com.jlj.model.Road;

public interface IRoadService {

	//添加对象
	public abstract void add(Road road) throws Exception;

	//删除对象
	public abstract void delete(Road road);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Road road);

	//获取所有对象
	public abstract List<Road> getRoads();

	//加载一个id的对象
	public abstract Road loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Road> queryList(int con, String convalue, int page,
			int size);

	public abstract Road loadByMkid(long mkid);

	public abstract void updateByRoadid(int leftcolor, int linecolor,
			int rightcolor, int rxcolor, int roadtype, Integer id);

	public abstract void updateByCondition(int deng, String dengtypestr,
			int roadtype, int stepid);

	public abstract List<Road> loadByStepid(Integer id);

}