package com.jlj.service;

import java.util.List;

import com.jlj.model.Commontime;

public interface ICommontimeService {

	//添加对象
	public abstract void add(Commontime commontime) throws Exception;

	//删除对象
	public abstract void delete(Commontime commontime);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Commontime commontime);

	//获取所有对象
	public abstract List<Commontime> getCommontimes();

	//加载一个id的对象
	public abstract Commontime loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Commontime> queryList(int con, String convalue,
			int page, int size);

	public abstract Commontime loadByMkid(long mkid);

	public abstract void updateByConditionOrdinaryid(int hour, int minute,
			int seconds, int workingway, int workingprogram, int lstime,
			int hdtime, int qchdtime, int orderid, Integer sigid);

	public abstract List<Commontime> getCommontimesBySigid(Integer id);

	public abstract List<Commontime> getCommontimesBySigAndTimetype(int sid,
			Integer timetype);

	public abstract void updateCommontimeSecond(String methodname, int second,
			int timeid);

	public abstract Commontime loadByOrderId(Integer orderid);

	public abstract Commontime loadByOrderIdAndTimetype(
			Integer timetype,Integer orderid, int signid);

	public abstract void updateCommontimeSecond(String methodname, int second,
			Integer orderid, Integer timetype, int signid);

	public abstract void updateCommontime(int hour, int minute, int seconds,
			int workingway, int orderid, int timetype, int sid);

}