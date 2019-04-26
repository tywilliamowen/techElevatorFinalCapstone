package com.techelevator.model.Log;

public class Log {
	private String editingUser;
	private String logText;
	private String timestamp;
	private long logEvent;

	public String getEditingUser() {
		return editingUser;
	}

	public void setEditingUser(String editingUser) {
		this.editingUser = editingUser;
	}

	public String getLogText() {
		return logText;
	}

	public void setLogText(String logText) {
		this.logText = logText;
	}

	public long getLogEvent() {
		return logEvent;
	}

	public void setLogEvent(long logEvent) {
		this.logEvent = logEvent;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
