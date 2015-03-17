package com.mercurio.marceloh.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mercurio.marceloh.entity.Marital;
import com.mercurio.marceloh.persistence.JPAUtil;

@ManagedBean
public class MaritalBean {
	
	private Marital marital = new Marital();
	private List<Marital> maritals;
	
	public Marital getMarital() {
		return marital;
	}
	
	public void setMarital(Marital marital) {
		this.marital = marital;
	}
	
	public List<Marital> getMaritals() {
		if(maritals == null){
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("SELECT a FROM marital a",Marital.class);
			maritals = q.getResultList();
			em.close();
		}		
		return maritals;
	}
	public void onSave(Marital marital){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		marital = em.merge(marital);
		em.persist(marital);		
		em.getTransaction().commit();
		em.close();		
	}

}
