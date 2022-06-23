package com.qa.opencart.customExceptions;

public class FrameworkException extends Exception{
	public FrameworkException(String messg){
		super(messg);
		printStackTrace();
	}


}

