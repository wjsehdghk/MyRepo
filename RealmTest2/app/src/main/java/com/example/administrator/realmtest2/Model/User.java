package com.example.administrator.realmtest2.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by Administrator on 2016-07-29.
 */
public class User extends RealmObject {

    private String name;
    private int age;

    //RealmObject를 상속받은 클래스와 1:1 관계
    private Dog dog;

    //RealmObject를 상속받은 클래스와 다대다 관계
    //다대다 관계는 RealmList를 이용해서 만든다.
    private RealmList<Cat> cats;
    @Ignore
    private int tempref;
    private long id;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public RealmList<Cat> getCats() {
        return cats;
    }

    public void setCats(RealmList<Cat> cats) {
        this.cats = cats;
    }

    public int getTempref() {
        return tempref;
    }

    public void setTempref(int tempref) {
        this.tempref = tempref;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
