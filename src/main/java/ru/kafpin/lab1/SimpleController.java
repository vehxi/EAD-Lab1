package ru.kafpin.lab1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SimpleController {

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "main-form";
    }

    @PostMapping("/form")
    public String processForm(
            @ModelAttribute Student student,
            Model model
    ) {
        int shortYear = student.getAdmissionYear() % 100;

        String yearPart = String.format("%02d", shortYear);
        String group = "ПИНз-1" + yearPart;
        String login = "student" + yearPart + "-" + student.getId();

        student.setGroup(group);
        student.setLogin(login);

        model.addAttribute("student", student);

        return "result";
    }
}