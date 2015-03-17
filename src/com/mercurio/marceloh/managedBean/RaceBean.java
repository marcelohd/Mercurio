package com.mercurio.marceloh.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mercurio.marceloh.entity.Race;
import com.mercurio.marceloh.persistence.JPAUtil;

@ManagedBean
public class RaceBean {
	
	private Race race = new Race();
	private List<Race> races;
	
	public Race getRace() {
		return race;
	}
	
	public void setRace(Race race) {
		this.race = race;
	}
	
	public List<Race> getRaces() {
		if(races == null){
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("SELECT a FROM race a",Race.class);
			races = q.getResultList();
			em.close();
		}		
		return races;
	}
	public void onSave(Race race){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		race = em.merge(race);
		em.persist(race);		
		em.getTransaction().commit();
		em.close();		
	}

}
