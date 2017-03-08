<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>旅行小秘</title>
<link rel="stylesheet" href="style/style.css">
<script type="text/javascript" src="js/clickchangepage.js"></script>
</head>

<body style="background-image:url(images/head.gif); background-repeat:no-repeat;">

<div class="container" id="start">
  <div class="header">
    <a href="home.jsp"><img src="logo/logo.png" alt="旅行小秘" name="logo" width="90" height="90" id="logo"/></a>
    <span class="user">
    <a href="login.jsp"><img src="user/user.png" alt="登录" name="user" width="40" height="40" /></a>
    </span>
  </div><!-- end .header -->
  <hr />
  <div class="menu">
    <ul>
      <li><input type="button" id="btn1" name="btn1" value="热门景点" style="color:#F90; border-color:#F90;" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn2" name="btn2" value="出行必备" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn3" name="btn3" value="暴走驴友" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn4" name="btn4" value="美图欣赏" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
    </ul>
    <form class="floatright">
      <input id="searchbutton" type="text" name="搜索" placeholder="请输入您要搜索的关键词" onfocus="this.style.borderColor='#F90';" onblur="this.style.borderColor='#999';" />
      <input type="submit" name="提交搜索" value="搜索" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='1px 1px 3px #F80';" onmouseup="this.style.backgroundColor='#A8A8A8'; this.style.boxShadow='';" />
    </form>
  </div>
  <div id="idcontent" class="content">
  <p>
  <div id="page1" class="content1">
    <img src="images/example1.jpg" />
    <img src="images/example2.jpg" />
    <img src="images/example3.jpg" />
    <img src="images/example2.jpg" />
  </div>
  <div id="page2" class="content2" >
    <img src="images/example1.jpg" />
    <img src="images/example1.jpg" />
    <img src="images/example1.jpg" />
    <img src="images/example2.jpg" />
  </div>
  <div id="page3" class="content3">
    <img src="images/example2.jpg" />
    <img src="images/example2.jpg" />
    <img src="images/example2.jpg" />
    <img src="images/example2.jpg" />
  </div>
  <div id="page4" class="content4">
    <img src="images/example3.jpg" />
    <img src="images/example3.jpg" />
    <img src="images/example3.jpg" />
    <img src="images/example2.jpg" />
  </div>
  </p>
  </div><!-- content end -->
  <hr />
  <div class="floatright">
    <input id="more" type="button" name="更多" value="更多" style="background:none; border:none;" onclick="changemore();" />
  </div>

  
</div><!-- container end -->
<!-- 回到顶部
<div class="top">
    <a href="#start"><input value="Top" type="button" /></a>
</div> 
 -->
  <br/><br/><br/><br/><br/>

<div class="footer">
  <font size="-1"><p><br/>版本号：计算机1105 | 专利号：201132606163</p>
  <p >Copyright © 2014-   Gowithme.com 版权所有</p></font>
</div><!--end .footer -->

</body>
</html>