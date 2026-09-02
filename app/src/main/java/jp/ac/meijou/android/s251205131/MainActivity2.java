package jp.ac.meijou.android.s251205131;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s251205131.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s251205131.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        // 2画面目が RESULT_OK を返した場合：Intentから "ret" キーの文字列を取得して表示
                        // Optional を使い、データが null の場合も安全に処理する
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.resultText.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        // 2画面目が RESULT_CANCELED を返した場合（Cancelボタン押下 or 戻るボタン）
                        binding.resultText.setText("Result: Canceled");
                    }
                    default -> {
                        // 上記以外の未知の結果コードが返ってきた場合
                        binding.resultText.setText("Result: Unknown(" + result.getResultCode() + ")");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //明示的Intentが押された時
        binding.buttonA.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
        });

        //暗黙的Intentが押された時
        binding.buttonB.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse( "https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        //Intentボタンが押された時
        binding.sentButton.setOnClickListener(view -> {
            String sentText = binding.intentEditText.getText().toString();

            var intent = new Intent(this, MainActivity3.class);
            intent.putExtra("editText", sentText);
            startActivity(intent);
        });

        binding.resultButton.setOnClickListener(view -> {
            var intent = new Intent(this, MainActivity3.class);
            getActivityResult.launch(intent);
        });
    }
}