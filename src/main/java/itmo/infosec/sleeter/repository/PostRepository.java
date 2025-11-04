package itmo.infosec.sleeter.repository;

import itmo.infosec.sleeter.model.Post;
import itmo.infosec.sleeter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);

    List<Post> getPostByUser(User user);
}
