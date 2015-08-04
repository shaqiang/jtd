package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IIssuedcommandDao;
import com.jlj.model.Issuedcommand;
import com.jlj.service.IIssuedcommandService;


@Component("issuedcommandService")
public class IssuedcommandServiceImp implements IIssuedcommandService  {
	private IIssuedcommandDao issuedcommandDao;
	public IIssuedcommandDao getIssuedcommandDao() {
		return issuedcommandDao;
	}
	@Resource
	public void setIssuedcommandDao(IIssuedcommandDao issuedcommandDao) {
		this.issuedcommandDao = issuedcommandDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#add(com.jlj.model.Issuedcommand)
	 */
	public void add(Issuedcommand issuedcommand) throws Exception {
		issuedcommandDao.save(issuedcommand);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#delete(com.jlj.model.Issuedcommand)
	 */
	public void delete(Issuedcommand issuedcommand) {
		issuedcommandDao.delete(issuedcommand);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#deleteById(int)
	 */
	public void deleteById(int id) {
		issuedcommandDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#update(com.jlj.model.Issuedcommand)
	 */
	public void update(Issuedcommand issuedcommand) {
		issuedcommandDao.update(issuedcommand);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#getIssuedcommands()
	 */
	public List<Issuedcommand> getIssuedcommands() {
		return issuedcommandDao.getIssuedcommands();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#loadById(int)
	 */
	public Issuedcommand loadById(int id) {
		return issuedcommandDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Issuedcommand mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return issuedcommandDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#queryList(int, java.lang.String, int, int)
	 */
	public List<Issuedcommand> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Issuedcommand mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return issuedcommandDao.pageList(queryString,p,page,size);
	}
	
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#queryIssuedcommandByIpAddress(java.lang.String)
	 */
	public Issuedcommand queryIssuedcommandByIpAddress(String ipAddress) {
		String queryString = "from Issuedcommand mo where mo.ip = :ipAddress";
		String[] paramNames = new String[] { "ipAddress" };
		Object[] values = new Object[] { ipAddress };
		return issuedcommandDao.queryByNamedParam(queryString, paramNames, values);
	}
	
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IIssuedcommandService#getAllIssuedcommands()
	 */
	public List<Issuedcommand> getAllIssuedcommands() {
		return issuedcommandDao.queryList("from Issuedcommand ");
	}
	public Issuedcommand loadBySigid(Integer id) {
		String queryString = "from Issuedcommand mo where mo.sig.id = :id";
		String[] paramNames = new String[] { "id" };
		Object[] values = new Object[] { id };
		return issuedcommandDao.queryByNamedParam(queryString, paramNames, values);
	}
	public void updateObjectById(String datastr, Integer id) {
		String queryString = "update Issuedcommand mo set mo.datas=:datastr where mo.id=:id ";
		String[] paramNames = new String[] {"datastr","id"};
		Object[] values = new Object[] {datastr, id};
		issuedcommandDao.updateByHql(queryString, paramNames, values);
		
	}
	public Issuedcommand loadBySigidAndNumber(int sigid, int number) {
		String queryString = "from Issuedcommand mo where mo.sig.id=:sigid and mo.number=:number";
		String[] paramNames = new String[] { "sigid","number"};
		Object[] values = new Object[] { sigid,number };
		return issuedcommandDao.queryByNamedParam(queryString, paramNames, values);
	}
	
}
