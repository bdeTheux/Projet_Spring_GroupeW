package GroupeD.commentsService.repository;

import GroupeD.commentsService.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    List<Comment> findByOrderByCreationDateAsc();

    List<Comment> findByUserIdOrderByCreationDateAsc(int userId);

    //@Query("select c.id, c.creationDate, c.rating, c.state, c.text, c.userId from comments c where c.candyId = ?1")
    List<Comment> findByCandyIdOrderByCreationDateAsc(int candyId);

    List<Comment> findByCandyIdAndUserIdOrderByCreationDateAsc(int candyId, int userId);

    List<Comment> findByCandyIdAndUserIdNotOrderByCreationDateAsc(int candyId, int userId);

    long countByCandyId(int candyId);

    @Query("select avg(c.rating) from comments c where c.candyId = ?1")
    double averageByCandyId(int candyId);
}
