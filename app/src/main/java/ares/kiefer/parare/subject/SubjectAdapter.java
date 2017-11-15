package ares.kiefer.parare.subject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import ares.kiefer.parare.R;
import ares.kiefer.parare.data.Subject;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>{

    ArrayList<Subject> subject;
    private AdapterView.OnItemClickListener onItemClickListener;

    public void setSubjects(ArrayList<Subject> subject){
        this.subject = subject;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public SubjectAdapter(ArrayList<Subject> subject){
        this.subject = subject;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_row_item_alternative, parent, false);

        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, final int position){
        Subject currentSubject = subject.get(position);
        holder.tvSubjectSection.setText(currentSubject.getSubjectName() + " " + currentSubject.getSection());

        String days = "";
        int day_counter = 0;
        for(int i = 0; i < 7; i++){
            if(day_counter != 0)
                days.concat(" ");
            if(currentSubject.isClassDay(i) == 1){
                switch(i){
                    case 0: days.concat("M");
                            break;
                    case 1: days.concat("T");
                            break;
                    case 2: days.concat("W");
                            break;
                    case 3: days.concat("H");
                            break;
                    case 4: days.concat("F");
                            break;
                    case 5: days.concat("S");
                            break;
                    case 6: days.concat("S");
                            break;
                }
                day_counter++;
            }
        }
        holder.tvDays.setText(days);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentSubject.getTimeStart());
        String timeFrame = "";
        if(calendar.get(Calendar.HOUR_OF_DAY) < 10)
            timeFrame = timeFrame.concat("0" + calendar.get(Calendar.HOUR_OF_DAY) + ":");
        else
            timeFrame = timeFrame.concat(calendar.get(Calendar.HOUR_OF_DAY) + ":");
        if(calendar.get(Calendar.MINUTE) < 10)
            timeFrame = timeFrame.concat("0" + calendar.get(Calendar.MINUTE) + " - ");
        else
            timeFrame = timeFrame.concat(calendar.get(Calendar.MINUTE) + " - ");

        calendar.setTimeInMillis(currentSubject.getTimeEnd());
        if(calendar.get(Calendar.HOUR_OF_DAY) < 10)
            timeFrame = timeFrame.concat("0" + calendar.get(Calendar.HOUR_OF_DAY) + ":");
        else
            timeFrame = timeFrame.concat(calendar.get(Calendar.HOUR_OF_DAY) + ":");
        if(calendar.get(Calendar.MINUTE) < 10)
            timeFrame = timeFrame.concat("0" + calendar.get(Calendar.MINUTE));
        else
            timeFrame = timeFrame.concat(calendar.get(Calendar.MINUTE) + "");

        holder.tvTime.setText(timeFrame);
    }

    @Override
    public int getItemCount() {
        return subject.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        TextView tvSubjectSection;
        TextView tvDays;
        TextView tvTime;
        View subjectContainer;

        public SubjectViewHolder(View itemView){
            super(itemView);
            tvSubjectSection = (TextView) itemView.findViewById(R.id.alternate_subject_section);
            tvDays = (TextView) itemView.findViewById(R.id.alternate_days);
            tvTime = (TextView) itemView.findViewById(R.id.alternate_start_end_time);
            subjectContainer = itemView.findViewById(R.id.subject_container);
        }
    }

    public boolean isSubjectEmpty(){
        return subject.isEmpty();
    }
}
