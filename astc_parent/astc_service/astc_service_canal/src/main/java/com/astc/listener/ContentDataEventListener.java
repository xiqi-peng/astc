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
    @Autowired(required = false)
    private ContentFeign contentFeign;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 自定义监听器
    @ListenPoint(destination = "example", schema = "changgou_content", table = {"tb_content"},
            eventType = {CanalEntry.EventType.INSERT, CanalEntry.EventType.UPDATE})
    public void onEventContent(CanalEntry.EntryType entryType, CanalEntry.RowData rowData){
        // 获取分类id
        String categoryId = getColumnValue(rowData, "category_id");
        // 通过分类id查询广告列表
        Result<List<Content>> result = contentFeign.findContentsByCategoryId(Long.parseLong(categoryId));
        // 存入redis
        stringRedisTemplate.boundValueOps("content_" + categoryId).set(JSON.toJSONString(result.getData()));
    }

    // 获取对应列的值
    private String getColumnValue(CanalEntry.RowData rowData, String columnName) {
        List<CanalEntry.Column> list = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : list) {
            if (columnName.equals(column.getName())){
                return column.getValue();
            }
        }
        return null;
    }
}
