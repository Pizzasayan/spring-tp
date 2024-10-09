package com.farda.controller;

import com.farda.model.Article;
import com.farda.model.User;
import com.farda.repository.ArticleRepository;
import com.farda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleRepository articleService;

    /**
     * Read - Get all users
     *
     * @return - An Iterable object of User fully
     */
    @GetMapping("/getUsers")
    public Iterable<User> getUsers(){
        return userService.getUsers();
    }

    /**
     * Create - Add a new User
     * @param User An object User
     * @return The User object saved
     */
    @PostMapping("/newUser")
    public User createUser(@RequestBody User user){

        // New user
        User userIn = new User(user.getUsername(), user.getEmail(), user.getPassword());

        // New article list
        List<Article> articles = new ArrayList<>();

        for(Article articleIn : user.getArticlesList()){

            // New article
            Article article = new Article(articleIn.getTitle(), articleIn.getContent());

            article.setAuthor(userIn);
            // Add article to article list after setting the owner
            articles.add(article);

        }

        userIn.setArticlesList(articles);

        User userOut = userService.saveUser(userIn);

        return userOut;
    }

    /**
     * Read - Get one User
     * @param id The id of the User
     * @return An User object full filled
     */
    @GetMapping("getUser/{id}")
    public User getUser(@PathVariable("id") final Long id){
        Optional<User> User = userService.getUser(id);
        if(User.isPresent()){
            return User.get();
        } else {
            return null;
        }
    }

    /**
     * Update - Update an existing User
     * @param id - The id of the User to update
     * @param User - The User object updated
     * @return
     */
    @PutMapping("updateUser/{id}")
    public User updateUser(@PathVariable("id") final Long id, @RequestBody User user) {
        Optional<User> e = userService.getUser(id);
        if(e.isPresent()){
            User currentUser = e.get();

            String username = user.getUsername();
            if(username != null){
                currentUser.setUsername(username);
            }
            String email = user.getEmail();
            if(email != null){
                currentUser.setEmail(email);
            }
            String password = user.getPassword();
            if(password != null){
                currentUser.setPassword(password);
            }
            userService.saveUser(currentUser);
            return currentUser;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete an User
     * @param id - The id of the User to delete
     */
    @DeleteMapping("removeUser/{id}")
    public String deleteUser(@PathVariable("id") final Long id){
        userService.deleteUser(id);
        return "User number" + id + " deleted.";
    }


    @PostMapping("/saveArticle")
    public String saveArticle(@RequestParam(name = "id") String id){

        Optional<User> userTemp = userService.getUser(Long.valueOf(id));
        User userIn = userTemp.get();

        List<Article> articles = new ArrayList<>();

        Article article = new Article("second tada", "this is");

        article.setAuthor(userTemp.orElse(null));
        articles.add(article);

        userIn.setArticlesList(articles);

        userService.saveUser(userIn);

        return "article saved";
    }

    @GetMapping("/getArticle/{id}")
    public Article getArticle(@PathVariable(name = "id")final Long id){

        Article articleOut = articleService.getById(id);

        return articleOut;
    }
}
