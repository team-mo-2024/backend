package teammo.com.teammo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userId;

    @Column(name = "id_code", nullable = false, length = 5)
    private String idCode;

    @Column(name = "id", nullable = false, length = 45)
    private String id;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "is_manager", nullable = false)
    private boolean isManager;

    @ManyToOne
    @JoinColumn(name = "id_code", referencedColumnName = "id_code", insertable = false, updatable = false)
    private Apartment apartment;

    // Getters and Setters
    public int getUserid() {
        return userId;
    }

    public void setUserid(int userid) {
        this.userId = userid;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
