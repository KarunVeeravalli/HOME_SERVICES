package com.homeservices.dto.common;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Header implements Serializable {
	
	private static final long serialVersionUID = -4180118016859931629L;
	private String channel; //mobile or web
	private String deviceId; 
	private String locale; //en_us
	private String trackingId; //uuid
	private String timestamp; 
	private String browserDetails;
	private String browserVersion;	
	
}
