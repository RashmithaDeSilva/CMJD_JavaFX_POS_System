package lk.ise.pos.dao;

import lk.ise.pos.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccessCode {

    public boolean saveCustomer(Customer c) throws ClassNotFoundException, SQLException {
        return CURD_util.execute("INSERT INTO customers VALUES(?,?,?,?)",
                c.getID(),c.getName(),c.getAddress(),c.getSalary());
    }
    public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException {
        return CURD_util.execute("UPDATE customers SET name=?, address=?, salary=? WHERE id=?",
                c.getID(),c.getName(),c.getAddress(),c.getSalary());
    }
    public Customer findCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CURD_util.execute("SELECT * FROM customers WHERE id=?",id);
        if (resultSet.next()) {
            return new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }

        return null;
    }
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return CURD_util.execute("DELETE FROM customers WHERE id=?",id);
    }
    public List<Customer> allCustomers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CURD_util.execute("SELECT * FROM customers");
        List<Customer> customerList= new ArrayList<>();
        while (resultSet.next()){
            customerList.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return customerList;
    }

}
