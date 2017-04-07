package com.wiggins.decimalrestrictions;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wiggins.decimalrestrictions.base.BaseActivity;
import com.wiggins.decimalrestrictions.utils.StringUtil;
import com.wiggins.decimalrestrictions.utils.ToastUtil;
import com.wiggins.decimalrestrictions.utils.UIUtils;
import com.wiggins.decimalrestrictions.widget.DrEditText;
import com.wiggins.decimalrestrictions.widget.TitleView;

/**
 * @Description EditText输入小数位限制
 * @Author 一花一世界
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TitleView titleView;
    private TextView tv_decimal_places;
    private DrEditText edt_input;
    private EditText edt_decimal_places;
    private Button btn_set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
        setData();
    }

    private void initView() {
        titleView = (TitleView) findViewById(R.id.titleView);
        titleView.setAppTitle(UIUtils.getString(R.string.title));
        titleView.setLeftImageVisibility(View.GONE);
        tv_decimal_places = (TextView) findViewById(R.id.tv_decimal_places);
        edt_input = (DrEditText) findViewById(R.id.edt_input);
        edt_decimal_places = (EditText) findViewById(R.id.edt_decimal_places);
        btn_set = (Button) findViewById(R.id.btn_set);
    }

    private void setListener() {
        btn_set.setOnClickListener(this);
    }

    private void setData() {
        tv_decimal_places.setText(String.format(UIUtils.getString(R.string.input_current_limit_decimal_places), edt_input.getDecimalPlaces()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_set:
                String decimalPlaces = edt_decimal_places.getText().toString();
                if (StringUtil.isEmpty(decimalPlaces)) {
                    ToastUtil.showText(UIUtils.getString(R.string.input_not_empty));
                } else if (Integer.valueOf(decimalPlaces) < 1) {
                    ToastUtil.showText(UIUtils.getString(R.string.input_least_one));
                    edt_input.setText("");
                    edt_decimal_places.setText("");
                } else {
                    edt_input.setDecimalPlaces(Integer.valueOf(decimalPlaces));
                    ToastUtil.showText(UIUtils.getString(R.string.setup_success));
                    setData();
                    edt_input.setText("");
                    edt_decimal_places.setText("");
                }
                break;
        }
    }
}
