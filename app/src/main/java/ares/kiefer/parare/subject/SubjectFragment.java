package ares.kiefer.parare.subject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ares.kiefer.parare.R;
import ares.kiefer.parare.database.DatabaseHelper;

import static android.app.Activity.RESULT_OK;

public class SubjectFragment extends Fragment implements View.OnClickListener{

    public static int REQUEST_CODE_ADD = 0;
    RecyclerView subjectRecyclerView;
    SubjectAdapter subjectAdapter;
    DatabaseHelper databaseHelper;
    TextView emptyTextView;

    private FloatingActionButton floatingButton;

    private long currentID;

    public SubjectFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = DatabaseHelper.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subject_fragment, container, false);
        subjectRecyclerView = (RecyclerView) view.findViewById(R.id.rec_view);
        emptyTextView = (TextView) view.findViewById(R.id.tv_empty_subject);
        subjectRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        floatingButton = (FloatingActionButton) view.findViewById(R.id.floating_button);
        floatingButton.setOnClickListener(this);
        currentID = getArguments().getLong("curr_id");
        subjectAdapter = new SubjectAdapter(databaseHelper.getAllUserSubjects(currentID));
        if(!subjectAdapter.isSubjectEmpty()){
            subjectRecyclerView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
            subjectRecyclerView.setAdapter(subjectAdapter);
        }
        else{
            subjectRecyclerView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onClick(View v){
        Intent intent = new Intent(getActivity(), AddSubject.class);
        intent.putExtra("user_id", currentID);
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
            subjectAdapter.notifyDataSetChanged();
    }
}
