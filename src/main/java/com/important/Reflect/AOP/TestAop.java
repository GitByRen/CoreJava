package com.important.Reflect.AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Human {

    void info();

    void fly();
}

class SuperMan implements Human {

    @Override
    public void info() {
        System.out.println("我是超人！");
    }

    @Override
    public void fly() {
        System.out.println("I believe I can fly！");
    }
}

class HumanUtil {

    public void method1() {
        System.out.println("=======第一个方法========");
    }

    public void method2() {
        System.out.println("=======第二个方法========");
    }
}

class MyInvitationHandler implements InvocationHandler {

    Object obj;

    public MyInvitationHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil hu = new HumanUtil();
        hu.method1();
        Object invoke = method.invoke(obj, args);
        hu.method2();
        return invoke;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
}

public class TestAop {
    public static void main(String[] args) {
        MyInvitationHandler mih = new MyInvitationHandler(new SuperMan());
        Human obj = mih.getProxy();
        obj.info();
        obj.fly();
    }
}
