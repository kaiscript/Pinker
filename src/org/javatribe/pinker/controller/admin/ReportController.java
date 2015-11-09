package org.javatribe.pinker.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.hibernate.SessionFactory;
import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.common.Pager.Order;
import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.service.CommentService;
import org.javatribe.pinker.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/** @author kaiscript
 * 2015年10月27日 下午4:47:47
 */
@Controller
@RequestMapping("/admin/report")
public class ReportController {
	
	@Resource(name = "reportServiceImpl")
	private ReportService reportService;
	
	private CommentService commentService;
	
	private SessionFactory sessionFactory;
	/**
	 * 注入 sessionFactory
	 * @param sessionFactory
	 */
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Resource(name = "commentServiceImpl")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@RequestMapping(value = "/list")
	public String reportPage(Model model,Pager pager){
		if(pager.getTotalCount()==0){
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(2);
			pager.setHasPreviousPage(false);// 第一页，故设置为无上一页
		}
		pager = reportService.findByPager(pager);
		pager.init();
		model.addAttribute("reportPager", pager);
		return "report/list";
		
	}
	
	/**
	 * 根据 pageIndex 返回pageSize条数据
	 * @param pageIndex
	 * @param model
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listByIndexPage(@RequestParam("pageIndex") int pageIndex,
			Model model, Pager pager) {
		if (pager.getTotalCount() == 0) {
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(2);
			pager.setHasPreviousPage(false);// 第一页，故设置为无上一页

			Map<String, Order> orderMap = new HashMap<String, Order>();
			orderMap.put("rpt_id", Order.asc);// 添加按rpt_id排序
			pager.setOrder(Order.asc);
			pager.setOrderMap(orderMap);
		}

		pager.setPageIndex(pageIndex);
		pager = reportService.findByPager(pager);
		pager.init();
		model.addAttribute("reportPager", pager);
		return "report/list";
	}
	
	/**
	 * 删除某条评论
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value="/delete/comment{commentId}",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteComment(@PathVariable int commentId){
		JSONObject json = new JSONObject();
		Comment comment = commentService.getById(commentId);
		comment.getReports().remove(comment);
		//OneNote Hibernate ObjectDeletedException
		if(comment!=null){
			commentService.delete(comment);
			json.put("code", "0");
			json.put("message", "delete success");
			return json;
		}
		json.put("code", 1);
		json.put("message", "delete fail");
		return json;
	}
}
