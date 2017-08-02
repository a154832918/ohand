package com.ohand.ohandFlow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandFlow.common.CacheUtil;
import com.ohand.ohandFlow.common.PFConstant;
import com.ohand.ohandFlow.common.Pagination;
import com.ohand.ohandFlow.domain.FlowCategoryDomain;
import com.ohand.ohandFlow.mapper.FlowCategoryMapper;
import com.ohand.ohandFlow.service.FlowCategoryService;
import com.ohand.ohandFlow.service.SequenceService;

@Service
public class FlowCategoryServiceImpl implements  FlowCategoryService {
	
	private static CacheUtil cacheUtil=CacheUtil.getInstance();
	
	@Autowired
	private FlowCategoryMapper flowCategoryMapper;
	
	@Autowired
	private SequenceService  sequenceService;
	
	public FlowCategoryDomain getObjectVOById(Integer id) {
		return flowCategoryMapper.getObjectVOById( id);
	}

	public List getObjectMapList(Map map) {
		return flowCategoryMapper
				.getObjectMapList( map);
	}

	public List getObjectVOList(Map map) {
		return flowCategoryMapper.getObjectVOList( map);
	}

	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = flowCategoryMapper.getObjectVOList(
				 map);
		List count = (flowCategoryMapper.getObjectMapListCount(
				 map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("NUMB"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	public Pagination getObjectVOListTreePage(Map map) {		 
		 int pageIndex=new Integer((String)map.get("pageIndex")).intValue();
		 int pageSize=new Integer((String) map.get("pageSize")).intValue();
		 List retList=flowCategoryMapper.getObjectVOListTreePagination(map);		 
		 List count=(flowCategoryMapper.getObjectVOListTreeCount(map));
		 int numb=((Integer)(count.get(0))).intValue();
		 return new Pagination(pageIndex,pageSize,numb,retList);
	}
	
	public FlowCategoryDomain insertObject(FlowCategoryDomain vo) {
		vo.setId(sequenceService.getNextId("pf_flow_category"));
		if (vo.getParentId() != null && vo.getParentId().intValue() == 0) {
			vo.setTopId(vo.getId());
			vo.setParentNodes(vo.getId()+",");
		} else {
			FlowCategoryDomain parentVO = getObjectVOById(vo.getParentId());
			vo.setTopId(parentVO.getTopId());
			vo.setParentNodes(parentVO.getParentNodes()+vo.getId()+",");
		}
		flowCategoryMapper.insertObject( vo);
		return vo;
	}

	public Integer updateObject(FlowCategoryDomain vo) {
		return flowCategoryMapper.updateObject( vo);
	}

	public Integer deleteObjectById(Integer i) {
		return flowCategoryMapper.deleteObjectById( i);
	}

	public List hasChild(Map map){
		return flowCategoryMapper.getObjectMapListCount(map);
	}
	
	public String id2Name(Integer id){
		Object obj=cacheUtil.get(PFConstant.CACHE_FLOW_CATEGROY_NAME);
		List list=null;
		Map map=new HashMap();
        map.put("enableFlag", PFConstant.DIRT_IS_YES);
		if(obj==null){
	        list= getObjectVOList(map);
	        obj=list;
	        cacheUtil.put(PFConstant.CACHE_FLOW_CATEGROY_NAME, obj);
		}
		List retList=(List) obj;
		if(retList!=null&&retList.size()>0){
			Integer parentId=-1;
			for(int j=0;j<retList.size();j++){
				FlowCategoryDomain vo=(FlowCategoryDomain) retList.get(j);
				if(id!=null&&id.intValue()==vo.getId()){
					return vo.getShowName();
				}
			}
			return "";
		}else{
			return "";
		}
	}
	
}