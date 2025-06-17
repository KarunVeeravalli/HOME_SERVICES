package com.homeservices.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {
	
   public String username;
   public Long userId;
   public Boolean isLoggedIn = true;
   public Boolean isAdmin ;
   private String jwtToken;
}
