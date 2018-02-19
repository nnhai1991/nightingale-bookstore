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
		<section id="main-panel">
			<div id="header">
				<h4><spring:message code="administration" /></h4>
			</div>
			<!--  For notification as feedback from create, update and delete -->
			<div id="notification" class="bg-green"></div>
			<div id="content-title-area">
				<h3>
					<spring:message code="add_image" /> ${articleDTO.article.name}
				</h3>
			</div>
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
								<label for="image">
									<c:url var="img" value="/resources/images/placeholder.png" />
									<img width="50%" style="margin: 0px auto" src="${img}" id="imagePlaceholder">
								</label>
								<form:input id="image" type="file" path="image"
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
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>

<script type="text/javascript">
	function fileSelected(input) {
		var fr = new FileReader();

		fr.readAsDataURL(input.files[0]);

		fr.onload = function(e) {
			document.getElementById('imagePlaceholder').src = this.result
		}
	}
</script>
