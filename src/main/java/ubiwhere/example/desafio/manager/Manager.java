/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ubiwhere.example.desafio.manager;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ubiwhere.example.desafio.dataBase.AddressDB;
import ubiwhere.example.desafio.dataBase.ContactDB;
import ubiwhere.example.desafio.dataBase.EntityDB;

/**
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
@Stateless(mappedName = "Manager")
public class Manager {

    private static final Logger LOG = LoggerFactory.getLogger(Manager.class);
    @PersistenceContext(name = "PersistenceUnit")
    private EntityManager entityManager;

    /**
     *
     * @param entity
     * @param contacts
     * @param address
     * @return
     */
    public EntityDB SaveContact(EntityDB entity, Collection<ContactDB> contacts, AddressDB address) {
        try {
            //force relation
            entity.setContact(contacts);
            //force relation
            entity.setAddress(address);
            LOG.info("Saving the object Entity on Database");
            entityManager.persist(entity);
            return entity;
        } catch (Exception e) {
            LOG.error("Saving object fail : " + e.getMessage());
            return null;
        }
    }

    public EntityDB RemoveContact(int contact) {
        try {
            EntityDB result = entityManager.find(EntityDB.class, contact);
            LOG.info("Removing the object Entity on Database");
            entityManager.remove(result);
            return result;
        } catch (Exception e) {
            LOG.error("Removing object fail : " + e.getMessage());
            return null;
        }
    }

    public EntityDB EditContact(EntityDB entity, Collection<ContactDB> contacts, AddressDB address) {
        try {
            EntityDB result = entityManager.find(EntityDB.class, entity.getId_entity());
            result.setLocation(entity.getLocation());
            result.setName(entity.getName());
            result.setPostalCode(entity.getPostalCode());

            //force relation
            entity.setContact(contacts);
            //force relation
            entity.setAddress(address);
            LOG.info("Editting the object Entity on Database");
            entityManager.merge(entity);
            return entity;
        } catch (Exception e) {
            LOG.error("Editting object fail : " + e.getMessage());
            return null;
        }
    }

    public List<EntityDB> getAllEntities() {
        try {
            Query query = entityManager.createQuery("SELECT e from EntityDB e", EntityDB.class);
            LOG.info("Getting all entities");
            return query.getResultList();
        } catch (Exception e) {
            LOG.error("Getting all entities fail : " + e.getMessage());
            return null;
        }
    }

    public List<EntityDB> getEntitiesByName(String name) {
        try {
            Query query = entityManager.createQuery("SELECT DISTINCT e FROM EntityDB e WHERE UPPER(e.name) LIKE :keyword ORDER BY e.name", EntityDB.class);
            query.setParameter("keyword", "%" + name.toUpperCase() + "%");
            LOG.info("Getting all entities by name");
            return query.getResultList();
        } catch (Exception e) {
            LOG.error("Getting all entities by name fail : " + e.getMessage());
            return null;
        }
    }

    public List<EntityDB> getEntitiesByAddress(String address) {
        try {
            Query query = entityManager.createQuery("SELECT DISTINCT e FROM AddressDB t INNER JOIN t.fkEntity e WHERE UPPER(t.streetName) LIKE :keyword ORDER BY e.name", EntityDB.class);
            query.setParameter("keyword", "%" + address.toUpperCase() + "%");
            LOG.info("Getting all entities by address");
            return query.getResultList();
        } catch (Exception e) {
            LOG.error("Getting all entities by address fail : " + e.getMessage());
            return null;
        }
    }

    public List<EntityDB> getEntitiesByLocation(String location) {
        try {
            Query query = entityManager.createQuery("SELECT DISTINCT e FROM EntityDB e WHERE UPPER(e.location) LIKE :keyword ORDER BY e.name", EntityDB.class);
            query.setParameter("keyword", "%" + location.toUpperCase() + "%");
            LOG.info("Getting all entities by location");
            return query.getResultList();
        } catch (Exception e) {
            LOG.error("Getting all entities by location fail : " + e.getMessage());
            return null;
        }

    }

    public List<EntityDB> getEntitiesByContact(String contact) {
        try {
            Query query = entityManager.createQuery("SELECT DISTINCT e FROM ContactDB c INNER JOIN c.fkEntity e WHERE UPPER(c.contact) LIKE :keyword ORDER BY e.name", EntityDB.class);
            query.setParameter("keyword", "%" + contact.toUpperCase() + "%");
            LOG.info("Getting all entities by contact");
            return query.getResultList();
        } catch (Exception e) {
            LOG.error("Getting all entities by contact fail : " + e.getMessage());
            return null;
        }
    }

    public EntityDB getEntityByID(Integer id) {
        try {
            EntityDB entity = entityManager.createQuery("SELECT e FROM EntityDB e WHERE e.id=:keyword", EntityDB.class).setParameter("keyword", id).getSingleResult();
            LOG.info("Getting entity by ID");
            return entity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            LOG.error("GetEntityById error : " + e.getMessage());
            return null;
        }
    }
}
