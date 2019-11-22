<%--
  Created by IntelliJ IDEA.
  User: LaughingGor
  Date: 2019-10-27
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/reset.css">
  <link rel="stylesheet" href="css/my-navbar.css">
</head>

<body>

<div id="main">
  <!--导航栏-->
  <nav class="navbar navbar-default">
    <div class="container">
      <!-- 下拉触发按钮 以及 商标-->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapse1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">科普研学平台</a>
      </div>
      <!-- 适配手机，将部分栏目转至下拉导航-->
      <div class="collapse navbar-collapse" id="collapse1">
        <ul class="nav navbar-nav">
          <li><a href="product/kpcp/">科普产品 <span class="sr-only">(current)</span></a></li>
          <li><a href="product/yxcp/">研学产品</a></li>
        </ul>
        <form class="navbar-form navbar-right">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
          </div>
          <button type="submit" class="btn btn-default">搜索</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
        </ul>
      </div><!-- /.navbar-collapse -->
    </div>
  </nav>
  <!--主面板-->
</div>

</body>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/vue.min.js"></script>
</html>
