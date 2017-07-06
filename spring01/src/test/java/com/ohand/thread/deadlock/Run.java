package com.ohand.thread.deadlock;

public class Run {

	public static void main(String[] args) {
		try {
		// TODO Auto-generated method stub
		DealThread t1=new DealThread();
		t1.setFlag("a");
		Thread t11=new Thread(t1);
		t11.start();
		
		
		Thread.sleep(100);
		
		
		
		DealThread t2=new DealThread();
		t2.setFlag("b");
		Thread t12=new Thread(t2);
		t12.start();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
