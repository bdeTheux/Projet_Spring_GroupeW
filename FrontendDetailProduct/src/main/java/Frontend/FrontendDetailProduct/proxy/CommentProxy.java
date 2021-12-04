package Frontend.FrontendDetailProduct.proxy;

import Frontend.FrontendDetailProduct.model.Comment;
import Frontend.FrontendDetailProduct.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name="commentAPI", url="http://localhost:8003/")
public interface CommentProxy {

    @GetMapping("/comments/candy/{cId}")
    List<Comment>getCommentsByCandy(@PathVariable("cId") int id);

    @GetMapping("/comments/candy/{cId}/average")
    double averageByCandyId(@PathVariable("cId") int cId);

    @GetMapping("/comments/{id}")
    Comment commentById(@PathVariable("id") int id);

    @PostMapping("/comments")
    Comment addComment(@RequestBody Comment comment);

    @PutMapping("/comments/{id}")
    Comment editComment(@RequestBody Comment comment, @PathVariable("id") int id);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") int id);

}
