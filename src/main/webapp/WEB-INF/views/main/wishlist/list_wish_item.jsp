<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            我的心愿单
        </h3>
    </div>
    <div class="panel-body">
        <ul class="list-group">
            <c:forEach items="${wishItems.getContent()}" var="wishItem">
                <li class="list-group-item">${wishItem.novelName}</li>
            </c:forEach>
        </ul>
    </div>
</div>