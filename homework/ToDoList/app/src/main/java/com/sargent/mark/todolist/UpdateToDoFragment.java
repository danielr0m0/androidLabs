package com.sargent.mark.todolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.sargent.mark.todolist.data.Category;

import java.util.Calendar;

/**
 * Created by mark on 7/5/17.
 */

public class UpdateToDoFragment extends DialogFragment {

    private EditText toDo;
    private DatePicker dp;
    private Spinner sp;
    private Button add;
    private final String TAG = "updatetodofragment";
    private long id;


    public UpdateToDoFragment(){}

    public static UpdateToDoFragment newInstance(int year, int month, int day, String descrpition, String category, long id) {
        UpdateToDoFragment f = new UpdateToDoFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", month);
        args.putInt("day", day);
        args.putLong("id", id);
        args.putString("description", descrpition);
        args.putString("category", category);

        f.setArguments(args);

        return f;
    }

    //To have a way for the activity to get the data from the dialog
    public interface OnUpdateDialogCloseListener {
        void closeUpdateDialog(int year, int month, int day, String description, String category, long id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);
        toDo = (EditText) view.findViewById(R.id.toDo);
        dp = (DatePicker) view.findViewById(R.id.datePicker);
        add = (Button) view.findViewById(R.id.add);

        sp = (Spinner) view.findViewById(R.id.spinner);
        sp = (Spinner) view.findViewById(R.id.category);
        sp.setAdapter(new ArrayAdapter<Category>(this.getActivity(), R.layout.support_simple_spinner_dropdown_item, Category.values()));

        // need to put the spinner on category selected. =
        //getting the index of the category
        sp.setSelection(getCategoryIndex(sp,getArguments().getString("category")));

        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        id = getArguments().getLong("id");
        String description = getArguments().getString("description");
        dp.updateDate(year, month, day);

        toDo.setText(description);

        add.setText("Update");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateToDoFragment.OnUpdateDialogCloseListener activity = (UpdateToDoFragment.OnUpdateDialogCloseListener) getActivity();
                Log.d(TAG, "id: " + id);
                activity.closeUpdateDialog(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), toDo.getText().toString(), sp.getSelectedItem().toString(),  id);
                UpdateToDoFragment.this.dismiss();
            }
        });

        return view;
    }

    // need to get index of category to set selected
    public int getCategoryIndex(Spinner sp, String key ){
        int index= 0;
            for (int i = 0; i < sp.getCount(); i++){
                if(sp.getItemAtPosition(i).toString().equals(key)){
                    index = i;
                }
            }
        return index;
    }
}