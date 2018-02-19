<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<tiles:insertDefinition name="admin">
	<tiles:putAttribute name="body">
		<section id="main-panel">
			<div id="header">
				<h4>
					<spring:message code="administration" />
				</h4>
			</div>
			<!--  For notification as feedback from create, update and delete -->
			<div id="notification" class="bg-green"></div>
			<div id="content-title-area">
				<h3>
					<spring:message code="update_article" />
				</h3>
			</div>
			<div id="content" class="form-content">
				<div class="basic-form">
					<c:url value="/admin/article-image/update" var="updateURL">
						<c:param name="${_csrf.parameterName}" value="${_csrf.token}" />
					</c:url>

					<form:form method="POST" action="${updateURL}"
						modelAttribute="articleImageDTO" enctype="multipart/form-data">

						<form:input type="hidden" path="articleImage.id" />
						<form:input type="hidden" path="articleImage.articleId" />
						
						<h5>
							<spring:message code="image" />
						</h5>

						<div>
							<form:input path="articleImage.assetId" type="hidden" />
							<label for="images"> <c:url
									value="/public/${articleImageDTO.articleImage.assetId}" var="img" /> <img
								src="${img}" id="imagePlaceholder">
							</label>

							<form:input id="images" type="file" path="image"
								 onchange="fileSelected(this)" />
						</div>

						<p class="error-message">
							<form:errors path="image" />
						</p>

						
						<h5>
							<spring:message code="sequence" />
						</h5>

						<form:input path="articleImage.sequence" type="text" placeholder="1000" />

						<p class="error-message">
							<form:errors path="articleImage.sequence" />
						</p>

						<p class="error-message">
							<form:errors path="article" />
						</p>

						<c:set var="update">
							<spring:message code="update" />
						</c:set>
						<c:url var="homeUrl" value="/admin/article/update">
                       		<c:param name="articleId" value="${articleImageDTO.article.id}" />
                        </c:url>
                        <a class="action-button" href="${homeUrl}">
                            <spring:message code="back_to_listing"/>
                        </a>
						<button type="submit" class="action-button">${update}</button>

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
