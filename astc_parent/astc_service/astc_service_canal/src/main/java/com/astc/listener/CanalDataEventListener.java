package com.astc.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.DeleteListenPoint;
import com.xpand.starter.canal.annotation.InsertListenPoint;
import com.xpand.starter.canal.annotation.UpdateListenPoint;

import java.util.List;

/**
 * @ClassName CanalDataEventListener
 * @Description 实现对表增删改操作的监听
 * @Author 彭茜奇
 * @Date 2:23 2020/5/5
 * @Version 2.1
 **/

@CanalEventListener
public class CanalDataEventListener {

    /**
     * @param entryType 监控对数据库操作的事件
     * @param rowData   操作的行数据
     * @return void
     * @author 栗子
     * @Description
     * @Date 12:29 2019/10/20
     **/
    @InsertListenPoint
    public void onEventInsert(CanalEntry.EntryType entryType, CanalEntry.RowData rowData) {
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        for (CanalEntry.Column column : afterColumnsList) {
            String columnName = column.getName();   // 列名称
            String value = column.getValue();       // 列对应的值
            System.out.println("列名：" + columnName + "列值：" + value);
        }
    }

    /**
     * @param entryType 监控对数据库操作的事件
     * @param rowData   操作的行数据
     * @return void
     * @author 栗子
     * @Description
     * @Date 12:29 2019/10/20
     **/
    @UpdateListenPoint
    public void onEventUpdate(CanalEntry.EntryType entryType, CanalEntry.RowData rowData) {
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList(); // 更新前的数据
        for (CanalEntry.Column column : beforeColumnsList) {
            String columnName = column.getName();   // 列名称
            String value = column.getValue();       // 列对应的值
            System.out.println("列名：" + columnName + "列值：" + value);
        }
        System.out.println("------------更新前后的数据--------------");
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();   // 更新后的数据
        for (CanalEntry.Column column : afterColumnsList) {
            String columnName = column.getName();   // 列名称
            String value = column.getValue();       // 列对应的值
            System.out.println("列名：" + columnName + "列值：" + value);
        }
    }

    /**
     * @param entryType 监控对数据库操作的事件
     * @param rowData   操作的行数据
     * @return void
     * @author 栗子
     * @Description
     * @Date 12:29 2019/10/20
     **/
    @DeleteListenPoint
    public void onEventDelete(CanalEntry.EntryType entryType, CanalEntry.RowData rowData) {
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList(); // 更新前的数据
        for (CanalEntry.Column column : beforeColumnsList) {
            String columnName = column.getName();   // 列名称
            String value = column.getValue();       // 列对应的值
            System.out.println("列名：" + columnName + "列值：" + value);
        }
    }
}
