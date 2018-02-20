<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="modal fade" tabindex="-1" role="dialog" id="addImageModal">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Upload Image</h4>
			</div>
			<div class="modal-body">
				<div id="content" class="form-content">
					<div class="basic-form">
						<c:url value="/admin/article/create-image" var="createURL">
							<c:param name="${_csrf.parameterName}" value="${_csrf.token}" />
						</c:url>
						<form:form method="POST" action="${createURL}"
							modelAttribute="articleImageDTO" enctype="multipart/form-data">
							<form:input path="articleId" type="hidden" value="${articleDTO.article.id}"/>
							<h5>
								<spring:message code="image" />
							</h5>
							<div>
								<label for="images">
									<c:url var="img" value="/resources/images/placeholder.png" />
									<img width="50%" style="margin: 0px auto" src="${img}"
										id="imagePlaceholder">
								</label>
								<form:input id="images" type="file" path="image"
									cssClass="hidden" onchange="fileSelected(this)" />
							</div>
							<p class="error-message">
								<form:errors path="image" />
							</p>
							<h5>
								<spring:message code="sequence" />
							</h5>
							<form:input path="sequence" type="text"
								placeholder="1000" />
							<p class="error-message">
								<form:errors path="sequence" />
							</p>
							<p class="error-message">
								<form:errors path="" />
							</p>
							<spring:message code="create" var="create" />
							<button type="submit" class="btn btn-primary"
								class="action-button">${create}</button>
						</form:form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
