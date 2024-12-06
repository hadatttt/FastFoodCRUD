package com.FastFoodCRUD.Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.FastFoodCRUD.Model.bean.category;
import com.FastFoodCRUD.Model.bean.fastFood;
import com.FastFoodCRUD.Model.dao.ConnectDatabase;

public class categoryDAO implements DAOInterface<category>{
	private static categoryDAO instance;
	private Connection connect = ConnectDatabase.getConnection();
	private categoryDAO() {
	}
	public static categoryDAO getInstance() {
		if (instance == null)
			instance = new categoryDAO();
		return instance;
	}

	@Override
	public void Insert(category category) {
		String query = "insert into category(categoryName) value(?)";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, category.getCategoryName());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Update(category category) {
		String query = "UPDATE category SET categoryName = ? WHERE categoryId = ?";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setString(1, category.getCategoryName());
			pst.setInt(2, category.getCategoryId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Delete(category category) {
		String query = "DELETE from category where categoryId = ?";
		try {
			PreparedStatement pst = connect.prepareStatement(query);
			pst.setInt(1, category.getCategoryId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<category> getAll() {
		ArrayList<category> categoryArrayList = new ArrayList<category>();
		try {
			String sqlQuery = "SELECT* FROM category";
			PreparedStatement pst = connect.prepareStatement(sqlQuery);
			ResultSet res = pst.executeQuery();
			while (res.next()) {
				category cateogory = new category(res.getInt(1), res.getString(2));
				categoryArrayList.add(cateogory);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryArrayList;
	}
	
	public category getCategoryById(int id) {
	    category category = null; 
	    String query = "SELECT * FROM category WHERE categoryId = ?";
	    try {
	        PreparedStatement pst = connect.prepareStatement(query);
	        pst.setInt(1, id); 
	        ResultSet res = pst.executeQuery();

	        if (res.next()) {
	            category = new category(
	                res.getInt(1), 
	                res.getString(2)
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return category; 
	}
	
}