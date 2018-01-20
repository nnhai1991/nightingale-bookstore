<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<!DOCTYPE html>
<html>

	<head>
	    <title><spring:message code="project_title"/></title>
	    <tiles:insertAttribute name="htmlhead"/>
	    
	</head>

	
	<body>
		<tiles:insertAttribute name="body"/>
		<tiles:insertAttribute name="footer"/>
	</body>

</html>