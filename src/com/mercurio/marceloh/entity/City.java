package com.mercurio.marceloh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema="public",name="city")
public class City implements java.io.Serializable{

	private static final long serialVersionUID = 3247280763933989589L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="city_sequence")
	@SequenceGenerator(sequenceName="city_sequence", name = "city_sequence")
	private Long     code;
	private String   name;
	private Integer  ibge;
	private String   state;
	private Boolean  trash;
	
	/*Getters ans Setters */
	public Long getCode() {
		return code;
	}
	
	public void setCode(Long code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getIbge() {
		return ibge;
	}
	
	public void setIbge(Integer numberibge) {
		this.ibge = numberibge;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public Boolean getTrash() {
		return trash;
	}
	
	public void setTrash(Boolean trash) {
		this.trash = trash;
	}

	/*Equals e HashCode*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
}

