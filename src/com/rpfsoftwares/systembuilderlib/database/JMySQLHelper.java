package com.rpfsoftwares.systembuilderlib.database;

/*Copyright (c) 2016 Rosário Pereira Fernandes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

/**
*
*Class to help you connect with <code>MySQL</code> databases.
*Simply pass your database settings to the constructor
*and call the helper methods (insert, select, update, delete) from
*the object you created.
*
*Example: <code>JMySqlHelper db= new JMySqlHelper("localhost","database1","root","password")</code>
* @author Rosário Pereira Fernandes
* @version 1.0.0
* @since 1.0.0
**/

public class JMySQLHelper {
	private Connection connection;
	private boolean debugging;
	
	/**
	 * Instantiates the Helper. Used to open the database connection.
	 * @param server - MySQL Server Address (e.g: <code>localhost,127.0.0.1</code>)
	 * @param database - database name
	 * @param username - username used to access the database
	 * @param password - password for this user.
	 */
	public JMySQLHelper(String server, String database, String username, String password)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+server+"/"+database,
					username, password);
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorCode(),
					JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Insert data in a specific table.
	 * @param tableName - Name of the table where we want to insert data
	 * @param pairs - JContentValues with the attribute name binded to the value
	 * to insert
	 * @return boolean - returns true if it is successfully inserted, or false if not
	 */
	public boolean insert(String tableName,JContentValues pairs)
	{
		String[]columns=pairs.getKeysArray();
		String[]values=pairs.getValuesArray();
		String query="INSERT INTO "+tableName+" ("+columns[0];
		for(int i=1;i<columns.length;i++)
			query=query+","+columns[i];
		query=query+") VALUES ('" + values[0];
		for(int i=1;i<values.length;i++)
			query=query+"','"+values[i];
		query=query+"')";
		return executeQuery(query);
	}
	
	/**
	 * Allows you to insert multiple lines in a table
	 * @param tableName - Name of the table where we want to insert data
	 * @param pairs - JContentValues Array with the attribute name binded to the value
	 * to insert
	 * @return the number of rows successfully inserted
	 */
	public int multipleInsert(String tableName,JContentValues [] pairs)
	{
		int rowsInserted=0;
		for(int j =0;j<pairs.length;j++)
		{
			String[]columns=pairs[j].getKeysArray();
			String[]values=pairs[j].getValuesArray();
			String query="INSERT INTO "+tableName+" ("+columns[0];
			for(int i=1;i<columns.length;i++)
				query=query+","+columns[i];
			query=query+") VALUES ('" + values[0];
			for(int i=1;i<values.length;i++)
				query=query+"','"+values[i];
			query=query+"')";
			if(executeQuery(query))
				rowsInserted++;
		}
		return rowsInserted;
	}

	/**
	 * Get table rows
	 * @param tableName - Name of the table where we want to query for data
	 * @param columns - Columns to be selected
	 * @param where - the WHERE query
	 * @return - ResultSet containing the table values, or null if couldn't
	 * query for data.
	 */
	public ResultSet select(String tableName, String [] columns, String where)
	{
		String query="SELECT ";
		if(columns==null ||columns.length<=0)
		{
			query=query+"*";
		}
		else
		{
			query=query+columns[0];
			if(columns.length>=1)
				for(int i=1;i<columns.length;i++)
					query=query+", "+columns[i];
		}
		query=query+" FROM "+tableName;
		if(where!=null)
			query=query+where;
		if(debugging)
			System.out.println(query);
		try
		{
			PreparedStatement st= connection.prepareStatement(query);
			return st.executeQuery();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorCode(),
					JOptionPane.ERROR_MESSAGE);
			return null;
		}//TODO
	}
	
	/**
	 * Get table rows in a specific order, limit and offset
	 * @param tableName Name of the table where we want to query for data.
	 * @param columns Columns to be selected
	 * @param where the WHERE query
	 * @param order the ORDER BY query
	 * @param limit the LIMIT query
	 * @param offset the OFFSET query
	 * @return ResultSet with the table data, or null if couldn't query
	 * for data
	 */
	public ResultSet select(String tableName,String[]columns,String where,
			String order,int limit, int offset)
	{
		return select(tableName,columns,where+" ORDER BY "+order+" LIMIT "
				+limit + " OFFSET "+offset);
	}
	
	/**
	 * Get table rows using column aliases
	 * @param tableName - Name of the table where we want to query for data.
	 * @param pairs
	 * @param where
	 * @return ResultSet with the table data
	 */
	public ResultSet select(String tableName, JContentValues pairs, String where)
	{
		String[]columns=pairs.getKeysArray();
		String[]aliases=pairs.getValuesArray();
		String query="SELECT ";
		if(columns==null ||columns.length<=0)
		{
			query=query+"*";
		}
		else
		{
			query=query+columns[0]+" AS '"+aliases[0]+"'";
			for(int i=1;i<columns.length;i++)
				query=query+", "+columns[i]+" AS '"+aliases[i]+"'";
		}
		query=query+" FROM "+tableName;
		if(where!=null)
			query=query+where;
		if(debugging)
			System.out.println(query);
		try
		{
			PreparedStatement st= connection.prepareStatement(query);
			return st.executeQuery();
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorCode(),
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	/**
	 * Get Table rows using column aliases in a specific order,limit and offset
	 * @param tableName
	 * @param columns
	 * @param where
	 * @param order
	 * @param limit
	 * @param offset
	 * @return ResultSet with the table data
	 */
	public ResultSet select(String tableName,JContentValues pairs,String where,
			String order,int limit, int offset)
	{
		return select(tableName,pairs,where+" ORDER BY "+order+" LIMIT "
				+limit + " OFFSET "+offset);
	}
	
	/**
	 * Update table data.
	 * @param tableName Name of the table where we want to update data.
	 * @param pairs JContentValues containg the atributes and the new values.
	 * @param where the WHERE query
	 * @return boolean true if data was successfully updated, or false if not 
	 */
	public boolean update(String tableName,JContentValues pairs, String where)
	{
		String[]columns=pairs.getKeysArray();
		String[]values=pairs.getValuesArray();
		String query="UPDATE "+tableName+ " SET "+columns[0]+" = '"+values[0]+"'";
		for(int i=1;i<pairs.size();i++)
			query=query+", "+columns[i]+" = '"+values[i]+"'";
		query=query+" WHERE "+where;
		return executeQuery(query);
	}
	
	/**
	 * Deletes a specific row.
	 * @param tableName Name of the table where we want to delete data.
	 * @param where the WHERE query
	 * @return boolean true if data was successfully updated, or false if not
	 */
	public boolean delete(String tableName, String where)
	{
		String query="DELETE FROM "+tableName+" WHERE "+where;
		return executeQuery(query);
	}
	
	/**
	 * Returns the Helper's connection
	 * (Used inside the library only, make sure you know what you're doing
	 * when using this method) 
	 * @return connection
	 */
	@Deprecated
	public Connection getConnection()
	{
		return connection;
	}
	
	/**
	 * Used to close the database connection.
	 * Please remember to close the connection on every Class/Method
	 * when you no longer need it.
	 * 
	 */
	public void close()
	{
		try 
		{
			if(!connection.isClosed())
				connection.close();
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorCode(),
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Automatically creates a ForeignKey object for you 
	 * @param foreignKey - the key name on your class
	 * @param tableName - the table name where this key is primary
	 * @param primaryKey - the name of the table's primary key
	 * @param description - the varchar atribute your want the user to see
	 * @return the ForeignKey object or null
	 */
	public ForeignKey getForeignKey(String foreignKey, String tableName, 
			String primaryKey, String description)
	{
		ResultSet rs = select(tableName,new String []{primaryKey, description}, null);
		int []ids;
		String []keys;
		try
		{
			rs.last();
			ids=new int[rs.getRow()];
			keys= new String[rs.getRow()];
			int index=0;
			rs.beforeFirst();
			while(rs.next())
			{
				ids[index]=rs.getInt(1);
				keys[index]=rs.getString(2);
				index++;
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorCode(),
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if(ids.length>0 && keys.length>0)
			return new ForeignKey(foreignKey, ids, keys);
		else
			return null;
	}
	
	protected boolean executeQuery(String query)
	{
		if(debugging)
			System.out.println(query);
		try
		{
			Statement st=connection.createStatement();
			st.execute(query);
			return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null , e.getMessage(),"Error "+e.getErrorCode(),
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	/**
	 * to check if Debugging Mode is Enabled
	 * @return true if debugging mode is enabled, or false if not.
	 */
	public boolean isDebugging() {
		return debugging;
	}

	/**
	 * If set to true, it will print every queryon the console
	 * so that you can find out what queries are running
	 * and which are wrong.
	 * @param debugging
	 */
	public void setDebuggingEnabled(boolean debugging) {
		this.debugging = debugging;
	}
	
}
