<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">${novelChapter.novelChapterName}</h3>
    </div>
    <div class="panel-body">
        ${novelChapter.novelChapterContent}
    </div>
</div>
<div>
    <ul class="pager">
        <li>
            <a href="${pageContext.request.contextPath}/novelChapter?novelId=${novelChapter.novelId}&chapterIndex=${novelChapter.novelChapterIndex-1}">Previous</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/novelChapter?novelId=${novelChapter.novelId}&chapterIndex=${novelChapter.novelChapterIndex+1}">Next</a>
        </li>
    </ul>
</div>