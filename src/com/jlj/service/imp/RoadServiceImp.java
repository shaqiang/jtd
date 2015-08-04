package com.jlj.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jlj.dao.IRoadDao;
import com.jlj.model.Road;
import com.jlj.service.IRoadService;


@Component("roadService")
public class RoadServiceImp implements IRoadService  {
	private IRoadDao roadDao;
	public IRoadDao getRoadDao() {
		return roadDao;
	}
	@Resource
	public void setRoadDao(IRoadDao roadDao) {
		this.roadDao = roadDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#add(com.jlj.model.Road)
	 */
	public void add(Road road) throws Exception {
		roadDao.save(road);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#delete(com.jlj.model.Road)
	 */
	public void delete(Road road) {
		roadDao.delete(road);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#deleteById(int)
	 */
	public void deleteById(int id) {
		roadDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#update(com.jlj.model.Road)
	 */
	public void update(Road road) {
		roadDao.update(road);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#getRoads()
	 */
	public List<Road> getRoads() {
		return roadDao.getRoads();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#loadById(int)
	 */
	public Road loadById(int id) {
		return roadDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int con, String convalue,int size) {
		int totalCount=this.getTotalCount(con, convalue);
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from Road mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return roadDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#queryList(int, java.lang.String, int, int)
	 */
	public List<Road> queryList(int con, String convalue, int page, int size) {
		String queryString = "from Road mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			//线路名称
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc ";
		return roadDao.pageList(queryString,p,page,size);
	}
	/* (non-Javadoc)
	 * @see com.jlj.service.imp.IRoadService#loadByMkid(long)
	 */
	public Road loadByMkid(long mkid) {
		// TODO Auto-generated method stub
		
		String queryString = "from Road mo where 1=1 and mo.mkid="+mkid;
		
		
		return roadDao.loadByMkid(queryString);
	}
	public void updateByRoadid(int leftcolor, int linecolor, int rightcolor,
			int rxcolor, int roadtype, Integer stepid) {
		String queryString="update Road mo set mo.leftcolor=:leftcolor,mo.linecolor=:linecolor,mo.rightcolor=:rightcolor,mo.rxcolor=:rxcolor where mo.step.id=:stepid and mo.roadtype=:roadtype";
		String[] paramNames = new String[] {"leftcolor","linecolor","rightcolor","rxcolor","roadtype","stepid"};
		Object[] values = new Object[]{leftcolor, linecolor, rightcolor,rxcolor, roadtype,stepid};
		roadDao.updateByHql(queryString, paramNames, values);
		
	}
	public void updateByCondition(int deng, String dengtypestr, int roadtype,
			int stepid) {
		String queryString="update Road mo set mo."+dengtypestr+"=:deng where mo.step.id=:stepid and mo.roadtype=:roadtype";
		String[] paramNames = new String[] {"deng","stepid","roadtype"};
		Object[] values = new Object[]{deng,stepid,roadtype};
		roadDao.updateByHql(queryString, paramNames, values);
	}
	public List<Road> loadByStepid(Integer id) {
		String queryString = "from Road mo where mo.step.id=?";
		Object[] p = new Object[]{id};
		return roadDao.getObjectsByCondition(queryString, p);
	}
}
