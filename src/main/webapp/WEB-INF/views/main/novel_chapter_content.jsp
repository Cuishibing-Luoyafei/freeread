<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">${novelContent.novelChapterName}</h3>
    </div>
    <div class="panel-body">
        ${novelContent.novelChapterContent}
    </div>
</div>
<div>
    <ul class="pager">
        <li><a href="${pageContext.request.contextPath}/novelContent?novelId=${novelContent.novelId}&chapterIndex=${novelContent.novelChapterIndex-1}">Previous</a></li>
        <li><a href="${pageContext.request.contextPath}/novelContent?novelId=${novelContent.novelId}&chapterIndex=${novelContent.novelChapterIndex+1}">Next</a></li>
    </ul>
</div>