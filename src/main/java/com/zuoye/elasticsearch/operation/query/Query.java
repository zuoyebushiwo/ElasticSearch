package com.zuoye.elasticsearch.operation.query;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;

import com.zuoye.elasticsearch.util.ESUtils;

/**
 * @author 昨夜不是我
 * @date 2015年4月29日
 */
public class Query {

	public static void main(String[] args) {
		Client client = ESUtils.getClient();
		GetResponse getResponse = client.prepareGet()
				.setType("producer").execute()
				.actionGet();
		System.out.println("get=" + getResponse.getSourceAsString());
	}

}
