<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<%@ include file="../head.html"%>
<title>学生信息编辑</title>

<script type="text/javascript">
	function changeMajorSelect(majorId){
		var req;
		var id=majorId;
		if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
		  req=new XMLHttpRequest();
		  }
		else{// code for IE6, IE5
		  req=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		
		req.onreadystatechange=function(){
			if(req.readyState ==4 && req.status ==200){
				
				var major="{\"majors\":"+req.responseText+"}";
				obj=JSON.parse(major);
				//alert(obj.majors[2].maj_name);
				var select =document.getElementById("selectMajor");
				select.length=1;
				select.options[0].text="请选择";
				for(var i=0;i<select.length;i++){
					var op=document.createElement('option');
					op.text=obj.majors[i].maj_name;
					op.value=obj.majors[i].maj_id;
					if(op.value==id){
						op.selected=true;
					}
					select.add(op,null);
				}
			}
		};
		req.open("POST","major/getMajorSelect/"+$("#selectDepartment").val()+"?json",true);
		req.send(null);
	}
	
</script>

</head>

<body>
	<div class="container">
		<div class="page-header" id="banner">
			<div class="row">
				<h1>修改学生信息</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<div class="well bs-component">
					<form class="form-horizontal" action="admin/student/updateStudent">
						<fieldset>
							<legend>修改学生 ${student.stu_name } 的资料</legend>
							<div class="form-group">
								<label for="inputId" class="col-lg-2 control-label">学号</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="inputId"
										placeholder="学号" name="stu_id" value="${student.stu_id }">
								</div>
							</div>
							<div class="form-group">
								<label for="inputName" class="col-lg-2 control-label">姓名</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="inputName"
										placeholder="姓名" name="stu_name" value="${student.stu_name }">
								</div>
							</div>
							<div class="form-group">
								<label for="inputGender" class="col-lg-2 control-label">性别</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="inputGender"
										placeholder="性别" name="stu_sex" value="${student.stu_sex }">
								</div>
							</div>


							<div class="form-group">
								<label for="select" class="col-lg-2 control-label">系别</label>
								<div class="col-lg-10">
									<select class="form-control" id="selectDepartment"
										name="departmentId" onchange="changeMajorSelect(${student.major.maj_id })">
										<c:forEach items="${department }" var="d">
											<c:if test="${d.dept_name == student.department.dept_name}">
												<option value="${d.dept_id }" selected="selected">${d.dept_name }</option>
											</c:if>
											<c:if test="${d.dept_name != student.department.dept_name}">
												<option value="${d.dept_id }">${d.dept_name }</option>
											</c:if>

										</c:forEach>

									</select>

								</div>
							</div>

							<div class="form-group">
								<label for="selectMajor" class="col-lg-2 control-label">专业</label>
								<div class="col-lg-10">
									<select class="form-control" id="selectMajor" name="majorId">
										<c:forEach items="${major }" var="m">
											<c:if test="${m.maj_id==student.major.maj_id }">
												<option value="${m.maj_id }" selected="selected">${m.maj_name }</option>
											</c:if>
											<c:if test="${m.maj_id!=student.major.maj_id }">
												<option value="${m.maj_id }">${m.maj_name }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label for="passwordQuestion" class="col-lg-2 control-label">密码</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" name="stu_password" value="${student.stu_password }">
								</div>
							</div>
							
							<div class="form-group">
								<label for="passwordQuestion" class="col-lg-2 control-label">密保问题</label>
								<div class="col-lg-10">
									<select class="form-control" id="passwordQuestion" name="stu_pw_question">
										<c:forEach items="${question }" var="q">
											<c:if test="${q.value==student.stu_pw_question }">
												<option value="${q.value }" selected="selected">${q.value }</option>
											</c:if>
											<c:if test="${q.value!=student.stu_pw_question }">
												<option value="${q.value }">${q.value }</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="form-group">
								<label for="passwordQuestion" class="col-lg-2 control-label">密保答案</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" value="${student.stu_pw_answer }"
										name="stu_pw_answer">
									<input type="hidden" name="stu_head_img" value="111222"/>
									<%-- <input name="stu_regist_time" type="hidden" value="${student.stu_regist_time }"/> --%>
								</div>
							</div>
							
							<div class="form-group">
								<label for="passwordQuestion" class="col-lg-2 control-label">注册时间</label>
								<div class="col-lg-10" >
									<input type="hidden" name="registTime" value="${student.stu_regist_time }"/>
									${student.stu_regist_time }
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="reset" class="btn btn-default">Cancel</button>
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</div>
							
							
							
						</fieldset>
					</form>
				</div>
			</div>
			<div class="col-lg-3"></div>
		</div>

	</div>
</body>
</html>
