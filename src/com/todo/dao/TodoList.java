package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.DbConnect;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;
public class TodoList {
	Connection conn;
	
	private List<TodoItem> list;

	public TodoList() {
//		this.list = new ArrayList<TodoItem>();
		this.conn = DbConnect.getConnection();
	}
//	
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader (new FileReader (filename));
			String line;
			String sql = "insert into test (title, memo, category, current_date, due_date, completeness, priority, member)" 
					 +"values (?,?,?,?,?,?,?, ?);";
			int records = 0;
			while((line = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line, "#");
				String category = st.nextToken();
				String title = st.nextToken();
				String description = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				String completeness = st.nextToken();
				String priority = st.nextToken();
				String member = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, description);
				pstmt.setString(3, category);
				pstmt.setString(4, current_date);
				pstmt.setString(5, due_date);
				pstmt.setString(6, completeness);
				pstmt.setString(7, priority);
				pstmt.setString(8, member);
				int count = pstmt.executeUpdate();
				if(count > 0) records++;
				pstmt.close();
			}
			System.out.println(records + "records read!!");
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int addItem(TodoItem t) {
		String sql = "insert into test (title, memo, category, current_date, due_date, completeness, priority, member)" + " values (?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setString(6, t.getCompleteness());
			pstmt.setString(7, t.getPriority());
			pstmt.setString(8, t.getMember());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
//	
	public boolean isDuplicate(String title) {
		String sql = "SELECT * FROM test WHERE title = ?";
		PreparedStatement pstmt;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int deleteItem(int index) {
		String sql = "delete from test where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
//
	public int updateItem(TodoItem t) {
		String sql = "update test set title=?, memo=?, category=?, current_date=?, due_date=?, completeness=?, priority=?, member=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setString(6, t.getCompleteness());
			pstmt.setString(7, t.getPriority());
			pstmt.setString(8, t.getMember());
			pstmt.setInt(9, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return count;
	}

	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM test";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM test";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String category = rs.getString("category");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String completeness = rs.getString("completeness");
				String priority = rs.getString("priority");
				String member = rs.getString("member");
				TodoItem t = new TodoItem(title, description, category, due_date,completeness, priority, member);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getList(String key) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		key = "%" + key + "%";
		try {
			String sql = "SELECT * FROM test WHERE title like ? or memo like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setString(2, key);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String completeness = rs.getString("completeness");
				String priority = rs.getString("priority");
				String member = rs.getString("member");
				TodoItem t = new TodoItem(title, description, category, due_date, completeness, priority, member);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<String> getCategories() {
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM test";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("category"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
//
	public ArrayList<TodoItem> getListCategory(String key) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM test WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String category = rs.getString("category");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String completeness = rs.getString("completeness");
				String priority = rs.getString("priority");
				String member = rs.getString("member");
				TodoItem t = new TodoItem(title, description, category, due_date, completeness, priority, member);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
//
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM test ORDER BY " + orderby;
			if (ordering == 0)
				sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String category = rs.getString("category");
				String current_date = rs.getString("current_date");
				String due_date = rs.getString("due_date");
				String completeness = rs.getString("completeness");
				String priority = rs.getString("priority");
				String member = rs.getString("member");
				TodoItem t = new TodoItem(title, description, category, due_date, completeness, priority, member);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateCompleteness(int index) {
		String sql = "update test set completeness=? where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "완료");
			pstmt.setInt(2, index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void deleteAll() {
		String sql = "delete from test;";
		PreparedStatement pstmt;
		int count;
		try {
			pstmt = conn.prepareStatement(sql);
			count  = pstmt.executeUpdate();
//			rs.next();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<TodoItem> getListpriority() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM test WHERE priority = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "*");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String completeness = rs.getString("completeness");
				String priority = rs.getString("priority");
				String member = rs.getString("member");
				TodoItem t = new TodoItem(title, description, category, due_date, completeness, priority, member);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListpriority_none() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM test WHERE priority = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "no");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String completeness = rs.getString("completeness");
				String priority = rs.getString("priority");
				String member = rs.getString("member");
				TodoItem t = new TodoItem(title, description, category, due_date, completeness, priority, member);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
