package myggum.openapifordaumkakao;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016-11-18.
 */
public interface APIService {

    @GET("search/web")
    Call<Channel> getInfo(@Query("apikey")String apikey,
                        @Query("q") String name,@Query("output") String data);
    @GET("search/image")
    Call<Channel> getImage(@Query("apikey")String apikey,
                           @Query("q") String name,@Query("output") String data);


}
/*
<?xml version="1.0" encoding="UTF-8"?>
<channel>
<title>Search Daum Open API</title>
<description>Daum Open API search result</description>
<generator>Daum Open API</generator>
<link>http://dna.daum.net/apis</link>
<lastBuildDate>Fri, 18 Nov 2016 15:13:38 +0900</lastBuildDate>
<totalCount>27299754</totalCount>
<pageCount>3994</pageCount>
<result>11</result>
<item>
<height>300</height>
<image>http://img.etoday.co.kr/pto_db/2014/04/20140404101012_432935_400_300.jpg</image>
<title>&lt;b&gt;카카오톡&lt;/b&gt; vs 라인, 오프라인에서도 캐릭터 상품으로 한판 승부</title>
<width>400</width>
<cp>16uW0kXYu15lwfSw6Z</cp>
<pubDate>20140404101156</pubDate>
<link>http://www.etoday.co.kr/news/section/newsview.php?idxno=895879</link>
<thumbnail>https://search2.kakaocdn.net/argon/130x130_85_c/KvwYlx8Xpwl</thumbnail>
</item>
<item>
<height>254</height>
<image>http://cfile4.uf.tistory.com/image/21763F3454E1741F0B8578</image>
<title>안드로이드 &lt;b&gt;카카오톡&lt;/b&gt; 사진 저장 위치 및 스크린샷 저장 위치 폴더</title>
<width>520</width>
<cp>794570</cp>
<pubDate>20150216151008</pubDate>
<link>http://mastmanban.tistory.com/882</link>
<thumbnail>https://search1.kakaocdn.net/argon/130x130_85_c/3I5eeBWErK0</thumbnail>
</item>
<item>
<height>342</height>
<image>http://i2.media.daumcdn.net/svc/image/U03/news/201312/03/hankyung/20131203013805162.jpg</image>
<title>010 번호 통합..&lt;b&gt;카카오톡&lt;/b&gt; 사용법은?</title>
<width>400</width>
<cp>16PB-CFqYmghfP9Oyr</cp>
<pubDate>20131203013804</pubDate>
<link>http://v.media.daum.net/v/20131203013804235</link>
<thumbnail>https://search2.kakaocdn.net/argon/130x130_85_c/J9Qxy8OFWAR</thumbnail>
</item>
<item>
<height>500</height>
<image>http://cfile6.uf.tistory.com/image/010A983F51ADCEA814BB7C</image>
<title>부모님께 &lt;b&gt;카카오톡&lt;/b&gt;을 권하지 않은 이유</title>
<width>800</width>
<cp>794570</cp>
<pubDate>20130605083000</pubDate>
<link>http://hrmac.tistory.com/725</link>
<thumbnail>https://search4.kakaocdn.net/argon/130x130_85_c/LqK5CbpCQRl</thumbnail>
</item>
<item>
<height>240</height>
<image>http://cfile3.uf.tistory.com/image/2428F43B5282E1F616C5AC</image>
<title>&lt;b&gt;카카오톡&lt;/b&gt; 프렌즈 이모티콘 카카오프렌즈 이모티콘</title>
<width>240</width>
<cp>794570</cp>
<pubDate>20131113113218</pubDate>
<link>http://bamsong2.tistory.com/126</link>
<thumbnail>https://search3.kakaocdn.net/argon/130x130_85_c/1mCfH0EUMqk</thumbnail>
</item>
<item>
<height>367</height>
<image>http://cfile7.uf.tistory.com/image/130E65344FE880FF1AD49A</image>
<title>[동상이몽] &lt;b&gt;카카오톡&lt;/b&gt; - 경제학 vs 컴퓨터공학</title>
<width>397</width>
<cp>794570</cp>
<pubDate>20120626072000</pubDate>
<link>http://goham20.tistory.com/1996</link>
<thumbnail>https://search1.kakaocdn.net/argon/130x130_85_c/GxkPCpGj4DM</thumbnail>
</item>
<item>
<height>240</height>
<image>http://cfile10.uf.tistory.com/image/25451F3B5282E1F70B1F63</image>
<title>&lt;b&gt;카카오톡&lt;/b&gt; 프렌즈 이모티콘 카카오프렌즈 이모티콘</title>
<width>240</width>
<cp>794570</cp>
<pubDate>20131113113218</pubDate>
<link>http://bamsong2.tistory.com/126</link>
<thumbnail>https://search1.kakaocdn.net/argon/130x130_85_c/4tVLRDpGZn6</thumbnail>
</item>
<item>
<height>1050</height>
<image>http://i2.media.daumcdn.net/svc/image/U03/news/201104/28/etnews/20110428181642181.jpg</image>
<title>&lt;b&gt;카카오톡&lt;/b&gt;, 오후 한때 장애 발생</title>
<width>700</width>
<cp>16yGc-mR1Rz5JT4-UZ</cp>
<pubDate>20110428181642</pubDate>
<link>http://v.media.daum.net/v/20110428181642170</link>
<thumbnail>https://search1.kakaocdn.net/argon/130x130_85_c/7KJloyCXBKi</thumbnail>
</item>
<item>
<height>514</height>
<image>http://t1.daumcdn.net/news/201602/07/mediatoday/20160207163947405iflp.jpg</image>
<title>&lt;b&gt;카카오톡&lt;/b&gt; 새 이모티콘 '라이언', 이렇게 탄생했다</title>
<width>700</width>
<cp>16LgxQNFgHxdsXzsJp</cp>
<pubDate>20160207163948</pubDate>
<link>http://v.media.daum.net/v/20160207163948315</link>
<thumbnail>https://search4.kakaocdn.net/argon/130x130_85_c/Jkzgz60Bfpx</thumbnail>
</item>
<item>
<height>400</height>
<image>http://t1.daumcdn.net/news/201607/28/kukinews/20160728191008942pvjk.jpg</image>
<title>&lt;b&gt;카카오톡&lt;/b&gt; 알림톡 불법 발송 논란, 방통위 조사 착수</title>
<width>400</width>
<cp>16AffY_mb9wFLBN77-</cp>
<pubDate>20160728191009</pubDate>
<link>http://v.media.daum.net/v/20160728191009515</link>
<thumbnail>https://search3.kakaocdn.net/argon/130x130_85_c/1hNIz9uggDS</thumbnail>
</item>
</channel>
*/