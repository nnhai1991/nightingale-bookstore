<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="form-group row">
	<label class="col-2 col-form-label">
		<spring:message code="name" />
	</label>
	<div class="col-6">
		<spring:message code="name" var="namePlaceholder" />
		<form:input path="article.name" type="text" class="form-control"
			placeholder="${namePlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.name" />
	</p>
</div>

<div class="form-group row">
	<label class="col-2 col-form-label">
		<spring:message code="sku" />
	</label>
	<div class="col-6">
		<spring:message code="sku" var="articleCodePlaceholder" />
		<form:input path="article.articleCode" type="text" class="form-control"
			placeholder="${articleCodePlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.articleCode" />
	</p>
</div>

<div class="form-group row">
	<label class="col-2 col-form-label">
		<spring:message code="description" />
	</label>
	<div class="col-6">
		<spring:message code="description" var="descriptionPlaceholder" />
		<form:input path="article.description" type="text" class="form-control"
			placeholder="${descriptionPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.description" />
	</p>
</div>

<div class="form-group row">
	<div class="form-check">
		<label class="form-check-label">
			<form:checkbox path="article.enabled" id="enabled" />
			<spring:message code="enabled" />
		</label>
		<p class="text-danger">
			<form:errors path="article.enabled" />
		</p>
	</div>
</div>
