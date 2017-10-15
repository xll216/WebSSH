<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by 蓝鸥科技有限公司  www.lanou3g.com.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎</title>
</head>
<body>

<a href="upload.jsp">单文件上传</a><br><br>

<a href="download.action?fileName=JavaEE框架开发.png">文件下载JavaEE框架开发.png</a><br><br>
<a href="download.action?fileName=Struts2.xmind">文件下载Struts2.xmind</a><br><br>

<a href="login.jsp">登录界面</a><br><br>

<a href="findAllDepartment_hr.action">部门二级联动</a>

<%--显示错误信息--%>
<s:actionerror></s:actionerror>
<s:fielderror></s:fielderror>


</body>
</html>
