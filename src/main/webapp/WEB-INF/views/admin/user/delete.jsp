<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<tiles:insertDefinition name="admin">
    <tiles:putAttribute name="body">
        <section id="main-panel">
            <div id="header">
                <h4>
                    <spring:message code="administration"/>
                </h4>
            </div>
            <!--  For notification as feedback from create, update and delete -->
            <div id="notification" class="bg-green"></div>
            <div id="content-title-area">
                <h3>
                    <spring:message code="delete_user"/>
                </h3>
            </div>
            <div id="content">
                <div class="detail-view">
                    <dl>

                        <dt>
                            <spring:message code="email"/>
                        </dt>
                        <dd>
                            <c:out value="${userDTO.user.email}"/>
                        </dd>

                        <dt>
                            <spring:message code="role"/>
                        </dt>
                        <dd>
                            <c:out value="${userDTO.role.name}"/>
                        </dd>

                        <dt>
                            <spring:message code="shop"/>
                        </dt>
                        <dd>
                            <c:out value="${userDTO.shop.name}"/>
                        </dd>

                        <dt>
                            <spring:message code="first_name"/>
                        </dt>
                        <dd>
                            <c:out value="${userDTO.user.firstName}"/>
                        </dd>

                        <dt>
                            <spring:message code="last_name"/>
                        </dt>
                        <dd>
                            <c:out value="${userDTO.user.lastName}"/>
                        </dd>

                        <dt>
                            <spring:message code="status"/>
                        </dt>
                        <dd>
                            <c:choose>
                                <c:when test="${userDTO.user.enabled}">
                                    <spring:message code="active"/>
                                </c:when>
                                <c:otherwise>
                                    <spring:message code="inactive"/>
                                </c:otherwise>
                            </c:choose>
                        </dd>


                        <dt>
                            <spring:message code="lock_status"/>
                        </dt>
                        <dd>
                            <c:choose>
                                <c:when test="${userDTO.user.notLocked}">
                                    <spring:message code="unlocked"/>
                                </c:when>
                                <c:otherwise>
                                    <spring:message code="locked"/>
                                </c:otherwise>
                            </c:choose>
                        </dd>

                    </dl>


                    <form:form method="POST" modelAttribute="user">
                        <form:input type="hidden" path="id"/>

                        <spring:message code="delete" var="delete"/>

						<c:url var="homeUrl" value="/user" />
						<a class="action-button" href="${homeUrl}"> <spring:message
								code="back_to_listing" />
						</a>
                        <button type="submit" class="action-button bg-red">
                                ${delete}</button>
                    </form:form>

                </div>
            </div>
        </section>
    </tiles:putAttribute>
</tiles:insertDefinition>
