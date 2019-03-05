package ru.ok.technopolis.students;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;


import java.util.List;

public class StudentAdapter extends  RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{

    private final List <Student> students ;
    private final Listener onStudentClickListener;

    public StudentAdapter(List<Student> students, Listener onStudentClickListener) {
        this.students = students;
        this.onStudentClickListener = onStudentClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.student_item, viewGroup, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStudentClickListener.onStudentClick((Student) v.getTag());
            }
        });
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder viewHolder, int i) {
        Student student = students.get(i);
        viewHolder.bind(student);
        viewHolder.itemView.setTag(student);
    }

    interface Listener{
        void onStudentClick(Student student);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static final class StudentViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final ImageView posterImageView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.student_item_name);
            posterImageView = itemView.findViewById(R.id.student_item_img);
        }

        private void bind(@NonNull Student student) {
            nameTextView.setText(student.getFirstName() + " " + student.getSecondName());
            posterImageView.setImageResource(student.getPhoto());
        }

    }
}
