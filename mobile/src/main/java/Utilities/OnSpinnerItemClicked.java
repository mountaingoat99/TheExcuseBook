package Utilities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.rodriguez.theexcusebook.ExcuseMe;

public class OnSpinnerItemClicked implements AdapterView.OnItemSelectedListener {

    @Override
    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {
        Toast.makeText(parent.getContext(), "Clicked : " +
                parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();

        ExcuseMe.sportId = pos + 1;
        ExcuseMe e = new ExcuseMe();
        e.savePreferences("sportId", ExcuseMe.sportId);
    }

    @Override
    public void onNothingSelected(AdapterView parent) {

    }
}
