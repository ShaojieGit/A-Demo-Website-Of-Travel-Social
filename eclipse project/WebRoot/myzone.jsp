

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
<s:if test="#session.user==#request.ThisUserDetail.username">
我的主页
</s:if>
<s:else>
<s:property value="#request.ThisUserDetail.username"/>的主页
</s:else>


</title>
<link rel="stylesheet" href="style/style.css">
<script type="text/javascript" src="js/clickchangepage_5.js"></script>
<script type="text/javascript" src="js/inputborder.js"></script>
<script type="text/javascript" src="js/showbigpicture.js"></script>
<script type="text/javascript" src="js/showshadow.js"></script>
<script type="text/javascript" src="js/closebigpicture.js"></script>
<script type="text/javascript" src="js/showpicturelightbox.js"></script>
<script type="text/javascript" src="jq/jquery-1.9.1.js"></script>
</head>

<body style="background-image:url(images/head.gif); background-repeat:no-repeat; background-color: #EEE;">

<div class="container" id="start">
  <div class="header">
    <a href="index.jsp"><img src="logo/logo.png" alt="旅行小秘" name="logo" width="90" height="90" id="logo"/></a>
    <span class="user">
    <s:if test="#session.user==null">
    
    	<a href="login.jsp"><img src="user/user.png" alt="登录" name="user" width="40" height="40" /></a>
    
    </s:if>
    <s:else>
    
    		${sessionScope.user}
    		<a href="Zone.action?username=<s:property value='#session.user'/>  "><img src="user/user.png" alt="" title="我的主页" name="user" width="40" height="40" /></a>
    		<a href="FriendAction.action"><img src="user/friend.png" alt="驴友动态" title="驴友动态" name="friendaction" width="40" height="40" /></a>
    		<a href="Quit.action"><img src="user/quit.png" title="退出"  width="40" height="40"/></a>
    	    
    </s:else>
    </span>
  </div><!-- end .header -->
  <hr />
  <div class="menu">
    <ul>
      <li><input type="button" id="btn1" name="btn1" value="动态" style="color:#F90; border-color:#F90;" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn2" name="btn2" value="关注" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn3" name="btn3" value="粉丝" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn4" name="btn4" value="资料" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn5" name="btn5" value="收藏" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
	
	
    <s:form action="SearchUser" cssClass="floatright">
      <input id="searchbutton" type="text" name="searchkey" placeholder="请输入您要搜索的关键词" onfocus="this.style.borderColor='#F90';" onblur="this.style.borderColor='#999';" />
      <input type="submit" name="search" value="搜索" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='1px 1px 3px #F80';" onmouseup="this.style.backgroundColor='#A8A8A8'; this.style.boxShadow='';" />
    </s:form>
  </div>
  <s:if test="#session.user!=null">   <!-- 已登录 -->
  <s:if test="#request.thisuser.username!=#session.user">   <!-- 不是在自己的空间 -->
  <s:if test="#request.thisuser.focused=='no'">
  
  		<s:form action="Focus">
             <s:hidden name="username" value="%{#request.thisuser.username}"/>
             <s:submit value='关注' cssStyle="height:35px; border-radius:4px; float:right;  margin-left:10px; cursor:pointer;" /></s:form>  

   </s:if>
  <s:else>
  		<s:form action="CancelFocus">
        <s:hidden name="username" value="%{#request.thisuser.username}"/>
        <s:submit value='取消关注' cssStyle="height:35px; border-radius:4px; float:right;  margin-left:10px; cursor:pointer;" /></s:form>  
  </s:else>
  </s:if>
  </s:if>
  <div id="idmain" class="main">
  
  
    <div id="page1" >
     
      <div class="publishaction">
      
        <s:form action="Publish" method="post" enctype="multipart/form-data">
  		     <s:textarea name="actiontext" cols="80" rows="5" />
  		     <s:file name="myFile" label="添加图片" cssStyle="float:left; margin-left:0;"/>
  		     <s:hidden name="towhere" value="zone"/>
			<s:submit value="发布" cssStyle="float:right;" />
       </s:form>
      </div>
      <br/><br/><br/>
      <s:iterator value="#request.myactions" id="id">
      <div class="oneaction">
        <div class="actionheader">
          <div class="headpicture" >
            <img src="HeadImage/<s:property value="#id.headimagename"/>"/>
          </div>
          <div class="actionusername">
            <s:property value="#id.username"/>
          </div>
          
			 <div class="actionshare">
			 <s:form action="Share">
             <s:hidden name="actiontext" value="%{#id.actiontext}"/>
             <s:hidden name="actionimage" value="%{#id.actionimage}"/>
             <s:hidden name="actionuser" value="%{#id.username}"/>
             <s:submit value='分享' /></s:form> 
             </div>
             <div class="actiondelete">
            <s:if test="#id.username==#session.user">
			     <s:form action="DeleteAction">
			     <s:hidden name="actionuser" value="%{#id.username}"/>
			     <s:hidden name="publishtime" value="%{#id.publishtime}"/>
			     <s:submit value='删除' onmouseover="this.style.color='#F00'; this.style.textShadow='1px 1px 2px #F00';" onmouseout="this.style.color='#F63'; this.style.textShadow='';"/></s:form>  
			 </s:if>
			 </div>
          <div class="actiontime">
            <s:property value="#id.publishtime.substring(0,19)"/>
          </div>
        </div><!-- actionheader end -->
        <div class="actionbody">
          <div class="actionword">
          <s:if test="#id.shared!=null&&#id.shared!=''">
          	分享自<s:property value="#id.shared"/><br/>
          	</s:if>
            <s:property value="#id.actiontext"/>
          </div>
          <div class="actionpicture">
            <s:if test="#id.actionimage!=null&&#id.actionimage!=''">
  			     <img src="ActionImage/<s:property value="#id.actionimage" />" />
  	        </s:if>
            
          </div>
          
          <div class="commenttable" >
          		<s:iterator value="#id.comments" id="commentid">

  								

  								<img style="width:40px;height:40px;" src="HeadImage/<s:property value="#commentid.headimagename"/>"/>
								<s:property value="#commentid.username"/>
								<s:property value="#commentid.commenttime.substring(0,19)"/><br/>
  								<s:if test="#commentid.repeat!=null&&#commentid.repeat!=''">
  								回复<s:property value="#commentid.repeat"/>:
  								</s:if>
  								<s:property value="#commentid.commentcontent"/>

  								<s:form action="ReplyComment">
						             <s:textarea cols="30" rows="1" name="commentcontent_reply"/>
						             <s:hidden name="username" value="%{#session.user}"/>
						             <s:hidden name="whoseaction" value="%{#id.username}"/>
						             <s:hidden name="reply" value="%{#commentid.username}"/>
						             <s:hidden name="publishtime" value="%{#id.publishtime}"/>  
						             <s:hidden name="whichpage" value="friendaction"/> 
						             <s:submit value='回复' cssStyle="margin-top:0;" />
             					</s:form>  
             					<br/>
    	  		</s:iterator>
    	  </div>
    	    
          <div class="commentaction">
            
            <s:form action="Comment">
             <s:textarea cols="80" rows="1" name='commentcontent' />
             <s:hidden name="username" value="%{#session.user}"/>
             <s:hidden name="whoseaction" value="%{#id.username}"/>
             <s:hidden name="publishtime" value="%{#id.publishtime}"/>
             <s:submit value='评论' />
             </s:form>
             
          </div>
        </div><!-- actionbody end -->
        <div class="grayline"></div>
      </div><!-- oneaction end -->
      
      </s:iterator>
    </div><!-- page1 end -->
    
    <div id="page2" >
      <div class="onefocus">
        <s:iterator value="#request.focus" id="id_focus">


  			<a href="Zone.action?username=${id_focus.username}"><img  width=50 height=50 src="HeadImage/<s:property value="#id_focus.headimagename"/>"/></a>

  			<a href="Zone.action?username=${id_focus.username}">用户名：<s:property value="#id_focus.username"/></a>
		
  		<s:if test="#session.user!=null">   <!-- 已登录 -->
        <s:if test="#id_focus.focused=='no'">

			<s:if test="#id_focus.username!=#session.user"><!-- 不能关注自己 -->
        	<s:form action="Focus">
             <s:hidden name="username" value="%{#id_focus.username}"/>
             <s:submit value='关注' cssStyle="" /></s:form>  
             </s:if>

        </s:if>
        <s:else>
        	<s:form action="CancelFocus">
             <s:hidden name="username" value="%{#id_focus.username}"/>
             <s:submit value='取消关注' /></s:form>  
        </s:else>
        </s:if>
        
		<hr /><br/>
  </s:iterator>
  </div><!-- onefocus end -->
    </div><!-- page2 end -->
    
    <div id="page3" >
      <div class="onefocus">
        <s:iterator value="#request.fans" id="id_fans">


  			<a href="Zone.action?username=${id_fans.username}"><img  width=50 height=50 src="HeadImage/<s:property value="#id_fans.headimagename"/>"/></a>

  			<a href="Zone.action?username=${id_fans.username}">用户名：<s:property value="#id_fans.username"/></a>
		
  		<s:if test="#session.user!=null">   <!-- 已登录 -->
			<s:if test="#id_fans.focused=='no'">
        	<s:if test="#id_fans.username!=#session.user"><!-- 不能关注自己 -->
		        	<s:form action="Focus">
		             <s:hidden name="username" value="%{#id_fans.username}"/>
		             <s:submit value='关注' /></s:form>  
             </s:if>
            </s:if>

        
             <s:if test="#id_fans.focusme=='yes'">
	        	 <s:form action="CancelFans">
	             <s:hidden name="username" value="%{#id_fans.username}"/>
	             <s:submit value='移除粉丝' /></s:form>  
             </s:if>
        </s:if>
        
		<hr /><br/>
  </s:iterator>
  </div><!-- onefocus end -->
    </div><!-- page3 end -->
    
    <div id="page4" class="info" >
   <div class="infodetail">
       
  <img  width=80 height=80 src="HeadImage/<s:property value="#request.ThisUserDetail.headimagename"/>"/></br>
 用户名：<s:property value="#request.ThisUserDetail.username"/></br> 
  邮箱：<s:property value="#request.ThisUserDetail.Email"/></br>
    性别：<s:property value="#request.ThisUserDetail.Sex"/></br>
        <s:if test="#request.ThisUserDetail.Usertype=='spotuser'||#request.ThisUserDetail.Usertype=='specialspotuser'">
        用户类型：景点用户</br>
        </s:if>
        <s:if test="#request.ThisUserDetail.Usertype=='spotuser'||#request.ThisUserDetail.Usertype=='specialspotuser'">
        所有景点:<s:property value="#request.ThisUserDetail.Spot"/><br/>
        </s:if>
  <s:if test="#request.ThisUserDetail.username==#session.user">
  <s:form action="Set">
             <s:hidden name="username" value="%{#request.ThisUserDetail.username}"/>
             <s:hidden name="headimagename" value="%{#request.ThisUserDetail.headimagename}"/>
             <s:hidden name="Email" value="%{#request.ThisUserDetail.Email}"/>
             <s:hidden name="Sex" value="%{#request.ThisUserDetail.Sex}"/>
             <s:hidden name="Usertype" value="%{#request.ThisUserDetail.Usertype}"/>
             <s:hidden name="Spot" value="%{#request.ThisUserDetail.Spot}"/>
             <s:submit value='设置' /></s:form>  
  
  </s:if>
  </div>
    </div><!-- page4 end -->
    <div id="page5" >
      <div class="collect">
          <s:iterator value="#request.CollectSpot" id="id_collectspot">

	
  			<a href="SpotInfo.action?spotname=${id_collectspot.spotname}"><img  width=400 height=400 src="SpotImage/<s:property value="#id_collectspot.image"/>"/></a>
			<a href="SpotInfo.action?spotname=${id_collectspot.spotname}">景点名称:<s:property value="#id_collectspot.spotname"/></a>
			<hr/>
			
  </s:iterator>
  
      </div>
    </div><!-- page5 end -->
    
  </div><!-- main end -->

 <br/>  <br/> <br/> <br/> <br/> <br/><br/> <br/> <br/><br/> <br/> <br/> <br/> <br/><br/> <br/> <br/>  <br/><br/><br/> <br/> <br/>  <br/><br/>
</div><!-- container end -->
<!-- 回到顶部
<div class="top">
    <a href="#start"><input value="Top" type="button" /></a>
</div> 
 -->

<div class="footer">
  <font size="-1"><p><br/>版本号：计算机1105 | 专利号：201132606163</p>
  <p >Copyright © 2014-   Gowithme.com 版权所有</p></font>
</div><!--end .footer -->

</body>
</html>
