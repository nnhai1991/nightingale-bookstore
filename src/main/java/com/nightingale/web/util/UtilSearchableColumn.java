package com.nightingale.web.util;

import java.util.LinkedHashMap;


/**
 * @author sl.wong
 *
 */
public class UtilSearchableColumn {
	
	public static class Order {
		
		public static OrderHashMap getSearchOptions() {
			return new OrderHashMap();
		}

		public static final String				ID						= "Id";
		public static final String				DEFAULT_SEARCH_COLUMN	= ID;

		private static class OrderHashMap extends LinkedHashMap<String, String> {

			private static final long serialVersionUID = -893381517429358947L;

			public OrderHashMap() {
				this.put(ID, "Search by ID");
			}
		}
	}
	
	public static class Queue {
		public static QueueHashMap getSearchOptions() {
			return new QueueHashMap();
		}

		public static final String				IC						= "IC";
		public static final String				EMAIL					= "Email";
		public static final String				PHONE_NUMBER			= "PhoneNumber";
		public static final String				DEFAULT_SEARCH_COLUMN	= IC;

		private static class QueueHashMap extends LinkedHashMap<String, String> {

			private static final long serialVersionUID = -893381517429358947L;

			public QueueHashMap() {
				this.put(IC, "Search by IC");
				this.put(EMAIL, "Search by Email");
				this.put(PHONE_NUMBER, "Search by Phone Number");
			}
		}
	} 
	
}
