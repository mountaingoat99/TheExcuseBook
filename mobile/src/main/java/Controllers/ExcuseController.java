package Controllers;

import android.content.Context;

import java.util.Hashtable;

import Data.DataInterface;

/**
 * Created by Jeremey on 4/7/2015.
 */
public class ExcuseController {

    public String FindExcuse(int sportId, Context context) {

        String excuse = null;

        DataInterface di = new DataInterface(context);
        Hashtable<Integer, String> excuseList = di.ExcuseListBySport(sportId);

        return excuse;
    }


}
