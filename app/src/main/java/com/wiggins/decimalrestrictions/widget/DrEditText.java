package com.wiggins.decimalrestrictions.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * @Description 自定义小数输入框
 * @Author 一花一世界
 */

public class DrEditText extends EditText {

    private int decimalPlaces = 4;//默认最多输入两位小数

    public DrEditText(Context context) {
        super(context);
        initView();
    }

    public DrEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        addTextChangedListener(new TextWatcher() {

            boolean deleteLastChar;// 是否需要删除末尾

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    // 如果点后面有超过三位数值,则删掉最后一位
                    int length = s.length() - s.toString().lastIndexOf(".");
                    // 说明后面有三位数值
                    deleteLastChar = length >= decimalPlaces;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    return;
                }
                if (deleteLastChar) {
                    // 设置新的截取的字符串
                    setText(s.toString().substring(0, s.toString().length() - 1));
                    // 光标强制到末尾
                    setSelection(getText().length());
                }
                // 以小数点开头，前面自动加上 "0"
                if (s.toString().startsWith(".")) {
                    setText("0" + s);
                    setSelection(getText().length());
                }
            }
        });
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText mEditText = (EditText) v;
                // 以小数点结尾，去掉小数点
                if (!hasFocus && mEditText.getText() != null && mEditText.getText().toString().endsWith(".")) {
                    setText(mEditText.getText().subSequence(0, mEditText.getText().length() - 1));
                    setSelection(getText().length());
                }
            }
        });
    }

    /**
     * @Description 设置输入位数
     */
    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces + 2;
    }

    /**
     * @Description 获取输入位数
     */
    public int getDecimalPlaces() {
        return decimalPlaces - 2;
    }
}
