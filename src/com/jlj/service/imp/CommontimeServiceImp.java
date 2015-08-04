package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.ICommontimeDao;
import com.jlj.model.Commontime;
import com.jlj.service.ICommontimeService;


@Component("commontimeService")
public class CommontimeServiceImp implements ICommontimeService  {
	private ICommontimeDao commontimeDao;
	public ICommontimeDao getCommontimeDao() {
		return commontimeDao;
	}
	@Resource
	public void setCommontimeDao(ICommontimeDao commontimeDao) {
		this.commontimeDao = commontimeDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#add(com.jlj.model.Commontime)
	 */
	public void add(Commontime commontime) throws Exception {
		commontimeDao.save(commontime);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#delete(com.jlj.model.Commontime)
	 */
	public void delete(Commontime commontime) {
		commontimeDao.delete(commontime);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#deleteById(int)
	 */
	public void deleteById(int id) {
		commontimeDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#update(com.jlj.model.Commontime)
	 */
	public void update(Commontime commontime) {
		commontimeDao.update(commontime);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#getCommontimes()
	 */
	public List<Commontime> getCommontimes() {
		return commontimeDao.getCommontimes();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#loadById(int)
	 */
	public Commontime loadById(int id) {
		return commontimeDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Commontime mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return commontimeDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#queryList(int, java.lang.String, int, int)
	 */
	public List<Commontime> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Commontime mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return commontimeDao.pageList(queryString,p,page,size);
	}
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ICommontimeService#loadByMkid(long)
	 */
	public Commontime loadByMkid(long mkid) {
		// TODO Auto-generated method stub
		
		String queryString = "from Commontime mo where 1=1 and mo.mkid="+mkid;
		
		return commontimeDao.loadByMkid(queryString);
	}
	public void updateByConditionOrdinaryid(int hour, int minute, int seconds,
			int workingway, int workingprogram, int lstime, int hdtime,
			int qchdtime, int orderid, Integer sigid) {
		String queryString = "update Commontime mo set mo.hour=:hour,mo.minute=:minute,mo.seconds=:seconds,mo.workingway=:workingway,mo.workingprogram=:workingprogram," +
				"mo.lstime=:lstime,mo.hdtime=:hdtime,mo.qchdtime=:qchdtime where mo.orderid = :orderid and mo.sig.id=:sigid ";
		String[] paramNames = new String[] {"hour","minute","seconds","workingway","workingprogram","lstime","hdtime","qchdtime","orderid","sigid"};
		Object[] values = new Object[] {hour, minute, seconds, workingway, workingprogram, lstime, hdtime, qchdtime, orderid, sigid};
		commontimeDao.updateByHql(queryString, paramNames, values);
	}
	public List<Commontime> getCommontimesBySigid(Integer id) {
		String queryString = "from Commontime mo where mo.sig.id=? ";
		Object[] p = new Object[]{id};;
		return commontimeDao.getObjectsByCondition(queryString, p);
	}
	public List<Commontime> getCommontimesBySigAndTimetype(int sid,
			Integer timetype) {
		String queryString = "from Commontime mo where mo.sig.id="+sid+" and  mo.timetype="+timetype;
		return commontimeDao.queryList(queryString);
	}
	public void updateCommontimeSecond(String methodname, int second, int timeid) {
		String queryString = "update Commontime mo set mo.t"+methodname+"=:second where mo.id=:timeid ";
		String[] paramNames = new String[] {"second","timeid"};
		Object[] values = new Object[] {second, timeid};
		commontimeDao.updateByHql(queryString, paramNames, values);
	}
	public Commontime loadByOrderId(Integer orderid) {
		String queryString = "from Commontime mo where 1=1 and mo.orderid="+orderid;
		return commontimeDao.loadByMkid(queryString);
	}
	public Commontime loadByOrderIdAndTimetype(Integer timetype,Integer orderid) {
		String queryString = "from Commontime mo where mo.orderid="+orderid+" and  mo.timetype="+timetype;
		List<Commontime> list = commontimeDao.queryList(queryString);
		
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
			
	}
	public void updateCommontimeSecond(String methodname, int second,
			Integer orderid, Integer timetype) {
		String queryString = "update Commontime mo set mo.t"+methodname+"=:second where mo.orderid=:orderid and mo.timetype=:timetype";
		String[] paramNames = new String[] {"second","orderid","timetype"};
		Object[] values = new Object[] {second, orderid,timetype};
		commontimeDao.updateByHql(queryString, paramNames, values);
		
	}
	public Commontime loadByOrderIdAndTimetype(Integer timetype,
			Integer orderid, int signid) {
		String queryString = "from Commontime mo where mo.orderid="+orderid+" and  mo.timetype="+timetype+" and  mo.sig.id="+signid;
		List<Commontime> list = commontimeDao.queryList(queryString);
		
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		else
		{
			return null;
		}
	}
	public void updateCommontime(int hour, int minute, int seconds,
			int workingway, int orderid, int timetype, int sid) {
		String queryString = "update Commontime mo set mo.hour=:hour,mo.minute=:minute,mo.seconds=:seconds,mo.workingway=:workingway" +
		" where mo.orderid = :orderid and mo.timetype = :timetype  and mo.sig.id=:sid ";
		String[] paramNames = new String[] {"hour","minute","seconds","workingway","orderid","timetype","sid"};
		Object[] values = new Object[] {hour, minute, seconds, workingway, orderid, timetype,sid};
		commontimeDao.updateByHql(queryString, paramNames, values);
	}
	
	public void updateCommontimeSecond(String methodname, int second,
			Integer orderid, Integer timetype, int signid) {
		String queryString = "update Commontime mo set mo.t"+methodname+"=:second where mo.orderid=:orderid and mo.timetype=:timetype and mo.sig.id=:signid";
		String[] paramNames = new String[] {"second","orderid","timetype","signid"};
		Object[] values = new Object[] {second, orderid,timetype,signid};
		commontimeDao.updateByHql(queryString, paramNames, values);
		
	}
}
