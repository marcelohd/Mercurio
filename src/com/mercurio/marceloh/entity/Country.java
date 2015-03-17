package com.mercurio.marceloh.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(schema="public",name="country")
public class Country implements java.io.Serializable {
	
	private static final long serialVersionUID = -1293863711504878883L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="country_sequence")
	@SequenceGenerator(sequenceName="country_sequence", name = "country_sequence")
	private Long 	code;
	private String 	name;
	private String 	codei18n;
	private String 	acronym;
	private Boolean trash;
	
	/*Getters and Setters*/
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
	
	public String getCodei18n() {
		return codei18n;
	}
	
	public void setCodei18n(String codei18n) {
		this.codei18n = codei18n;
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	public Boolean getTrash() {
		return trash;
	}
	
	public void setTrash(Boolean trash) {
		this.trash = trash;
	}
	
	/*hashCode and Equals*/
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
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
}
