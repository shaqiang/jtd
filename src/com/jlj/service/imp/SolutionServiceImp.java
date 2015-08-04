package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.ISolutionDao;
import com.jlj.model.Solution;
import com.jlj.service.ISolutionService;


@Component("solutionService")
public class SolutionServiceImp implements ISolutionService  {
	private ISolutionDao solutionDao;
	public ISolutionDao getSolutionDao() {
		return solutionDao;
	}
	@Resource
	public void setSolutionDao(ISolutionDao solutionDao) {
		this.solutionDao = solutionDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#add(com.jlj.model.Solution)
	 */
	public void add(Solution solution) throws Exception {
		solutionDao.save(solution);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#delete(com.jlj.model.Solution)
	 */
	public void delete(Solution solution) {
		solutionDao.delete(solution);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#deleteById(int)
	 */
	public void deleteById(int id) {
		solutionDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#update(com.jlj.model.Solution)
	 */
	public void update(Solution solution) {
		solutionDao.update(solution);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#getSolutions()
	 */
	public List<Solution> getSolutions() {
		return solutionDao.getSolutions();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#loadById(int)
	 */
	public Solution loadById(int id) {
		return solutionDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Solution mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return solutionDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#queryList(int, java.lang.String, int, int)
	 */
	public List<Solution> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Solution mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return solutionDao.pageList(queryString,p,page,size);
	}
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISolutionService#loadByMkid(long)
	 */
	public Solution loadByMkid(long mkid) {
		// TODO Auto-generated method stub
		
		String queryString = "from Solution mo where 1=1 and mo.mkid="+mkid;
		
		
		return solutionDao.loadByMkid(queryString);
	}
	public List<Solution> getSolutionsBySignidOrder(Integer id) {
		String queryString = "from Solution mo where mo.sig.id=? order by mo.orderid asc ";
		Object[] p = new Object[]{id};
		return solutionDao.getObjectsByCondition(queryString, p);
	}
	public void updateBySoluid(String soluname, int soluid) {
		String queryString = "update Solution mo set mo.soluname=:soluname where mo.id=:soluid ";
		String[] paramNames = new String[] {"soluname","soluid"};
		Object[] values = new Object[] {soluname, soluid};
		solutionDao.updateByHql(queryString, paramNames, values);
		
	}
	
	public List<Solution> loadByPubid(Integer pubid) {
		String queryString = "from Solution mo where 1=1 and mo.sig.id ="+pubid+" order by mo.orderid asc";
		return solutionDao.queryList(queryString);
	}
	public Solution getSolutionBySignidAndOrderid(Integer signid, int soluorderid) {
		String queryString="from Solution mo where mo.sig.id=:signid and mo.orderid=:soluorderid";
		String[] paramNames=new String[]{"signid","soluorderid"};
		Object[] values=new Object[]{signid,soluorderid};
		return solutionDao.queryByNamedParam(queryString,paramNames,values);
	}
}
