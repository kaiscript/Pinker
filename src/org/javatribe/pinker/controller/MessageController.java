package org.javatribe.pinker.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.service.CommentService;
import org.javatribe.pinker.util.FormatTrans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mars
 *
 *2015年10月25日 下午3:48:53
 */

@Controller
@RequestMapping("/message")
public class MessageController {
	
	private CommentService commentService;
	
	@Resource(name="commentServiceImpl")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	
	/**
	 * 根据用户ID来获得对其评价回复的评价
	 * @param user_id
	 * @return
	 */
	@RequestMapping(value="/aboutMe/{user_id}.json")
	@ResponseBody
	public JSONArray getMessage(@PathVariable int user_id){
		JSONArray messageJson = new JSONArray();
		List<Comment> lists = commentService.getCommentsByCommentatorId(user_id);
		for(Comment c: lists){
			JSONObject json = new JSONObject();
			json.put("commentId", c.getCmt_id());
			json.put("courseTeacher", c.getCourse().getCrs_teacher_name());
			json.put("courseName", c.getCourse().getCrs_name());
			json.put("commentTime", FormatTrans.getHowLongTime(c.getCmt_time()));
			json.put("replyContent", c.getCmt_content());
			if( c.getComment_reply()!=null){
				json.put("originalCommentId", c.getComment_reply().getCmt_id());
				json.put("originalCommentContent", c.getComment_reply().getCmt_content());
			}
			messageJson.add(json);
		}
		return messageJson;
	}

	
	/**
	 * 通过用户ID来获得其评价内容
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/my/{id}.json")
	@ResponseBody
	public JSONArray getMyMessages(@PathVariable int id){
		JSONArray messageJson = new JSONArray();
		List<Comment> lists = commentService.getCommentsByCommentatorId(id);
		for(Comment c: lists){
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
