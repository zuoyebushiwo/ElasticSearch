package com.mkfree;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.After;
import org.junit.Before;

/**
 * 业务模块描述:【xxxxx】
 * 作者: 沈婷婷
 * 日期: 13-4-18 上午11:49
 */

@SuppressWarnings("unchecked")
public class ESClient
{
    public Client client;

    @Before
    public void initESClient(){
        //配置你的es,现在这里只配置了集群的名,默认是elasticsearch,跟服务器的相同
        Settings settings = ImmutableSettings.settingsBuilder()
            .put("cluster.name", "elasticsearch")
            .build();
        //这里可以同时连接集群的服务器,可以多个,并且连接服务是可访问的
        client = new TransportClient(settings)
            .addTransportAddress(new InetSocketTransportAddress("192.168.85.3", 9300));
    }

    @After
    public void closeESClient(){
        if(null != client)
            client.close();
    }
}
