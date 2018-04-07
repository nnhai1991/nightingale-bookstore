<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime"%>
<%@ page import="com.nightingale.util.DateFormat"%>

<c:set var="dateTimeFormat"
	value="<%=DateFormat.DISPLAY_DATE_TIME%>" />

<tiles:insertDefinition name="admin-template">
    <tiles:putAttribute name="body">
        <div class="container-fluid">

			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><spring:message code="administration" /></li>
				<li class="breadcrumb-item"><spring:message code="model" /></li>
				<li class="breadcrumb-item active"><spring:message code="details" /></li>
			</ol>
			
            <!--  For notification as feedback from create, update and delete -->
            <div id="notification" class="bg-green"></div>

			<div id="content">
				<div class="detail-view">
					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="id" />
						</label>
						<span class="col-form-label">
							<c:out value="${modelDTO.model.id}" />
						</span>
					</div>


					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="name" />
						</label>
						<span class="col-form-label">
							<c:out value="${modelDTO.model.name}" />
						</span>
					</div>


					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="sku" />
						</label>
						<span class="col-form-label">
							<c:out value="${modelDTO.model.modelCode}" />
						</span>
					</div>
					
					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="description" />
						</label>
						<span class="col-form-label">
							<c:out value="${modelDTO.model.description}" />
						</span>
					</div>

					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="status" />
						</label>
						<span class="col-form-label">
							<c:if test="${modelDTO.model.enabled == true}">
								<spring:message code="enabled" />
							</c:if>
							<c:if test="${modelDTO.model.enabled == false}">
								<spring:message code="disabled" />
							</c:if>
						</span>
					</div>
					
					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="created_by" />
						</label>
						<span class="col-form-label">
							<c:out value="${modelDTO.model.createdBy}" />
						</span>
					</div>
					
					<div class="form-group row">
						<label class="col-2 col-form-label">
							<spring:message code="status" />
						</label>
						<span class="col-form-label">
							<javatime:format 
								value="${modelDTO.model.createdDateLocal}" 
								pattern="${dateTimeFormat}"
                                var="createdDate"/>
                            <script>
                                document.write(utcToLocalDate('${createdDate}'));
                            </script>
						</span>
					</div>

					<c:url var="homeUrl" value="/model" />
					<a class="btn btn-outline-secondary" href="${homeUrl}">
						<spring:message code="back_to_listing" />
					</a>
				</div>
			</div>

		</div>
    </tiles:putAttribute>
</tiles:insertDefinition>
