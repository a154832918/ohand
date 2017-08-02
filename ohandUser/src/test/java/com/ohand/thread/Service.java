package com.ohand.thread;

public class Service {
	String lockA=new String("A");
	String lockB=new String("B");
	
	public void methodA(){
		synchronized (lockA) {
			System.out.println("methodA     begin......");
			boolean isContinueRun=true;
			while(isContinueRun){
				// System.out.println("methodA     +++++......");
			}
			System.out.println("methodA     end......");
		}
		
	}
	
	synchronized public void methodB(){
		System.out.println("methodB  methodB   methodB......");
		
		System.out.println("methodB  methodB   methodB......");
	}
	
}
