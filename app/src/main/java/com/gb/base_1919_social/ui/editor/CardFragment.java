package com.gb.base_1919_social.ui.editor;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.gb.base_1919_social.R;
import com.gb.base_1919_social.repository.PostData;
import com.gb.base_1919_social.ui.MainActivity;

import java.util.Calendar;


public class CardFragment extends Fragment {



    PostData postData;

    public static CardFragment newInstance(PostData postData) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putParcelable("postData", postData);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_card, container, false);
    }
    Calendar calendar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(getArguments()!=null){ // FIXME HW нужен рефактор. Добавить initView(), setContent(), setListeners(), extractDate(), save()
            postData = getArguments().getParcelable("postData");
            ((EditText)view.findViewById(R.id.inputTitle)).setText(postData.getTitle());
            ((EditText)view.findViewById(R.id.inputDescription)).setText(postData.getDescription());

            calendar = Calendar.getInstance();
            calendar.setTime(postData.getDate());
            ((DatePicker) view.findViewById(R.id.inputDate)).init(calendar.get(Calendar.YEAR)-1,
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                ((DatePicker) view.findViewById(R.id.inputDate)).setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        calendar.set(Calendar.YEAR,i);
                        calendar.set(Calendar.MONTH,i1);
                        calendar.set(Calendar.DAY_OF_MONTH,i2);
                    }
                });
            }
            view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View it) {
                    postData.setTitle(((EditText)view.findViewById(R.id.inputTitle)).getText().toString());
                    postData.setDescription(((EditText)view.findViewById(R.id.inputDescription)).getText().toString());

                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
                        DatePicker datePicker= ((DatePicker) view.findViewById(R.id.inputDate));
                        calendar.set(Calendar.YEAR,datePicker.getYear());
                        calendar.set(Calendar.MONTH,datePicker.getMonth());
                        calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                    }
                    postData.setDate(calendar.getTime());
                    ((MainActivity) requireActivity()).getPublisher().sendMessage(postData);
                    ((MainActivity) requireActivity()).getSupportFragmentManager().popBackStack();
                }
            });
        }
    }

}