package tech.insight.mybatis;

import java.lang.reflect.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/23 16:30:31
 */
public class MySqlSessionFactory {
    public static final String JDBCURL = "jdbc:mysql://localhost:3306/mybatis_db";
    public static final String DBUSER = "root";
    public static final String PASSWORD = "12345678";

    @SuppressWarnings("all")
    public <T> T getMapper(Class<T> mapperClass) {
        return (T) Proxy.newProxyInstance(MySqlSessionFactory.class.getClassLoader(), new Class[]{mapperClass},
                new invocationHandler());

    }

    static class invocationHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().startsWith("select")) {
                return invokeSelect(method, args);
            }
            return null;
        }

        private Object invokeSelect(Method method, Object[] args) {
            String sql = createSelectSql(method);
            try (Connection conn = DriverManager.getConnection(JDBCURL, DBUSER, PASSWORD);
                 PreparedStatement statement = conn.prepareStatement(sql)) {
                for (int i = 0; i < args.length; i++) {
                    Object arg = args[i];
                    if (arg instanceof Integer) {
                        statement.setInt(i + 1, (int) arg);
                    } else if (arg instanceof String) {
                        statement.setString(i + 1, arg.toString());
                    }
                }
                ResultSet res = statement.executeQuery();
                if (res.next()) {
                    return parseResule(res, method.getReturnType());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        private Object parseResule(ResultSet res, Class<?> returnType) throws Exception {
            Constructor<?> constructor = returnType.getConstructor();
            Object result = constructor.newInstance();
            Field[] declaredFields = returnType.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Object column = null;
                String name = declaredField.getName();
                if (declaredField.getType() == String.class) {
                    column = res.getString(name);
                } else if (declaredField.getType() == Integer.class) {
                    column = res.getInt(name);
                }
                declaredField.setAccessible(true);
                declaredField.set(result, column);
            }
            return result;
        }

        private String createSelectSql(Method method) {
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("SELECT ");
            List<String> selectCols = getSelectCols(method.getReturnType());
            sqlBuilder.append(String.join(",", selectCols));
            sqlBuilder.append(" FROM ");
            String tableName = getSelectTableName(method.getReturnType());
            sqlBuilder.append(tableName);
            sqlBuilder.append(" WHERE ");
            String where = getSelectWhere(method);
            sqlBuilder.append(where);
            return sqlBuilder.toString();
        }

        private String getSelectWhere(Method method) {
            return Arrays.stream(method.getParameters())
                    .map((parameter) -> {
                        Param param = parameter.getAnnotation(Param.class);
                        String collum = param.value();
                        return collum + " = ?";
                    }).collect(Collectors.joining(" and "));
        }

        private String getSelectTableName(Class<?> returnType) {
            Table table = returnType.getAnnotation(Table.class);
            if (table == null) {
                throw new RuntimeException("返回值无法确定查询表");
            }
            return table.tableName();
        }

        private List<String> getSelectCols(Class<?> returnType) {
            Field[] declaredFields = returnType.getDeclaredFields();
            return Arrays.stream(declaredFields).map(Field::getName).collect(Collectors.toList());
        }

    }
}

