<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>领取小秘</title>
<link rel="stylesheet" href="style/style.css">
<script type="text/javascript" src="js/inputborder.js"></script>
<script type="text/javascript" src="js/checkcin.js"></script>

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
    <div class="login"> <!-- onload="init();"-->
    <s:form action="Regist" name="form1" method="post" enctype="multipart/form-data" onsubmit="return checkregister();" >
      <div class="error">
      <p id="usernameerror" style="font-size:12px; color:#f90;">请输入20位以内用户名！</p>
      <p id="userpasswerror" style="font-size:12px; color:#f90;">请输入20位以内密码！</p>
      <p id="userpasswagainerror" style="font-size:12px; color:#f90;">两次密码不匹配！</p>
      </div>
      <p style="font-size:12px; color:#f90;">注：带*为必填项</p>
      *账号：<br /><s:textfield name="username" type="text" id="username" value="" placeholder="请输入账号" cssStyle="height:25px; width:200px; border:#999 1px solid; border-radius:3px;" onfocus="focusin(id);" onblur="focusout(id)" onmouseover="overbordercolor(id);" onmouseout="outbordercolor(id);" /><font color="red">${message}</font><br /><br />
      *密码：<br /><s:password name="password" id="userpassw" type="password" placeholder="请输入密码" onfocus="focusin(id);" onblur="focusout(id)" onmouseover="overbordercolor(id);" onmouseout="outbordercolor(id);" /><br /><br />
      确认密码：<br /><input id="userpasswagain" type="password" name="确认密码" placeholder="请再次输入密码" onfocus="focusin(id);" onblur="focusout(id)" onmouseover="overbordercolor(id);" onmouseout="outbordercolor(id);" /><br /><br />
      邮箱：<br /><s:textfield name="email" id="mailbox" type="email" placeholder="请输入邮箱" onfocus="focusin(id);" onblur="focusout(id)" onmouseover="overbordercolor(id);" onmouseout="outbordercolor(id);" /><br /><br />
      头像：<s:file name="myFile" id="headimage" type="file" /><br /><br />
      性别：  <s:radio list="{'男','女','其他'}" name="sex" /><br /><br />
      <span class="loginbuttons">
      <a href="index.jsp"><input type="button" style="margin-right:59px" name="取消" value="取    消" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='2px 2px 5px #F80';" onmouseup="this.style.backgroundColor='#F90'; this.style.boxShadow='';"></a>
      <s:submit name="提交注册" value="注    册" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='2px 2px 5px #F80';" onmouseup="this.style.backgroundColor='#F90'; this.style.boxShadow='';"/>
      </span>
      <span class="goregister">
        <a href="login.jsp">去登录 ></a>
      </span>
    </s:form>
    </div>
  </div>
  <div style="margin-bottom:30px;">
  </div>
  
</div><!-- end .container -->
<div class="footer">
  <font size="-1"><p><br/>版本号：计算机1105 | 专利号：201132606163</p>
  <p >Copyright © 2014-   Gowithme.com 版权所有</p></font>
</div><!--end .footer -->

</body>
</html>
