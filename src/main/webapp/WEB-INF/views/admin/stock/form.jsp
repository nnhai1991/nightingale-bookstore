<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="com.nightingale.Constants"%>
<c:set var="types" value="<%=Constants.StockType.AVAILABLES%>"></c:set>
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
		<form:select path="article.id" type="text" class="form-control"
			placeholder="${articlePlaceholder}" items="${articles}"
			itemLabel="name" itemValue="id" />
	</div>
	<p class="text-danger">
		<form:errors path="article.id" />
	</p>
</div>
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
<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="quantity" />
	</label>
	<div class="col-sm-8">
		<spring:message code="quantity" var="quantityPlaceholder" />
		<form:input path="quantity" type="number" class="form-control"
			placeholder="${quantityPlaceholder}" />
	</div>
	<p class="text-danger">
		<form:errors path="quantity" />
	</p>
</div>
<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="stock_date_time" />
		(
		<security:authentication property="principal.user.timezone" />
		)
	</label>
	<div class="col-sm-8">
		<spring:message code="stock_date" var="datePlaceholder" />
		<form:input path="displayStockDateTime" type="text"
			class="form-control datetimepicker" placeholder="${datePlaceholder}" required="required" />
	</div>
	<p class="text-danger">
		<form:errors path="displayStockDateTime" />
	</p>
</div>
<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="fromSite" />
	</label>
	<div class="col-sm-8">
		<spring:message code="fromSite" var="fromSitePlaceholder" />
		<form:select path="fromSite.id" type="text" class="form-control"
			placeholder="${fromSitePlaceholder}">
			<form:option value="0">None</form:option>
			<form:options items="${sites}" itemLabel="name" itemValue="id" />
		</form:select>
	</div>
	<p class="text-danger">
		<form:errors path="fromSite.id" />
	</p>
</div>
<div class="form-group">
	<label class="col-sm-4 col-form-label">
		<spring:message code="toSite" />
	</label>
	<div class="col-sm-8">
		<spring:message code="toSite" var="toSitePlaceholder" />
		<form:select path="toSite.id" type="text" class="form-control"
			placeholder="${toSitePlaceholder}">
			<form:option value="0">None</form:option>
			<form:options items="${sites}" itemLabel="name" itemValue="id" />
		</form:select>
	</div>
	<p class="text-danger">
		<form:errors path="toSite.id" />
	</p>
</div>
<script type="text/javascript">
	$(function() {
		$('.datetimepicker').datetimepicker({
			format:"DD/MM/YYYY HH:mm"
		});
	});
</script>
