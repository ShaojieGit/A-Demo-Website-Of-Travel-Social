
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
      <li><input type="button" id="btn1" name="btn1" value="热门景点" style="color:#F90; border-color:#F90;" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn2" name="btn2" value="出行必备" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn3" name="btn3" value="暴走驴友" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn4" name="btn4" value="美图欣赏" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
    </ul>
    <s:form action="SearchUser" cssClass="floatright">
      <input id="searchbutton" type="text" name="searchkey" placeholder="请输入您要搜索的关键词" onfocus="this.style.borderColor='#F90';" onblur="this.style.borderColor='#999';" />
      <input type="submit" name="search" value="搜索" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='1px 1px 3px #F80';" onmouseup="this.style.backgroundColor='#A8A8A8'; this.style.boxShadow='';" />
    </s:form>
  </div>
  <div id="idcontent" class="content">
  <p>
  <div id="page1" class="content1">
    <a href="SpotInfo.action?spotname=南湖公园"><img src="images/01.jpg" /></a>
    <a href="SpotInfo.action?spotname=北陵公园"><img src="images/02.jpg" /></a>
    <a href="SpotInfo.action?spotname=棋盘山"><img src="images/03.jpg" /></a>
    <img src="images/04.jpg" />
    <img src="images/05.jpg" />
    <img src="images/06.jpg" />
    <img src="images/07.jpg" />
    <img src="images/08.jpg" />
    <img src="images/09.jpg" />
    <img src="images/10.jpg" />
    <img src="images/11.jpg" />
  </div>
  <div id="page2" class="content2" >
    <a href="http://detail.tmall.com/item.htm?spm=a230r.1.14.110.UkTHTb&id=19390716082"><img src="images/001.jpg" /></a>
    <a href="http://detail.tmall.com/item.htm?spm=a230r.1.14.1.lK2ga2&id=38799975361&ad_id=&am_id=&cm_id=140105335569ed55e27b&pm_id="></a><img src="images/002.jpg" /></a>
    <a href="http://item.taobao.com/item.htm?spm=a230r.1.14.35.oQQiHl&id=39100393720&ns=1#detail"></a><img src="images/003.jpg" /></a>
    <img src="images/10.jpg" />
    <img src="images/11.jpg" />
    <img src="images/12.jpg" />
  </div>
  <div id="page3" class="content3">
    <a href="Zone.action?username=张少杰"><img src="images/01.jpg" /></a>
    <a href="Zone.action?username=夏雨"><img src="images/02.jpg" /></a>
    <a href="Zone.action?username=徐维"><img src="images/03.jpg" /></a>
    <img src="images/04.jpg" />
    <img src="images/05.jpg" />
    <img src="images/06.jpg" />
  </div>
  <div id="page4" class="content4">
    <img src="images/12.jpg" />
    <img src="images/13.jpg" />
    <img src="images/14.jpg" />
    <img src="images/15.jpg" />
    <img src="images/16.jpg" />
    <img src="images/17.jpg" />
    <img src="images/18.jpg" />
    <img src="images/19.jpg" />
    <img src="images/20.jpg" />
    <img src="images/21.jpg" />
    <img src="images/22.jpg" />
    <img src="images/23.jpg" />
  </div>
  </p>
  </div><!-- content end -->
  <hr />
  <div class="floatright">

    <a href="ShowSpots.action"><input  type="button" name="搜索" value="搜索" style="background:none; border:none; cursor:pointer;" /></a>
  	<input id="more" type="button" name="更多" value="更多" style="background:none; border:none;" onclick="changemore();" />
  </div>

</div><!-- container end -->
<!-- 回到顶部
<div class="top">
    <a href="#start"><input value="Top" type="button" /></a>
</div> 
 -->
  <br/><br/><br/><br/><br/><br/><br/><br/>

<div class="footer">
  <font size="-1"><p><br/>版本号：计算机1105 | 专利号：201132606163</p>
  <p >Copyright © 2014-   Gowithme.com 版权所有</p></font>
</div><!--end .footer -->

</body>
  

</html>
