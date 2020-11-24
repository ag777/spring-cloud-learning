package org.example.demo.resource.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 装载minio配置项
 * @author ag777 <837915770@vip.qq.com>
 * @Date: 2020/11/23 17:55
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinIOProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
}
