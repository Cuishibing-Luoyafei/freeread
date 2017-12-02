<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">章节列表</h3>
	</div>
	<div class="panel-body">
		<c:set var="novelContents" value="${pageNovelContents.getContent()}" />
		<c:set var="startIndex" value="0" />
		<c:set var="endIndex" value="${novelContents.size()-1}" />
		<c:set var="step" value="4" />
		<c:if test="${endIndex >= 0 }">
			<div class="table-responsive">
				<table class="table">
					<c:forEach items="${novelContents}" begin="${startIndex}"
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
							<c:forEach items="${novelContents }" var="novelContent"
								begin="${status.index }" end="${sEndIndex }" step="1"
								varStatus="status1">
								<td>
									<div>
										<div>
											<label style="width: 150"><small><a
                                                    href="${pageContext.request.contextPath }/novelChapter?novelId=${novelContent.novelId}&chapterIndex=${novelContent.novelChapterIndex}">${novelContent.novelChapterName }</a>
                                            </small>
                                            </label>
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
		<!-- 暫時設定為3 -->
		<c:set var="showPageRange" value="3" />
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
							href="novelChapterList?novelId=${pageNovelContents.getContent().get(0).novelId }&page=${index.index }&size=${pageNovelContents.getSize()}">${index.index + 1}</a></li>
					</c:otherwise>
				</c:choose>

			</c:forEach>
			<c:if test="${pageNovelContents.hasNext() }">
				<li><a
					href="novelChapterList?novelId=${pageNovelContents.getContent().get(0).novelId }&page=${pageNovelContents.getNumber()+1 }&size=${pageNovelContents.getSize()}">&raquo;</a></li>
			</c:if>
		</c:if>
	</ul>
</div>