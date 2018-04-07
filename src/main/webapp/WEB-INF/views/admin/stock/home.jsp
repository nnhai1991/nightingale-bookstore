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

<c:set var="dateTimeFormat"
	value="<%=DateFormat.DISPLAY_DATE_TIME%>" />

<tiles:insertDefinition name="admin-template">
	<tiles:putAttribute name="body">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message
						code="administration" /></li>
				<li class="breadcrumb-item active"><spring:message code="stock" /></li>
			</ol>

			<div id="notification" class="bg-green"></div>

			<c:url value="/admin/stock/create" var="createURL" />
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
						<th><spring:message code="type" /></th>
						<th><spring:message code="fromSite" /></th>
						<th><spring:message code="toSite" /></th>
						<th><spring:message code="quantity" /></th>
						<th><spring:message code="stock_date_time" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${stocks}" var="stock">
						<tr>
							<td>${stock.id}</td>
							<td>${stock.article.name}</td>
							<td>${stock.type}</td>
							<td>${stock.fromSite.name}</td>
							<td>${stock.toSite.name}</td>
							<td>${stock.quantity}</td>
                            <td>
                            <javatime:format value="${stock.displayStockDateTime}" pattern="dd/MM/yyyy HH:mm"/>
                            (<security:authentication property="principal.user.timezone" />)
                            </td>
							<td><c:url value="/admin/stock/update" var="updateURL">
									<c:param value="${stock.id}" name="stockId" />
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

						<c:url value="/admin/stock" var="pageURL">
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
