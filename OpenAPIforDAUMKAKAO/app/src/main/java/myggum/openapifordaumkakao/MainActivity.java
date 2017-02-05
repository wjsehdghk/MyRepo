package myggum.openapifordaumkakao;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.List;

import myggum.openapifordaumkakao.Fragment.ProductList;
import myggum.openapifordaumkakao.Fragment.Setting;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d("TESTGG","2");
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }

    static final String CLIENT_ID = "829951459515136986";
    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    FragmentTransaction ft;
    ProductList productList;
    Setting setting;
    Fragment fragment;
    ActionBar actionBar;
    RelativeLayout layout;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingmode:
                Toast.makeText(getBaseContext(), "테스트", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TESTGG","3");
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("MYGGUM");
        layout = (RelativeLayout)findViewById(R.id.linear);
        productList = new ProductList();
        setting = new Setting();
        fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, productList);
        ft.commit();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomview);
        //
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = productList;
                        actionBar.setTitle("MYGGUM");
                        break;
                    case R.id.setting:
                        fragment = setting;
                        actionBar.setTitle("설정");
                        break;
                    case R.id.logout:
                        Snackbar.make(layout,"로그아웃되었습니다",Snackbar.LENGTH_SHORT).show();
                        return true;

                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();
                return true;
            }
        });
        /*dialog.setMessage("데이터 불러오는중");
        dialog.show();*/

    }
}
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
// imageview = (ImageView) findViewById(R.id.imageView);

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