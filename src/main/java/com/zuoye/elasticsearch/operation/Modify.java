package com.zuoye.elasticsearch.operation;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;

import com.zuoye.elasticsearch.ESUtils;

/**
 * @author 昨夜不是我
 * @date 2015年4月29日
 */
public class Modify {

	public static void main(String[] args) {

		Client client = ESUtils.getClient();
		GetResponse getResponse = client.prepareGet()
				.setIndex(ESUtils.getIndexName())
				.setType(ESUtils.getTypeName()).setId("1").execute()
				.actionGet();

		System.out
				.println("berfore update version=" + getResponse.getVersion());

		UpdateResponse updateResponse = client
				.prepareUpdate()
				.setIndex(ESUtils.getIndexName())
				.setType(ESUtils.getTypeName())
				.setDoc("{\"prodId\":1,\"prodName\":\"ipad5\",\"prodDesc\":\"比你想的更强大噢耶\",\"catId\":1}")
				.setId("1").execute().actionGet();

		System.out.println("更新成功， isCreated=" + updateResponse.isCreated());
		getResponse = client.prepareGet().setIndex(ESUtils.getIndexName())
				.setType(ESUtils.getTypeName()).setId("1").execute()
				.actionGet();
		System.out.println("get version=" + getResponse.getVersion());

	}

}
