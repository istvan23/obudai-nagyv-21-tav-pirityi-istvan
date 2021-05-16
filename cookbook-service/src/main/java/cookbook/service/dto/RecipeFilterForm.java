package cookbook.service.dto;

import java.util.List;

public class RecipeFilterForm {
    private String searchText;
    private List<String> constraints;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<String> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<String> constraints) {
        this.constraints = constraints;
    }
}
