package com.ohand.ohandFlow.domain.fsm;

import java.util.EventObject;
import java.util.Iterator;

import com.ohand.ohandFlow.event.listener.AbstractEventQueue;
import com.ohand.ohandFlow.event.listener.OhandEventListener;

public class FlowInstEventQueue extends AbstractEventQueue
{
	private static FlowInstEventQueue flowInstEventQueue = null;

	public static FlowInstEventQueue getInstance()
	{
		if (flowInstEventQueue == null)
		{
			flowInstEventQueue = new FlowInstEventQueue();
		}
		return flowInstEventQueue;
	}

	private FlowInstEventQueue()
	{
		boolean useThread = true;
		 
		this.setUseThread(useThread);
	}

	protected void invokeEvent(EventObject event)
	{
		if (event != null && event instanceof FlowInstEvent)
		{
			OhandEventListener listener = null;
			FlowInstEvent fe = (FlowInstEvent) event;
			try
			{
				Iterator it = listeners.values().iterator();
				while (it.hasNext() && !this.isStopped())
				{
					try
					{	//TODO  使用的具体事件监听器
						listener = (OhandEventListener) it.next();
						listener.onEvent(fe);
					}
					catch (Exception ex)
					{
						ex.printStackTrace();
					}
				}
			}
			catch (Exception exp)
			{
				exp.printStackTrace();
			}
		}
	}
	
	public String getQueueName()
	{
		return "flowInstEvent";
	}

}
