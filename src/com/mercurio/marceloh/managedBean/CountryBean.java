package com.mercurio.marceloh.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mercurio.marceloh.entity.Country;
import com.mercurio.marceloh.persistence.JPAUtil;

@ManagedBean
public class CountryBean {

	private Country country = new Country();
	private List<Country> countries;
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public List<Country> getCountries() {
		if(countries == null){
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("SELECT a FROM country a",Country.class);
			countries = q.getResultList();
			em.close();
		}		
		return countries;
	}
	public void onSave(Country country){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		country = em.merge(country);
		em.persist(country);		
		em.getTransaction().commit();
		em.close();		
	}

}
