package com.nightingale;

import java.util.Arrays;
import java.util.List;

public final class Constants {
	
    public static class Roles {
        public static final String SA = "SA";
        public static final String AD = "AD";
    }

    public static class Profiles {
        public static final String LOCAL = "local";
        public static final String DEVELOPMENT = "dev";
        public static final String STAGING = "stg";
        public static final String PRODUCTION = "prod";
    }
    
    public static class TagTypes {
        public static final String CATEGORIES = "Categories";
        public static final String AUTHORS = "Authors";
        public static final String BOOK_TAG = "Book Tag";
    }
    
    public static class Currency {
        public static final String SGD = "SGD";
        public static final String VND = "VND";
        public static final List<String> AVAILABLES = Arrays.asList(SGD,VND);
    }

	public static final int MAX_FAILED_LOGIN_ATTEMPTS = 10;
   
}
