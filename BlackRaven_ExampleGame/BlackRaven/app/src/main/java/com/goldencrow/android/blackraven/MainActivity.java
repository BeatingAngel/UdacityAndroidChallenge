package com.goldencrow.android.blackraven;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.goldencrow.android.blackraven.entities.Monster;
import com.goldencrow.android.blackraven.entities.MonsterAttack;
import com.goldencrow.android.blackraven.entities.enums.MonsterType;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Monster> mMonsters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMonsters = new LinkedList<>();

        // Add a mock-monster into my list of all monsters.
        // All monsters will be saved in a SQLiteDB in the future.
        mMonsters.add(new Monster(
                "Ghost Shadow Wolf",
                R.drawable.ic_grey_wolf_silhouette,
                200,
                new MonsterAttack[]{
                        new MonsterAttack("Bite", 10, 10),
                        new MonsterAttack("Shadow Bind", 30, 3),
                        new MonsterAttack("Shadow Dance", 50, 2),
                        new MonsterAttack("Claw Attack", 15, 6)
                },
                EnumSet.of(MonsterType.GHOST, MonsterType.SHADOW))
        );
    }

    /**
     * Starts a mock fight to test the fighting activity.
     *
     * @param view  which called the function to start the fight.
     */
    public void enter_mock_fight(View view) {

        Intent fightIntent = new Intent(this, FightActivity.class);

        // This part of code will be changed in the future into selecting a
        // random monster from the list.
        //
        // Send the encountered monster to the fight activity.
        Monster opponent = mMonsters.get(0);
        if (opponent != null) {
            fightIntent.putExtra("Opponent", mMonsters.get(0));
        }

        startActivity(fightIntent);
    }

    /**
     * Open the inventory.
     *
     * @param view  which wants to open the inventory.
     */
    public void open_inventory(View view) {

        Intent inventoryIntent = new Intent(this, InventoryActivity.class);

        startActivity(inventoryIntent);
    }
}
