package com.mercurio.marceloh.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mercurio.marceloh.entity.PositiveNegative;
import com.mercurio.marceloh.persistence.JPAUtil;

@ManagedBean
public class PositiveNegativeBean {

	private PositiveNegative positiveNegative = new PositiveNegative();
	private List<PositiveNegative> positiveNegatives;
	
	public PositiveNegative getPositiveNegative() {
		return positiveNegative;
	}
	
	public void setPositiveNegative(PositiveNegative positiveNegative) {
		this.positiveNegative = positiveNegative;
	}
	
	public List<PositiveNegative> getPositiveNegatives() {
		if(positiveNegatives == null){
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("SELECT a FROM positiveNegative a",PositiveNegative.class);
			positiveNegatives = q.getResultList();
			em.close();
		}		
		return positiveNegatives;
	}
	public void onSave(PositiveNegative positiveNegative){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		positiveNegative = em.merge(positiveNegative);
		em.persist(positiveNegative);		
		em.getTransaction().commit();
		em.close();		
	}

}
