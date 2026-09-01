package jp.ac.meijou.android.s251205131;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205131.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
        });

        prefDataStore = PrefDataStore.getInstance(this);
        prefDataStore.getString("name")
                .ifPresent(name -> {
                    /*
                    var modText = "(pref)" + name;
                    Log.d("meijo", modText);
                    binding.textView.setText(modText);
                     */ //デバック用のコード
                    binding.textView.setText(name);
                });

        binding.save.setOnClickListener(view -> {
            String text = binding.editTextText.getText().toString();
            if ("a" .equals(text)){
                //binding.textView.setText("Aの画像");
                binding.vectorAsset.setImageResource(R.drawable.asset123);
            } else if ("b" .equals((text))) {
                //binding.textView.setText("Bの画像");
                binding.vectorAsset.setImageResource(R.drawable.ic_launcher_background);
            } else {
                //binding.textView.setText("知らない画像");
                binding.vectorAsset.setImageResource(R.drawable.ic_launcher_foreground);
            }
            prefDataStore.setString("name", text);
        });

        // TextView text_view = findViewById(R.id.text_view);
        // text_view.setText(R.string.text);
        // binding.textView.setText(R.string.text);
        // binding.vectorAsset.setImageResource(R.drawable.asset123);

        binding.change.setOnClickListener(view -> {
            String text = binding.editTextText.getText().toString();
            binding.textView.setText(text);
        });

        binding.reset.setOnClickListener(view -> {
            String text = "Name";
            binding.textView.setText("Guten Morgen!");
            binding.editTextText.setText(text);
            prefDataStore.setString("name", text);
            binding.vectorAsset.setImageResource(R.drawable.vector_asset);
        });

        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
               /* String text = s.toString();
                binding.textView.setText(text); */
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

    }
}