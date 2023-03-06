package lt.ku.pvmc.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DecimalFormat;

@Controller
public class PVMCController {

    @GetMapping("/")
    public String home(Model model){
        return "index";
    }

    @PostMapping("/")
    public String calculate(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) Double price,
            Model model
    ){

        if (name == null || quantity == null || price == null) {
            model.addAttribute("general_error", "Prašome užpildyti visus laukelius ir bandyti dar kartą.");
            return "index";
        }

        double pvm = 21;
        DecimalFormat f = new DecimalFormat("##.00");
        double priceWithoutPVM = price - (price * (pvm / 100));
        double sumWithPVM = quantity * price;
        double sumWithoutPVM = quantity * priceWithoutPVM;

        model.addAttribute("name", name);
        model.addAttribute("quantity", quantity);
        model.addAttribute("price", f.format(price) + " €");
        model.addAttribute("price_without_pvm", f.format(priceWithoutPVM) + " €");
        model.addAttribute("sum_with_pvm", f.format(sumWithPVM) + " €");
        model.addAttribute("sum_without_pvm", f.format(sumWithoutPVM) + " €");

        return "results";
    }
}