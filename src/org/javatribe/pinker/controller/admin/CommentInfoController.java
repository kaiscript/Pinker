package org.javatribe.pinker.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mars
 *
 *2015年9月7日 下午3:22:59
 */

@Controller
@RequestMapping("/admin/comment")
public class CommentInfoController {

	private CommentService commentService;
	
	@Resource(name="commentServiceImpl")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}


	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model, Pager pager){
		if(pager.getTotalCount()==0){
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(5);
			pager.setHasPreviousPage(false);
		}
		
		pager = commentService.findByPager(pager);
		pager.init();
		model.addAttribute("commentPager", pager);
		
		return "admin/commentList";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public String list(@RequestParam("pageIndex") int pageIndex, Model model, Pager pager){
		if(pager.getTotalCount()==0){
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(5);
			pager.setHasPreviousPage(false);
		}
		pager.setPageIndex(pageIndex);
		pager = commentService.findByPager(pager);
		pager.init();
		model.addAttribute("commentPager", pager);
		
		return "admin/commentList";
	}
	
	@RequestMapping(value="/delete")
	public String delete(@RequestParam("id") int id, Model model, Pager pager){
		Comment comment = commentService.getById(id);
		if( comment!=null)
			commentService.delete(comment);
		if(pager.getTotalCount()==0){
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(5);
			pager.setHasPreviousPage(false);
		}
		
		pager = commentService.findByPager(pager);
		pager.init();
		model.addAttribute("commentPager", pager);
		
		
		return "admin/commentList";
	}
	
	@RequestMapping(value="/search")
	public String search(@RequestParam("keyword") String keyword, Model model, Pager pager){
		if(pager.getTotalCount()==0){
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(5);
			pager.setHasPreviousPage(false);
		}
		pager = commentService.getByKeyword(keyword, pager);
		pager.init();
		model.addAttribute("commentPager", pager);
		return "admin/commentList";
	}
	
}
