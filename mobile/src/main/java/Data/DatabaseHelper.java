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
    protected static final String DEFAULT_SPORT_TABLE = "default_sport";

    // common column names
    protected static final String KEY_ID = "id";

    // Sports column names
    protected static final String SPORT_NAME = "sport_name";

    // Excuse column names
    protected static final String SPORT_ID = "sport_id";
    protected static final String EXCUSE_NAME = "excuse_name";

    // default sport column names
    protected static final String DEFAULT_ID = "default_id";

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

    public static String getDEFAULT_SPORT_TABLE() {
        return DEFAULT_SPORT_TABLE;
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

    public static String getDEFAULT_ID() {
        return DEFAULT_ID;
    }

    // create tables
    public static final String CREATE_TABLE_SPORTS = "CREATE TABLE IF NOT EXISTS "
            + SPORTS_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
            + SPORT_NAME + " TEXT " + ")";

    public static final String CREATE_TABLE_EXCUSES = "CREATE TABLE IF NOT EXISTS "
            + EXCUSES_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SPORT_ID + " INTEGER, " + EXCUSE_NAME + " TEXT "
            + ")";

    public static final String CREATE_TABLE_DEFAULT_SPORT = "CREATE TABLE IF NOT EXISTS "
            + DEFAULT_SPORT_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DEFAULT_ID + " INTEGER " + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create the tables
        db.execSQL(CREATE_TABLE_SPORTS);
        db.execSQL(CREATE_TABLE_EXCUSES);
        db.execSQL(CREATE_TABLE_DEFAULT_SPORT);

        // fill the tables
        fillSports(db);
        fillExcuses(db);
        fillDefault(db);
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
        values.put(SPORT_ID, excuse.getSportId());
        values.put(EXCUSE_NAME, excuse.getExcuseName());

        db.insert(EXCUSES_TABLE, null, values);
    }

    public void createDefault(DefaultModel d, SQLiteDatabase db){

        ContentValues values = new ContentValues();
        values.put(DEFAULT_ID, d.getDefaultID());

        db.insert(DEFAULT_SPORT_TABLE, null, values);
    }

    private void fillDefault(SQLiteDatabase db) {

        DefaultModel d = new DefaultModel(1);

        createDefault(d, db);
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
        ExcuseModel ex1 = new ExcuseModel(1, "I Suck");
        ExcuseModel ex2 = new ExcuseModel(1, "Couldn't Clip In");
        ExcuseModel ex3 = new ExcuseModel(1, "Fork's pressure was too low");
        ExcuseModel ex4 = new ExcuseModel(1, "I'm riding rigid");
        ExcuseModel ex5 = new ExcuseModel(1, "I didn't Pre-Ride");
        ExcuseModel ex6 = new ExcuseModel(1, "My car is a manual, and I had to work the clutch");
        ExcuseModel ex7 = new ExcuseModel(1, "I didn't have tubeless");
        ExcuseModel ex8 = new ExcuseModel(1,"I over-trained");
        ExcuseModel ex9 = new ExcuseModel(1, "I under-trained");
        ExcuseModel ex10 = new ExcuseModel(1, "Saddle was 1 cm too high");

        // Running excuses
        ExcuseModel ex11 = new ExcuseModel(2, "test1");
        ExcuseModel ex12 = new ExcuseModel(2, "test1");
        ExcuseModel ex13 = new ExcuseModel(2, "test1");
        ExcuseModel ex14 = new ExcuseModel(2, "test1");
        ExcuseModel ex15 = new ExcuseModel(2, "test1");
        ExcuseModel ex16 = new ExcuseModel(2, "test1");
        ExcuseModel ex17 = new ExcuseModel(2, "test1");
        ExcuseModel ex18 = new ExcuseModel(2, "test1");
        ExcuseModel ex19 = new ExcuseModel(2, "test1");
        ExcuseModel ex20 = new ExcuseModel(2, "test1");

        // XC Skiing Excuses
        ExcuseModel ex21 = new ExcuseModel(3, "test1");
        ExcuseModel ex22 = new ExcuseModel(3, "test1");
        ExcuseModel ex23 = new ExcuseModel(3, "test1");
        ExcuseModel ex24 = new ExcuseModel(3, "test1");
        ExcuseModel ex25 = new ExcuseModel(3, "test1");
        ExcuseModel ex26 = new ExcuseModel(3, "test1");
        ExcuseModel ex27 = new ExcuseModel(3, "test1");
        ExcuseModel ex28 = new ExcuseModel(3, "test1");
        ExcuseModel ex29 = new ExcuseModel(3, "test1");
        ExcuseModel ex30 = new ExcuseModel(3, "test1");

        // Diving excuses
        ExcuseModel ex41 = new ExcuseModel(4, "test1");
        ExcuseModel ex42 = new ExcuseModel(4, "test1");
        ExcuseModel ex43 = new ExcuseModel(4, "test1");
        ExcuseModel ex44 = new ExcuseModel(4, "test1");
        ExcuseModel ex45 = new ExcuseModel(4, "test1");
        ExcuseModel ex46 = new ExcuseModel(4, "test1");
        ExcuseModel ex47 = new ExcuseModel(4, "test1");
        ExcuseModel ex48 = new ExcuseModel(4, "test1");
        ExcuseModel ex49 = new ExcuseModel(4, "test1");
        ExcuseModel ex50 = new ExcuseModel(4, "test1");

        // Swimming Excuses
        ExcuseModel ex51 = new ExcuseModel(5, "test1");
        ExcuseModel ex52 = new ExcuseModel(5, "test1");
        ExcuseModel ex53 = new ExcuseModel(5, "test1");
        ExcuseModel ex54 = new ExcuseModel(5, "test1");
        ExcuseModel ex55 = new ExcuseModel(5, "test1");
        ExcuseModel ex56 = new ExcuseModel(5, "test1");
        ExcuseModel ex57 = new ExcuseModel(5, "test1");
        ExcuseModel ex58 = new ExcuseModel(5, "test1");
        ExcuseModel ex59 = new ExcuseModel(5, "test1");
        ExcuseModel ex60 = new ExcuseModel(5, "test1");

        // Triathlon
        ExcuseModel ex61 = new ExcuseModel(6, "test1");
        ExcuseModel ex62 = new ExcuseModel(6, "test1");
        ExcuseModel ex63 = new ExcuseModel(6, "test1");
        ExcuseModel ex64 = new ExcuseModel(6, "test1");
        ExcuseModel ex65 = new ExcuseModel(6, "test1");
        ExcuseModel ex66 = new ExcuseModel(6, "test1");
        ExcuseModel ex67 = new ExcuseModel(6, "test1");
        ExcuseModel ex68 = new ExcuseModel(6, "test1");
        ExcuseModel ex69 = new ExcuseModel(6, "test1");
        ExcuseModel ex70 = new ExcuseModel(6, "test1");

        // AM Football excuses
        ExcuseModel ex71 = new ExcuseModel(7, "test1");
        ExcuseModel ex72 = new ExcuseModel(7, "test1");
        ExcuseModel ex73 = new ExcuseModel(7, "test1");
        ExcuseModel ex74 = new ExcuseModel(7, "test1");
        ExcuseModel ex75 = new ExcuseModel(7, "test1");
        ExcuseModel ex76 = new ExcuseModel(7, "test1");
        ExcuseModel ex77 = new ExcuseModel(7, "test1");
        ExcuseModel ex78 = new ExcuseModel(7, "test1");
        ExcuseModel ex79 = new ExcuseModel(7, "test1");
        ExcuseModel ex80 = new ExcuseModel(7, "test1");

        // Baseball excuses
        ExcuseModel ex81 = new ExcuseModel(8, "test1");
        ExcuseModel ex82 = new ExcuseModel(8, "test1");
        ExcuseModel ex83 = new ExcuseModel(8, "test1");
        ExcuseModel ex84 = new ExcuseModel(8, "test1");
        ExcuseModel ex85 = new ExcuseModel(8, "test1");
        ExcuseModel ex86 = new ExcuseModel(8, "test1");
        ExcuseModel ex87 = new ExcuseModel(8, "test1");
        ExcuseModel ex88 = new ExcuseModel(8, "test1");
        ExcuseModel ex89 = new ExcuseModel(8, "test1");
        ExcuseModel ex90 = new ExcuseModel(8, "test1");

        // Football excuses
        ExcuseModel ex91 = new ExcuseModel(9, "test1");
        ExcuseModel ex92 = new ExcuseModel(9, "test1");
        ExcuseModel ex93 = new ExcuseModel(9, "test1");
        ExcuseModel ex94 = new ExcuseModel(9, "test1");
        ExcuseModel ex95 = new ExcuseModel(9, "test1");
        ExcuseModel ex96 = new ExcuseModel(9, "test1");
        ExcuseModel ex97 = new ExcuseModel(9, "test1");
        ExcuseModel ex98 = new ExcuseModel(9, "test1");
        ExcuseModel ex99 = new ExcuseModel(9, "test1");
        ExcuseModel ex100 = new ExcuseModel(9, "test1");

        // Golf excuses
        ExcuseModel ex101 = new ExcuseModel(10, "test1");
        ExcuseModel ex102 = new ExcuseModel(10, "test1");
        ExcuseModel ex103 = new ExcuseModel(10, "test1");
        ExcuseModel ex104 = new ExcuseModel(10, "test1");
        ExcuseModel ex105 = new ExcuseModel(10, "test1");
        ExcuseModel ex106 = new ExcuseModel(10, "test1");
        ExcuseModel ex107 = new ExcuseModel(10, "test1");
        ExcuseModel ex108 = new ExcuseModel(10, "test1");
        ExcuseModel ex109 = new ExcuseModel(10, "test1");
        ExcuseModel ex110 = new ExcuseModel(10, "test1");

        createExcuses(ex1, db);
        createExcuses(ex2, db);
        createExcuses(ex3, db);
        createExcuses(ex4, db);
        createExcuses(ex5, db);
        createExcuses(ex6, db);
        createExcuses(ex7, db);
        createExcuses(ex8, db);
        createExcuses(ex9, db);
        createExcuses(ex10, db);

        createExcuses(ex11, db);
        createExcuses(ex12, db);
        createExcuses(ex13, db);
        createExcuses(ex14, db);
        createExcuses(ex15, db);
        createExcuses(ex16, db);
        createExcuses(ex17, db);
        createExcuses(ex18, db);
        createExcuses(ex19, db);
        createExcuses(ex20, db);

        createExcuses(ex21, db);
        createExcuses(ex22, db);
        createExcuses(ex23, db);
        createExcuses(ex24, db);
        createExcuses(ex25, db);
        createExcuses(ex26, db);
        createExcuses(ex27, db);
        createExcuses(ex28, db);
        createExcuses(ex29, db);
        createExcuses(ex30, db);

        createExcuses(ex41, db);
        createExcuses(ex42, db);
        createExcuses(ex43, db);
        createExcuses(ex44, db);
        createExcuses(ex45, db);
        createExcuses(ex46, db);
        createExcuses(ex47, db);
        createExcuses(ex48, db);
        createExcuses(ex49, db);
        createExcuses(ex50, db);

        createExcuses(ex51, db);
        createExcuses(ex52, db);
        createExcuses(ex53, db);
        createExcuses(ex54, db);
        createExcuses(ex55, db);
        createExcuses(ex56, db);
        createExcuses(ex57, db);
        createExcuses(ex58, db);
        createExcuses(ex59, db);
        createExcuses(ex60, db);

        createExcuses(ex61, db);
        createExcuses(ex62, db);
        createExcuses(ex63, db);
        createExcuses(ex64, db);
        createExcuses(ex65, db);
        createExcuses(ex66, db);
        createExcuses(ex67, db);
        createExcuses(ex68, db);
        createExcuses(ex69, db);
        createExcuses(ex70, db);

        createExcuses(ex71, db);
        createExcuses(ex72, db);
        createExcuses(ex73, db);
        createExcuses(ex74, db);
        createExcuses(ex75, db);
        createExcuses(ex76, db);
        createExcuses(ex77, db);
        createExcuses(ex78, db);
        createExcuses(ex79, db);
        createExcuses(ex80, db);

        createExcuses(ex81, db);
        createExcuses(ex82, db);
        createExcuses(ex83, db);
        createExcuses(ex84, db);
        createExcuses(ex85, db);
        createExcuses(ex86, db);
        createExcuses(ex87, db);
        createExcuses(ex88, db);
        createExcuses(ex89, db);
        createExcuses(ex90, db);

        createExcuses(ex91, db);
        createExcuses(ex92, db);
        createExcuses(ex93, db);
        createExcuses(ex94, db);
        createExcuses(ex95, db);
        createExcuses(ex96, db);
        createExcuses(ex97, db);
        createExcuses(ex98, db);
        createExcuses(ex99, db);
        createExcuses(ex100, db);

        createExcuses(ex101, db);
        createExcuses(ex102, db);
        createExcuses(ex103, db);
        createExcuses(ex104, db);
        createExcuses(ex105, db);
        createExcuses(ex106, db);
        createExcuses(ex107, db);
        createExcuses(ex108, db);
        createExcuses(ex109, db);
        createExcuses(ex110, db);
    }
}
