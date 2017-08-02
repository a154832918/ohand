package com.ohand.ohandFlow.domain.fsm;

import java.util.EventObject;
import java.util.Iterator;

import com.ohand.ohandFlow.event.listener.AbstractEventQueue;
import com.ohand.ohandFlow.event.listener.OhandEventListener;


public class WorkItemEventQueue extends AbstractEventQueue
{

	private static WorkItemEventQueue workItemEventQueue = null;

	public static WorkItemEventQueue getInstance()
	{
		if (workItemEventQueue == null)
		{
			workItemEventQueue = new WorkItemEventQueue();
		}
		return workItemEventQueue;
	}

	private WorkItemEventQueue()
	{
		boolean useThread = true;
		 
		this.setUseThread(useThread);
	}

	protected void invokeEvent(EventObject event)
	{
		if (event != null && event instanceof WorkItemEvent)
		{
			OhandEventListener listener = null;
			WorkItemEvent fe = (WorkItemEvent) event;
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
		return "workItemEvent";
	}

}
