package com.important.DesignPattern.composite;

import java.util.ArrayList;
import java.util.List;

public class University extends OrganizationComponent {

	private List<OrganizationComponent> colleges = new ArrayList<>();

	public University(String name, String desc) {
		super(name, desc);
	}

	@Override
	protected void add(OrganizationComponent organizationComponent) {
		colleges.add(organizationComponent);
	}

	@Override
	public void print() {
		System.out.println("--------------" + super.getName() + "--------------");
		colleges.stream().forEach(college -> {
			college.print();
		});
	}

}
