package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IGreenroadDao;
import com.jlj.model.Greenroad;
import com.jlj.service.IGreenroadService;


@Component("greenroadService")
public class GreenroadServiceImp implements IGreenroadService  {
	private IGreenroadDao greenroadDao;
	public IGreenroadDao getGreenroadDao() {
		return greenroadDao;
	}
	@Resource
	public void setGreenroadDao(IGreenroadDao greenroadDao) {
		this.greenroadDao = greenroadDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#add(com.jlj.model.Greenroad)
	 */
	public void add(Greenroad greenroad) throws Exception {
		greenroadDao.save(greenroad);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#delete(com.jlj.model.Greenroad)
	 */
	public void delete(Greenroad greenroad) {
		greenroadDao.delete(greenroad);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#deleteById(int)
	 */
	public void deleteById(int id) {
		greenroadDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#update(com.jlj.model.Greenroad)
	 */
	public void update(Greenroad greenroad) {
		greenroadDao.update(greenroad);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#getGreenroads()
	 */
	public List<Greenroad> getGreenroads() {
		return greenroadDao.getGreenroads();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#loadById(int)
	 */
	public Greenroad loadById(int id) {
		return greenroadDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Greenroad mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return greenroadDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#queryList(int, java.lang.String, int, int)
	 */
	public List<Greenroad> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Greenroad mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return greenroadDao.pageList(queryString,p,page,size);
	}
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#loadByMkid(long)
	 */
	public Greenroad loadByMkid(long mkid) {
		String queryString = "from Greenroad mo where 1=1 and mo.marklineid="+mkid;
		return greenroadDao.loadByMkid(queryString);
	}
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#queryGreenroadByIpAddress(java.lang.String)
	 */
	public Greenroad queryGreenroadByIpAddress(String ipAddress) {
		String queryString = "from Greenroad mo where mo.ip = :ipAddress";
		String[] paramNames = new String[] { "ipAddress" };
		Object[] values = new Object[] { ipAddress };
		return greenroadDao.queryByNamedParam(queryString, paramNames, values);
	}
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenroadService#getAllGreenroads()
	 */
	public List<Greenroad> getAllGreenroads() {
		return greenroadDao.queryList("from Greenroad ");
	}
	public List<Greenroad> getAllGreenroads(int i) {
		// TODO Auto-generated method stub
		return greenroadDao.queryList("from Greenroad mo where mo.type="+i);
	}
}
