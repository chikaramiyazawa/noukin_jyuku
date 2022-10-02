package Validate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Bean.UserBean;
import DAO.UserDAO;

public class UserValidate {
    public static List<String> Validate(HttpServletRequest request, UserBean insertBean){

        List<String> list = new ArrayList<>();

        UserDAO userDAO = new UserDAO();

        if(insertBean.getUser_ID() == null){

            list.add("ユーザーIDを入力してください。");
            insertBean.setMessage(list);
            System.out.println("ユーザーIDを入力してください。");

            UserBean dplicate  = userDAO.findById(insertBean.getUser_ID());

            if(dplicate != null){

                list.add("入力されたユーザーIDはすでに使用されております。");
                insertBean.setMessage(list);
                System.out.println("入力されたユーザーIDはすでに使用されております。");

            }

        }

        if(insertBean.getUser_NAME() == null){

            list.add("ユーザー名を入力してください。");
            insertBean.setMessage(list);
            System.out.println("ユーザー名を入力してください。");

        }

        if(insertBean.getUser_Pass() == null){

            list.add("パスワードを入力してください。");
            insertBean.setMessage(list);
            System.out.println("パスワードを入力してください。");
        }

        if(insertBean.getAdmin() == 0){

            list.add("権限を選択してください。");
            insertBean.setMessage(list);
            System.out.println("権限を選択してください。");
        }

        if(list.size() > 0){
            list.add("登録失敗しました");
            insertBean.setMessage(list);
            System.out.println("登録失敗しました");
        }


        return list;


    }

}
