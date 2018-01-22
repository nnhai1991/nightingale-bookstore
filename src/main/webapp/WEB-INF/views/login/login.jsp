<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<tiles:insertDefinition name="default-template">
    <tiles:putAttribute name="body">

        <c:url value="/loginProcess" var="url"/>

        <div class="container">
            <div class="logo">
                <c:url var="imgSrc" value="/resources/images/logo.png"/>
                <img src="${imgSrc}" alt="logo"/>
            </div>

            <div class="page-title">Nightingale Bookstore</div>

            <div class="content">

                <span class="fa fa-lock big-icon"></span>

                <form:form method="post" class="login-form" action="${url}">
                    <div class="form-group left">
                        <label for="email"><spring:message code="email" text="Email"/></label>
                        <input type="text" name="email" class="form-username form-control" id="email">

                    </div>

                    <div class="form-group left">
                        <label for="email"><spring:message code="password" text="Password"/></label>
                        <input type="password" name="password" class="form-password form-control" id="password">


                    </div>

                    <button type="submit" class="submit-button"><spring:message code="login" text="Login"/>
                    </button>
                </form:form>
            </div>
        </div>


    </tiles:putAttribute>
</tiles:insertDefinition>


