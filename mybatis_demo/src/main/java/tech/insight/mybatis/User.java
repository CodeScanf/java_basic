package tech.insight.mybatis;

import lombok.Data;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/23 16:13:06
 */
@Data
@Table(tableName = "user")
public class User {
    private Integer id;
    private String name;
    private Integer age;
}
