package com.mercurio.marceloh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="scholarity")
public class Scholarity implements java.io.Serializable {
	

	private static final long serialVersionUID = 976444540370315273L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="scholarity_sequence")
	@SequenceGenerator(sequenceName="scholarity_sequence", name = "scholarity_sequence")
	private Long    code;
	private String  name;
	private Boolean trash;
	
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
	
	public Boolean getTrash() {
		return trash;
	}
	
	public void setTrash(Boolean trash) {
		this.trash = trash;
	}

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
		Scholarity other = (Scholarity) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}
