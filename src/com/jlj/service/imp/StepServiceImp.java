package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IStepDao;
import com.jlj.model.Step;
import com.jlj.service.IStepService;


@Component("stepService")
public class StepServiceImp implements IStepService  {
	private IStepDao stepDao;
	public IStepDao getStepDao() {
		return stepDao;
	}
	@Resource
	public void setStepDao(IStepDao stepDao) {
		this.stepDao = stepDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#add(com.jlj.model.Step)
	 */
	public void add(Step step) throws Exception {
		stepDao.save(step);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#delete(com.jlj.model.Step)
	 */
	public void delete(Step step) {
		stepDao.delete(step);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#deleteById(int)
	 */
	public void deleteById(int id) {
		stepDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#update(com.jlj.model.Step)
	 */
	public void update(Step step) {
		stepDao.update(step);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#getSteps()
	 */
	public List<Step> getSteps() {
		return stepDao.getSteps();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#loadById(int)
	 */
	public Step loadById(int id) {
		return stepDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Step mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return stepDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#queryList(int, java.lang.String, int, int)
	 */
	public List<Step> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Step mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return stepDao.pageList(queryString,p,page,size);
	}
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IStepService#loadByMkid(long)
	 */
	public Step loadByMkid(long mkid) {
		// TODO Auto-generated method stub
		
		String queryString = "from Step mo where 1=1 and mo.mkid="+mkid;
		
		
		return stepDao.loadByMkid(queryString);
	}
	public List<Step> loadBySoId(int soid) {
		// TODO Auto-generated method stub
		String queryString = "from Step mo where mod(mo.orderid,2)=0 and mo.solution.id="+soid;
		return stepDao.queryList(queryString);
	}
	public Step queryStepBySoluid(int orderid, int soluid) {
		String queryString="from Step mo where mo.orderid=:orderid and mo.solution.id=:soluid";
		String[] paramNames=new String[]{"orderid","soluid"};
		Object[] values=new Object[]{orderid,soluid};
		return stepDao.queryByNamedParam(queryString,paramNames,values);
	}
	public void updateByStepid(String phasename, String stepname, Integer stepid) {
		String queryString="update Step mo set mo.phasename=:phasename,mo.stepname=:stepname where mo.id=:stepid";
		String[] paramNames = new String[] {"phasename","stepname","stepid"};
		Object[] values = new Object[]{phasename,stepname,stepid};
		stepDao.updateByHql(queryString, paramNames, values);
	}
	public List<Step> loadBySoIdStep(int soid) {
		String queryString = "from Step mo where 1=1 and mo.solution.id="+soid;
		return stepDao.queryList(queryString);
	}
	public Step queryStepByPharseNameAndSoluid(int pharseNumber, Integer id) {
		String queryString="from Step mo where mo.phasename=:phasename and mo.solution.id=:id";
		String[] paramNames=new String[]{"phasename","id"};
		Object[] values=new Object[]{"相位"+pharseNumber,id};
		return stepDao.queryByNamedParam(queryString,paramNames,values);
	}
}
