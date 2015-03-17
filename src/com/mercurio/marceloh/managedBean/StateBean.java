package com.mercurio.marceloh.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mercurio.marceloh.entity.State;
import com.mercurio.marceloh.persistence.JPAUtil;

@ManagedBean
public class StateBean {
	private State state = new State();
	private List<State> states;
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public List<State> getStates() {
		if(states == null){
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("SELECT a FROM state a",State.class);
			states = q.getResultList();
			em.close();
		}		
		return states;
	}
	public void onSave(State state){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		state = em.merge(state);
		em.persist(state);		
		em.getTransaction().commit();
		em.close();		
	}

}
