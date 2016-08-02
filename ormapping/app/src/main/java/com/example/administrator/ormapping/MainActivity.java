package com.example.administrator.ormapping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.ormapping.model.Book;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Book> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Book테이블에 필드값 저장하기.
        Book book= new Book("Heloo","1st edition");
        book.save();
        long count = Book.count(Book.class);
        //테이블에 레코드들이 존재하는지 확인한다.
        //-1 : 테이블이 생성되지 않음. 0: 테이블에 레코드가 없음.
        //Book테이블에 있는 레코드 가져와서 업데이트 하기.
        Book book1=Book.findById(Book.class,1);
        book1.title="halo";
        book1.edition="2st edition";
        book1.save();
        bookList= new ArrayList<>();
        //테이블의 전체 레코드를 보고 싶을때는 arraylist를 사용.
        if(count>0){
            bookList=Book.listAll(Book.class);//테이블의 전체 레코드를 booklist에 저장.
        }
        /*
        if(count>0){
            bookList=Book.find(Book.class,"title=?","halo"); // 쿼리를 작성하여 title이 halo인 레코드를 찾아서 저장.
        }
        if(count>0){

            bookList=Book.find(Book.class,"title=?","halo");
           // Book book2=Book.findById(Book.class,bookList.getId());
           // book2.delete();// Book테이블에 있는 레코드 삭제.
        }

*/


        //find by id, id = 2
        Book book11 = Book.findById(Book.class, 2);

        //find with where clause and the arguments
        List<Book> book4 = Book.find(Book.class, "title = ?", "halo");

        //find with custom query
        List<Book> book5 = Book.findWithQuery(Book.class, "SELECT * FROM Contact WHERE title = ?", "halo");

        //find with query builder
        List<Book> book6 = Select.from(Book.class).where(Condition.prop("title").eq("halo")).list();

        //delete by id, id = 3
        Book book7 = Book.findById(Book.class, (long) 3);
        book7.delete();

        //delete all
        SugarRecord.deleteAll(Book.class);

    }
}
