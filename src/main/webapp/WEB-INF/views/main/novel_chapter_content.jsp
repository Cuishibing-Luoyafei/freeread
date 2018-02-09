<div class="panel panel-default">
    <div class="panel-heading">
        <div class="panel-title">
            <label>${novelChapter.novelChapterName}</label>
            <a style="float:right"
               href="${pageContext.request.contextPath}/novelChapter/novelChapterList?novelId=${novelChapter.novelId }">返回章节列表</a>
            <a href="${pageContext.request.contextPath}/secretNovel/addSecretNovel?novelId=${novelChapter.novelId }"
               style="float:right;position:relative;left:-10px">加入书架</a>
        </div>
    </div>
    <div class="panel-body">
        ${novelChapter.novelChapterContent}
    </div>
</div>
<div>
    <ul class="pager">
        <li>
            <a href="${pageContext.request.contextPath}/novelChapter/novelChapter?novelId=${novelChapter.novelId}&chapterIndex=${novelChapter.novelChapterIndex-1}">上一章</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/novelChapter/novelChapter?novelId=${novelChapter.novelId}&chapterIndex=${novelChapter.novelChapterIndex+1}">下一章</a>
        </li>
    </ul>
</div>