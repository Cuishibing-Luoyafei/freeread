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
                             src="https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2559880466,1764208247&fm=58"
                             width="150" height="200">
					</div>
					<div>
						<label style="width: 150px"><small><a
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