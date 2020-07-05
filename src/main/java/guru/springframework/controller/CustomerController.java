package guru.springframework.controller;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String listCustomer(Model model) {
        model.addAttribute("customers", customerService.listAllCustomers());
        return "customers";
    }
    @RequestMapping("/customer/{id}")
    public String getCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getCustomer(id) );
        return "customer";
    }
    @RequestMapping("/customer/new")
    public String newCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customerform";
    }
    @RequestMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getCustomer(id));
        return "customerform";
    }

    @RequestMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdate(Customer customer){
        Customer newCustomer = customerService.saveOrUpdateCustomer(customer);
        return "redirect:/customer/"+newCustomer.getId();
    }

}