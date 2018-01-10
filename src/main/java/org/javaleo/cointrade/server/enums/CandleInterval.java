package org.javaleo.cointrade.server.enums;

import org.apache.commons.lang3.StringUtils;

public enum CandleInterval {

		MIN5("5 min"), MIN10("10 min"), MIN30("30 min"), HOUR1("1 hour"), HOUR2("2 hours"), HOUR6("6 hours"), D1("1 day"), MON1("1 month");

	private CandleInterval(String description) {
		this.description = description;
	}

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static CandleInterval getByName(String name) {
		for (CandleInterval itn : CandleInterval.values()) {
			if (StringUtils.equalsIgnoreCase(itn.name(), name)) {
				return itn;
			}
		}
		return null;
	}

}
