package io.zhangjia.mall.api.oss;

import com.aliyun.oss.OSSClient;

import java.io.*;

public class OSS {
    //    public void createBucket(){
//        // Endpoint以杭州为例，其它Region请按实际情况填写。
//        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
//// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//        String accessKeyId = "<yourAccessKeyId>";
//        String accessKeySecret = "<yourAccessKeySecret>";
//        String bucketName = "<yourBucketName>";
//
//// 创建OSSClient实例。
//        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//
//// 创建存储空间。
//        ossClient.createBucket(bucketName);
//
//// 关闭OSSClient。
//        ossClient.shutdown();
//    }
    public static void main(String[] args) {
    }

    public void uplocad(String imgUrl) throws FileNotFoundException {

        String endpoint = "http://oss-cn-qingdao.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "keyid";
        String accessKeySecret = "secret";
        String bucketName = "zhangjia";
        String objectName = "aa.jpg";

//
//// 创建OSSClient实例。
//        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//
//// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
//        ossClient.putObject(bucketName, objectName, new File("C:\\Users\\zhang\\Desktop\\11.jpg"));
//
//// 关闭OSSClient。
//        ossClient.shutdown();

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        InputStream inputStream = new FileInputStream("<yourlocalFile>");
        ossClient.putObject(bucketName, objectName, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

}
