package teammo.com.teammo.repository;

import teammo.com.teammo.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teammo.com.teammo.model.User;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, String> {
}
