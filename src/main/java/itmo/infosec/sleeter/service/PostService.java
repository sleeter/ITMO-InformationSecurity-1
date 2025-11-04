package itmo.infosec.sleeter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import itmo.infosec.sleeter.dto.PostRequest;
import itmo.infosec.sleeter.dto.PostResponse;
import itmo.infosec.sleeter.model.Post;
import itmo.infosec.sleeter.repository.PostRepository;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public void createPost(PostRequest p) {
        var post = new Post();
        post.setDescription(p.description());
        post.setUser(userService.getCurrentUser());
        postRepository.save(post);
    }

    public List<PostResponse> getPosts() {
        var user = userService.getCurrentUser();
        return postRepository.getPostByUser(user).stream().map(
                p -> new PostResponse(p.getId(), StringEscapeUtils.escapeJava(p.getDescription()))
        ).toList();
    }
}
