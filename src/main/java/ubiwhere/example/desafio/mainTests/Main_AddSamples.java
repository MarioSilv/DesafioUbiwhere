/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.example.desafio.mainTests;

import java.util.ArrayList;
import java.util.List;
import ubiwhere.example.desafio.dataBase.AddressDB;
import ubiwhere.example.desafio.dataBase.ContactDB;
import ubiwhere.example.desafio.dataBase.ContactTypeEnum;
import ubiwhere.example.desafio.dataBase.EntityDB;
import ubiwhere.example.desafio.utils.UtilRestCall;
import ubiwhere.example.desafio.utils.Utilities;

/**
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
public class Main_AddSamples {

    /**
     * @param args the command line arguments
     */
    private static String ipAddress = "http://localhost:8080/";

    public static void main(String[] args) {
        int values = 1;
        //add contacts 

        List<EntityDB> list = generateValues(values);
        //send to server 

        for (int i = 0; i < list.size(); i++) {
            System.out.println("Object send (ADD): " + Utilities.getGsonFactory().toJson(list.get(i)));
            String result = UtilRestCall.callAPIPOST(ipAddress + "Desafio/Services/Contacts/", Utilities.getGsonFactory().toJson(list.get(i)));
            System.out.println(result);
            list.set(i, convertStringToEntityDB(result));
        }

        //Edit values
        values = values * 4 * 2 + 1;
        for (EntityDB entity : list) {
            entity.setLocation("new things");
            entity.setName("a new name " + values--);
            System.out.println("Object send (EDIT): " + Utilities.getGsonFactory().toJson(entity));
            entity = convertStringToEntityDB(UtilRestCall.callAPIPUT(ipAddress + "Desafio/Services/Contacts/", Utilities.getGsonFactory().toJson(entity)));

        }
        //delete all values
        for (EntityDB entity : list) {
            System.out.println(UtilRestCall.callAPIDELETE(ipAddress + "Desafio/Services/Contacts/" + entity.getId_entity()));

        }

    }

    private static List<EntityDB> generateValues(int nEntities) {
        List<EntityDB> list = new ArrayList();
        EntityDB db;
//with all class's
        for (int i = 0; i < (nEntities * 4); i += 4) {
            List<ContactDB> contacts = new ArrayList();
            ContactDB contc1 = new ContactDB(ContactTypeEnum.phone, "91" + String.format("%07d", i));
            ContactDB contc2 = new ContactDB(ContactTypeEnum.phone, "91" + String.format("%07d", i + 1));
            ContactDB contc3 = new ContactDB(ContactTypeEnum.phone, "91" + String.format("%07d", i + 2));
            ContactDB contc4 = new ContactDB(ContactTypeEnum.phone, "91" + String.format("%07d", i + 3));
            contacts.add(contc1);
            contacts.add(contc2);
            contacts.add(contc3);
            contacts.add(contc4);
            //add address
            AddressDB address = new AddressDB("the house", i);
            //add entity
            db = new EntityDB("the name " + (i / 4), "the location", address, "3430", contacts);
            list.add(db);
        }
        //Only contacts
        for (int i = (nEntities * 4); i < (nEntities * 4) * 2; i += 4) {
            List<ContactDB> contacts = new ArrayList();
            ContactDB contc1 = new ContactDB(ContactTypeEnum.phone, "91" + String.format("%07d", i));
            ContactDB contc2 = new ContactDB(ContactTypeEnum.phone, "91" + String.format("%07d", i + 1));
            ContactDB contc3 = new ContactDB(ContactTypeEnum.phone, "91" + String.format("%07d", i + 2));
            ContactDB contc4 = new ContactDB(ContactTypeEnum.phone, "91" + String.format("%07d", i + 3));
            contacts.add(contc1);
            contacts.add(contc2);
            contacts.add(contc3);
            contacts.add(contc4);
            db = new EntityDB("the name " + (i / 4), "the location", "3430", contacts);
            list.add(db);

        }
        //Only Address
        AddressDB address = new AddressDB("the house", (nEntities * 4) * 2);
        db = new EntityDB("the name " + ((nEntities * 4) * 2), "the location", address, "3430");
        list.add(db);
        //only entity
        db = new EntityDB("the name " + ((nEntities * 4) * 2 + 1), "the location", "3430");
        list.add(db);

        return list;
    }

    private static EntityDB convertStringToEntityDB(String body) {
        try {
            Class<?> c = Class.forName("ubiwhere.example.desafio.dataBase.EntityDB");
            Object data = Utilities.getGsonFactory().fromJson(body, c);
            return (EntityDB) data;
        } catch (ClassNotFoundException ex) {

        }
        return null;
    }
}
