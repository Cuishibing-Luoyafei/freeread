<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            我的心愿单
        </h3>
    </div>
    <div class="panel-body">
        <ul class="list-group">
            <c:forEach items="${wishItems.getContent()}" var="wishItem">
                <li class="list-group-item">
                    <label>${wishItem.novelName}</label>
                    <a href="${pageContext.request.contextPath}/wishlist/removeWishItem?userName=${wishItem.userName}&userEmail=${wishItem.userEmail}&novelName=${wishItem.novelName}" style="float:right">删除</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>