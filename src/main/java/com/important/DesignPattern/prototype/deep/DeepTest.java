package com.important.DesignPattern.prototype.deep;

public class DeepTest {

	public static void main(String[] args) {
		DeepCopy deepCopy = new DeepCopy();
		deepCopy.setName("宋江");
		DeepClonableTarget deClonableTarget = new DeepClonableTarget("clone", "cloneClass");
		deepCopy.setDeClonableTarget(deClonableTarget);

		System.out.println("deepCopy.name：" + deepCopy.getName() + "，deepCopy.deepClonableTarget："
				+ deepCopy.getDeClonableTarget().hashCode());
		
		// 方式一：
		DeepCopy deepClone = deepCopy.clone();
		System.out.println("deepClone.name：" + deepClone.getName() + "，deepClone.deepClonableTarget："
				+ deepClone.getDeClonableTarget().hashCode());

		// 方式二：
		DeepCopy serialClone = deepCopy.deepClone();
		System.out.println("serialClone.name：" + serialClone.getName() + "，serialClone.deepClonableTarget："
				+ serialClone.getDeClonableTarget().hashCode());
	}

}
