package com.jlj.dao.imp;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.jlj.dao.IGreenconflictDao;
import com.jlj.model.Greenconflict;
@Component("greenconflictDao")
public class GreenconflictDaoImp implements IGreenconflictDao {
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	//保存一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#save(com.jlj.model.Greenconflict)
	 */
	public void save(Greenconflict greenconflict) {
		this.hibernateTemplate.save(greenconflict);
	}
	
	//保存一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#savereturn(com.jlj.model.Greenconflict)
	 */
	public Integer savereturn(Greenconflict greenconflict) {
		return (Integer) this.hibernateTemplate.save(greenconflict);
	}
	
	//删除一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#delete(com.jlj.model.Greenconflict)
	 */
	public void delete(Greenconflict greenconflict) {
		this.hibernateTemplate.delete(greenconflict);
	}
	//根据ID删除一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}
	
	//修改一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#update(com.jlj.model.Greenconflict)
	 */
	public void update(Greenconflict greenconflict) {
		this.hibernateTemplate.update(greenconflict);
	}
	
	//根据hql语句、条件、条件值修改某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public void updateByHql(final String hql,final String[] paramNames,final Object[] values) {
		this.hibernateTemplate.execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				for (int i = 0; i < paramNames.length; i++) {
					query.setParameter(paramNames[i], values[i]);
				}
				query.executeUpdate();
				return null;
			}
			
		});
	}
	
	//获得所有记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#getGreenconflicts()
	 */
	public List<Greenconflict> getGreenconflicts() {
		return this.hibernateTemplate.loadAll(Greenconflict.class);
	}
	
	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#queryList(java.lang.String)
	 */
	public List<Greenconflict> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}
	
	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<Greenconflict> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}
	
	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<Greenconflict> queryList(String queryString, String[] paramNames,
			Object[] values)
	{
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}
	
	
	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<Greenconflict> getObjectsByIdList(final String hql,final List<Integer> idList) {
		return this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(hql);
				query.setParameterList("idList", idList);
				return query.list();
			}
			
		});
	}
	
	//根据hql语句、条件值、分页查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<Greenconflict> pageList(final String queryString,final Object[] p,final Integer page,
			final Integer size) {
		return this.hibernateTemplate.executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query=session.createQuery(queryString);
				if(p!=null&&p.length>0){
					for (int i = 0; i < p.length; i++) {
						query.setParameter(i, p[i]);
					}
				}
				if(page!=null&&page>0&&size!=null&&size>0){
					query.setFirstResult((page-1)*size).setMaxResults(size);
				}
				return query.list();
			}
			
		});
	}
	
	
	
	//根据hql、条件值获得一个唯一值
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#getUniqueResult(java.lang.String, java.lang.Object[])
	 */
	public int getUniqueResult(final String queryString,final Object[] p) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		if(p!=null&&p.length>0){
			for (int i = 0; i < p.length; i++) {
				query.setParameter(i, p[i]);
			}
		}
		Object obj=query.uniqueResult();
		return ((Long)obj).intValue();
			
	}
	
	//根据id查询一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#loadById(int)
	 */
	public Greenconflict loadById(int id) {
		return (Greenconflict) this.hibernateTemplate.load(Greenconflict.class, id);
	}
	
	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public Greenconflict queryByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(Greenconflict)list.get(0):null;
	}
	
	//根据hql语句、条件值来查询是否存在该记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#checkGreenconflictExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkGreenconflictExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}
	//根据hql批量修改
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#updateGreenconflictByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateGreenconflictByhql(String queryString, Object[] p) {
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		if(p!=null&&p.length>0){
			for (int i = 0; i < p.length; i++) {
				query.setParameter(i, p[i]);
			}
		}
		//返回受影响的行数
		return query.executeUpdate();
	}
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenconflictDao#loadByMkid(java.lang.String)
	 */
	public Greenconflict loadByMkid(String queryString) {
		// TODO Auto-generated method stub
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		List greenconflicts = query.list();
		System.out.println(greenconflicts);
		if(greenconflicts!=null&&greenconflicts.size()>0)
		{
			return (Greenconflict) greenconflicts.get(0);
		}
		return null;
	}
	


}
