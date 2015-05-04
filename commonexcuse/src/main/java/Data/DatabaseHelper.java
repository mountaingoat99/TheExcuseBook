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
        SportsModel sport11 = new SportsModel(11, "Ultimate Frisbee");
        SportsModel sport12 = new SportsModel(12, "Basketball");

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
        createSports(sport11, db);
        createSports(sport12, db);
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
        ExcuseModel ex130 = new ExcuseModel(1, "Wrong tires for conditions");
        ExcuseModel ex131 = new ExcuseModel(1, "Couldn't shift into my granny");
        ExcuseModel ex132 = new ExcuseModel(1, "Rode too hard yesterday");
        ExcuseModel ex133 = new ExcuseModel(1, "Someone bumped me at the start");
        ExcuseModel ex134 = new ExcuseModel(1, "I was too overgeared");
        ExcuseModel ex135 = new ExcuseModel(1, "My CO2 was empty");
        ExcuseModel ex136 = new ExcuseModel(1, "'Insert Name Here' was in my way");
        ExcuseModel ex137 = new ExcuseModel(1, "Was stuck in lapped traffic");
        ExcuseModel ex138 = new ExcuseModel(1, "Aunt Flow is visiting");
        ExcuseModel ex139 = new ExcuseModel(1, "Forgot my shoes");
        ExcuseModel ex140 = new ExcuseModel(1, "Lens were too dark/too light");
        ExcuseModel ex141 = new ExcuseModel(1, "Chain suck");
        ExcuseModel ex142 = new ExcuseModel(1, "Brakes were rubbing");
        ExcuseModel ex143 = new ExcuseModel(1, "Lost my gels");
        ExcuseModel ex144 = new ExcuseModel(1, "Missed my feed");
        ExcuseModel ex145 = new ExcuseModel(1, "Ran out of water");

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
        ExcuseModel ex45 = new ExcuseModel(4, "I have water in my ears");
        ExcuseModel ex46 = new ExcuseModel(4, "Too much chlorine in the pool");
        ExcuseModel ex47 = new ExcuseModel(4, "The water was too cold");
        ExcuseModel ex48 = new ExcuseModel(4, "That was not my best dive");
        ExcuseModel ex49 = new ExcuseModel(4, "The board was too old");
        ExcuseModel ex50 = new ExcuseModel(4, "The pool didn't seem deep enough");

        // Swimming Excuses
        ExcuseModel ex51 = new ExcuseModel(5, "I broke my taper");
        ExcuseModel ex52 = new ExcuseModel(5, "Drank too much Gatorade");
        ExcuseModel ex53 = new ExcuseModel(5, "I didn't eat enough");
        ExcuseModel ex54 = new ExcuseModel(5, "The pool was too hot");
        ExcuseModel ex55 = new ExcuseModel(5, "I have a paddle related injury");
        ExcuseModel ex56 = new ExcuseModel(5, "My goggles are broken");
        ExcuseModel ex57 = new ExcuseModel(5, "I have water in my ears");
        ExcuseModel ex58 = new ExcuseModel(5, "I have foot/ankle/knee/calf/forehead cramps");
        ExcuseModel ex59 = new ExcuseModel(5, "My suit is falling apart");
        ExcuseModel ex60 = new ExcuseModel(5, "Too much chlorine in the pool");
        ExcuseModel ex147 = new ExcuseModel(5, "The water was too cold");
        ExcuseModel ex148 = new ExcuseModel(5, "I didn't hear the start horn");
        ExcuseModel ex149 = new ExcuseModel(5, "I hate that stroke");
        ExcuseModel ex150 = new ExcuseModel(5, "I slipped on the block");
        ExcuseModel ex151 = new ExcuseModel(5, "My feet slipped on the turn");
        ExcuseModel ex152 = new ExcuseModel(5, "I forgot my swimsuit");

        // Triathlon
        ExcuseModel ex61 = new ExcuseModel(6, "I over-trained");
        ExcuseModel ex62 = new ExcuseModel(6, "I under-trained");
        ExcuseModel ex63 = new ExcuseModel(6, "Saddle was 1 cm too high");
        ExcuseModel ex64 = new ExcuseModel(6, "I didn't have my carbon wheels");
        ExcuseModel ex65 = new ExcuseModel(6, "These weren't the right shoes");
        ExcuseModel ex66 = new ExcuseModel(6, "I screwed up my taper");
        ExcuseModel ex67 = new ExcuseModel(6, "Water was too cold");
        ExcuseModel ex68 = new ExcuseModel(6, "Water was too hot");
        ExcuseModel ex69 = new ExcuseModel(6, "Too much headwind");
        ExcuseModel ex70 = new ExcuseModel(6, "Too many crosswinds");
        ExcuseModel ex153 = new ExcuseModel(6, "Someone bumped me in the swim");
        ExcuseModel ex154 = new ExcuseModel(6, "I was too overgeared");
        ExcuseModel ex155 = new ExcuseModel(6, "Brakes were rubbing");
        ExcuseModel ex156 = new ExcuseModel(6, "Lost my gels");
        ExcuseModel ex157 = new ExcuseModel(6, "Missed my feed");
        ExcuseModel ex158 = new ExcuseModel(6, "Ran out of water");
        ExcuseModel ex159 = new ExcuseModel(6, "My transitions sucked");
        ExcuseModel ex160 = new ExcuseModel(6, "I was just running it as a training run");
        ExcuseModel ex161 = new ExcuseModel(6, "My compression shorts were on backwards");
        ExcuseModel ex162 = new ExcuseModel(6, "No one took the pace out quick enough");
        ExcuseModel ex163 = new ExcuseModel(6, "I'm saving it for 'Insert Next Race Here'");
        ExcuseModel ex164 = new ExcuseModel(6, "I sprinted the first mile so that I could be in the photo");
        ExcuseModel ex165 = new ExcuseModel(6, "My transitions sucked");
        ExcuseModel ex166 = new ExcuseModel(6, "Didn't swim well, water felt too wet");
        ExcuseModel ex167 = new ExcuseModel(6, "Didn't draft on the bike so my run sucked");
        ExcuseModel ex168 = new ExcuseModel(6, "It was raining");
        ExcuseModel ex169 = new ExcuseModel(6, "Can't go hard, I have a big brick workout this week");
        ExcuseModel ex170 = new ExcuseModel(6, "I would be faster, but I have a life");
        ExcuseModel ex171 = new ExcuseModel(6, "I could PR that but it was too windy");
        ExcuseModel ex172 = new ExcuseModel(6, "They must have been doping");
        ExcuseModel ex173 = new ExcuseModel(6, "My wetsuit was too tight");

        // AM Football excuses
        ExcuseModel ex71 = new ExcuseModel(7, "I was hungover");
        ExcuseModel ex72 = new ExcuseModel(7, "The sun was in my eyes");
        ExcuseModel ex73 = new ExcuseModel(7, "The refs lost us the game");
        ExcuseModel ex74 = new ExcuseModel(7, "Too many injuries");
        ExcuseModel ex75 = new ExcuseModel(7, "Our division is too tough");
        ExcuseModel ex76 = new ExcuseModel(7, "My teammates really suck");
        ExcuseModel ex77 = new ExcuseModel(7, "We had our backup QB playing");
        ExcuseModel ex78 = new ExcuseModel(7, "Couldn't recover from yesterday's workout");
        ExcuseModel ex79 = new ExcuseModel(7, "We never play well after a bye week");
        ExcuseModel ex80 = new ExcuseModel(7, "If only out kicker made that 50-yarder");

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
        ExcuseModel ex98 = new ExcuseModel(9, "Couldn't get the volume of noise from our fans that we got in the last game");
        ExcuseModel ex99 = new ExcuseModel(9, "Our style means we need a bigger space to play because we play a positional game");
        ExcuseModel ex100 = new ExcuseModel(9, "The balls were too bouncy");
        ExcuseModel ex146 = new ExcuseModel(9, "We played very well in the first half, but maybe it was too cold in the second half");

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

        // Ultimate Frisbee
        ExcuseModel ex174 = new ExcuseModel(11, "I lost it in the sun");
        ExcuseModel ex175 = new ExcuseModel(11, "I'm pretty sure I stopped rotation");
        ExcuseModel ex176 = new ExcuseModel(11, "The wind caught it");
        ExcuseModel ex177 = new ExcuseModel(11, "I suck");
        ExcuseModel ex178 = new ExcuseModel(11, "I tripped");
        ExcuseModel ex179 = new ExcuseModel(11, "The field's uneven");
        ExcuseModel ex180 = new ExcuseModel(11, "Pick!");
        ExcuseModel ex181 = new ExcuseModel(11, "It slipped");
        ExcuseModel ex182 = new ExcuseModel(11, "I just put a little too much IO on it");
        ExcuseModel ex183 = new ExcuseModel(11, "He pushed off");
        ExcuseModel ex184 = new ExcuseModel(11, "I got turned around");
        ExcuseModel ex185 = new ExcuseModel(11, "Disc was too floaty");
        ExcuseModel ex186 = new ExcuseModel(11, "I had the wrong grip");
        ExcuseModel ex187 = new ExcuseModel(11, "I guess it's too windy for a hammer");
        ExcuseModel ex188 = new ExcuseModel(11, "I misread it");
        ExcuseModel ex189 = new ExcuseModel(11, "I think this is a 200 gram disc");

        // Basketball excuses
        ExcuseModel ex190 = new ExcuseModel(12, "I got fouled");
        ExcuseModel ex191 = new ExcuseModel(12, "It felt great out of my hand");
        ExcuseModel ex192 = new ExcuseModel(12, "I thought you were gonna cut");
        ExcuseModel ex193 = new ExcuseModel(12, "These aren't my good shoes");
        ExcuseModel ex194 = new ExcuseModel(12, "This ball's too bouncy");
        ExcuseModel ex195 = new ExcuseModel(12, "This ball's too flat");
        ExcuseModel ex196 = new ExcuseModel(12, "I'm just getting warmed up");
        ExcuseModel ex197 = new ExcuseModel(12, "I haven't played in awhile");
        ExcuseModel ex198 = new ExcuseModel(12, "The floor's too slippery");
        ExcuseModel ex199 = new ExcuseModel(12, "I think that's a girl's ball");
        ExcuseModel ex200 = new ExcuseModel(12, "It slipped");
        ExcuseModel ex201 = new ExcuseModel(12, "I forgot to keep my elbows in");
        ExcuseModel ex202 = new ExcuseModel(12, "I usually make those");
        ExcuseModel ex203 = new ExcuseModel(12, "I think the floor's uneven");
        ExcuseModel ex204 = new ExcuseModel(12, "I thought he was going the other way");
        ExcuseModel ex205 = new ExcuseModel(12, "I thought I was closer to the hoop");
        ExcuseModel ex206 = new ExcuseModel(12, "The ball's too slippery");
        ExcuseModel ex207 = new ExcuseModel(12, "I don't think this ball is round");

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
        createExcuses(ex130, db);
        createExcuses(ex131, db);
        createExcuses(ex132, db);
        createExcuses(ex133, db);
        createExcuses(ex134, db);
        createExcuses(ex135, db);
        createExcuses(ex136, db);
        createExcuses(ex137, db);
        createExcuses(ex138, db);
        createExcuses(ex139, db);
        createExcuses(ex140, db);
        createExcuses(ex141, db);
        createExcuses(ex142, db);
        createExcuses(ex143, db);
        createExcuses(ex144, db);
        createExcuses(ex145, db);

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
        createExcuses(ex147, db);
        createExcuses(ex148, db);
        createExcuses(ex149, db);
        createExcuses(ex150, db);
        createExcuses(ex151, db);
        createExcuses(ex152, db);

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
        createExcuses(ex153, db);
        createExcuses(ex154, db);
        createExcuses(ex155, db);
        createExcuses(ex156, db);
        createExcuses(ex157, db);
        createExcuses(ex158, db);
        createExcuses(ex159, db);
        createExcuses(ex160, db);
        createExcuses(ex161, db);
        createExcuses(ex162, db);
        createExcuses(ex163, db);
        createExcuses(ex164, db);
        createExcuses(ex165, db);
        createExcuses(ex166, db);
        createExcuses(ex167, db);
        createExcuses(ex168, db);
        createExcuses(ex169, db);
        createExcuses(ex170, db);
        createExcuses(ex171, db);
        createExcuses(ex172, db);
        createExcuses(ex173, db);

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
        createExcuses(ex146, db);

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

        createExcuses(ex174, db);
        createExcuses(ex175, db);
        createExcuses(ex176, db);
        createExcuses(ex177, db);
        createExcuses(ex178, db);
        createExcuses(ex179, db);
        createExcuses(ex180, db);
        createExcuses(ex181, db);
        createExcuses(ex182, db);
        createExcuses(ex183, db);
        createExcuses(ex184, db);
        createExcuses(ex185, db);
        createExcuses(ex186, db);
        createExcuses(ex187, db);
        createExcuses(ex188, db);
        createExcuses(ex189, db);

        createExcuses(ex190, db);
        createExcuses(ex191, db);
        createExcuses(ex192, db);
        createExcuses(ex193, db);
        createExcuses(ex194, db);
        createExcuses(ex195, db);
        createExcuses(ex196, db);
        createExcuses(ex197, db);
        createExcuses(ex198, db);
        createExcuses(ex199, db);
        createExcuses(ex200, db);
        createExcuses(ex201, db);
        createExcuses(ex202, db);
        createExcuses(ex203, db);
        createExcuses(ex204, db);
        createExcuses(ex205, db);
        createExcuses(ex206, db);
        createExcuses(ex207, db);

    }
}
