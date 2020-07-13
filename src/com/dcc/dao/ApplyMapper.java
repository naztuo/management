package com.dcc.dao;

import com.dcc.po.Apply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyMapper {


	/**
	 * 查询总数
	 * @param news
	 * @return
	 * @throws Exception
	 */
	int countApply() throws Exception;

	/**
	 * 分页查询
	 * @param num
	 * @return
	 * @throws Exception
	 */
	List<Apply> fenyeApply(Integer num) throws Exception;

	/**
	 * 删除news
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteApply(Integer id) throws Exception;

	/**
	 * 批量删除dao
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int delsApply(String[] array) throws Exception;
	/**
	 * 单个查询
	 * @param array
	 * @return
	 * @throws Exception
	 */
	Apply findIdApply(Integer id) throws Exception;
	/**
	 * 更新
	 * @param news
	 * @return
	 * @throws Exception
	 */
	int updateApply(Apply news) throws Exception;
	/**
	 * 模糊查询
	 * @param news
	 * @return
	 * @throws Exception
	 */
	List<Apply> searchApply(@Param("title") String title) throws Exception;

}
