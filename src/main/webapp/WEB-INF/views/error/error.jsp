<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tiles:insertDefinition name="default-template">
	<tiles:putAttribute name="body">
		<section class="screen-height">
			<div class="page-title container text-align-center">
				<h1><spring:message code="something_went_wrong" /></h1>
				<c:url value="/" var="url" />
				<p>
					<spring:message code="something_went_wrong_message" arguments="${url}" htmlEscape="false" />
				</p>
			</div>
		</section>
	</tiles:putAttribute>
</tiles:insertDefinition>