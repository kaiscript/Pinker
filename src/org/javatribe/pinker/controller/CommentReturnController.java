package org.javatribe.pinker.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.service.CommentService;
import org.javatribe.pinker.service.StudentService;
import org.javatribe.pinker.util.FormatTrans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author kaiscript
 * 2015年10月11日 上午11:00:05
 */

@Controller
@RequestMapping("/comment")
public class CommentReturnController {
	
	private CommentService commentService;
	private StudentService studentService;
	
	@Resource(name="commentServiceImpl")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	/**
	 * 根据评论id获取相应 评论数据
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value="/id{commentId}.json",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getCommentById(@PathVariable int commentId){
		Comment comment = commentService.getById(commentId);
		JSONObject json = new JSONObject();
		String username = getName(comment.getCmt_user_id());
		json.put("commentId", comment.getCmt_id());
		json.put("userId",comment.getCmt_user_id());
		json.put("userHeadImg", getHeadImg(comment.getCmt_user_id()));
		json.put("userName", username);
		json.put("teaName", comment.getCourse().getCrs_teacher_name());
		json.put("courseId", comment.getCourse().getCrs_id());
		json.put("courseName", comment.getCourse().getCrs_name());
		json.put("data",FormatTrans.getHowLongTime(comment.getCmt_time()));
		json.put("content", comment.getCmt_content());
		json.put("good", comment.getCmt_like_number());
		json.put("bad", comment.getCmt_against_number());
		return json;
	}
	
	/**
	 * 返回从指定第几条数据开始 返回数据，数据默认10条
	 * @param firstResult 从第几页开始
	 * @return json数据
	 */
	@RequestMapping(value="/list/{firstResult}.json",method =RequestMethod.GET)
	@ResponseBody
	public JSONArray getCommentJson(@PathVariable int firstResult){
		List<Comment> comments = commentService.getByFirstresultDesc(firstResult);
		return getCommentsJSONArray(comments);
	}
	
	
	/**
	 * 返回全部comments的数据
	 * @return json数据
	 */
	@RequestMapping(value = "/list/all.json",method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getAllComments(){
		List<Comment> getCommentsList = commentService.getAllListDesc();
		return getCommentsJSONArray(getCommentsList);
	}
	
	@RequestMapping(value="/allcommentsincourse/courseId{courseId}/{firstResult}.json",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getCourseDetailCommentsByFirstresult(@PathVariable int courseId,
			@PathVariable int firstResult){
		List<Comment> comments = commentService.getCommentByCourseIdAndFirstresult(courseId, firstResult);
		return getCommentsIncourseJSONArray(comments);
	}
	
	/**
	 * 返回某个课程下的所有评论
	 * @param courseId
	 * @return json数据
	 */
	@RequestMapping(value="/allcommentsincourse/courseId{courseId}.json",method = RequestMethod.GET)
	@ResponseBody
	public JSONArray getCourseDetailComments(@PathVariable int courseId){
		List<Comment> comments = commentService.getCommentsByCourseId(courseId);
		return getCommentsIncourseJSONArray(comments);
	}
	
	/**
	 * 某个课程下的所有评论 的JSONArray数组
	 * @param comments
	 * @return
	 */
	public JSONArray getCommentsIncourseJSONArray(List<Comment> comments){
		JSONArray commentsJson = new JSONArray();
		for(Comment c:comments){
			JSONObject json=new JSONObject();
			json.put("commentId", c.getCmt_id());
			json.put("courseId", c.getCourse().getCrs_id());
			json.put("userId",c.getCmt_user_id());
			json.put("commentContent",c.getCmt_content());
			json.put("userHeadImg", getHeadImg(c.getCmt_user_id()));
			json.put("data",FormatTrans.getHowLongTime(c.getCmt_time()));
			json.put("good", c.getCmt_like_number());
			json.put("bad", c.getCmt_against_number());
			commentsJson.add(json);
		}
		return commentsJson;
	}
	
	/**
	 * 返回评论列表的JSON数组
	 * @param comments
	 * @return
	 */
	public JSONArray getCommentsJSONArray(List<Comment> comments){ 
		JSONArray commentsJson = new JSONArray();
		for(Comment c:comments){
			JSONObject json = new JSONObject();
			String username = getName(c.getCmt_user_id());
			json.put("commentId", c.getCmt_id());
			json.put("userId",c.getCmt_user_id());
			json.put("userHeadImg", getHeadImg(c.getCmt_user_id()));
			json.put("userName", username);
			json.put("teaName", c.getCourse().getCrs_teacher_name());
			json.put("courseId", c.getCourse().getCrs_id());
			json.put("courseName", c.getCourse().getCrs_name());
			json.put("data",FormatTrans.getHowLongTime(c.getCmt_time()));
			json.put("content", c.getCmt_content());
			json.put("good", c.getCmt_like_number());
			json.put("bad", c.getCmt_against_number());
			commentsJson.add(json);
		}
		return commentsJson;
	}
	
	/**
	 * 根据用户id长度判断是学生还是教师，从而去对应的学生或教师表查询 用户名字
	 * @param user_id 用户id
	 * @return 用户姓名
	 */
	public String getName(int user_id){
		String id = String.valueOf(user_id);
		//学生学号长度为9
		if(id.length()==9){
			Student student = studentService.getById(user_id);
			return student.getStu_name();
		}
		return "";
	}
	
	/**
	 * 根据用户id长度判断是学生还是教师，从而去对应的学生或教师表查询 用户头像url
	 * @param user_id
	 * @return
	 */
	public String getHeadImg(int user_id){
		String id = String.valueOf(user_id);
		if(id.length()==9){
			Student student = studentService.getById(user_id);
			return student.getStu_head_img();
		}
		return "";
	}
}
