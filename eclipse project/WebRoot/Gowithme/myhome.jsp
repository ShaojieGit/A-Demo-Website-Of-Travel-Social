<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的主页</title>
<link rel="stylesheet" href="style/style.css">
<script type="text/javascript" src="js/clickchangepage_7.js"></script>
<script type="text/javascript" src="js/inputborder.js"></script>
<script type="text/javascript" src="js/showbigpicture.js"></script>
<script type="text/javascript" src="js/showshadow.js"></script>
<script type="text/javascript" src="js/closebigpicture.js"></script>
</head>

<body style="background-image:url(images/head.gif); background-repeat:no-repeat;">

<div class="container" id="start">
  <div class="header">
    <a href="home.jsp"><img src="logo/logo.png" alt="旅行小秘" name="logo" width="90" height="90" id="logo"/></a>
    <span class="user">
    <a href="friendaction.jsp"><img src="user/friend.png" alt="驴友动态" title="驴友动态" name="friendaction" width="40" height="40" /></a>
    <a href="myhome.jsp"><img src="user/user.png" alt="我的主页" title="我的主页" name="myhome" width="40" height="40" /></a>
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
      <li><input type="button" id="btn6" name="btn6" value="消息" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
      <li><input type="button" id="btn7" name="btn7" value="留言板" onClick="changemenu(id);" onmouseover="overcolor(id);" onmouseout="outcolor(id);" /></li>
    <form class="floatright">
      <input type="text" name="搜索" placeholder="请输入您要搜索的关键词" onfocus="this.style.borderColor='#F90';" onblur="this.style.borderColor='#999';" />
      <input type="submit" name="提交搜索" value="搜索" onmousedown="this.style.backgroundColor='#F80'; this.style.boxShadow='1px 1px 3px #F80';" onmouseup="this.style.backgroundColor='#A8A8A8'; this.style.boxShadow='';" />
    </form>
  </div>
  <div id="idmain" class="main">
    <div id="page1" >
      <div class="oneaction">
        <div class="actionheader">
          <div class="headpicture" >
            <img src="images/example3.jpg" />
          </div>
          <div class="actionusername">
            徐维
          </div>
          <div class="actiondelete">
            <input type="button" name="删除" value="删除" onmouseover="this.style.color='#F00'; this.style.textShadow='1px 1px 2px #F00';" onmouseout="this.style.color='#F63'; this.style.textShadow='';" />
          </div>
          <div class="actiontime">
            2014.8.4.9.14
          </div>
        </div><!-- actionheader end -->
        <div class="actionbody">
          <div class="actionword">
            test 文字  我靠！
          </div>
          <div class="actionpicture">
            <img src="images/example1.jpg" />
          </div>
          <div class="commentaction">
            <textarea name="commentborder" onfocus="focusincomment(name);" onblur="focusoutcomment(name)"; onmouseover="overbordercolorcomment(name);" onmouseout="outbordercolorcomment(name);" ></textarea>
            <input name="comment" value="评论" type="button"   />
            <input name="share" value="分享" type="button"   />
          </div>
          <hr />
        </div><!-- actionbody end -->
      </div><!-- oneaction end -->
    </div><!-- page1 end -->
    
    <div id="page2" >
      <div class="onefocus">
        <div class="headpicture" >
            <img src="images/example3.jpg" />
        </div>
        <div class="actionusername">
            徐维
        </div>
        <hr />
      </div><!-- onefocus end -->
      <div class="onefocus">
        <div class="headpicture" >
            <img src="images/example3.jpg" />
        </div>
        <div class="actionusername">
            徐维
        </div>
        <hr />
      </div><!-- onefocus end -->
    </div><!-- page2 end -->
    <div id="page3" >
      <div class="onefocus">
        <div class="headpicture" >
            <img src="images/example3.jpg" />
        </div>
        <div class="actionusername">
            徐维
        </div>
        <hr />
      </div><!-- onefocus end -->
      <div class="onefocus">
        <div class="headpicture" >
            <img src="images/example3.jpg" />
        </div>
        <div class="actionusername">
            徐维
        </div>
        <hr />
      </div><!-- onefocus end -->
    </div><!-- page3 end -->
    <div id="page4" >
      <div class="oneaction">
        <img src="images/example1.jpg" />
      </div>
    </div><!-- page4 end -->
    <div id="page5" >
      <div class="oneaction">
        <img src="images/example2.jpg" />
      </div>
    </div><!-- page5 end -->
    <div id="page6" >
      <div class="oneaction">
        <img src="images/example3.jpg" />
      </div>
    </div><!-- page6 end -->
    <div id="page7" >
      <div class="oneaction">
        <img src="images/example3.jpg" />
      </div>
    </div><!-- page7 end -->
  </div><!-- main end -->
  
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