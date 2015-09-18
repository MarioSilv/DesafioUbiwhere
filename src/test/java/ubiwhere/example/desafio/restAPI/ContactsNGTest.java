/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.example.desafio.restAPI;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ubiwhere.example.desafio.utils.Errors;
import ubiwhere.example.desafio.utils.Responses;

/**
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
public class ContactsNGTest {

    public ContactsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getContacts method, of class Contacts.
     */
    @Test
    public void testGetContacts_String_String() {
        System.out.println("getContacts");
        String typeOfQuery = "";
        String query = "";
        Contacts instance = new Contacts();
        Object expResult = Responses.errorParameterMissing(Errors.ParametersMissing.toString()).getEntity();
        Object result = instance.getContacts(typeOfQuery, query).getEntity();
        assertEquals(result, expResult);

    }

    /**
     * Test of getContacts method, of class Contacts.
     */
    @Test
    public void testGetContacts_long() {
        System.out.println("getContacts");
        int idContact = 0;
        Contacts instance = new Contacts();
        Object expResult = Responses.errorParameterInvalid(Errors.InvalidParameters.toString()).getEntity();
        Object result = instance.getContacts(idContact).getEntity();
        assertEquals(result, expResult);

    }

    /**
     * Test of addContact method, of class Contacts.
     */
    @Test
    public void testAddContact() {
        System.out.println("addContact");
        String message = "";
        Contacts instance = new Contacts();
        Object expResult = Responses.errorBodyInvalid(Errors.InvalidBody.toString()).getEntity();
        Object result = instance.addContact(message).getEntity();
        assertEquals(result, expResult);

    }

    /**
     * Test of editContact method, of class Contacts.
     */
    @Test
    public void testEditContact() {
        System.out.println("editContact");
        String message = "";
        Contacts instance = new Contacts();
        Object expResult = Responses.errorBodyInvalid(Errors.InvalidBody.toString()).getEntity();
        Object result = instance.editContact(message).getEntity();
        assertEquals(result, expResult);

    }

}
