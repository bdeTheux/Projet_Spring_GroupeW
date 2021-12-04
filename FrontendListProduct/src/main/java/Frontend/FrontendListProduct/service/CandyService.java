package Frontend.FrontendListProduct.service;


import Frontend.FrontendListProduct.model.Candy;
import Frontend.FrontendListProduct.model.CandyDTO;
import Frontend.FrontendListProduct.model.Category;
import Frontend.FrontendListProduct.proxy.CandyProxy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandyService {

    private CandyProxy proxy;
    public CandyService(CandyProxy proxy){
        this.proxy = proxy;
    }

    public Category findCategory(String category){
        for(Category cat: Category.values()){
            if(cat.getName().equals(category)){
                return cat;
            }
        }
        return null;
    }

    public List<Category> getCategories(){
        List<Category> cat = new ArrayList<>();
        for(Category c : Category.values()){
            cat.add(c);
        }
        return cat;
    }
    public boolean noEmptyField(CandyDTO candy){
        if(candy.getName().equals("") || candy.getShortDescription().equals("") || candy.getDetailDescription().equals("")){
            return false;
        }
        return true;
    }


    public List<Candy> findAll(Category category, String order, Double min, Double max){
        return proxy.findAll(category, order, min, max);
    }

    public void saveCandy(Candy candy){
        proxy.saveCandy(candy);
    }

    public void updateCandy(int id, Candy candy){
        proxy.updateCandy(id, candy);
    }
    public Candy findById(int id){
        return proxy.findById(id);
    }

    public void deleteCandy(int id){
        proxy.deleteCandy(id);
    }

}
