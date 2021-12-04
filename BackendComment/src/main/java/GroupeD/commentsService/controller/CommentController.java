package GroupeD.commentsService.controller;

import GroupeD.commentsService.model.Comment;
import GroupeD.commentsService.service.CommentsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentsService commentsService;

    public CommentController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/user/{id}")
    public Iterable<Comment> getCommentsByUser(@PathVariable("id") int id) {
        return commentsService.findByUserId(id);
    }

    @GetMapping("/candy/{id}")
    public Iterable<Comment> getCommentsByCandy(@PathVariable("id") int id) {
        return commentsService.findByCandyId(id);
    }

    @GetMapping("/candy/{cId}/withuser/{uId}")
    public Iterable<Comment> getCommentsByCandyAndUser(@PathVariable("cId") int cId, @PathVariable("uId") int uId) {
        return commentsService.findByCandyIdAndUserId(cId, uId);
    }

    @GetMapping("/candy/{cId}/exceptuser/{uId}")
    public Iterable<Comment> getCommentsByCandyExceptUser(@PathVariable("cId") int cId, @PathVariable("uId") int uId) {
        return commentsService.findByCandyIdExceptUserId(cId, uId);
    }

    @GetMapping("/candy/{cId}/count")
    public long countCommentsByCandyId(@PathVariable("cId") int cId) {
        return commentsService.countByCandyId(cId);
    }

    @GetMapping("/candy/{cId}/average")
    public double averageByCandyId(@PathVariable("cId") int cId) {
        return commentsService.averageByCandyId(cId);
    }


    // CRUD //
    @GetMapping("/{id}")
    public Comment commentById(@PathVariable("id") int id) {
        return commentsService.findById(id);
    }

    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentsService.saveComment(comment);
    }

    @PutMapping("/{id}")
    public Comment editComment(@RequestBody Comment comment, @PathVariable("id") int id) {
        return commentsService.updateComment(id, comment);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        commentsService.delete(id);
    }

    // Not asked but may be useful later //
    @GetMapping
    public Iterable<Comment> findAll() {
        return commentsService.findAll();
    }


}
