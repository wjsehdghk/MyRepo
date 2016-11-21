package myggum.openapifordaumkakao;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.daum.mf.oauth.MobileOAuthLibrary;
import net.daum.mf.oauth.OAuthError;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button;
    Retrofit retrofit;
    Channel channel;
    List<Channel.Item> items;
    ImageView imageview;
    static final String apikey = "4f4ce90940718827c723e492596efbc4";
    APIService apiService;

    static final String CLIENT_ID = "829951459515136986";

    TextView logText;

    /*@Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Uri uri = intent.getData();
        if (uri != null) {
            // authorize() 호출 후에 url scheme을 통해 callback이 들어온다.
            MobileOAuthLibrary.getInstance().handleUrlScheme(uri);
        }
    }
    MobileOAuthLibrary.OAuthListener oAuthListener = new MobileOAuthLibrary.OAuthListener() {
        @Override
        public void onAuthorizeSuccess() {
            logText.append("onAuthorizeSuccess");
        }

        @Override
        public void onAuthorizeFail(OAuthError.OAuthErrorCodes errorCode, String errorMessage) {
            logText.append("onAuthorizeFail : " + errorMessage);
            if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorInvalidAuthorizationRequest)) {
                // 파라미터를 잘못 사용한 경우.
            } else if   (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorUnauthorizedClient)) {
                // 승인되지 않은 Client ID 를 사용한 경우         } else if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorAccessDenied)) {
                // 사용자가 승인 페이지에서 "취소"를 누른 경우         } else if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorUnsupportedResponseType)) {
                // 지원되지 않는 인증방식을 사용한 경우         } else if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorInvalidScope)) {
                // 유효한 권한 요청이 아닌 경우.
            }
        }
        @Override
        public void onRequestResourceSuccess(String response) {
            // 결과 피싱은 앱에서 담당한다.
            logText.append("onRequestResourceSuccess : " + response);
        }
        @Override
        public void onRequestResourceFail(OAuthError.OAuthErrorCodes errorCode, String errorMessage) {
            logText.append("onRequestResourceFail : " + errorMessage);
            if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorInvalidToken)) {
                // access token 이 없거나 만료처리된 경우 or 401 에러             // authorize() 를 통해 다시 access token을 발급 받아야함.
            } else if   (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorInvalidResourceRequest)) {
                // 서버와 통신중 400 에러가 발생한 경우         } else if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorInsufficientScope)) {
                // 서버와 통신중 403 에러가 발생한 경우         } else if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorServiceNotFound)) {
                // 서버와 통신중 404 에러가 발생한 경우         } else if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorNetwork)) {
                // 현재 휴대폰의 네트워크를 이용할 수 없는 경우         } else if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorServer)) {
                // 서버쪽에서 에러가 발생하는 경우             // 서버 페이지에 문제가 있는 경우이므로 api 담당자와 얘기해야함.
            } else if (errorCode.equals(OAuthError.OAuthErrorCodes.OAuthErrorUnknown)) {
                // 서버와 통신중 그 외 알수 없는 에러가 발생한 경우.
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MobileOAuthLibrary.getInstance().uninitialize();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Button verify = (Button) findViewById(R.id.verify);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // access token 발급받기.
                MobileOAuthLibrary.getInstance().authorize(MainActivity.this, oAuthListener);
            }
        });

        Button profile = (Button) findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // oauth 2.0 을 지원하는 profile API 사용하기
                MobileOAuthLibrary.getInstance().requestResourceWithPath(getApplicationContext(),
                        oAuthListener, "/user/v1/show.json");
            }
        });
        Button expire = (Button) findViewById(R.id.expire);
        expire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // access token 만료처리하기.
                MobileOAuthLibrary.getInstance().expireAuthorization();
                if (MobileOAuthLibrary.getInstance().isAuthorized()) {
                    logText.append("expire fail");
                } else {
                    logText.append("expire success");
                }
            }
        });
        // 인증 진행중 Activity가 내려간 경우를 위해 여기서도 처리해준다.
        Uri uri = getIntent().getData();
        if (uri != null) {
            // authorize() 호출 후에 url scheme을 통해 callback이 들어온다.
            MobileOAuthLibrary.getInstance().handleUrlScheme(uri);
        }*/
        button = (Button) findViewById(R.id.button);
        imageview = (ImageView) findViewById(R.id.imageView);

        channel = new Channel();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.daum.net/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Channel> call = apiService.getImage(apikey, "카카오톡", "xml");
                call.enqueue(new Callback<Channel>() {
                    @Override
                    public void onResponse(Call<Channel> call, Response<Channel> response) {
                        int statuscode = response.code();
                        Toast.makeText(getBaseContext(), "code : " + statuscode, Toast.LENGTH_LONG).show();
                        channel = response.body();
                        items = channel.getItemList();
                        String str = items.get(0).getImage();
                        Log.d("tag:::", "msg-->" + str);

                        Glide.with(getBaseContext()).load(str).into(imageview);

                    }

                    @Override
                    public void onFailure(Call<Channel> call, Throwable t) {
                        String str = t.getLocalizedMessage();
                        Log.d("tag:::", "msg-->" + str);
                    }
                });

            }
        });
    }
}
