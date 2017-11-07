<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">推荐</h3>
	</div>
	<div class="panel-body">
		<c:set var="startIndex" value="0" />
		<c:set var="endIndex" value="${recommendNovels.size()-1}" />
		<c:set var="step" value="4" />
		<c:if test="${endIndex >= 0 }">
			<div class="table-responsive">
				<table class="table">
					<c:forEach items="${recommendNovels}" begin="${startIndex}"
						end="${endIndex }" step="${step }" varStatus="status">
						<tr>
							<c:choose>
								<c:when test="${status.index + step > endIndex }">
									<c:set var="sEndIndex" value="${endIndex}" />
								</c:when>
								<c:otherwise>
									<c:set var="sEndIndex" value="${status.index + step - 1}" />
								</c:otherwise>
							</c:choose>
							<c:forEach items="${recommendNovels }" var="novel"
								begin="${status.index }" end="${sEndIndex }" step="1"
								varStatus="status1">
								<td>
									<div>
										<div>
											<img alt=""
												src="https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2559880466,1764208247&fm=58"
												width="150" height="200">
										</div>
										<div>
											<label style="width: 150"><small><a
													href="novelDeatil?novelId=${novel.novelId }">${novel.novelName }</a></small></label>
										</div>
									</div>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
	</div>
</div>

<div align="center">
	<ul class="pagination">
		<li><a href="#">&laquo;</a></li>
		<li><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#">&raquo;</a></li>
	</ul>
</div>