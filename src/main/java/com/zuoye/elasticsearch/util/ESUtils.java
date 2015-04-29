package com.zuoye.elasticsearch.util;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @author 昨夜不是我
 * @date 2015年4月28日
 */
public class ESUtils {

	public static final String INDEX_NAME = "esindex";

	public static String getIndexName() {
		return INDEX_NAME;
	}

	public static final String TYPE_NAME = "estype";

	public static String getTypeName() {
		return TYPE_NAME;
	}

	public static Client getClient() {
		Settings settings = ImmutableSettings.settingsBuilder()
				// 指定集群名称
				.put("cluster.name", "zhangxuejun")
				// 探测集群中机器状态
				.put("client.transport.sniff", true).build();

		/*
		 * 创建客户端，所有的操作都由客户端开始，这个就好像是 JDBC 的 Connection 对象 用完记得要关闭
		 */
		TransportClient client = new TransportClient(settings) // new TransportClient(settings) 非集群
				.addTransportAddress(new InetSocketTransportAddress(
						"127.0.0.1", 9300));
		return client;
	}

	public static void closeClient(Client client) {
		if (client != null) {
			client.close();
		}
	}
	
}
