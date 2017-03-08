<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
    <a href="home.jsp"><img src="logo/logo.png" alt="旅行小秘" name="logo" width="90" height="90" id="logo"/></a>
    <span class="user">
    <a href="login.jsp"><img src="user/user.png" alt="登录" title="登录注册" name="user" width="40" height="40"/></a>
    </span>
  </div><!-- end .header -->
  <hr />
  
  <div class="logincenter">
    <div class="login">
    <form onsubmit="checkblank();return(false);">
      <br /><br />
      账号：<br /><input type="email" id="username"  name="账号" placeholder="请输入登录账号" onfocus="focusin(id);" onblur="focusout(id)"; onmouseover="overbordercolor(id);" onmouseout="outbordercolor(id);" /><br /><br />
      密码：<br /><input id="userpassw" type="password" name="密码" placeholder="请输入登录密码" onfocus="focusin(id);" onblur="focusout(id)"; onmouseover="overbordercolor(id);" onmouseout="outbordercolor(id);" /><br /><br />
      <span class="loginbuttons">
      <a href="home.jsp"><input type="button" style="margin-right:59px" name="取消" value="取    消" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='2px 2px 5px #F80';" onmouseup="this.style.backgroundColor='#F90'; this.style.boxShadow='';"></a>
      <a href="friend_action.jsp"><input type="submit" name="提交登录" value="登    录" onclick="checkblank();return(false);" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='2px 2px 5px #F80';" onmouseup="this.style.backgroundColor='#F90'; this.style.boxShadow='';"></a>
      </span>
      <span class="goregister">
        <a href="register.jsp">去注册 ></a>
      </span>
    </form>
    </div>
  </div>
  <div style="margin-bottom:190px;">
  </div>
  
</div><!-- end .container -->
<div class="footer">
  <font size="-1"><p><br/>版本号：计算机1105 | 专利号：201132606163</p>
  <p >Copyright © 2014-   Gowithme.com 版权所有</p></font>
</div><!--end .footer -->

</body>
</html>