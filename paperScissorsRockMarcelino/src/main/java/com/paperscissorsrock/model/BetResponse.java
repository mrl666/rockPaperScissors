package com.paperscissorsrock.model;

public class BetResponse {

	private String appBet;
	private String resultBet;
	private String errorMsg;

	public BetResponse() {
	}

	public String getAppBet() {
		return appBet;
	}

	public void setAppBet(String appBet) {
		this.appBet = appBet;
	}

	public String getResultBet() {
		return resultBet;
	}

	public void setResultBet(String resultBet) {
		this.resultBet = resultBet;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "BetResponse [appBet=" + appBet + ", resultBet=" + resultBet
				+ ", errorMsg=" + errorMsg + "]";
	}
	
}
