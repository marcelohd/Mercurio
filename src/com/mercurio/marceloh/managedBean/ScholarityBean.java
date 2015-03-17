package com.mercurio.marceloh.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mercurio.marceloh.entity.Scholarity;
import com.mercurio.marceloh.persistence.JPAUtil;

@ManagedBean
public class ScholarityBean {
	private Scholarity scholarity = new Scholarity();
	private List<Scholarity> scholarities;
	
	public Scholarity getScholarity() {
		return scholarity;
	}
	
	public void setScholarity(Scholarity scholarity) {
		this.scholarity = scholarity;
	}
	
	public List<Scholarity> getScholarities() {
		if(scholarities == null){
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("SELECT a FROM scholarity a",Scholarity.class);
			scholarities = q.getResultList();
			em.close();
		}		
		return scholarities;
	}
	public void onSave(Scholarity scholarity){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		scholarity = em.merge(scholarity);
		em.persist(scholarity);		
		em.getTransaction().commit();
		em.close();		
	}


}
