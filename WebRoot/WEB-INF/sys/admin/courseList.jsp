<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="../nav.html"%>
<%@ include file="../head.html"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function submitPager(index) {
		$(document).ready(function() {
			$("#pagerForm").attr("action", "admin/course/list?pageIndex=" + index);
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
	
</script>
</head>
<body>
<div style="margin: 50px 100px">
	<div class="col-lg-4">
		<div class="input-group">
			<form action="" method="">
				<input type="text" class="form-control"
					placeholder="搜索课程，用课程名 / 教师名搜索"> <span
					class="input-group-btn"> 
				<input type="submit"
					class="btn btn-default" value="Go !"/>
					<input type="submit"
					class="btn btn-default" value="Go !"/>
			</form>
			</span>
		</div>
		<!-- /input-group -->
	</div>
	<!-- /.col-lg-6 -->

	<table class="table table-striped row table-bordered">
		<caption><center>课程评价</center></caption>
		<tr class="info">
			<th class="col-md-1">课程编号</th>
			<th class="col-md-3">课程名称</th>
			<th class="col-md-2">授课教师</th>
			<th class="col-md-2">平均星级</th>
			<th class="col-md-1">是否为选修</th>
			<th>查看详情</th>
		</tr>
		<c:set var="datas" value="${coursePager.datas }"/>
		<c:forEach items="${datas }" var="c" varStatus="status">
			<tr>
				<td>${c.crs_id }</td>
				<td>${c.crs_name }</td>
				<td>${c.crs_teacher_name }</td>
				<td><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span></td>
				<td><span class="glyphicon glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span></td>
				<td><button class="btn btn-primary">课程细节</button> 
			<button class="btn btn-success">查看评价</button></td>
			</tr>
		</c:forEach>
		<tr>
			<td>1</td>
			<td>高等数学</td>
			<td>林奕武</td>
			<td><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span></td>
			<td><span class="glyphicon glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span></td>
			<td><button class="btn btn-primary">课程细节</button> 
			<button class="btn btn-success">查看评价</button></td>
		</tr>
		<tr>
			<td>2</td>
			<td>C语言程序设计</td>
			<td>鲜征征</td>
			<td><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span></td>
			<td><span class="glyphicon glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span></td>
			<td><button class="btn btn-primary">课程细节</button> 
			<button class="btn btn-success">查看评价</button></td>
		</tr>
		<tr>
			<td>3</td>
			<td>计算机网络</td>
			<td>韩冬</td>
			<td><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span></td>
			<td></td>
			<td><button class="btn btn-primary">课程细节</button> 
			<button class="btn btn-success">查看评价</button></td>
		</tr>
		<tr>
			<td>4</td>
			<td>大学英语</td>
			<td>唐小丹</td>
			<td><span class="glyphicon glyphicon glyphicon-star" aria-hidden="true"></span></td>
			<td><span class="glyphicon glyphicon glyphicon glyphicon-ok" aria-hidden="true"></span></td>
			<td><button class="btn btn-primary">课程细节</button> 
			<button class="btn btn-success">查看评价</button></td>
		</tr>
		
	</table>
	
	<!-- 分页 -->
				<form id="pagerForm" action="" method="post">
					<nav>
						<ul class="pagination">
							<!-- 上一页 -->
							<c:if test="${coursePager.hasPreviousPage ==false }">
								<li class="disabled"><a aria-label="Previous"><span
										aria-hidden="true">«</span></a></li>
							</c:if>
							<c:if test="${coursePager.hasPreviousPage ==true }">
								<li><a href="javascript:void(0);" aria-label="Previous"
									onclick="previousPage()"><span aria-hidden="true">«</span></a></li>
							</c:if>
							<!-- 页码 -->
							<!-- <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li> -->

							<c:forEach var="i" begin="1" end="${coursePager.totalPage }">
								<c:if test="${coursePager.pageIndex== i }">
									<li class="active"><a id="indexA"> <c:out
												value="${i }"></c:out></a></li>
								</c:if>
								<c:if test="${coursePager.pageIndex!=i }">
									<li><a href="javascript:void(0);"
										onclick="submitPager('${i}')"> <c:out value="${i }"/></a>
									</li>
								</c:if>
							</c:forEach>

							<!-- 下一页 -->
							<c:if test="${coursePager.hasNextPage == false }">
								<li class="disabled"><a aria-label="Next"> <span
										aria-hidden="true">»</span></a></li>
							</c:if>
							<c:if test="${coursePager.hasNextPage == true }">
								<li><a href="javascript:void(0)" onclick="nextPage()"
									aria-label="Next"><span aria-hidden="true">»</span></a></li>
							</c:if>

						</ul>
					</nav>
				</form>
	
	</div>
</body>
</html>