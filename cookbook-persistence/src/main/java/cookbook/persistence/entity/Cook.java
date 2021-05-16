package cookbook.persistence.entity;

import cookbook.domain.UserType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cook extends User {
    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "uploader")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "uploader")
    private List<Recipe> ownRecipes;

    public Cook() {
        super();
        this.comments = new ArrayList<>();
        this.ownRecipes = new ArrayList<>();
        this.setUserType(UserType.USER);
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
