package com.em.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.em.AppController;
import com.em.MainActivity;
import com.em.R;
import com.em.enums.AttendanceType;
import com.em.utils.EmUtils;
import com.em.vo.Student;

public class PlaceholderFragment extends Fragment {
    private static final String INDEX = "fragment_number";

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView studentName = (TextView) rootView.findViewById(R.id.studentName);
        int index = getArguments().getInt(INDEX);
        final Student data = ((MainActivity) getActivity()).getStudents().get(index);
        studentName.setText(data.getStudentName());
        TextView studentId = (TextView) rootView.findViewById(R.id.studentId);
        studentId.setText(data.getStudentId().toString());
        NetworkImageView studentImage = (NetworkImageView) rootView.findViewById(R.id.studentImage);
        loadProfileImage(studentImage);
        ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(AttendanceType.getAttendanceType(data.getType()).getColor());
        rootView.findViewById(R.id.presentButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), AttendanceType.getAttendanceType(data.getType()).toString() + "=>Present", Toast.LENGTH_SHORT).show();
                data.setType(AttendanceType.PRESENT.getType());
                ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(Color.GREEN);
                ((Button) rootView.findViewById(R.id.absentButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.leaveButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.lateButton)).setTextColor(Color.BLACK);
                //((ViewPager) getActivity()).selectPage(1);
            }
        });
        rootView.findViewById(R.id.absentButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), AttendanceType.getAttendanceType(data.getType()).toString() + "=>Absent", Toast.LENGTH_SHORT).show();
                data.setType(AttendanceType.ABSENT.getType());
                ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.absentButton)).setTextColor(Color.RED);
                ((Button) rootView.findViewById(R.id.leaveButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.lateButton)).setTextColor(Color.BLACK);
            }
        });
        rootView.findViewById(R.id.leaveButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), AttendanceType.getAttendanceType(data.getType()).toString() + "=>Leave", Toast.LENGTH_SHORT).show();
                data.setType(AttendanceType.LEAVE.getType());
                ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.absentButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.leaveButton)).setTextColor(Color.YELLOW);
                ((Button) rootView.findViewById(R.id.lateButton)).setTextColor(Color.BLACK);
            }
        });
        rootView.findViewById(R.id.lateButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), AttendanceType.getAttendanceType(data.getType()).toString() + "=>Late", Toast.LENGTH_SHORT).show();
                data.setType(AttendanceType.LATE.getType());
                ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.absentButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.leaveButton)).setTextColor(Color.BLACK);
                ((Button) rootView.findViewById(R.id.lateButton)).setTextColor(Color.BLUE);
            }
        });
        return rootView;
    }
    private void loadProfileImage(NetworkImageView imageView){
        ImageLoader mImageLoader = AppController.getInstance().getImageLoader();
        imageView.setImageUrl(EmUtils.getrServerUrl()+"images", mImageLoader);
    }
}