<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ohand.ohandUser.common.PFConstant"%>
<%@ page import="com.ohand.ohandUser.common.DateUtil"%>
<%@ page import="com.ohand.ohandUser.domain.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Ohand办公</title>
<link href="<%=PFConstant.JAVASCRIPT_PATH%>bootstrap/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=PFConstant.JAVASCRIPT_PATH%>vue/dist/vue.js"></script>
<script src="<%=PFConstant.JAVASCRIPT_PATH%>bootstrap/bootstrap-3.2.0-dist/jquery-1.11.1.min.js"></script>
<script src="<%=PFConstant.JAVASCRIPT_PATH%>bootstrap/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
<script  type="text/JavaScript" src="<%=PFConstant.JAVASCRIPT_PATH%>ohand/ldCommonUtil.js"></script>
 
<script type="text/javascript">

</script>

<style type="text/css">
	.oaContainer{
		margin-right: 0px;
		margin-left: 0px;
		width: 100%;
	}
	
	.row{
		margin-right: 6px;
		margin-left: 0px;
	}
	
	.oaPortlet{
		height:350px;
	}
	.oaNav{
		background-color: #2dc3c1;
	}
	.oaCol{
		padding: 3px 3px 3px 3px;
	}
	
	
	
	/* 日程 */
.agenda {
	font-size: 11px;
}
.agenda .agendaOption {
	width: 80px;
	overflow: hidden;
	float: left;
	margin-right: 6px;
}
.agenda .calendar {
	text-align: center;
	border: 1px solid #DDD;
	padding: 1px;
	width: 76px;
}
.agenda .calendar .ym {
	height: 28px;
	line-height: 28px;
	background: #E20000;
	color: #FFF;
	font-weight: bold;
	font-size: 14px;
}
.agenda .calendar .week {
	color: #666;
	margin-bottom: 10px;
}
.agenda .calendar .day {
	font-size: 24px;
	font-weight: bold;
	line-height: 40px;
	color: #000;
}
.agenda .calendar .time {
	background: #DDD;
	line-height: 22px;
}
.agenda .daySelect {
	height: 22px;
	margin: 6px 0px;
}
.agenda .daySelect a {
	display: block;
	background: #75AFE7;
	float: left;
	height: 22px;
	line-height: 22px;
	color: #FFF;
	text-align: center;
}
.agenda .daySelect a:hover {
}
.agenda .daySelect a.preDay{
	width: 20px;
	margin-right: 1px;
}
.agenda .daySelect a.nextDay {
	width: 20px;
	margin-left: 1px;
}
.agenda .daySelect a.today {
	width: 38px;
}
.agenda  .partnerCalendar {
	margin: 6px 0px;
	display: block;
	padding-left: 25px;
	width: 55px;
	height: 22px;
	line-height: 22px;
	color: #FFF;
}

.agendaList {
}
.agendaList td {
	padding: 2px 0px;
	line-height: 140%;
}
.agendaList .agendaTime {
	width: 3em;
	vertical-align: top;
}
	
