package com.homeservices.dto.common;

import com.homeservices.util.RepoHelper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CRequest {
	
	public Header header;
	
	public String requestedDateTime;
	
	public Long userId;
	
	public String username = RepoHelper.getUser()!=null?RepoHelper.getUser().getUsername():null;
	
	public String jwt = RepoHelper.getUser()!=null?RepoHelper.getUser().getJwtToken():null;
	
}
