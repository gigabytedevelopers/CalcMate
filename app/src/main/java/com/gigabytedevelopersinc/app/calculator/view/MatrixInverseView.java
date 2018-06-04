

package com.gigabytedevelopersinc.app.calculator.view;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.Html;
import android.text.InputType;

import com.gigabytedevelopersinc.app.calculator.MutableString;
import com.gigabytedevelopersinc.app.calculator.R;

public class MatrixInverseView extends AppCompatTextView {
    private final static char PLACEHOLDER = '\uFEFF';
    public final static String PATTERN = PLACEHOLDER + "^-1";

    public MatrixInverseView(Context context) {
        super(context);
    }

    public MatrixInverseView(final AdvancedDisplay display) {
        super(display.getContext());
        setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        setText(Html.fromHtml("<sup><small>-1</small></sup>"));
        setTextAppearance(display.getContext(), R.style.display_style);
        setPadding(0, 0, 0, 0);
    }

    @Override
    public String toString() {
        return PATTERN;
    }

    public static boolean load(final MutableString text, final AdvancedDisplay parent) {
        boolean changed = MatrixInverseView.load(text, parent, parent.getChildCount());
        if(changed) {
            // Always append a trailing EditText
            CalculatorEditText.load(parent);
        }
        return changed;
    }

    public static boolean load(final MutableString text, final AdvancedDisplay parent, final int pos) {
        if(!text.startsWith(PATTERN)) return false;

        text.setText(text.substring(PATTERN.length()));

        MatrixInverseView mv = new MatrixInverseView(parent);
        parent.addView(mv, pos);

        return true;
    }
}
