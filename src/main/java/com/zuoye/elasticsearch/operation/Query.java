package com.zuoye.elasticsearch.operation;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;

import com.zuoye.elasticsearch.ESUtils;

/**
 * @author 昨夜不是我
 * @date 2015年4月29日
 */
public class Query {

	public static void main(String[] args) {
		
		Client client = ESUtils.getClient();
		GetResponse getResponse = client.prepareGet()
				.setIndex(ESUtils.getIndexName())
				.setType(ESUtils.getTypeName()).setId("2").execute()
				.actionGet();
		System.out.println("get=" + getResponse.getSourceAsString());
	}

}
