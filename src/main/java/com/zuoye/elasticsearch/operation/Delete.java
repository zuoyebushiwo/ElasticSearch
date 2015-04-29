package com.zuoye.elasticsearch.operation;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;

import com.zuoye.elasticsearch.ESUtils;

/**
 * @author 昨夜不是我
 * @date 2015年4月29日
 */
public class Delete {

	public static void main(String[] args) {
		Client client = ESUtils.getClient();
		DeleteResponse delResponse = client.prepareDelete()
				.setIndex(ESUtils.getIndexName())
				.setType(ESUtils.getTypeName()).setId("1").execute()
				.actionGet();
		System.out.println("del is found=" + delResponse.isFound());
	}

}
