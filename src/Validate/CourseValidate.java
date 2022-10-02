package Validate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Bean.CourseBean;

public class CourseValidate {

    public static List<String> Validate(HttpServletRequest request , CourseBean insertBean){

        List<String> list = new ArrayList<>();

        String alert = null;


        if(insertBean.getCourse_Name() == null){
            list.add("コース名を入力してください");

            System.out.println("コース名を入力してください");
        }

        if(insertBean.getDetail() == null){
            list.add("コースの詳細を入力してください");

            System.out.println("コースの詳細を入力してください");
        }

        if(list.size() > 0){
            list.add("登録失敗しました。");

            System.out.println("登録失敗しました。");
        }

        return list;
    }

}
