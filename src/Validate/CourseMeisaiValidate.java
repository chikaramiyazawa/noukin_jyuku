package Validate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Bean.CourseMeisaiBean;

public class CourseMeisaiValidate {

    public static List<String> Validate(HttpServletRequest request, CourseMeisaiBean insertBean){

        List<String> list = new ArrayList<>();

        String alert = null;

        if(insertBean.getTuition() == -1){
            list.add("授業料を入力してください");

            System.out.println("授業料を入力してください");
        }

        if(insertBean.getSubject() == -1){
            list.add("科目を選択してください");

            System.out.println("科目を選択してください");
        }

        if(insertBean.getDay_Lecture() == -1){
            list.add("開催曜日を選択してください");

            System.out.println("開催曜日を選択してください");


        }

        if(insertBean.getStart_Lecture() == null){
            list.add("講義開始期間を入力してください");

            System.out.println("講義開始期間を入力してださい");
        }

        if(insertBean.getEnd_Lecture() == null){
            list.add("講義終了機関を入力してください");

            System.out.println("講義終了期間を入力してください");
        }

        if(insertBean.getStart_Lecture() != null && insertBean.getEnd_Lecture() != null){
            if(insertBean.getStart_Lecture().after(insertBean.getEnd_Lecture())){
                list.add("講義終了機関を必ず講義開始期間の後日になるように入力してください");

                System.out.println("講義終了機関を必ず講義開始期間の後日になるように入力してください");
            }
        }

        if(insertBean.getTEACHER() == null){
            list.add("担当講師を選択してください");

            System.out.println("担当講師を選択してください");
        }

        if(list.size() > 0){
            list.add("登録失敗しました");

            System.out.println("登録失敗しました");
        }

        return list;


    }

}
