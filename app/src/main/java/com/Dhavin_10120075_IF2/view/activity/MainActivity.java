package com.Dhavin_10120075_IF2.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.Dhavin_10120075_IF2.R;
import com.Dhavin_10120075_IF2.view.fragment.NoteFragment;
import com.Dhavin_10120075_IF2.view.fragment.ProfileFragment;
import com.Dhavin_10120075_IF2.view.fragment.InfoFragment;
import com.google.firebase.auth.FirebaseAuth;

/**
 * NAMA    : Dhavin Ilham Zulfah
 * NIM     : 10120075
 * Kelas   : IF-2
 * MatKul  : Aplikasi Komputasi Bergerak
 * Pengganti UTS AKB
 */
public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        //menampilkan halaman yang pertama muncul
        getFragmentPage(new InfoFragment());

        //insialisasi bottom nav
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case
                        R.id.info:
                        fragment = new InfoFragment();
                        break;
                    case
                        R.id.note:
                        fragment = new NoteFragment();
                        break;
                    case
                        R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                    case
                        R.id.logout:
                        signOut();
                        break;
                }
                return getFragmentPage(fragment);
            }
        });
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
        redirectToLogin(); // Redirect to the login page after logout
    }

    private void redirectToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.page_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

/**
 * NAMA    : Dhavin Ilham Zulfah
 * NIM     : 10120075
 * Kelas   : IF-2
 * MatKul  : Aplikasi Komputasi Bergerak
 * Pengganti UTS AKB
 */