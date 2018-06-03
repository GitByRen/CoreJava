package com.test;

import java.lang.reflect.Method;

public interface Advice {

	void beforeMethod(Method before);

	void afterMethod(Method after);

}
