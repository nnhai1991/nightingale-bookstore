<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<%@ page import="com.nightingale.util.DateFormat"%>

<c:set var="dateTimeFormat" value="<%=DateFormat.DISPLAY_DATE_TIME%>" />

<tiles:insertDefinition name="admin-template">
	<tiles:putAttribute name="body">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message
						code="administration" /></li>
				<li class="breadcrumb-item active"><spring:message
						code="discount" /></li>
			</ol>

			<div id="notification" class="bg-green"></div>

			<c:url value="/admin/discount/create" var="createURL" />
			<a class="btn btn-primary" href="${createURL}"> <i
				class="fa fa-plus"></i> <spring:message code="create" />
			</a> <br /> <br />

			<form class="form" action="" method="GET">
				<div class="input-group">
					<input type="text" class="form-control" name="keyword"
						placeholder='<spring:message code="type_here_to_search" />'
						value="${keyword}"> <span class="input-group-addon"><i
						class="fa fa-search"></i></span>
				</div>
				<c:if test="${not empty error}">
					<div class="error-message">
						<spring:message code="${error}" />
					</div>
				</c:if>
			</form>

			<br />
			<table class="table table-bordered table-responsive-xs table-sm">
				<thead>
					<tr>
						<th><spring:message code="id" /></th>
						<th><spring:message code="article" /></th>
						<th><spring:message code="description" /></th>
						<th><spring:message code="type" /></th>
						<th><spring:message code="amount" /></th>
						<th><spring:message code="start_date" /></th>
						<th><spring:message code="end_date" /></th>
						<th><spring:message code="enabled" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${discounts}" var="discount">
						<tr>
							<td>${discount.id}</td>
							<td><c:forEach items="${discount.articles}" var="article"> ${article.name} <br/></c:forEach></td>
							<td>${discount.description}</td>
							<td>${discount.type}</td>
							<td>${discount.amount}</td>
							<td><javatime:format value="${discount.startDateLocal}"
									pattern="dd/MM/yyyy HH:mm" /> (<security:authentication
									property="principal.user.timezone" />)</td>
							<td><javatime:format value="${discount.endDateLocal}"
									pattern="dd/MM/yyyy HH:mm" /> (<security:authentication
									property="principal.user.timezone" />)</td>
							<td><spring:message code="${discount.enabled}" htmlEscape="false" />

							</td>
							<td><c:url value="/admin/discount/update" var="updateURL">
									<c:param value="${discount.id}" name="discountId" />
								</c:url> <a class="btn btn-default" href="${updateURL}"> <i
									class="fa fa-pencil"></i>
							</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<c:if test="${pagination.totalPages gt 0 }">
				<nav>
					<ul class="pagination">

						<c:url value="/admin/discount" var="pageURL">
							<c:param name="keyword" value="${keyword}" />
						</c:url>
						<li class="page-item"><a class="page-link"
							href="${pageURL}&pageNo=${pagination.previousJumpPage}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>

						<c:forEach begin="${pagination.startingPage}"
							end="${pagination.endingPage}" var="i">

							<c:choose>
								<c:when test="${pagination.currentPage eq i }">
									<li class="page-item active"><a class="page-link"
										href="${pageURL}&pageNo=${i}">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="${pageURL}&pageNo=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>

						</c:forEach>

						<li class="page-item"><a
							href="${pageURL}&pageNo=${pagination.nextJumpPage}"
							class="page-link" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>

					</ul>
				</nav>
			</c:if>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
