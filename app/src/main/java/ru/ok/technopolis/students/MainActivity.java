package ru.ok.technopolis.students;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final List<Student> students = new ArrayList<>();
    private int indexOfStudent = -1;
    private final Random random = new Random();
    private final StudentAdapter studentAdapter = new StudentAdapter(students, new StudentAdapter.Listener() {
        @Override
        public void onStudentClick(Student student) {
            ImageView img = findViewById(R.id.activity_main_img);
            EditText firstName = findViewById(R.id.activity_main_et_fn);
            EditText secondName = findViewById(R.id.activity_main_et_sn);
            firstName.setText(student.getFirstName());
            secondName.setText(student.getSecondName());
            img.setImageResource(student.getPhoto());
            if(student.isMaleGender()){
                setCheckBox(R.id.activity_main_chb_w, true);
                setCheckBox(R.id.activity_main_chb_m, false);
            }
            else{
                setCheckBox(R.id.activity_main_chb_m, true);
                setCheckBox(R.id.activity_main_chb_w, false);
            }
            indexOfStudent = students.indexOf(student);
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateStudents();
        setCheckBox(R.id.activity_main_chb_m, true);
        RecyclerView recyclerView = findViewById(R.id.activity_main_rv);
        recyclerView.setAdapter(studentAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        findViewById(R.id.activity_main_chb_m).setOnClickListener(this);
        findViewById(R.id.activity_main_chb_w).setOnClickListener(this);
        findViewById(R.id.activity_main_but_add).setOnClickListener(this);
        findViewById(R.id.activity_main_but_del).setOnClickListener(this);
        findViewById(R.id.activity_main_but_save).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.activity_main_chb_m) {
            if(isCheckBox(R.id.activity_main_chb_m))
                setCheckBox(R.id.activity_main_chb_w, false);
            else
                setCheckBox(R.id.activity_main_chb_w, true);
        }
        else if (v.getId() == R.id.activity_main_chb_w) {
            if(isCheckBox(R.id.activity_main_chb_w))
                setCheckBox(R.id.activity_main_chb_m, false);
            else
                setCheckBox(R.id.activity_main_chb_m, true);
        }
        else if (v.getId() == R.id.activity_main_but_add) {
            addStudent();
            studentAdapter.notifyDataSetChanged();
        }
        else if (v.getId() == R.id.activity_main_but_del) {
            deleteStudent();
            studentAdapter.notifyDataSetChanged();
        }
        else if (v.getId() == R.id.activity_main_but_save) {
            changeStudent();
            studentAdapter.notifyDataSetChanged();
        }
    }

    public void changeStudent(){
        if (indexOfStudent >= 0){
            EditText firstName = findViewById(R.id.activity_main_et_fn);
            EditText secondName = findViewById(R.id.activity_main_et_sn);
            students.get(indexOfStudent).setFirstName(firstName.getText().toString());
            students.get(indexOfStudent).setSecondName(secondName.getText().toString());
            students.get(indexOfStudent).setMaleGender(isCheckBox(R.id.activity_main_chb_w));
            indexOfStudent = -1;
        }
    }

    public void deleteStudent() {
        if (indexOfStudent >= 0){
            ImageView img = findViewById(R.id.activity_main_img);
            EditText firstName = findViewById(R.id.activity_main_et_fn);
            EditText secondName = findViewById(R.id.activity_main_et_sn);
            img.setImageResource(0);
            firstName.setText("");
            secondName.setText("");
            students.remove(indexOfStudent);
            indexOfStudent = -1;
        }
    }

    public void addStudent(){
        EditText firstName = findViewById(R.id.activity_main_et_fn);
        EditText secondName = findViewById(R.id.activity_main_et_sn);
        CheckBox box = findViewById(R.id.activity_main_chb_w);
        int img = 0;
        int rand = random.nextInt(3);
        if(box.isChecked()){
            switch (rand) {
                case 0:
                    img = R.drawable.woman1;
                    break;
                case 1:
                    img = R.drawable.woman2;
                    break;
                case 2:
                    img = R.drawable.woman3;
                    break;
                    default:
                        break;
            }
        }
        else {
            switch (rand) {
                case 0:
                    img = R.drawable.man1;
                    break;
                case 1:
                    img = R.drawable.man2;
                    break;
                case 2:
                    img = R.drawable.man3;
                    break;
                default:
                    break;
            }
        }
        students.add(new Student(firstName.getText().toString(), secondName.getText().toString(), box.isChecked(), img));
    }

    public void setCheckBox(int id, boolean value){
        CheckBox box = findViewById(id);
        box.setChecked(value);
    }

    public boolean isCheckBox(int id){
        CheckBox box = findViewById(id);
        return box.isChecked();
    }

    private void generateStudents(){
        students.add(new Student("Vasia","Sidorov",false,R.drawable.male_1));
        students.add(new Student("Sanay","Smirnov",false,R.drawable.male_2));
        students.add(new Student("Masdas","Palauskas",false,R.drawable.male_3));
        students.add(new Student("Lisa","Skerlett",true,R.drawable.female_1));
        students.add(new Student("Lucy","Gomez",true,R.drawable.female_2));
        students.add(new Student("Ana","Soong",true,R.drawable.female_3));
    }
}
