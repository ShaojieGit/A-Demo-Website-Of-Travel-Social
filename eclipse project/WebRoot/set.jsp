<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>设置</title>
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
  <div class="menu">
    <s:form action="SearchUser" cssClass="floatright">
      <input id="searchbutton" type="text" name="searchkey" placeholder="请输入您要搜索的关键词" onfocus="this.style.borderColor='#F90';" onblur="this.style.borderColor='#999';" />
      <input type="submit" name="search" value="搜索" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='1px 1px 3px #F80';" onmouseup="this.style.backgroundColor='#A8A8A8'; this.style.boxShadow='';" />
    </s:form>
  </div>
  <div class="commonspotuser">
	<s:if test="#session.usertype=='commonuser'">
		
		<s:form action="SpotUser">
	    	<s:submit value='成为景点用户' />
	    </s:form>  
	</s:if>
	<s:if test="#session.usertype=='spotuser'">
	

	    <s:form action="ManageSpot">
	    	<s:submit value='管理景点信息' />
	    </s:form>
	</s:if>
	<br/>
	<a href="changepassword.jsp"><input type="button" value="修  改  密  码 "></a>
	</div>
	<div class="info">
	<div class="infodetail">
 修改个人信息:<br/>
<s:form action="ChangeUserInfo" method="post" enctype="multipart/form-data">
	   				<s:hidden name="Usertype" value="%{#request.UserDetailToSet.Usertype}"/>
	   				<s:hidden name="Spot" value="%{#request.UserDetailToSet.Spot}"/>
	   				用户名：<s:textfield name="username" label="用户名" value="%{#request.UserDetailToSet.username}" cols="80" rows="10"/><font color="red">${message}</font><br/><br/>
	   				邮        箱：<s:textfield name="Email" label="邮箱" value="%{#request.UserDetailToSet.Email}" cols="80" rows="10"/><br/><br/>
	   				性        别：<s:radio name="Sex" list="{'男','女','其他'}" label="性别" value="%{#request.UserDetailToSet.Sex}" /><br/><br/>
  
	   				
	   				<s:file name="myFile" label="上传新头像"/><br/><br/>
	   				<s:submit value="保存修改" />
</s:form>
</div>
</div>
<br/><br/><br/><br/><br/><br/>
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