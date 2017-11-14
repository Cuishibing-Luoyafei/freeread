<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#example-navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath }/">FREE READ</a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav">
                <c:forEach items="${allNovelClasses}" var="classes">
                    <li><a href="#">${classes.novelClassName}</a></li>
                </c:forEach>
            </ul>
            <div>
                <form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath}/searchNovelByName" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" name="searchNovelName">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
        </div>
    </div>
</nav>