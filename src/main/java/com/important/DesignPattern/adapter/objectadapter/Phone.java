package com.important.DesignPattern.adapter.objectadapter;

public class Phone {

	public void charging(Voltage5V iVoltage5v) {
		if (iVoltage5v.output5V() == 5) {
			System.out.println("适配后电压为5v，可以充电~~");
		} else if(iVoltage5v.output5V() > 5) {
			System.out.println("适配后电压大于5V，不能充电~~");
		}
	}
	
}
