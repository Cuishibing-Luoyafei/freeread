<form class="form-horizontal" role="form">
  <div class="form-group">
    <label for="novelName" class="col-sm-2 control-label">名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="novelName" disabled="disabled" value="${novelHead.novelName }">
    </div>
  </div>
  <div class="form-group">
    <label for="author" class="col-sm-2 control-label">作者</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="author" disabled="disabled" value="${novelHead.novelAuthor }"/>
    </div>
  </div>
  <div class="form-group">
    <label for="desc" class="col-sm-2 control-label">简介</label>
    <div class="col-sm-10">
      <textarea rows="" cols="" class="form-control" id="desc">${novelHead.novelDesc }</textarea>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">开始阅读</button>
    </div>
  </div>
</form>