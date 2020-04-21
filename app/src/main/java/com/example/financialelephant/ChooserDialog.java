package com.example.financialelephant;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class ChooserDialog {

    private ImageView backBtn;
    private TextView chooserText;
    private RecyclerView chooserRecView;

    public void showDialog(Activity activity, Bundle args) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        initView(dialog);
        String title = args.getString("ChooserName");
        chooserText.setText(title);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void initView(Dialog dialog){
        backBtn = dialog.findViewById(R.id.backBtn);
        chooserText = dialog.findViewById(R.id.chooserText);
        chooserRecView = dialog.findViewById(R.id.chooserRecView);
    }

}
