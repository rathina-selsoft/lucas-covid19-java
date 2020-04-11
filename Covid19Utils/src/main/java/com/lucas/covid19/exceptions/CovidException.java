package com.lucas.covid19.exceptions;

import org.apache.commons.lang3.StringUtils;

import com.lucas.covid19.constants.CovidConstant;



public class CovidException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5505734100902084448L;
	
	private String errorType;
	private String error;

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public CovidException() {
	}

	public CovidException(String errorType, String error) {
		this.errorType = errorType;
		this.error = error;
	}

	public CovidException(String errorType, Throwable t) {
		this.errorType = errorType;
		this.error = (t != null
				? (StringUtils
						.isNotBlank(t.getMessage())
								? t.getMessage()
								: (StringUtils.isNotBlank(t.getLocalizedMessage()) ? t.getLocalizedMessage()
										: CovidConstant.IRRECOVERABLE_ERROR))
				: CovidConstant.IRRECOVERABLE_ERROR);
	}

	public CovidException(String errorType, String customMessage, Throwable t) {
		this.errorType = errorType;
		this.error = new StringBuilder(customMessage)
				.append(" Exception is : ").append(
						t != null
								? (StringUtils.isNotBlank(t.getMessage()) ? t.getMessage()
										: (StringUtils.isNotBlank(t.getLocalizedMessage()) ? t.getLocalizedMessage()
												: CovidConstant.IRRECOVERABLE_ERROR))
								: CovidConstant.IRRECOVERABLE_ERROR)
				.toString();
	}

	@Override
	public String toString() {
		return ("Error Type : " + this.getErrorType() + ", Error : " + this.getError());
	}

}
