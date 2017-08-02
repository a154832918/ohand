package com.ohand.ohandFlow.script;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceFactory {
	
	private ApplicationContext xmlBeanFactory = null;
	
	private static ServiceFactory instance = null;
	
	public ServiceFactory(){
		String fileName = "/com/ohand/ohandFlow/script/applicationContext-script.xml";
		xmlBeanFactory= new ClassPathXmlApplicationContext(fileName);
	}

	public static ServiceFactory getInstance() {
		
		if(instance == null){
			
			instance = new  ServiceFactory();
		}
		return instance;
	}
	
	public Object getBeanByName(String name) {
		return (Object) xmlBeanFactory.getBean(name);
	}
	
	public ScriptService getScriptService() {
		return (ScriptService) xmlBeanFactory.getBean("scriptServiceProxy");
	}

 
	
}