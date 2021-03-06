package br.com.mymarket.fragments;

import android.app.Fragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import br.com.mymarket.R;
import br.com.mymarket.activities.MainActivity;
import br.com.mymarket.infra.MyLog;
import br.com.mymarket.utils.MaskWatcher;

public class OauthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_oauth, null);

        TextView tv = (TextView)view.findViewById(R.id.cadastrar);
        tv.setPaintFlags(tv.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        return view;
    }
	
    public void acessarApk(View view) {
    	MyLog.i("CLICK FRAGMENT OAUTH");
    	((MainActivity)this.getActivity()).acessarApk(view);
	}

    public void cadastrarActivity(View view) {
        MyLog.i("CLICK FRAGMENT REGISTER");
        ((MainActivity)this.getActivity()).cadastrarActivity(view);
    }

    
}
