package com.astc.util;

import com.astc.file.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

/**
 * @ClassName FastClint
 * @Description 封装FastDFS的api工具类
 * @Author 彭茜奇
 * @Date 11:58 2020/4/13
 * @Version 2.1
 **/

public class FastClient {
    /**
     * @author 栗子
     * @Description 初始化FastDFS配置
     * @Date 17:12 2019/8/11
     * @return
     **/
    static {
        //初始化Fast配置文件
        String path = "fdfs_client.conf";
        String config_name = new ClassPathResource(path).getPath();
        try {
            ClientGlobal.init(config_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //文件上传
    public static String[] uploadFile(FastDFSFile fastDFSFile) {
        try {
            // 获取附件属性
            byte[] file_buff = fastDFSFile.getContent();   // 文件内容
            String ext_name = fastDFSFile.getExt();       // 文件扩展名
            NameValuePair[] meta_list = new NameValuePair[1]; //附件的备注

            meta_list[0] = new NameValuePair(fastDFSFile.getMd5());
            // 1.创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2.根据客户端获取跟踪服务端
            TrackerServer connection = trackerClient.getConnection();
            // 3.创建储存服务器客户端
            StorageClient storageClient = new StorageClient(connection, null);
            // 4.实现附件上传等操作
            String[] uploadResult = storageClient.upload_appender_file(file_buff, ext_name, meta_list);

            return uploadResult;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取文件路径
    public static String getUrl() {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、获取跟踪服务器地址
            String hostAddress = trackerServer.getInetSocketAddress().getAddress().getHostAddress();
            int port = ClientGlobal.getG_tracker_http_port();
            String url = "http://" + hostAddress + ":" + port;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //文件下载
    public static byte[] downloadFile(String group_name, String remote_filename) {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、文件下载
            byte[] file_buff = storageClient.download_file(group_name, remote_filename);
            return file_buff;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    文件删除
    public static void deleteFile(String group_name, String remote_filename) {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、文件删除
            storageClient.delete_file(group_name, remote_filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取文件附件信息
    public static FileInfo getFileInfo(String group_name, String remote_filename) {
        try {
            // 1、创建跟踪服务器客户端
            TrackerClient trackerClient = new TrackerClient();
            // 2、获取跟踪服务器
            TrackerServer trackerServer = trackerClient.getConnection();
            // 3、创建存储服务器客户端
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 4、获取文件信息
            FileInfo fileInfo = storageClient.get_file_info(group_name, remote_filename);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
