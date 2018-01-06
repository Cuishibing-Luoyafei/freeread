<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">搜索结果</h3>
    </div>
    <div class="panel-body">
        <div class="row">
            <c:forEach items="${searchResult.getContent()}" var="novel">
			<span class="col-md-2" style="display:inline-block">

					<div>
						<img alt=""
                             src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515286698498&di=0dc59b4137ca5b4bf0123aab250136e8&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fwh%253D450%252C600%2Fsign%3Da8db9b0f6c061d957d133f3c4ec426e7%2Fdcc451da81cb39dbb7278561d2160924ab183025.jpg"
                             width="150px" height="200px">
					</div>
					<div>
						<label style="width: 150px;position: relative;bottom:100px"><small><a
                                href="${pageContext.request.contextPath }/novelHead/novelDetails?novelId=${novel.novelId }">${novel.novelName }</a>
						</small>
						</label>
					</div>

			</span>
            </c:forEach>
            <c:if test="${searchResult.hasContent() == false}">
                需要订阅该小说吗?本站收录该小说时会以邮件通知您!
            </c:if>
        </div>
    </div>
</div>

<div align="center">
    <ul class="pagination">
        <c:set var="showPageRange" value="3"/><!-- 显示页的范围,当前页左边显示3页,右边显示3页 -->
        <c:if test="${searchResult.hasContent() == true }">
            <c:if test="${searchResult.hasPrevious()}">
                <li><a
                        href="${pageContext.request.contextPath }/novelHead/recommend?page=${searchResult.getNumber()-1 }&size=${searchResult.getSize()}&className=${className}">&laquo;</a>
                </li>
            </c:if>
            <c:set var="startIndex" value="0"/>
            <c:if test="${searchResult.getNumber() - showPageRange >= 0}">
                <c:set var="startIndex"
                       value="${searchResult.getNumber() - showPageRange}"/>
            </c:if>
            <c:set var="endIndex"
                   value="${searchResult.getNumber() + showPageRange }"/>
            <c:if test="${endIndex > searchResult.getTotalPages() - 1}">
                <c:set var="endIndex"
                       value="${searchResult.getTotalPages() - 1}"/>
            </c:if>
            <c:forEach begin="${startIndex }" end="${endIndex }" step="1"
                       varStatus="index">
                <c:choose>
                    <c:when test="${index.index == searchResult.getNumber()}">
                        <li class="active"><a
                                href="#">${index.index + 1}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a
                                href="${pageContext.request.contextPath }/novelHead/recommend?page=${index.index }&size=${searchResult.getSize()}&className=${className}">${index.index + 1}</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            <c:if test="${searchResult.hasNext() }">
                <li><a
                        href="${pageContext.request.contextPath }/novelHead/recommend?page=${searchResult.getNumber()+1 }&size=${searchResult.getSize()}&className=${className}">&raquo;</a>
                </li>
            </c:if>
        </c:if>
    </ul>
</div>