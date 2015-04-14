package com.sigaritus.swu.recitepoem.read;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.sigaritus.swu.recitepoem.R;

import org.w3c.dom.Text;

public class ReadPoemActivity extends Activity {
    private SpeechSynthesizer mySynthesizer;

    TextView poem_all;
    ImageView speak_img;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_poem);

        poem_all = (TextView)findViewById(R.id.poem_read_all);
        speak_img = (ImageView)findViewById(R.id.speak_img);
        String s = getIntent().getStringExtra("poem");
            poem_all.setText(s);

        speak_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySynthesizer.setParameter(SpeechConstant.VOICE_NAME,"xiaoyan");
//设置音调
                mySynthesizer.setParameter(SpeechConstant.PITCH,"50");
//设置音量
                mySynthesizer.setParameter(SpeechConstant.VOLUME,"50");
                int code = mySynthesizer.startSpeaking(poem_all.getText().toString(), mTtsListener);
                Log.d("mySynthesiezer start:", code+"");
            }
        });

        //在使用语音平台上传应用包的时候会自定生成一个appid   应该使用与包相对应的appid在申请提交后没有使用次数的限制

        //语音初始化，在使用应用使用时需要初始化一次就好，如果没有这句会出现10111初始化失败
        SpeechUtility.createUtility(ReadPoemActivity.this, "appid=552c65de");
        //处理语音合成关键类
        mySynthesizer = SpeechSynthesizer.createSynthesizer(this, myInitListener);
    }

    private InitListener myInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("mySynthesiezer:", "InitListener init() code = " + code);
        }
    };


    private SynthesizerListener mTtsListener = new SynthesizerListener() {
        @Override
        public void onSpeakBegin() {

        }
        @Override
        public void onSpeakPaused() {

        }
        @Override
        public void onSpeakResumed() {
        }
        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
        }
        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        @Override
        public void onEvent(int i, int i2, int i3, Bundle bundle) {

        }

        @Override
        public void onCompleted(SpeechError error) {
            if(error!=null)
            {
                Log.d("mySynthesiezecode:", error.getErrorCode()+"");
            }
            else
            {
                Log.d("mySynthesiezercode:", "0");
            }
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_poem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
