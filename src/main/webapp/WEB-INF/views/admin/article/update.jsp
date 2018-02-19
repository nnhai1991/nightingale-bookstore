<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
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
				<li class="breadcrumb-item"><spring:message code="article" /></li>
				<li class="breadcrumb-item active"><spring:message
						code="update" /></li>
			</ol>
			<c:url value="/admin/article/update" var="updateURL">
				<c:param name="${_csrf.parameterName}" value="${_csrf.token}" />
			</c:url>
			<div class="panel panel-default">
				<div class="panel-body">
					<form:form method="POST" action="${updateURL}"
						modelAttribute="articleDTO.article">
						<form:input type="hidden" path="id" />
						<c:import url="form.jsp"></c:import>
						<c:forEach items="${articleDTO.articleImages}" var="image">
							<div>
								<h5>
									<spring:message code="sequence" />
									: ${image.sequence}
								</h5>
								<c:url value="/public/${image.assetId}" var="img" />
								<c:url var="updateImage" value="/admin/article-image/update">
									<c:param name="articleImageId" value="${image.id}" />
								</c:url>
								<c:url var="deleteImage" value="/admin/article-image/delete">
									<c:param name="articleImageId" value="${image.id}" />
								</c:url>
								<a class="action-button" href="${updateImage}"
									style="position: absolute; margin-top: 10px; margin-left: 10px; opacity: 0.8;">
									<span class="glyphicon glyphicon-pencil"></span>
								</a>
								<a class="action-button" href="${deleteImage}"
									style="position: absolute; margin-top: 60px; margin-left: 10px; opacity: 0.8;">
									<span class="glyphicon glyphicon-trash"></span>
								</a>
								<img src="${img}" height="200px">
							</div>
						</c:forEach>
						<div class="form-group">
							<c:url var="homeUrl" value="/admin/article" />
							<a class="btn btn-default" href="${homeUrl}">
								<spring:message code="back_to_listing" />
							</a>
							<button class="btn btn-primary" type="button"
								class="action-button" onclick="showAddImage()">
								<spring:message code="add_image" />
							</button>
							<button class="btn btn-primary" type="submit"
								class="action-button">
								<spring:message code="update" />
							</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
<c:import url="add-image-modal.jsp"></c:import>

<script type="text/javascript">
	function showAddImage(){
		$("#addImageModal").modal("show");
	}
	function fileSelected(input) {
		var fr = new FileReader();

		fr.readAsDataURL(input.files[0]);

		fr.onload = function(e) {
			document.getElementById('imagePlaceholder').src = this.result
		}
	}
</script>