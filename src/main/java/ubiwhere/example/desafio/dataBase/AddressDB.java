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
import javax.persistence.OneToOne;

/**
 *
 * @author <a href="mailto:mariosilva11@gmail.com">Mário Silva</a>
 */
@Entity
public class AddressDB implements Serializable {

    @Expose
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAddress")
    private Integer id;
    @Expose
    private String streetName;
    @Expose
    private int houseNumber;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idAddress")
    private EntityDB fkEntity;

    /**
     *
     */
    public AddressDB() {
    }

    /**
     *
     * @param streetName
     * @param houseNumber
     */
    public AddressDB(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    /**
     *
     * @param id
     * @param streetName
     * @param houseNumber
     */
    public AddressDB(int id, String streetName, int houseNumber) {
        this.id = id;
        this.streetName = streetName;
        this.houseNumber = houseNumber;

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
    public void setEntityDB(EntityDB fkEntity) {
        if (fkEntity != null && this.getEntity() != fkEntity) {
            fkEntity.setAddress(this);
            this.fkEntity = fkEntity;
        }
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     *
     * @param streetName
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     *
     * @return
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     *
     * @param houseNumber
     */
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return getId();
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddressDB) {
            AddressDB address = (AddressDB) obj;
            return address.getId() == getId();
        }

        return false;
    }
}
