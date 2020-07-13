package com.dcc.service;

import com.alibaba.fastjson.JSON;
import com.dcc.dao.ApplyMapper;
import com.dcc.po.Apply;
import com.dcc.util.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyService {
	@Autowired
	private ApplyMapper applyMapper;




	
	public String countApply() throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer count = applyMapper.countApply();
		map.put("state", count);
		return JSON.toJSONString(map);
	}


	public String fenyeApply(Integer num) throws Exception {
		List<Apply> lists = applyMapper.fenyeApply(num);
		return JSON.toJSONString(lists);
	}


	@Transactional
	public String deleteApply(Integer id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		int del = applyMapper.deleteApply(id);
		if(del==1) {
			map.put("state", "1");
		}else {
			map.put("state", "-1");
		}
		return JSON.toJSONString(map);
	}


	@Transactional
	public String delsApply(String array) throws Exception {
		Map<String, String> map = new HashMap<>();
		String[] split = array.split(",");
		int de = applyMapper.delsApply(split);
		if(de>0) {
			map.put("state", "1");
		}else {
			map.put("state", "-1");
		}
		return JSON.toJSONString(map);
	}


	public String findIdN(Integer id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Apply Apply = applyMapper.findIdApply(id);
		return JSON.toJSONString(Apply);
	}


	@Transactional
	public int updateNew(Apply Apply) throws Exception {
		return applyMapper.updateApply(Apply);
	}




	public String searchNew(String title) throws Exception {
		List<Apply> list = applyMapper.searchApply(title);
		return JSON.toJSONString(list);
	}

}
