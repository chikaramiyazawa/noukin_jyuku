package Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserBean {

    String User_ID = null;

    String User_NAME = null;

    String User_Pass = null;

    int admin =  0;

    Timestamp update = null;


    int delete_flg = 0;


    long tuition = 0;

    List <String> message = new ArrayList<>();

    String Login = null;

    public String getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(String user_ID) {
        User_ID = user_ID;
    }

    public String getUser_NAME() {
        return User_NAME;
    }

    public void setUser_NAME(String user_NAME) {
        User_NAME = user_NAME;
    }

    public String getUser_Pass() {
        return User_Pass;
    }

    public void setUser_Pass(String user_Pass) {
        User_Pass = user_Pass;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public Timestamp getUpdate() {
        return update;
    }

    public void setUpdate(Timestamp update) {
        this.update = update;
    }

    public long getTuition() {
        return tuition;
    }

    public void setTuition(long tuition) {
        this.tuition = tuition;
    }

    public int getDelete_flg() {
        return delete_flg;
    }

    public void setDelete_flg(int delete_flg) {
        this.delete_flg = delete_flg;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }



}
