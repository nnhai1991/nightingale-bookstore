<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css" rel="stylesheet" media="all">
        *:not(br):not(tr):not(html) {
            font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            color: #333;
        }

        body {
            width: 100% !important;
            height: 100%;
            margin: 0;
            line-height: 1.4;
            /*background-color: #F2F4F6;*/
            /*color: #74787E;*/
        }

        .action-button {
            text-transform: uppercase;
            display: inline-block;
            padding: 10px 30px;
            border-radius: 30px;
            background-color: #1428A0;
            /* Samsung blue */
            color: #FFF;
            margin-top: 20px;
            text-align: center;
            text-decoration: none;
            font-weight: 700;
            letter-spacing: 1px;
            font-size: 14px;
        }


    </style>
</head>

<body>
<div>
    <spring:message code="greeting_message" htmlEscape="false" arguments="${name}"/>
    <br/><br/>

    <spring:message code="we_received_a_request"/>
    <br/><br/>
    <spring:message code="click_to_reset"/>
    <br/><br/>

    <c:url var="verifyUrl" value="${emailBaseUrl}/reset-password">
        <c:param name="token" value="${token}"/>
    </c:url>
    <a class="action-button" href="${verifyUrl}">
        <spring:message code="reset_password"/>
    </a>
    <br/><br/>

    <spring:message code="if_you_did_not_request"/>
    <br/><br/>

    <spring:message code="email_signature"/>
    <br/>
    <spring:message code="email_signature_name"/>
</div>
</body>

</html>
