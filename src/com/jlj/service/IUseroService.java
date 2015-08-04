package com.jlj.service;

import java.util.List;

import com.jlj.model.Usero;

public interface IUseroService {

	//添加对象
	public abstract void add(Usero usero) throws Exception;

	//删除对象
	public abstract void delete(Usero usero);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Usero usero);

	//获取所有对象
	public abstract List<Usero> getUsers();

	//加载一个id的对象
	public abstract Usero loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount( String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Usero> queryList( String convalue, int page,
			int size);

	public abstract Usero userlogin(String username, String password);

	public abstract void updatePwd(String newpwd, Integer uid);

}