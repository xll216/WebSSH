<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by 蓝鸥科技有限公司  www.lanou3g.com.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传单个文件</title>
</head>
<body>
<h4>上传单个文件</h4>

<form action="singleUpload.action" enctype="multipart/form-data" method="post">

    选择文件：<input type="file" name="photo"><br>
    选择文件：<input type="file" name="photo"><br>
    选择文件：<input type="file" name="photo"><br>

    <input type="submit" value="上传">


</form>

<%--显示错误信息--%>
<s:actionerror></s:actionerror>

</body>
</html>
