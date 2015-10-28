package org.javatribe.pinker.controller.admin;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Likes;
import org.javatribe.pinker.service.CommentService;
import org.javatribe.pinker.service.LikeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/** @author kaiscript
 * 2015年10月25日 下午1:19:49
 */
@Controller
@RequestMapping("/like")
public class LikeController {
	
	private LikeService likeService;
	private CommentService commentService;
	
	@Resource(name = "likeServiceImpl")
	public void setLikeService(LikeService likeService) {
		this.likeService = likeService;
	}
	
	@Resource(name = "commentServiceImpl")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * 进行点赞
	 * @param like_cmt_id 评价编号
	 * @param like_user_id 点赞者
	 * @param like_cmt_user_id 评价拥有者
	 * @param ifLike    like or unlike.like:0 unlike:1
	 * @return
	 */
	@RequestMapping(value="/dolike/{like_cmt_id}/{like_user_id}/{like_cmt_user_id}/{ifLike}",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject doLike(@PathVariable int like_cmt_id,@PathVariable int like_user_id,@PathVariable int like_cmt_user_id,@PathVariable int ifLike){
		JSONObject json = new JSONObject();
		Likes like = likeService.getLikeByCommentidAndUserid(like_cmt_id, like_user_id);
		if(like ==null){ //在数据库中找不到记录
			like = new Likes();
			like.setLike_cmt_id(like_cmt_id);
			like.setLike_user_id(like_user_id);
			like.setLike_time(new Date());
			like.setLike_cmt_user_id(like_cmt_user_id);
			likeService.save(like);
			Comment comment = commentService.getById(like_cmt_id);
			comment.setCmt_like_number(comment.getCmt_like_number()+1);//点赞+1
			commentService.save(comment);
			json.put("message", "已点赞");
		}
		else{
			json.put("message", "你已经点过赞了");
		}
		return json;
	}
	
	/** 
	 * 检查是否点赞
	 * @param like_cmt_id 评价编号
	 * @param like_user_id 点赞者
	 * @return
	 */
	@RequestMapping(value="/checklike/{like_cmt_id}/{like_user_id}")
	@ResponseBody
	public JSONObject checkLike(@PathVariable int like_cmt_id,@PathVariable int like_user_id){
		JSONObject json = new JSONObject();
		Likes like = likeService.getLikeByCommentidAndUserid(like_cmt_id,like_user_id);
		if(like!=null){  //数据库有此点赞数据
			json.put("code", 0); //code为0代表已点过赞
			json.put("message",like_user_id+"对此评论已点过赞");
			return json;
		}
		json.put("code", 1);
		json.put("message",like_user_id+"此评论未点过赞");
		return json;
	}
}
