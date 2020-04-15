package com;

import com.astc.file.FastDFSFile;
import com.astc.util.FastClient;
import org.junit.Test;

/**
 * @ClassName astc
 * @Description
 * @Author 彭茜奇
 * @Date 19:40 2020/4/13
 * @Version 2.1
 **/

public class astc {
    @Test
    public void testDeleteFile(){
        String group_name = "group1";
        String remote_filename = "M00/00/00/wKjThF1P5z2ANGgiAAL6RQQtvo0582.jpg";
        FastClient.deleteFile(group_name, remote_filename);
    }
}
