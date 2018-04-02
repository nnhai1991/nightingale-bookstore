package com.nightingale.util;

import java.util.HashMap;
import java.util.Map;

public class UtilConstants {
	
	//Shop Dashboard
	public static final String CURRENT_TAB = "currentTab";
	
	public class PaymentStatus {
		public static final String COMPLETED = "COMPLETED";
		public static final String DELIVERED = "DELIVERED";
	}
	
	public class PurchaseOrderStatus {
		public static final String CANCELLED = "CANCELLED";
		public static final String COMPLETED = "COMPLETED";
	}
	
	public static final Map<Integer, String> stockMasterStatus = createStockMasterStatusMap();
	private static Map<Integer, String> createStockMasterStatusMap() {
        Map<Integer,String> map = new HashMap<Integer,String>();
        map.put(0, "NEW");
        map.put(1, "DRAFT");
        map.put(2, "COMPLETED");
        return map;
    }
	
	public static class StockMasterStatus {
		public static final Integer NEW = 0;
		public static final Integer DRAFT = 1;
		public static final Integer COMPLETED = 2;
	}
	
	public static class Delivery {
		public static final Integer DRAFT = 1;
		public static final Integer COMPLETED = 2;
	}
	
	public static class IMEIInfoStatus {
		public static final Integer DRAFT = 1;
		public static final Integer AVAILABLE = 2;
		public static final Integer PAID = 3;
		public static final Integer ISSUED = 4;
	}
	
    public class Roles {

        public static final String SA = "SA";
        public static final String AD = "AD";
        public static final String IN = "IN";
        public static final String DE = "DE";
        public static final String PA = "PA";
        public static final String INDEPA = "INPADE";

    }

    public class Profiles {
        public static final String LOCAL = "local";
        public static final String DEVELOPMENT = "dev";
        public static final String STAGING = "stg";
        public static final String PRODUCTION = "prod";
    }

    public class Config {

        public static final String MAX_FAILED_LOGIN_ATTEMPTS = "max_failed_login_attempts";
        public static final String DEFAULT_TIME_ZONE_ID = "default_time_zone_id";
    }

    public class Staff {

        public static final int OTHERS_ID = -1;
        public static final String OTHERS = "Others";

    }

    public class EmailGroupCodes{
        public static final String TOP_MANAGEMENT_CODE = "top_management";
        //public static final String SYSTEM_MANAGEMENT_CODE = "system_management";    	
        public static final String TOP_MANAGEMENT = "Top Management";
        //public static final String SYSTEM_MANAGEMENT = "System Management";    	
    }
    
    public enum ExcelRowDataType{
        BOOLEAN,
        DATE,
        DOUBLE,
        STRING
    }
    
}
