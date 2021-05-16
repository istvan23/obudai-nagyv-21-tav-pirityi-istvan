package cookbook.domain;

import java.util.List;

public class Cook extends User {
    private List<Comment> comments;
    private List<Recipe> ownRecipes;

    public Cook() {
        super();
    }

    public Cook(Long id, String username, String password, List<Comment> comments, List<Recipe> ownRecipes) {
        super(id, password, username);
        this.comments = comments;
        this.ownRecipes = ownRecipes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Recipe> getOwnRecipes() {
        return ownRecipes;
    }

    public void setOwnRecipes(List<Recipe> ownRecipes) {
        this.ownRecipes = ownRecipes;
    }
}
