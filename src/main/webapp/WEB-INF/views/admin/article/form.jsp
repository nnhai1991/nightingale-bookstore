<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<p class="text-danger">
	<spring:message code="${error}" text="${error}"/>
</p>
<p class="text-danger">
	<form:errors path="article" />
</p>

<div class="form-group">
	<label class="col-2 col-form-label"> <spring:message
			code="name" />
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

<div class="form-group">
	<label class="col-2 col-form-label"> <spring:message
			code="code" />
	</label>
	<div class="col-6">
		<spring:message code="code" var="codePlaceholder" />
		<form:input path="article.code" type="text" class="form-control"
			placeholder="${codePlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.code" />
	</p>
</div>

<div class="form-group">
	<label class="col-2 col-form-label"> <spring:message
			code="description" />
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

<div class="form-group">
	<label class="col-2 col-form-label"> <spring:message
			code="priority" />
	</label>
	<div class="col-6">
		<spring:message code="priority" var="priorityPlaceholder" />
		<form:input path="article.priority" type="text" class="form-control"
			placeholder="${priorityPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="article.code" />
	</p>
</div>
<div class="form-group">
	<label class="col-2 col-form-label"> <spring:message
			code="tag" />
	</label>
	<div class="col-6">
		<form:select class="form-control" path="tags" multiple="multiple" data-role="tagsinput" items="${articleDTO.tags}"></form:select>
	</div>
	<p class="text-danger">
		<form:errors path="tags" />
	</p>
</div>


<div class="form-group">
	<div class="form-check">
		<label class="form-check-label"> <form:checkbox path="article.enabled"
				id="enabled" /> <spring:message code="enabled" />
		</label>
		<p class="text-danger">
			<form:errors path="article.enabled" />
		</p>
	</div>
</div>
