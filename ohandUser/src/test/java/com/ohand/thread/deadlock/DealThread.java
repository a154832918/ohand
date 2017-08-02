package com.ohand.thread.deadlock;

public class DealThread implements Runnable {

	public String username;
	public Object lock1=new Object();
	public Object lock2=new Object();
	
	public void setFlag(String username){
		this.username=username;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		if(username.equals("a")){
			synchronized (lock1) {
				try {
					System.out.println("username="+username);
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			synchronized (lock2) {
				try {
					System.out.println("lock1>lock2");
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(username.equals("b")){
			synchronized (lock2) {
				try {
					System.out.println("username="+username);
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			synchronized (lock1) {
				try {
					System.out.println("lock2>lock1");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
