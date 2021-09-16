package com.rezzie.api.user;

import com.rezzie.api.user.contact.ContactInformation;
import com.rezzie.api.user.contact.ContactInformationRepository;
import com.rezzie.api.user.education.Education;
import com.rezzie.api.user.education.EducationRepository;
import com.rezzie.api.user.headline.Headline;
import com.rezzie.api.user.headline.HeadlineRepository;
import com.rezzie.api.user.licenseAndCertificate.LicenseAndCertificate;
import com.rezzie.api.user.licenseAndCertificate.LicenseAndCertificateRepository;
import com.rezzie.api.user.skills.Skills;
import com.rezzie.api.user.skills.SkillsRepository;
import com.rezzie.api.user.volunteerHistory.VolunteerHistory;
import com.rezzie.api.user.volunteerHistory.VolunteerHistoryRepository;
import com.rezzie.api.user.workExperience.WorkExperience;
import com.rezzie.api.user.workExperience.WorkExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class AppResource {

    //-cp target/classes:target/dependency/* com.rezzie.api.user.AppResource

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

    @Autowired
    private VolunteerHistoryRepository volunteerHistoryRepository;

    @Autowired
    private LicenseAndCertificateRepository licenseAndCertificateRepository;

    @Autowired
    private SkillsRepository skillsRepository;

    @PostMapping("/api/register")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> register(@Valid @RequestBody UserRequest userProfile){
        return userRepository.findByEmail(userProfile.getEmail()).map(user ->
                Res.errorResponse("Email Already Exist")
        ).orElseGet(() -> {
            User userNew = new User();
            userNew.setFirstName(userProfile.getFirstName());
            userNew.setLastName(userProfile.getLastName());
            userNew.setEmail(userProfile.getEmail());
            userNew.setPassword(userProfile.getPassword());
            userNew.setActive(true);
            userRepository.save(userNew);
            return Res.successResponse("Created successfully",
                    userNew);
        });
    }

    @PostMapping("/api/login")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> login(@Valid @RequestBody UserSignInRequest userSignInRequest) {
        return userRepository.findByEmail(userSignInRequest.getEmail()).map(user ->
                {
                    if (user.getPassword().equals(userSignInRequest.getPassword())) {
                        return Res.successResponse("Created successfully", user);
                    } else {
                        return Res.errorResponse("Incorrect Password");
                    }
                }
        ).orElseGet(() -> Res.errorResponse("Email is not registered"));
    }

    @PostMapping("/api/forgot_password")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> forgotPassword(@Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        return userRepository.findByEmail(forgetPasswordRequest.getEmail()).map(user ->
                {
                    user.setPassword(user.getLastName());
                    userRepository.save(user);
                    return Res.successNoDataResponse("Password has been reset to your surname");
                }
        ).orElseGet(() -> Res.errorResponse("Email is not registered"));
    }


    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> test() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("message", "Hello World!");
        return Res.successResponse("Successful Response",attributes);
    }

    @GetMapping("/hello")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> testHello() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("message", "Hello World Welcome!");
        return Res.successResponse("Successful Response",attributes);
    }

    @GetMapping("/api/users")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<List<User>> retrieveAllUsers() {
        return Res.successResponse("Successful Response",userRepository.findAll());
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

    /*@PostMapping("/api/users")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }*/

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
    public Res<?> createHeadline(@PathVariable int id, @RequestBody Headline headline) {

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

        if (headline.getDetails().length()<200 ){
            return Res.errorResponse("Headline is less than 200");
        }
        return
                headlineRepository.findByUser(user).map(headline1 ->
                        Res.errorResponse("Entry has already been made for this user")
                ).orElseGet(()-> {
                    headlineRepository.save(headline);
                    return Res.successResponse("Created Successfully", headline);
                });

    }
    @PostMapping("api/users/{id}/headline/{headlineId}/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> editHeadline(@PathVariable int id,
                              @PathVariable int headlineId,
                              @RequestBody Headline headline) {
        Optional<User> userOptional = userRepository.findById(id);
        Res<ContactInformation> userNewPost = new Res<>();
        if(!userOptional.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("User not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        Optional<Headline> headlineOptional =
                headlineRepository.findById(headlineId);
        if(!headlineOptional.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("Headline not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        Headline headlineEdit = headlineOptional.get();
        if (headline.getDetails().length()<200 ){
            return Res.errorResponse("Headline is less than 200");
        }
        headlineEdit.setDetails(headline.getDetails());
        headlineRepository.save(headlineEdit);
        return Res.successResponse("Updated Successfully", headlineEdit);
    }

    /*
    *         //Optional<User> userOptional = userRepository.findById(id);
    *  /*if(!userOptional.isPresent()) {
            return
            */
    /*userAllPost.setStatus(false);
            userAllPost.setMessage("User not found");
            userAllPost.setData(null);
            return userAllPost;*/
    /*
        }
        */
    /*userAllPost.setStatus(true);
        userAllPost.setMessage("Created successfully");
        userAllPost.setData(userOptional.get().getContactInformation());*/
    /*
        return Res.successResponse("Created successfully", userOptional.get().getContactInformation());
    */


    @GetMapping("/api/users/{id}/contact")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<ContactInformation> getContactByUserId(@PathVariable int id) {
        return userRepository.findById(id).map(user ->
                Res.successResponse("Fetched successfully", user.getContactInformation())
        ).orElseGet( () ->Res.errorResponse("User not found"));
    }

    @PostMapping("/api/users/{id}/contact")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> createContact(@PathVariable int id, @RequestBody ContactInformation contactInfo) {
        System.out.println("user id.....-> "+ id);
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

        return
                contactInformationRepository.findByUser(user).map(headline1 ->
                        Res.errorResponse("Entry has already been made for this user")
                ).orElseGet(()-> {
                    contactInformationRepository.save(contactInfo);
                    return Res.successResponse("Created Successfully", contactInfo);
                });

    }

    @PostMapping("/api/users/{id}/contact/{contactId}/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> editContact(@PathVariable int id,
                              @PathVariable int contactId,
                              @RequestBody ContactInformation contactInfo) {
        System.out.println("user id.....-> "+ id);
        Optional<User> userOptional = userRepository.findById(id);
        Res<ContactInformation> userNewPost = new Res<>();
        if(!userOptional.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("User not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        User user = userOptional.get();
        Optional<ContactInformation> contactInformation = contactInformationRepository.findById(contactId);
        if(!contactInformation.isPresent()) {
            userNewPost.setStatus(false);
            userNewPost.setMessage("Contact Information not found");
            userNewPost.setData(null);
            return userNewPost;
        }
        ContactInformation contactInfoEdit = contactInformation.get();
        contactInfoEdit.setCity(contactInfo.getCity());
        contactInfoEdit.setCountry(contactInfo.getCountry());
        contactInfoEdit.setLinkedInUrl(contactInfo.getLinkedInUrl());
        contactInfoEdit.setPortfolioUrl(contactInfo.getPortfolioUrl());

        contactInformationRepository.save(contactInfoEdit);
        return Res.successResponse("Updated Successfully", contactInfoEdit);


    }

/******
 * Work Experience
 * */
    @PostMapping("/api/users/{id}/workexperience")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<WorkExperience> createWorkExperience(@PathVariable int id, @RequestBody WorkExperience workExperience) {

        System.out.println("user id.....-> "+ id);

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
        userNewPost.setMessage("Created Successfully");
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
        workExperienceRes.setMessage("Fetched successfully");
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
        workExperienceRes.setMessage("Fetched successfully");
        workExperienceRes.setData(workExperienceOptional.get());
        return workExperienceRes;
    }

    @PostMapping("/api/users/{id}/workexperience/{weId}/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<WorkExperience> editAllWorkExperienceByUserIdAndExperienceId(@PathVariable int id,
                                                                            @PathVariable int weId,
                                                                            @RequestBody WorkExperience workExperience) {
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

        WorkExperience workExperienceEdit = workExperienceOptional.get();

        workExperienceEdit.setTitle(workExperience.getTitle());
        workExperienceEdit.setEmploymentType(workExperience.getEmploymentType());
        workExperienceEdit.setCompanyName(workExperience.getCompanyName());
        workExperienceEdit.setCompanyLocation(workExperience.getCompanyLocation());
        workExperienceEdit.setStartDate(workExperience.getStartDate());
        workExperienceEdit.setActive(workExperience.getActive());
        workExperienceEdit.setEndDate(workExperience.getEndDate());
        workExperienceEdit.setAchievement(workExperience.getAchievement());

        workExperienceRepository.save(workExperienceEdit);
        return Res.successResponse("Updated Successfully", workExperienceEdit);
    }

    @DeleteMapping("/api/users/{id}/workexperience/{weId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<WorkExperience> deleteAllWorkExperienceByUserIdAndExperienceId(
            @PathVariable int id, @PathVariable int weId) {

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
        workExperienceRepository.deleteById(weId);
        workExperienceRes.setStatus(true);
        workExperienceRes.setMessage("Deleted successfully");
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
        workExperienceRes.setMessage("Fetched successfully");
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
            educationRes.setMessage("Education not found");
            educationRes.setData(null);
            return educationRes;
        }
        educationRes.setStatus(true);
        educationRes.setMessage("Fetched successfully");
        educationRes.setData(educationOptional.get());
        return educationRes;
    }

    @DeleteMapping("/api/users/{id}/education/{educationId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<Education> deleteEducationByUserId(
            @PathVariable int id, @PathVariable int educationId) {

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
            educationRes.setMessage("Education not found");
            educationRes.setData(null);
            return educationRes;
        }
        educationRepository.deleteById(educationId);
        educationRes.setStatus(true);
        educationRes.setMessage("Deleted successfully");
        return educationRes;
    }

    @PostMapping("/api/users/{id}/education/{educationId}/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<Education> editAllEducationByUserId(@PathVariable int id,
                                                   @PathVariable int educationId,
                                                   @RequestBody Education education) {
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
            educationRes.setMessage("Education Record not found");
            educationRes.setData(null);
            return educationRes;
        }

        Education educationEdit = educationOptional.get();

        educationEdit.setName(education.getName());
        educationEdit.setDegree(education.getDegree());
        educationEdit.setFieldOfStudy(education.getFieldOfStudy());
        educationEdit.setGrade(education.getGrade());
        educationEdit.setStartDate(education.getStartDate());
        educationEdit.setActive(education.getActive());
        educationEdit.setEndDate(education.getEndDate());
        educationEdit.setOthers(education.getOthers());

        educationRepository.save(educationEdit);
        return Res.successResponse("Updated Successfully", educationEdit);
    }

    /******
     * VolunteerHistory
     * */
    @PostMapping("/api/users/{id}/volunteerHistory")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<VolunteerHistory> createVolunteerHistory(@PathVariable int id,
                                                        @RequestBody VolunteerHistory volunteerHistory) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<VolunteerHistory> volunteerHistoryRes = new Res<>();
        if(!userOptional.isPresent()) {
            volunteerHistoryRes.setStatus(false);
            volunteerHistoryRes.setMessage("User not found");
            volunteerHistoryRes.setData(null);
            return volunteerHistoryRes;
        }
        User user = userOptional.get();
        volunteerHistory.setUser(user);
        volunteerHistoryRepository.save(volunteerHistory);

        volunteerHistoryRes.setStatus(true);
        volunteerHistoryRes.setMessage("Created successfully");
        volunteerHistoryRes.setData(volunteerHistory);
        return volunteerHistoryRes;
    }

    @GetMapping("/api/users/{id}/volunteerHistory")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<List<VolunteerHistory>> getAllVolunteerHistoryByUserId(@PathVariable int id) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<List<VolunteerHistory>> volunteerHistoryRes = new Res<>();
        if(!userOptional.isPresent()) {
            volunteerHistoryRes.setStatus(false);
            volunteerHistoryRes.setMessage("User not found");
            volunteerHistoryRes.setData(null);
            return volunteerHistoryRes;
        }

        volunteerHistoryRes.setStatus(true);
        volunteerHistoryRes.setMessage("Created successfully");
        volunteerHistoryRes.setData(userOptional.get().getVolunteerHistories());
        return volunteerHistoryRes;
    }


    @GetMapping("/api/users/{id}/volunteerHistory/{volunteerHistoryId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<VolunteerHistory> getAllVolunteerHistoryByUserIdAndEducationId
            (@PathVariable int id, @PathVariable int volunteerHistoryId) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<VolunteerHistory> volunteerHistoryRes = new Res<>();
        if(!userOptional.isPresent()) {
            volunteerHistoryRes.setStatus(false);
            volunteerHistoryRes.setMessage("User not found");
            volunteerHistoryRes.setData(null);
            return volunteerHistoryRes;
        }

        Optional<VolunteerHistory> volunteerHistoryOptional =
                volunteerHistoryRepository.findById(volunteerHistoryId);
        if(!volunteerHistoryOptional.isPresent()) {
            volunteerHistoryRes.setStatus(false);
            volunteerHistoryRes.setMessage("Volunteer history not found");
            volunteerHistoryRes.setData(null);
            return volunteerHistoryRes;
        }
        volunteerHistoryRes.setStatus(true);
        volunteerHistoryRes.setMessage("Created successfully");
        volunteerHistoryRes.setData(volunteerHistoryOptional.get());
        return volunteerHistoryRes;
    }

    @DeleteMapping("/api/users/{id}/volunteerHistory/{volunteerHistoryId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<VolunteerHistory> deleteVolunteerHistoryByUserId(
            @PathVariable int id, @PathVariable int volunteerHistoryId) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<VolunteerHistory> volunteerHistoryRes = new Res<>();
        if(!userOptional.isPresent()) {
            volunteerHistoryRes.setStatus(false);
            volunteerHistoryRes.setMessage("User not found");
            volunteerHistoryRes.setData(null);
            return volunteerHistoryRes;
        }

        Optional<VolunteerHistory> educationOptional =
                volunteerHistoryRepository.findById(volunteerHistoryId);
        if(!educationOptional.isPresent()) {
            volunteerHistoryRes.setStatus(false);
            volunteerHistoryRes.setMessage("Volunteer history not found");
            volunteerHistoryRes.setData(null);
            return volunteerHistoryRes;
        }
        volunteerHistoryRepository.deleteById(volunteerHistoryId);
        volunteerHistoryRes.setStatus(true);
        volunteerHistoryRes.setMessage("Deleted successfully");
        return volunteerHistoryRes;
    }

    @PostMapping("/api/users/{id}/volunteerHistory/{volunteerHistoryId}/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<VolunteerHistory> editAllVolunteerHistoryByUserId(@PathVariable int id,
                                                   @PathVariable int volunteerHistoryId,
                                                   @RequestBody VolunteerHistory volunteerHistory) {
        Optional<User> userOptional = userRepository.findById(id);
        Res<VolunteerHistory> volunteerHistoryRes = new Res<>();
        if(!userOptional.isPresent()) {
            volunteerHistoryRes.setStatus(false);
            volunteerHistoryRes.setMessage("User not found");
            volunteerHistoryRes.setData(null);
            return volunteerHistoryRes;
        }
        Optional<VolunteerHistory> volunteerHistoryOptional =
                volunteerHistoryRepository.findById(volunteerHistoryId);
        if(!volunteerHistoryOptional.isPresent()) {
            volunteerHistoryRes.setStatus(false);
            volunteerHistoryRes.setMessage("Volunteer History record not found");
            volunteerHistoryRes.setData(null);
            return volunteerHistoryRes;
        }

        VolunteerHistory volunteerHistoryEdit = volunteerHistoryOptional.get();

        volunteerHistoryEdit.setNameOfOrganization(volunteerHistory.getNameOfOrganization());
        volunteerHistoryEdit.setRole(volunteerHistory.getRole());
        volunteerHistoryEdit.setRoleDetails(volunteerHistory.getRoleDetails());
        volunteerHistoryEdit.setStartDate(volunteerHistory.getStartDate());
        volunteerHistoryEdit.setActive(volunteerHistory.getActive());
        volunteerHistoryEdit.setEndDate(volunteerHistory.getEndDate());

        volunteerHistoryRepository.save(volunteerHistoryEdit);
        return Res.successResponse("Updated Successfully", volunteerHistoryEdit);
    }
    /******
     * LicenseAndCertificate
     * */
    @PostMapping("/api/users/{id}/licenseAndCertificate")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<LicenseAndCertificate> createLicenseAndCertificate(@PathVariable int id,
                                                             @RequestBody LicenseAndCertificate licenseAndCertificate) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<LicenseAndCertificate> licenseAndCertificateRes = new Res<>();
        if(!userOptional.isPresent()) {
            licenseAndCertificateRes.setStatus(false);
            licenseAndCertificateRes.setMessage("User not found");
            licenseAndCertificateRes.setData(null);
            return licenseAndCertificateRes;
        }
        User user = userOptional.get();
        licenseAndCertificate.setUser(user);
        licenseAndCertificateRepository.save(licenseAndCertificate);

        licenseAndCertificateRes.setStatus(true);
        licenseAndCertificateRes.setMessage("Created successfully");
        licenseAndCertificateRes.setData(licenseAndCertificate);
        return licenseAndCertificateRes;
    }

    @GetMapping("/api/users/{id}/licenseAndCertificate")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<List<LicenseAndCertificate>> getAllLicenseAndCertificateByUserId(@PathVariable int id) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<List<LicenseAndCertificate>> licenseAndCertificateRes = new Res<>();
        if(!userOptional.isPresent()) {
            licenseAndCertificateRes.setStatus(false);
            licenseAndCertificateRes.setMessage("User not found");
            licenseAndCertificateRes.setData(null);
            return licenseAndCertificateRes;
        }

        licenseAndCertificateRes.setStatus(true);
        licenseAndCertificateRes.setMessage("Fetched successfully");
        licenseAndCertificateRes.setData(userOptional.get().getLicenseAndCertificates());
        return licenseAndCertificateRes;
    }


    @GetMapping("/api/users/{id}/licenseAndCertificate/{licenseAndCertificateId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<LicenseAndCertificate> getAllLicenseAndCertificateByUserIdAndEducationId
            (@PathVariable int id, @PathVariable int licenseAndCertificateId) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<LicenseAndCertificate> licenseAndCertificateRes = new Res<>();
        if(!userOptional.isPresent()) {
            licenseAndCertificateRes.setStatus(false);
            licenseAndCertificateRes.setMessage("User not found");
            licenseAndCertificateRes.setData(null);
            return licenseAndCertificateRes;
        }

        Optional<LicenseAndCertificate> volunteerHistoryOptional =
                licenseAndCertificateRepository.findById(licenseAndCertificateId);
        if(!volunteerHistoryOptional.isPresent()) {
            licenseAndCertificateRes.setStatus(false);
            licenseAndCertificateRes.setMessage("License and certificate not found");
            licenseAndCertificateRes.setData(null);
            return licenseAndCertificateRes;
        }
        licenseAndCertificateRes.setStatus(true);
        licenseAndCertificateRes.setMessage("Fetched successfully");
        licenseAndCertificateRes.setData(volunteerHistoryOptional.get());
        return licenseAndCertificateRes;
    }

    @DeleteMapping("/api/users/{id}/licenseAndCertificate/{licenseAndCertificateId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<LicenseAndCertificate> deleteLicenceByUserId(
            @PathVariable int id, @PathVariable int licenseAndCertificateId) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<LicenseAndCertificate> licenseAndCertificateRes = new Res<>();
        if(!userOptional.isPresent()) {
            licenseAndCertificateRes.setStatus(false);
            licenseAndCertificateRes.setMessage("User not found");
            licenseAndCertificateRes.setData(null);
            return licenseAndCertificateRes;
        }

        Optional<LicenseAndCertificate> licenseAndCertificateOptional =
                licenseAndCertificateRepository.findById(licenseAndCertificateId);
        if(!licenseAndCertificateOptional.isPresent()) {
            licenseAndCertificateRes.setStatus(false);
            licenseAndCertificateRes.setMessage("License and certificate not found");
            licenseAndCertificateRes.setData(null);
            return licenseAndCertificateRes;
        }
        licenseAndCertificateRepository.deleteById(licenseAndCertificateId);
        licenseAndCertificateRes.setStatus(true);
        licenseAndCertificateRes.setMessage("Deleted successfully");
        return licenseAndCertificateRes;
    }

    @PostMapping("/api/users/{id}/licenseAndCertificate/{licenseAndCertificateId}/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<LicenseAndCertificate> editLicenceHistoryByUserId(@PathVariable int id,
                                                                 @PathVariable int licenseAndCertificateId,
                                                                 @RequestBody LicenseAndCertificate licenseAndCertificate) {
        Optional<User> userOptional = userRepository.findById(id);
        Res<LicenseAndCertificate> licenseAndCertificateRes = new Res<>();
        if(!userOptional.isPresent()) {
            licenseAndCertificateRes.setStatus(false);
            licenseAndCertificateRes.setMessage("User not found");
            licenseAndCertificateRes.setData(null);
            return licenseAndCertificateRes;
        }
        Optional<LicenseAndCertificate> licenseAndCertificateOptional =
                licenseAndCertificateRepository.findById(licenseAndCertificateId);
        if(!licenseAndCertificateOptional.isPresent()) {
            licenseAndCertificateRes.setStatus(false);
            licenseAndCertificateRes.setMessage("License and Certificate record not found");
            licenseAndCertificateRes.setData(null);
            return licenseAndCertificateRes;
        }

        LicenseAndCertificate licenseAndCertificateEdit = licenseAndCertificateOptional.get();

        licenseAndCertificateEdit.setNameOfCertificate(licenseAndCertificate.getNameOfCertificate());
        licenseAndCertificateEdit.setRole(licenseAndCertificate.getRole());
        licenseAndCertificateEdit.setIssuingOrganization(licenseAndCertificate.getIssuingOrganization());
        licenseAndCertificateEdit.setIssueDate(licenseAndCertificate.getIssueDate());
        licenseAndCertificateEdit.setCanExpire(licenseAndCertificate.getCanExpire());
        licenseAndCertificateEdit.setExpirationDate(licenseAndCertificate.getExpirationDate());
        licenseAndCertificateEdit.setCredentialId(licenseAndCertificate.getCredentialId());
        licenseAndCertificateEdit.setCredentialUrl(licenseAndCertificate.getCredentialUrl());

        licenseAndCertificateRepository.save(licenseAndCertificateEdit);
        return Res.successResponse("Updated Successfully", licenseAndCertificateEdit);
    }

    /******
     * Skills
     * */
    @PostMapping("/api/users/{id}/skills")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> createSkill(@PathVariable int id,
                                   @RequestBody List<Skills> skills) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<List<Skills>> skillsRes = new Res<>();
        if(!userOptional.isPresent()) {
            skillsRes.setStatus(false);
            skillsRes.setMessage("User not found");
            skillsRes.setData(null);
            return skillsRes;
        }
        User user = userOptional.get();
        for (Skills s: skills){
            s.setUser(user);
            skillsRepository.save(s);
        }

        skillsRes.setStatus(true);
        skillsRes.setMessage("Created successfully");
        skillsRes.setData(skills);
        return skillsRes;
    }

    @GetMapping("/api/users/{id}/skills")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<List<Skills>> getAllSkillByUserId(@PathVariable int id) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<List<Skills>> skillRes = new Res<>();
        if(!userOptional.isPresent()) {
            skillRes.setStatus(false);
            skillRes.setMessage("User not found");
            skillRes.setData(null);
            return skillRes;
        }

        skillRes.setStatus(true);
        skillRes.setMessage("Fetched successfully");
        skillRes.setData(userOptional.get().getSkills());
        return skillRes;
    }

    @GetMapping("/api/users/{id}/skills/{skillsId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<Skills> getSkillsByUserIdAndEducationId
            (@PathVariable int id, @PathVariable int skillsId) {

        Optional<User> userOptional = userRepository.findById(id);
        Res<Skills> skillsRes = new Res<>();
        if(!userOptional.isPresent()) {
            skillsRes.setStatus(false);
            skillsRes.setMessage("User not found");
            skillsRes.setData(null);
            return skillsRes;
        }

        Optional<Skills> skillsOptional =
                skillsRepository.findById(skillsId);
        if(!skillsOptional.isPresent()) {
            skillsRes.setStatus(false);
            skillsRes.setMessage("Skills not found");
            skillsRes.setData(null);
            return skillsRes;
        }
        skillsRes.setStatus(true);
        skillsRes.setMessage("Fetched successfully");
        skillsRes.setData(skillsOptional.get());
        return skillsRes;
    }

    @PostMapping("/api/users/{id}/skills/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> editSkillsUserId(@PathVariable int id,
                                   @RequestBody List<Skills> skills) {
        Optional<User> userOptional = userRepository.findById(id);
        Res<Skills> skillsRes = new Res<>();
        if(!userOptional.isPresent()) {
            skillsRes.setStatus(false);
            skillsRes.setMessage("User not found");
            skillsRes.setData(null);
            return skillsRes;
        }
        skillsRepository.deleteAll();
        User user = userOptional.get();
        for (Skills s: skills) {
            s.setUser(user);
            skillsRepository.save(s);
        }
        return Res.successResponse("Updated Successfully", skills);
    }

    /******
     * Accomplishment
     * */


    /******
     * GetAllById
     * */
    @GetMapping("/api/users/{id}/get")
    @ResponseStatus(code = HttpStatus.OK)
    public Res<?> getProfile(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        Res<Skills> skillsRes = new Res<>();
        if(!userOptional.isPresent()) {
            skillsRes.setStatus(false);
            skillsRes.setMessage("User not found");
            skillsRes.setData(null);
            return skillsRes;
        }
        User user = userOptional.get();
        UserResponse userRes = new UserResponse();
        userRes.setFirstName(user.getFirstName());
        userRes.setLastName(user.getLastName());
        userRes.setEmail(user.getEmail());
        userRes.setDateOfBirth(user.getDateOfBirth());
        userRes.setGender(user.getGender());
        userRes.setActive(user.getActive());
        userRes.setVolunteerHistories(user.getVolunteerHistories());
        userRes.setContactInformation(user.getContactInformation());
        userRes.setHeadline(user.getHeadline());
        userRes.setEducations(user.getEducations());
        userRes.setLicenseAndCertificates(user.getLicenseAndCertificates());
        userRes.setSkills(user.getSkills());
        userRes.setHeadline(user.getHeadline());
        userRes.setEducations(user.getEducations());
        userRes.setWorkExperiences(user.getWorkExperiences());
        return Res.successResponse("User Profile", userRes);
    }





}
