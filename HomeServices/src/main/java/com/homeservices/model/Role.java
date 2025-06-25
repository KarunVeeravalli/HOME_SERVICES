package com.homeservices.model;

import com.homeservices.enums.URole;
import com.homeservices.util.CommonClass;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ROLE" , uniqueConstraints = {@UniqueConstraint(columnNames =  "name")})
public class Role extends CommonClass{
	
	@Column(length = 20)
	@Enumerated(EnumType.STRING)
	private URole name;
	
	 	public Role(Long id, URole name) {
			this.id = id;
			this.name = name;
		}

		public Role(URole name) {
			this.name = name;
		}
	}
