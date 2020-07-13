package com.dcc.controller.news;

import com.alibaba.fastjson.JSON;
import com.dcc.base.Response;
import com.dcc.po.News;
import com.dcc.po.NewsType;
import com.dcc.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("news")
public class NewsController {
	@Autowired
	private NewsService newsService;


	@RequestMapping("/news.html")
	public String gotoNews(){
		return "news/news";
	}

	@RequestMapping("/news_add.html")
	public String gotoAddNews(){
		return "news/news_add";
	}

	@RequestMapping("/news_update.html")
	public String gotoUpdateNews(){
		return "news/news_update";
	}
	
	@RequestMapping(value="/selectType.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String selectType() throws Exception {
		String types = newsService.selectNewsType();
		return types;
	}
	
	
	@RequestMapping("/updateImgs.do")
	@ResponseBody
	public String updateImgs(MultipartFile imgsg) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
			String yimg  = imgsg.getOriginalFilename();
			if(imgsg!=null && yimg!=null && yimg.length()>0) {
				map.put("state", "1");
				map.put("name", yimg);
				map.put("file", multipartFileToBase64(imgsg));
			}
		return JSON.toJSONString(map);
	}
	
	@RequestMapping("/addNews.do")
	@ResponseBody
	public Response addNews(News news) throws Exception {
		 return Response.ok(newsService.addNews(news));
	}

	@RequestMapping("/searchNews.html")
	public String toSearchNews(String stitle) {
		return "news/searchNews";
	}

	@RequestMapping("/counts.do")
	@ResponseBody
	public String counts() throws Exception {
		String cc = newsService.countNews();
		return cc;
	}
	
	@RequestMapping(value="/findPage.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String findPage(Integer Fenye) throws Exception {
		String fy = newsService.fenyeNews(Fenye);
		return fy;
	}
	
	@RequestMapping(value="/delnews.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String delnews(Integer result) throws Exception {
		String del = newsService.deleteNews(result);
		return del;
	}
	
	@RequestMapping(value="/delsn.do")
	@ResponseBody
	public String delsn(String result) throws Exception {
		String del = newsService.delsNews(result);
		return del;
	}
	
	@RequestMapping(value="/findIdNew.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String findIdNew(Integer id) throws Exception {
	String news = newsService.findIdN(id);
		return  news;
	}
	
	@RequestMapping("/updatenew.do")
	@ResponseBody
	public Response updatenew(News news) throws Exception {
		return Response.ok(newsService.updateNew(news));
	}
	
	@RequestMapping("/addType.do")
	@ResponseBody
	public Response addType(NewsType type) throws Exception {
		return Response.ok(newsService.addNewsType(type));
	}
	
	@RequestMapping(value="/searchnews.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String searchnews(String title) throws Exception {
		String search = newsService.searchNew(title);
		return  search;
	}


	public String multipartFileToBase64(MultipartFile file) throws Exception {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String base64EncoderImg = base64Encoder.encode(file.getBytes());
		return base64EncoderImg;
	}

}
