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

<body class="fixed-nav sticky-footer bg-dark" id="page-top">

	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">

		<a class="navbar-brand" href="/shop-dashboard"><spring:message code="shop_dashboard" /></a>

		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<c:url value="/shop" var="shopURL" />
		<c:url value="/model" var="modelURL" />
		<c:url value="/user" var="userURL" />
		<c:url value="/inventory" var="inventoryURL" />
		<c:url value="/delivery" var="deliveryURL" />
		<c:url value="/payment" var="paymentURL" />
		<c:url value="/report/payment/paymentInfoByDate" var="paymentInfoByDateURL" />

		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li
					class="nav-item <c:if test="${currentTab eq 'dashboard'}">active</c:if>"
					data-toggle="tooltip" data-placement="right" title="Dashboard">
					<a class="nav-link" href="/shop-dashboard"> <i class="fa fa-fw fa-dashboard"></i>
						<span class="nav-link-text"><spring:message code="dashboard" /></span>
					</a>
				</li>
				
				<security:authorize access="hasAnyRole('SA')">
					<li
						class="nav-item <c:if test="${currentTab eq 'shop'}">active</c:if>"
						data-toggle="tooltip" data-placement="right"><a
						class="nav-link" href="${shopURL}"> <i
							class="fa fa-fw fa-home"></i> <span class="nav-link-text"><spring:message
									code="shops" /></span>
						</a>
					</li>
				</security:authorize>
				
				<security:authorize access="hasAnyRole('SA')">
					<li
						class="nav-item <c:if test="${currentTab eq 'model'}">active</c:if>"
						data-toggle="tooltip" data-placement="right"><a
						class="nav-link" href="${modelURL}"> <i
							class="fa fa-fw fa-tablet"></i> <span class="nav-link-text"><spring:message
									code="model" /></span>
						</a>
					</li>
				</security:authorize>
				
				<security:authorize access="hasAnyRole('SA,AD')">
					<li
						class="nav-item <c:if test="${currentTab eq 'user'}">active</c:if>"
						data-toggle="tooltip" data-placement="right"><a
						class="nav-link" href="${userURL}"> <i
							class="fa fa-fw fa-users"></i> <span class="nav-link-text"><spring:message
									code="user" /></span>
						</a>
					</li>
				</security:authorize>
				
				<security:authorize access="hasAnyRole('AD,IN,INPADE')">
					<li
						class="nav-item <c:if test="${currentTab eq 'inventory'}">active</c:if>"
						data-toggle="tooltip" data-placement="right"><a
						class="nav-link" href="${inventoryURL}"> <i
							class="fa fa-fw fa-database"></i> <span class="nav-link-text"><spring:message
									code="inventory" /></span>
						</a>
					</li>
				</security:authorize>

				<security:authorize access="hasAnyRole('AD,PA,INPADE')">
					<li
						class="nav-item <c:if test="${currentTab eq 'payment'}">active</c:if>"
						data-toggle="tooltip" data-placement="right"><a
						class="nav-link" href="${paymentURL}"> <i
							class="fa fa-fw fa-money"></i> <span class="nav-link-text"><spring:message
									code="payment" /></span>
						</a>
					</li>
				</security:authorize>

				<security:authorize access="hasAnyRole('AD,PA,INPADE')">
					<li
						class="nav-item <c:if test="${currentTab eq 'delivery'}">active</c:if>"
						data-toggle="tooltip" data-placement="right"><a
						class="nav-link" href="${deliveryURL}"> <i
							class="fa fa-fw fa-envelope-open-o"></i> <span class="nav-link-text"><spring:message
									code="delivery" /></span>
						</a>
					</li>
				</security:authorize>

				<security:authorize access="hasAnyRole('AD,DE,INPADE')">
					<li
						class="nav-item <c:if test="${currentTab eq 'paymentInfoByDate'}">active</c:if>"
						data-toggle="tooltip" data-placement="right"><a
						class="nav-link" href="${paymentInfoByDateURL}"> <i
							class="fa fa-fw fa-table"></i> <span class="nav-link-text">Payment Info by Date Report</span>
						</a>
					</li>
				</security:authorize>
			</ul>

			<ul class="navbar-nav sidenav-toggler">
				<li class="nav-item"><a class="nav-link text-center"
					id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
					</a>
				</li>
			</ul>

			<ul class="navbar-nav ml-auto">
				<c:url value="/logout" var="logout" />
				<form:form id="logoutForm" action="${logout}" method="post">
					<li class="nav-item"><a href="#"
						onclick="document.getElementById('logoutForm').submit()"
						class="nav-link"> <i class="fa fa-fw fa-sign-out"></i> <spring:message
								code="logout" />
					</a></li>
				</form:form>
			</ul>
			
		</div>
	</nav>
	<div class="content-wrapper">
		<tiles:insertAttribute name="body" />
		<footer class="sticky-footer">
			<div class="container">
				<div class="text-center">
					<small>Copyright © Samsung 2017</small>
				</div>
			</div>
		</footer>

		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>
		<!-- Custom scripts for all pages-->
		<script
			src="<c:url value="/resources/library/admin-template/js/sd-admin.min.js"/>"></script>
	</div>

</body>

</html>