/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contact.dao;

import contact.model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author tranthanhan
 */
public class ContactDAOImpl implements ContactDAO{
  private JdbcTemplate jdbcTemplate;

  public ContactDAOImpl(DataSource datasource) {
    jdbcTemplate = new JdbcTemplate(datasource);
  }

  @Override
  public void saveOrUpdate(Contact contact) {
    if (contact.getId() > 0) {
      // update
      String sql = "UPDATE contact SET name = ?, email =?, address =?, telephone=? WHERE contact_id=?";
      jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(), contact.getTelephone());
    } else {
      // insert
      String sql = "INSERT INTO contact(name, email, address, telephone) VALUE(?, ?, ?, ?,)";
      jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(), contact.getTelephone());
    }
  }

  @Override
  public void delete(int contactID) {
    String sql = "DELTE FROM contact WHERE contact_id=?";
    jdbcTemplate.update(sql, contactID);
  }

  @Override
  public Contact get(int contactID) {
    String sql = "SELECT * FROM contact WHERE contact_id=" + contactID;
    return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {
      @Override
      public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (rs.next()){
          Contact contact = new Contact();
          contact.setId(rs.getInt("contact_id"));
          contact.setName(rs.getString("name"));
          contact.setEmail(rs.getString("email"));
          contact.setAddress(rs.getString("address"));
          contact.setTelephone(rs.getString("telephone"));
          return contact;
        }
        return null;
      }
    });
  }

  @Override
  public List<Contact> list() {
    String sql = "SELECT * from contact";
    List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {
      @Override
      public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact aContact = new Contact();
        aContact.setId(rs.getInt("contact_id"));
        aContact.setName(rs.getString("name"));
        aContact.setEmail(rs.getString("email"));
        aContact.setAddress(rs.getString("address"));
        aContact.setTelephone(rs.getString("telephone"));
    
        return  aContact;
      }
    });

    return listContact;
  }
}
