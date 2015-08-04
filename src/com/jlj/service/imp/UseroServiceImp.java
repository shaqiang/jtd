package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IUseroDao;
import com.jlj.model.Usero;
import com.jlj.service.IUseroService;


@Component("useroService")
public class UseroServiceImp implements IUseroService  {
	private IUseroDao userDao;
	public IUseroDao getUserDao() {
		return userDao;
	}
	@Resource
	public void setUserDao(IUseroDao userDao) {
		this.userDao = userDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#add(com.jlj.model.User)
	 */
	public void add(Usero user) throws Exception {
		userDao.save(user);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#delete(com.jlj.model.User)
	 */
	public void delete(Usero user) {
		userDao.delete(user);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#deleteById(int)
	 */
	public void deleteById(int id) {
		userDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#update(com.jlj.model.User)
	 */
	public void update(Usero user) {
		userDao.update(user);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#getUsers()
	 */
	public List<Usero> getUsers() {
		return userDao.getUsers();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#loadById(int)
	 */
	public Usero loadById(int id) {
		return userDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(String convalue) {
		String queryString = "select count(*) from Usero mo where 1=1 ";
		Object[] p = null;
		if(convalue!=null&&!convalue.trim().equals("")){
			//用户姓名
			queryString += "and mo.username like ? "; 
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return userDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IUserService#queryList(int, java.lang.String, int, int)
	 */
	public List<Usero> queryList(String convalue, int page, int size) {
		String queryString = "from Usero mo where 1=1 ";
		Object[] p = null;
		if(convalue!=null&&!convalue.trim().equals("")){
			//用户姓名
			queryString += "and mo.username like ? "; 
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return userDao.pageList(queryString,p,page,size);
	}
	public Usero userlogin(String username, String password) {
		String queryString="from Usero mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return userDao.queryByNamedParam(queryString,paramNames,values);
	}
	public void updatePwd(String newpwd, Integer uid) {
		String queryString="update Usero mo set mo.password=? where mo.id=?";
		Object[] p=new Object[]{newpwd,uid};
		userDao.updateUserByhql(queryString,p);
	}
	
}
