package com.ohand.ohandFlow.event.listener;

public class Queue extends java.util.Vector {
	
	private static final long serialVersionUID = 6437065111935535398L;
	
	private String name = "";

	public Queue() {
		super();
	}

	public Queue(String str) {
		super();
		this.setName(str);
	}

	public synchronized void enq(Object x) {
		if (x == null)
			return;
		super.add(x);
	}

	public synchronized Object deq() {
		if (this.isEmpty()) {
			return null;
		}
		Object x = this.get(0);
		this.remove(0);
		return x;
	}

	public synchronized Object front() {
		if (this.isEmpty()) {
			return null;
		}
		Object x = this.get(0);
		return x;
	}

	public boolean isEmpty() {
		return super.isEmpty();
	}

	public synchronized void clear() {
		return;
	}

	public int search(Object x) {
		return super.indexOf(x);
	}

	public String getName() {
		return name;
	}

	public void setName(String string) {
		name = string;
	}

}
