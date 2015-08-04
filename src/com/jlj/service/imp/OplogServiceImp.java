package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IOplogDao;
import com.jlj.model.Oplog;
import com.jlj.service.IOplogService;


@Component("oplogService")
public class OplogServiceImp implements IOplogService {
	private IOplogDao oplogDao;
	public IOplogDao getOplogDao() {
		return oplogDao;
	}
	@Resource
	public void setOplogDao(IOplogDao oplogDao) {
		this.oplogDao = oplogDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#add(com.jlj.model.Oplog)
	 */
	public void add(Oplog oplog) throws Exception {
		oplogDao.save(oplog);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#delete(com.jlj.model.Oplog)
	 */
	public void delete(Oplog oplog) {
		oplogDao.delete(oplog);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#deleteById(int)
	 */
	public void deleteById(int id) {
		oplogDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#update(com.jlj.model.Oplog)
	 */
	public void update(Oplog oplog) {
		oplogDao.update(oplog);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#getOplogs()
	 */
	public List<Oplog> getOplogs() {
		return oplogDao.getOplogs();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#loadById(int)
	 */
	public Oplog loadById(int id) {
		return oplogDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#getTotalCount(int, java.lang.String)
	 */
	public int getConditionTotalCount(int uid, String opevent, String startdate, String enddate) {
		String queryString = "select count(*) from Oplog mo where mo.usero.id=? and mo.opevent like ? ";
		Object[] p = new Object[]{uid,'%'+opevent+'%'};
		if(startdate!=null&&!startdate.trim().equals("")){
			queryString += "and mo.optime >='"+startdate+"'"; 
		}
		if(enddate!=null&&!enddate.trim().equals("")){
			queryString += "and mo.optime <='"+enddate+" 23:59:59'"; 
		}
//		queryString += " order by mo.id desc ";
		return oplogDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#queryList(int, java.lang.String, int, int)
	 */
	public List<Oplog> queryConditionList(int uid, String opevent, String startdate, String enddate, int page, int size) {
		String queryString = "from Oplog mo where mo.usero.id=? and mo.opevent like ? ";
		Object[] p = new Object[]{uid,'%'+opevent+'%'};
		if(startdate!=null&&!startdate.trim().equals("")){
			queryString += "and mo.optime >='"+startdate+"'"; 
		}
		if(enddate!=null&&!enddate.trim().equals("")){
			queryString += "and mo.optime <='"+enddate+" 23:59:59'"; 
		}
		queryString += " order by mo.id desc ";
		return oplogDao.pageList(queryString,p,page,size);
	}
	
	
	//后台管理-获取总记录数-不带条件
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount() {
		String queryString = "select count(*) from Oplog mo ";
		Object[] p = null;
//		queryString += " order by mo.id desc ";
		return oplogDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录-不带条件
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IOplogService#queryList(int, java.lang.String, int, int)
	 */
	public List<Oplog> queryList(int page, int size) {
		String queryString = "from Oplog mo ";
		Object[] p = null;
		queryString += " order by mo.id desc ";
		return oplogDao.pageList(queryString,p,page,size);
	}
	public int getSigidTotalCount(String number) {
		String queryString = "select count(*) from Oplog mo where mo.signumber=? ";
		Object[] p = new Object[]{number};
		
//		queryString += " order by mo.id desc ";
		return oplogDao.getUniqueResult(queryString,p);
	}
	public List<Oplog> querySigidList(String number, int page, int size) {
		String queryString = "from Oplog mo where mo.signumber=? ";
		Object[] p = new Object[]{number};
//		queryString += " order by mo.id desc ";
		return oplogDao.pageList(queryString,p,page,size);
	}
}
