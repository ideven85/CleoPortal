package game.pokemon.controllers;

import game.pokemon.model.Beverage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
//@ControllerAdvice
public class CafeController {

    @GetMapping("/menu")
    public String showMenu(Model model){
        Beverage cola = new Beverage();
        cola.setId(1L);
        cola.setName("Coca Cola");
        cola.setPrice(10D);
        Beverage pepsi = new Beverage();
        cola.setId(2L);
        cola.setName("Pepsi");
        cola.setPrice(15D);
       /* Beverage fonta = new Beverage();
        cola.setId(3L);
        cola.setName("Fonta");
        cola.setPrice(20D);*/

        ArrayList<Beverage> beverages = new ArrayList<Beverage>();
        beverages.add(pepsi);
        beverages.add(cola);

       // beverages.add(fonta);
        model.addAttribute("beveragesList",beverages);
        return "bevarages";



    }
}
