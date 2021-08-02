package com.example.celikpusaka.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.celikpusaka.user.QuizContract.*;
import com.example.celikpusaka.user.Question;

import java.util.ArrayList;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz6.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question("Faraid dari segi bahasa adalah:", "A. Sesuatu", "B. Ditentukan", "C. Sesuatu yang ditentukan ","D. Harta pusaka", 3);
        addQuestion(q1);
        Question q2 = new Question("\"Bahagian yang ditentukan oleh syarak untuk waris daripada peninggalan si mati\". Pernyataan tersebut adalah merujuk kepada:", "A. Ta'rif Faraid", "B. Maksud dari segi syarak ", "C. Entah, saya pun tak taulah","D. Maksud dari segi bahasa", 2);
        addQuestion(q2);
        Question q3 = new Question("Waris perlulah hidup jika ingin mewarisi harta pusaka.", "A. Pernyataan di atas betul  ", "B. Pernyataan di atas salah ", "C. Saya tidak pasti ","D. Pernyataan ini tidak wujud", 1);
        addQuestion(q3);
        Question q4 = new Question("Berikut adalah sahabat-sahabat yang masyhur di dalam ilmu faraid kecuali", "A. Saidina Ali bin Abu Talib", "B. Saidina Abdullah bin Abbas", "C. Saidina Zaid bin Thabit","D. Saidina Umar Al-Khattab", 4);
        addQuestion(q4);
        Question q5 = new Question("Faraid dapat dilaksanakan apabila ia mempunyai:", "A. Muwaris, waris dan maurus" , "B. Ijab dan qabul", "C. Al-aqidani, mauqud a'laihi","D. Mal, waris dan muwaris", 1);
        addQuestion(q5);

        Question q6 = new Question("Hareez mempunyai hubungan rapat dengan gurunya. Suatu hari, gurunya telah meninggal dunia disebabkan kemalangan. Hareez sangat sedih. Adakah Hareez mendapat harta pusaka daripada gurunya?", "A.\tHareez akan dapat harta pusaka gurunya", "B.\tHareez tidak dapat harta pusaka gurunya ", "C.\tMungkin dapat mungkin tidak","D.\tEntahlah, aku pun pening", 2);
        addQuestion(q6);
        Question q7 = new Question("Berapakah sebab-sebab mempusakai harta pusaka di dalam faraid?", "A. 2", "B. 3", "C. 4","D. 5", 3);
        addQuestion(q7);
        Question q8 = new Question("Apakah sebab-sebab mempusakai", "A.\tKerabat/nasab dan nikah", "B.\tNikah dan wala'", "C.\tKerabat/nasab, nikah dan wala'","D.\tKerabat/nasab, nikah, wala' dan baitul mal", 4);
        addQuestion(q8);
        Question q9 = new Question("Wala' ialah:", "A.\tOrang yang memerdekakan hamba ", "B.\tSuami atau isteri", "C.\tBapa hingga keatas, anak hingga kebawah dan adik-beradik","D.\tPerbendaharaan Harta", 1);
        addQuestion(q9);
        Question q10 = new Question("Adakah kita boleh mempusakai harta para nabi?\n" +
                "\n", "A.\tYa, boleh mempusakai" , "B.\tTidak boleh mempusakai  ", "C.\tBoleh dan tidak boleh bergantung kepada keadaan","D.\tBDapat separuh sahaja", 2);
        addQuestion(q10);

        Question q11 = new Question("Ahli waris lelaki ada berapa semuanya?", "A.\t10", "B.\t13", "C.\t15","D.\t14", 3);
        addQuestion(q11);
        Question q12 = new Question("Ahli waris perempuan ada berapa semuanya?", "A.\t10 ", "B.\t11", "C.\t15","D.\t14", 1);
        addQuestion(q12);
        Question q13 = new Question("Ahli waris yang berhak menerima harta warisan menurut pembahagian yang ditentukan oleh syara' ialah pengertian __________.\n" +
                "\n", "A.\tAhli Asobah", "B.\tAhli fardhu ", "C.\tAhli surau ","B.\tAhli masjid ", 2);
        addQuestion(q13);
        Question q14 = new Question("Ahli waris yang berhak menerima harta warisan dengan tidak ditentukan pembahagian syara' ialah pengertian __________.", "A.\tAhli Asobah ", "B.\tAhli fardhu", "C.\tAhli surau","D.\tAhli masjid", 1);
        addQuestion(q14);
        Question q15 = new Question("Daud mempunyai sebuah keluarga. Suatu hari, Daud telah buat keputusan untuk keluar daripada agama Islam dan bertukar namanya kepada David. Keesokan harinya dia meninggal dunia disebabkan sakit, adakah keluarganya mewarisi harta pusakanya?" +
                "\n", "A.\tKeluarganya mewarisi harta pusaka David" , "B.\tKeluarganya tidak dapat mewarisi harta pusakanya ", "C.\tSaya tidak tahu","D.\tSoalan ni buat saya peninglah", 2);
        addQuestion(q15);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);

    }

        public ArrayList<Question> getAllQuestions(){
            ArrayList<Question> questionList = new ArrayList<>();
            db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
            if (c.moveToFirst()) {
                do {
                    Question question = new Question();
                    question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                    question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                    question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                    question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                    question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                    question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                    questionList.add(question);
                } while (c.moveToNext());
            }
            c.close();
            return questionList;
        }
    }