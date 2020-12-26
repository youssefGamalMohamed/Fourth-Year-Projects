package com.example.employeeassignment1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyEmployeeRecyclerViewAdater extends RecyclerView.Adapter<MyEmployeeRecyclerViewAdater.EmployeeHolder> {

    ArrayList<Employee> emparr;

    public MyEmployeeRecyclerViewAdater(ArrayList<Employee> emparr) {
        this.emparr = emparr;
    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycler_view_item,null,false);
        EmployeeHolder empholder = new EmployeeHolder(v);
        return empholder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeHolder holder, int position) {
        Employee emp = emparr.get(position);
        holder.name.setText(emp.Name.toString());
        holder.title.setText(emp.Title.toString());
        holder.email.setText(emp.Email.toString());
        holder.phone.setText(emp.Phone.toString());
        holder.departmentname.setText(emp.DepartmentName.toString());
        holder.empimage.setImageResource(R.drawable.personimage);
    }

    @Override
    public int getItemCount() {
        return emparr.size();
    }

    class EmployeeHolder extends RecyclerView.ViewHolder{
        TextView name , title , phone , email , departmentname;
        ImageView empimage;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.personname);
            title = itemView.findViewById(R.id.persontitle);
            phone = itemView.findViewById(R.id.personphone);
            email = itemView.findViewById(R.id.personemail);
            empimage = itemView.findViewById(R.id.personimage);
            departmentname = itemView.findViewById(R.id.persondepartmentname);
        }
    }
}
