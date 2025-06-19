package com.homeservices.dto.common;

import com.homeservices.util.RepoHelper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CRequest {
	
	public Header requestHeader;
	
	public String requestedDateTime;
	
	public Long userId;
	
	public String username = RepoHelper.isLoggedIn()?RepoHelper.getUser()!=null?RepoHelper.getUser().getUsername():null:null;
	
	public String jwt = RepoHelper.isLoggedIn()?RepoHelper.getUser()!=null?RepoHelper.getUser().getJwtToken():null:null;
	
	
}
