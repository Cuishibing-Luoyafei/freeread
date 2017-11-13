<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">推荐</h3>
	</div>
	<div class="panel-body">
		<c:set var="recommendNovels" value="${pageRecommendNovels.getContent()}"/>
		<c:set var="startIndex" value="0" />
		<c:set var="endIndex" value="${recommendNovels.size()-1}" />
		<c:set var="step" value="4" />
		<c:if test="${endIndex >= 0 }">
			<div class="table-responsive">
				<table class="table">
					<c:forEach items="${recommendNovels}" begin="${startIndex}"
						end="${endIndex }" step="${step }" varStatus="status">
						<tr>
							<c:choose>
								<c:when test="${status.index + step > endIndex }">
									<c:set var="sEndIndex" value="${endIndex}" />
								</c:when>
								<c:otherwise>
									<c:set var="sEndIndex" value="${status.index + step - 1}" />
								</c:otherwise>
							</c:choose>
							<c:forEach items="${recommendNovels }" var="novel"
								begin="${status.index }" end="${sEndIndex }" step="1"
								varStatus="status1">
								<td>
									<div>
										<div>
											<img alt=""
												src="https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2559880466,1764208247&fm=58"
												width="150" height="200">
										</div>
										<div>
											<label style="width: 150"><small><a
													href="${pageContext.request.contextPath }/novelDeatil?novelId=${novel.novelId }">${novel.novelName }</a></small></label>
										</div>
									</div>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</div>

<div align="center">
	<ul class="pagination">
		<c:set var="showPageRange" value="3"/><!-- 显示页的范围,当前页左边显示3页,右边显示3页 -->
		<c:if test="${pageRecommendNovels.hasContent() == true }">
			<c:if test="${pageRecommendNovels.hasPrevious()}">
				<li><a
					href="${pageContext.request.contextPath }/recomend?page=${pageRecommendNovels.getNumber()-1 }&size=${pageRecommendNovels.getSize()}">&laquo;</a></li>
			</c:if>
			<c:set var="startIndex" value="0" />
			<c:if test="${pageRecommendNovels.getNumber() - showPageRange >= 0}">
				<c:set var="startIndex"
					value="${pageRecommendNovels.getNumber() - showPageRange}" />
			</c:if>
			<c:set var="endIndex"
				value="${pageRecommendNovels.getNumber() + showPageRange }" />
			<c:if test="${endIndex > pageRecommendNovels.getTotalPages() - 1}">
				<c:set var="endIndex"
					value="${pageRecommendNovels.getTotalPages() - 1}" />
			</c:if>
			<c:forEach begin="${startIndex }" end="${endIndex }" step="1"
				varStatus="index">
				<c:choose>
					<c:when test="${index.index == pageRecommendNovels.getNumber()}">
						<li class="active"><a
							href="#">${index.index + 1}</a></li>
					</c:when>
					<c:otherwise>
						<li><a
							href="${pageContext.request.contextPath }/recomend?page=${index.index }&size=${pageRecommendNovels.getSize()}">${index.index + 1}</a></li>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${pageRecommendNovels.hasNext() }">
				<li><a
					href="${pageContext.request.contextPath }/recomend?page=${pageRecommendNovels.getNumber()+1 }&size=${pageRecommendNovels.getSize()}">&raquo;</a></li>
			</c:if>
		</c:if>
	</ul>
</div>