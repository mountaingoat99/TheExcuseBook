package Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DataInterface extends DatabaseHelper {

    public DataInterface(Context context) {
        super(context);
    }

    // select List of Sports
    public List<String> SportList() {
        List<String> sport = new ArrayList<>();

        String selectQuery = "SELECT " + getSportName() + " FROM " + getSports();
        Log.e(getLog(), selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do{
                SportsModel s = new SportsModel();
                sport.add(s.setSportName(c.getString(c.getColumnIndex(getSportName()))));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return sport;
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

    // may not use this
    // select Dictionary of Excuses by Sport
    public Hashtable<Integer,String> ExcuseListBySport(int sportid){
        Hashtable<Integer, String> excuse = new Hashtable<>();

        String selectQuery = "SELECT " + getKeyId() + ", " + getExcuseName()
                + " FROM " + getExcuses()
                + " WHERE " + getSportId() + "=" + sportid;
        Log.e(getLog(), selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()) {              // TODO this needs to loop through each excuse
            do{
                excuse.put(c.getInt(0), c.getString(1));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return excuse;
    }

    // Insert new Excuse record by sport
    public void AddExcuse(String excuseName, int sportId){
        SQLiteDatabase db = this.getWritableDatabase();

        ExcuseModel excuse = new ExcuseModel(sportId, excuseName);
        createExcuses(excuse, db);
        db.close();
    }

    // Update Excuse record
    public void EditExcuse(int id, String name){
        String selectQuery = "UPDATE " + getExcuses()
                + " SET " + getExcuseName() + "='" + name + "' WHERE "
                + getKeyId() + "=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectQuery);
        db.close();
    }

}
