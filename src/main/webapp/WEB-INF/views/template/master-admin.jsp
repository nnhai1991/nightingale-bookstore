<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"
	scope="session" />
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="project_title_admin" /></title>
<tiles:insertAttribute name="htmlhead" />
</head>
<body>
	<!-- Navigation-->
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">
				<spring:message code="project_title"></spring:message>
			</a>
		</div>
		<!-- /.navbar-header -->
		<ul class="nav navbar-top-links navbar-right">
			<!-- /.dropdown -->
			<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">
					<i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
				</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#">
							<i class="fa fa-user fa-fw"></i> User Profile
						</a></li>
					<li><a href="#">
							<i class="fa fa-gear fa-fw"></i> Settings
						</a></li>
					<li class="divider"></li>
					<li><a href="login.html">
							<i class="fa fa-sign-out fa-fw"></i> Logout
						</a></li>
				</ul> <!-- /.dropdown-user --></li>
			<!-- /.dropdown -->
		</ul>
		<!-- /.navbar-top-links -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav in" id="side-menu">
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div> <!-- /input-group -->
					</li>
					<li><a href="index.html" class="active">
							<i class="fa fa-dashboard fa-fw"></i> Dashboard
						</a></li>
					<li><a href="#">
							<i class="fa fa-bar-chart-o fa-fw"></i>Configuration<span
								class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level collapse">
						    <li><a href="<c:url value="/admin/site"/>">Site</a></li>
							<li><a href="<c:url value="/admin/article"/>">Article</a></li>
							<li><a href="<c:url value="/admin/tag"/>">Tag</a></li>
						</ul>
					</li>
					<li><a href="<c:url value="/admin/stock"/>">Stock</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side -->
	</nav>
	<div id="page-wrapper" style="min-height: 660px;">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Dashboard</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="row">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<div class="content-wrapper">
		<footer class="sticky-footer">
			<div class="container">
				<div class="text-center">
					<small>Copyright © Nightingale 2017</small>
				</div>
			</div>
		</footer>
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top">
			<i class="fa fa-angle-up"></i>
		</a>
	</div>
</body>
</html>