package com.yingluo.Appraiser.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.DynamicDrawableSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.yingluo.Appraiser.R;

import de.greenrobot.event.EventBus;

/**
 * 发表评论弹出的那个对话框
 *
 * @author 王亚立
 */
public class InputMessageDialog extends Dialog {

    private Context context;
    private EditText inputmsg;

    private SendMessageCallback mCallback;
    private TextView number;
    private RelativeLayout rootView;
    private RelativeLayout biaoqing;
    private boolean firstcreate;
    private LinearLayout llPointGroup;
    private ViewPager emojisPager;
    private boolean show;
    private int previousPointEnale = 0;

    /**
     * 自定义接口，用于回调按钮点击事件到Activity
     */
    public interface SendMessageCallback {

    	/**
    	 * 发送消息
    	 * 
    	 * @param message
    	 */
        public void sendMessage(String message);
    }

    public InputMessageDialog(final Context context, final SendMessageCallback mCallback) {
        super(context, R.style.commit_dialog);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.layout_tucao);
        this.context = context;
        this.mCallback = mCallback;
        firstcreate = true;
        show = true;
        setCanceledOnTouchOutside(true);
        inputmsg = (EditText) findViewById(R.id.input);
        inputmsg.setGravity(Gravity.CENTER_VERTICAL);
//        number = (TextView) findViewById(R.id.input_num);
        
        //发送按钮
//        findViewById(R.id.input_submit_image).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                sendMessage();
//            }
//        });
//        //发送按钮
//        findViewById(R.id.input_send_btn).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                sendMessage();
//            }
//        });

        inputmsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String themsg = inputmsg.getText().toString();

//                if (themsg.length() > 40) {
//                    number.setTextColor(context.getResources().getColor(R.color.movetitlecolor));
//                    number.setText((40 - themsg.length()) + "");
//                } else {
//                    number.setTextColor(context.getResources().getColor(R.color.account_number_col));
//                    number.setText(themsg.length() + "");
//                }
//                mCallback.textChange(themsg);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
        //SOFT_INPUT_ADJUST_PAN

    }

    public void setMessage(String msg) {
        if (msg != null) {
            inputmsg.setText(msg);
            inputmsg.setSelection(msg.length());
            biaoqing.setVisibility(View.GONE);
            }
    }

}
