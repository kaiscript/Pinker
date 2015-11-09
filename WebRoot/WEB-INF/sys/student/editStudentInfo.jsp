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

<!-- 上传图片到本地服务器所需的js -->
<!-- <script type="text/javascript" src="js/ajaxfileupload.js"></script> --> 
<script type="text/javascript" src="js/jquery.form.min.js"></script>
<title>学生信息编辑</title>

<script type="text/javascript">
	
	$(document).ready(function() {
		initUploadForm();
	});
	
	$('input[name=FileContent]').change(function () {
		initUploadForm();
	});

	function changeMajorSelect(majorId) {
		var req;
		var id = majorId;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			req = new XMLHttpRequest();
		} else {// code for IE6, IE5
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}

		req.onreadystatechange = function() {
			if (req.readyState == 4 && req.status == 200) {

				var major = "{\"majors\":" + req.responseText + "}";
				obj = JSON.parse(major);
				//alert(obj.majors[2].maj_name);
				var select = document.getElementById("selectMajor");
				select.length = 1;
				select.options[0].text = "请选择";
				for (var i = 0; i < select.length; i++) {
					var op = document.createElement('option');
					op.text = obj.majors[i].maj_name;
					op.value = obj.majors[i].maj_id;
					if (op.value == id) {
						op.selected = true;
					}
					select.add(op, null);
				}
			}
		};
		req.open("POST", "major/getMajorSelect/" + $("#selectDepartment").val(), true);
		req.send(null);
	}

	/**
	 * 上传图片 到本地服务器 .图片上传按钮已注释 
	 */
	/* function uploadPic() {

		$.ajaxFileUpload({
			url : 'pic/upload',
			secureuri : false, //是否启用安全提交,默认为false
			fileElementId : 'photo', //文件选择框的id属性
			dataType : 'json', //服务器返回的格式,可以是json或xml等
			success : function(data, status) { //服务器响应成功时的处理函数
				if (status == "success") {
					for(var i=0;i<=100;i++){
						$("#progress").attr("style","width: "+i+"%;");
					}
					alert("上传成功");
					
				}

				$("#headImg").attr("src", "pic/" + data.photoName);
				$("#hiddenHeadImg").attr("value", "pic/" + data.photoName);
			},
			error:function(data,status){
				if(status!="success"){
					alert("上传失败");
				}
			}
		});
	} */
	
	
	function initUploadForm () {
		$.getJSON('pic/getsign', function(data) {
			var sign = data.sign,
			url = data.url + '?sign=' + encodeURIComponent(sign);
			var options = { 
	            type: 'post',
	            url: url,
	            dataType: 'json',
			    success:function(ret) { 
			    	for(var i=1;i<=30;i++){
						$("#progress").attr("style","width: "+i+"%;");
					}
			    	uploadImgUrl(ret.data.url, ret.data.download_url);
			    	$("#headImg").attr("src", ret.data.download_url);//更新当前头像url
			    	$("#hiddenHeadImg").attr("value",ret.data.download_url);
			    	$('#downloadUrl').html(ret.data.download_url);
			    	
			    	//$('#fileid').text(ret.data.fileid);
			    	//$('#url').text(ret.data.url);
			    	//alert(ret.data.download_url);
			    	//alert(ret.data.fileid);
			    	//alert(ret.data.url);
			    },
			    error:function (ret) {
			    	alert(ret.responseText);
			    }
			}; 
			// pass options to ajaxForm 
			$('#uploadForm').ajaxForm(options);
		});
	}
	
	function uploadImgUrl(usefulurl,downloadurl){
		$.ajax({
			type:'POST',
			url:'pic/uploadHeadimg',
			data:{
				'userid':$("#stuId").val(),
				'url':usefulurl,
				'downloadurl':downloadurl
			},
			dateType : 'json',
			success:function(data){
				if(data.code==0){
					for(var i=30;i<=100;i++){
						$("#progress").attr("style","width: "+i+"%;");
					}
		    		alert("上传头像成功");
				}
				else if(data.code==1)
					alert("上传头像失败");
			}
		});
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
								<label for="stuId" class="col-lg-2 control-label">学号</label>
								<div class="col-lg-10">
									<input type="text" class="form-control" id="stuId"
										placeholder="学号" name="stu_id" value="${student.stu_id }">
									<input type="hidden" name="orginal_stu_id" value="${student.stu_id }">
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
										name="departmentId"
										onchange="changeMajorSelect(${student.major.maj_id })">
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
									<input type="text" class="form-control" name="stu_password"
										value="${student.stu_password }">
								</div>
							</div>

							<div class="form-group">
								<label for="passwordQuestion" class="col-lg-2 control-label">密保问题</label>
								<div class="col-lg-10">
									<select class="form-control" id="passwordQuestion"
										name="stu_pw_question">
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
									<input type="text" class="form-control"
										value="${student.stu_pw_answer }" name="stu_pw_answer">
								</div>
							</div>

							<div class="form-group">
								<label for="passwordQuestion" class="col-lg-2 control-label">注册时间</label>
								<div class="col-lg-10">
									<input type="hidden" name="registTime" value="${student.stu_regist_time }" />
									${student.stu_regist_time }
								</div>
							</div>

							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<button type="reset" class="btn btn-default">
										<a href="admin/student/list">返回</a>
									</button>
									<button type="submit" class="btn btn-primary">提交</button>
								</div>
							</div>

							<input type="hidden" id="hiddenHeadImg" name="stu_head_img" value="${student.stu_head_img }">

						</fieldset>
					</form>
				</div>
			</div>
			<div class="col-lg-3">
				<img id="headImg" src="${student.stu_head_img }" class="img-thumbnail"> 
				<form id="uploadForm">
					<input type="file" id="imgFile" name="FileContent" value="浏览"></input> 
					<input type="submit" value="上传"/>
					<!-- <input type="submit" value="上传" onclick="uploadPic()" /> -->
				</form>
				<p>
				<div class="progress">
					<div id="progress" class="progress-bar" role="progressbar" aria-valuenow="60"
						aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
					</div>
				</div>
				<span id="downloadUrl"></span>
				<div id="fileid"></div>
				<div id="url"></div>  
			</div>

		</div>

	</div>
</body>
</html>
