<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <label>我的收藏</label>
        </div>
    </div>
    <div class="panel-body">
        <ul class="list-group">
            <c:forEach items="${secretNovels}" var="secretNovel">
                <li class="list-group-item"><a href="${pageContext.request.contextPath}/novelDetails?novelId=${secretNovel.novelId}">${secretNovel.novelName}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>