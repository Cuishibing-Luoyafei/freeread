<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">推荐</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<c:forEach items="${pageRecommendNovels.getContent()}" var="novel">
			<span class="col-md-2" style="display:inline-block" >

					<div>
						<img alt=""
							 src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515286698498&di=0dc59b4137ca5b4bf0123aab250136e8&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3Da8db9b0f6c061d957d133f3c4ec426e7%2Fdcc451da81cb39dbb7278561d2160924ab183025.jpg"
							 width="150px" height="200px">
						<%--<label style="position: relative;bottom:100px">${novel.novelName}</label>--%>
					</div>
					<div>
						<label style="width: 150px;position: relative;bottom:100px"><small><a
                                href="${pageContext.request.contextPath }/novelHead/novelDetails?novelId=${novel.novelId }">${novel.novelName }</a>
						</small>
						</label>
					</div>

			</span>
			</c:forEach>
		</div>
	</div>
</div>

<div align="center">
	<ul class="pagination">
		<c:set var="showPageRange" value="3"/><!-- 显示页的范围,当前页左边显示3页,右边显示3页 -->
		<c:if test="${pageRecommendNovels.hasContent() == true }">
			<c:if test="${pageRecommendNovels.hasPrevious()}">
				<li><a
                        href="${pageContext.request.contextPath }/novelHead/recommend?page=${pageRecommendNovels.getNumber()-1 }&size=${pageRecommendNovels.getSize()}&className=${className}">&laquo;</a>
				</li>
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
                                href="${pageContext.request.contextPath }/novelHead/recommend?page=${index.index }&size=${pageRecommendNovels.getSize()}&className=${className}">${index.index + 1}</a>
						</li>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${pageRecommendNovels.hasNext() }">
				<li><a
                        href="${pageContext.request.contextPath }/novelHead/recommend?page=${pageRecommendNovels.getNumber()+1 }&size=${pageRecommendNovels.getSize()}&className=${className}">&raquo;</a>
				</li>
			</c:if>
		</c:if>
	</ul>
</div>