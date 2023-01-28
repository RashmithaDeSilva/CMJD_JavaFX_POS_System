package lk.ise.pos.dao.custom.impl;

import lk.ise.pos.dao.CURD_util;
import lk.ise.pos.dao.custom.CustomerDao;
import lk.ise.pos.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer c) throws Exception {
        return CURD_util.execute("INSERT INTO customers VALUES(?,?,?,?)",
                c.getID(),c.getName(),c.getAddress(),c.getSalary());
    }

    @Override
    public boolean update(Customer c) throws Exception {
        return CURD_util.execute("UPDATE customers SET name=?, address=?, salary=? WHERE id=?",
                c.getID(),c.getName(),c.getAddress(),c.getSalary());
    }

    @Override
    public Customer find(String id) throws Exception {
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

    @Override
    public boolean delete(String id) throws Exception {
        return CURD_util.execute("DELETE FROM customers WHERE id=?",id);
    }

    @Override
    public List<Customer> findAll() throws Exception {
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
