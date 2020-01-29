package com.important.juc.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.important.java8.lambda.Employee;
import com.important.java8.lambda.Employee.Status;

/**
 * CompletableFuture 提供了四个静态方法来创建一个异步操作：
 * public static CompletableFuture<Void> runAsync(Runnable runnable) 
 * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U>supplier,Executor executor)
 * 
 * runAsync方法不支持返回值，supplyAsync可以支持返回值。
 * 
 * 
 * 计算结果完成时的回调方法：
 * public CompletableFuture<T> whenComplete(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action)
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T,? super Throwable> action, Executor executor)
 * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn)
 * 
 * whenComplete 和 whenCompleteAsync 的区别：
 * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
 * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
 * 
 * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化：
 * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
 * 
 * handle 是执行任务完成时对结果的处理。
 * handle 方法和 thenApply 方法处理方式基本一样。不同的是：
 * handle 是在任务完成后再执行，还可以处理异常的任务。
 * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
 * public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
 * public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn,Executor executor);
 * 
 * 
 * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
 * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn);
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other,BiFunction<? super T,? super U,? extends V> fn,Executor executor);
 * 
 */
public class TestCompletableFuture {

	/**
	 * 描述串行关系
	 * 
	 * CompletionStage<R> thenApply(fn);
	 * CompletionStage<R> thenApplyAsync(fn);
	 * CompletionStage<Void> thenAccept(consumer);
	 * CompletionStage<Void> thenAcceptAsync(consumer);
	 * CompletionStage<Void> thenRun(action);
	 * CompletionStage<Void> thenRunAsync(action);
	 * CompletionStage<R> thenCompose(fn);
	 * CompletionStage<R> thenComposeAsync(fn);
	 */
	@Test
	public void testApply() {
		String join = CompletableFuture.supplyAsync(() -> "He").thenApplyAsync(v -> v + "llo").thenApply(String::toUpperCase).join();
		System.out.println(join);
	}
	
	/**
	 * 描述 AND 汇聚关系
	 * 
	 * CompletionStage<R> thenCombine(other, fn);
	 * CompletionStage<R> thenCombineAsync(other, fn);
	 * CompletionStage<Void> thenAcceptBoth(other, consumer);
	 * CompletionStage<Void> thenAcceptBothAsync(other, consumer);
	 * CompletionStage<Void> runAfterBoth(other, action);
	 * CompletionStage<Void> runAfterBothAsync(other, action);
	 */
	@Test
	public void testCombine() {
		String result = CompletableFuture.supplyAsync(() -> {
			try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
		}).thenCombine(CompletableFuture.supplyAsync(()-> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1,s2) -> s1 + " " + s2).join();
		System.out.println(result);
	}
	
	/**
	 * 描述OR 关系
	 * 
	 * CompletionStage applyToEither(other, fn);
	 * CompletionStage applyToEitherAsync(other, fn);
	 * CompletionStage acceptEither(other, consumer);
	 * CompletionStage acceptEitherAsync(other, consumer);
	 * CompletionStage runAfterEither(other, action);
	 * CompletionStage runAfterEitherAsync(other, action);
	 */
	@Test
	public void testApplyToEither() {	
		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
			int t = 5;
			try {
				TimeUnit.SECONDS.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return String.valueOf(t);
		});

		CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
			int t = 10;
			try {
				TimeUnit.SECONDS.sleep(t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return String.valueOf(t);
		});

		CompletableFuture<String> f3 = f1.applyToEither(f2, s -> s);

		System.out.println(f3.join());
	}
	
	/**
	 * 异常处理
	 * 
	 * CompletionStage exceptionally(fn);
	 * CompletionStage<R> whenComplete(consumer);
	 * CompletionStage<R> whenCompleteAsync(consumer);
	 * CompletionStage<R> handle(fn);
	 * CompletionStage<R> handleAsync(fn);
	 */
	@Test
	public void testWhenComplete() {	
		CompletableFuture.supplyAsync(() -> "He").whenComplete((x, y) -> System.out.println(x));
	}
	
}
