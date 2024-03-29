package Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DataInterface extends DatabaseHelper {

    public DataInterface(Context context) {
        super(context);
    }

    // select List of Excuses by sport
    public List<String> ExcuseList(int sportid) {
        List<String> excuse = new ArrayList<>();

        String selectQuery = "SELECT " + getKeyId() + ", " + getExcuseName()
                + " FROM " + getExcuses()
                + " WHERE " + getSportId() + "=" + sportid;
        Log.e(getLog(), selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do{
                ExcuseModel s = new ExcuseModel();
                excuse.add(s.setExcuseName(c.getString(c.getColumnIndex(getExcuseName()))));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return excuse;
    }

    // select the list of sports and the ID's
    public LinkedHashMap<Integer, String> ExcuseSports() {
        LinkedHashMap<Integer, String> sports = new LinkedHashMap<>();

        String selectQuery = "SELECT " + getKeyId() + ", " + getSportName()
                  + " FROM " + getSports();

        Log.e(getLog(), selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                sports.put(c.getInt(0), c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return sports;
    }

    // Insert new Excuse record by sport
    public void AddExcuse(String excuseName, int sportId){
        SQLiteDatabase db = this.getWritableDatabase();
        ExcuseModel excuse = new ExcuseModel(sportId, excuseName);
        createExcuses(excuse, db);
        db.close();
    }

    // Insert New Sport
    public int AddSport(String sportName){
        String newSportExcuse = "I've never liked this sport anyway";

        SQLiteDatabase db = this.getWritableDatabase();
        SportsModel sport = new SportsModel(sportName);
        createNewSport(sport, db);

        // get the new sportid
        int sportID = newSportId(sportName);

        // add one default excuse for the sport here
        // open the db again
        SQLiteDatabase db1 = this.getWritableDatabase();
        ExcuseModel excuse = new ExcuseModel(sportID, newSportExcuse);
        createExcuses(excuse, db1);
        db.close();

        return sportID;
    }

    // Insert New Sport with an ID, the wear app uses this on sync
    public void AddSport(String sportName, int sportid){
        String newSportExcuse = "I've never liked this sport anyway";

        SQLiteDatabase db = this.getWritableDatabase();
        SportsModel sport = new SportsModel(sportid, sportName);
        createNewSport(sport, db);

        // add one default excuse for the sport here
        // open the db again
        SQLiteDatabase db1 = this.getWritableDatabase();
        ExcuseModel excuse = new ExcuseModel(sportid, newSportExcuse);
        createExcuses(excuse, db1);
        db.close();
    }

    // get the new sportID
    public int newSportId(String newSportName) {
        int sportid = 0;
        String selectQuery = "SELECT " + getKeyId()
                + " FROM " + getSports()
                + " WHERE " + getSportName() + "='" + newSportName + "'";
        Log.e(getLog(), selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                sportid = c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return sportid;
    }

    // check the default_sport
    public int DefaultSport() {
        int sportid = 0;
        String selectQuery = "SELECT " + getDEFAULT_ID()
                + " FROM " + getDEFAULT_SPORT_TABLE()
                + " WHERE " + getKeyId() + "=1";
        Log.e(getLog(), selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                sportid = c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return sportid;
    }

    public String DefaultSportName(int sportid){
        String sportName = null;
        String selectQuery = "SELECT " + getSportName()
                + " FROM " + getSports()
                + " WHERE " + getKeyId() + "=" + sportid;

        Log.e(getLog(), selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {
            do {
                sportName = c.getString(0);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return sportName;
    }

    // update the default id
    public void UpdateDefaultSport(int sportid){
        String selectQuery = "UPDATE " + getDEFAULT_SPORT_TABLE()
                + " SET " + getDEFAULT_ID() + "='" + sportid + "' WHERE "
                + getKeyId() + "=1";
        Log.e(getLog(), selectQuery);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectQuery);
        db.close();
    }
}
