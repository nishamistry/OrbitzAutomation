package com.ispl.automation.sample.orbitz.common;

import com.infostretch.automation.step.QAFTestStep;


public class Main {
	@QAFTestStep(description = "hello {0}")
	void hello(Object s) {
		System.out.println(s);
	}
}
