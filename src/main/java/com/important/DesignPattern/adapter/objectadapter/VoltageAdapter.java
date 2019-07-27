package com.important.DesignPattern.adapter.objectadapter;

public class VoltageAdapter implements Voltage5V {

	private Voltage220V voltage220V;
	
	public VoltageAdapter(Voltage220V voltage220V) {
		this.voltage220V = voltage220V;
	}
	
	@Override
	public int output5V() {
		int srcV = voltage220V.output220V();
		int destV = srcV / 44;
		return destV;
	}

}
