<ul>
    <c:forEach items="${secretNovels}" var="secretNovel">
        <li><a href="${pageContext.request.contextPath}/novelDetails?novelId=${secretNovel.novelId}">${secretNovel.novelName}</a></li>
    </c:forEach>
</ul>