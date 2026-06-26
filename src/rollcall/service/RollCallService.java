package rollcall.service;

import rollcall.dao.StudentDAO;
import rollcall.model.RollCallRecord;
import rollcall.model.Student;
import rollcall.util.WeightedRandom;

import java.util.List;

public class RollCallService {

    private final StudentDAO dao;
    private List<Student> students;
    private List<RollCallRecord> records;
    private String currentCourse = "默认课程";

    public RollCallService() {
        dao = new StudentDAO();
        reload();
    }

    public void setCourse(String course) { currentCourse = course; }
    public String getCourse() { return currentCourse; }
    public List<Student> getStudents() { return students; }
    public List<RollCallRecord> getRecords() { return records; }

    public void reload() {
        students = dao.loadStudents();
        records = dao.loadRecords();
    }

    public Student call() {
        reload();
        if (students.isEmpty()) return null;

        int index = WeightedRandom.select(students, null);
        if (index < 0) return null;
        Student selected = students.get(index);
        selected.incrementCalled();
        dao.saveStudents(students);
        return selected;
    }

    public void recordAnswer(Student student, boolean answered) {
        reload();
        for (Student s : students) {
            if (s.getStudentNo().equals(student.getStudentNo())) {
                if (answered) s.incrementAnswered();
                break;
            }
        }

        RollCallRecord record = new RollCallRecord(
                student.getStudentNo(), student.getName(),
                currentCourse, answered);
        records.add(record);
        dao.saveRecords(records);
        dao.saveStudents(students);
    }
}
