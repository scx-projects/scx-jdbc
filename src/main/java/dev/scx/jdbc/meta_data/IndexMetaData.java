package dev.scx.jdbc.meta_data;

import dev.scx.jdbc.mapping.Index;

/// IndexMetaData
///
/// @author scx567888
/// @version 0.0.1
public record IndexMetaData(String name, String columnName, boolean unique) implements Index {

}
