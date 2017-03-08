<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="Bean.City" %>
<%@ page import="Bean.District" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>管理景点信息</title>
<link rel="stylesheet" href="style/style.css">
<%
   	 	Map<City, List<District>> cityMap = new HashMap<City, List<District>>();//map集
		City cityDalian=new City("辽宁省");
		City cityShen=new City("山西省");
		District dl1=new District("沈阳市");
		District dl2=new District("大连市");
		District dl3=new District("锦州市");
		District dl4=new District("葫芦岛市");
		District dl5=new District("盘锦市");
		District dl6=new District("本溪市");
		District dl7=new District("辽阳市");
		List<District> dlDistrict=new ArrayList<District>();
		dlDistrict.add(dl1);
		dlDistrict.add(dl2);
		dlDistrict.add(dl3);
		dlDistrict.add(dl4);
		dlDistrict.add(dl5);
		dlDistrict.add(dl6);
		dlDistrict.add(dl7);
		District sy1=new District("太原市");
		District sy2=new District("运城市");
		District sy3=new District("吕梁市");
		District sy4=new District("大同市");
		District sy5=new District("阳泉市");
		District sy6=new District("朔州市");
		District sy7=new District("忻州市");
		District sy8=new District("长治市");
		District sy9=new District("晋城市");
		List<District> syDistrict=new ArrayList<District>();
		syDistrict.add(sy1);
		syDistrict.add(sy2);
		syDistrict.add(sy3);
		syDistrict.add(sy4);
		syDistrict.add(sy5);
		syDistrict.add(sy6);
		syDistrict.add(sy7);
		syDistrict.add(sy8);
		syDistrict.add(sy9);
		cityMap.put(cityDalian, dlDistrict);
		cityMap.put(cityShen,syDistrict);
		request.setAttribute("map", cityMap);  
   	  %>
<STYLE type="text/css">  
       .doubleselect br{  
           display:none;  
}  
    </STYLE>  
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
<s:if test="#request.havespot=='no'">
您还没有创建景点信息，请填写景点信息<br/>
<div class="doubleselect">
	<s:form action="CreateSpot" name="form1" method="post" enctype="multipart/form-data">
			<p>景点名称：<s:textfield name="spotname" label="景点名称" /><font color="red">${message}</font></p>
			<p>地点：<s:doubleselect 
		    formName="form1" 
		    label="地区"
		    list="#request.map.keySet()"
		    name="city"
		    id="comboId"
			listKey="city"
			listValue="city" 
			doubleName="district"
			doubleId="comboDoubleId"
			doubleList="#request.map[top]"
			doubleListKey="district"
			doubleListValue="district"  />	</p>
			<p>景点介绍：<s:textfield name="spottext" label="景点介绍" cols="80" rows="10"/></p>
			<p>联系电话：<s:textfield name="phonenumber" label="联系电话"/></p>
			<p>景点级别：<s:radio list="{'A','AA','AAA','AAAA','AAAAA'}" name="level" label="级别"/></p>
			<p><s:file name="myFile" label="上传单个图片（上传多个图片请多次上传）"/></p>
			
			<s:submit value="提交" />
	</s:form>
</div>
</s:if>
<s:else>

   				景点名称：<s:property value="%{myspot.spotname}"/><br/><br/>
 
   				地点：<s:property value="%{myspot.position}"/><br/><br/>

	   			<s:form action="ChangeSpotInfo" method="post" enctype="multipart/form-data">
	   				<s:hidden name="spotname" value="%{myspot.spotname}"/>
	   				联系电话：<s:textfield name="phonenumber" value="%{myspot.phonenumber}" label="联系电话"/><br/><br/>
	   				景点介绍：<s:textfield name="spottext" cssStyle="width:330px;" label="景点介绍" value="%{myspot.spottext}" cols="80" rows="10"/><br/><br/>
	   				
	   				景区级别：<s:radio list="{'A','AA','AAA','AAAA','AAAAA'}" value="%{myspot.level}" name="level" label="级别"/><br/><br/>
	   				添加图片：<s:file name="myFile" label="添加单个图片（添加多个图片请多次上传）"/><br/><br/>
	   				<s:submit value="保存" />
	   			</s:form>

</s:else>
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