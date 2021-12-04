package GroupeD.commentsService.service;

import GroupeD.commentsService.model.Comment;
import GroupeD.commentsService.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentsService {

    private final CommentRepository repo;

    public CommentsService(CommentRepository repo) {
        this.repo = repo;
    }

    public Iterable<Comment> findByUserId(int id) {
        return repo.findByUserIdOrderByCreationDateAsc(id);
    }

    public Iterable<Comment> findByCandyId(int id) {
        return repo.findByCandyIdOrderByCreationDateAsc(id);
    }

    public Iterable<Comment> findByCandyIdAndUserId(int candyId, int userId) {
        return repo.findByCandyIdAndUserIdOrderByCreationDateAsc(candyId, userId);
    }

    public Iterable<Comment> findByCandyIdExceptUserId(int candyId, int userId) {
        return repo.findByCandyIdAndUserIdNotOrderByCreationDateAsc(candyId, userId);
    }

    public Comment saveComment(Comment comment) {
        Comment c = null;
        try {
            c = repo.save(comment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Cannot add comment", e);
        }
        return c;
    }

    public Comment updateComment(int id, Comment comment) {
        Comment c = findById(id);
        c.setText(comment.getText());
        c.setState(comment.getState());

        try {
            c = repo.save(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Cannot update commentr", e);
        }
        return c;
    }

    public Iterable<Comment> findAll() {
        return repo.findByOrderByCreationDateAsc();
    }

    public Comment findById(int id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot found comment"));
    }

    public void delete(int id) {
        try {
            repo.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Cannot delete comment", e);
        }
    }

    public long countByCandyId(int id) {
        return repo.countByCandyId(id);
    }

    public double averageByCandyId(int id) {
        return repo.averageByCandyId(id);
    }
}
