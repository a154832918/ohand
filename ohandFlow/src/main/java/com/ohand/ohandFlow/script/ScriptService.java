package com.ohand.ohandFlow.script;

import java.util.List;
import java.util.Map;

import com.ohand.common.exception.BussinessException;

public interface ScriptService {

	public String getScriptEngineVersion();
	
	public void initService(Map valueMap);

	public ScriptResult runScriptById(String scriptId) throws BussinessException;	

	public ScriptResult runScriptById(String scriptId, Map parameters) throws BussinessException;

	public List getAllScripts() throws BussinessException;

	public ScriptResult runScriptByName(String scriptName) throws BussinessException;

	public ScriptResult runScriptByName(String scriptName, Map parameters)
		throws BussinessException;

	public ScriptResult runScriptByContent(String scriptContent) throws BussinessException;

	public ScriptResult runScriptByContent(String scriptContent, Map parameters) throws BussinessException;
	
	public boolean validateScript(String scriptContent)throws BussinessException;
 
	public ScriptResult runScript(Object scope, Map parameters, String scriptContent) throws BussinessException;
	
	public ScriptResult initScriptScope(String scriptContent, Map parameters) throws BussinessException;
}
