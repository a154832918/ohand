package com.ohand.ohandFlow.script;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.domain.FlowInstDomain;
import com.ohand.ohandFlow.domain.FlowWorkItemDomain;
import com.ohand.ohandFlow.domain.UserInfo;
import com.ohand.ohandFlow.domain.fsm.StateMachineAdmin;
import com.ohand.ohandFlow.domain.fsm.StateMachineProcessor;

/*
 * for outter
 */
public class ScriptFacade {
	
	private static StateMachineProcessor stateMachineProcessor;
	
	public static void execute(String workItemId) throws BussinessException, IOException {
		System.out.println("--------------this is the begin of script--------------");
		OhandScriptFunctionUtil util=new OhandScriptFunctionUtil();
		util.execute("", "", new HashMap());
		System.out.println("--------------this is the end of script--------------");
	}

	public static Map executeWithScriptPath(String scriptPath,String scriptType,UserInfo user,FlowWorkItemDomain flowWorkItemDomain,FlowInstDomain flowInstDomain) throws BussinessException, IOException {
		System.out.println("--------------this is the begin of script--------------");
		OhandScriptFunctionUtil util=new OhandScriptFunctionUtil();
		StateMachineAdmin stateMachineAdmin=StateMachineAdmin.getInstance();
		Map retMap=new HashMap();
		Map map=new HashMap();
			map.put("userInfo",user);
			map.put("flowInstDomain",flowInstDomain);
			map.put("flowWorkItemDomain",flowWorkItemDomain);
			map.put("stateMachineProcessor",stateMachineProcessor);
			map.put("stateMachineAdmin",stateMachineAdmin);
			map.put("retMap", retMap);
		ScriptResult result =  util.executeWithScriptPath(scriptPath, map);
		if(result!=null){
			retMap = (Map) result.getResultByKey("retMap");		  
		}
		System.out.println("--------------this is the end of script--------------");
		return retMap;
		
	}
	
	
}
