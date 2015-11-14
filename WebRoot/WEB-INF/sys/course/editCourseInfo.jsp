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

<base href="<%=basePath%>">
<%@ include file="../head.html"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>课程信息编辑</title>

<script type="text/javascript">

	function returnBack()
	{
		$("#courseForm").attr("action", "admin/courseInfo/list");
		//提交
		$("#courseForm").submit();
	}
	
</script>

</head>
<body>

	<div class="container">
		<div class="page-header" id="banner">
			<div class="row">
				<h1>修改课程信息</h1>
			</div>
		</div>
		<!-- 占用3列空白，为了使表单显示在中间 -->
		<div class="col-lg-3"></div>
		<div class="col-lg-6">
		<!-- 底框 -->
		<div class="well bs-component">
		<form role="form" id="courseForm" class="form-horizontal" action="/Pinker/admin/courseInfo/updateCourse">
			<div class="row">
  				<div class="form-group">
  					<!-- 占两列 -->
  					<label for="crs_name" class="col-lg-2 control-label">课程名称</label>
  					<!-- 占10列 -->
    				<div class="col-md-10">
						<input type="text" class="form-control" name = "crs_id" value = "${course.crs_id }"placeholder="课程编号">
						<input type="hidden" name="orginal_crs_id" value="${course.crs_id }">
					</div>	
				</div>
  			</div>
  			<div class="row">
  				<div class="form-group">
  					<!-- 占两列 -->
  					<label for="crs_name" class="col-lg-2 control-label">课程名称</label>
  					<!-- 占10列 -->
    				<div class="col-md-10">
						<input type="text" class="form-control" name = "crs_name" value = "${course.crs_name }"placeholder="课程名称">
						<%-- <input type="hidden" name="orginal_crs_name" value="${course.crs_name }"> --%>
					</div>	
				</div>
  			</div>
  			<div class="row">
  				<!-- 对齐 -->
   				<div class="form-group">
  					<!-- 占两列 -->
  					<label for="crs_desc" class="col-lg-2 control-label">课程描述</label>
  					<!-- 占10列 -->
    				<div class="col-md-10">
						<input type="text" class="form-control" name = "crs_desc" value = "${course.crs_desc }"placeholder="课程描述">
						<%-- <input type="hidden" name="orginal_crs_desc" value="${course.crs_desc }"> --%>
					</div>	
				</div>
  			</div>
  			<div class="row">
  				<div class="form-group">
    				<label for="crs_teacher_name" class="col-lg-2 control-label">任课老师</label>
    				<div class="col-md-10">
    					<input type="text" class="form-control" name = "crs_teacher_name" value = "${course.crs_teacher_name }"placeholder="任课老师">
						<%-- <input type="hidden" name="orginal_crs_teacher_name" value="${course.crs_teacher_name }"> --%>
					</div>
			 	</div>
			 </div>
			 <div class="row">
  				<div class="form-group">
    				<label for="crs_avg_star" class="col-lg-2 control-label">平均星级</label>
    				<div class="col-md-10">
    					<input type="text" class="form-control" name = "crs_avg_star" value = "${course.crs_avg_star }"placeholder="平均星级">
						<%-- <input type="hidden" name="orginal_crs_avg_star" value="${course.crs_avg_star }"> --%>
					</div>
			 	</div>
			 </div>
			 <div class="row">
  				<div class="form-group">
    				<label for="crs_labels" class="col-lg-2 control-label">课程标签</label>
    				<div class="col-md-10">
    					<input type="text" class="form-control" name = "crs_labels" value = "${course.crs_labels }"placeholder="课程标签">
						<%-- <input type="hidden" name="orginal_crs_labels" value="${course.crs_labels }"> --%>
					</div>
			 	</div>
			 </div>
			  <div class="row">
  				<div class="form-group">
    				<label for="catg_name" class="col-lg-2 control-label">所属门类</label>
    				<div class="col-md-10">
    					<input type="text" class="form-control" name = "catg_name" value = "${course.elective_category.catg_name }"placeholder="所属门类">
						<%-- <input type="hidden" name="orginal_catg_name" value="${course.elective_category.catg_name }"> --%>
					</div>
			 	</div>
			 </div>
			 <div class="row">
  				<div class="form-group">
    				<label for="maj_name" class="col-lg-2 control-label">所属专业</label>
    				<div class="col-md-10">
    					<input type="text" class="form-control" name = "maj_name" value = "${course.major.maj_name }"placeholder="所属专业">
						<%-- <input type="hidden" name="orginal_maj_name" value="${course.major.maj_name }"> --%>
					</div>
			 	</div>
			 </div>
			 
			 <div class="form-group">
				<div class="col-lg-10 col-lg-offset-2">
					<button type="button" class="btn btn-default">
						<a href="javascript:void(0)" onclick="returnBack()">返回</a>
					</button>
					<button type="submit" class="btn btn-primary">提交</button>
				</div>
			</div>
			 
		</form>
		</div>
		</div>
	</div>


</body>
</html>