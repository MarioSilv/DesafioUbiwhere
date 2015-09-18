/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.example.desafio.dataBase;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
@Entity
@Table(name = "Entity")
public class EntityDB implements Serializable {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEntityDB")
    private Integer id;

    @Expose
    private String name;

    @Expose
    private String location;

    @Expose
    @OneToOne(mappedBy = "fkEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AddressDB address;

    @Expose
    private String postalCode;

    @Expose
    @OneToMany(mappedBy = "fkEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<ContactDB> contact;

    /**
     *
     */
    public EntityDB() {
    }

    /**
     *
     * @param id
     * @param name
     * @param location
     * @param postalCode
     */
    public EntityDB(Integer id, String name, String location, String postalCode) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.postalCode = postalCode;
    }

    /**
     *
     * @param name
     * @param location
     * @param postalCode
     */
    public EntityDB(String name, String location, String postalCode) {
        this.name = name;
        this.location = location;
        this.postalCode = postalCode;
    }

    /**
     *
     * @param name
     * @param location
     * @param address
     * @param postalCode
     */
    public EntityDB(String name, String location, AddressDB address, String postalCode) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.postalCode = postalCode;
    }

    /**
     *
     * @param name
     * @param location
     * @param postalCode
     * @param contact
     */
    public EntityDB(String name, String location, String postalCode, Collection<ContactDB> contact) {
        this.name = name;
        this.location = location;
        this.postalCode = postalCode;
        this.contact = contact;
    }

    /**
     *
     * @param name
     * @param location
     * @param address
     * @param postalCode
     * @param contact
     */
    public EntityDB(String name, String location, AddressDB address, String postalCode, Collection<ContactDB> contact) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.postalCode = postalCode;
        this.contact = contact;
    }

    /**
     *
     * @return
     */
    public Integer getId_entity() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId_entity(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public AddressDB getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(AddressDB address) {
        if (address != null && this.address != address) {
            this.address = address;
            address.setEntityDB(this);
        }

    }

    /**
     *
     * @return
     */
    public Collection<ContactDB> getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void addContact(ContactDB contact) {
        if (contact != null && !this.getContact().contains(contact)) {
            this.getContact().add(contact);
            contact.setEntity(this);
        }
    }

    /**
     *
     * @param contacts
     */
    public void setContact(Collection<ContactDB> contacts) {
        this.contact = new ArrayList();
        if (contacts != null && !contacts.isEmpty()) {
            for (ContactDB cont : contacts) {
                this.addContact(cont);
            }
        }

    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
