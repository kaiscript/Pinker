<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<%@ include file="../head.html"%>
<script type="text/javascript">
	function submitPager(index) {
		$(document).ready(function() {
			$("#pagerForm").attr("action", "admin/report/list?pageIndex=" + index);
			$("#pagerForm").submit();
		});

	}
	/**
	 * 获取id为indexA的标签<a> 的页码。-1为上一页，+1下一页
	 */
	function previousPage() {
		submitPager($("#indexA").text() - 1);
	}

	function nextPage() {
		submitPager(parseInt($("#indexA").text()) + 1);
	}
	
	function deleteComment(id,commentId){
		
		$.ajax({
			type : 'POST',
			url : 'admin/report/delete/comment'+commentId,
			data : {
			},
			dateType : 'json',
			success : function(data) {
				if(data.code==0){
					alert("删除评论成功");
					$("#"+id).slideUp("slow");
				}
				
			}
		});
	}
</script>

</head>
<body>
	<!-- 引入顶部导航栏 -->
	<%@ include file="../nav.html"%>
	<!--按需增侧边导航-->
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-1">
				<!-- <ul class="nav nav-sidebar">
					<li><a href="admin/student/list">学生管理</a></li>
					<li><a href="#">教师管理</a></li>
					<li><a href="#">XXX</a></li>
				</ul> -->
			</div>
			<div class="col-sm-11">
				<h1 class="page-header">举报信息</h1>

				<h2 class="sub-header">详情</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>序号</th>
								<th>评价所属课程</th>
								<th>课程教师</th>
								<th>评价内容</th>
								<th>举报次数</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="datas" value="${reportPager.datas }"/>
							<c:forEach items="${datas }" var="r" varStatus="status">
								<tr id="${status.index }">
									<td>${status.index+1 }</td>
									<td>${r.comment.course.crs_name }</td>
									<td>${r.comment.course.crs_teacher_name }</td>
									<td>${r.comment.cmt_content }</td>
									<td>${r.comment.cmt_report_number }</td>
									<td>
									<a href="javascript:void(0)" onclick="deleteComment('${status.index }','${r.comment.cmt_id }')"><span class="label label-default">删除评论</span></a>
										
									</td>
									<input id="hiddenId${status.index }" type="hidden" value="${status.index }"/>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

				<!-- 分页 -->
				<form id="pagerForm" action="" method="post">
					<nav>
						<ul class="pagination">
							<!-- 上一页 -->
							<c:if test="${reportPager.hasPreviousPage ==false }">
								<li class="disabled"><a aria-label="Previous"><span
										aria-hidden="true">«</span></a></li>
							</c:if>
							<c:if test="${reportPager.hasPreviousPage ==true }">
								<li><a href="javascript:void(0);" aria-label="Previous"
									onclick="previousPage()"><span aria-hidden="true">«</span></a></li>
							</c:if>
							<!-- 页码 -->
							<!-- <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li> -->

							<c:forEach var="i" begin="1" end="${reportPager.totalPage }">
								<c:if test="${reportPager.pageIndex== i }">
									<li class="active"><a id="indexA"> <c:out
												value="${i }"></c:out></a></li>
								</c:if>
								<c:if test="${reportPager.pageIndex!=i }">
									<li><a href="javascript:void(0);"
										onclick="submitPager('${i}')"> <c:out value="${i }"/></a>
									</li>
								</c:if>
							</c:forEach>

							<!-- 下一页 -->
							<c:if test="${reportPager.hasNextPage == false }">
								<li class="disabled"><a aria-label="Next"> <span
										aria-hidden="true">»</span></a></li>
							</c:if>
							<c:if test="${reportPager.hasNextPage == true }">
								<li><a href="javascript:void(0)" onclick="nextPage()"
									aria-label="Next"><span aria-hidden="true">»</span></a></li>
							</c:if>

						</ul>
					</nav>
				</form>

			</div>
		</div>
	</div>


	<!-- 编辑模态框（Modal） -->
	<div class="modal fade" id="studentEditModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width:450px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="stuId" class="col-sm-2 control-label">学号</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="stuId"
									placeholder="请输入学号" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="name"
									placeholder="请输入姓名">
							</div>
						</div>
						<div class="form-group">
							<label for="gender" class="col-sm-2 control-label">性别</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="gender"
									placeholder="请输入性别">
							</div>
						</div>
						<div class="form-group">
							<label for="department" class="col-sm-2 control-label">系别</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="department"
									placeholder="请输入系别">
							</div>
						</div>
						<div class="form-group">
							<label for="major" class="col-sm-2 control-label">专业</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="major"
									placeholder="请输入专业" >
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
</body>
</html>
