package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.ITqsigDao;
import com.jlj.model.Tqsig;
import com.jlj.service.ITqsigService;


@Component("tqsigService")
public class TqsigServiceImp implements ITqsigService {
	private ITqsigDao tqsigDao;
	public ITqsigDao getTqsigDao() {
		return tqsigDao;
	}
	@Resource
	public void setTqsigDao(ITqsigDao tqsigDao) {
		this.tqsigDao = tqsigDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#add(com.jlj.model.Tqsig)
	 */
	public void add(Tqsig tqsig) throws Exception {
		tqsigDao.save(tqsig);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#delete(com.jlj.model.Tqsig)
	 */
	public void delete(Tqsig tqsig) {
		tqsigDao.delete(tqsig);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#deleteById(int)
	 */
	public void deleteById(int id) {
		tqsigDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#update(com.jlj.model.Tqsig)
	 */
	public void update(Tqsig tqsig) {
		tqsigDao.update(tqsig);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#getTqsigs()
	 */
	public List<Tqsig> getTqsigs() {
		return tqsigDao.getTqsigs();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#loadById(int)
	 */
	public Tqsig loadById(int id) {
		return tqsigDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Tqsig mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return tqsigDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ITqsigService#queryList(int, java.lang.String, int, int)
	 */
	public List<Tqsig> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Tqsig mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return tqsigDao.pageList(queryString,p,page,size);
	}
	public Tqsig queryByNumber(Integer greenid,String number) {
		String queryString = "from Tqsig mo where mo.number =:number and mo.greenroad.id="+greenid;
		String[] paramNames = new String[] { "number" };
		Object[] values = new Object[] { number };
		return tqsigDao.queryByNamedParam(queryString, paramNames, values);
	}
	public List<Tqsig> getTqsigsByGrid(int grid) {
		String queryString = "from Tqsig mo where mo.greenroad.id=? ";
		Object[] p = new Object[]{grid};
		queryString += " order by mo.orderid asc ";
		return tqsigDao.getObjectsByCondition(queryString, p);
	}
	
	
}
