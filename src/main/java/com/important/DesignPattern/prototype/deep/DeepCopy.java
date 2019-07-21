package com.important.DesignPattern.prototype.deep;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DeepCopy implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private DeepClonableTarget deClonableTarget;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeepClonableTarget getDeClonableTarget() {
		return deClonableTarget;
	}

	public void setDeClonableTarget(DeepClonableTarget deClonableTarget) {
		this.deClonableTarget = deClonableTarget;
	}

	// 深拷贝-方式1 使用clone方法
	@Override
	protected DeepCopy clone() {
		DeepCopy deepCopy = null;
		try {
			deepCopy = (DeepCopy) super.clone();
			deepCopy.deClonableTarget = deClonableTarget.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		// 对引用类型的属性，进行单独处理
		return deepCopy;
	}

	// 深拷贝-方式2 使用序列化反序列化
	public DeepCopy deepClone() {
		ObjectInputStream ois = null;
		ByteArrayInputStream bis = null;
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(this);
			bis = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bis);
			DeepCopy deepCopy = (DeepCopy) ois.readObject();
			return deepCopy;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
