package com.astc.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.astc.content.feign.ContentFeign;
import com.astc.content.pojo.Content;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

/**
 * @ClassName ContentlDataEventListener
 * @Description 实现对广告数据的监听
 * @Author 彭茜奇
 * @Date 0:18 2020/5/6
 * @Version 2.1
 **/
@CanalEventListener
public class ContentDataEventListener {
    @Autowired
    private ContentFeign contentFeign;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 自定义监听器
    @ListenPoint(destination = "example", schema = {"changgou_content"}, table = {"tb_content"},
            eventType = {CanalEntry.EventType.INSERT, CanalEntry.EventType.UPDATE})
    public void enventContentData(CanalEntry.EntryType entryType, CanalEntry.RowData rowData){
        // 获取广告分类id
        String category_id = getCategoryId(rowData, "category_id");
        // 获取广告列表数据
        Result result = contentFeign.findContentsByCategoryId(Long.parseLong(category_id));
        List<Content> list = (List<Content>) result.getData();
        // 将数据写入redis
        stringRedisTemplate.boundValueOps("content_" + category_id).set(JSON.toJSONString(list));
    }

    private String getCategoryId(CanalEntry.RowData rowData, String category_id) {
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        if (afterColumnsList != null && afterColumnsList.size() > 0){
            for (CanalEntry.Column column : afterColumnsList) {
                String columnName = column.getName();
                if (category_id.equals(columnName)){
                    return column.getValue();
                }
            }
        }
        return null;
    }
}
