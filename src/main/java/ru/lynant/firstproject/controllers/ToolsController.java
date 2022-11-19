package ru.lynant.firstproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lynant.firstproject.dao.PersonDAO;
import ru.lynant.firstproject.dao.ToolDAO;
import ru.lynant.firstproject.models.Person;
import ru.lynant.firstproject.models.Tool;

import javax.validation.Valid;

@Controller
@RequestMapping("/tools")
public class ToolsController {

    private final ToolDAO toolDAO;
    private final PersonDAO personDAO;

    @Autowired
    public ToolsController(ToolDAO toolDAO, PersonDAO personDAO) {
        this.toolDAO = toolDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String list(Model model) {
        model.addAttribute("tools", toolDAO.list());
        return "tools/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person")Person person) {
        model.addAttribute("tool", toolDAO.show(id));
        model.addAttribute("people", personDAO.list());
        model.addAttribute("nameRental", personDAO.nameRental(id));
        return "tools/show";
    }

    @GetMapping("/new")
    public String newTool(@ModelAttribute("tool") Tool tool) {
        return "tools/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("tool") @Valid Tool tool,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "tools/new";

        toolDAO.save(tool);
        return "redirect:/tools";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("tool", toolDAO.show(id));
        return "tools/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("tool") @Valid Tool tool, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "tools/edit";

        toolDAO.update(id, tool);
        return "redirect:/tools";
    }

    @PostMapping("/{id}/addIdPerson")
    public String addClientID(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        System.out.println("Add controller tool");
        toolDAO.addClientID(id, person.getClient_id());
        return "redirect:/tools";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        toolDAO.delete(id);
        return "redirect:/tools";
    }

    @PostMapping("/{id}/deleteIdPerson")
    public String deleteClientID(@PathVariable("id") int id) {
        System.out.println("delete controller tool");
        toolDAO.deleteClientID(id);
        return "redirect:/tools";
    }
}
