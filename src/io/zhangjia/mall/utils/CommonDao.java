package io.zhangjia.mall.utils;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用的DAO，可以对任意表做增删改查
 */
public class CommonDao extends JDBCUtils {

    /**
     * 对任意表做增删改操作
     *
     * @return
     */
    public int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // 获取连接
            conn = getConnection();
            // 创建语句对象
            pstm = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 需要设置占位符
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符
                    pstm.setObject(i + 1, params[i]);
                }
            }
            // 执行
            int i = pstm.executeUpdate();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            close(pstm, conn);
        }
        return 0;
    }

    public <T> List<T> query4BeanList(String sql, BeanResultSetHandler<T> beanResultSetHandler, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            // 获取连接
            conn = getConnection();
            // 创建语句对象
            pstm = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 需要设置占位符
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符
                    pstm.setObject(i + 1, params[i]);
                }
            }
            // 执行SQL语句
            rs = pstm.executeQuery();
            // 处理结果
            while (rs.next()) {
                T t = beanResultSetHandler.getBeanFromResultSet(rs);
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstm, conn);
        }
        return list;
    }

    public <T> T query4Bean(String sql, BeanResultSetHandler<T> beanResultSetHandler, Object... params) {
        List<T> list = query4BeanList(sql, beanResultSetHandler, params);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public <T> List<T> query4BeanList(String sql, Class<T> clazz, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            // 获取连接
            conn = getConnection();
            // 创建语句对象
            pstm = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 需要设置占位符
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符
                    pstm.setObject(i + 1, params[i]);
                    System.out.println("i" + (i + 1) + "param = " + params[i]);
                }
            }
            // 执行SQL语句
            rs = pstm.executeQuery();
            // 处理结果
            // 获取所有的属性
            Field[] fields = clazz.getDeclaredFields();
            // 获取元数据
            ResultSetMetaData metaData = rs.getMetaData();
            // 获取共有多少列
            int columnCount = metaData.getColumnCount();
            // 准备一个字符串数组，用来保存字段名
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metaData.getColumnName(i + 1);
            }
            while (rs.next()) {
                T t = clazz.newInstance();
                for (Field field : fields) {
                    // 获取属性名
                    String name = field.getName();
                    // 根据属性名拼接set方法名
                    String smn = "";
                    // System.out.println(field.getType().getName());
                    if (field.getType() == boolean.class && name.contains("is")) {
                        smn = "set" + name.substring(name.indexOf("is") + 2);
                    } else {

                        smn = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    }
                    // 根据set方法名找到set方法
                    Class<?> type = field.getType();
                    Method sm = clazz.getMethod(smn, type);
                    for (String columnName : columnNames) {
                        if (columnName.replaceAll("_", "").equalsIgnoreCase(name)) {
                            // 从ResultSet中获取值
                            Object value = rs.getObject(columnName);
                            if (value == null) {
                                break;
                            }
                            // 输出每个value的类型以及属性的类型
                            if (type == int.class || type == Integer.class) {
                                value = rs.getInt(columnName);
                                sm.invoke(t, value);
                                break;
                            }
                            if (type == double.class || type == Double.class) {
                                value = rs.getDouble(columnName);
                                sm.invoke(t, value);
                                break;
                            }

                            if (type == long.class || type == Long.class) {
                                value = rs.getLong(columnName);
                                sm.invoke(t, value);
                                break;
                            }
                            if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                                value = rs.getBoolean(columnName);
                                sm.invoke(t, value);
                                break;
                            }
                            // 执行set方法
                            // System.out.println(value.getClass());
                            sm.invoke(t, value);
                        }
                    }
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstm, conn);
        }
        return list;
    }

    public <T> T query4Bean(String sql, Class<T> clazz, Object... params) {
        List<T> list = query4BeanList(sql, clazz, params);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public List<Map<String, Object>> query4MapList(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            conn = getConnection();
            pstm = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 需要设置占位符
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符
                    pstm.setObject(i + 1, params[i]);
                }
            }
            rs = pstm.executeQuery();
            // 处理结果集
            // 获取元数据
            ResultSetMetaData metaData = rs.getMetaData();
            // 通过metaData获取共有多少列
            int columnCount = metaData.getColumnCount();
            // 准备一个字符串数组，用来存储每个列的列名
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                // 通过metaData获取字段名
                columnNames[i] = metaData.getColumnName(i + 1);
            }
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = columnNames[i];
                    map.put(columnName, rs.getObject(columnName));
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstm, conn);
        }
        return list;
    }

    public Map<String, Object> query4Map(String sql, Object... params) {
        List<Map<String, Object>> list = query4MapList(sql, params);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    protected interface BeanResultSetHandler<T> {
        T getBeanFromResultSet(ResultSet rs) throws SQLException;
    }

    // 获取单条int类型的数据
    public int query4IntData(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 创建语句对象
            pstm = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 需要设置占位符
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符
                    pstm.setObject(i + 1, params[i]);
                }
            }
            // 执行SQL语句
            rs = pstm.executeQuery();
            // 处理结果
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstm, conn);
        }
        return 0;
    }

    // 获取单条double类型的数据
    public double query4DoubleData(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 创建语句对象
            pstm = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 需要设置占位符
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符
                    pstm.setObject(i + 1, params[i]);
                }
            }
            // 执行SQL语句
            rs = pstm.executeQuery();
            // 处理结果
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstm, conn);
        }
        return 0;
    }

    // 获取单条字符串类型数据
    public String query4StringData(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // 获取连接
            conn = getConnection();
            // 创建语句对象
            pstm = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 需要设置占位符
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符
                    pstm.setObject(i + 1, params[i]);
                }
            }
            // 执行SQL语句
            rs = pstm.executeQuery();
            // 处理结果
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstm, conn);
        }
        return null;
    }

    public <T> List<T> findBypage(String sql, Class<T> clazz, int pageNo, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<T> list = new ArrayList<>();
        try {
            // 获取连接
            conn = getConnection();
            // 创建语句对象
            pstm = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                // 需要设置占位符
                for (int i = 0; i < params.length; i++) {
                    // 设置占位符
                    pstm.setObject(i + 1, params[i]);
                }
            }
            // 执行SQL语句
            rs = pstm.executeQuery();
            // 处理结果
            // 获取所有的属性
            Field[] fields = clazz.getDeclaredFields();
            // 获取元数据
            ResultSetMetaData metaData = rs.getMetaData();
            // 获取共有多少列
            int columnCount = metaData.getColumnCount();
            // 准备一个字符串数组，用来保存字段名
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metaData.getColumnName(i + 1);
            }
            while (rs.next()) {
                T t = clazz.newInstance();
                for (Field field : fields) {
                    // 获取属性名
                    String name = field.getName();
                    // 根据属性名拼接set方法名
                    String smn = "";
                    // System.out.println(field.getType().getName());
                    if (field.getType() == boolean.class && name.contains("is")) {
                        smn = "set" + name.substring(name.indexOf("is") + 2);
                    } else {

                        smn = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    }
                    // 根据set方法名找到set方法
                    Class<?> type = field.getType();
                    Method sm = clazz.getMethod(smn, type);
                    for (String columnName : columnNames) {
                        if (columnName.replaceAll("_", "").equalsIgnoreCase(name)) {
                            // 从ResultSet中获取值
                            Object value = rs.getObject(columnName);
                            if (value == null) {
                                break;
                            }
                            // 输出每个value的类型以及属性的类型
                            if (type == int.class || type == Integer.class) {
                                value = rs.getInt(columnName);
                                sm.invoke(t, value);
                                break;
                            }
                            if (type == double.class || type == Double.class) {
                                value = rs.getDouble(columnName);
                                sm.invoke(t, value);
                                break;
                            }

                            if (type == long.class || type == Long.class) {
                                value = rs.getLong(columnName);
                                sm.invoke(t, value);
                                break;
                            }
                            if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                                value = rs.getBoolean(columnName);
                                sm.invoke(t, value);
                                break;
                            }
                            // 执行set方法
                            // System.out.println(value.getClass());
                            sm.invoke(t, value);
                        }
                    }
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstm, conn);
        }
        return list;
    }

}
