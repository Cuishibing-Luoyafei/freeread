<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">排行榜</h3>
	</div>
	<div class="panel-body">
		<ul class="list-group">
			<c:forEach items="${pagePopularityNovels.getContent() }" var="novelHeads">
				<li class="list-group-item">
					<a href="${pageContext.request.contextPath}/novelDetails?novelId=${novelHeads.novelId }">${novelHeads.novelName}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>