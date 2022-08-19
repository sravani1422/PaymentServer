package com.pp.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankBic {
	
	@Id
	String bic;
	
	String name;

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BankBic [bic=" + bic + ", name=" + name + "]";
	}
	
	
	
	
	
}
