package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IUserareaDao;
import com.jlj.model.Userarea;
import com.jlj.service.IUserareaService;


@Component("userareaService")
public class UserareaServiceImp implements IUserareaService  {
	private IUserareaDao userareaDao;
	public IUserareaDao getUserareaDao() {
		return userareaDao;
	}
	@Resource
	public void setUserareaDao(IUserareaDao userareaDao) {
		this.userareaDao = userareaDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#add(com.jlj.model.Userarea)
	 */
	public void add(Userarea userarea) throws Exception {
		userareaDao.save(userarea);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#delete(com.jlj.model.Userarea)
	 */
	public void delete(Userarea userarea) {
		userareaDao.delete(userarea);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#deleteById(int)
	 */
	public void deleteById(int id) {
		userareaDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#update(com.jlj.model.Userarea)
	 */
	public void update(Userarea userarea) {
		userareaDao.update(userarea);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#getUserareas()
	 */
	public List<Userarea> getUserareas() {
		return userareaDao.getUserareas();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#loadById(int)
	 */
	public Userarea loadById(int id) {
		return userareaDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Userarea mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return userareaDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserareaService#queryList(int, java.lang.String, int, int)
	 */
	public List<Userarea> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Userarea mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return userareaDao.pageList(queryString,p,page,size);
	}
	public int getConditionTotalCount(int userid, String uareaname) {
		String queryString = "select count(*) from Userarea mo where mo.usero.id=? ";
		Object[] p = null;
		if(uareaname!=null&&!uareaname.equals("")){
			queryString += "and mo.uareaname like ? "; 
			p = new Object[]{userid,'%'+uareaname+'%'};
		}else{
			p = new Object[]{userid};
		}
		return userareaDao.getUniqueResult(queryString,p);
	}
	public int getPageCount(int totalCount, int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	public List<Userarea> queryConditionList(int userid, String uareaname,
			int page, int size) {
		String queryString = "from Userarea mo where mo.usero.id=? ";
		Object[] p = null;
		if(uareaname!=null&&!uareaname.equals("")){
			queryString += "and mo.uareaname like ? "; 
			p = new Object[]{userid,'%'+uareaname+'%'};
		}else{
			p = new Object[]{userid};
		}
		return userareaDao.pageList(queryString,p,page,size);
	}
	public int getConditionTotalCount(String uareaname) {
		String queryString = "select count(*) from Userarea mo where 1=1 ";
		Object[] p = null;
		if(uareaname!=null&&!uareaname.equals("")){
			queryString += "and mo.uareaname like ? "; 
			p = new Object[]{'%'+uareaname+'%'};
		}
		return userareaDao.getUniqueResult(queryString,p);
	}
	public List<Userarea> queryConditionList(String uareaname, int page,
			int size) {
		String queryString = "from Userarea mo where 1=1 ";
		Object[] p = null;
		if(uareaname!=null&&!uareaname.equals("")){
			queryString += "and mo.uareaname like ? "; 
			p = new Object[]{'%'+uareaname+'%'};
		}
		return userareaDao.pageList(queryString,p,page,size);
	}
	public List<Userarea> queryList(Integer id) {
		// TODO Auto-generated method stub
		String queryString = "from Userarea mo where mo.usero.id=?";
		Object[] p = new Object[]{id};;
		return userareaDao.getObjectsByCondition(queryString, p);
	}
	public Userarea getUserByAreaname(String uareaname) {
		String queryString="from Userarea mo where mo.uareaname=:uareaname";
		String[] paramNames=new String[]{"uareaname"};
		Object[] values=new Object[]{uareaname};
		return userareaDao.queryByNamedParam(queryString,paramNames,values);
	}
	
}
