package com.aki.geographiccollection.client.view.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aki.geographiccollection.client.view.ui.callback.DialogCallBack;
import com.aki.geographiccollection.client.utils.DisplayUtil;
import com.aki.geographiccollection.client.utils.UIUtils;

import org.aki.geographiccollection.R;

import butterknife.ButterKnife;

/**
 * Created by aki on 2016/8/25.
 */
public class BaseDialog extends Dialog {
    private TextView contentText, positive, negative;
    private DialogCallBack dialogCallBack;

    public BaseDialog(Context context, @Nullable View parent, @Nullable DialogCallBack dialogCallBack, String content, String... option) {
        super(context);
        this.dialogCallBack = dialogCallBack;
        initViews(parent);
        initDate(dialogCallBack, content, option);
    }

    private void initViews(View parent) {

        ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();

        layoutParams.height = DisplayUtil.getScreanHeight() / 4;
        layoutParams.width = DisplayUtil.getScreanWidth() * 178 / 100;

        parent.setLayoutParams(layoutParams);

        contentText = UIUtils.getInstance().$(parent, R.id.content_message);
        positive = UIUtils.getInstance().$(parent, R.id.tv_positive);
        negative = UIUtils.getInstance().$(parent, R.id.tv_negative);

        getWindow().setBackgroundDrawable(new ColorDrawable(0x00ffffff));

        setContentView(parent);
        ButterKnife.bind(parent);
    }

    private void initDate(DialogCallBack dialogCallBack, String content, String... options) {

        contentText.setText(TextUtils.isEmpty(content) ? getContext().getString(R.string.confirm) : content);

        if (null != options) {
            switch (options.length) {
                case 0:
                    break;
                case 1:
                    positive.setText(options[0]);
                    break;
                case 2:
                    positive.setText(options[0]);
                    negative.setText(options[1]);
                    break;
            }
        }

        positive.setOnClickListener(onClickListener);
        negative.setOnClickListener(onClickListener);
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tv_positive) {
                dialogCallBack.agree();
            } else if (v.getId() == R.id.tv_negative) {
                dialogCallBack.reject();
            }
        }
    };
}
