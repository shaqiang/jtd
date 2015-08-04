package com.jlj.service;

import java.util.List;

import com.jlj.model.Flow;

public interface IFlowService {

	//添加对象
	public abstract void add(Flow flow) throws Exception;

	//添加对象
	public int addReturn(Flow flow) throws Exception;
	
	//删除对象
	public abstract void delete(Flow flow);

	//删除某个id的对象
	public abstract void deleteById(int id);

	//修改对象
	public abstract void update(Flow flow);

	//获取所有对象
	public abstract List<Flow> getFlows();

	//加载一个id的对象
	public abstract Flow loadById(int id);

	//后台管理-页数获取
	public abstract int getPageCount(int con, String convalue, int size);

	//后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue);

	//后台管理-获取符合条件的记录
	public abstract List<Flow> queryList(int con, String convalue, int page,
			int size);

	public abstract Flow queryFlowByIpAddress(String ipAddress);

	public abstract void updateFlowStatus(int flowStatus, int error_code,
			Integer id);

	public abstract void updateFlowByCondition(String flowziduan, int flow,
			int flow_returnid);

	public abstract int getConditionTotalCount(int sigid, String time1, String time2);

	public abstract int getPageCount(int totalCount, int size);

	public abstract List<Flow> queryConditionList(int sigid, String time1, String time2,
			int page, int size);

	public abstract int getTotalCount();

	public abstract List<Flow> queryList(int page, int size);

	public abstract List<Flow> queryConditionList2(int sigid, String time1,
			String time2);

	public abstract Flow getById(int flow_returnid);



}