package com.example.codcamotracker;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mwiiiAssaultRifles;
    private ArrayList<String> mwiiiBattleRifles;
    private ArrayList<String> mwiiiShotguns;
    private ArrayList<String> mwiiiSMGs;
    private ArrayList<String> mwiiiLMGs;
    private ArrayList<String> mwiiiMarksmanRifles;
    private ArrayList<String> mwiiiSniperRifles;
    private ArrayList<String> mwiiiHandguns;
    private ArrayList<String> mwiiiLaunchers;
    private ArrayList<String> mwiiiMelee;
    private ArrayList<String> mwiiAssaultRifles;
    private ArrayList<String> mwiiBattleRifles;
    private ArrayList<String> mwiiShotguns;
    private ArrayList<String> mwiiSMGs;
    private ArrayList<String> mwiiLMGs;
    private ArrayList<String> mwiiMarksmanRifles;
    private ArrayList<String> mwiiSniperRifles;
    private ArrayList<String> mwiiHandguns;
    private ArrayList<String> mwiiLaunchers;
    private ArrayList<String> mwiiMelee;
    private static HashMap<String, ComponentState> itemStates = new HashMap<>();

    public static class GunListAdapter extends ArrayAdapter<String> {

        public GunListAdapter(Context context, List<String> guns) {
            super(context, 0, guns);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }

            String gun = getItem(position);
            TextView gunNameTextView = convertView.findViewById(R.id.gunNameTextView);
            ProgressBar progressBar = convertView.findViewById(R.id.progressBar);

            CheckBox checkBox1 = convertView.findViewById(R.id.checkBox1);
            CheckBox checkBox2 = convertView.findViewById(R.id.checkBox2);
            CheckBox checkBox3 = convertView.findViewById(R.id.checkBox3);
            CheckBox checkBox4 = convertView.findViewById(R.id.checkBox4);

            gunNameTextView.setText(gun);
            // Set the progress of the progress bar

            final CheckBox[] checkBoxes = {checkBox1, checkBox2, checkBox3, checkBox4};

            // Retrieve the saved state for this item
            ComponentState state = MainActivity.itemStates.get(gun);
            if (state != null) {
                boolean[] checkBoxStates = state.getCheckBoxStates();
                int progressBarValue = state.getProgressBarValue();

                for (int i = 0; i < checkBoxStates.length; i++) {
                    checkBoxes[i].setChecked(checkBoxStates[i]);
                }
                progressBar.setProgress(progressBarValue);
            }

            for (CheckBox checkBox : checkBoxes) {
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    int totalChecked = 0;
                    for (CheckBox box : checkBoxes) {
                        if (box.isChecked()) totalChecked++;
                    }

                    switch (totalChecked) {
                        case 0:
                            progressBar.setProgress(0);
                            break;
                        case 1:
                            progressBar.setProgress(25);
                            break;
                        case 2:
                            progressBar.setProgress(50);
                            break;
                        case 3:
                            progressBar.setProgress(75);
                            break;
                        case 4:
                            progressBar.setProgress(100);
                            break;
                    }

                    // Update the state in the map
                    boolean[] updatedCheckBoxStates = new boolean[4];
                    for (int i = 0; i < checkBoxes.length; i++) {
                        updatedCheckBoxStates[i] = checkBoxes[i].isChecked();
                    }
                    ComponentState updatedState = new ComponentState(updatedCheckBoxStates, progressBar.getProgress());
                    MainActivity.itemStates.put(gun, updatedState);
                });
            }

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load component states when the app starts
        loadComponentStates();

        Spinner classes = findViewById(R.id.sp_classes);
        EditText MWIII = findViewById(R.id.text_mwiii);
        Chip mwiiiChip = findViewById(R.id.MWIIIChip);
        ListView mwiii = findViewById(R.id.mwiii);
        EditText MWII = findViewById(R.id.text_mwii);
        Chip mwiiChip = findViewById(R.id.MWIIChip);
        ListView mwii = findViewById(R.id.mwii);
