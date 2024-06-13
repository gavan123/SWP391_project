package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;

public class CategoryDAO extends DBContext {

    // Lấy một Category bằng ID
    public Category getCategoryById(int categoryId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Category WHERE CategoryID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("Name"),
                        rs.getString("Description")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }

    // Lấy tất cả Category
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Category";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("CategoryID"),
                        rs.getString("Name"),
                        rs.getString("Description")
                );
                categoryList.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return categoryList;
    }

    // Thêm một Category mới
    public void addCategory(Category category) {
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Category (Name, Description) VALUES (?, ?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    // Cập nhật thông tin Category
    public void updateCategory(Category category) {
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Category SET Name = ?, Description = ? WHERE CategoryID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.setInt(3, category.getCategoryId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    // Xóa một Category
    public void deleteCategory(int categoryId) {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Category WHERE CategoryID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePreparedStatement(ps);
        }
    }

    public static void main(String[] args) {
        CategoryDAO cateDAO = new CategoryDAO();
        List<Category> cate = cateDAO.getAllCategories();

        for (Category category : cate) {
            System.out.println(category.getName());
        }
    }
}
