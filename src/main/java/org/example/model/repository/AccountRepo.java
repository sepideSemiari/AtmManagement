package org.example.model.repository;


import org.example.model.entity.Account;
import org.example.model.entity.CardStatus;
import org.example.model.queries.AccountQuery;
import org.example.util.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepo {
    public void createTable() {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AccountQuery.CREATE_TABLE)) {
            preparedStatement.execute();

        } catch (SQLException e) {
            handleSQLException("Error creating table:" + e.getMessage(), e);

        }
    }

    public void save(Account account) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(AccountQuery.SAVE_QUERY)) {
            setParameterForSave(account, ps);
        } catch (SQLException e) {
            handleSQLException("Error saving account:" + e.getMessage(), e);
        }
    }

    private static void setParameterForSave(Account account, PreparedStatement ps) throws SQLException {
        ps.setString(1, account.getNumberAccount());
        ps.setDouble(2, account.getBalance());
        ps.setString(3, String.valueOf(account.getCardStatus()));
        ps.setString(4, String.valueOf(account.getIssueDate()));
        ps.setString(5, String.valueOf(account.getExpireDate()));
    }

    public void update(Account account) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(AccountQuery.UPDATE_QUERY)) {
            setParameterForUpdate(account, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            handleSQLException("Error updating account:" + e.getMessage(), e);
        }
    }

    private void setParameterForUpdate(Account account, PreparedStatement ps) throws SQLException {
        ps.setLong(1, account.getId());
        ps.setString(2, account.getNumberAccount());
        ps.setDouble(3, account.getBalance());
        ps.setString(4, String.valueOf(account.getCardStatus()));
        ps.setString(5, String.valueOf(account.getIssueDate()));
        ps.setString(6, String.valueOf(account.getExpireDate()));
    }

    public void delete(long id) {
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(AccountQuery.DELETE_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            handleSQLException("Error deleting account: " + e.getMessage(), e);
        }
    }

    public Account findById(long id) {
        try (Connection con = JDBC.getConnection();
             PreparedStatement ps = con.prepareStatement(AccountQuery.FIND_BY_ID_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapAccount(rs);
                }
            }
        } catch (SQLException e) {
            handleSQLException("Error finding account by id: " + e.getMessage(), e);
        }
        return null;
    }

    public Account mapAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setNumberAccount(rs.getString("numberAccount"));
        account.setBalance(rs.getDouble("balance"));
        account.setCardStatus(CardStatus.valueOf(rs.getString("cardStatus")));
        account.setIssueDate(rs.getTimestamp("issueDate"));
        account.setExpireDate(rs.getTimestamp("expireDate"));
        return account;
    }

    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(AccountQuery.FIND_ALL_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                accounts.add(mapAccount(rs));
            }
        } catch (SQLException e) {
            handleSQLException("Error to find all of account" + e.getMessage(), e);
        }
        return accounts;
    }

    public Double getBalance(String accountNumber) {
        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(AccountQuery.All_Balance_Query)) {
            preparedStatement.setString(1, accountNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                }
            }
        } catch (SQLException e) {
            handleSQLException("Error: " + e.getMessage(), e);
        }
        return null;
    }


    private void handleSQLException(String message, SQLException e) {
        throw new RuntimeException(message, e);
    }

}
