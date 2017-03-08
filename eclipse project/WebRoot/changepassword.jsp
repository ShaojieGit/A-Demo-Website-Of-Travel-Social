<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>设置密码</title>
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
  <div class="info">
	<div class="infodetail">
<s:actionerror/>
<s:form action="ChangePassword">
	原      密     码：<s:textfield name="oldpassword" label="原密码"/><font color="red">${message}</font><br/><br/>
	新      密     码：<s:textfield name="newpassword" label="新密码"/><br/><br/>
	确认密码：<s:textfield name="newpassword2" label="再次输入新密码"/><br/><br/>
	<s:submit value="确认修改"/>
</s:form>
</div>
</div>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
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