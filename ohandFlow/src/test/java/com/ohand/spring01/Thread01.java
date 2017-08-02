package com.ohand.spring01;

public class Thread01 {

	public static void main(String[] args) {
		try {
			MyThread t=new MyThread();
			t.start();
			Thread.sleep(100);
			t.interrupt();
			
			System.out.println("是否停止1："+t.interrupted());
			System.out.println("是否停止2："+t.interrupted());
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class MyThread extends Thread{
	public void run(){
		super.run();
		for(int i=0;i<5000;i++){
			System.out.println("i="+(i+1));
		}
	}
}


