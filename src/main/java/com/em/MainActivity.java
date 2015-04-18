package com.em;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.em.enums.AttendanceType;
import com.em.vo.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private List<Student> students;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        progressDialog = new ProgressDialog(this);
        fetchStudents(1l);
    }

    public void selectFragment(int index) {
        mViewPager.setCurrentItem(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        //SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_save) {
            Toast.makeText(this.getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
            return true;
        }
        /*else if( id==  R.id.action_search ){
            Toast.makeText(this.getApplicationContext(), "TODO", Toast.LENGTH_SHORT).show();
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    private List<Student> fetchStudents() {
        RequestQueue rq = Volley.newRequestQueue(this);
        List<Student> data = new ArrayList<Student>();
        Student student = new Student();
        student.setStudentId(21231l);
        student.setStudentName("Abhishek Sharma");
        student.setStudentIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.student, null));
        student.setType(AttendanceType.UNDEFINED);
        data.add(student);
        JSONObject jsonObject;
        try {
            jsonObject   = new JSONObject("{\"name\":\"abhishek}\"");
        }
        catch(JSONException e){

        }
        student = new Student();
        student.setStudentId(21232l);
        student.setStudentName("Santosh Bhattacharya");
        student.setStudentIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.student, null));
        student.setType(AttendanceType.UNDEFINED);
        data.add(student);
        student = new Student();
        student.setStudentId(21233l);
        student.setStudentName("Kumar Vishwas");
        student.setType(AttendanceType.UNDEFINED);
        student.setStudentIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.student, null));
        data.add(student);
        return data;
    }

    private List<Student> fetchStudents(Long studentId) {
        final Activity activity = this;
        RequestQueue rq = Volley.newRequestQueue(this);
        showProgressDialog();
        StringRequest postReq = new StringRequest(Request.Method.GET, "http://google.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(activity.getApplicationContext(), response, Toast.LENGTH_LONG).show();
                students = fetchStudents();
                mSectionsPagerAdapter.setDataProvider(students);
                mViewPager.setAdapter(mSectionsPagerAdapter);
                hideProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error [" + error + "]");
            }
        });
        rq.add(postReq);
        return new ArrayList<Student>();
    }

    private void showProgressDialog() {
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void hideProgressDialog() {
        progressDialog.hide();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        List<Student> data;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setDataProvider(List<Student> data) {
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return data.get(position).getStudentName();
        }
    }

    public static class PlaceholderFragment extends Fragment {
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
            ImageView studentImage = (ImageView) rootView.findViewById(R.id.studentImage);
            studentImage.setImageDrawable(data.getStudentIcon());
            ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(data.getType().getColor());
            rootView.findViewById(R.id.presentButton).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), data.getType().toString() + "=>Present", Toast.LENGTH_SHORT).show();
                    data.setType(AttendanceType.PRESENT);
                    ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(Color.GREEN);
                    ((Button) rootView.findViewById(R.id.absentButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.leaveButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.lateButton)).setTextColor(Color.BLACK);
                    //((ViewPager) getActivity()).selectPage(1);
                }
            });
            rootView.findViewById(R.id.absentButton).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), data.getType().toString() + "=>Absent", Toast.LENGTH_SHORT).show();
                    data.setType(AttendanceType.ABSENT);
                    ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.absentButton)).setTextColor(Color.RED);
                    ((Button) rootView.findViewById(R.id.leaveButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.lateButton)).setTextColor(Color.BLACK);

                }
            });
            rootView.findViewById(R.id.leaveButton).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), data.getType().toString() + "=>Leave", Toast.LENGTH_SHORT).show();
                    data.setType(AttendanceType.LEAVE);
                    ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.absentButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.leaveButton)).setTextColor(Color.YELLOW);
                    ((Button) rootView.findViewById(R.id.lateButton)).setTextColor(Color.BLACK);

                }
            });
            rootView.findViewById(R.id.lateButton).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(getActivity().getApplicationContext(), data.getType().toString() + "=>Late", Toast.LENGTH_SHORT).show();
                    data.setType(AttendanceType.LATE);
                    ((Button) rootView.findViewById(R.id.presentButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.absentButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.leaveButton)).setTextColor(Color.BLACK);
                    ((Button) rootView.findViewById(R.id.lateButton)).setTextColor(Color.BLUE);

                }
            });
            return rootView;
        }
    }
}