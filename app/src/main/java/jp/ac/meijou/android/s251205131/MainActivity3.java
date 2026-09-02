package jp.ac.meijou.android.s251205131;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205131.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int display; //表示されている数値
    private int operand1;
    private int operand2;
    private Operator operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.Button_0.setOnClickListener(view -> pushOperand(0));
        binding.Button_1.setOnClickListener(view -> pushOperand(1));
        binding.Button_2.setOnClickListener(view -> pushOperand(2));
        binding.Button_3.setOnClickListener(view -> pushOperand(3));
        binding.Button_4.setOnClickListener(view -> pushOperand(4));
        binding.Button_5.setOnClickListener(view -> pushOperand(5));
        binding.Button_6.setOnClickListener(view -> pushOperand(6));
        binding.Button_7.setOnClickListener(view -> pushOperand(7));
        binding.Button_8.setOnClickListener(view -> pushOperand(8));
        binding.Button_9.setOnClickListener(view -> pushOperand(9));
    }
}