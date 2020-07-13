package com.dcc.controller.apply;

import com.alibaba.fastjson.JSON;
import com.dcc.base.Response;
import com.dcc.po.Apply;
import com.dcc.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("apply")
public class ApplyController {
	@Autowired
	private ApplyService applyService;


	@RequestMapping("/apply.html")
	public String gotoApply(){
		return "apply/apply";
	}

	@RequestMapping("/apply_add.html")
	public String gotoAddApply(){
		return "apply/apply_add";
	}

	@RequestMapping("/apply_update.html")
	public String gotoUpdateApply(){
		return "apply/apply_update";
	}
	

	
	
	@RequestMapping("/updateImgs.do")
	@ResponseBody
	public String updateImgs(MultipartFile imgsg) throws Exception {
		Map<String, String> map = new HashMap<>();
			String yimg  = imgsg.getOriginalFilename();
			if(imgsg!=null && yimg!=null && yimg.length()>0) {
				map.put("state", "1");
				map.put("name", yimg);
				map.put("file", multipartFileToBase64(imgsg));
			}
		return JSON.toJSONString(map);
	}


	@RequestMapping("/searchApply.html")
	public String toSearchApply(String stitle) {
		return "apply/searchApply";
	}

	@RequestMapping("/counts.do")
	@ResponseBody
	public String counts() throws Exception {
		String cc = applyService.countApply();
		return cc;
	}
	
	@RequestMapping(value="/findPage.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String findPage(Integer Fenye) throws Exception {
		String fy = applyService.fenyeApply(Fenye);
		return fy;
	}
	
	@RequestMapping(value="/delapply.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String delapply(Integer result) throws Exception {
		String del = applyService.deleteApply(result);
		return del;
	}
	
	@RequestMapping(value="/delsn.do")
	@ResponseBody
	public String delsn(String result) throws Exception {
		String del = applyService.delsApply(result);
		return del;
	}
	
	@RequestMapping(value="/findIdNew.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String findIdNew(Integer id) throws Exception {
	String apply = applyService.findIdN(id);
		return  apply;
	}
	
	@RequestMapping("/updatenew.do")
	@ResponseBody
	public Response updatenew(Apply apply) throws Exception {
		return Response.ok(applyService.updateNew(apply));
	}
	

	
	@RequestMapping(value="/searchapply.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String searchapply(String title) throws Exception {
		String search = applyService.searchNew(title);
		return  search;
	}


	public String multipartFileToBase64(MultipartFile file) throws Exception {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String base64EncoderImg = base64Encoder.encode(file.getBytes());
		return base64EncoderImg;
	}

}
