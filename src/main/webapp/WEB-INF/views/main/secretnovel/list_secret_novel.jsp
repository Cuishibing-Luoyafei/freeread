<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <label>我的收藏</label>
        </div>
    </div>
    <div class="panel-body">
        <ul class="list-group">
            <c:forEach items="${secretNovels}" var="secretNovel">
                <c:choose>
                    <c:when test="${secretNovel.outOfStock == true}">
                        <li class="list-group-item"
                            style="background-color: grey">
                            <label>${secretNovel.novelName}</label>
                            <a style="float:right"
                               href="${pageContext.request.contextPath}/secretNovel/removeSecretNovel?novelId=${secretNovel.novelId}">删除</a>
                            <label style="float:right;margin-right: 20px;">已下架</label>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="list-group-item">
                            <a href="${pageContext.request.contextPath}/novelHead/novelDetails?novelId=${secretNovel.novelId}">${secretNovel.novelName}</a>
                            <a style="float:right"
                               href="${pageContext.request.contextPath}/secretNovel/removeSecretNovel?novelId=${secretNovel.novelId}">删除</a>
                            <a style="float:right;margin-right:30px"
                               href="${pageContext.request.contextPath }/novelChapter/novelChapter?novelId=${secretNovel.novelId}&chapterIndex=${secretNovel.lastReadChapter}">继续阅读</a>
                        </li>

                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</div>