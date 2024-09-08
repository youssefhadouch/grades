package com.ltp.gradessubmission.grades.service;

import com.ltp.gradessubmission.grades.Constants;
import com.ltp.gradessubmission.grades.Grade;
import com.ltp.gradessubmission.grades.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository = new GradeRepository();

    public Grade getGrade(int index) {
        return gradeRepository.getGrade(index);
    }

    public void addGrade(Grade grade) {
        gradeRepository.addGrade(grade);
    }

    public void updateGrade(Grade grade, int index) {
        gradeRepository.updateGrade(grade, index);
    }

    public List<Grade> getGrades() {
        return gradeRepository.getGrades();
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < getGrades().size(); i++) {
            if (getGrades().get(i).getName().equals(id)) return i;
        }
        return Constants.Not_Found;
    }

    public Grade getGradeById(String id) {
        int index = getGradeIndex(id);
        return index == Constants.Not_Found ? new Grade() : getGrade(index);
    }

    public void submitGrade(Grade grade) {
        int index = getGradeIndex(grade.getName());
        if (index == Constants.Not_Found) {
            addGrade(grade);
        } else {
            updateGrade(grade, index);
        }
    }
}
