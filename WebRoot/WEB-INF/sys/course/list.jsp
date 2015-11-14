<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	//对head.html中的basePath设置值, http://localhost:8080/Pinker/
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程信息列表</title>
<script src="js/star-rating.min.js" type="text/javascript"></script>
<link href="css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function submitPager(index) {
		$(document).ready(function() {
			
			/* $("#input-id").rating(); //默认值初始化	 
			$("#input-id").rating({'size':'lg'});  //带参数初始化  */
			
		//用action记录当前的页数。
			$("#pagerForm").attr("action", "admin/courseInfo/list?pageIndex=" + index);
		//提交
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
	

	 /*  jQuery(document).ready(function () {

	       $("#input-id").rating();

	   }); */

	
</script>
</head>
<%@ include file="../head.html"%>
<body>

	<!-- 引入顶部导航栏 -->
	<%@ include file="../nav.html"%>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-1">
				<!-- <ul class="nav nav-sidebar">
					<li><a href="admin/student/list">学生管理</a></li>
					<li><a href="#">教师管理</a></li>
					<li><a href="#">XXX</a></li>
				</ul>  -->
			</div>
			<div class="col-sm-10">
				<h1 class="page-header">课程信息</h1>

				<h3 class="sub-header">课程列表</h3>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>课程编号</th>
								<th>课程名称</th>
								<th>课程描述</th>
								<th>任课老师</th>
								<th>平均星级</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<c:set var="datas" value="${coursePager.datas }"/>
							<c:forEach items="${datas }" var="s" varStatus="status">
								<tr>
									<td>${s.crs_id }</td>
									 <td>${s.crs_name }</td>
									<td>${s.crs_desc }</td>
									<td>${s.crs_teacher_name }</td>
									<td>
										<!-- 多少分就几颗星。 -->
										<c:forEach begin="1" end="${s.crs_avg_star }">
  												<span class="glyphicon glyphicon-star"></span>
										</c:forEach>  
										<%-- <input id="input-id" type="number" class="rating-stars" min=0 max=5 step=0.5 data-size="lg" data-default-caption="${s.crs_avg_star } hearts" data-star-captions="${s.crs_avg_star }"> --%>
										<%-- <div class = "star-rating rating-xl rating-active">
											<div class = "clear-rating clear-rating-active" title = "Clear">
												
											</div>
											<div class = "rating-container rating-gly" data-content = "">
												<div class = "rating-stars" data-content = "" style = "width: 60%">
													
												</div>
												<input id = "input-id" class = "rating form-comtrol" type = "number" data-star-captions = "{${s.crs_avg_star }}" data-default-caption = "${s.crs_avg_star } hearts" data-symbol = "" data-size = "xl" step = "0.5" max = "5" min = "0" />
											</div>
										</div> --%>
									</td>
									<td>
									<a href="admin/courseInfo/editCourseInfo/id${s.crs_id }"><span class="label label-default">编辑</span></a>
									<button type="button" class="btn btn-primary btn-xs"
										data-toggle="modal" data-target="#courseEditModal"
										onclick="">模态框编辑(待)</button>
										
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
								<!-- 把当前页标记为indexA，用于显示。 -->
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
			</div>
		</div>
	
	
</body>
</html>