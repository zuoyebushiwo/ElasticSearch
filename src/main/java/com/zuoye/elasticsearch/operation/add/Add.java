package com.zuoye.elasticsearch.operation.add;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import com.zuoye.elasticsearch.util.ESUtils;

/**
 * 在 es 中，没有增加这个概念，所谓增加，就是将一条记录存储到 es 里面。 Es 里面将这个过 程叫做索引（ index）
 * 
 * @author 昨夜不是我
 * @date 2015年4月28日
 */
public class Add {

	public static void main(String[] args) {
		
		Client client = ESUtils.getClient();
		IndexResponse indexResponse = client
				.prepareIndex()
				.setIndex(ESUtils.getIndexName())
				.setType(ESUtils.getTypeName())
				.setSource(
						"{\"prodId\":1,\"prodName\":\"ipad5\",\"prodDesc\":\"aaa\",\"catId\":1}")
				.setId("1").execute().actionGet();
		ESUtils.closeClient(client);
	}
}