//        Chip resetChip = findViewById(R.id.resetChip);

        // MWIII Weapons
        mwiiiAssaultRifles = new ArrayList<>();
        mwiiiAssaultRifles.add("FR 5.56");
        mwiiiAssaultRifles.add("SVA 545");
        mwiiiAssaultRifles.add("RAM-7");
        mwiiiAssaultRifles.add("DG-56");
        mwiiiAssaultRifles.add("BP50");
        mwiiiAssaultRifles.add("MCW");
        mwiiiAssaultRifles.add("MTZ-556");
        mwiiiAssaultRifles.add("Holger 556");
        mwiiiAssaultRifles.add("BAL-27");
        // Add more MWIII Assault Rifles as needed

        mwiiiBattleRifles = new ArrayList<>();
        mwiiiBattleRifles.add("SOA Subverter");
        mwiiiBattleRifles.add("BAS-B");
        mwiiiBattleRifles.add("Sidewinder");
        mwiiiBattleRifles.add("MTZ-762");
        // Add more MWIII Battle Rifles as needed

        mwiiiSMGs = new ArrayList<>();
        mwiiiSMGs.add("RAM-9");
        mwiiiSMGs.add("HRM-9");
        mwiiiSMGs.add("Striker 9");
        mwiiiSMGs.add("Striker");
        mwiiiSMGs.add("AMR9");
        mwiiiSMGs.add("Rival-9");
        mwiiiSMGs.add("WSP-9");
        mwiiiSMGs.add("WSP Swarm");
        // Add more MWIII SMGs as needed

        mwiiiLMGs = new ArrayList<>();
        mwiiiLMGs.add("Bruen MK9");
        mwiiiLMGs.add("TAQ Eradicator");
        mwiiiLMGs.add("DG-58 LSW");
        mwiiiLMGs.add("TAQ Evolvere");
        mwiiiLMGs.add("Pulemyot 762");
        mwiiiLMGs.add("Holger 26");
        // Add more MWIII LMGs as needed

        mwiiiShotguns = new ArrayList<>();
        mwiiiShotguns.add("Riveter");
        mwiiiShotguns.add("Haymaker");
        mwiiiShotguns.add("Lockwood 680");
        // Add more MWIII Shotguns as needed

        mwiiiMarksmanRifles = new ArrayList<>();
        mwiiiMarksmanRifles.add("MCW 6.8");
        mwiiiMarksmanRifles.add("MTZ Interceptor");
        mwiiiMarksmanRifles.add("KVD Enforcer");
        mwiiiMarksmanRifles.add("DM56");
        // Add more MWIII Marksman Rifles as needed

        mwiiiSniperRifles = new ArrayList<>();
        mwiiiSniperRifles.add("Mors");
        mwiiiSniperRifles.add("Longbow");
        mwiiiSniperRifles.add("XRK Stalker");
        mwiiiSniperRifles.add("KV Inhibitor");
        mwiiiSniperRifles.add("KATT-AMR");
        // Add more MWIII Sniper Rifles as needed

        mwiiiHandguns = new ArrayList<>();
        mwiiiHandguns.add("TYR");
        mwiiiHandguns.add("COR-45");
        mwiiiHandguns.add("Renetti");
        mwiiiHandguns.add("WSP Stinger");
        // Add more MWIII Handguns as needed

        mwiiiLaunchers = new ArrayList<>();
        mwiiiLaunchers.add("RGL-80");
        mwiiiLaunchers.add("Stormender");
        // Add more MWIII Launchers as needed

        mwiiiMelee = new ArrayList<>();
        mwiiiMelee.add("Gladiator");
        mwiiiMelee.add("Soulrender");
        mwiiiMelee.add("Karambit");
        mwiiiMelee.add("Gutter Knife");
        // Add more MWIII Melee weapons as needed

        // MWII Weapons
        mwiiAssaultRifles = new ArrayList<>();
        mwiiAssaultRifles.add("Chimera");
        mwiiAssaultRifles.add("Lachmann-556");
        mwiiAssaultRifles.add("STB 556");
        mwiiAssaultRifles.add("M4");
        mwiiAssaultRifles.add("M16");
        mwiiAssaultRifles.add("Kastov 762");
        mwiiAssaultRifles.add("Kastov-74U");
        mwiiAssaultRifles.add("Kastov 545");
        mwiiAssaultRifles.add("M13B");
        mwiiAssaultRifles.add("TAQ-56");
        mwiiAssaultRifles.add("ISO Hemlock");
        mwiiAssaultRifles.add("Tempus Razorback");
        mwiiAssaultRifles.add("M13C");
        mwiiAssaultRifles.add("FR Avancer");
        mwiiAssaultRifles.add("TR-76 Geist");
        // Add more MWII Assault Rifles as needed

        mwiiBattleRifles = new ArrayList<>();
        mwiiBattleRifles.add("TAQ-V");
        mwiiBattleRifles.add("Lachmann-762");
        mwiiBattleRifles.add("SQ-14");
        mwiiBattleRifles.add("FTAC Recon");
        mwiiBattleRifles.add("Cronen Squall");
        // Add more MWII Battle Rifles as needed

        mwiiSMGs = new ArrayList<>();
        mwiiSMGs.add("Lachmann Sub");
        mwiiSMGs.add("BAS-P");
        mwiiSMGs.add("MX9");
        mwiiSMGs.add("Vaznev-9K");
        mwiiSMGs.add("FSS Hurricane");
        mwiiSMGs.add("Minibak");
        mwiiSMGs.add("PDSW 528");
        mwiiSMGs.add("VEL 46");
        mwiiSMGs.add("Fennec 45");
        mwiiSMGs.add("ISO 45");
        mwiiSMGs.add("Lachmann Shroud");
        mwiiSMGs.add("ISO 9mm");
        // Add more MWII SMGs as needed

        mwiiLMGs = new ArrayList<>();
        mwiiLMGs.add("Raal MG");
        mwiiLMGs.add("HCR 56");
        mwiiLMGs.add("556 Icarus");
        mwiiLMGs.add("RPK");
        mwiiLMGs.add("Rapp H");
        mwiiLMGs.add("Sakin MG38");
        // Add more MWII LMGs as needed

        mwiiShotguns = new ArrayList<>();
        mwiiShotguns.add("Lockwood 300");
        mwiiShotguns.add("Bryson 800");
        mwiiShotguns.add("Bryson 890");
        mwiiShotguns.add("Expedite 12");
        mwiiShotguns.add("KV Broadside");
        mwiiShotguns.add("MX Guardian");
        // Add more MWII Shotguns as needed

        mwiiMarksmanRifles = new ArrayList<>();
        mwiiMarksmanRifles.add("LM-S");
        mwiiMarksmanRifles.add("SP-R 208");
        mwiiMarksmanRifles.add("EBR-14");
        mwiiMarksmanRifles.add("SA-B 50");
        mwiiMarksmanRifles.add("Lockwood MK2");
        mwiiMarksmanRifles.add("TAQ-M");
        mwiiMarksmanRifles.add("Crossbow");
        mwiiMarksmanRifles.add("Tempus Torrent");
        // Add more MWII Marksman Rifles as needed

        mwiiSniperRifles = new ArrayList<>();
        mwiiSniperRifles.add("LA-B 330");
        mwiiSniperRifles.add("SP-X 80");
        mwiiSniperRifles.add("MCPR-300");
        mwiiSniperRifles.add("Victus XMR");
        mwiiSniperRifles.add("Signal 50");
        mwiiSniperRifles.add("FJX Imperium");
        mwiiSniperRifles.add("Carrack .300");
        // Add more MWII Sniper Rifles as needed

        mwiiHandguns = new ArrayList<>();
        mwiiHandguns.add("X12");
        mwiiHandguns.add("X13 Auto");
        mwiiHandguns.add(".50 GS");
        mwiiHandguns.add("P890");
        mwiiHandguns.add("Basilisk");
        mwiiHandguns.add("FTAC Siege");
        mwiiHandguns.add("GS Magna");
        mwiiHandguns.add("9mm Daemon");
        // Add more MWII Handguns as needed

        mwiiLaunchers = new ArrayList<>();
        mwiiLaunchers.add("RPG-7");
        mwiiLaunchers.add("PILA");
        mwiiLaunchers.add("JOKR");
        mwiiLaunchers.add("STRELA-P");
        // Add more MWII Launchers as needed

        mwiiMelee = new ArrayList<>();
        mwiiMelee.add("Riot Shield");
        mwiiMelee.add("Combat Knife");
        mwiiMelee.add("Dual Kodachis");
        mwiiMelee.add("Tonfa");
        mwiiMelee.add("Pickaxe");
        mwiiMelee.add("Dual Kamas");
        // Add more MWII Melee weapons as needed

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"Assault Rifles", "Battle Rifles", "SMGs", "LMGs", "Shotguns", "Marksman Rifles", "Sniper Rifles", "Handguns", "Launchers", "Melee"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classes.setAdapter(spinnerAdapter);

        final ArrayAdapter[] ListViewAdapter = new ArrayAdapter[]{new GunListAdapter(MainActivity.this, mwiiiAssaultRifles)};
        mwiii.setAdapter(ListViewAdapter[0]);

        mwiiiChip.setOnClickListener(v -> {
            // Updates the ListView when mwiiiChip is clicked
            mwiii.setVisibility(View.VISIBLE);
            MWIII.setVisibility(View.VISIBLE);
            mwii.setVisibility(View.INVISIBLE);
            MWII.setVisibility(View.INVISIBLE);

        });

        mwiiChip.setOnClickListener(v -> {
            // Updates the ListView when mwiiChip is clicked
            mwii.setVisibility(View.VISIBLE);
            MWII.setVisibility(View.VISIBLE);
            mwiii.setVisibility(View.INVISIBLE);
            MWIII.setVisibility(View.INVISIBLE);
        });

