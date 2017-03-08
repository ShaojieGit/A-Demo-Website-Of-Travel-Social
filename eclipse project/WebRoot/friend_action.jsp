
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>驴友动态</title>
<link rel="stylesheet" href="style/style.css">
<script type="text/javascript" src="js/inputborder.js"></script>
<script type="text/javascript" src="js/showbigpicture.js"></script>
<script type="text/javascript" src="js/showshadow.js"></script>
<script type="text/javascript" src="js/closebigpicture.js"></script>
<script type="text/javascript" src="jq/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/showpicturelightbox.js"></script>

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
  <div id="idmain" class="main">
    <div id="page1" >
      <div class="publishaction" >
      
        <s:form action="Publish" method="post" enctype="multipart/form-data">
  		     <s:textarea name="actiontext" cols="80" rows="5" cssStyle="width:720px; height:80px;border-color:#999; border-radius:3px;" />
  		     <s:file name="myFile" label="添加图片" cssStyle="float:left; margin-left:0;"/>
  		     <s:hidden name="towhere" value="friendaction"/>
			<s:submit value="发布" cssStyle="float:right; height:35px; border-radius:4px; float:right; margin-left:10px; cursor:pointer;" />
       </s:form>
      </div>
      <br/><br/><br/>
      <s:iterator value="#request.friendaction" id="id">
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
          <h1>
          <div class="actionpicture">
            <s:if test="#id.actionimage!=null&&#id.actionimage!=''">
  			     <img  src="ActionImage/<s:property value="#id.actionimage" />" />
  	        </s:if>
            
          </div>
          </h1>
          
          <div class="commenttable" >
          		<s:iterator value="#id.comments" id="commentid">

  								<img style="width:40px;height:40px;" src="HeadImage/<s:property value="#commentid.headimagename"/>"/>
								<s:property value="#commentid.username"/>
  								<s:if test="#commentid.repeat!=null&&#commentid.repeat!=''">
  								回复<s:property value="#commentid.repeat"/>:
  								</s:if>
  								评论时间：<s:property value="#commentid.commenttime.substring(0,19)"/><br/>
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
    	  		</s:iterator>
    	  </div>
    	    
            <div class="commentaction">
            <s:form action="Comment">
             <s:textarea cols="80" rows="1" name='commentcontent' />
             <s:hidden name="username" value="%{#session.user}"/>
             <s:hidden name="whoseaction" value="%{#id.username}"/>
             <s:hidden name="publishtime" value="%{#id.publishtime}"/>
             <s:submit value='评论'  />
             </s:form>
          </div>
        </div><!-- actionbody end -->
        <div class="grayline"></div>
      </div><!-- oneaction end -->
      
      </s:iterator>
    </div><!-- page1 end --> 
  </div><!-- main end -->
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>  <br/><br/>
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
