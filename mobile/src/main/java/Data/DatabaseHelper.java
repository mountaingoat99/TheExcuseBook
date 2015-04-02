package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //logcat tag
    protected static final String LOG = "DatabaseHelper";
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "EXCUSE_DB";

    // Table names
    protected static final String SPORTS_TABLE = "sports";
    protected static final String EXCUSES_TABLE = "excuses";

    // common column names
    protected static final String KEY_ID = "id";

    // Sports column names
    protected static final String SPORT_NAME = "sport_name";
    // Excuse column names
    protected static final String SPORT_ID = "sport_id";
    protected static final String EXCUSE_NAME = "excuse_name";

    public static String getLog() {
        return LOG;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    public static String getSports() {
        return SPORTS_TABLE;
    }

    public static String getExcuses() {
        return EXCUSES_TABLE;
    }

    public static String getKeyId() {
        return KEY_ID;
    }

    public static String getSportName() {
        return SPORT_NAME;
    }

    public static String getSportId() {
        return SPORT_ID;
    }

    public static String getExcuseName() {
        return EXCUSE_NAME;
    }

    // create tables
    public static final String CREATE_TABLE_SPORTS = "CREATE TABLE IF NOT EXISTS "
            + SPORTS_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
            + SPORT_NAME + " TEXT " + ")";

    public static final String CREATE_TABLE_EXCUSES = "CREATE TABLE IF NOT EXISTS "
            + EXCUSES_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SPORT_ID + " INTEGER, " + EXCUSE_NAME + " TEXT "
            + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the tables
        db.execSQL(CREATE_TABLE_SPORTS);
        db.execSQL(CREATE_TABLE_EXCUSES);

        // fill the tables
        fillSports(db);
        fillExcuses(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch (newVersion) {
            case 2:
                // new tables if needed go here
                break;
            default:
                throw new IllegalStateException(
                        "onUpgrade() with unknown new Version" + newVersion);
        }
    }

    public void createSports(SportsModel sport, SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(KEY_ID, sport.getSportId());
        values.put(SPORT_NAME, sport.getSportName());

        db.insert(SPORTS_TABLE, null, values);
    }

    public void createExcuses(ExcuseModel excuse, SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(KEY_ID, excuse.getSportId());
        values.put(SPORT_ID, excuse.getSportId());
        values.put(EXCUSE_NAME, excuse.getExcuseName());

        db.insert(EXCUSES_TABLE, null, values);
    }

    private void fillSports(SQLiteDatabase db){

        SportsModel sport1 = new SportsModel(1, "Cycling");
        SportsModel sport2 = new SportsModel(2, "Running");
        SportsModel sport3 = new SportsModel(3, "XC Skiing");
        SportsModel sport4 = new SportsModel(4, "Diving");
        SportsModel sport5 = new SportsModel(5, "Swimming");
        SportsModel sport6 = new SportsModel(6, "Triathlon");
        SportsModel sport7 = new SportsModel(7, "Football(American)");
        SportsModel sport8 = new SportsModel(8, "Baseball");
        SportsModel sport9 = new SportsModel(9, "Football(Soccer)");
        SportsModel sport10 = new SportsModel(10, "Golf");

        createSports(sport1, db);
        createSports(sport2, db);
        createSports(sport3, db);
        createSports(sport4, db);
        createSports(sport5, db);
        createSports(sport6, db);
        createSports(sport7, db);
        createSports(sport8, db);
        createSports(sport9, db);
        createSports(sport10, db);
    }

    private void fillExcuses(SQLiteDatabase db){
        // cycling excuses
        ExcuseModel ex1 = new ExcuseModel(1, "");
        ExcuseModel ex2 = new ExcuseModel(1, "");
        ExcuseModel ex3 = new ExcuseModel(1, "");
        ExcuseModel ex4 = new ExcuseModel(1, "");
        ExcuseModel ex5 = new ExcuseModel(1, "");
        ExcuseModel ex6 = new ExcuseModel(1, "");
        ExcuseModel ex7 = new ExcuseModel(1, "");
        ExcuseModel ex8 = new ExcuseModel(1, "");
        ExcuseModel ex9 = new ExcuseModel(1, "");
        ExcuseModel ex10 = new ExcuseModel(1, "");

        // Running excuses
        ExcuseModel ex11 = new ExcuseModel(2, "");
        ExcuseModel ex12 = new ExcuseModel(2, "");
        ExcuseModel ex13 = new ExcuseModel(2, "");
        ExcuseModel ex14 = new ExcuseModel(2, "");
        ExcuseModel ex15 = new ExcuseModel(2, "");
        ExcuseModel ex16 = new ExcuseModel(2, "");
        ExcuseModel ex17 = new ExcuseModel(2, "");
        ExcuseModel ex18 = new ExcuseModel(2, "");
        ExcuseModel ex19 = new ExcuseModel(2, "");
        ExcuseModel ex20 = new ExcuseModel(2, "");

        // XC Skiing Excuses
        ExcuseModel ex21 = new ExcuseModel(3, "");
        ExcuseModel ex22 = new ExcuseModel(3, "");
        ExcuseModel ex23 = new ExcuseModel(3, "");
        ExcuseModel ex24 = new ExcuseModel(3, "");
        ExcuseModel ex25 = new ExcuseModel(3, "");
        ExcuseModel ex26 = new ExcuseModel(3, "");
        ExcuseModel ex27 = new ExcuseModel(3, "");
        ExcuseModel ex28 = new ExcuseModel(3, "");
        ExcuseModel ex29 = new ExcuseModel(3, "");
        ExcuseModel ex30 = new ExcuseModel(3, "");

        // Diving excuses
        ExcuseModel ex41 = new ExcuseModel(4, "");
        ExcuseModel ex42 = new ExcuseModel(4, "");
        ExcuseModel ex43 = new ExcuseModel(4, "");
        ExcuseModel ex44 = new ExcuseModel(4, "");
        ExcuseModel ex45 = new ExcuseModel(4, "");
        ExcuseModel ex46 = new ExcuseModel(4, "");
        ExcuseModel ex47 = new ExcuseModel(4, "");
        ExcuseModel ex48 = new ExcuseModel(4, "");
        ExcuseModel ex49 = new ExcuseModel(4, "");
        ExcuseModel ex50 = new ExcuseModel(4, "");

        // Swimming Excuses
        ExcuseModel ex51 = new ExcuseModel(5, "");
        ExcuseModel ex52 = new ExcuseModel(5, "");
        ExcuseModel ex53 = new ExcuseModel(5, "");
        ExcuseModel ex54 = new ExcuseModel(5, "");
        ExcuseModel ex55 = new ExcuseModel(5, "");
        ExcuseModel ex56 = new ExcuseModel(5, "");
        ExcuseModel ex57 = new ExcuseModel(5, "");
        ExcuseModel ex58 = new ExcuseModel(5, "");
        ExcuseModel ex59 = new ExcuseModel(5, "");
        ExcuseModel ex60 = new ExcuseModel(5, "");

        // Triathlon
        ExcuseModel ex61 = new ExcuseModel(6, "");
        ExcuseModel ex62 = new ExcuseModel(6, "");
        ExcuseModel ex63 = new ExcuseModel(6, "");
        ExcuseModel ex64 = new ExcuseModel(6, "");
        ExcuseModel ex65 = new ExcuseModel(6, "");
        ExcuseModel ex66 = new ExcuseModel(6, "");
        ExcuseModel ex67 = new ExcuseModel(6, "");
        ExcuseModel ex68 = new ExcuseModel(6, "");
        ExcuseModel ex69 = new ExcuseModel(6, "");
        ExcuseModel ex70 = new ExcuseModel(6, "");

        // AM Football excuses
        ExcuseModel ex71 = new ExcuseModel(7, "");
        ExcuseModel ex72 = new ExcuseModel(7, "");
        ExcuseModel ex73 = new ExcuseModel(7, "");
        ExcuseModel ex74 = new ExcuseModel(7, "");
        ExcuseModel ex75 = new ExcuseModel(7, "");
        ExcuseModel ex76 = new ExcuseModel(7, "");
        ExcuseModel ex77 = new ExcuseModel(7, "");
        ExcuseModel ex78 = new ExcuseModel(7, "");
        ExcuseModel ex79 = new ExcuseModel(7, "");
        ExcuseModel ex80 = new ExcuseModel(7, "");

        // Baseball excuses
        ExcuseModel ex81 = new ExcuseModel(8, "");
        ExcuseModel ex82 = new ExcuseModel(8, "");
        ExcuseModel ex83 = new ExcuseModel(8, "");
        ExcuseModel ex84 = new ExcuseModel(8, "");
        ExcuseModel ex85 = new ExcuseModel(8, "");
        ExcuseModel ex86 = new ExcuseModel(8, "");
        ExcuseModel ex87 = new ExcuseModel(8, "");
        ExcuseModel ex88 = new ExcuseModel(8, "");
        ExcuseModel ex89 = new ExcuseModel(8, "");
        ExcuseModel ex90 = new ExcuseModel(8, "");

        // Football excuses
        ExcuseModel ex91 = new ExcuseModel(9, "");
        ExcuseModel ex92 = new ExcuseModel(9, "");
        ExcuseModel ex93 = new ExcuseModel(9, "");
        ExcuseModel ex94 = new ExcuseModel(9, "");
        ExcuseModel ex95 = new ExcuseModel(9, "");
        ExcuseModel ex96 = new ExcuseModel(9, "");
        ExcuseModel ex97 = new ExcuseModel(9, "");
        ExcuseModel ex98 = new ExcuseModel(9, "");
        ExcuseModel ex99 = new ExcuseModel(9, "");
        ExcuseModel ex100 = new ExcuseModel(9, "");

        // Golf excuses
        ExcuseModel ex101 = new ExcuseModel(10, "");
        ExcuseModel ex102 = new ExcuseModel(10, "");
        ExcuseModel ex103 = new ExcuseModel(10, "");
        ExcuseModel ex104 = new ExcuseModel(10, "");
        ExcuseModel ex105 = new ExcuseModel(10, "");
        ExcuseModel ex106 = new ExcuseModel(10, "");
        ExcuseModel ex107 = new ExcuseModel(10, "");
        ExcuseModel ex108 = new ExcuseModel(10, "");
        ExcuseModel ex109 = new ExcuseModel(10, "");
        ExcuseModel ex110 = new ExcuseModel(10, "");

    }
}
