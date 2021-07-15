package com.rezzie.api.user;

import com.rezzie.api.user.contact.ContactInformation;
import com.rezzie.api.user.contact.ContactInformationRepository;
import com.rezzie.api.user.education.Education;
import com.rezzie.api.user.education.EducationRepository;
import com.rezzie.api.user.headline.Headline;
import com.rezzie.api.user.headline.HeadlineRepository;
import com.rezzie.api.user.workExperience.WorkExperience;
import com.rezzie.api.user.workExperience.WorkExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AppResource {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Autowired
    private HeadlineRepository headlineRepository;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private EducationRepository educationRepository;

    @GetMapping("/api/users")
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/api/users/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<User> retrieveUser(@PathVariable int id) {
        Res<User> userRes = new Res<>();
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()){
            userRes.setData(null);
            userRes.setMessage("User not found");
            userRes.setStatus(false);
        }else{
            userRes.setMessage("Successful");
            userRes.setData(user.get());
            userRes.setStatus(true);
        }
        return userRes;
    }

    @PostMapping("/api/users")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /*@GetMapping("/api/users/{id}/posts")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<List<Post>> retrieveAllUsers(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        Res<List<Post>> userAllPost = new Res<>();
        if(!userOptional.isPresent()) {
            userAllPost.setStatus(false);
            userAllPost.setMessage("User not found");
            userAllPost.setData(null);
            return userAllPost;
        }

        userAllPost.setStatus(true);
        userAllPost.setMessage("Created successfully");
        userAllPost.setData(userOptional.get().getPosts());

        return userAllPost;
    }

    @PostMapping("/api/users/{id}/posts")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<Post> createPost(@PathVariable int id, @RequestBody Post post) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<Post> userNewPost = new Res<>();
        if(!userOptional.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("User not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        userNewPost.setStatus(true);
        userNewPost.setMessage("Created successfully");
        userNewPost.setData(post);
        return userNewPost;
    }
*/

    @GetMapping("/api/users/{id}/headline")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<Headline> getHeadlineByUserId(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        Res<Headline> userAllPost = new Res<>();
        if(!userOptional.isPresent()) {
            userAllPost.setStatus(false);
            userAllPost.setMessage("User not found");
            userAllPost.setData(null);
            return userAllPost;
        }

        userAllPost.setStatus(true);
        userAllPost.setMessage("Created successfully");
        userAllPost.setData(userOptional.get().getHeadline());

        return userAllPost;
    }

    @PostMapping("/api/users/{id}/headline")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<Headline> createHeadline(@PathVariable int id, @RequestBody Headline headline) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<Headline> userNewPost = new Res<>();
        if(!userOptional.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("User not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        User user = userOptional.get();
        headline.setUser(user);
        headlineRepository.save(headline);

        userNewPost.setStatus(true);
        userNewPost.setMessage("Created successfully");
        userNewPost.setData(headline);
        return userNewPost;
    }

    @GetMapping("/api/users/{id}/contact")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<ContactInformation> getContactByUserId(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);

        Res<ContactInformation> userAllPost = new Res<>();
        if(!userOptional.isPresent()) {
            userAllPost.setStatus(false);
            userAllPost.setMessage("User not found");
            userAllPost.setData(null);
            return userAllPost;
        }

        userAllPost.setStatus(true);
        userAllPost.setMessage("Created successfully");
        userAllPost.setData(userOptional.get().getContactInformation());

        return userAllPost;
    }

    @PostMapping("/api/users/{id}/contact")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<ContactInformation> createContact(@PathVariable int id, @RequestBody ContactInformation contactInfo) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<ContactInformation> userNewPost = new Res<>();
        if(!userOptional.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("User not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        User user = userOptional.get();
        contactInfo.setUser(user);
        contactInformationRepository.save(contactInfo);

        userNewPost.setStatus(true);
        userNewPost.setMessage("Created successfully");
        userNewPost.setData(contactInfo);
        return userNewPost;
    }

/******
 * Work Experience
 * */
    @PostMapping("/api/users/{id}/workexperience")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<WorkExperience> createWorkExperience(@PathVariable int id, @RequestBody WorkExperience workExperience) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<WorkExperience> userNewPost = new Res<>();
        if(!userOptional.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("User not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        User user = userOptional.get();
        workExperience.setUser(user);
        workExperienceRepository.save(workExperience);

        userNewPost.setStatus(true);
        userNewPost.setMessage("Created successfully");
        userNewPost.setData(workExperience);
        return userNewPost;
    }

    @GetMapping("/api/users/{id}/workexperience")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<List<WorkExperience>> getAllWorkExperienceByUserId(@PathVariable int id) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<List<WorkExperience>> workExperienceRes = new Res<>();
        if(!userOptional.isPresent()) {
            workExperienceRes.setStatus(false);
            workExperienceRes.setMessage("User not found");
            workExperienceRes.setData(null);
            return workExperienceRes;
        }

        workExperienceRes.setStatus(true);
        workExperienceRes.setMessage("Created successfully");
        workExperienceRes.setData(userOptional.get().getWorkExperiences());
        return workExperienceRes;
    }


    @GetMapping("/api/users/{id}/workexperience/{weId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<WorkExperience> getAllWorkExperienceByUserIdAndExperienceId(@PathVariable int id, @PathVariable int weId) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<WorkExperience> workExperienceRes = new Res<>();
        if(!userOptional.isPresent()) {
            workExperienceRes.setStatus(false);
            workExperienceRes.setMessage("User not found");
            workExperienceRes.setData(null);
            return workExperienceRes;
        }

        Optional<WorkExperience> workExperienceOptional =
                workExperienceRepository.findById(weId);
        if(!workExperienceOptional.isPresent()) {
            workExperienceRes.setStatus(false);
            workExperienceRes.setMessage("work experience not found");
            workExperienceRes.setData(null);
            return workExperienceRes;
        }

        workExperienceRes.setStatus(true);
        workExperienceRes.setMessage("Created successfully");
        workExperienceRes.setData(workExperienceOptional.get());
        return workExperienceRes;
    }


    /******
     * Education
     * */
    @PostMapping("/api/users/{id}/education")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<Education> createEducation(@PathVariable int id, @RequestBody Education education) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<Education> userNewPost = new Res<>();
        if(!userOptional.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("User not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        User user = userOptional.get();
        education.setUser(user);
        educationRepository.save(education);

        userNewPost.setStatus(true);
        userNewPost.setMessage("Created successfully");
        userNewPost.setData(education);
        return userNewPost;
    }

    @GetMapping("/api/users/{id}/education")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<List<Education>> getAllEducationByUserId(@PathVariable int id) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<List<Education>> workExperienceRes = new Res<>();
        if(!userOptional.isPresent()) {
            workExperienceRes.setStatus(false);
            workExperienceRes.setMessage("User not found");
            workExperienceRes.setData(null);
            return workExperienceRes;
        }

        workExperienceRes.setStatus(true);
        workExperienceRes.setMessage("Created successfully");
        workExperienceRes.setData(userOptional.get().getEducations());
        return workExperienceRes;
    }


    @GetMapping("/api/users/{id}/education/{educationId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<Education> getAllEducationByUserIdAndEducationId(@PathVariable int id, @PathVariable int educationId) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<Education> educationRes = new Res<>();
        if(!userOptional.isPresent()) {
            educationRes.setStatus(false);
            educationRes.setMessage("User not found");
            educationRes.setData(null);
            return educationRes;
        }

        Optional<Education> educationOptional =
                educationRepository.findById(educationId);
        if(!educationOptional.isPresent()) {
            educationRes.setStatus(false);
            educationRes.setMessage("work experience not found");
            educationRes.setData(null);
            return educationRes;
        }
        educationRes.setStatus(true);
        educationRes.setMessage("Created successfully");
        educationRes.setData(educationOptional.get());
        return educationRes;
    }



}
