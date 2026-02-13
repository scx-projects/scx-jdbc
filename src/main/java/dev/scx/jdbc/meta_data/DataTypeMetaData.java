package dev.scx.jdbc.meta_data;

import dev.scx.jdbc.JDBCType;
import dev.scx.jdbc.mapping.DataType;

/// DataTypeMetaData
///
/// @author scx567888
/// @version 0.0.1
public record DataTypeMetaData(JDBCType jdbcType, String name, Integer length) implements DataType {

}
