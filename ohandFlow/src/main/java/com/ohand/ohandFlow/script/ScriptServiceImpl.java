//package com.ohand.ohandFlow.script;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.mozilla.javascript.Context;
//import org.mozilla.javascript.ImporterTopLevel;
//import org.mozilla.javascript.NativeJavaClass;
//import org.mozilla.javascript.Script;
//import org.mozilla.javascript.ScriptRuntime;
//import org.mozilla.javascript.Scriptable;
//import org.mozilla.javascript.ScriptableObject;
//import org.mozilla.javascript.Wrapper;
//import org.springframework.util.ResourceUtils;
//
//import com.ohand.common.exception.BussinessException;
//import com.ohand.common.mapcollection.MapCollectionUtils;
//
///**
// * 
// * @author liud
// * 
// */
//public class ScriptServiceImpl implements ScriptService {
//
//	private static Log logger = LogFactory.getLog(ScriptServiceImpl.class);
//
//	public static Map map = new HashMap();
//
//	// private static Context scriptContext = Context.enter();
//
//	private ScriptClassLoader scriptClassLoader = null;
//
//	public static final String JAR_URL_SEPARATOR = "!/";
//
//	public static final String SCRIPT_CLASSPATH = "rhino.classPath";
//
//	private Context createContext() {
//		Context ctx = Context.enter();
//		if (scriptClassLoader != null) {
//			ctx.setApplicationClassLoader(scriptClassLoader);
//		}
//
//		return ctx;
//	}
//
//	private URL extractJarFileURL(URL jarUrl) throws MalformedURLException {
//		String urlFile = jarUrl.getFile();
//		int separatorIndex = urlFile.indexOf(JAR_URL_SEPARATOR);
//		if (separatorIndex != -1) {
//			String jarFile = urlFile.substring(0, separatorIndex);
//			try {
//				return new URL(jarFile);
//			} catch (MalformedURLException ex) {
//				if (!jarFile.startsWith("/")) {
//					jarFile = "/" + jarFile;
//				}
//				return new URL(ResourceUtils.FILE_URL_PREFIX + jarFile);
//			}
//		} else {
//			return jarUrl;
//		}
//	}
//
//	public void init() {
//
//	}
//
//	public String getScriptEngineVersion() {
//		Context scriptContext = createContext();
//		return scriptContext.getImplementationVersion();
//	}
//
//	public boolean validateScript(String scriptContent) throws BussinessException {
//		try {
//			Context scriptContext = createContext();
//			Script script = scriptContext.compileString(scriptContent, "test",
//					1, null);
//			return (script != null);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new BussinessException(e.getMessage());
//		}
//	}
//
//	public ScriptResult initScriptScope(String scriptContent, Map parameters)
//			throws BussinessException {
//
//		StringBuffer sb = new StringBuffer();
//
//		try {
//			List list = null;
//			if (list == null)
//				list = new ArrayList();
//
//			if (StringUtils.isNotBlank(scriptContent))
//				sb.append(scriptContent);
//
//			parameters.putAll(map);
//			return runScriptForInit(sb.toString(), parameters);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			logger.error("scriptContent:" + scriptContent);
//			throw new BussinessException(e);
//		}
//	}
//
//	private ScriptResult runScriptForInit(String initScript, Map parameters)
//			throws BussinessException {
//		ScriptResult scriptResult = null;
//		if (StringUtils.isEmpty(initScript))
//			initScript = "";
//
//		try {
//			Context scriptContext = createContext();
//
//			StringBuffer script = new StringBuffer();
//			scriptResult = new ScriptResult();
//			Scriptable scope = new ImporterTopLevel(scriptContext);
//			
//			//Scriptable scope =scriptContext.initStandardObjects();
//			
//			script.append(initScript);
//
//			Scriptable jsArgsOut = Context.toObject(System.out, scope);
//			scope.put("out", scope, jsArgsOut);
//
//			Scriptable jsResultOut = Context.toObject(scriptResult, scope);
//			scope.put("scriptResult", scope, jsResultOut);
//
//			Scriptable jsArgsErr = Context.toObject(System.err, scope);
//			scope.put("err", scope, jsArgsErr);
//
//			if (parameters != null && !MapCollectionUtils.isEmpty(parameters)) {
//				Iterator it = parameters.entrySet().iterator();
//				Map.Entry entry = null;
//				String key = null;
//				while (it.hasNext()) {
//					entry = (Map.Entry) it.next();
//					if (entry == null)
//						continue;
//					if (entry.getKey() == null)
//						continue;
//					if (entry.getValue() == null)
//						continue;
//
//					if (!(entry.getKey() instanceof String))
//						continue;
//					key = (String) entry.getKey();
//
//					scope.put(key, scope, entry.getValue());
//				}
//			}
//
//			scriptContext.evaluateString(scope, script.toString(),
//					"<Finishing script>", 1, null);
//			return getScriptResult(scope);
//		} catch (Exception e) {
//			throw new BussinessException(e);
//		} finally {
//			try {
//				Context.exit();
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//
//		}
//	}
//
//	private ScriptResult getScriptResult(Scriptable scope) {
//		if (scope == null)
//			return null;
//
//		ScriptResult scriptResult = null;
//
//		Object x = scope.get("scriptResult", scope);
//
//		if (x == Scriptable.NOT_FOUND) {
//			scriptResult = new ScriptResult();
//		} else {
//			scriptResult = (ScriptResult) Context.toType(x, ScriptResult.class);
//		}
//
//		scriptResult.addResult(ScriptResult.SCOPE_KEY, scope);
//		Object[] variables = scope.getIds();
//		for (int i = 0; i < variables.length; i++) {
//			if ("out".equals(variables[i]) || "err".equals(variables[i])
//					|| "scriptResult".equals(variables[i])) {
//				continue;
//			}
//			Object temp = scope.get(variables[i].toString(), scope);
//			if (temp instanceof NativeJavaClass) {
//				continue;
//			} else if (temp instanceof ScriptableObject) {
//				String className = ((ScriptableObject) temp).getClassName();
//				if ("String".equals(className)) {
//					scriptResult
//							.addResult(
//									variables[i].toString(),
//									((ScriptableObject) temp)
//											.getDefaultValue(ScriptRuntime.StringClass));
//				} else if ("Number".equals(className)) {
//					scriptResult
//							.addResult(
//									variables[i].toString(),
//									((ScriptableObject) temp)
//											.getDefaultValue(ScriptRuntime.NumberClass));
//				} else if ("Boolean".equals(className)) {
//					scriptResult
//							.addResult(
//									variables[i].toString(),
//									((ScriptableObject) temp)
//											.getDefaultValue(ScriptRuntime.BooleanClass));
//				} else if ("Date".equals(className)) {
//					scriptResult.addResult(variables[i].toString(),
//							Context.toType(temp, String.class));
//				} else if ("Array".equals(className)) {
//					scriptResult.addResult(variables[i].toString(),
//							Context.toType(temp, Object[].class));
//				}
//			} else if (temp instanceof Wrapper) {
//				scriptResult.addResult(variables[i].toString(),
//						((Wrapper) temp).unwrap());
//			} else {
//				scriptResult.addResult(variables[i].toString(), temp);
//			}
//		}
//
//		return scriptResult;
//	}
//
//	/**
//	 * @throws Exception
//	 */
//	public ScriptResult runScript(Object scope, Map parameters,
//			String scriptContent) throws BussinessException {
//		ScriptResult sr = null;
//		if (StringUtils.isEmpty(scriptContent))
//			return null;
//
//		Context cx = createContext();
//
//		try {
//			Scriptable scriptable = null;
//			if ((scope != null) && (scope instanceof Scriptable)) {
//				scriptable = (Scriptable) scope;
//			} else {
//				scriptable = new ImporterTopLevel(cx);
//			}
//
//			if (parameters != null && !MapCollectionUtils.isEmpty(parameters)) {
//				Iterator it = parameters.entrySet().iterator();
//				Map.Entry entry = null;
//				String key = null;
//				while (it.hasNext()) {
//					entry = (Map.Entry) it.next();
//					if (entry == null)
//						continue;
//					if (entry.getKey() == null)
//						continue;
//
//					if (!(entry.getKey() instanceof String))
//						continue;
//					key = (String) entry.getKey();
//
//					scriptable.put(key, scriptable, entry.getValue());
//				}
//			}
//
//			logger.debug("context>" + cx);
//
//			cx.evaluateString(scriptable, scriptContent, "<cmd>", 1, null);
//			sr = getScriptResult(scriptable);
//
//		} catch (Exception e) {
//			logger.error("\n" + scriptContent + "\n", e);
//			throw new BussinessException(e);
//		} finally {
//			Context.exit();
//		}
//		return sr;
//	}
//
//	public void initService(Map valueMap) {
//		map.putAll(valueMap);
//	}
//
//	public ScriptResult runScriptById(String scriptId) throws BussinessException {
//		return runScriptById(scriptId, null);
//	}
//
//	public ScriptResult runScriptById(String scriptId, Map parameters)
//			throws BussinessException {
//		return null;
//	}
//
//	public ScriptResult runScriptByName(String scriptName) throws BussinessException {
//		return this.runScriptByName(scriptName, null);
//	}
//
//	public ScriptResult runScriptByName(String scriptName, Map parameters)
//			throws BussinessException {
//		/*
//		 * if(StringUtils.isEmpty(scriptName)) return null;
//		 * 
//		 * Map paramterMap = new HashMap(); paramterMap.put("hanShuMingCheng",
//		 * scriptName);
//		 * 
//		 * List list =
//		 * noTransPersister.queryList("G_GongYongHanShu.selectByMap",
//		 * paramterMap, this.getContext());
//		 * 
//		 * if(list == null || list.isEmpty()) return null;
//		 * 
//		 * ScriptBO bo = (ScriptBO)list.iterator().next();
//		 * 
//		 * return this.runScriptForInit(bo, parameters);
//		 */
//		return null;
//	}
//
//	public ScriptResult runScriptByContent(String scriptContent)
//			throws BussinessException {
//		return runScriptByContent(scriptContent, null);
//	}
//
//	public ScriptResult runScriptByContent(String scriptContent, Map parameters)
//			throws BussinessException {
//		/*
//		 * if(StringUtils.isEmpty(scriptContent)) return null;
//		 * 
//		 * ScriptBO bo = new ScriptBO(); bo.setHanShuNeiRong(scriptContent);
//		 * return this.runScriptForInit(bo, parameters);
//		 */
//		return null;
//	}
//
//	public List getAllScripts() throws BussinessException {
//
//		return null;
//	}
//
//}
