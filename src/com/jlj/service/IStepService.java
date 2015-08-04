package com.jlj.service;

import java.util.List;

import com.jlj.model.Step;

public interface IStepService {

	//添加对象
	public abstract void add(Step step) throws Exception;

	//删除对象
	public abstract void delete(Step step);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Step step);

	//获取所有对象
	public abstract List<Step> getSteps();

	//加载一个id的对象
	public abstract Step loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Step> queryList(int con, String convalue, int page,
			int size);

	public abstract Step loadByMkid(long mkid);

	public abstract List<Step> loadBySoId(int soid);

	public abstract Step queryStepBySoluid(int orderid, int soluid);

	public abstract void updateByStepid(String phasename, String stepname,
			Integer id);

	public abstract List<Step> loadBySoIdStep(int soid);

	public abstract Step queryStepByPharseNameAndSoluid(int pharseNumber,
			Integer id);

}