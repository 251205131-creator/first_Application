package jp.ac.meijou.android.s251205131;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.function.BiFunction;

import jp.ac.meijou.android.s251205131.databinding.ActivityMain3Binding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;

    private int display; //表示されている数値
    private int operand1;//1つ目の入力値
    private int operand2;//2つ目の入力値
    private Operator operator;//現在入力されている演算子

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //intentを取得する
        Intent intent = getIntent();
        String sentText = intent.getStringExtra("editText");
        binding.text.setText(sentText);


        //Okボタンとcancelボタンの実装
        binding.buttonOk.setOnClickListener(view -> {
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK, intent);
            finish();
        });

        binding.buttonCancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });


        private void pushOperand(int num){
            if (operator == null) {
                operand1 = operand1 * 10 + num;
                display = operand1;
            } else {
                operand2 = operand2 * 10 + num;
                display = operand2;
            }
            binding.text.setText(String.valueOf(display));
        }

        //数値をリセットする
        private void clear() {
            operand1 = 0;
            operand2 = 0;
            display = 0;
            operator = null;
            binding.text.setText(String.valueOf(display));
        }
        //入力された2つの数値および演算子を用いて計算を行う

        private void calc() {
            if (operator == null) {
                return;
            }
            display = operator.calc.apply(operand1, operand2);
            binding.text.setText(String.valueOf(display));
        }

        private enum Operator {
            // 加算（operand1 + operand2）
            PLUS(Integer::sum),
            // 減算（operand1 − operand2）
            MINUS((a, b) -> a - b),
            // 乗算（operand1 × operand2）
            MULTIPLY((a, b) -> a * b),
            // 除算（operand1 ÷ operand2）※ゼロ除算は未ガード
            DIVIDE((a, b) -> a / b);
        }


        // この演算子に対応する計算処理。
        public final BiFunction<Integer, Integer, Integer> calc;

        Operator(BiFunction < Integer, Integer, Integer > calc) {
            this.calc = calc;
        }


        binding.Button0.setOnClickListener(view -> pushOperand(0));
        binding.Button1.setOnClickListener(view -> pushOperand(1));
        binding.Button2.setOnClickListener(view -> pushOperand(2));
        binding.Button3.setOnClickListener(view -> pushOperand(3));
        binding.Button4.setOnClickListener(view -> pushOperand(4));
        binding.Button5.setOnClickListener(view -> pushOperand(5));
        binding.Button6.setOnClickListener(view -> pushOperand(6));
        binding.Button7.setOnClickListener(view -> pushOperand(7));
        binding.Button8.setOnClickListener(view -> pushOperand(8));
        binding.Button9.setOnClickListener(view -> pushOperand(9));

        // deleteボタン：入力値・演算子をすべてリセットしてディスプレイを0に戻す
        binding.ButtonDelete.setOnClickListener(view -> clear());

        // 演算子ボタン：選択した演算子を保持する（＝ボタン押下時に使用）
        binding.ButtonPlus.setOnClickListener(view -> operator = Operator.PLUS);
        binding.ButtonMinus.setOnClickListener(view -> operator = Operator.MINUS);
        binding.ButtonMultiply.setOnClickListener(view -> operator = Operator.MULTIPLY);
        binding.ButtonDivide.setOnClickListener(view -> operator = Operator.DIVIDE);




    }
}