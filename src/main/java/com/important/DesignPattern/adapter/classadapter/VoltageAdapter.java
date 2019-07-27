package com.important.DesignPattern.adapter.classadapter;

public class VoltageAdapter extends Voltage220V implements Voltage5V {

	@Override
	public int output5V() {
		int srcV = output220V();
		int destV = srcV / 44;
		return destV;
	}

}
