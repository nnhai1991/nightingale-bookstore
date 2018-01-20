<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>

<tiles:insertDefinition name="admin-template">
	<tiles:putAttribute name="body">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message
						code="administration" /></li>
				<li class="breadcrumb-item active"><spring:message code="user" /></li>
			</ol>

			<div id="notification" class="bg-green"></div>

			<c:url value="/user/create" var="createURL" />
			<a class="btn btn-outline-dark"
				onClick="window.location='${createURL}'"> <span
				class="glyphicon glyphicon-plus"></span> <spring:message
					code="new_user" />
			</a> <br /> <br />
			<!-- <div class="content-note"></div> -->

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
			<br />
			<table class="table table-bordered table-responsive-xs table-sm">
				<thead>
					<tr>
						<th><spring:message code="first_name" /></th>
						<th><spring:message code="last_name" /></th>
						<th><spring:message code="email" /></th>
						<th><spring:message code="role" /></th>
						<th><spring:message code="shop" /></th>
						<th><spring:message code="locked" /></th>
						<th><spring:message code="status" /></th>
						<th></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${users}" var="userDTO">
						<tr>

							<td>${userDTO.user.firstName}</td>
							<td>${userDTO.user.lastName}</td>
							<td>${userDTO.user.email}</td>
							<td>${userDTO.role.name}</td>
							<td>${userDTO.shop.name}</td>
							<td><c:choose>
									<c:when test="${userDTO.user.notLocked}">
										<spring:message code="unlocked" />
									</c:when>
									<c:otherwise>
										<spring:message code="locked" />
									</c:otherwise>

								</c:choose></td>

							<td><c:choose>
									<c:when test="${userDTO.user.enabled}">
										<spring:message code="active" />
									</c:when>
									<c:otherwise>
										<spring:message code="inactive" />
									</c:otherwise>

								</c:choose></td>

							<c:url value="/user/details" var="detailsURL">
								<c:param value="${userDTO.user.id}" name="userId" />
							</c:url>

							<c:url value="/user/update" var="updateURL">
								<c:param value="${userDTO.user.id}" name="userId" />
							</c:url>
							<c:url value="/user/delete" var="deleteURL">
								<c:param value="${userDTO.user.id}" name="userId" />
							</c:url>
							<c:url value="/user/change-password" var="changePasswordURL">
								<c:param value="${userDTO.user.id}" name="userId" />
							</c:url>
							<td nowrap="nowrap"><a href="${detailsURL}"
								class="btn btn-outline-dark btn-sm"> <i class="fa fa-eye"></i>
									<spring:message code="details" />
							</a> <a href="${updateURL}" class="btn btn-outline-dark btn-sm">
									<i class="fa fa-pencil"></i> <spring:message code="update" />
							</a>
							<a href="${changePasswordURL}" class="btn btn-outline-dark btn-sm">
									<i class="fa fa-lock"></i> <spring:message code="change_password" />
							</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>

			<c:if test="${pagination.totalPages gt 0 }">
				<nav>
					<ul class="pagination">
						<c:url value="/user" var="pageURL">
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
