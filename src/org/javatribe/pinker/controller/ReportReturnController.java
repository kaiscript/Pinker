package org.javatribe.pinker.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Report;
import org.javatribe.pinker.service.CommentService;
import org.javatribe.pinker.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/** @author kaiscript
 * 2015年10月30日 下午7:53:19
 */
@Controller
@RequestMapping("/report")
public class ReportReturnController {
	
	@Resource(name ="reportServiceImpl")
	private ReportService reportService;
	
	@Resource(name = "commentServiceImpl")
	private CommentService commentService;
	
	/**
	 * 获取所有举报内容
	 * @return
	 */
	@RequestMapping(value = "/all.json",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getAllReport(){
		JSONArray jsonArr = new JSONArray();
		List<Report> reports = reportService.getAllList();
		
		for(Report r:reports){
			JSONObject json = new JSONObject();
			json.put("reportId", r.getRpt_id());
			json.put("commentContent", r.getComment().getCmt_content());
			json.put("reportNumber",r.getComment().getCmt_report_number());
			jsonArr.add(json);
		}
		return jsonArr;
	}
	
	/**
	 * 举报某条评论
	 * @param userId
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value = "/{userId}/{commentId}.json",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject report(@PathVariable int userId,@PathVariable int commentId){
		JSONObject json = new JSONObject();
		Comment comment = commentService.getById(commentId);
		Report report = reportService.getReportByCommentId(comment.getCmt_id());
		if(report ==null){
			report = new Report();
			report.setComment(comment);
			report.setRpt_cmt_person_id(String.valueOf(userId));
			comment.setCmt_report_number(comment.getCmt_report_number()+1);//举报次数+1
			reportService.save(report);
			
		}
		else{
			String[] persons = report.getRpt_cmt_person_id().split(",");
			if(persons==null){
				report.setRpt_cmt_person_id(report.getRpt_cmt_person_id()+","+userId);
				comment.setCmt_report_number(comment.getCmt_report_number()+1);
				reportService.update(report);
			}
			else{
				for(String p:persons){
					if(p.equals(String.valueOf(userId))){
						json.put("code", 1);
						json.put("message", "举报失败，你已举报此评论");
						return json;
					}
				}
				report.setRpt_cmt_person_id(report.getRpt_cmt_person_id()+","+userId);
				comment.setCmt_report_number(comment.getCmt_report_number()+1);
				reportService.update(report);
			}
		}
		commentService.save(comment);
		json.put("code", 0);
		json.put("message", "举报成功");
		return json;
	}
	
}
