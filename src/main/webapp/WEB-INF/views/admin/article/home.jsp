<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<%@ page import="com.nightingale.app.util.UtilFormat"%>

<c:set var="dateTimeFormat"
	value="<%=UtilFormat.UTC_JAVSCRIPT_DISPLAY_FORMAT%>" />

<tiles:insertDefinition name="admin-template">
	<tiles:putAttribute name="body">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message code="administration"/></li>
				<li class="breadcrumb-item active"><spring:message code="model"/></li>
			</ol>

			<div id="notification" class="bg-green"></div>
			
			<c:url value="/model/create" var="createURL" />
			<a class="btn btn-outline-dark" href="${createURL}">
				<i class="fa fa-plus"></i>
				<spring:message code="new_model" />
			</a>
			
			<br/><br/>
			
			<form class="form" action="" method="GET">
				<div class="input-group">
					<input type="text" class="form-control" name="keyword"
						placeholder='<spring:message code="type_here_to_search" />'
						value="${keyword}">
					<span class="input-group-addon"><i class="fa fa-search"></i></span>
				</div>
				<c:if test="${not empty error}">
					<div class="error-message">
						<spring:message code="${error}" />
					</div>
				</c:if>
			</form>
			
			<br/>
			<table class="table table-bordered table-responsive-xs table-sm">
				<thead>
					<tr>
						<th><spring:message code="id" /></th>
						<th><spring:message code="name" /></th>
						<th><spring:message code="sku" /></th>
						<th><spring:message code="description" /></th>
						<th><spring:message code="status" /></th>
						<th><spring:message code="created_by" /></th>
						<th><spring:message code="created_date" /></th>
						<th><spring:message code="action" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${models}" var="model">
						<tr>
							<td>${model.id}</td>
							<td>${model.name}</td>
							<td>${model.modelCode}</td>
							<td>${model.description}</td>
							<td>
								<c:if test="${model.enabled == true}">
									<spring:message code="enabled" />
								</c:if>
								<c:if test="${model.enabled == false}">
									<spring:message code="disabled" />
								</c:if>
							</td>
							<td>${model.createdBy}</td>
							<td>
								<javatime:format value="${model.createdDateLocal}" pattern="${dateTimeFormat}"
                                                 var="createdDate"/>
                                <script>
                                    document.write(utcToLocalDate('${createdDate}'));
                                </script>
							</td>

							<c:url value="/model/details" var="detailsURL">
								<c:param value="${model.id}" name="modelId" />
							</c:url>
							<c:url value="/model/update" var="updateURL">
								<c:param value="${model.id}" name="modelId" />
							</c:url>

							<td>
								<a class="btn btn-outline-dark" href="${detailsURL}">
									<i class="fa fa-eye"></i>
									<spring:message code="details" />
								</a>
								<a class="btn btn-outline-dark" href="${updateURL}">
									<i class="fa fa-eye"></i>
									<spring:message code="update" />
								</a>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<c:if test="${pagination.totalPages gt 0 }">
				<nav>
					<ul class="pagination">

						<c:url value="/model" var="pageURL">
							<c:param name="keyword" value="${keyword}" />
						</c:url>
						<li class="page-item"><a class="page-link"
								href="${pageURL}&pageNo=${pagination.previousJumpPage}"
								aria-label="Previous">
								<span aria-hidden="true">&laquo;</span>
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
								class="page-link" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a></li>

					</ul>
				</nav>
			</c:if>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
