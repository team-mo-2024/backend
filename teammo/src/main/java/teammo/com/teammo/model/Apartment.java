package teammo.com.teammo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="apartment")
public class Apartment {

    @Id
    private String id_code;

    @Column(name = "apartment_name", nullable = false, length = 45)
    @JsonProperty("apartment_name")
    private String apartmentName;

    @Column(name = "address", nullable = false, length = 100)
    @JsonProperty("address")
    private String address;

    public String getIdCode() {
        return id_code;
    }

    public void setIdCode(String id_code) {
        this.id_code = id_code;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartment_name) {
        this.apartmentName = apartment_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
