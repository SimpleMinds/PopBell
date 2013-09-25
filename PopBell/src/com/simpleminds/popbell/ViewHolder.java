package com.simpleminds.popbell;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class ViewHolder {

private TextView textView;
private CheckBox checkBox;
public View base;

public ViewHolder(View base){
this.base = base;
}

public TextView getTextView() {
return textView;
}

public void setTextView(TextView textView) {
this.textView = textView;
}

public CheckBox getCheckBox() {
return checkBox;
}

public void setCheckBox(CheckBox checkBox) {
this.checkBox = checkBox;
}
}