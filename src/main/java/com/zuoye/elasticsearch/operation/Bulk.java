package com.zuoye.elasticsearch.operation;


import java.security.SecureRandom;
import java.util.Random;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;

import com.zuoye.elasticsearch.ESUtils;
import com.zuoye.elasticsearch.Utils;
import com.zuoye.elasticsearch.operation.model.User;

/**
 * Bulk，英文单词是散装，有多并且大的意思。在这里就是批量组装查询或者索引数据的意思。 通常，如果一个程序有批量接口，则性能肯定比单个数据处理的要快。在
 * es 中，索引与搜 索是一个互相矛盾和对立的。如果你希望查询的速度快，则应该让索引尽量少的改变，如果 你希望索引的速度快些，则查询的性能必将降低。 ES
 * 索引文档，通常是比较耗费性能的， 而且会影响到查询的速度。但如果一次索引多条数据，则 es 会将这一批数据一起刷入磁盘， 提高性能。 Bulk
 * 就是这样一个批量接口，可以将很多待索引的数据一次发生给 ES，然后 ES 将这些数据批量的刷入内存和磁盘，从而提高性能。另外， ES
 * 不仅有批量索引的接口，还 有批量查询的接口。 我们现在假设有一个 User 对象，需要批量索引到 es 中。代码如下：
 * 
 * @author 昨夜不是我
 * @date 2015年4月29日
 */
public class Bulk {

	public static void main(String[] args) {

		Client client = ESUtils.getClient();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		User user = new User();
		for (int i = 1; i < 40001; i++) {
			user.setName("user_" + i);
			SecureRandom random = new SecureRandom();
			long l = Math.abs(random.nextLong());
			user.setWeight(l);
			user.setMarried(l % 1 == 0 ? true : false);
			user.setAge(l % 2 == 0 ? 28 : 82);
			IndexRequestBuilder ir = client.prepareIndex(ESUtils.INDEX_NAME,
					ESUtils.USER_TYPE, String.valueOf(i)).setSource(
					Utils.toJson(user));
			bulkRequest.add(ir);
		}
		long beginTime = System.currentTimeMillis();
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		long useTime = System.currentTimeMillis() - beginTime;
		// 1406ms
		System.out.println("useTime:" + useTime);
		if (bulkResponse.hasFailures()) {
			// process failures by iterating through each bulk response item
		}

	}

}
