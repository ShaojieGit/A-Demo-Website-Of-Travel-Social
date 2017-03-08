<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title><s:property value="#request.currentspotdetail.spotname"/></title>
<link rel="stylesheet" href="style/style.css">
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
  <div class="spotdetail">
<div class="wordpicture"> 
 <div class="spotword">
景点名称:<s:property value="%{#request.currentspotdetail.spotname}"/><br/>
景点地点：<s:property value="%{#request.currentspotdetail.position}"/><br/>
景点等级：<s:property value="%{#request.currentspotdetail.level}"/><br/>
联系电话：<s:property value="%{#request.currentspotdetail.phonenumber}"/><br/>
文字介绍：<s:property value="%{#request.currentspotdetail.spottext}"/><br/>
</div> 
<div class="collectbutton">
<s:if test="#session.user!=null">
	<s:if test="#request.currentspotdetail.collected==null">
	<s:form action="CollectSpot">
	             <s:hidden name="username" value="%{#session.user}"/>
	             <s:hidden name="spotname" value="%{#request.currentspotdetail.spotname}"/>
	             <s:submit value='收藏' />
	             
	</s:form>
	</s:if>
		
	<s:else>
	您已经收藏该景点
	</s:else>
</s:if>
</div>
<div class="spotpicture">
 <s:iterator value="#request.currentspotdetail.images" id="id_image">
 
 <img  width=440 height=440 src="SpotImage/<s:property value="#id_image"/>"/>
 </s:iterator>
 </div>

</div >
评价景点:
<div class="spotcomment">
<s:if test="#session.user!=null">
<s:form action="CommentSpot">
             <s:textarea cols="80" rows="1" name="commentcontent" />
             <s:hidden name="commentuser" value="%{#session.user}"/>
             <s:hidden name="spotname" value="%{#request.currentspotdetail.spotname}"/>
             <s:submit value='评价' />
</s:form>
</s:if>

</div>
</br>
               
<s:iterator value="#request.currentspotdetail.comments" id="id_comments">
	<img  width=50 height=50 src="HeadImage/<s:property value="#id_comments.headimagename"/>"/>
 	<s:property value="#id_comments.commentuser"/>
 	<s:property value="#id_comments.commenttime.substring(0,19)"/><br/>
 	<s:property value="#id_comments.commentcontent"/>
 	<s:if test="#id_comments.commentuser==#session.user">
        
        	<s:form action="DeleteSpotComment">
             <s:hidden name="commentuser" value="%{#id_comments.commentuser}"/>
             <s:hidden name="commenttime" value="%{#id_comments.commenttime} "/>
             <!-- 下面是为了记住删除前在查看哪个景点 -->
             <s:hidden name="spotname" value="%{#id_comments.spotname}"/>
             <s:submit value='删除' /></s:form>  
        
 </s:if>
 <hr/>
 <br/>
 </s:iterator>
 </div>
</div><!-- container end -->
<div class="footer">
  <font size="-1"><p><br/>版本号：计算机1105 | 专利号：201132606163</p>
  <p >Copyright © 2014-   Gowithme.com 版权所有</p></font>
</div><!--end .footer -->

</body>
</html>
</body>
</html>