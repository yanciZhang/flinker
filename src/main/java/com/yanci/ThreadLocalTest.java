package com.yanci;

public class ThreadLocalTest {
	
	private static ThreadLocal<String> tl = new ThreadLocal<>();
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				String a = "sss";
				tl.set(a);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.gc();
				System.out.println(Thread.currentThread().getName() + "----" + tl.get());
				
				tl.remove();
				tl.set(a);
			}
		}, "aaa");
		
//		Thread t2 = new Thread(new Runnable() {
//			public void run() {
//				String a = "bbbb";
//				tl.set(a);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println(Thread.currentThread().getName() + "----" + tl.get());
//			}
//		}, "bbbb");
		
		t1.start();
//		t2.start();
	}

}
