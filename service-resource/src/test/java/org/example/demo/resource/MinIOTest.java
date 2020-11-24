package org.example.demo.resource;

import com.ag777.util.file.FileUtils;
import com.ag777.util.lang.IOUtils;
import io.minio.GetObjectResponse;
import io.minio.ObjectWriteResponse;

import io.minio.errors.*;
import org.example.demo.resource.config.utils.MinIOTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * minio测试
 *
 * @author ag777 <837915770@vip.qq.com>
 * @Date: 2020/11/24 10:32
 */
@SpringBootTest
public class MinIOTest {

    @Resource
    MinIOTemplate minTemplate;

    //测试创建bucket
    @Test
    void testCreateBucket() throws Exception {
        minTemplate.makeBucket("test");
    }

    //测试上传文件对象
    @Test
    void testPutObject() throws Exception {
        File uploadFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "testUpload.txt");
        ObjectWriteResponse response = minTemplate.putObject("test",
                "base/uploadtest.txt",
                uploadFile.getAbsolutePath());
        System.out.println(response.object());
    }

    //测试删除文件对象
    @Test
    void testDeleteObject() throws Exception {
        minTemplate.removeObject("test",
                "base/uploadtest.txt");
    }

    @Test
    void testDownLoad() throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        GetObjectResponse response = minTemplate.getObject("test", "base/uploadtest.txt");
        FileUtils.write(response, "e:/测试.txt");
    }

    @Test
    void filaPath() throws FileNotFoundException {
        //如果找不到文件，请执行maven compile
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "testUpload.txt");
        System.out.println(file.exists()+":"+file.getAbsolutePath());
    }
}
