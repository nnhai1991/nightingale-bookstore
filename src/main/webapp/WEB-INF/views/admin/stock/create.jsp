<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link rel="stylesheet" href="<c:url value="/resources/vendor/bootstrap-tagsinput/bootstrap-tagsinput.css"/>"/>
<script src="<c:url value="/resources/vendor/bootstrap-tagsinput/bootstrap-tagsinput.min.js"/>"></script>


<tiles:insertDefinition name="admin-template">
	<tiles:putAttribute name="body">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message
						code="administration" /></li>
				<li class="breadcrumb-item"><spring:message code="stock" /></li>
				<li class="breadcrumb-item active"><spring:message
						code="create" /></li>
			</ol>
			<c:url value="/admin/stock/create" var="createURL">
				<c:param name="${_csrf.parameterName}" value="${_csrf.token}" />
			</c:url>
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form method="POST" action="${createURL}"
						modelAttribute="stock" cssClass="form-horizontal">

						<c:import url="form.jsp"></c:import>
						<div class="form-group">
							<spring:message code="create" var="create" />

							<c:url var="homeUrl" value="/admin/stock" />
							<a class="btn btn-default" href="${homeUrl}"> <spring:message
									code="back_to_listing" />
							</a>

							<button class="btn btn-primary" type="submit"
								class="action-button">${create}</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

