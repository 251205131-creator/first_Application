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
    private int operand1;
    private int operand2;
    private Operator operator;

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

        // AC（All Clear）ボタン：入力値・演算子をすべてリセットしてディスプレイを0に戻す
        binding.ButtonDelete.setOnClickListener(view -> clear());

        // 演算子ボタン：選択した演算子を保持する（＝ボタン押下時に使用）
        binding.ButtonPlus.setOnClickListener(view -> operator = Operator.PLUS);
        binding.ButtonMinus.setOnClickListener(view -> operator = Operator.MINUS);
        binding.ButtonMultiply.setOnClickListener(view -> operator = Operator.MULTIPLY);
        binding.ButtonDivide.setOnClickListener(view -> operator = Operator.DIVIDE);

        /**
         * オペランドの末尾にnumを追加する。
         * <p>
         * 演算子が未選択の場合は {@code operand1}、選択済みの場合は {@code operand2} に
         * 数字を追記し、TextView に表示を更新します。
         * </p>
         *
         * @param num 追加する数字（0〜9）
         */
        private void pushOperand(int num) {
            if (operator == null) {
                operand1 = operand1 * 10 + num;
                display = operand1;
            } else {
                operand2 = operand2 * 10 + num;
                display = operand2;
            }
            binding.text.setText(String.valueOf(display));
        }

        /**
         * 電卓の演算子を表す列挙型。
         * <p>
         * 各定数は対応する計算処理（{@link BiFunction}）を保持しており、
         * {@link #calc} フィールドを通じて実際の計算に利用されます。
         * </p>
         */
        private enum Operator {
            /** 加算（operand1 + operand2） */
            PLUS(Integer::sum),
            /** 減算（operand1 − operand2） */
            MINUS((a, b) -> a - b),
            /** 乗算（operand1 × operand2） */
            MULTIPLY((a, b) -> a * b),
            /** 除算（operand1 ÷ operand2）※ゼロ除算は未ガード */
            DIVIDE((a, b) -> a / b);
    }
}