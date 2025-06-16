package com.homeservices.model;

import com.homeservices.util.CommonClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Email extends CommonClass{
	
	private String fromEmail;
	
	private String toEmail;
	
	private String subject;
	
	@Lob
	private String body;
}
