<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<tiles:insertDefinition name="admin-template">
	<tiles:putAttribute name="body">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message
						code="administration" /></li>
				<li class="breadcrumb-item"><spring:message code="user" /></li>
				<li class="breadcrumb-item active"><spring:message
						code="create" /></li>
			</ol>
			<c:url value="/user/create" var="createURL">
				<c:param name="${_csrf.parameterName}" value="${_csrf.token}" />
			</c:url>

			<form:form method="POST" action="${createURL}" modelAttribute="user">

				<c:import url="form.jsp"></c:import>

				<div class="form-group row">
					<label class="col-2 col-form-label">
						<spring:message code="password" />
					</label>
					<div class="col-6">
						<spring:message code="password" var="passwordPlaceholder" />
						<form:input path="password" type="password" class="form-control" 
							placeholder="${passwordPlaceholder}" />
					</div>
					<p class="text-danger">
						<form:errors path="password" htmlEscape="false" />
					</p>
				</div>
				<div class="form-group row">
					<label class="col-2 col-form-label">
						<spring:message code="verify_password" />
					</label>
					<div class="col-6">
						<spring:message code="verify_password"
							var="verifyPasswordPlaceholder" />
						<form:input path="passwordConfirm" type="password" class="form-control" 
							placeholder="${verifyPasswordPlaceholder}" />
					</div>
					<p class="text-danger">
						<form:errors path="passwordConfirm" />
					</p>
				</div>

				<div class="form-group">
					<spring:message code="create" var="create" />
					<c:url var="homeUrl" value="/user" />
					<a class="btn btn-outline-secondary" href="${homeUrl}"> <spring:message
							code="back_to_listing" />
					</a>
					<button class="btn btn-outline-primary" type="submit">${create}</button>
				</div>
			</form:form>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

