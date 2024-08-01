package teammo.com.teammo.repository;

import teammo.com.teammo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser_UserId(int userId);
    List<Post> findByType(String type);
}
