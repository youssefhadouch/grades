package com.ltp.gradessubmission.grades;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class GradeController {


    List<Grade> studentGrades = new ArrayList<>();


@GetMapping("/")
public String getForm(Model model , @RequestParam(required = false) String name) {
int index = getGradeIndex(name);
    model.addAttribute("grade", index == Constants.Not_Found ? new Grade() : studentGrades.get(index) );
    return "form";
}


@PostMapping("/handleSubmit")
public String submitForm(@Valid Grade grade , BindingResult result){

    if(result.hasErrors())return "form";
    int index = getGradeIndex(grade.getName());
if(index == Constants.Not_Found){
    studentGrades.add(grade);
}
else {
    studentGrades.set(index , grade);
}

return "redirect:/grades";
}

    @GetMapping("/grades")
    public String getGrades(Model model) {


        model.addAttribute("grades", studentGrades);
        return "grades";

    }


    public Integer getGradeIndex(String name){
    for (int i = 0; i < studentGrades.size(); i++){
        if (studentGrades.get(i).getName().equals(name))
            return i;
    }
    return Constants.Not_Found;
    }






}
