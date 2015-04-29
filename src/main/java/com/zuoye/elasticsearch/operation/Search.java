package com.zuoye.elasticsearch.operation;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.zuoye.elasticsearch.ESUtils;

/**
 * @author 昨夜不是我
 * @date 2015年4月29日
 */
public class Search {

	public static void main(String[] args) {

		Client client = ESUtils.getClient();

		QueryBuilder query = QueryBuilders.queryString("user*");
		SearchResponse response = client.prepareSearch(ESUtils.INDEX_NAME)
		// 设置查询条件,
				.setQuery(query).setFrom(0).setSize(600).execute().actionGet();
		/**
		 * SearchHits是SearchHit的复数形式，表示这个是一个列表
		 */
		SearchHits shs = response.getHits();
		System.out.println("总共有： " + shs.hits().length);
		for (SearchHit hit : shs) {
			System.out.println(hit.getSourceAsString());
		}
		client.close();
	}

}
