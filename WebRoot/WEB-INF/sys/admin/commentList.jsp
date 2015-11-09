<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ include file="../nav.html"%>
<%@ include file="../head.html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript">
	function submitPager(index) {
		$(document).ready(
				function() {
					$("#pagerForm").attr("action",
							"admin/comment/list?pageIndex=" + index);
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
	function deleteMessage(id) {
		alert(id);
	}
	
	function dodel(id){
		$('.modal').modal('show').on('shown',function(){
			$(".btn-primary").attr('href','Del.asp?id='+id);
		});
	}
</script>
</script>
</head>

<body>
	<div style="margin: 50px 100px">
		<div class="col-lg-4">
			<div class="input-group">
				<form action="" method="">
					<input type="text" class="form-control"
						placeholder="搜索课程，用课程名 / 教师名搜索"> <span class="input-group-btn"> <input type="submit"
						class="btn btn-default" value="Go !" /> 
				</span>
				</form>
			</div>
			<!-- /input-group -->
		</div>
		<!-- /.col-lg-6 -->

		<table class="table table-striped row table-bordered">
			<caption>
				<center>课程评价</center>
			</caption>
			<tr class="info">
				<th class="col-md-1">课程编号</th>
				<th class="col-md-1">评价用户</th>
				<th class="col-md-1">评价时间</th>
				<th class="col-md-1">星级评价</th>
				<th class="col-md-1">标签</th>
				<th class="col-md-3">内容评价</th>
				<th class="col-md-1">是否匿名</th>
			<!-- 	<th class="col-md-1">赞同数/反对数</th> -->
				<th class="col-md-1">举报数</th>
				<th class="col-md-2">评论操作</th>
			</tr>
			<c:set var="datas" value="${commentPager.datas }" />
			<c:forEach items="${datas }" var="c" varStatus="status">
				<tr>
					<td>${status.index+1} </td>
					<td>${c.cmt_user_id }</td>
					<td><h6>${c.cmt_time }</h6></td>
					<td>${c.cmt_star }</td>
					<td>${c.cmt_crs_label }</td>
					<td>${c.cmt_content }</td>
					<c:if  test="${c.cmt_is_anon==true }">
						<td><span class="glyphicon glyphicon glyphicon glyphicon-ok"
							aria-hidden="true"></span></td>
					</c:if>
					<c:if test="${c.cmt_is_anon==false }">
						<td></td>
					</c:if>
				<%-- 	<td>${c.cmt_like_number }/${c.cmt_against_number }</td> --%>
					<td>${c.cmt_report_number }</td>
					<td><a href=""><button
								class="btn btn-danger">详情</button></a> <a href="admin/comment/delete?id=${ c.cmt_id}"><button
								class="btn btn-success">删除</button> </a></td>
					<!-- data-target=".bs-example-modal-sm" 	data-target="#deleteConfirmModal" data-toggle="modal" -->
				</tr>
			</c:forEach>
			<!-- <tr>
				<td>1</td>
				<td>高等数学</td>
				<td>林奕武</td>
				<td><span class="glyphicon glyphicon glyphicon-star"
					aria-hidden="true"></span><span
					class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span
					class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span></td>
				<td><span class="glyphicon glyphicon glyphicon glyphicon-ok"
					aria-hidden="true"></span></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td>
					<button class="btn btn-success" onclick="dodel(2)">删除</button>
				</td>
			</tr>
			<tr>
				<td>2</td>
				<td>C语言程序设计</td>
				<td>鲜征征</td>
				<td><span class="glyphicon glyphicon glyphicon-star"
					aria-hidden="true"></span><span
					class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span></td>
				<td><span class="glyphicon glyphicon glyphicon glyphicon-ok"
					aria-hidden="true"></span></td>
				<td>
					<button class="btn btn-success">删除</button>
				</td>
			</tr>
			<tr>
				<td>3</td>
				<td>计算机网络</td>
				<td>韩冬</td>
				<td><span class="glyphicon glyphicon glyphicon-star"
					aria-hidden="true"></span><span
					class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span
					class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span
					class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span></td>
				<td></td>
				<td>
					<button class="btn btn-success">删除</button>
				</td>
			</tr>
			<tr>
				<td>4</td>
				<td>大学英语</td>
				<td>唐小丹</td>
				<td><span class="glyphicon glyphicon glyphicon-star"
					aria-hidden="true"></span></td>
				<td><span class="glyphicon glyphicon glyphicon glyphicon-ok"
					aria-hidden="true"></span></td>
				<td>
					<button class="btn btn-success" onclick="window.location.href('"
						admin/comment/list?pageIndex=" + index')">删除</button>
				</td>
			</tr> -->

		</table>
		<!-- 分页 -->
		<form id="pagerForm" action="" method="post">
			<nav>
			<ul class="pagination">
				<!-- 上一页 -->
				<c:if test="${commentPager.hasPreviousPage ==false }">
					<li class="disabled"><a aria-label="Previous"><span
							aria-hidden="true">«</span></a></li>
				</c:if>
				<c:if test="${commentPager.hasPreviousPage ==true }">
					<li><a href="javascript:void(0);" aria-label="Previous"
						onclick="previousPage()"><span aria-hidden="true">«</span></a></li>
				</c:if>
				<!-- 页码 -->
				<!-- <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li> -->

				<c:forEach var="i" begin="1" end="${commentPager.totalPage }">
					<c:if test="${commentPager.pageIndex== i }">
						<li class="active"><a id="indexA"> <c:out value="${i }"></c:out></a></li>
					</c:if>
					<c:if test="${commentPager.pageIndex!=i }">
						<li><a href="javascript:void(0);"
							onclick="submitPager('${i}')"> <c:out value="${i }" /></a></li>
					</c:if>
				</c:forEach>

				<!-- 下一页 -->
				<c:if test="${commentPager.hasNextPage == false }">
					<li class="disabled"><a aria-label="Next"> <span
							aria-hidden="true">»</span></a></li>
				</c:if>
				<c:if test="${commentPager.hasNextPage == true }">
					<li><a href="javascript:void(0)" onclick="nextPage()"
						aria-label="Next"><span aria-hidden="true">»</span></a></li>
				</c:if>

			</ul>
			</nav>
		</form>

		<!-- 删除确认窗口 -->
		<div class="modal fade" id="deleteConfirmModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width:450px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">删除确认</h4>
					</div>
					<div class="modal-body">确认删除该条？</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal"
							onclick="alert('删除');">确定</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
</body>
</html>
