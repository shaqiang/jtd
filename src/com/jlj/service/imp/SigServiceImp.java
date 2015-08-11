package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.ISigDao;
import com.jlj.model.Sig;
import com.jlj.service.ISigService;


@Component("sigService")
public class SigServiceImp implements ISigService  {
	private ISigDao sigDao;
	public ISigDao getSigDao() {
		return sigDao;
	}
	@Resource
	public void setSigDao(ISigDao sigDao) {
		this.sigDao = sigDao;
	}
	//添加对象
	public void add(Sig sig) throws Exception {
		sigDao.save(sig);
	}
	//删除对象
	public void delete(Sig sig) {
		sigDao.delete(sig);
	}
	//删除某个id的对象
	public void deleteById(int id) {
		sigDao.deleteById(id);
	}
	//修改对象
	public void update(Sig sig) {
		sigDao.update(sig);
	}
	//获取所有对象
	public List<Sig> getSigs() {
		return sigDao.getSigs();
	}
	//加载一个id的对象
	public Sig loadById(int id) {
		return sigDao.loadById(id);
	}
	//后台管理-页数获取
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Sig mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return sigDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	public List<Sig> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Sig mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return sigDao.pageList(queryString,p,page,size);
	}
	public Sig loadByMkid(long mkid) {
		String queryString = "from Sig mo where  mo.mkid="+mkid;
		return sigDao.loadByMkid(queryString);
	}
	public Sig querySigByIpAddress(String ipAddress) {
		String queryString = "from Sig mo where mo.ip = :ipAddress";
		String[] paramNames = new String[] { "ipAddress" };
		Object[] values = new Object[] { ipAddress };
		return sigDao.queryByNamedParam(queryString, paramNames, values);
	}
	public List<Sig> getAllSigs() {
		return sigDao.queryList("from Sig ");
	}
	public List<Sig> querySigsByUser(int userid) {
		String queryString = "from Sig mo where mo.userarea.usero.id = ? ";
		Object[] p =  new Object[]{userid};
		queryString += " order by mo.name asc ";
		return sigDao.getObjectsByCondition(queryString, p);
	}
	public void updateSigStatus(int sigStatus, int error_code, Integer id) {
		String queryString = "update Sig mo set mo.iserror=:iserror,mo.errorcode=:errorcode where mo.id=:id ";
		String[] paramNames = new String[] {"iserror","errorcode","id"};
		Object[] values = new Object[] {sigStatus,error_code, id};
		sigDao.updateByHql(queryString, paramNames, values);
	}
	public List<Sig> querySigsByUserarea(Integer id) {
		String queryString = "from Sig mo where mo.userarea.id = ? ";
		Object[] p =  new Object[]{id};
		return sigDao.getObjectsByCondition(queryString, p);
	}
	public Sig querySigByNumber(String number) {
		String queryString = "from Sig mo where mo.number =:number";
		String[] paramNames = new String[] { "number" };
		Object[] values = new Object[] { number };
		return sigDao.queryByNamedParam(queryString, paramNames, values);
	}
	public Sig querySigByName(String name) {
		String queryString = "from Sig mo where mo.name =:name";
		String[] paramNames = new String[] { "name" };
		Object[] values = new Object[] { name };
		return sigDao.queryByNamedParam(queryString, paramNames, values);
	}
	public List<Sig> getNotNullSigs() {
		String queryString = "from Sig mo where mo.name!=null and mo.lat!=null and mo.lng!=null ";
		Object[] p =  null;
		return sigDao.getObjectsByCondition(queryString, p);
	}
	public List<Sig> getSigsByUserarea(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
