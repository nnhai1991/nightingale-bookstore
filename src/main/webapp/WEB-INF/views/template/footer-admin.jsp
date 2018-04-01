<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<footer>

	<!-- Logout Modal-->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to
						Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are
					ready to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

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
	
</footer>

<script type="text/javascript">
	$(document).ready(function() {
		if ('${modalMessage}' != null && '${modalMessage}' != "") {
			$('#myModal').modal({
				backdrop : 'static',
				keyboard : false
			})
		}
		
		if($('img').length > 1) {
			$('.disclaimers').removeClass("hide");
		}
		
	});
</script>
