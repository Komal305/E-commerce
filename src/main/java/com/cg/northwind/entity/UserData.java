//package com.cg.northwind.entity;
//
//import java.util.Set;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//@Entity
//@Table(name="user_data")
//public class UserData {
//
//
//	    @Id
//	    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
//	    @GenericGenerator(name = "native",strategy = "native")
//	    @Column(name = "userdata_id")
//	    private int id;
//	    private String email;
//	    
//	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	    private String password;
//
//	    private String role;
//
//
//	    @JsonIgnore
//	    @OneToMany(mappedBy="userdata",fetch=FetchType.EAGER)
//	    private Set<Authority> authorities;
//
//
//		public int getId() {
//			return id;
//		}
//
//
//		public void setId(int id) {
//			this.id = id;
//		}
//
//
//		public String getEmail() {
//			return email;
//		}
//
//
//		public void setEmail(String email) {
//			this.email = email;
//		}
//
//
//		public String getPassword() {
//			return password;
//		}
//
//
//		public void setPassword(String password) {
//			this.password = password;
//		}
//
//
//		public String getRole() {
//			return role;
//		}
//
//
//		public void setRole(String role) {
//			this.role = role;
//		}
//
//
//		public Set<Authority> getAuthorities() {
//			return authorities;
//		}
//
//
//		public void setAuthorities(Set<Authority> authorities) {
//			this.authorities = authorities;
//		}
//	    
//	    
//
//}
//
