package com.important.DesignPattern.prototype.deep;

import java.io.Serializable;

public class DeepClonableTarget implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private String cloneName;

	private String cloneClass;

	public DeepClonableTarget(String cloneName, String cloneClass) {
		this.cloneName = cloneName;
		this.cloneClass = cloneClass;
	}

	public String getCloneName() {
		return cloneName;
	}

	public void setCloneName(String cloneName) {
		this.cloneName = cloneName;
	}

	public String getCloneClass() {
		return cloneClass;
	}

	public void setCloneClass(String cloneClass) {
		this.cloneClass = cloneClass;
	}

	@Override
	protected DeepClonableTarget clone() throws CloneNotSupportedException {
		return (DeepClonableTarget) super.clone();
	}
}
