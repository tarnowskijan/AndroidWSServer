package edu.agh.wsserver.gui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import edu.agh.wsserver.R;

public class GuiParamsFragment extends Fragment {
	public GuiParamsFragment(){}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
        View rootView = inflater.inflate(R.layout.fragment_params, container, false);
          
        return rootView;
    }
}