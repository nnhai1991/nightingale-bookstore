<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<tiles:insertDefinition name="admin-template">
    <tiles:putAttribute name="body">
			<div class="container-fluid">
				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item">Dashboard</li>
					<li class="breadcrumb-item active">My Dashboard</li>
				</ol>
				
				<!-- Content -->
		      	
		      	<h2>Shop Dashboard Admin Page</h2>
		      	
				<p>Currently still in development phase.</p>
				<button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo">TODO</button>
				<div id="demo" class="collapse">
				    <ul>
				    	<li>Multi language</li>
				    	<li>Add authorization checking</li>
				    </ul>
				</div>
				
			</div>

			


	</tiles:putAttribute>
</tiles:insertDefinition>
