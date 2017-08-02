
package com.spring.model.VO;

import lombok.Data;

import java.util.List;

/**
 * Created by YangFan on 2016/11/28 上午10:53.
 * <p/>
 */
@Data
public class AuthTokenVO {
    /**
     *token
     */
    private String token;
    private Long userId;
    private List<String> resourceList;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<String> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}

}
