package dev.scx.jdbc.type_handler;

import dev.scx.format.FormatToNodeException;
import dev.scx.format.NodeToFormatException;
import dev.scx.object.ObjectNodeConvertException;
import dev.scx.reflect.ScxReflect;
import dev.scx.reflect.TypeInfo;
import dev.scx.serialize.ScxSerialize;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import static java.lang.System.Logger.Level.ERROR;

/// ObjectTypeHandler 默认都会转换为 json 字符串来存储
///
/// @author scx567888
/// @version 0.0.1
public class ObjectTypeHandler implements TypeHandler<Object> {

    private static final System.Logger logger = System.getLogger(ObjectTypeHandler.class.getName());

    private final TypeInfo javaType;

    public ObjectTypeHandler(Type type) {
        this.javaType = ScxReflect.typeOf(type);
    }

    public ObjectTypeHandler(TypeInfo type) {
        this.javaType = type;
    }

    @Override
    public void setObject(PreparedStatement ps, int i, Object parameter) throws SQLException {
        try {
            var json = ScxSerialize.toJson(parameter);
            ps.setString(i, json);
        } catch (ObjectNodeConvertException| NodeToFormatException e) {
            logger.log(ERROR, "序列化时发生错误 , 已使用 NULL !!!", e);
            ps.setNull(i, Types.NULL);
        }
    }

    @Override
    public Object getObject(ResultSet rs, int index) throws SQLException {
        var json = rs.getString(index);
        if (json == null) {
            return null;
        }
        try {
            return ScxSerialize.fromJson(json, javaType);
        } catch (FormatToNodeException| ObjectNodeConvertException e) {
            logger.log(ERROR, "反序列化时发生错误 , 已使用 NULL !!!", e);
            return null;
        }
    }

}
