package com.sdkj.iosdialog.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sdkj.iosdialog.R;

import androidx.core.content.ContextCompat;

/**
 * @author:wjj
 * @date: 2019/7/19 18:04.
 * @description:
 */
public class AlertMsgImgDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    //private TextView txt_title;
    private ImageView img_ok, img_fail;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public AlertMsgImgDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public AlertMsgImgDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alert_img_dialog, null);

        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        //txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        img_ok = (ImageView) view.findViewById(R.id.img_ok);
        img_fail = (ImageView) view.findViewById(R.id.img_fail);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        setGone();
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), FrameLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    private void setMsgColor(int color) {
        txt_msg.setTextColor(color);
    }

    private void setMsgSize(float size) {
        txt_msg.setTextSize(size);
    }

    private void setMsg(int gravity) {
        txt_msg.setGravity(gravity);
    }

    /**
     * 恢复初始
     *
     * @return
     */
    public AlertMsgImgDialog setGone() {
        if (lLayout_bg != null) {
            img_ok.setVisibility(View.GONE);
            img_fail.setVisibility(View.GONE);
            btn_pos.setVisibility(View.GONE);
            //txt_title.setVisibility(View.GONE);
            txt_msg.setVisibility(View.GONE);
            btn_neg.setVisibility(View.GONE);
            btn_pos.setVisibility(View.GONE);
            img_line.setVisibility(View.GONE);

        }
        showTitle = false;
        showMsg = false;
        showPosBtn = false;
        showNegBtn = false;
        return this;
    }

    /**
     * 设置图片
     *
     * @return
     */
    public AlertMsgImgDialog setImg(boolean isOK) {
        if (isOK)
            img_ok.setVisibility(View.VISIBLE);
        else
            img_fail.setVisibility(View.VISIBLE);
        return this;
    }

    /**
     * 设置成功显示的图片
     * @param drawable
     */
    private void setImgOK(int drawable) {
        img_ok.setImageDrawable(context.getResources().getDrawable(drawable));
    }

    /**
     * 设置失败显示的城市
     * @param drawable
     */
    private void setImgFail(int drawable) {
        img_ok.setImageDrawable(context.getResources().getDrawable(drawable));
    }

    /**
     * 设置title
     *
     * @param title
     * @return
     */
//    public AlertMsgImgDialog setTitle(String title) {
//        showTitle = true;
//        if (TextUtils.isEmpty(title)) {
//            txt_title.setText("提示");
//        } else {
//            txt_title.setText(title);
//        }
//        return this;
//    }

    /**
     * 设置Message
     *
     * @param msg
     * @return
     */
    public AlertMsgImgDialog setMsg(String msg) {
        showMsg = true;
        if (TextUtils.isEmpty(msg)) {
            txt_msg.setText("");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    /**
     * 设置点击外部是否消失
     *
     * @param cancel
     * @return
     */
    public AlertMsgImgDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    /**
     * 右侧按钮
     *
     * @param text
     * @param listener
     * @return
     */
    public AlertMsgImgDialog setPositiveButton(String text,
                                               final OnClickListener listener) {
        return setPositiveButton(text, -1, listener);
    }

    public AlertMsgImgDialog setPositiveButton(String text, int color,
                                               final OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("");
        } else {
            btn_pos.setText(text);
        }
        if (color == -1) {
            color = R.color.action_sheet_org;
        }
        btn_pos.setTextColor(ContextCompat.getColor(context, color));
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    /**
     * 左侧按钮
     *
     * @param text
     * @param listener
     * @return
     */

    public AlertMsgImgDialog setNegativeButton(String text,
                                               final OnClickListener listener) {

        return setNegativeButton(text, -1, listener);
    }

    public AlertMsgImgDialog setNegativeButton(String text, int color,
                                               final OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText("");
        } else {
            btn_neg.setText(text);
        }
        if (color == -1) {
            color = R.color.action_sheet_grey;
        }
        btn_neg.setTextColor(ContextCompat.getColor(context, color));

        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClick(v);
                dismiss();
            }
        });
        return this;
    }

    /**
     * 设置显示
     */
    private void setLayout() {
        if (!showTitle && !showMsg) {
            //txt_title.setText("");
            //txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            //txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alert_dialog_selector);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alert_dialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alert_dialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alert_dialog_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alert_dialog_selector);
        }
    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public boolean isShowing() {
        if (dialog != null) {
            if (dialog.isShowing())
                return true;
            else
                return false;
        }
        return false;
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }

    }
}
