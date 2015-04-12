package Controllers;

import android.content.Context;

import Data.DataInterface;

public class DefaultController {

    public static int DefaultSport(Context context){

        DataInterface di = new DataInterface(context);
        return di.DefaultSport();
    }

    public static String DefaultSportName(int sportid, Context context) {

        DataInterface di = new DataInterface(context);
        return di.DefaultSportName(sportid);
    }
}
