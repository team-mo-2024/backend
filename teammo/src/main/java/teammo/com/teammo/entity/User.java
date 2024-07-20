package teammo.com.teammo.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    private int userid;

    @Column(name = "id", nullable = false, length = 45)
    private String id;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "is_manager", nullable = false)
    private boolean is_manager;

    // Getters and Setters
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
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
        return is_manager;
    }

    public void setManager(boolean manager) {
        is_manager = manager;
    }
}
