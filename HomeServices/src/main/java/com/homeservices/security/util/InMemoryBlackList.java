package com.homeservices.security.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class InMemoryBlackList implements TokenBlackList{

	private Set<String> blacklist = new HashSet<>();
	
	@Override
	public void addToBlacklist(String token) {
		blacklist.add(token);
	}

	@Override
	public boolean isBlacklisted(String token) {
		return blacklist.contains(token);
	}

}
