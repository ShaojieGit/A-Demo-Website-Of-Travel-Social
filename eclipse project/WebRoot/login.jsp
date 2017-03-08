
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录小秘</title>
<link rel="stylesheet" href="style/style.css">
<script type="text/javascript" src="js/inputborder.js"></script>
</head>

<body style="background-image:url(images/head.gif); background-repeat:no-repeat;">

<div class="container">
  <div class="header">
    <a href="index.jsp"><img src="logo/logo.png" alt="旅行小秘" name="logo" width="90" height="90" id="logo"/></a>
    <span class="user">
    <s:if test="#session.user==null">
    
    	<a href="login.jsp"><img src="user/user.png" alt="登录" name="user" width="40" height="40" /></a>
    
    </s:if>
    <s:else>
    
    		${sessionScope.user}
    		<a href="Zone.action?username=${sessionScope.user}"><img src="user/user.png" alt="" title="我的主页" name="user" width="40" height="40" /></a>
    		<a href="FriendAction.action"><img src="user/friend.png" alt="驴友动态" title="驴友动态" name="friendaction" width="40" height="40" /></a>
    		<a href="Quit.action"><img src="user/quit.png" title="退出"  width="40" height="40"/></a>
    	    
    </s:else>
    </span>
  </div><!-- end .header -->
  <hr />
  
  <div class="logincenter">
    <div class="login">
    <s:form action="Login">
      <br /><font color="red">${message}</font> <br />
      账号：<br /><s:textfield id="username" type="text" name="username" cssStyle="height:25px; width:200px; border:#999 1px solid; border-radius:3px;" placeholder="请输入登录账号" onfocus="focusin(id);" onblur="focusout(id)" onmouseover="overbordercolor(id);" onmouseout="outbordercolor(id);" /><br /><br />
 	  ${message1}    
      密码：<br /><s:password id="userpassw" type="password" name="password" placeholder="请输入登录密码" onfocus="focusin(id);" onblur="focusout(id)" onmouseover="overbordercolor(id);" onmouseout="outbordercolor(id);" /><br /><br />
      ${message2}
      <span class="loginbuttons">
      <a href="index.jsp"><input type="button" style="margin-right:59px" name="取消" value="取    消" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='2px 2px 5px #F80';" onmouseup="this.style.backgroundColor='#F90'; this.style.boxShadow='';"></a>
      <s:submit name="提交登录" value="登    录" onmousedown="this.cssStyle.backgroundColor='#F80'; this.cssStyle.boxShadow='2px 2px 5px #F80';" onmouseup="this.cssStyle.backgroundColor='#F90'; this.cssStyle.boxShadow='';"/>

      </span>
      <span class="goregister">
        <a href="regist.jsp">去注册 ></a>
      </span>
    </s:form>
    </div>
  </div>
  <div style="margin-bottom:190px;">
  </div>
  <br/><br/><br/><br/>
</div><!-- end .container -->
<div class="footer">
  <font size="-1"><p><br/>版本号：计算机1105 | 专利号：201132606163</p>
  <p >Copyright © 2014-   Gowithme.com 版权所有</p></font>
</div><!--end .footer -->

</body>
</html>