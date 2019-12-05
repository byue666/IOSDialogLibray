package com.sdkj.iosdialog.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
public class AlertEditDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private EditText edit_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public AlertEditDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public AlertEditDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alert_edit_dialog, null);

        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        edit_msg = (EditText) view.findViewById(R.id.edit_msg);
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


    /**
     * @param typeface Typeface.NOMARL 正常，Typeface.BOLD 加粗
     */
    private void setTitleStyle(int typeface) {
        txt_title.setTypeface(Typeface.defaultFromStyle(typeface));//加粗
    }

    private void setTitleColor(int color) {
        txt_title.setTextColor(color);
    }

    private void setTitleSize(float size) {
        txt_title.setTextSize(size);
    }

    private void setMsgColor(int color) {
        edit_msg.setTextColor(color);
    }

    private void setMsgSize(float size) {
        edit_msg.setTextSize(size);
    }

    /**
     * 恢复初始
     *
     * @return
     */
    public AlertEditDialog setGone() {
        if (lLayout_bg != null) {
            txt_title.setVisibility(View.GONE);
            edit_msg.setVisibility(View.GONE);
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
     * 设置title
     *
     * @param title
     * @return
     */
    public AlertEditDialog setTitle(String title) {
        showTitle = true;
        if (TextUtils.isEmpty(title)) {
            txt_title.setText("提示");
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    /**
     * 设置Message
     *
     * @param msg
     * @return
     */
    public AlertEditDialog setMsg(String msg) {
        showMsg = true;
        if (TextUtils.isEmpty(msg)) {
            edit_msg.setText("");
        } else {
            edit_msg.setText(msg);
        }
        return this;
    }

    public String getEditMsg() {
        String msg = edit_msg.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            msg = "";
        }
        return msg;
    }


    /**
     * 设置Message
     *
     * @param msg
     * @return
     */
    public AlertEditDialog setHint(String msg) {
        showMsg = true;
        if (TextUtils.isEmpty(msg)) {
            edit_msg.setHint("");
        } else {
            edit_msg.setHint(msg);
        }
        return this;
    }

    /**
     * 设置点击外部是否消失
     *
     * @param cancel
     * @return
     */
    public AlertEditDialog setCancelable(boolean cancel) {
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
    public AlertEditDialog setPositiveButton(String text,
                                             final OnClickListener listener) {
        return setPositiveButton(text, -1, listener);
    }

    public AlertEditDialog setPositiveButton(String text, int color,
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

        String editMsg = edit_msg.getText().toString();
        btn_pos.setTag(editMsg + "");
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

    public AlertEditDialog setNegativeButton(String text,
                                             final OnClickListener listener) {

        return setNegativeButton(text, -1, listener);
    }

    public AlertEditDialog setNegativeButton(String text, int color,
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
            txt_title.setText("");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            edit_msg.setVisibility(View.VISIBLE);
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
