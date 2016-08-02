package com.example.administrator.ormapping.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

/**
 * Created by Administrator on 2016-07-26.
 */


//Entity
//Book은 테이블
public class Book extends SugarRecord {


  public  String title; //테이블의 필드
   public String edition;

    public Book(){
    }

    //기본 생성자가 있어야 한다.
    //기본 생성자가 없으면 오류가 난다.
    public Book(String title,String edition){
        this.title=title;
        this.edition=edition;
    }

    public String getTitle(String title){
        return title;
    }
    public void setTitle(){
        this.title=title;
    }
    public String getEdition(){
        return edition;
    }
    public void setEdition(String edition){
        this.edition=edition;
    }

}
