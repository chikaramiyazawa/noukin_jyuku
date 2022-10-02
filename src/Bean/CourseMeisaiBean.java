package Bean;

import java.sql.Timestamp;

public class CourseMeisaiBean {

    String Course_ID = null;

    int Grade = 0;

    int Subject = 0;


    long tuition = 0;

    Timestamp Start_Lecture = null;

    Timestamp End_Lecture = null;

    int Day_Lecture = 0;


    String TEACHER = null;

    int Delete_Flg = 0;


    public String getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(String course_ID) {
        Course_ID = course_ID;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public int getSubject() {
        return Subject;
    }

    public void setSubject(int subject) {
        Subject = subject;
    }


    public long getTuition() {
        return tuition;
    }

    public void setTuition(long tuition) {
        this.tuition = tuition;
    }

    public Timestamp getStart_Lecture() {
        return Start_Lecture;
    }

    public void setStart_Lecture(Timestamp start_Lecture) {
        Start_Lecture = start_Lecture;
    }

    public Timestamp getEnd_Lecture() {
        return End_Lecture;
    }

    public void setEnd_Lecture(Timestamp end_Lecture) {
        End_Lecture = end_Lecture;
    }

    public int getDay_Lecture() {
        return Day_Lecture;
    }

    public void setDay_Lecture(int day_Lecture) {
        Day_Lecture = day_Lecture;
    }


    public String getTEACHER() {
        return TEACHER;
    }

    public void setTEACHER(String teacher) {
        TEACHER = teacher;
    }

    public int getDelete_Flg() {
        return Delete_Flg;
    }

    public void setDelete_Flg(int delete_flg) {
        Delete_Flg = delete_flg;
    }







}
