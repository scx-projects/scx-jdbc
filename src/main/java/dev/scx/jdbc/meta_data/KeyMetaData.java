package dev.scx.jdbc.meta_data;

import dev.scx.jdbc.mapping.Key;

/// KeyMetaData
///
/// @author scx567888
/// @version 0.0.1
public record KeyMetaData(String name, String columnName, boolean primary) implements Key {

}
