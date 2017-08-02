package com.ohand.ohandFlow.event.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;

import com.ohand.ohandFlow.common.UUIDUtil;

public abstract class AbstractEventQueue {

	protected HashMap listeners = new HashMap();

	protected Queue queue = new Queue("事件队列");

	protected Invoker invoker = null;

	private boolean bStop = false;

	private boolean useThread = true;

	protected boolean debug;

	protected AbstractEventQueue() {
		this.start();
	}

	public class Invoker extends Thread {
		public void run() {
			EventObject event = null;
			while (true) {
				if (isStopped()) {
					break;
				}
				if (queue.isEmpty()) {
					break;
				}
				EventWrapper wrapper = pop();
				if (wrapper != null) {
					event = wrapper.getEvent();
					if (event != null) {
						invokeEvent(event);
					}
				}
			}
		}
	}

	abstract protected void invokeEvent(EventObject event);

	public void put(EventObject event) {
		EventWrapper wrapper = new EventWrapper(event);
		put(wrapper);
	}

	protected void put(EventWrapper wrapper) {
		if (isUseThread()) {
			queue.enq(wrapper);
			if (invoker == null || !invoker.isAlive()) {
				invoker = new Invoker();
				invoker.start();
			}
		} else {
			invokeEvent(wrapper.getEvent());
		}
	}

	protected synchronized EventWrapper pop() {
		return (EventWrapper) queue.deq();
	}

	protected void finalize() throws Throwable {
		stop();
	}

	public synchronized boolean isStopped() {
		return bStop;
	}

	public synchronized void stop() {
		bStop = true;
	}

	public synchronized void start() {
		bStop = false;
	}

	public synchronized void registerListener(String name,
			EventListener listener) {
		listeners.put(name, listener);
	}

	public synchronized void unregisterListener(String name) {
		listeners.remove(name);
	}

	public synchronized EventListener getListener(String name) {
		return (EventListener) listeners.get(name);
	}

	public static final class EventWrapper {
		String id = null;

		Date eventTime = null;

		EventObject event = null;

		public EventWrapper(EventObject event) {
			this.event = event;
			eventTime = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			id = sf.format(eventTime) + UUIDUtil.generateUUID();
		}

		public EventObject getEvent() {
			return event;
		}

		public Date getEventTime() {
			return eventTime;
		}

		public String getId() {
			return id;
		}

		public void setEvent(EventObject object) {
			event = object;
		}

		public void setEventTime(Date date) {
			eventTime = date;
		}

		public void setId(String string) {
			id = string;
		}

	}

	/**
	 * @return
	 */
	public synchronized boolean isUseThread() {
		return useThread;
	}

	/**
	 * @param b
	 */
	public synchronized void setUseThread(boolean b) {
		useThread = b;
	}

}
