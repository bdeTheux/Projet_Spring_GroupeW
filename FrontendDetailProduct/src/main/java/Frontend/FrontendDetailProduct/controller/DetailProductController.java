package Frontend.FrontendDetailProduct.controller;

import Frontend.FrontendDetailProduct.model.Candy;
import Frontend.FrontendDetailProduct.proxy.CandyProxy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class DetailProductController {
    private CandyProxy proxy;

    public DetailProductController(CandyProxy proxy){
        this.proxy = proxy;
    }

    @GetMapping("/{id}")
    public String candy(@PathVariable("id") int id, Model model){
        Candy candy = proxy.findById(id);
        model.addAttribute("candy", candy);
        return "candy" + id;
    }
}
