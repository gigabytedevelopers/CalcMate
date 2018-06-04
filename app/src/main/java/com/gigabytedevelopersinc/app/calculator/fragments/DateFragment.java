package com.gigabytedevelopersinc.app.calculator.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.gigabytedevelopersinc.app.calculator.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFragment extends Fragment {

    public Calendar calendar;
    public TextView startDateView, endDateView, startTimeView, endTimeView;
    public int year_start, month_start, day_start, year_end, month_end, day_end;
    public int hour_start, minute_start, hour_end, minute_end;
    public boolean is24HourView;
    String stringStartDate, stringEndDate;
    Button startDateBtn, endDateBtn, startTimeBtn, endTimeBtn;
    private DatePickerDialog.OnDateSetListener myStartDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            year_start = arg1;
            month_start = arg2 + 1;
            day_start = arg3;
            startDateView.setText(year_start + "-" + month_start + "-" + day_start);
        }
    };
    private DatePickerDialog.OnDateSetListener myEndDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            year_end = arg1;
            month_end = arg2 + 1;
            day_end = arg3;
            endDateView.setText(year_end + "-" + month_end + "-" + day_end);
        }
    };
    private TimePickerDialog.OnTimeSetListener myStartTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
            hour_start = arg1;
            minute_start = arg2;
            startTimeView.setText(hour_start + ":" + minute_start);
        }
    };
    private TimePickerDialog.OnTimeSetListener myEndTimeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
            hour_end = arg1;
            minute_end = arg2;
            endTimeView.setText(hour_end + ":" + minute_end);
        }
    };

    public DateFragment() {
    }

    public static android.support.v4.app.Fragment newInstance() {
        return new DateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startDateView = (TextView) view.findViewById(R.id.startDateTextView);
        calendar = Calendar.getInstance();

        year_start = calendar.get(Calendar.YEAR);
        month_start = calendar.get(Calendar.MONTH);
        day_start = calendar.get(Calendar.DAY_OF_MONTH);

        endDateView = (TextView) view.findViewById(R.id.endDateTextView);
        year_end = calendar.get(Calendar.YEAR);
        month_end = calendar.get(Calendar.MONTH);
        day_end = calendar.get(Calendar.DAY_OF_MONTH);

        startTimeView = (TextView) view.findViewById(R.id.startTimeTextView);
        endTimeView = (TextView) view.findViewById(R.id.endTimeTextView);

        startDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = new DatePickerDialog(getActivity(), myStartDateListener, year_start, month_start, day_start);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        calcDiff(view);
                    }
                });
                dialog.show();
            }
        });
        startDateBtn = (Button) view.findViewById(R.id.start_date_btn);
        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = new DatePickerDialog(getActivity(), myStartDateListener, year_start, month_start, day_start);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        calcDiff(view);
                    }
                });
                dialog.show();
            }
        });

        endDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = new DatePickerDialog(getActivity(), myEndDateListener, year_end, month_end, day_end);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        calcDiff(view);
                    }
                });
                dialog.show();
            }
        });
        endDateBtn = (Button) view.findViewById(R.id.end_date_btn);
        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = new DatePickerDialog(getActivity(), myEndDateListener, year_end, month_end, day_end);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        calcDiff(view);
                    }
                });
                dialog.show();
            }
        });

        startTimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = new TimePickerDialog(getActivity(), myStartTimeListener, hour_start, minute_start, is24HourView);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        calcDiff(view);
                    }
                });
                dialog.show();
            }
        });
        startTimeBtn = (Button) view.findViewById(R.id.start_time_btn);
        startTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = new TimePickerDialog(getActivity(), myStartTimeListener, hour_start, minute_start, is24HourView);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        calcDiff(view);
                    }
                });
                dialog.show();
            }
        });

        endTimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = new TimePickerDialog(getActivity(), myEndTimeListener, hour_end, minute_end, is24HourView);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        calcDiff(view);
                    }
                });
                dialog.show();
            }
        });
        endTimeBtn = (Button) view.findViewById(R.id.end_time_btn);
        endTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Dialog dialog = new TimePickerDialog(getActivity(), myEndTimeListener, hour_end, minute_end, is24HourView);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        calcDiff(view);
                    }
                });
                dialog.show();
            }
        });
    }

    public void calcDiff(View view) {
        try {
            stringStartDate = year_start + " " + month_start + " " + day_start + " " + hour_start + ":" + minute_start;
            stringEndDate = year_end + " " + month_end + " " + day_end + " " + hour_end + ":" + minute_end;
            DateFormat format = new SimpleDateFormat("yyyy MM d HH:mm", Locale.ENGLISH);
            Date startDate = format.parse(stringStartDate);
            Date endDate = format.parse(stringEndDate);
            System.out.println(startDate);
            System.out.println(endDate);
            long different = endDate.getTime() - startDate.getTime();

            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;

            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            long elapsedMinutes = different / minutesInMilli;
            different = different % minutesInMilli;

            long elapsedSeconds = different / secondsInMilli;

            TextView resultView = (TextView) view.findViewById(R.id.result);
            resultView.setText(elapsedDays + " days\n" + elapsedHours + " hours\n" + elapsedMinutes + " minutes\n");
        } catch (Exception e) {
            //
        }
    }
}
