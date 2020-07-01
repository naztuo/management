package com.dcc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.dcc.dao.LinkMapper;
import com.dcc.po.Link;
import com.dcc.util.Time;

@Service
public class LinkService {
	@Autowired
	private LinkMapper linkMapper;

	@Transactional
	public void addLink(Link link) throws Exception {
		link.setDatas(Time.getTime());
		linkMapper.addLinkDao(link);
	}

	public String countLink() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer num = linkMapper.countLink();
		map.put("state", num);
		return JSON.toJSONString(map);
	}

	public String fenyeLink(Integer num) throws Exception {
		List<Link> lists = linkMapper.fenyeLink(num);
		return JSON.toJSONString(lists);
	}

	@Transactional
	public String deleteLink(Integer id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		int del =linkMapper.deleteLink(id);
		if(del>0) {
			map.put("state", "1");
		}else {
			map.put("state", "-1");
		}
		return JSON.toJSONString(map);
	}

	public String findIdLi(Integer id) throws Exception {
		Link link =linkMapper.findIdLink(id);
		return JSON.toJSONString(link);
	}
	public void updateLin(Link link) throws Exception {
		linkMapper.updateLink(link);
		
	}

}
