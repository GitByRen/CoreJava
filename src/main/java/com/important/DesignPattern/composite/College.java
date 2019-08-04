package com.important.DesignPattern.composite;

import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent {

	private List<OrganizationComponent> departments = new ArrayList<>();

	public College(String name, String desc) {
		super(name, desc);
	}

	@Override
	protected void add(OrganizationComponent organizationComponent) {
		departments.add(organizationComponent);
	}

	@Override
	public void print() {
		System.out.println("--------------" + super.getName() + "--------------");
		departments.stream().forEach(department -> {
			department.print();
		});
	}

}
