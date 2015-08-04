package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.ISigsystimeDao;
import com.jlj.model.Sigsystime;
import com.jlj.service.ISigsystimeService;


@Component("sigsystimeService")
public class SigsystimeServiceImp implements ISigsystimeService  {
	private ISigsystimeDao sigsystimeDao;
	public ISigsystimeDao getSigsystimeDao() {
		return sigsystimeDao;
	}
	@Resource
	public void setSigsystimeDao(ISigsystimeDao sigsystimeDao) {
		this.sigsystimeDao = sigsystimeDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#add(com.jlj.model.Sigsystime)
	 */
	public void add(Sigsystime sigsystime) throws Exception {
		sigsystimeDao.save(sigsystime);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#delete(com.jlj.model.Sigsystime)
	 */
	public void delete(Sigsystime sigsystime) {
		sigsystimeDao.delete(sigsystime);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#deleteById(int)
	 */
	public void deleteById(int id) {
		sigsystimeDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#update(com.jlj.model.Sigsystime)
	 */
	public void update(Sigsystime sigsystime) {
		sigsystimeDao.update(sigsystime);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#getSigsystimes()
	 */
	public List<Sigsystime> getSigsystimes() {
		return sigsystimeDao.getSigsystimes();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#loadById(int)
	 */
	public Sigsystime loadById(int id) {
		return sigsystimeDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Sigsystime mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return sigsystimeDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#queryList(int, java.lang.String, int, int)
	 */
	public List<Sigsystime> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Sigsystime mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return sigsystimeDao.pageList(queryString,p,page,size);
	}
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.ISigsystimeService#loadByMkid(long)
	 */
	public Sigsystime loadByMkid(long mkid) {
		// TODO Auto-generated method stub
		
		String queryString = "from Sigsystime mo where 1=1 and mo.mkid="+mkid;
		
		
		return sigsystimeDao.loadByMkid(queryString);
	}
}
