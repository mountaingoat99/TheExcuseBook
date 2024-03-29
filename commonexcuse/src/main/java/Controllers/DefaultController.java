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

    public static int AddNewSport(String sportName, Context context){
        DataInterface di = new DataInterface(context);
        return di.AddSport(sportName);
    }

    public static void AddNewSportWithID(String sportName, int sportId, Context context) {
        DataInterface di = new DataInterface(context);
        di.AddSport(sportName, sportId);
    }
}
