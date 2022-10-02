package Validate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Bean.SubjectBean;

public class SubjectValidate {
	 public static List<String> Validate(HttpServletRequest request, SubjectBean insertBean){

		 List<String> list = new ArrayList<>();

		   if(insertBean.getID() == -1){

	            list.add("IDを入力してください。");
	            insertBean.setMessage(list);
	            System.out.println("IDを入力してください。");

	        }



	       if(insertBean.getName() == null){

	            list.add("科目名を入力してください。");
	            insertBean.setMessage(list);
	            System.out.println("科目名を入力してください。");

	        }



		 return list;


	 }

}
