<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.nightingale.Constants"%>
<c:set var="types" value="<%=Constants.SiteTypes.AVAILABLES%>"></c:set>
<p class="text-danger">
	<spring:message code="${error}" text="${error}"/>
</p>
<p class="text-danger">
	<form:errors path="" />
</p>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="name" />
	</label>
	<div class="col-sm-8">
		<spring:message code="name" var="namePlaceholder" />
		<form:input path="name" type="text" class="form-control"
			placeholder="${namePlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="name" />
	</p>
</div>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="type" />
	</label>
	<div class="col-sm-8">
		<spring:message code="type" var="typePlaceholder" />
		<form:select path="type" class="form-control" items="${types}"/>
	</div>
	<p class="text-danger">
		<form:errors path="type" />
	</p>
</div>

<div class="form-group">
	<label class="col-sm-4 col-form-label"> <spring:message
			code="address" />
	</label>
	<div class="col-sm-8">
		<spring:message code="description" var="addressPlaceholder" />
		<form:input path="address" type="text" class="form-control"
			placeholder="${addressPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="address" />
	</p>
</div>

<div class="form-group">
	<div class="col-sm-8 col-sm-offset-4">
		<label class="form-check-label"> <form:checkbox path="enabled"
				id="enabled" /> <spring:message code="enabled" />
		</label>
		<p class="text-danger">
			<form:errors path="enabled" />
		</p>
	</div>
</div>