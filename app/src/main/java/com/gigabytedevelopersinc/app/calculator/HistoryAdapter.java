
package com.gigabytedevelopersinc.app.calculator;

import java.util.Vector;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gigabytedevelopersinc.app.calculator.view.HistoryLine;

class HistoryAdapter extends BaseAdapter {
    private final Vector<HistoryEntry> mEntries;
    private final LayoutInflater mInflater;
    private final EquationFormatter mEquationFormatter;
    private final History mHistory;

    HistoryAdapter(Context context, History history) {
        mEntries = history.mEntries;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEquationFormatter = new EquationFormatter();
        mHistory = history;
    }

    @Override
    public int getCount() {
        return mEntries.size() - 1;
    }

    @Override
    public Object getItem(int position) {
        return mEntries.elementAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HistoryLine view;
        if(convertView == null) {
            view = (HistoryLine) mInflater.inflate(R.layout.history_entry, parent, false);
        }
        else {
            view = (HistoryLine) convertView;
        }

        TextView expr = (TextView) view.findViewById(R.id.historyExpr);
        TextView result = (TextView) view.findViewById(R.id.historyResult);

        HistoryEntry entry = mEntries.elementAt(position);
        expr.setText(Html.fromHtml(mEquationFormatter.insertSupscripts(entry.getBase())));
        result.setText(entry.getEdited());
        view.setHistoryEntry(entry);
        view.setHistory(mHistory);
        view.setAdapter(this);

        return view;
    }
}
