package com.homeservices.security.util;

public interface TokenBlackList {
	void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}
