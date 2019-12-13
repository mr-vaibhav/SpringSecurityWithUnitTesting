package com.security.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "user")
public class UserLogin implements Serializable{

	
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    @NotEmpty(message = "username must not be empty")
	    @Column(nullable = false)
	    @Pattern(regexp="^[A-Za-z]*$")
	    private String username;
	    @NotEmpty(message = "password not be empty")
	  // @Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,12}$")
	    private String password;
	    @Transient
	    private String passwordConfirm;
	    @Transient
	    private String role;
	    @Transient
	    private String userMail;
	    
		
		@CreationTimestamp
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "dateCreated", nullable = false)
	    private Date dateCreated;
	    
	    public String getPasswordConfirm() {
			return passwordConfirm;
		}
		public void setPasswordConfirm(String passwordConfirm) {
			this.passwordConfirm = passwordConfirm;
		}
		@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JoinTable(name = "user_authority",
	            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
	            inverseJoinColumns = { @JoinColumn(name = "authority_id", referencedColumnName = "id") })
	    private Set<Authority> authorities = new HashSet<>();
	    
	    
		public UserLogin() {
			super();
		}
		
		public UserLogin(Integer id, @NotEmpty String username, @NotEmpty String password, String passwordConfirm,
				Date dateCreated, Set<Authority> authorities) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.passwordConfirm = passwordConfirm;
			this.dateCreated = dateCreated;
			this.authorities = authorities;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Date getDateCreated() {
			return dateCreated;
		}
		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}
		public Set<Authority> getAuthorities() {
			return authorities;
		}
		public void setAuthorities(Set<Authority> authorities) {
			this.authorities = authorities;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public String getUserMail() {
			return userMail;
		}
		public void setUserMail(String userMail) {
			this.userMail = userMail;
		}
		@Override
		public String toString() {
			return "UserLogin [id=" + id + ", username=" + username + ", password=" + password + ", passwordConfirm="
					+ passwordConfirm + ", dateCreated=" + dateCreated + ", authorities=" + authorities + "]";
		}
		
		
    
}
