package Controllers;

import android.content.Context;

import java.util.List;
import java.util.Random;

import Data.DataInterface;

public class ExcuseController {

    public String FindExcuse(int sportId, Context context) {

        String excuse;

        // get the excuse list by sport
        DataInterface di = new DataInterface(context);
        List<String> excuseList = di.ExcuseList(sportId);

        // convert it to an array
        String[] list = excuseList.toArray(new String[excuseList.size()]);

        // get a random string from the array to send back
        Random r = new Random();
        excuse = list[r.nextInt(list.length)];

        return excuse;
    }


}
