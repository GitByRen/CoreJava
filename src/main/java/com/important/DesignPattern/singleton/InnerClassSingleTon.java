package com.important.DesignPattern.singleton;

public class InnerClassSingleTon {

	private InnerClassSingleTon() {
	}

	private static class StaticInnerClass {
		private static final InnerClassSingleTon innerClassSingleTon = new InnerClassSingleTon();
	}

	public static InnerClassSingleTon getIntance() {
		return StaticInnerClass.innerClassSingleTon;
	}

}
