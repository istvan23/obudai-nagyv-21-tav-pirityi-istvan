package cookbook.service;

import cookbook.domain.*;
import cookbook.persistence.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class CookbookServiceImpl implements CookbookService{

    private Data data;
    private Cook user;
    private CookBook cookBook;

    @Autowired
    public CookbookServiceImpl(Data data){
        this.data = data;
        this.cookBook = new CookBook(data.getRecipes(), data.getCooks());
    }

    @Override
    public void login(String username) {
        this.user = this.cookBook.getCooks().stream().filter(x -> x.getUsername().equals(username)).findFirst().get();
    }

    @Override
    public void logout() {
        this.user = null;
    }

    @Override
    public void addRecipe(Recipe recipe) {
        recipe.setId(this.data.generateId(Recipe.class));
        this.cookBook.getRecipes().add(recipe);
        this.user.getOwnRecipes().add(recipe);
    }

    @Override
    public void saveComment(Recipe recipe, String comment) {
        Comment newComment = new Comment(this.data.generateId(Comment.class), comment, LocalDateTime.now());
        recipe.getComments().add(newComment);
        this.user.getComments().add(newComment);
    }

    @Override
    public boolean isLoggedIn() {
        return this.user != null;
    }

    @Override
    public List<Recipe> getRecipes() {
        return this.cookBook.getRecipes();
    }

    @Override
    public Set<Category> getCategories() {
        return Set.of(Category.values());
    }

    @Override
    public Cook getCurrentUser() {
        return this.user;
    }

    @Override
    public boolean authenticate(String username, String password) {
        for(Cook cook : this.cookBook.getCooks()){
            if (cook.getUsername().equals(username) && cook.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteRecipe(String recipe) {
        if (this.user != null){
            Recipe deletable = this.cookBook.getRecipes().get(Integer.parseInt(recipe));
            this.cookBook.getRecipes().remove(deletable);
            this.cookBook.getCooks().forEach(cook -> {
                if (cook.getOwnRecipes().contains(deletable)){
                    cook.getOwnRecipes().remove(deletable);
                }
                if (!cook.getComments().isEmpty()){
                    deletable.getComments().forEach( comment -> {
                        if (cook.getComments().contains(comment)){
                            cook.getComments().remove(comment);
                        }
                    });
                }
            });
        }
    }
}
