package com.mkfree;

import org.elasticsearch.action.delete.DeleteResponse;
import org.junit.Test;

import java.util.Map;

/**
 * 业务模块描述:【删除索引】
 *  * 它允许json的格式获取一个索引
    参数(index,type,id)
    •index 索引名称
    •type 索引类型
  •id 索引id (前面是自动构建json串，未指定id，所以默认elasticsearch自动生成了一个_uid，如文档_uid是post#5Oklgp7MRWO5UekVf4wyUA 格式为:索引类型#索引id)
 * 作者: 沈婷婷
 * 日期: 13-4-18 下午2:06
 */

@SuppressWarnings("unchecked")
public class _Delete extends ESClient
{
    @Test
	public void delete() {
		DeleteResponse response = client.prepareDelete("blog", "post", "5Oklgp7MRWO5UekVf4wyUA").execute().actionGet();
		// 下面是不在多线程操作,他默认为.setOperationThreaded(true)
		// DeleteResponse response = client.prepareDelete("twitter", "tweet","1").setOperationThreaded(false).execute().actionGet();
		// 返回索引是否存在,存在删除
        boolean isNotFound = response.isNotFound();
		System.out.println(isNotFound);
        // 返回索引是否存在,存在删除
		boolean notFound = response.notFound();
		System.out.println(notFound);
        // 返回响应头
		Map headers = response.getHeaders();
		System.out.println(headers);
	}
}
