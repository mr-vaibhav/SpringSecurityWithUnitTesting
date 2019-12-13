package com.security.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "authority")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@NotEmpty(message = "Authority type not be empty")
	private AuthorityType name;

	enum AuthorityType {
		ROLE_ADMIN, ROLE_USER
	}

	public Authority() {
		super();
	}

	public Authority(Integer id, AuthorityType name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AuthorityType getName() {
		return name;
	}

	public void setName(AuthorityType name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", name=" + name + "]";
	}

}
