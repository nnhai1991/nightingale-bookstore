<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.nightingale.Constants"%>
<c:set var="types" value="<%=Constants.DiscountTypes.AVAILABLES%>"></c:set>
<p class="text-danger">
	<spring:message code="${error}" text="${error}" />
</p>
<p class="text-danger">
	<form:errors path="" />
</p>
<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="name" />
	</label>
	<div class="col-sm-8">
		<spring:message code="article" var="articlePlaceholder" />
		<form:select path="articles" type="text" class="form-control" multiple="true" 
			placeholder="${articlePlaceholder}" items="${articles}"
			itemLabel="name" />
	</div>
	<p class="text-danger">
		<form:errors path="articles" />
	</p>
</div>
<!-- description -->
<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="description" />
	</label>
	<div class="col-sm-8">
		<spring:message code="description" var="descriptionPlaceholder" />
		<form:input path="description" type="text" class="form-control"
			placeholder="${descriptionPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="description" />
	</p>
</div>
<!-- description -->

<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="type" />
	</label>
	<div class="col-sm-8">
		<spring:message code="type" var="typePlaceholder" />
		<form:select path="type" class="form-control" items="${types}" />
	</div>
	<p class="text-danger">
		<form:errors path="type" />
	</p>
</div>

<!-- amount -->
<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="amount" />
	</label>
	<div class="col-sm-8">
		<spring:message code="amount" var="amountPlaceholder" />
		<form:input path="amount" type="number" class="form-control"
			placeholder="${amountPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="amount" />
	</p>
</div>

<!-- start date -->
<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="start_date" />
		(
		<security:authentication property="principal.user.timezone" />
		)
	</label>
	<div class="col-sm-8">
		<spring:message code="start_date" var="start_datePlaceholder" />
		<form:input path="startDateLocal" type="text"
			class="form-control datetimepicker" placeholder="${start_datePlaceholder}" required="required" />
	</div>
	<p class="text-danger">
		<form:errors path="startDateLocal" />
	</p>
</div>

<!-- end date -->

<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="end_date" />
		(
		<security:authentication property="principal.user.timezone" />
		)
	</label>
	<div class="col-sm-8">
		<spring:message code="end_date" var="endDatePlaceholder" />
		<form:input path="endDateLocal" type="text"
			class="form-control datetimepicker" placeholder="${endDatePlaceholder}" required="required" />
	</div>
	<p class="text-danger">
		<form:errors path="endDateLocal" />
	</p>
</div>
<!-- enabled -->
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

<script type="text/javascript">
	$(function() {
		$('.datetimepicker').datetimepicker({
			format:"DD/MM/YYYY HH:mm"
		});
	});
</script>
