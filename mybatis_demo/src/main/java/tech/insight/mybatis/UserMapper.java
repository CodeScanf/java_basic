package tech.insight.mybatis;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/23 16:13:18
 */
public interface UserMapper {
    User selectById(@Param("id") int id);
}
