package cookbook.service.transformer;

import cookbook.persistence.entity.Cook;
import cookbook.service.dto.CookDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CookTransformer {
    public CookDTO convertToDto(Cook cook){
        CookDTO cookDTO = new CookDTO();
        cookDTO.setId(cook.getId());
        cookDTO.setUsername(cook.getUsername());
        cookDTO.setPassword(cook.getPassword());

        cookDTO.setOwnRecipes(new ArrayList<>());
        cookDTO.setComments(new ArrayList<>());
        return cookDTO;
    }

    public Cook convertToEntity(CookDTO cook){
        Cook cookEntity = new Cook();
        cookEntity.setId(cook.getId());
        cookEntity.setUsername(cook.getUsername());
        cookEntity.setPassword(cook.getPassword());
        return cookEntity;
    }
}
