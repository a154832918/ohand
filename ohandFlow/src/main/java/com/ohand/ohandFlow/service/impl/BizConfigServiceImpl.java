package com.ohand.ohandFlow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.PFConstant;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.BizConfigDomain;
import com.ohand.ohandFlow.mapper.BizConfigMapper;
import com.ohand.ohandFlow.service.BizConfigService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class BizConfigServiceImpl  implements  BizConfigService  {

	@Autowired
	private BizConfigMapper bizConfigMapper;
	@Autowired
	private SequenceService  sequenceService;	
	
	public BizConfigDomain getObjectVOById(Integer id) {
		return bizConfigMapper.getObjectVOById(id);
	}
	
	public List getObjectMapList(Map map) {
		return bizConfigMapper.getObjectMapList(map);
	}
	
	public List getTables(Map map) {
		return bizConfigMapper.getTables(map);
	}
	
	@Transactional
	public void doPublish(BizConfigDomain vo) {
		String flowCode=vo.getFlowCode()+"";
		Map map=new HashMap();
			map.put("flowCode", flowCode);
		List retList=bizConfigMapper.getObjectVOList(map);
		if(retList!=null&&retList.size()>0){
			for(int i=0;i<retList.size();i++){
				BizConfigDomain tempVO=(BizConfigDomain) retList.get(i);
				tempVO.setPublishVersion(vo.getId());
				bizConfigMapper.updateObject(tempVO);
			}
		}
		vo.setPublishVersion(vo.getId());
		bizConfigMapper.updateObject(vo);
	}
	
	/*
	 *  1、判断flowId与TableName一致否...
	 *  2、更新isLast字段
	 */
	@Transactional
	public void bindingTable(BizConfigDomain vo) {
		BizConfigDomain dbVO=new BizConfigDomain();
		boolean isSame=true;
		Map flowMap=new HashMap();
			flowMap.put("flowCode", vo.getFlowCode());
		List retList=bizConfigMapper.getObjectVOList(flowMap);
		if(retList!=null&&retList.size()>0){
			dbVO=(BizConfigDomain) retList.get(0);
			String voTableName=vo.getTableName();
			String dbTableName=dbVO.getTableName();
			if(voTableName!=null&&dbTableName!=null&&!dbTableName.equals(voTableName)){
				isSame=false;
			}
			for(int i=0;i<retList.size();i++){
				BizConfigDomain	tempVO=(BizConfigDomain) retList.get(i);
				tempVO.setIsLast(new Integer(PFConstant.DIRT_IS_NO));
				bizConfigMapper.updateObject( tempVO);
			}
		}
		if(!isSame){
			throw new BussinessException("bpm配置,flowId与TableName不一致...");
		}
		vo.setId(sequenceService.getNextId("pf_biz_config"));
		vo.setPublishVersion(dbVO.getPublishVersion());
		bizConfigMapper.insertObject(vo);
	}
	
	public List getObjectVOList(Map map) {
		return bizConfigMapper.getObjectVOList(map);
	}
	
	public Pagination getObjectVOListPage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=bizConfigMapper.getObjectMapListPagination(map);		 
		 List count=(bizConfigMapper.getObjectMapListCount(map));
			int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
			return new Pagination(pageIndex, pageSize, numb, retList);
	}
	
	public BizConfigDomain insertObject(BizConfigDomain vo) {
		vo.setId(sequenceService.getNextId("pf_biz_config"));
		bizConfigMapper.insertObject(vo);
		return vo;
	}

	public Integer updateObject(BizConfigDomain vo) {
		return bizConfigMapper.updateObject(vo);
	}
	
	public Integer deleteObjectById(Integer i) {
		return bizConfigMapper.deleteObjectById(i);
	}
	
}