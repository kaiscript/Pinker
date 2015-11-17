package org.javatribe.pinker.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Course;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.service.CommentService;
import org.javatribe.pinker.service.CourseService;
import org.javatribe.pinker.service.StudentService;
import org.javatribe.pinker.util.FormatTrans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kaiscript 2015年10月11日 上午11:00:05
 */

@Controller
@RequestMapping("/comment")
public class CommentReturnController {

	private CommentService commentService;
	private StudentService studentService;
	private CourseService courseService;
		
	@Resource(name = "commentServiceImpl")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@Resource(name = "courseServiceImpl")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	/**
	 * 插入评论
	 * @param cmt_user_id
	 * @param cmt_crs_label
	 * @param cmt_content
	 * @param cmt_star
	 * @param cmt_is_anon
	 * @param cmt_crs_id
	 * @return
	 */
	@RequestMapping(value = "/insert/{cmt_user_id}/{cmt_crs_label}/{cmt_content}/{cmt_star}/{cmt_is_anon}/{cmt_crs_id}.json"
			,method=RequestMethod.GET)
	@ResponseBody
	public JSONObject insertComment(@PathVariable int cmt_user_id,@PathVariable String cmt_crs_label,
				@PathVariable String cmt_content,@PathVariable int cmt_star,
				@PathVariable boolean cmt_is_anon,@PathVariable int cmt_crs_id){
		JSONObject json = new JSONObject();
		Comment comment = new Comment();
		comment.setCmt_user_id(cmt_user_id);
		comment.setCmt_time(new Date());
		comment.setCmt_crs_label(cmt_crs_label);
		comment.setCmt_content(cmt_content);
		comment.setCmt_star(cmt_star);
		comment.setCmt_is_anon(cmt_is_anon);
		comment.setCmt_like_number(0);
		comment.setCmt_against_number(0);
		comment.setCmt_report_number(0);
		Course course = courseService.getById(cmt_crs_id);
		comment.setCourse(course);
		if(commentService.save(comment)){
			json.put("code", 0);
			json.put("message", "评论成功");
			return json;
		}
		json.put("code", 1);
		json.put("message", "评论失败,请重试");
		return json;
	}
	
	
	/**
	 * 关注课程 的最新评论
	 * @param stu_id 学生id
	 * @return
	 */
	@RequestMapping(value = "/attention/{stu_id}/all.json",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getAttentionComment(@PathVariable int stu_id){
		
		List<Comment> comments = commentService.
				getCommentByCourseIdSet(getCourseIdSet(stu_id));
		JSONArray jsonArr = getCommentsJSONArray(comments);
		return jsonArr;
	}
	
	/**
	 * 根据学生id 获取其关注课程的评论，从firstResult开始的10条评论
	 * @param stu_id
	 * @param firstResult
	 * @return
	 */
	@RequestMapping(value = "/attention/{stu_id}/{firstResult}.json",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getAttentionCommentByFirstresult(@PathVariable int stu_id,@PathVariable int firstResult){
		
		List<Comment> comments = commentService.
				getCommentByCourseIdSetAndFirstresult(getCourseIdSet(stu_id), firstResult);
		JSONArray jsonArr =  getCommentsJSONArray(comments);
		return jsonArr;
	}
	
	/**
	 * 根据评论id获取相应 评论数据
	 * 
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value = "/id{commentId}.json", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getCommentById(@PathVariable int commentId) {
		Comment comment = commentService.getById(commentId);
		JSONObject json = new JSONObject();
		String username = getName(comment.getCmt_user_id());
		json.put("commentId", comment.getCmt_id());
		json.put("userId", comment.getCmt_user_id());
		json.put("userHeadImg", getHeadImg(comment.getCmt_user_id()));
		json.put("userName", username);
		json.put("teaName", comment.getCourse().getCrs_teacher_name());
		json.put("courseId", comment.getCourse().getCrs_id());
		json.put("courseName", comment.getCourse().getCrs_name());
		json.put("courseDesc", comment.getCourse().getCrs_desc());
		json.put("data", FormatTrans.getHowLongTime(comment.getCmt_time()));
		json.put("content", comment.getCmt_content());
		json.put("good", comment.getCmt_like_number());
		json.put("bad", comment.getCmt_against_number());
		return json;
	}

	/**
	 * 返回从指定第几条数据开始 返回数据，数据默认10条
	 * 
	 * @param firstResult
	 *            从第几页开始
	 * @return json数据
	 */
	@RequestMapping(value = "/list/{firstResult}.json", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getCommentJson(@PathVariable int firstResult) {
		List<Comment> comments = commentService
				.getByFirstresultDesc(firstResult);
		return getCommentsJSONArray(comments);
	}

	/**
	 * 返回全部comments的数据
	 * 
	 * @return json数据
	 */
	@RequestMapping(value = "/list/all.json", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getAllComments() {
		List<Comment> getCommentsList = commentService.getAllListDesc();
		return getCommentsJSONArray(getCommentsList);
	}

	@RequestMapping(value = "/allcommentsincourse/courseId{courseId}/{firstResult}.json", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getCourseDetailCommentsByFirstresult(
			@PathVariable int courseId, @PathVariable int firstResult) {
		List<Comment> comments = commentService
				.getCommentByCourseIdAndFirstresult(courseId, firstResult);
		return getCommentsJSONArray(comments);
	}

	/**
	 * 返回某个课程下的所有评论
	 * 
	 * @param courseId
	 * @return json数据
	 */
	@RequestMapping(value = "/allcommentsincourse/courseId{courseId}.json", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getCourseDetailComments(@PathVariable int courseId) {
		List<Comment> comments = commentService.getCommentsByCourseId(courseId);
		return getCommentsJSONArray(comments);
	}

	/**
	 * 返回评论列表的JSON数组
	 * 
	 * @param comments
	 * @return
	 */
	public JSONArray getCommentsJSONArray(List<Comment> comments) {
		JSONArray commentsJson = new JSONArray();
		for (Comment c : comments) {
			JSONObject json = new JSONObject();
			String username = getName(c.getCmt_user_id());
			json.put("commentId", c.getCmt_id());
			json.put("userId", c.getCmt_user_id());
			json.put("userHeadImg", getHeadImg(c.getCmt_user_id()));
			json.put("userName", username);
			json.put("teaName", c.getCourse().getCrs_teacher_name());
			json.put("courseId", c.getCourse().getCrs_id());
			json.put("courseName", c.getCourse().getCrs_name());
			json.put("courseDesc", c.getCourse().getCrs_desc());
			json.put("data", FormatTrans.getHowLongTime(c.getCmt_time()));
			json.put("content", c.getCmt_content());
			json.put("label", c.getCmt_crs_label());
			json.put("star", c.getCmt_star());
			json.put("good", c.getCmt_like_number());
			json.put("bad", c.getCmt_against_number());
			commentsJson.add(json);
		}
		return commentsJson;
	}

	/**
	 * 根据用户id长度判断是学生还是教师，从而去对应的学生或教师表查询 用户名字
	 * 
	 * @param user_id
	 *            用户id
	 * @return 用户姓名
	 */
	public String getName(int user_id) {
		String id = String.valueOf(user_id);
		// 学生学号长度为9
		if (id.length() == 9) {
			Student student = studentService.getById(user_id);
			return student.getStu_name();
		}
		return "";
	}

	/**
	 * 根据用户id长度判断是学生还是教师，从而去对应的学生或教师表查询 用户头像url
	 * 
	 * @param user_id
	 * @return
	 */
	public String getHeadImg(int user_id) {
		String id = String.valueOf(user_id);
		if (id.length() == 9) {
			Student student = studentService.getById(user_id);
			return student.getStu_head_img();
		}
		return "";
	}
	
	
	/**
	 * 根据学生id取得所关注课程的idSet
	 * @param stu_id
	 * @return
	 */
	public Integer[] getCourseIdSet(int stu_id){
		Student student = studentService.getById(stu_id);
		String attentionCourse = student.getStu_attn_crs_ids();
		String[] strCourses = attentionCourse.split(",");
		Integer[] coursesIdSet = new Integer[strCourses.length];
		for(int i=0;i<strCourses.length;i++){
			coursesIdSet[i]=Integer.parseInt(strCourses[i]);
		}
		return coursesIdSet;
	}
}
