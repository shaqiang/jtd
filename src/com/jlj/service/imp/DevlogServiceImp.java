package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IDevlogDao;
import com.jlj.model.Devlog;
import com.jlj.service.IDevlogService;


@Component("devlogService")
public class DevlogServiceImp implements IDevlogService  {
	private IDevlogDao devlogDao;
	public IDevlogDao getDevlogDao() {
		return devlogDao;
	}
	@Resource
	public void setDevlogDao(IDevlogDao devlogDao) {
		this.devlogDao = devlogDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#add(com.jlj.model.Devlog)
	 */
	public void add(Devlog devlog) throws Exception {
		devlogDao.save(devlog);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#delete(com.jlj.model.Devlog)
	 */
	public void delete(Devlog devlog) {
		devlogDao.delete(devlog);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#deleteById(int)
	 */
	public void deleteById(int id) {
		devlogDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#update(com.jlj.model.Devlog)
	 */
	public void update(Devlog devlog) {
		devlogDao.update(devlog);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#getDevlogs()
	 */
	public List<Devlog> getDevlogs() {
		return devlogDao.getDevlogs();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#loadById(int)
	 */
	public Devlog loadById(int id) {
		return devlogDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount() {
		String queryString = "select count(*) from Devlog mo ";
		Object[] p = null;
//		queryString += " order by mo.id desc ";
		return devlogDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IDevlogService#queryList(int, java.lang.String, int, int)
	 */
	public List<Devlog> queryList(int page, int size) {
		String queryString = "from Devlog mo ";
		Object[] p = null;
		queryString += " order by mo.id desc ";
		return devlogDao.pageList(queryString,p,page,size);
	}
	public int getConditionTotalCount(int sigid, String devevent,
			String startdate, String enddate) {
		String queryString = "select count(*) from Devlog mo where mo.sig.id=? and mo.devevent like '%"+devevent+"%'";
		Object[] p = new Object[]{sigid};
		if(startdate!=null&&!startdate.trim().equals("")){
			queryString += "and mo.devtime >='"+startdate+"'"; 
		}
		if(enddate!=null&&!enddate.trim().equals("")){
			queryString += "and mo.devtime <='"+enddate+" 23:59:59'"; 
		}
		return devlogDao.getUniqueResult(queryString, p);
	}
	public List<Devlog> queryConditionList(int sigid, String devevent,
			String startdate, String enddate, int page, int size) {
		String queryString = "from Devlog mo where mo.sig.id=? and mo.devevent like '%"+devevent+"%'";
		Object[] p = new Object[]{sigid};
		if(startdate!=null&&!startdate.trim().equals("")){
			queryString += " and mo.devtime >='"+startdate+"'"; 
		}
		if(enddate!=null&&!enddate.trim().equals("")){
			queryString += " and mo.devtime <='"+enddate+" 23:59:59'"; 
		}
		queryString += " order by mo.id desc";
		return devlogDao.pageList(queryString, p, page, size);
	}
	public int getSigTotalCount(String number,int iserror) {
		String queryString = "select count(*) from Devlog mo where mo.sig.number=? and mo.iserror=? ";
		Object[] p = new Object[]{number,iserror};
		return devlogDao.getUniqueResult(queryString, p);
	}
	public List<Devlog> querySigList(String number,int iserror, int page, int size) {
		String queryString = "from Devlog mo where mo.sig.number=? and mo.iserror=? order by mo.id desc";
		Object[] p = new Object[]{number,iserror};
		return devlogDao.pageList(queryString, p, page, size);
	}
	public void deleteAllBySigid(int iserror, int sigid) {
		String queryString = "delete from Devlog mo where mo.iserror=? and mo.sig.id=?";
		Object[] p = new Object[]{iserror,sigid};
		devlogDao.updateDevlogByhql(queryString, p);
	}
	
}
