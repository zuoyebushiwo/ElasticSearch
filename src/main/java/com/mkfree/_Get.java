package com.mkfree;

import org.elasticsearch.action.get.GetResponse;
import org.junit.Test;

import java.util.Map;

/**
 * 业务模块描述:【xxxxx】
 * 它允许json的格式获取一个索引
    参数(index,type,id)
    •index 索引名称
    •type 索引类型
    •id 索引id (前面是自动构建json串，未指定id，所以默认elasticsearch自动生成了一个_uid，如文档_uid是post#5Oklgp7MRWO5UekVf4wyUA 格式为:索引类型#索引id)
 * 作者: 沈婷婷
 * 日期: 13-4-18 下午2:01
 */

@SuppressWarnings("unchecked")
public class _Get extends ESClient
{

    @Test
	public void get() {
		GetResponse response = client.prepareGet("blog", "post", "5Oklgp7MRWO5UekVf4wyUA").execute().actionGet();
		// 下面是不在多线程操作,他默认为.setOperationThreaded(true)
		// GetResponse response = client.prepareGet("twitter", "tweet","1").setOperationThreaded(false).execute().actionGet();

         // 获取请求头
        Map headers = response.getHeaders();
		System.out.println(headers);
        // 判断索引是否存在
		boolean exists = response.exists();
		System.out.println(exists);
        // 获取索引,并且打印出索引内容
		String sourceString = response.sourceAsString();
		System.out.println(sourceString);
        // 获取索引id
		String id = response.getId();
		System.out.println(id);
        // 获取索引的内容是否为空,下面还有一些方法,我没有一一列举出来,大家可以试试
		boolean sourceEmpty = response.isSourceEmpty();
		System.out.println(sourceEmpty);
	}
}
