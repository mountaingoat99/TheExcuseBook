package Utilities;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.rodriguez.theexcusebook.ExcuseMe;

import Data.DataInterface;

public class OnSpinnerItemClicked implements AdapterView.OnItemSelectedListener {

    private Dialog _dialog;
    private int _originalSportID;
    private Context _context;

    public OnSpinnerItemClicked(Dialog dialog, int sportID, Context context) {
        _dialog = dialog;
        _originalSportID = sportID;
        _context = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent,
                               View view, int pos, long id) {
        Toast.makeText(parent.getContext(), "Clicked : " +
                parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();

        ExcuseMe.sportId = pos + 1;
        DataInterface di = new DataInterface(_context);
        ExcuseMe em = new ExcuseMe();

        if (_originalSportID != ExcuseMe.sportId) {
            di.UpdateDefaultSport(ExcuseMe.sportId);
            _dialog.cancel();
            em.checkDefaultSport(_context);
        }
    }

    @Override
    public void onNothingSelected(AdapterView parent) {

    }
}
