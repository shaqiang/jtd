package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IFlowDao;
import com.jlj.model.Flow;
import com.jlj.service.IFlowService;


@Component("flowService")
public class FlowServiceImp implements IFlowService  {
	private IFlowDao flowDao;
	public IFlowDao getFlowDao() {
		return flowDao;
	}
	@Resource
	public void setFlowDao(IFlowDao flowDao) {
		this.flowDao = flowDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#add(com.jlj.model.Flow)
	 */
	public void add(Flow flow) throws Exception {
		flowDao.save(flow);
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#add(com.jlj.model.Flow)
	 */
	public int addReturn(Flow flow) throws Exception {
		return flowDao.savereturn(flow);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#delete(com.jlj.model.Flow)
	 */
	public void delete(Flow flow) {
		flowDao.delete(flow);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#deleteById(int)
	 */
	public void deleteById(int id) {
		flowDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#update(com.jlj.model.Flow)
	 */
	public void update(Flow flow) {
		flowDao.update(flow);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#getFlows()
	 */
	public List<Flow> getFlows() {
		return flowDao.getFlows();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#loadById(int)
	 */
	public Flow loadById(int id) {
		return flowDao.loadById(id);
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#loadById(int)
	 */
	public Flow getById(int id) {
		return flowDao.getById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Flow mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return flowDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#queryList(int, java.lang.String, int, int)
	 */
	public List<Flow> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Flow mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return flowDao.pageList(queryString,p,page,size);
	}
	
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#queryFlowByIpAddress(java.lang.String)
	 */
	public Flow queryFlowByIpAddress(String ipAddress) {
		String queryString = "from Flow mo where mo.ip = :ipAddress";
		String[] paramNames = new String[] { "ipAddress" };
		Object[] values = new Object[] { ipAddress };
		return flowDao.queryByNamedParam(queryString, paramNames, values);
	}


	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IFlowService#updateFlowStatus(int, int, java.lang.Integer)
	 */
	public void updateFlowStatus(int flowStatus, int error_code, Integer id) {
		String queryString = "update Flow mo set mo.iserror=:iserror,mo.errorcode=:errorcode where mo.id=:id ";
		String[] paramNames = new String[] {"iserror","errorcode","id"};
		Object[] values = new Object[] {flowStatus,error_code, id};
		flowDao.updateByHql(queryString, paramNames, values);
	}
	public void updateFlowByCondition(String flowziduan, int flow,
			int flow_returnid) {
		String queryString = "update Flow mo set mo."+flowziduan+"=:flow where mo.id=:flow_returnid ";
		String[] paramNames = new String[] {"flow","flow_returnid"};
		Object[] values = new Object[] {flow, flow_returnid};
		flowDao.updateByHql(queryString, paramNames, values);
		
	}
	//车流量
	public int getConditionTotalCount(int sigid, String time1, String time2) {
		String queryString = "select count(*) from Flow mo where mo.sig.id=? ";
		Object[] p = null;
		if(time1!=null&&!time1.equals("")){
			queryString += " and mo.time >='"+time1+"'";
		}
		if(time2!=null&&!time2.equals("")){
			queryString += " and mo.time <='"+time2+"'";
		}
		p = new Object[]{sigid};
		
//		queryString += " order by mo.id desc ";
		return flowDao.getUniqueResult(queryString,p);
	}
	public List<Flow> queryConditionList(int sigid, String time1, String time2, int page,
			int size) {
		String queryString = "from Flow mo where mo.sig.id=? ";
		Object[] p = null;
		if(time1!=null&&!time1.equals("")){
			queryString += " and mo.time >='"+time1+"'";
		}
		if(time2!=null&&!time2.equals("")){
			queryString += " and mo.time <='"+time2+"'";
		}
		p = new Object[]{sigid};
//		queryString += " order by mo.id desc ";
		return flowDao.pageList(queryString, p, page, size);
	}
	public int getPageCount(int totalCount, int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	public int getTotalCount() {
		String queryString = "select count(*) from Flow mo where 1=1 ";
		Object[] p = null;
//		queryString += " order by mo.id desc ";
		return flowDao.getUniqueResult(queryString,p);
	}
	
	public List<Flow> queryList(int page, int size) {
		String queryString = "from Flow mo where 1=1 ";
		Object[] p = null;
//		queryString += " order by mo.id desc ";
		return flowDao.pageList(queryString,p,page,size);
	}
	public List<Flow> queryConditionList2(int sigid, String time1, String time2) {
		String queryString = "from Flow mo where mo.sig.id=? ";
		Object[] p = null;
		if(time1!=null&&!time1.equals("")){
			queryString += " and mo.time >='"+time1+"'";
		}
		if(time2!=null&&!time2.equals("")){
			queryString += " and mo.time <='"+time2+"'";
		}
		p = new Object[]{sigid};
//		queryString += " order by mo.id desc ";
		return flowDao.getObjectsByCondition(queryString, p);
	}
}
