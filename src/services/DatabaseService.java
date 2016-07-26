package services;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import app.model.vo.UserVO;

public class DatabaseService {

	Connection connection;

	private String[] dbpath = { "assets/db/", "dbname", ".db" };

	static public final String 
		QUERY_CREATE_TABLE 	= "CREATE TABLE IF NOT EXISTS "
	, 	QUERY_UPDATE	 	= "UPDATE "
	, 	QUERY_INSERT_INTO 	= "INSERT INTO "
	, 	QUERY_SELECT_ALL 	= "SELECT * FROM "
	, 	QUERY_PRIMARY_KEY 	= " PRIMARY KEY"
	,	QUERY_NOT_NULL 		= " NOT NULL"
	, 	QUERY_TYPE_TEXT 	= " TEXT" + QUERY_NOT_NULL
	,	QUERY_TYPE_INT 		= " INTEGER" + QUERY_NOT_NULL
	, 	QUERY_VALUES	 	= " VALUES("
	, 	QUERY_BRACKET_R 	= " ("
	, 	QUERY_BRACKET_L 	= ") "
	, 	QUERY_ORDER_BY		= " ORDER BY "
	, 	QUERY_LIMIT			= " LIMIT "
	, 	QUERY_SET			= " SET "
	, 	QUERY_WHERE			= " WHERE "
	;

	static private DatabaseService _instance = new DatabaseService();
	static public DatabaseService getInstance() { return _instance; }
	private DatabaseService() { }

	static private final Class 
		STRING_CLASS 	= String.class
	,	LONG_CLASS 		= long.class
	;
	
	public void initialize(String dbname) throws Exception {
		dbpath[1] = dbname;
		Class.forName("org.sqlite.JDBC");
	    String path = String.join("", dbpath);
		connection = DriverManager.getConnection("jdbc:sqlite:" + path);
	}

	public boolean createTableFromClass(Class value, String primaryKey) throws Exception {
		String tableName = value.getSimpleName();
		ArrayList<String> query = new ArrayList<String>();
		ArrayList<String> params = new ArrayList<String>();
		
		Field[] fields = value.getDeclaredFields();
		ArrayList<String> paramFields = new ArrayList<String>();

		for (Field field : fields) {
			paramFields.clear();

			String paramName = field.getName();
			Class paramType = field.getType();

			String paramTypeName = QUERY_TYPE_INT;
			if (paramType == STRING_CLASS) {
				paramTypeName = QUERY_TYPE_TEXT;
			}

			paramFields.add(paramName);
			paramFields.add(paramTypeName);

			if (paramName == primaryKey)
				paramFields.add(QUERY_PRIMARY_KEY);

			params.add(String.join("", paramFields));
		}

		query.add(QUERY_CREATE_TABLE);
		query.add(tableName);
		query.add(QUERY_BRACKET_R);
		query.add(String.join(", ", params));
		query.add(QUERY_BRACKET_L);

		String createTableQuery = String.join("", query);

		System.out.println("DatabaseService > createTableFromClass > " + tableName);
		System.out.println("DatabaseService > query > " + createTableQuery);

		Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        statement.executeUpdate(createTableQuery);

		return true;
	}
	
	public ArrayList<Object> retrieveObjectWithCriteria(Class tableClass, String criteria) throws Exception 
	{
		String tableName = tableClass.getSimpleName();
		Constructor constructor = tableClass.getConstructor();
		Field[] fields = tableClass.getDeclaredFields();
		ArrayList<String> query = new ArrayList<String>();
		Statement statement = connection.createStatement();
		ArrayList<Object> result = new ArrayList<Object>();
		
		query.add(QUERY_SELECT_ALL);
		query.add(tableName);
		query.add(criteria);
		
		String createTableQuery = String.join("", query);
		
		statement.setQueryTimeout(30);
		ResultSet rs = statement.executeQuery(createTableQuery);
		
		while(rs.next())
        {
			Object obj = constructor.newInstance();
			for (Field field : fields) {
				String paramName = field.getName();
				field.set(obj, rs.getObject(paramName));
			}
			result.add(obj);
		}

		return result;
	}
	
	public void storeObjectToTable(Object value) throws Exception 
	{
		Class table = value.getClass();
		String tableName = table.getSimpleName();
		Field[] fields = table.getDeclaredFields();
		ArrayList<String> query = new ArrayList<String>();
		ArrayList<String> params = new ArrayList<String>();

		for (Field field : fields) {
			Class paramType = field.getType();
			String paramValue = field.get(value).toString();
			if (paramType == STRING_CLASS) {
				paramValue = "'" + paramValue + "'";
			}
			params.add(paramValue);
		}
		
		String paramsValues = String.join("", query);
		
		query.add(QUERY_INSERT_INTO);
		query.add(tableName);
		query.add(QUERY_VALUES);
		query.add(String.join(", ", params));
		query.add(QUERY_BRACKET_L);
		
		String createTableQuery = String.join("", query);
		
		System.out.println("DatabaseService > query > " + createTableQuery);
		
		Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        statement.executeUpdate(createTableQuery);
    }
	
	public void updateObjectParams(Class tableClass, String[] params, Object[] values, String criteria) throws Exception
	{
		String tableName = tableClass.getSimpleName();
		ArrayList<String> query = new ArrayList<String>();
		ArrayList<String> paramsValues = new ArrayList<String>();
		
		for (int i = 0; i < params.length; i++) {
			String param = params[i];
			Object value = values[i];
			paramsValues.add(param + "='"+value+"'");
		}
		
		query.add(QUERY_UPDATE);
		query.add(tableName);
		query.add(QUERY_SET);
		query.add(String.join(",", paramsValues));
		
		String createTableQuery = String.join("", query);
		
		System.out.println("DatabaseService > query > " + createTableQuery);
		
		Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        statement.executeUpdate(createTableQuery);
	}
}
