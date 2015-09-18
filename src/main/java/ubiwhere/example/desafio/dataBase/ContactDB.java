/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.example.desafio.dataBase;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
@Entity
@Table(name = "Contact")
public class ContactDB implements Serializable {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContact")
    private Integer id;
    @Expose
    private ContactTypeEnum typeOfContact;
    @Expose
    private String contact;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "fkEntity")
    private EntityDB fkEntity;

    /**
     *
     */
    public ContactDB() {
    }

    /**
     *
     * @param id
     * @param typeOfContact
     * @param contact
     * @param fkEntity
     */
    public ContactDB(Integer id, ContactTypeEnum typeOfContact, String contact, EntityDB fkEntity) {
        this.id = id;
        this.typeOfContact = typeOfContact;
        this.contact = contact;
        this.fkEntity = fkEntity;
    }

    /**
     *
     * @param typeOfContact
     * @param contact
     */
    public ContactDB(ContactTypeEnum typeOfContact, String contact) {
        this.id = 0;
        this.typeOfContact = typeOfContact;
        this.contact = contact;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public ContactTypeEnum getTypeOfContact() {
        return typeOfContact;
    }

    /**
     *
     * @param typeOfContact
     */
    public void setTypeOfContact(ContactTypeEnum typeOfContact) {
        this.typeOfContact = typeOfContact;
    }

    /**
     *
     * @return
     */
    public String getContact() {
        return contact;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *
     * @return
     */
    public EntityDB getEntity() {
        return fkEntity;
    }

    /**
     *
     * @param fkEntity
     */
    public void setEntity(EntityDB fkEntity) {
        if (this.getEntity() != fkEntity) {
            this.fkEntity = fkEntity;
            fkEntity.addContact(this);
        }

    }
}
