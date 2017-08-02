package com.ohand.ohandUser.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ohand.ohandUser.common.PFConstant;
import com.ohand.ohandUser.common.Pagination;
import com.ohand.ohandUser.domain.ResourceDomain;
import com.ohand.ohandUser.domain.tree.ResourceTreeGridVO;
import com.ohand.ohandUser.mapper.ResourceMapper;
import com.ohand.ohandUser.service.ResourceService;
import com.ohand.ohandUser.service.SequenceService;
import com.ohand.ohandUser.service.UserService;
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private  SequenceService  sequenceService;
	
	@Override
	public ResourceDomain getObjectVOById(Integer id) {
		return resourceMapper.getObjectVOById(id);
	}

	@Override
	public List getObjectMapList(Map map) {
		return resourceMapper.getObjectMapList(map);
	}

	@Override
	public List getObjectVOList(Map map) {
		return resourceMapper.getObjectVOList(map);
	}

	@Override
	public Pagination getObjectVOListPage(Map map) {
		int pageIndex = new Integer((String) map.get("pageIndex")).intValue();
		int pageSize = new Integer((String) map.get("pageSize")).intValue();
		List retList = resourceMapper.getObjectMapListPagination(map);
		List count = (resourceMapper.getObjectMapListCount(map));
		int numb = new Integer(""+ ((Map) (count.get(0))).get("numb"));
		return new Pagination(pageIndex, pageSize, numb, retList);
	}

	@Override
	public ResourceDomain insertObject(ResourceDomain vo) {
		vo.setId(sequenceService.getNextId("pf_resource"));
		Integer topId=vo.getTopId();
		if(topId==null ||(topId!=null&&topId.intValue()<0) ){
			vo.setTopId(vo.getId());
			vo.setParentNodes(vo.getId()+",");
		}else{
			ResourceDomain domain=getObjectVOById(vo.getParentId());
			vo.setParentNodes(domain.getParentNodes()+vo.getId()+",");
		}
		if(vo.getSysCode()==null){
			vo.setSysCode("oa");
		}
		resourceMapper.insertObject( vo);
		return vo;
	}

	@Override
	public Integer updateObject(ResourceDomain vo) {
		if(vo.getSysCode()==null){
			vo.setSysCode("oa");
		}
		return resourceMapper.updateObject( vo);
	}

	@Override
	public Integer deleteObjectById(Integer i) {
		return resourceMapper.deleteObjectById(i);
		}

	@Override
	public List fetchSortedMenuByTopId(String topId) {
		List retList=new ArrayList();
		Map map =new HashMap();
			map.put("topId", topId);
			map.put("enableFlag", PFConstant.DIRT_IS_YES);
		List list=resourceMapper.getObjectVOList(map);
		if(list!=null&& list.size()>0){
			for(int i=0;i<list.size();i++){//第二级栏目
				ResourceTreeGridVO vo2=(ResourceTreeGridVO) list.get(i);
				if(vo2.getParentId()!=null && (vo2.getParentId()+"").equals(topId)){
					List childList=new ArrayList();
					for(int j=0;j<list.size();j++){//第三级栏目
						ResourceTreeGridVO vo3=(ResourceTreeGridVO) list.get(j);
						if(vo3.getParentId()!=null && !(vo3.getParentId()).equals("")&&vo3.getParentId().intValue()==vo2.getId().intValue()){
							childList.add(vo3);
						}
					}
					vo2.setChildren(childList);
					retList.add(vo2);
				}
			}
		}
		return retList;
	}

}
