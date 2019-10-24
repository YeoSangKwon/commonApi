/*
 * Create by SangKwon on 2019. 10. 15.
 */

package md.ysk5898.quiz;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import md.ysk5898.com.R;
import md.ysk5898.com.databinding.ActivityQuizBinding;


/**
 * 퀴즈풀기
 */

public class QuizActivity extends AppCompatActivity {

    ActivityQuizBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz);
        binding.setActivity(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                if (!"".equals(binding.edt1.getText().toString())) {
                    String result1 = 첫번째퀴즈(binding.edt1.getText().toString());
                    binding.txt1.setText(result1);
                    Log.e("11111", "" + result1);
                }
                break;
            case R.id.btn_2:
                두번째퀴즈();
                break;
            case R.id.btn_3:
                if (!"".equals(binding.edt3.getText().toString())) {
                    int result3 = 세번째퀴즈(Integer.parseInt(binding.edt3.getText().toString()));
                    binding.txt3.setText(result3 + " 번");
                }
                break;
            case R.id.btn_4:
                String result4 = 네번째퀴즈();
                binding.txt4.setText(result4);
                break;
            case R.id.btn_5:
                if (!"".equals(binding.edt5.getText().toString())) {
                    StringBuilder result5 = 다섯번째퀴즈(binding.edt5.getText().toString());
                    binding.txt5.setText(result5);
                }
                break;
            case R.id.btn_6:
                if (!"".equals(binding.edt6.getText().toString())) {
                    StringBuilder result6 = 여섯번째퀴즈(binding.edt6.getText().toString());
                    binding.txt6.setText(result6);
                }
                break;
        }
    }

    @SuppressWarnings("NonAsciiCharacters")
    private String 첫번째퀴즈(String encrypt) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> resultList = new ArrayList<>();

        // 스트링 -> 배열 변환
        int i = 0;
        do {
            arrayList.add(encrypt.substring(i, i + 1));
            i++;
            if ((i + 1) > encrypt.length()) break;
        } while (i < encrypt.length());


        resultList.add(arrayList.get(0));
        for (int j = 0; j < arrayList.size(); j++) {
            if (!resultList.contains(arrayList.get(j))) {
                resultList.add(arrayList.get(j));
            }
        }

        String temp = "";
        for (String s : resultList) {
            temp = temp + s;
        }
        return temp;
    }

    @SuppressWarnings("NonAsciiCharacters")
    private void 두번째퀴즈() {
        syncThread A = new syncThread("A");
        syncThread B = new syncThread("B");
        A.start();

        synchronized (A) {
            try {
                Log.e("1111", "A 완료대기");
                A.wait();
                Log.e("1111", "B 시작");
                B.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized (B) {
            try {
                Log.e("1111", "B 완료대기");
                B.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 369 박수 횟수 계산하기
     */
    @SuppressWarnings("NonAsciiCharacters")
    private int 세번째퀴즈(int number) {
        int count = 0;
        for (int i = 1; i <= number; i++) {
            String numToString = String.valueOf(i);
            for (int j = 0; j < numToString.length(); j++) {
                if (numToString.charAt(j) == '3'
                    || numToString.charAt(j) == '6'
                    || numToString.charAt(j) == '9') {
                    count++;
                }
            }
        }
        String a = "A";

        return count;
    }

    /**
     * 책펴서 높은수 구하기
     */
    @SuppressWarnings("NonAsciiCharacters")
    private String 네번째퀴즈() {
        Random random = new Random();
        return "pobi = " + randomValue(random) + " / crong = " + randomValue(random);
    }


    /**
     * 영어 반전
     * */
    @SuppressWarnings("NonAsciiCharacters")
    private StringBuilder 다섯번째퀴즈(String text) {
        char[] changeText = new char[text.length()];
        char[] resultText = new char[text.length()];
        text.getChars(0, text.length(), changeText, 0);

        for(int i = 0; i < changeText.length; i++){
            if((int)changeText[i] > 90){
                resultText[i] = (char) (219 - (int)changeText[i]);
            }else{
                resultText[i] = (char) (155 - (int)changeText[i]);
            }
        }

        StringBuilder result = new StringBuilder();
        for(int j =0; j < resultText.length; j++){
            result.append(resultText[j]);
        }

        Log.e("11111", "" + result);
        return result;
    }

    /**
     * 지폐 동전 나누기
     * */
    @SuppressWarnings("NonAsciiCharacters")
    private StringBuilder 여섯번째퀴즈(String toString) {
        int data = Integer.parseInt(toString);
        return selectMoney(data);
    }

    public StringBuilder selectMoney(int money){
        int[] div = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};
        int[] result = new int[div.length];

        for(int i = 0; i < div.length; i++){
            if(money / div[i] > 0){
                result[i] = money / div[i];
                money = money % div[i];
            }else{
                result[i] = 0;
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int std : result){
            builder.append(std);
        }
        Log.e("11111",""+builder);

        return builder;

    }

    public int randomValue(Random random) {
        int value = random.nextInt(401);
        if (value == 0 || value == 1 || value == 399 || value == 400) {
            randomValue(random);
        }

        int Right = (value % 2 == 0) ? value : value + 1;
        int Left = Right - 1;

        int resultRigth = getValue(String.valueOf(Right));
        int resultLeft = getValue(String.valueOf(Left));

        int result = (resultRigth > resultLeft) ? resultRigth : resultLeft;

        return result;
    }

    public int getValue(String value) {
        int resultValue;

        if (value.contains("0")) {
            resultValue = 0;
            for (int i = 0; i < value.length(); i++) {
                resultValue += value.charAt(i) - '0';
            }
        } else {
            resultValue = 1;
            int temp = 0;
            for (int i = 0; i < value.length(); i++) {
                resultValue *= value.charAt(i) - '0';
                temp += value.charAt(i) - '0';
            }
            resultValue = (resultValue > temp) ? resultValue : temp;
        }

        return resultValue;
    }

    public class syncThread extends Thread {
        private String log;

        public syncThread(String log) {
            this.log = log;
        }

        @Override
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 10; i++) {
                    try {
                        sleep(500);
                        Log.e("1111", log + " - " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                notify();
            }
        }
    }
}
