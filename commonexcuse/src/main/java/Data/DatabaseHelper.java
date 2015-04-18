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
            + SPORTS_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
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

    public void createNewSport(SportsModel sport, SQLiteDatabase db){

        ContentValues values = new ContentValues();
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

        SportsModel sport = new SportsModel(0, "Choose a Default Sport");
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

        createSports(sport, db);
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
        ExcuseModel ex116 = new ExcuseModel(1, "I didn't feel good during my warm up");
        ExcuseModel ex117 = new ExcuseModel(1, "I didn't warm up");
        ExcuseModel ex118 = new ExcuseModel(1, "I didn't have my carbon wheels");
        ExcuseModel ex119 = new ExcuseModel(1, "I'm tapering");
        ExcuseModel ex120 = new ExcuseModel(1, "The guys I ride with are tools");
        ExcuseModel ex121 = new ExcuseModel(1, "The group I was with wasn't very strong");
        ExcuseModel ex122 = new ExcuseModel(1, "Too much headwind");
        ExcuseModel ex123 = new ExcuseModel(1, "Too many crosswinds");
        ExcuseModel ex128 = new ExcuseModel(1, "I was hungover");

        // Running excuses
        ExcuseModel ex11 = new ExcuseModel(2, "Too much leg chaffing today");
        ExcuseModel ex12 = new ExcuseModel(2, "I did too many miles this week");
        ExcuseModel ex13 = new ExcuseModel(2, "I didn't do enough miles this week");
        ExcuseModel ex14 = new ExcuseModel(2, "I'm tapering");
        ExcuseModel ex15 = new ExcuseModel(2, "It was too windy");
        ExcuseModel ex16 = new ExcuseModel(2, "Not enough sleep");
        ExcuseModel ex17 = new ExcuseModel(2, "Ate way too many carbs");
        ExcuseModel ex18 = new ExcuseModel(2, "Didn't eat enough carbs");
        ExcuseModel ex19 = new ExcuseModel(2, "Had to go to the bathroom");
        ExcuseModel ex20 = new ExcuseModel(2, "I was hungover");
        ExcuseModel ex124 = new ExcuseModel(2, "It wasn't my good distance");
        ExcuseModel ex125 = new ExcuseModel(2, "Forgot my chews");
        ExcuseModel ex126 = new ExcuseModel(2, "I had a blister");
        ExcuseModel ex127 = new ExcuseModel(2, "I pulled a muscle");

        // XC Skiing Excuses
        ExcuseModel ex21 = new ExcuseModel(3, "I over trained");
        ExcuseModel ex22 = new ExcuseModel(3, "I under trained");
        ExcuseModel ex23 = new ExcuseModel(3, "Picked the wrong wax");
        ExcuseModel ex24 = new ExcuseModel(3, "Picked the wrong skis");
        ExcuseModel ex25 = new ExcuseModel(3, "Not my type of snow");
        ExcuseModel ex26 = new ExcuseModel(3, "Just got a really poor grind");
        ExcuseModel ex27 = new ExcuseModel(3, "I was hungover");
        ExcuseModel ex28 = new ExcuseModel(3, "I never liked this course anyway");
        ExcuseModel ex29 = new ExcuseModel(3, "I never really liked these skis");
        ExcuseModel ex30 = new ExcuseModel(3, "Snow was too soft");
        ExcuseModel ex129 = new ExcuseModel(3, "It wasn't very good grooming");

        // Diving excuses
        ExcuseModel ex41 = new ExcuseModel(4, "I lost my spot");
        ExcuseModel ex42 = new ExcuseModel(4, "I slipped");
        ExcuseModel ex43 = new ExcuseModel(4, "The board had no bounce");
        ExcuseModel ex44 = new ExcuseModel(4, "The board had too much bounce");
        ExcuseModel ex45 = new ExcuseModel(4, "test1");
        ExcuseModel ex46 = new ExcuseModel(4, "test1");
        ExcuseModel ex47 = new ExcuseModel(4, "test1");
        ExcuseModel ex48 = new ExcuseModel(4, "test1");
        ExcuseModel ex49 = new ExcuseModel(4, "test1");
        ExcuseModel ex50 = new ExcuseModel(4, "test1");

        // Swimming Excuses
        ExcuseModel ex51 = new ExcuseModel(5, "I broke my taper");
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
        ExcuseModel ex71 = new ExcuseModel(7, "I was hungover");
        ExcuseModel ex72 = new ExcuseModel(7, "The sun was in my eyes");
        ExcuseModel ex73 = new ExcuseModel(7, "The refs lost us the game");
        ExcuseModel ex74 = new ExcuseModel(7, "Too many injuries");
        ExcuseModel ex75 = new ExcuseModel(7, "Our division is too tough");
        ExcuseModel ex76 = new ExcuseModel(7, "My teammates really suck");
        ExcuseModel ex77 = new ExcuseModel(7, "We had our backup QB playing");
        ExcuseModel ex78 = new ExcuseModel(7, "test1");
        ExcuseModel ex79 = new ExcuseModel(7, "test1");
        ExcuseModel ex80 = new ExcuseModel(7, "test1");

        // Baseball excuses
        ExcuseModel ex81 = new ExcuseModel(8, "Sun was in my eyes");
        ExcuseModel ex82 = new ExcuseModel(8, "I just worked out");
        ExcuseModel ex83 = new ExcuseModel(8, "Wasn't paying attention");
        ExcuseModel ex84 = new ExcuseModel(8, "Didn't think this was a serious game");
        ExcuseModel ex85 = new ExcuseModel(8, "I've always done it like that");
        ExcuseModel ex86 = new ExcuseModel(8, "Other team must be doping");
        ExcuseModel ex87 = new ExcuseModel(8, "We got a bad call");
        ExcuseModel ex88 = new ExcuseModel(8, "Too many injuries");
        ExcuseModel ex89 = new ExcuseModel(8, "My teammates really suck");
        ExcuseModel ex90 = new ExcuseModel(8, "Our division is too tough");

        // Football excuses
        ExcuseModel ex91 = new ExcuseModel(9, "Other team must be doping");
        ExcuseModel ex92 = new ExcuseModel(9, "Sun was in my eyes");
        ExcuseModel ex93 = new ExcuseModel(9, "I wasn't paying attention");
        ExcuseModel ex94 = new ExcuseModel(9, "We had some bad calls");
        ExcuseModel ex95 = new ExcuseModel(9, "We have too many injuries");
        ExcuseModel ex96 = new ExcuseModel(9, "My teammates really suck");
        ExcuseModel ex97 = new ExcuseModel(9, "We had the backup goalie playing");
        ExcuseModel ex98 = new ExcuseModel(9, "test1");
        ExcuseModel ex99 = new ExcuseModel(9, "test1");
        ExcuseModel ex100 = new ExcuseModel(9, "test1");

        // Golf excuses
        ExcuseModel ex101 = new ExcuseModel(10, "Didn't warm up");
        ExcuseModel ex102 = new ExcuseModel(10, "It's too windy");
        ExcuseModel ex103 = new ExcuseModel(10, "Someone talked in my backswing");
        ExcuseModel ex104 = new ExcuseModel(10, "Ball has less dimples than I usually play");
        ExcuseModel ex105 = new ExcuseModel(10, "The sun was in my eyes");
        ExcuseModel ex106 = new ExcuseModel(10, "I already played 18 before this, so I'm drunk");
        ExcuseModel ex107 = new ExcuseModel(10, "Trying something I saw on YouTube to fix my slice");
        ExcuseModel ex108 = new ExcuseModel(10, "That was a practice swing");
        ExcuseModel ex109 = new ExcuseModel(10, "I'm not drunk enough yet");
        ExcuseModel ex110 = new ExcuseModel(10, "I hate this kind of grass");
        ExcuseModel ex111 = new ExcuseModel(10, "Shot would have been perfect if it not for the tree");
        ExcuseModel ex112 = new ExcuseModel(10, "I meant to do that!");
        ExcuseModel ex113 = new ExcuseModel(10, "I am a better player than these clubs allow me to be");
        ExcuseModel ex114 = new ExcuseModel(10, "I'm better than this I swear");
        ExcuseModel ex115 = new ExcuseModel(10, "I was in between clubs for that distance");

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
        createExcuses(ex116, db);
        createExcuses(ex117, db);
        createExcuses(ex118, db);
        createExcuses(ex119, db);
        createExcuses(ex120, db);
        createExcuses(ex121, db);
        createExcuses(ex122, db);
        createExcuses(ex123, db);
        createExcuses(ex128, db);

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
        createExcuses(ex124, db);
        createExcuses(ex125, db);
        createExcuses(ex126, db);
        createExcuses(ex127, db);

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
        createExcuses(ex129, db);

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
        createExcuses(ex111, db);
        createExcuses(ex112, db);
        createExcuses(ex113, db);
        createExcuses(ex114, db);
        createExcuses(ex115, db);
    }
}
