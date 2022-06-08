package com.example.examples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Script;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workingsOnTv;
    TextView resultTv;
    boolean leftBracket = true;
    String workings = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        workingsOnTv = findViewById(R.id.workingsTextView);
        resultTv = findViewById(R.id.resultTextView);
    }

    private void setWorkings(String givenValue) {
        workings = workings + givenValue;
        workingsOnTv.setText(workings);
    }

    public void clearBtn(View view) {
        workingsOnTv.setText("");
        resultTv.setText("");
        workings = "";

    }
    public void equalsOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();
        try {
            result = (Double) engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if (result != null) {
            resultTv.setText(String.valueOf(result.doubleValue()));
        }
    }
    private void checkForPowerOf() {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '^') {
                indexOfPowers.add(i);
            }
        }
        formula = workings;
        tempFormula = workings;
        for (Integer index : indexOfPowers) {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index) {
        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i < workings.length(); i++) {
            if (isNumeric(workings.charAt(i))) {
                numberRight = numberRight + workings.charAt(i);
            }else
                break;
        }
        for (int i = index - 1; i >= 0; i--) {
            if (isNumeric(workings.charAt(i))) {
                numberLeft = numberLeft + workings.charAt(i);
            } else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char c) {
        if ((c <= '9' && c >= '0') || c == '.') {
            return true;
        } else return false;
    }

    public void bracketsOnClick(View view) {
        if (leftBracket) {
            setWorkings("(");
            leftBracket = false;
        }else{
            setWorkings(")");
            leftBracket = true;
        }
    }
    public void powerOfOnClick(View view) {
        setWorkings("^");
    }
    public void divisionOnClick(View view) {
        setWorkings("/");
    }
    public void sevenOnClick(View view) {
        setWorkings("7");
    }
    public void zeroOnClick(View view) {
        setWorkings("0");
    }
    public void dotOnClick(View view) {
        setWorkings(".");
    }
    public void plusOnClick(View view) {
        setWorkings("+");
    }
    public void threeOnClick(View view) {
        setWorkings("3");
    }
    public void twoOnClick(View view) {
        setWorkings("2");
    }
    public void oneOnClick(View view) {
        setWorkings("1");
    }
    public void decimalOnClick(View view) {
        setWorkings("-");
    }
    public void sixOnClick(View view) {
        setWorkings("6");
    }
    public void fiveOnClick(View view) {
        setWorkings("5");
    }
    public void fourOnClick(View view) {
        setWorkings("4");
    }
    public void nineOnClick(View view) {
        setWorkings("9");
    }
    public void eightOnClick(View view) {
        setWorkings("8");
    }
    public void multiplyOnClick(View view) {
        setWorkings("*");
    }
}