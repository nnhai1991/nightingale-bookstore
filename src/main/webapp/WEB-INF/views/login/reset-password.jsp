<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<tiles:insertDefinition name="default-template">
	<tiles:putAttribute name="body">
		<div class="top-content">

			<div class="inner-bg">
				<div class="container">
					<div class="row">
						<div class="col-sm-8 col-sm-offset-2 text">
							<h1>
								<spring:message code="project_title"></spring:message>
							</h1>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6 col-sm-offset-3 form-box">
							<div class="form-top">
								<div class="form-top-left">
									<h3><spring:message code="change_password"/></h3>
									<p>Enter your email and new password to:</p>
								</div>
								<div class="form-top-right">
									<i class="fa fa-lock"></i>
								</div>
							</div>
							<div class="form-bottom">
								<form:form method="POST" modelAttribute="userForUpdatePassword">
									<form:input path="id" type="hidden" readonly="true" />
									<div class="form-group row">
										<label class="col-2 col-form-label">
											<spring:message code="email" />
										</label>
										<div class="col-6">
											<spring:message code="email_placeholder"
												var="emailPlaceholder" />
											<form:input path="email" type="text" class="form-control"
												placeholder="${emailPlaceholder}" />

											<p class="error-message">
												<form:errors path="email" />
											</p>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-2 col-form-label">
											<spring:message code="password" />
										</label>
										<div class="col-6">
											<spring:message code="password" var="passwordPlaceholder" />
											<form:input path="password" type="password"
												class="form-control" placeholder="${passwordPlaceholder}" />

											<p class="error-message">
												<form:errors path="password" htmlEscape="false" />
											</p>

										</div>
									</div>
									<div class="form-group row">
										<label class="col-2 col-form-label">
											<spring:message code="verify_password" />
										</label>
										<div class="col-6">
											<spring:message code="verify_password"
												var="verifyPasswordPlaceholder" />
											<form:input path="passwordConfirm" type="password"
												class="form-control"
												placeholder="${verifyPasswordPlaceholder}" />

											<p class="error-message">
												<form:errors path="passwordConfirm" />
											</p>

											<p class="error-message">
												<form:errors path="" />
											</p>
										</div>
									</div>
									<button type="submit" class="btn btn-outline-primary">
										<spring:message code="change_password" />

									</button>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>

