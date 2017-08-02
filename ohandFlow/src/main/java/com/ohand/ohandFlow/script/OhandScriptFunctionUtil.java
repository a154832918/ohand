package com.ohand.ohandFlow.script;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.OhandFileUtil;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-12-10
 * @Function:
 */
public class OhandScriptFunctionUtil {
	
	//TODO 改变修饰 public protected
	protected OhandScriptFunctionUtil(){
		
	}

	public static ScriptResult execute(String activityId, String jsType,
			Map parameter) throws BussinessException, IOException {

		String body = OhandFileUtil.readScriptFile(activityId,activityId, jsType,
				true);

		if (StringUtils.isEmpty(body))
			return null;

		ScriptService ss = ServiceFactory.getInstance().getScriptService();

		StringBuffer sb = new StringBuffer();
		sb.append(" importPackage(Packages.java.lang); ");
		sb.append(" importPackage(Packages.java.util); ");

		sb.append(body);

		ScriptResult sr = ss.initScriptScope(sb.toString(), parameter);

		return sr;
	}

	public static ScriptResult executeWithScriptPath(String scriptPath,
			Map parameter) throws BussinessException, IOException {
		
		String body = OhandFileUtil.readFile(scriptPath,true);
		
		if (StringUtils.isEmpty(body))
			return null;

		ScriptService ss = ServiceFactory.getInstance().getScriptService();

		StringBuffer sb = new StringBuffer();
		sb.append(" importPackage(Packages.java.lang); ");
		sb.append(" importPackage(Packages.java.util); ");

		sb.append(body);

		ScriptResult sr = ss.initScriptScope(sb.toString(), parameter);

		return sr;
	}
	
	public static ScriptResult runScript(String body, Map parameter)
			throws BussinessException {

		if (StringUtils.isEmpty(body))
			return null;

		ScriptService ss = ServiceFactory.getInstance().getScriptService();

		StringBuffer sb = new StringBuffer();
		sb.append(" importPackage(Packages.java.lang); ");
		sb.append(" importPackage(Packages.java.util); ");

		sb.append(body);
		
		ScriptResult sr = ss.initScriptScope(sb.toString(), parameter);

		return sr;
	}
}
