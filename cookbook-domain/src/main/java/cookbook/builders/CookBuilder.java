package cookbook.builders;

import cookbook.domain.Comment;
import cookbook.domain.Cook;
import cookbook.domain.Recipe;

import java.util.List;

public class CookBuilder {
    private Long id;
    private String password;
    private String username;
    private List<Comment> comments;
    private List<Recipe> ownRecipes;

    public CookBuilder(){

    }

    public CookBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public CookBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CookBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public CookBuilder setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public CookBuilder setOwnRecipes(List<Recipe> ownRecipes) {
        this.ownRecipes = ownRecipes;
        return this;
    }

    public Cook getCook(){
        return new Cook(this.id, this.username, this.password, this.comments, this.ownRecipes);
    }
}
