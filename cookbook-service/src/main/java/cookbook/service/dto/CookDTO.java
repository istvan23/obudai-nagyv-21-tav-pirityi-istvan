package cookbook.service.dto;

import java.util.List;

public class CookDTO extends UserDTO {
    private List<CommentDTO> comments;
    private List<RecipeDTO> ownRecipes;

    public CookDTO() {
        super();
    }

    public CookDTO(Long id, String username, String password, List<CommentDTO> comments, List<RecipeDTO> ownRecipes) {
        super(id, password, username);
        this.comments = comments;
        this.ownRecipes = ownRecipes;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<RecipeDTO> getOwnRecipes() {
        return ownRecipes;
    }

    public void setOwnRecipes(List<RecipeDTO> ownRecipes) {
        this.ownRecipes = ownRecipes;
    }
}
