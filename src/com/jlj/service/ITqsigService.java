package com.jlj.service;

import java.util.List;

import com.jlj.model.Tqsig;

public interface ITqsigService {

	//添加对象
	public abstract void add(Tqsig tqsig) throws Exception;

	//删除对象
	public abstract void delete(Tqsig tqsig);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Tqsig tqsig);

	//获取所有对象
	public abstract List<Tqsig> getTqsigs();

	//加载一个id的对象
	public abstract Tqsig loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Tqsig> queryList(int con, String convalue, int page,
			int size);

	public abstract Tqsig queryByNumber(Integer integer, String number);

	public abstract List<Tqsig> getTqsigsByGrid(int grid);

}