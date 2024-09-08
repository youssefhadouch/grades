package com.ltp.gradessubmission.grades.controller;

import com.ltp.gradessubmission.grades.Grade;
import com.ltp.gradessubmission.grades.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        Grade grade = (id == null || id.isEmpty()) ? new Grade() : gradeService.getGradeById(id);


        System.out.println("Grade being passed to form: " + grade);

        model.addAttribute("grade", grade);
        return "form";
    }



    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result) {
        if (result.hasErrors()) return "form";
        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        model.addAttribute("grades", gradeService.getGrades());
        return "grades";
    }
}
