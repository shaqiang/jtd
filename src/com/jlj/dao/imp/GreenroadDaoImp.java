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

import com.jlj.dao.IGreenroadDao;
import com.jlj.model.Greenroad;
@Component("greenroadDao")
public class GreenroadDaoImp implements IGreenroadDao {
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
	 * @see com.jlj.dao.imp.IGreenroad#save(com.jlj.model.Greenroad)
	 */
	public void save(Greenroad greenroad) {
		this.hibernateTemplate.save(greenroad);
	}
	
	//保存一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#savereturn(com.jlj.model.Greenroad)
	 */
	public Integer savereturn(Greenroad greenroad) {
		return (Integer) this.hibernateTemplate.save(greenroad);
	}
	
	//删除一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#delete(com.jlj.model.Greenroad)
	 */
	public void delete(Greenroad greenroad) {
		this.hibernateTemplate.delete(greenroad);
	}
	//根据ID删除一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}
	
	//修改一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#update(com.jlj.model.Greenroad)
	 */
	public void update(Greenroad greenroad) {
		this.hibernateTemplate.update(greenroad);
	}
	
	//根据hql语句、条件、条件值修改某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
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
	 * @see com.jlj.dao.imp.IGreenroad#getGreenroads()
	 */
	public List<Greenroad> getGreenroads() {
		return this.hibernateTemplate.loadAll(Greenroad.class);
	}
	
	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#queryList(java.lang.String)
	 */
	public List<Greenroad> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}
	
	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<Greenroad> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}
	
	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<Greenroad> queryList(String queryString, String[] paramNames,
			Object[] values)
	{
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}
	
	
	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<Greenroad> getObjectsByIdList(final String hql,final List<Integer> idList) {
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
	 * @see com.jlj.dao.imp.IGreenroad#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<Greenroad> pageList(final String queryString,final Object[] p,final Integer page,
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
	 * @see com.jlj.dao.imp.IGreenroad#getUniqueResult(java.lang.String, java.lang.Object[])
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
	 * @see com.jlj.dao.imp.IGreenroad#loadById(int)
	 */
	public Greenroad loadById(int id) {
		return (Greenroad) this.hibernateTemplate.load(Greenroad.class, id);
	}
	
	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public Greenroad queryByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(Greenroad)list.get(0):null;
	}
	
	//根据hql语句、条件值来查询是否存在该记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#checkGreenroadExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkGreenroadExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}
	//根据hql批量修改
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.IGreenroad#updateGreenroadByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateGreenroadByhql(String queryString, Object[] p) {
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
	 * @see com.jlj.dao.imp.IGreenroad#loadByMkid(java.lang.String)
	 */
	public Greenroad loadByMkid(String queryString) {
		// TODO Auto-generated method stub
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		List greenroads = query.list();
		System.out.println(greenroads);
		if(greenroads!=null&&greenroads.size()>0)
		{
			return (Greenroad) greenroads.get(0);
		}
		return null;
	}
	


}
