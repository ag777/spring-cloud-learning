package org.example.demo.other.config.activemq.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ag777 <837915770@vip.qq.com>
 * @Description activemq传输对象
 * @Date: 2020/11/23 15:32
 */
@Data
@AllArgsConstructor
public class QueenMessage implements Serializable {
    private String title;
    private String content;
}
