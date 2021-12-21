package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import product.model.Category;
import product.model.Product;
import product.service.ICategoryService;
import product.service.IProductService;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    IProductService iProductService;
    @Autowired
    ICategoryService iCategoryService;

    @ModelAttribute("category")
    public Iterable<Category> categories() {
        return iCategoryService.findAll();
    }

    @GetMapping("create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("create")
    public String create(Product product) {
        iProductService.save(product);
        return "redirect:/";
    }

    @GetMapping("sort/{idc}")
    public String sort(Model model, String search,@PathVariable Integer idc) {
        Iterable<Product> products = new ArrayList<>();
        if (search == null) {
            if (idc==1  ) {
                products = iProductService.findAllByOrderByPriceDesc();
            } else {
                products = iProductService.findAllByOrderByPriceAsc();
            }
        } else {
            products = iProductService.findByName(search);
            model.addAttribute("back","back");
        }
        model.addAttribute("products", products);
        return "list";
    }

    @GetMapping("")
    public String list(Model model, String search) {
        Iterable<Product> products = new ArrayList<>();
        if (search == null) {
            products = iProductService.findAll();
        } else {
            products = iProductService.findByName(search);
            model.addAttribute("back","back");
        }
        model.addAttribute("products", products);
        return "list";
    }

    @GetMapping("edit/{id}")
    public ModelAndView editForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Optional<Product> product = iProductService.findById(id);
        modelAndView.addObject(product.get());
        return modelAndView;
    }

    @PostMapping("edit")
    public String edit(Product product) {
        iProductService.save(product);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        iProductService.remove(id);
        return "redirect:/";
    }
}
