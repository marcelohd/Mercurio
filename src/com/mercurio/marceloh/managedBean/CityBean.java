package com.mercurio.marceloh.managedBean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.mercurio.marceloh.entity.City;
import com.mercurio.marceloh.persistence.JPAUtil;

@ManagedBean
public class CityBean {
	private City city = new City();
	private List<City> cities;
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	public List<City> getCities() {
		if(cities == null){
			EntityManager em = JPAUtil.getEntityManager();
			Query q = em.createQuery("SELECT a FROM city a",City.class);
			cities = q.getResultList();
			em.close();
		}		
		return cities;
	}
	public void onSave(City city){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		city = em.merge(city);
		em.persist(city);		
		em.getTransaction().commit();
		em.close();		
	}
}
