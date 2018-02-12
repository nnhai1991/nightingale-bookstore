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

		<div class="container-fluid">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"
						class="active"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				</ol>

				<!-- Wrapper for slides -->
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img src="<c:url value="/resources/images/carousel_1.jpeg" />">
						<div class="carousel-caption"></div>
					</div>
					<div class="item">
						<img src="<c:url value="/resources/images/carousel_2.jpeg" />">
						<div class="carousel-caption"></div>
					</div>
					<div class="item">
						<img src="<c:url value="/resources/images/carousel_3.jpeg" />">
						<div class="carousel-caption"></div>
					</div>
				</div>

				<!-- Controls -->
			</div>
		</div>
		<br />
		<br />
		<br />
		<div class="container">
			<h2>Latest Books</h2>
			<div class="row">
				<div class="col-sm-3">
					<img src="<c:url value="/resources/images/sample_1.jpg" />"
						width="100%">
					<h3>Sample Book 1</h3>
				</div>
				<div class="col-sm-3">
					<img src="<c:url value="/resources/images/sample_2.jpg" />"
						width="100%">
					<h3>Sample Book 2</h3>
				</div>
				<div class="col-sm-3">
					<img src="<c:url value="/resources/images/sample_3.jpg" />"
						width="100%">
					<h3>Sample Book 3</h3>
				</div>
				<div class="col-sm-3">
					<img src="<c:url value="/resources/images/sample_4.jpg" />"
						width="100%">
					<h3>Sample Book 4</h3>
				</div>
			</div>
		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>


