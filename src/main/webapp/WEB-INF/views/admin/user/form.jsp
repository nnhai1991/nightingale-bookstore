<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<div class="form-group">
	<p class="text-danger">
		<form:errors path="" />
	</p>
</div>
<div class="form-group row">
	<label class="col-2 col-form-label">
		<spring:message code="email" />
	</label>
	<spring:message code="email_placeholder" var="emailPlaceholder" />
	<div class="col-6">
		<form:input path="email" type="text" class="form-control"
			placeholder="${emailPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="email" />
	</p>
</div>

<div class="form-group row">
	<label class="col-2 col-form-label">
		<spring:message code="role" />
	</label>
	<div class="col-6">
		<form:select path="roleId" items="${roles}" itemLabel="name"
			class="form-control" itemValue="id" />
	</div>
</div>
<div class="form-group row">
	<label class="col-2 col-form-label">
		<spring:message code="shop" />
	</label>
	<div class="col-6">
		<form:select path="shopId" items="${shops}" itemLabel="name"
			class="form-control" itemValue="id" />
	</div>
</div>

<div class="form-group row">
	<label class="col-2 col-form-label">
		<spring:message code="first_name" />
	</label>
	<spring:message code="first_name_placeholder"
		var="firstNamePlaceholder" />
	<div class="col-6">
		<form:input path="firstName" type="text" class="form-control"
			placeholder="${firstNamePlaceholder}" />

	</div>
	<p class="text-danger">
		<form:errors path="firstName" />
	</p>
</div>

<div class="form-group row">
	<label class="col-2 col-form-label">
		<spring:message code="last_name" />
	</label>
	<spring:message code="last_name_placeholder" var="lastNamePlaceholder" />
	<div class="col-6">
		<form:input path="lastName" type="text" class="form-control"
			placeholder="${lastNamePlaceholder}" />

	</div>
	<p class="text-danger">
		<form:errors path="lastName" />
	</p>
</div>
<div class="form-group row">
	<div class="form-check">
		<label class="form-check-label">
			<form:checkbox path="enabled" id="enabled" />
			<spring:message code="enabled" />
		</label>
		<p class="text-danger">
			<form:errors path="enabled" />
		</p>
	</div>
</div>
<div class="form-group row">
	<div class="form-check">
		<label class="form-check-label">
			<form:checkbox path="notLocked" id="notLocked" />
			<spring:message code="unlocked" />
		</label>
	</div>
	<p class="text-danger">
		<form:errors path="notLocked" />
	</p>
</div>

