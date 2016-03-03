/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contact.dao;

import contact.model.Contact;
import java.util.List;

/**
 *
 * @author tranthanhan
 */
public interface ContactDAO {

  public void saveOrUpdate(Contact contact);

  public void delete(int contactID);

  public Contact get(int contactID);

  public List<Contact> list();
}
