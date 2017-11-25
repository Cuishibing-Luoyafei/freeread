<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">
			小说详情
		</h3>
	</div>
	<div class="panel-body">
		<form class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label">名称</label>
				<div class="col-sm-10">
					<lable class="form-control" id="novelName">${novelHead.novelName }</lable>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">作者</label>
				<div class="col-sm-10">
					<lable class="form-control" id="author">${novelHead.novelAuthor }</lable>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">简介</label>
				<div class="col-sm-10">
					<%--<lable class="form-control" id="desc">${novelHead.novelDesc }</lable>--%>
					<textarea class="form-control" id="desc" rows="10">${novelHead.novelDesc}
					</textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a href="${pageContext.request.contextPath }/novelChapterList?novelId=${novelHead.novelId }">开始阅读</a>
				</div>
			</div>
		</form>
	</div>
</div>