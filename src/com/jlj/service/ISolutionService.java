package com.jlj.service;

import java.util.List;

import com.jlj.model.Solution;

public interface ISolutionService {

	//添加对象
	public abstract void add(Solution solution) throws Exception;

	//删除对象
	public abstract void delete(Solution solution);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Solution solution);

	//获取所有对象
	public abstract List<Solution> getSolutions();

	//加载一个id的对象
	public abstract Solution loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Solution> queryList(int con, String convalue,
			int page, int size);

	public abstract Solution loadByMkid(long mkid);

	public abstract void updateBySoluid(String soluname, int soluid);

	public abstract List<Solution> getSolutionsBySignidOrder(Integer signid);

	public abstract List<Solution> loadByPubid(Integer pubid);

	public abstract Solution getSolutionBySignidAndOrderid(Integer id,
			int soluorderid);



}