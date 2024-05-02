package org.example.model.repository;


import org.example.model.entity.Role;
import org.example.model.entity.User;
import org.example.model.queries.UserQuery;
import org.example.util.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    public void createTable() {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(UserQuery.CREATE_TABLE)) {
            ps.execute();
        } catch (SQLException e) {
            handleSQLException("Error creating table:" + e.getMessage(), e);
        }
    }

    public void save(User user) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserQuery.SAVE_QUERY)) {
            setParametersForSave(preparedStatement, user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException("Error saving user:" + e.getMessage(), e);
        }
    }

    private void setParametersForSave(PreparedStatement ps, User user) throws SQLException {
        ps.setTimestamp(1, user.getRegisterDate());
        ps.setString(2, user.getNationalCode());
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setString(5, user.getPassword());
        ps.setString(6, String.valueOf(user.getRole()));
        ps.setString(7, user.getCardNumber());
    }

    public void update(User user) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UserQuery.UPDATE_QUERY)) {
            setParametersForUpdate(user, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException("Error updating user:" + e.getMessage(), e);
        }
    }

    private void setParametersForUpdate(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getNationalCode());
        ps.setString(2, user.getFirstName());
        ps.setString(3, user.getLastName());
        ps.setString(4, user.getPassword());
        ps.setString(5, String.valueOf(user.getRole()));
        ps.setLong(6, user.getId());
        ps.setString(7, user.getCardNumber());
    }

    public void delete(long id) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(UserQuery.DELETE_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            handleSQLException("Error deleting user:" + e.getMessage(), e);
        }
    }

    public User findById(long id) {
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(UserQuery.FIND_BY_ID_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapUser(rs);
                }
            }
        } catch (SQLException e) {
            handleSQLException("Error finding user by id: " + e.getMessage(), e);
        }
        return null;
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(UserQuery.FIND_ALL_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                userList.add(mapUser(rs));
            }
        } catch (SQLException e) {
            handleSQLException("Error retrieving users: " + e.getMessage(), e);
        }
        return userList;
    }

//    public Role login(String national_code, String password) {
//        Role role = null;
//        try (Connection con = JDBC.getConnection();
//             PreparedStatement ps = con.prepareStatement(UserQuery.LOGIN_QUERY)) {
//            ps.setString(1, national_code);
//            ps.setString(2, password);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    role = Role.valueOf(rs.getString("role"));
//
//                } else {
//                    System.out.println("no user found");
//                }
//            }
//
//
//        } catch (SQLException e) {
//            handleSQLException("Error retrieving users: " + e.getMessage(), e);
//        }
//        return role;
//    }

    public Role login(String national_code, String password){
        Role role = null;
        try(Connection con = JDBC.getConnection();
        PreparedStatement ps = con.prepareStatement(UserQuery.LOGIN_QUERY)){
            ps.setString(1,national_code);
            ps.setString(1,password);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                role = Role.valueOf(rs.getString("role"));
            }

        } catch (SQLException e) {
            handleSQLException("ERROR RETRIVEING USERS: " + e.getMessage(), e);
        }

        return role;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setRegisterDate(rs.getTimestamp("register_date"));
        user.setNationalCode(rs.getString("national_code"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastname"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        return user;
    }

    public void changePassword(String numberCard, String national_code, String newPassword) {
        Role role = null;
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(UserQuery.CHANGE_PASS_QUERY)) {
            ps.setString(1, newPassword);
            ps.setString(2, numberCard);
            ps.setString(3, national_code);

            ps.executeUpdate();
        } catch (SQLException e) {
            handleSQLException("Error retrieving users: " + e.getMessage(), e);
        }
    }

    private void handleSQLException(String message, SQLException e) {
        throw new RuntimeException(message, e);
    }
}
