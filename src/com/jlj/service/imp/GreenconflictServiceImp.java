package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IGreenconflictDao;
import com.jlj.model.Greenconflict;
import com.jlj.service.IGreenconflictService;


@Component("greenconflictService")
public class GreenconflictServiceImp implements IGreenconflictService  {
	private IGreenconflictDao greenconflictDao;
	public IGreenconflictDao getGreenconflictDao() {
		return greenconflictDao;
	}
	@Resource
	public void setGreenconflictDao(IGreenconflictDao greenconflictDao) {
		this.greenconflictDao = greenconflictDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#add(com.jlj.model.Greenconflict)
	 */
	public void add(Greenconflict greenconflict) throws Exception {
		greenconflictDao.save(greenconflict);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#delete(com.jlj.model.Greenconflict)
	 */
	public void delete(Greenconflict greenconflict) {
		greenconflictDao.delete(greenconflict);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#deleteById(int)
	 */
	public void deleteById(int id) {
		greenconflictDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#update(com.jlj.model.Greenconflict)
	 */
	public void update(Greenconflict greenconflict) {
		greenconflictDao.update(greenconflict);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#getGreenconflicts()
	 */
	public List<Greenconflict> getGreenconflicts() {
		return greenconflictDao.getGreenconflicts();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#loadById(int)
	 */
	public Greenconflict loadById(int id) {
		return greenconflictDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Greenconflict mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return greenconflictDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IGreenconflictService#queryList(int, java.lang.String, int, int)
	 */
	public List<Greenconflict> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Greenconflict mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return greenconflictDao.pageList(queryString,p,page,size);
	}
	public List<Greenconflict> loadBySid(int id) {
		String queryString = "from Greenconflict mo where 1=1 and mo.sig.id ="+id;
		return greenconflictDao.queryList(queryString);
	}
	public void updateGreenByCondition(int isct, String name, int gid) {
		String queryString = "update Greenconflict mo set mo.l"+name+"=:isct where mo.id=:gid ";
		String[] paramNames = new String[] {"isct","gid"};
		Object[] values = new Object[] {isct, gid};
		greenconflictDao.updateByHql(queryString, paramNames, values);
	}
	public List<Greenconflict> getGreensByIpAddress(String sigIp) {
		String queryString = "from Greenconflict mo where mo.sig.ip = ? order by mo.id desc";
		Object[] p = new Object[]{sigIp};
		return greenconflictDao.getObjectsByCondition(queryString, p);
	}
	public List<Greenconflict> getGreensBySigNumber(String sigNumber) {
		String queryString = "from Greenconflict mo where mo.sig.number = ? order by mo.id desc";
		Object[] p = new Object[]{sigNumber};
		return greenconflictDao.getObjectsByCondition(queryString, p);
	}
	
}
