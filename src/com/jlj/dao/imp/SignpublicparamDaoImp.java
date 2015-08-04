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

import com.jlj.dao.ISignpublicparamDao;
import com.jlj.model.Signpublicparam;
@Component("signpublicparamDao")
public class SignpublicparamDaoImp implements ISignpublicparamDao {
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
	 * @see com.jlj.dao.imp.ISignpublicparamDao#save(com.jlj.model.Signpublicparam)
	 */
	public void save(Signpublicparam signpublicparam) {
		this.hibernateTemplate.save(signpublicparam);
	}
	
	//保存一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#savereturn(com.jlj.model.Signpublicparam)
	 */
	public Integer savereturn(Signpublicparam signpublicparam) {
		return (Integer) this.hibernateTemplate.save(signpublicparam);
	}
	
	//删除一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#delete(com.jlj.model.Signpublicparam)
	 */
	public void delete(Signpublicparam signpublicparam) {
		this.hibernateTemplate.delete(signpublicparam);
	}
	//根据ID删除一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#deleteById(int)
	 */
	public void deleteById(int id) {
		this.hibernateTemplate.delete(this.loadById(id));
	}
	
	//修改一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#update(com.jlj.model.Signpublicparam)
	 */
	public void update(Signpublicparam signpublicparam) {
		this.hibernateTemplate.update(signpublicparam);
	}
	
	//根据hql语句、条件、条件值修改某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#updateByHql(java.lang.String, java.lang.String[], java.lang.Object[])
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
	 * @see com.jlj.dao.imp.ISignpublicparamDao#getSignpublicparams()
	 */
	public List<Signpublicparam> getSignpublicparams() {
		return this.hibernateTemplate.loadAll(Signpublicparam.class);
	}
	
	//根据hql语句来查询所有记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#queryList(java.lang.String)
	 */
	public List<Signpublicparam> queryList(String queryString) {
		return this.hibernateTemplate.find(queryString);
	}
	
	//根据hql、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#getObjectsByCondition(java.lang.String, java.lang.Object[])
	 */
	public List<Signpublicparam> getObjectsByCondition(String queryString, Object[] p) {
		return this.hibernateTemplate.find(queryString,p);
	}
	
	//根据hql语句、条件、条件值查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#queryList(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public List<Signpublicparam> queryList(String queryString, String[] paramNames,
			Object[] values)
	{
		List list =  this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list;
	}
	
	
	//根据hql、id列表查询某些记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#getObjectsByIdList(java.lang.String, java.util.List)
	 */
	public List<Signpublicparam> getObjectsByIdList(final String hql,final List<Integer> idList) {
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
	 * @see com.jlj.dao.imp.ISignpublicparamDao#pageList(java.lang.String, java.lang.Object[], java.lang.Integer, java.lang.Integer)
	 */
	public List<Signpublicparam> pageList(final String queryString,final Object[] p,final Integer page,
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
	 * @see com.jlj.dao.imp.ISignpublicparamDao#getUniqueResult(java.lang.String, java.lang.Object[])
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
	 * @see com.jlj.dao.imp.ISignpublicparamDao#loadById(int)
	 */
	public Signpublicparam loadById(int id) {
		return (Signpublicparam) this.hibernateTemplate.load(Signpublicparam.class, id);
	}
	
	//根据hql语句、条件、值来查询一条记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#queryByNamedParam(java.lang.String, java.lang.String[], java.lang.Object[])
	 */
	public Signpublicparam queryByNamedParam(String queryString, String[] paramNames,
			Object[] values) {
		List list=this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
		return list.size()>0?(Signpublicparam)list.get(0):null;
	}
	
	//根据hql语句、条件值来查询是否存在该记录
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#checkSignpublicparamExistsWithName(java.lang.String, java.lang.Object[])
	 */
	public boolean checkSignpublicparamExistsWithName(String queryString, Object[] p) {
		List list =  this.hibernateTemplate.find(queryString,p);
		return list.size()>0 ? true : false;
	}
	//根据hql批量修改
	/* (non-Javadoc)
	 * @see com.jlj.dao.imp.ISignpublicparamDao#updateSignpublicparamByhql(java.lang.String, java.lang.Object[])
	 */
	public int updateSignpublicparamByhql(String queryString, Object[] p) {
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
	 * @see com.jlj.dao.imp.ISignpublicparamDao#loadByMkid(java.lang.String)
	 */
	public Signpublicparam loadByMkid(String queryString) {
		// TODO Auto-generated method stub
		Session session=this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query= session.createQuery(queryString);
		List signpublicparams = query.list();
		System.out.println(signpublicparams);
		if(signpublicparams!=null&&signpublicparams.size()>0)
		{
			return (Signpublicparam) signpublicparams.get(0);
		}
		return null;
	}
	


}
