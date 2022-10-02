package Bean;

import java.sql.Timestamp;

public class CourseBean {

    String Course_ID = null;

    String Course_Name = null;

    String Detail = null;

    Timestamp created = null;

    int Delete_Flg = 0;




    public String getCourse_ID() {
        return Course_ID;
    }

    public void setCourse_ID(String course_ID) {
        Course_ID = course_ID;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public int getDelete_Flg() {
        return Delete_Flg;
    }

    public void setDelete_Flg(int delete_flg) {
        Delete_Flg = delete_flg;
    }






}