//        resetChip.setOnClickListener( v -> {
//            // Resets the progress bar when Reset is clicked
//
//        });

        classes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = classes.getSelectedItem().toString();
                if (selectedCategory.equals("Assault Rifles")) {
                    GunListAdapter adapter = new GunListAdapter(MainActivity.this, mwiiiAssaultRifles);
                    mwiii.setAdapter(adapter);
                }
                if (selectedCategory.equals("Assault Rifles")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiAssaultRifles);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Battle Rifles")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiBattleRifles);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Battle Rifles")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiBattleRifles);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("SMGs")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiSMGs);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("SMGs")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiSMGs);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Shotguns")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiShotguns);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Shotguns")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiShotguns);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("LMGs")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiLMGs);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("LMGs")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiLMGs);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Marksman Rifles")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiMarksmanRifles);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Marksman Rifles")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiMarksmanRifles);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Sniper Rifles")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiSniperRifles);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Sniper Rifles")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiSniperRifles);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Handguns")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiHandguns);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Handguns")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiHandguns);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Launchers")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiLaunchers);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Launchers")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiLaunchers);
                    mwii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Melee")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiiMelee);
                    mwiii.setAdapter(listViewAdapter);
                }
                if (selectedCategory.equals("Melee")) {
                    GunListAdapter listViewAdapter = new GunListAdapter(MainActivity.this, mwiiMelee);
                    mwii.setAdapter(listViewAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save component states when the app is about to close
        saveComponentStates();
    }

    private void saveComponentStates() {
        try (FileOutputStream fos = new FileOutputStream("componentStates.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(itemStates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadComponentStates() {
        try (FileInputStream fis = new FileInputStream("componentStates.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            itemStates = (HashMap<String, ComponentState>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
