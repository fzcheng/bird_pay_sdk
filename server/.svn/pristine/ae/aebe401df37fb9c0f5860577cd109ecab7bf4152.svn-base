package com.cheyooh.service.dal.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.CallableStatementHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.SimpleStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.cheyooh.service.dal.diagnosis.Diagnosis;
import com.cheyooh.service.dal.diagnosis.SqlLog;
import com.cheyooh.service.dal.dialect.BatisDialect;
import com.cheyooh.service.dal.dialect.Dialect;
import com.cheyooh.tools.cfg.EnvUtil;
import com.cheyooh.tools.log.Logger;

public class SqlStatementHandler implements StatementHandler {
	static Logger logger = new Logger(SqlStatementHandler.class);

	private static ThreadLocal<List<String>> sqls = new ThreadLocal<List<String>>();

	public static void startLogSql() {
		sqls.set(new ArrayList<String>());
	}

	public static List<String> getLogSql() {
		List<String> ret = sqls.get();
		if (ret != null) {
			sqls.remove();
		}
		return ret;
	}

	public static void addLogSql(String sql) {
		List<String> ret = sqls.get();
		if (ret != null) {
			ret.add(sql);
		}
	}

	public static void logSql(Date startTime, Statement statement, BoundSql boundSql, MappedStatement mappedStatement) {
		Date endTime = new Date();
		long mills = endTime.getTime() - startTime.getTime();

		StringBuilder sql = new StringBuilder();
		if (boundSql != null) {
			sql.append(boundSql.getSql());
		} else {
			sql.append(statement.toString());
			int p = sql.indexOf(":");
			if (p > 0) {
				sql = sql.delete(0, p + 1);
			}
		}

		int lm = EnvUtil.env.getInt("dal.diagnosis.longsql.time", 500);
		if (lm >= 0 && mills >= lm) {
			// 检查是否为诊断SQL语句, 防止循环写入
			if (isDiagnosisSql(sql) == false) {
				SqlLog log = new SqlLog();
				log.setApp("mybatis");
				log.setEndTime(endTime);
				log.setSql(sql.toString());
				log.setStartTime(startTime);
				log.setStatement(mappedStatement.getId());
				log.setTime((int) mills);
				log.setTraceId("");

				Diagnosis.addLog(log);
			}
		}

		sql.append("  /*[").append(mills).append(" ms]*/");
		addLogSql(sql.toString());
	}

	private static boolean isDiagnosisSql(StringBuilder sql) {
		String c=Diagnosis.getWriterClass();
		
		Exception e = new Exception();
		StackTraceElement[] stes = e.getStackTrace();
		for (StackTraceElement ste : stes) {
			String className = ste.getClassName();
			
			if(c.equals(className)){
				return true;				 
			}
		}
		return false;
	}

	public Statement prepare(Connection connection) throws SQLException {
		return delegate.prepare(connection);
	}

	public void parameterize(Statement statement) throws SQLException {
		delegate.parameterize(statement);
	}

	public void batch(Statement statement) throws SQLException {
		delegate.batch(statement);
	}

	public int update(Statement statement) throws SQLException {
		return delegate.update(statement);
	}

	@SuppressWarnings("rawtypes")
	public List query(Statement statement, ResultHandler resultHandler) throws SQLException {
		return delegate.query(statement, resultHandler);
	}

	public BoundSql getBoundSql() {
		return delegate.getBoundSql();
	}

	public ParameterHandler getParameterHandler() {
		return delegate.getParameterHandler();
	}

	private final StatementHandler delegate;

	public SqlStatementHandler(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) {

		switch (ms.getStatementType()) {
		case STATEMENT:
			delegate = new SelfSimpleStatementHandler(executor, ms, parameter, rowBounds, resultHandler);
			break;
		case PREPARED:
			delegate = new SelfPreparedStatementHandler(executor, ms, parameter, rowBounds, resultHandler);
			break;
		case CALLABLE:
			delegate = new SelfCallableStatementHandler(executor, ms, parameter, rowBounds, resultHandler);
			break;
		default:
			throw new ExecutorException("Unknown statement type: " + ms.getStatementType());
		}

	}

