<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">章节列表</h3>
	</div>
	<div class="panel-body">
		<div class="row">
			<c:forEach items="${pageNovelContents.getContent()}" var="novelContent">
			<span class="col-md-4 col-sm-6">
				<label style="min-width: 190px">
					<small>
						<a href="${pageContext.request.contextPath }/novelChapter/novelChapter?novelId=${novelContent.novelId}&chapterIndex=${novelContent.novelChapterIndex}">${novelContent.novelChapterName }</a>
					</small>
				</label>
			</span>
			</c:forEach>
		</div>
	</div>
</div>

<div align="center">
	<ul class="pagination">
		<!-- 暫時設定為3 -->
		<c:set var="showPageRange" value="6" />
		<!-- 显示页的范围,当前页左边显示3页,右边显示3页 -->
		<c:if test="${pageNovelContents.hasContent() == true}">
			<c:if test="${pageNovelContents.hasPrevious()}">
				<li><a
						href="novelChapterList?novelId=${pageNovelContents.getContent().get(0).novelId }&page=${pageNovelContents.getNumber()-1 }&size=${pageNovelContents.getSize()}">&laquo;</a></li>
			</c:if>
			<c:set var="startIndex" value="0" />
			<c:if test="${pageNovelContents.getNumber() - showPageRange >= 0}">
				<c:set var="startIndex"
					   value="${pageNovelContents.getNumber() - showPageRange}" />
			</c:if>
			<c:set var="endIndex"
				   value="${pageNovelContents.getNumber() + showPageRange }" />
			<c:if test="${endIndex > pageNovelContents.getTotalPages() - 1}">
				<c:set var="endIndex"
					   value="${pageNovelContents.getTotalPages() - 1}" />
			</c:if>
			<c:forEach
					begin="${startIndex }" end="${endIndex }" step="1"
					varStatus="index">
				<c:choose>
					<c:when test="${index.index == pageNovelContents.getNumber()}">
						<li class="active"><a href="#">${index.index + 1}</a></li>
					</c:when>
					<c:otherwise>
						<li><a
                                href="${pageContext.request.contextPath}/novelChapter/novelChapterList?novelId=${pageNovelContents.getContent().get(0).novelId }&page=${index.index }&size=${pageNovelContents.getSize()}">${index.index + 1}</a>
                        </li>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${pageNovelContents.hasNext() }">
				<li><a
                        href="${pageContext.request.contextPath}/novelChapter/novelChapterList?novelId=${pageNovelContents.getContent().get(0).novelId }&page=${pageNovelContents.getNumber()+1 }&size=${pageNovelContents.getSize()}">&raquo;</a>
                </li>
			</c:if>
		</c:if>
	</ul>
</div>