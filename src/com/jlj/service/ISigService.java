package com.jlj.service;

import java.util.List;

import com.jlj.model.Sig;

public interface ISigService {

	//添加对象
	public abstract void add(Sig sig) throws Exception;

	//删除对象
	public abstract void delete(Sig sig);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Sig sig);

	//获取所有对象
	public abstract List<Sig> getSigs();

	//加载一个id的对象
	public abstract Sig loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Sig> queryList(int con, String convalue, int page,
			int size);

	public abstract Sig loadByMkid(long mkid);

	public abstract Sig querySigByIpAddress(String ipAddress);

	public abstract List<Sig> getAllSigs();

	public abstract List<Sig> querySigsByUser(int userid);

	public abstract void updateSigStatus(int sigStatus, int error_code,
			Integer id);

	public abstract List<Sig> querySigsByUserarea(Integer id);

	public abstract Sig querySigByNumber(String number);

	public abstract Sig querySigByName(String name);

	public abstract List<Sig> getNotNullSigs();


	public abstract List<Sig> querySigsByUserid(int uid);



}