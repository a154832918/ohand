package com.ohand.ohandFlow.event;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.PFConstant;
import com.ohand.ohandFlow.domain.EventListenConfigDomain;
import com.ohand.ohandFlow.event.listener.AbstractEventQueue;
import com.ohand.ohandFlow.framework.SpringContextUtils;
import com.ohand.ohandFlow.service.impl.EventListenConfigServiceImpl;

public class EventFactory
{
	private Map<String, AbstractEventQueue>  queueMap=new HashMap<String, AbstractEventQueue>();
	
	private static EventFactory EventFactory = null;
	
	private transient EventListenConfigServiceImpl eventListenConfigService=(EventListenConfigServiceImpl)SpringContextUtils.getBeanByClass(EventListenConfigServiceImpl.class);;

	public static EventFactory getInstance()
	{
		if (EventFactory == null)
		{
			EventFactory = new EventFactory();
		}
		return EventFactory;
	}

	private EventFactory()
	{
		Map map=new HashMap();
		map.put("isOpen", PFConstant.DIRT_IS_YES);
		List retList=eventListenConfigService.getObjectVOList(map);
		if(retList!=null&&retList.size()>0){
			for(int i=0;i<retList.size();i++){
				EventListenConfigDomain vo=(EventListenConfigDomain)retList.get(i);
				String listenClassStr=vo.getListenerClass();
				String queueClassStr=vo.getQueueClass();
				String queueName=vo.getQueueName();
				EventListener objListen=null;
				AbstractEventQueue objQueue=null;
				try {
					objListen=(EventListener) Class.forName(listenClassStr).newInstance();
					Class  objClass=Class.forName(queueClassStr);
					Constructor objC=objClass.getDeclaredConstructor();
		            objC.setAccessible(true);  
		            objQueue=(AbstractEventQueue)objC.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
					throw new BussinessException("EventFactory InstantiationException listenClassStr:"+listenClassStr+"   queueClassStr:"+queueClassStr);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new BussinessException("EventFactory IllegalAccessException listenClassStr:"+listenClassStr+"   queueClassStr:"+queueClassStr);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new BussinessException("EventFactory ClassNotFoundException listenClassStr:"+listenClassStr+"   queueClassStr:"+queueClassStr);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					throw new BussinessException("EventFactory IllegalArgumentException listenClassStr:"+listenClassStr+"   queueClassStr:"+queueClassStr);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					throw new BussinessException("EventFactory InvocationTargetException listenClassStr:"+listenClassStr+"   queueClassStr:"+queueClassStr);
				} catch (SecurityException e) {
					e.printStackTrace();
					throw new BussinessException("EventFactory SecurityException listenClassStr:"+listenClassStr+"   queueClassStr:"+queueClassStr);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					throw new BussinessException("EventFactory NoSuchMethodException listenClassStr:"+listenClassStr+"   queueClassStr:"+queueClassStr);
				}  
				// 注册事件监听器
				objQueue.registerListener(listenClassStr, objListen);
				if(!queueMap.containsKey(queueName)){
					queueMap.put(queueName,objQueue);
				}
			}
		}
	}

	public AbstractEventQueue getOhandEventQueue(String name){
		return queueMap.get(name);
	}
	 
}
