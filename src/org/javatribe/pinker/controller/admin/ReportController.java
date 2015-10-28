package org.javatribe.pinker.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.common.Pager.Order;
import org.javatribe.pinker.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** @author kaiscript
 * 2015年10月27日 下午4:47:47
 */
@Controller
@RequestMapping("/admin/report")
public class ReportController {
	
	@Resource(name = "reportServiceImpl")
	private ReportService reportService;
	
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
	
}