</style>
</head>
<body >
<div id="app" >
	<nav class="navbar navbar-default oaNav " style="margin-bottom:3px;" role="navigation">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">Ohand</a>
	    </div>
	    <ul class="nav navbar-nav navbar-right ">
	      <li><a style="color: red;" data-toggle="dropdown" data-hover="dropdown"  @click="toHome()"  title="首页"><span class="glyphicon glyphicon-home"></span> 首页</a></li>
	      <li>
	      		<a style="color: red;" id="openOhandQuick" data-toggle="dropdown" data-hover="dropdown"  href="#" title="快捷方式"><span class="glyphicon glyphicon-th"></span> 快捷方式</a>
	      		<div class="dropdown-menu" role="menu"  aria-labelledby="openOhandQuick">
						<div class="row" style="width:580px;">
								
								<%
								List resourceList=(List)request.getAttribute("resourceList");
								if(resourceList!=null && resourceList.size()>0){
									int p=0;
									for(int i=0;i<resourceList.size();i++){
										ResourceDomain r=(ResourceDomain)resourceList.get(i);
										if(p==0){
										%>	
										<div class="col-md-4 text-xs-center">
											<ul  class="nav">
										<%	
										}
										%>
									<li>
								       	<a ><i class="icon-user icon-white"></i>
								       		<span style="color: rgb(255, 140, 60);" class="<%=r.getMenuLogo()%>" @click="menuClick(<%=r.getId()%>)"><%=r.getName() %> </span>
								       	</a>
								   	</li>
								  		<%
								  		p++;
								  		if(p==4){
								  		%>
								  			</ul>
										</div>
								  		<%
								  			p=0;
								  		}
										%>
							<%
									}
								}
							%>
							<!-- 
							<div class="col-md-4 text-xs-center">
								<ul  class="nav">
							    	<li>
								       	<a ><i class="icon-user icon-white"></i>
								       		<span style="color: rgb(255, 140, 60);" class="glyphicon glyphicon-file" @click="menuClick()"> 新建公文</span>
								       	</a>
								   	</li>
								   	
								   	<li>
								       	<a><i class="icon-user icon-white"></i>
								       		<span style="color: rgb(255, 140, 60);" class="glyphicon glyphicon-plus" @click="menuClick()">个人日程</span>
								       	</a>
								     </li>
								     <li>
								       	<a><i class="icon-user icon-white"></i>
								      		<span style="color: rgb(255, 140, 60);" class="glyphicon glyphicon-th-list" @click="menuClick()" > 待办列表</span>
								       	</a>
								       </li>
							    </ul>
							</div>
							<div class="col-md-4 text-xs-center">
								<ul  class="nav">
							    			<li>
									        	<a ><i class="icon-user icon-white"></i>
									        		<span style="color: rgb(255, 140, 60);" class="glyphicon glyphicon-th-list" @click="menuClick()">已办列表</span>
									        	</a>
								        	</li>
								        	<li>
									        	<a><i class="icon-user icon-white"></i>
									        		<span  style="color: rgb(255, 140, 60);" class="glyphicon glyphicon-search" @click="menuClick()">公文查询</span>
									        	</a>
									        </li>
									        <li>
									        	<a><i class="icon-user icon-white"></i>
									        		<span  style="color: rgb(255, 140, 60);" class="glyphicon glyphicon-pencil" @click="menuClick()" >沟通交流</span>
									        	</a>
									        </li>
							      </ul>
							</div>
							<div class="col-md-4 text-xs-center">
								<ul  class="nav">
							    			<li>
									        	<a ><i class="icon-user icon-white"></i>
									        		<span style="color: rgb(255, 140, 60);"  class="glyphicon glyphicon-pencil" @click="menuClick()">信息发布</span>
									        	</a>
								        	</li>
								        	<li>
									        	<a><i class="icon-user icon-white"></i>
									        		<span style="color: rgb(255, 140, 60);"  class="glyphicon glyphicon-bell" @click="menuClick()">通知公告</span>
									        	</a>
									        </li>
									        <li>
									        	<a><i class="icon-user icon-white"></i>
									        		<span style="color: rgb(255, 140, 60);" class="glyphicon glyphicon-user" @click="menuClick()" >个人资料</span>
									        	</a>
									        </li>
							      </ul>
							</div>
							 -->	
						</div>
				</div>
	      </li>
	      <li>
	      	<a  style="color: red;" data-toggle="dropdown" data-hover="dropdown"   href="#" title="帮助文档"><span class="glyphicon glyphicon-question-sign"></span>帮助文档</a>
	      </li>
	      <li>
	      	<a style="color: red;" id="openPersonInfo" data-toggle="dropdown" data-hover="dropdown"  href="#" title="个人信息"><span class="glyphicon glyphicon-user"></span> 个人信息</a>
		      	<div class="dropdown-menu" role="menu"  aria-labelledby="openPersonInfo">
							<div class="row" style="width:400px;">
								<div class="col-sm-6 col-md-6">
							        <a href="#" class="thumbnail">
							            <img class="img-circle" src="/ohandUser/personImage/headerImage"
							                 alt="通用的占位符缩略图">
							        </a>
							    </div>
							    <div class="col-sm-6 col-md-6">
							    		<ul  class="nav">
							    			<li>
									        	<a ><i class="icon-user icon-white"></i>
									        		<span class="glyphicon glyphicon-user" @click="uploadImage()"> 上传个人头像</span>
									        	</a>
								        	</li>
								        	<li>
									        	<a><i class="icon-user icon-white"></i>
									        		<span class="glyphicon glyphicon-user" @click="alterPwd()"> 修改密码</span>
									        	</a>
									        </li>
									        <li>
									        	<a><i class="icon-user icon-white"></i>
									        		<span class="glyphicon glyphicon-user" @click="alterPersonInfo()" > 修改个人资料</span>
									        	</a>
									        </li>
							        	</ul>
							    </div>
							</div>
					</div>
	      </li>
	      <li>
	      	<a  style="color: red;" data-toggle="dropdown" data-hover="dropdown"   href="#" title="退出系统"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;&nbsp;&nbsp;
	      	</a>
	      </li>
	      <li>&nbsp;&nbsp;&nbsp;&nbsp;</li>	      
	    </ul>
	  </div>
	</nav>
	<div class="container-fluid oaContainer" >
	   <div id="homepage" style="height:100%;" class="row" >
	      <div class="col-xs-6 col-sm-3 oaCol " >
	         <div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">个人日程</h3>
				</div>
				<div class="panel-body  oaPortlet">
					<%
						String today=DateUtil.date2String(new Date(), "yyyy-MM-dd");
						String[] day=today.split("-");
						String time=DateUtil.date2String(new Date(), "HH:mm");
					%>
					<div class="pnlContent">
						<div class="agenda">
							<div class="agendaOption">
								<div class="calendar">
									<div class="ym"><%=day[0]+"-"+day[1] %></div>
									<div class="day"><%=day[2] %></div>
									<div class="week"><%=DateUtil.getWeek(new Date())%></div>
									<div class="time"><%=time %></div>
								</div>
								<div class="daySelect">
								<a href="javascript:void(0)"  class="preDay">&lt;</a>
								<a href="javascript:void(0)"  class="today">今天</a>
								<a href="javascript:void(0)"  class="nextDay">&gt;</a>
								</div>
								<span class="glyphicon glyphicon-user"><a href="#">伙伴日程</a></span>
							</div>
							<table border="0" cellspacing="0" cellpadding="0" id="agendaList" class="agendaList">
							</table>
						</div>
					</div>
				</div>
			</div>
	      </div>
	      <div class="col-xs-6 col-sm-6 oaCol " >
	        <div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title">
										待办事项(<span style="color: red">{{todoTotal}}</span>)
										<span style="float:right;">
												<h5>
												<a href="#">更多...</a></h5>
										</span>
					</h3>
				</div>
				<div class="panel-body   oaPortlet">
					<ul class="list-group"  >
					  <li   v-for="todo in todoData " class="list-group-item">	<span >{{todo.RECEIVER}}</span>
					  		<a  href="javascript:void"  @click="openWorkItem(todo.FLOW_INST_ID,todo.ID)"  >{{todo.TITLE}}</a> 
					  		<span >{{ new Date(todo.CREATE_DATE).toLocaleString()}}</span>
					  </li>
					</ul>
				</div>
			</div>
	      </div>
	      <div class="clearfix visible-xs"></div>
	      <div class="col-xs-6 col-sm-3 oaCol " >
				<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">快速查询</h3>
						</div>
						<div class="panel-body oaPortlet">
								<div >
					                <div class="input-group">
					                    <input type="text" class="form-control">
					                    <span class="input-group-btn">
					                        <button class="btn btn-default" type="button">公文搜索</button>
					                    </span>
					                </div>
					            </div>
						</div>
				</div>
		  </div>
	      <div class="col-xs-6 col-sm-6 oaCol  " >
	         <div class="panel panel-warning">
				<div class="panel-heading">
					<h3 class="panel-title">通知公告</h3>
				</div>
				<div class="panel-body oaPortlet">
					这是一个基本的面板4
				</div>
			</div>
	      </div>
	      <div class="col-xs-6 col-sm-3 oaCol " >
	         <div class="panel panel-danger">
				<div class="panel-heading">
					<h3 class="panel-title">规章制度</h3>
				</div>
				<div class="panel-body oaPortlet">
					这是一个基本的面板5
				</div>
			</div>
	      </div>
	     <div class="col-xs-6 col-sm-3 oaCol " >
	         <div class="panel panel-danger">
				<div class="panel-heading">
					<h3 class="panel-title">信息发布</h3>
				</div>
				<div class="panel-body oaPortlet">
					这是一个基本的面板5
				</div>
			</div>
	      </div> 
	   </div>
	   <div id="menupage" style="display:none;height:100%;"  class="row" >
	        <div  style="float:left;"  >
		        <div class="slimScrollDiv" style="width: auto; height: 100%; overflow: hidden; position: relative;">
				    <div class="sidebar-collapse" style="width: auto; height: 100%; overflow: hidden;">
			                	<input type="hidden" id="menupageIsOpen" value="1"/>
			                	<div style="margin: 10px;padding: 5px;"><a><span title="展开" class="sidebar-menus-icon glyphicon glyphicon-th-list" alt="展开"></span></a></div>
			                	
			                	<ul class="nav side-menu" id="side-menu" style="display: block;">
				                    
				                    <li title="活动BUG" alt="活动BUG"  >
				                        <a href="javascript:void(0)">
				                        	<table>
					                        	<tr>
					                        		<td>
					                        			<span  class="glyphicon glyphicon-th-list"  >活动BUG</span>
					                        		</td>
					                        		<td>
					                        			&nbsp;&nbsp;<span style="float:right;" title="隐藏" class="fa glyphicon glyphicon-chevron-left" alt="隐藏"></span>
					                        		</td>					                        		
					                        	</tr>
					                     	</table>
					                     	<div  style="display: none;" ><span class="menutip glyphicon glyphicon-th-list"  ></span></div>
					                     </a>
				                    </li>
				                    <li title="活动BUG" alt="活动BUG"  >
				                        <a href="javascript:void(0)">
				                        	<table>
					                        	<tr>
					                        		<td>
					                        			<span  class="glyphicon glyphicon-th-list"  >活动BUG</span>
					                        		</td>
					                        		<td>
					                        			&nbsp;&nbsp;<span style="float:right;" title="隐藏" class="fa glyphicon glyphicon-chevron-left" alt="隐藏"></span>
					                        		</td>					                        		
					                        	</tr>
					                     	</table>
					                     	<div style="display: none;"><span  class="menutip glyphicon glyphicon-th-list"  ></span></div>
					                     </a>
				                    </li>			                    
			                	</ul>
			       </div>
			       <div class="slimScrollBar" style="background: rgb(0, 0, 0); border-radius: 7px; left: 1px; top: 0px; width: 7px; height: 400.49px; display: none; position: absolute; z-index: 999; opacity: 0.4;"></div><div class="slimScrollRail" style="background: rgb(51, 51, 51); border-radius: 7px; left: 1px; top: 0px; width: 7px; height: 100%; display: none; position: absolute; z-index: 90; opacity: 0.9;"></div></div>
             </div>
	        <div id="myTabGuide" style="float:left;"  >
	      	</div>
    	</div>
	</div>
 </div>
 
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					编辑
				</h4>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary">
					提交更改
				</button>
			</div>
		</div>
	</div>
 </div>
 
 <script src="/ohandUser/website/homeClient.js"></script>
 
 <script type="text/javascript">

 </script>
 
 
 
 
 
</body>
</html>