package ares.kiefer.parare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kiefer on 11/14/2017.
 */

public class TaskFragment extends Fragment {

    private long currentID;

    public TaskFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.task_fragment, container, false);
    }

    public void setCurrentID(long id){
        currentID = id;
    }
}
