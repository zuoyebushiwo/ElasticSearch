package com.mkfree;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import java.util.Map;

/**
 * 业务模块描述:【xxxxx】
 * 作者: 沈婷婷
 * 日期: 13-4-18 下午1:55
 */

@SuppressWarnings("unchecked")
public class _Search extends ESClient
{
    @Test
    public void search(){
        search("title");
    }

    private void search(String q){
        // 创建查询索引
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("blog");
        // 设置查询索引类型
        searchRequestBuilder.setTypes("post");
        // 设置查询类型
		// 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
		// 2.SearchType.SCAN = 扫描查询,无序
		// 3.SearchType.COUNT = 不设置的话,这个为默认值,还有的自己去试试吧
        searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        // 设置查询关键词
		// fieldQuery 这个必须是你的索引字段哦,不然查不到数据,这里我只设置两个字段 id ,title
		searchRequestBuilder.setQuery(QueryBuilders.fieldQuery("title", q));
		// 设置查询数据的位置,分页用吧
		searchRequestBuilder.setFrom(0);
		// 设置查询结果集的最大条数
		searchRequestBuilder.setSize(60);
		// 设置是否按查询匹配度排序
		searchRequestBuilder.setExplain(true);
		// 最后就是返回搜索响应信息
		SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHits searchHits = response.getHits();
		SearchHit[] hits = searchHits.getHits();
        System.out.println("hit:" + hits.length);
		for (int i = 0; i < hits.length; i++) {
			SearchHit hit = hits[i];
			Map result = hit.getSource();
			System.out.println(result);
		}
		System.out.println("search success ..");

    }
}
