package org.javatribe.pinker.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.entity.Teacher;
import org.javatribe.pinker.service.CommentService;
import org.javatribe.pinker.service.StudentService;
import org.javatribe.pinker.service.TeacherService;
import org.javatribe.pinker.util.FormatTrans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mars
 * 
 *         2015年10月25日 下午3:48:53
 */

@Controller
@RequestMapping("/message")
public class MessageReturnController {

	private CommentService commentService;
	private StudentService studentService;
	private TeacherService teacherService;

	@Resource(name = "commentServiceImpl")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Resource(name = "teacherServiceImpl")
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	/**
	 * 根据用户ID来获得对其评价回复的评价 （别人对自己的评论点赞的提醒仍未做）
	 * 
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value = "/aboutMe/{user_id}.json")
	@ResponseBody
	public JSONArray getMessage(@PathVariable int user_id) {
		JSONArray messageJson = new JSONArray();
		List<Comment> lists = commentService
				.getReplyCommentsByOriCommentatorId(user_id);
		for (Comment c : lists) {
			JSONObject json = new JSONObject();
			//测试数据有一些有回复者id而没有回复评论ID，实际中可以去掉if语句
			if (c.getComment_reply() != null) {
				json.put("commentId", c.getCmt_id());
				json.put("courseTeacher", c.getCourse().getCrs_teacher_name());
				json.put("courseName", c.getCourse().getCrs_name());
				json.put("commentTime",
						FormatTrans.getHowLongTime(c.getCmt_time()));
				json.put("replyContent", c.getCmt_content());
				json.put("likeNumber", c.getCmt_like_number());

				json.put("originalCommentId", c.getComment_reply().getCmt_id());
				json.put("originalCommentContent", c.getComment_reply()
						.getCmt_content());
				if (c.getCmt_user_id() / 100000000 == 1) {
					Student s = studentService.getById(c.getCmt_user_id());
					json.put("responder", s.getStu_name());
					json.put("responderHeadImg", s.getStu_head_img());
				} else {
					Teacher t = teacherService.getById(c.getCmt_user_id());
					System.out.println("-----------" + t);
					json.put("responder", t.getTch_name());
					json.put("responderHeadImg", t.getTch_head_img());
				}
				messageJson.add(json);
			}
		}
		return messageJson;
	}

	/**
	 * 通过用户ID来获得其评价内容
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/my/{id}.json")
	@ResponseBody
	public JSONArray getMyMessages(@PathVariable int id) {
		JSONArray messageJson = new JSONArray();
		List<Comment> lists = commentService.getCommentsByCommentatorId(id);
		for (Comment c : lists) {
			JSONObject json = new JSONObject();
			json.put("commentId", c.getCmt_id());
			json.put("commentTeacher", c.getCourse().getCrs_teacher_name());
			json.put("commentName", c.getCourse().getCrs_name());
			json.put("commentTime", FormatTrans.getHowLongTime(c.getCmt_time()));
			json.put("commentLabel", c.getCmt_crs_label());
			json.put("commentStar", c.getCmt_star());
			json.put("commentContent", c.getCmt_content());
			messageJson.add(json);
		}
		return messageJson;
	}

}