	private static Statement createStatement(Connection connection, MappedStatement mappedStatement, BoundSql boundSql, RowBounds rowBounds)
			throws SQLException {
		String sql = boundSql.getSql();
		if (rowBounds != null && rowBounds != RowBounds.DEFAULT) {
			Dialect dialect = BatisDialect.getDialect(mappedStatement.getConfiguration());
			sql = dialect.getLimitString(sql, rowBounds.getOffset(), rowBounds.getLimit());
		}

		switch (mappedStatement.getStatementType()) {
			case STATEMENT:
				if (mappedStatement.getResultSetType() != null) {
					return connection.createStatement(mappedStatement.getResultSetType().getValue(), ResultSet.CONCUR_READ_ONLY);
				} else {
					return connection.createStatement();
				}
			case PREPARED:
				if (mappedStatement.getKeyGenerator() instanceof Jdbc3KeyGenerator) {
					return connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				} else if (mappedStatement.getResultSetType() != null) {
					return connection.prepareStatement(sql, mappedStatement.getResultSetType().getValue(), ResultSet.CONCUR_READ_ONLY);
				} else {
					return connection.prepareStatement(sql);
				}
	
			case CALLABLE:
				if (mappedStatement.getResultSetType() != null) {
					return connection.prepareCall(sql, mappedStatement.getResultSetType().getValue(), ResultSet.CONCUR_READ_ONLY);
				} else {
					return connection.prepareCall(sql);
				}
			default:
				throw new ExecutorException("Unknown statement type: " + mappedStatement.getStatementType());
		}

	}

	public static class SelfSimpleStatementHandler extends SimpleStatementHandler {
		public SelfSimpleStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) {
			super(executor, mappedStatement, parameter, rowBounds, resultHandler);
		}

		public int update(Statement statement) throws SQLException {
			Date startTime = new Date();
			try {
				return super.update(statement);
			} finally {
				logSql(startTime, statement, boundSql, this.mappedStatement);
			}
		}

		public void batch(Statement statement) throws SQLException {
			Date startTime = new Date();
			try {
				super.batch(statement);
			} finally {
				logSql(startTime, statement, boundSql, this.mappedStatement);
			}
		}

		@SuppressWarnings("rawtypes")
		public List query(Statement statement, ResultHandler resultHandler) throws SQLException {
			Date startTime = new Date();
			try {
				return super.query(statement, resultHandler);
			} finally {
				logSql(startTime, statement, boundSql, this.mappedStatement);
			}
		}

		protected Statement instantiateStatement(Connection connection) throws SQLException {
			return createStatement(connection, mappedStatement, boundSql, rowBounds);
		}
	}

	public static class SelfPreparedStatementHandler extends PreparedStatementHandler {
		public SelfPreparedStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds,
				ResultHandler resultHandler) {
			super(executor, mappedStatement, parameter, rowBounds, resultHandler);
		}

		public int update(Statement statement) throws SQLException {
			Date startTime = new Date();
			try {
				return super.update(statement);
			} finally {
				logSql(startTime, statement, null, this.mappedStatement);
			}
		}

		public void batch(Statement statement) throws SQLException {
			Date startTime = new Date();
			try {
				super.batch(statement);
			} finally {
				logSql(startTime, statement, null, this.mappedStatement);
			}
		}

		@SuppressWarnings("rawtypes")
		public List query(Statement statement, ResultHandler resultHandler) throws SQLException {
			Date startTime = new Date();
			try {
				return super.query(statement, resultHandler);
			} finally {
				logSql(startTime, statement, null, this.mappedStatement);
			}
		}

		protected Statement instantiateStatement(Connection connection) throws SQLException {
			return createStatement(connection, mappedStatement, boundSql, rowBounds);
		}
	}

	public static class SelfCallableStatementHandler extends CallableStatementHandler {

		public SelfCallableStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds,
				ResultHandler resultHandler) {
			super(executor, mappedStatement, parameter, rowBounds, resultHandler);

		}

		public int update(Statement statement) throws SQLException {
			Date startTime = new Date();
			try {
				return super.update(statement);
			} finally {
				logSql(startTime, statement, null, this.mappedStatement);
			}
		}

		public void batch(Statement statement) throws SQLException {
			Date startTime = new Date();
			try {
				super.batch(statement);
			} finally {
				logSql(startTime, statement, null, this.mappedStatement);
			}
		}

		@SuppressWarnings("rawtypes")
		public List query(Statement statement, ResultHandler resultHandler) throws SQLException {
			Date startTime = new Date();
			try {
				return super.query(statement, resultHandler);
			} finally {
				logSql(startTime, statement, null, this.mappedStatement);
			}
		}

		protected Statement instantiateStatement(Connection connection) throws SQLException {
			return createStatement(connection, mappedStatement, boundSql, rowBounds);
		}

	}
}
