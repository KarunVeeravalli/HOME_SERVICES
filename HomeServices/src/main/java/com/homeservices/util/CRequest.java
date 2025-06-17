package com.homeservices.util;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CRequest {
	
	public LocalDateTime requestedDateTime;
	
	public Long userId;
	
	public String username = RepoHelper.getUser()!=null?RepoHelper.getUser().getUsername():null;
	
	public String jwt = RepoHelper.getUser()!=null?RepoHelper.getUser().getJwtToken():null;
	
	public String requestId;
	
	
}
