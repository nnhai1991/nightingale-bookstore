<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.nightingale.app.util.UtilFormat"%>

<tiles:insertDefinition name="admin-template">

	<tiles:putAttribute name="body">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message code="administration" /></li>
				<li class="breadcrumb-item"><spring:message code="model" /></li>
				<li class="breadcrumb-item active"><spring:message code="update" /></li>
			</ol>

			<c:url value="/model/update" var="updateURL">
				<c:param name="${_csrf.parameterName}" value="${_csrf.token}" />
			</c:url>


			<form:form method="POST" action="${updateURL}" modelAttribute="modelDTO">
				<form:input type="hidden" path="model.id" />
				<c:import url="form.jsp"></c:import>
				<div class="form-group">
					<spring:message code="update" var="update" />
					
					<c:url var="homeUrl" value="/model" />
					<a class="btn btn-outline-secondary" href="${homeUrl}">
						<spring:message code="back_to_listing" />
					</a>

					<button class="btn btn-outline-primary" type="submit"
						class="action-button">${update}</button>
				</div>
			</form:form>
		</div>

	</tiles:putAttribute>
</tiles:insertDefinition>

