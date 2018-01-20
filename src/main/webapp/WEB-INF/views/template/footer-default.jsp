<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 id="modal-title" class="modal-title">Message</h4>
			</div>
			<div class="modal-body">
				<p id="modal-message">
					<c:if test="${not empty modalMessage}">
						<spring:message code="${modalMessage}" />
					</c:if>
				</p>
			</div>
			<div class="modal-footer">
				<button id="btnModalCLose" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		if ('${modalMessage}' != null && '${modalMessage}' != "") {
			$('#myModal').modal({
				backdrop : 'static',
				keyboard : false
			})
		}

	});
</script>

