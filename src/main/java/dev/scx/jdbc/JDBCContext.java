package dev.scx.jdbc;

import dev.scx.jdbc.dialect.Dialect;
import dev.scx.jdbc.sql.SQLRunner;

import javax.sql.DataSource;

import static dev.scx.jdbc.dialect.DialectSelector.findDialect;

/// JDBCContext
///
/// @author scx567888
/// @version 0.0.1
public class JDBCContext {

    private final DataSource dataSource;
    private final Dialect dialect;
    private final SQLRunner sqlRunner;

    public JDBCContext(DataSource dataSource) {
        this.dataSource = dataSource;
        this.dialect = findDialect(dataSource);
        this.sqlRunner = new SQLRunner(this);
    }

    public SQLRunner sqlRunner() {
        return sqlRunner;
    }

    public Dialect dialect() {
        return this.dialect;
    }

    public DataSource dataSource() {
        return this.dataSource;
    }

}
