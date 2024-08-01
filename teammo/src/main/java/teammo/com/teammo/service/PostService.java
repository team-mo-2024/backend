package teammo.com.teammo.service;

import teammo.com.teammo.model.Apartment;
import teammo.com.teammo.model.Post;
import teammo.com.teammo.dto.PostDTO;
import teammo.com.teammo.model.User;
import teammo.com.teammo.repository.UserRepository;
import teammo.com.teammo.repository.ApartmentRepository;
import teammo.com.teammo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    // 모든 게시물 조회
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //id로 게시물 조회
    public Optional<PostDTO> findById(int id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            return Optional.of(convertToDTO(postOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    // 특정 사용자의 게시물 조회
    public List<PostDTO> getPostsByUserId(int userId) {
        List<Post> posts = postRepository.findByUser_UserId(userId);
        return posts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // 특정 유형의 게시물 조회
    public List<PostDTO> getPostsByType(String type) {
        List<Post> posts = postRepository.findByType(type);
        return posts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    //새로운 게시물 생성
    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setCreateDate(postDTO.getCreateDate());
        post.setModifyDate(postDTO.getModifyDate());
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImage(postDTO.getImage());
        post.setIsAnonymous(postDTO.getIsAnonymous());
        post.setType(postDTO.getType());

        // 사용자와 아파트를 데이터베이스에서 가져와서 설정합니다
        User user = userRepository.findById(postDTO.getUserid())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Apartment apartment = apartmentRepository.findById(postDTO.getIdCode())
                .orElseThrow(() -> new RuntimeException("Apartment not found"));

        post.setUser(user);
        post.setApartment(apartment);

        postRepository.save(post);
        return convertToDTO(post);
    }


    public Optional<PostDTO> updatePost(int id, PostDTO postDTO) {
        Optional<Post> postOptional = postRepository.findById(id);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();

            // 기존 게시물의 날짜는 변경하지 않고, 수정일만 변경합니다.
            post.setModifyDate(postDTO.getModifyDate());
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            post.setImage(postDTO.getImage());
            post.setIsAnonymous(postDTO.getIsAnonymous());
            post.setType(postDTO.getType());

            // 사용자와 아파트 정보를 데이터베이스에서 가져와 설정합니다.
            User user = userRepository.findById(postDTO.getUserid())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Apartment apartment = apartmentRepository.findById(postDTO.getIdCode())
                    .orElseThrow(() -> new RuntimeException("Apartment not found"));

            post.setUser(user);
            post.setApartment(apartment);

            postRepository.save(post);
            return Optional.of(convertToDTO(post));
        } else {
            return Optional.empty();
        }
    }

    //게시물 삭제
    public boolean deletePost(int id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    //post 엔티티를 postdto로 변환
    private PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setCreateDate(post.getCreateDate());
        postDTO.setModifyDate(post.getModifyDate());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setImage(post.getImage());
        postDTO.setIsAnonymous(post.getIsAnonymous());
        postDTO.setType(post.getType());
        postDTO.setUserid(post.getUser().getUserId());
        postDTO.setIdCode(post.getApartment().getIdCode());
        return postDTO;
    }

}
