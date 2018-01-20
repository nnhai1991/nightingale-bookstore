<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<tiles:insertDefinition name="admin-template">
	<tiles:putAttribute name="body">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message
						code="administration" /></li>
				<li class="breadcrumb-item"><spring:message code="shop" /></li>
				<li class="breadcrumb-item active"><spring:message
						code="details" /></li>
			</ol>
			<!--  For notification as feedback from create, update and delete -->
			<div id="notification" class="bg-green"></div>
			<div id="content">
				<div class="detail-view">
					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="email" />
						</label>
						<span class="col-form-label"> <c:out value="${userDTO.user.email}" />
						</span>
					</div>

					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="role" />
						</label>
						<span class="col-form-label"> <c:out value="${userDTO.role.name}" />
						</span>
					</div>

					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="shop" />
						</label>
						<span class="col-form-label"> <c:out value="${userDTO.shop.name}" />
						</span>
					</div>

					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="first_name" />
						</label>
						<span class="col-form-label"> <c:out value="${userDTO.user.firstName}" />
						</span>
					</div>

					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="last_name" />
						</label>
						<span class="col-form-label"> <c:out value="${userDTO.user.lastName}" />
						</span>
					</div>

					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="status" />
						</label>
						<span class="col-form-label"> 
							<c:choose>
								<c:when test="${userDTO.user.enabled}">
									<spring:message code="active" />
								</c:when>
								<c:otherwise>
									<spring:message code="inactive" />
								</c:otherwise>
							</c:choose>
						</span>
					</div>


					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="lock_status" />
						</label>
						<span class="col-form-label"> 
							<c:choose>
								<c:when test="${userDTO.user.notLocked}">
									<spring:message code="unlocked" />
								</c:when>
								<c:otherwise>
									<spring:message code="locked" />
								</c:otherwise>
							</c:choose>
						</span>
					</div>
					<c:url value="/user/change-password"
						var="changePasswordURL">
						<c:param value="${userDTO.user.id}" name="userId" />
					</c:url>
					<form:form method="POST" action="${changePasswordURL}">
						<c:url var="homeUrl" value="/user" />
						<a class="btn btn-outline-dark" href="${homeUrl}"> <spring:message
								code="back_to_listing" />
						</a>

						<button type="submit" class="btn btn-dark">
							<i class="fa fa-lock"></i>
							<spring:message code="change_password" />
						</button>
					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
