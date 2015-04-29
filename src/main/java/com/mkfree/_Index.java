package com.mkfree;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;

import java.io.IOException;

/**
 * 业务模块描述:【创建索引】
 * elasticsearch 创建索引 java api,它允许json格式并且要按照一定的规则去创建索引,使其可以用于查询

生成json文档

下面会有几种方法创建json文档
•手动拼接一个json的文档格式,使其创建索引
•你可以使用map然后转换成json
•可以利用第三方的库jackson,生成对应的json文档,使其创建索引
• 使用elasticsearch提供的工具类生成json文档格式,创建索引

 * 作者: 沈婷婷
 * 日期: 13-4-18 下午1:45
 */

@SuppressWarnings("unchecked")
public class _Index extends ESClient
{
  /**
     * 创建索引
     */
    @Test
    public void index(){
        for(int i=0; i< 10; i++){
            String id="id"+i;
            String title = "this is title "+ i;
            client.prepareIndex("blog", "post")
                .setSource(buildJson(id, title))
                .execute()
                .actionGet();
        }
    }

    /**
     * 构建Json串
     * @param id
     * @param title
     * @return
     */
    private String buildJson(String id, String title)
    {
        String json="";
        try
        {
            XContentBuilder contentBuilder = XContentFactory.jsonBuilder()
                .startObject();
            contentBuilder.field("id", id);
            contentBuilder.field("title", title);
            json = contentBuilder.endObject().string();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return json;
    }
}
