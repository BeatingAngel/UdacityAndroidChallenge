package com.goldencrow.android.blackraven;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goldencrow.android.blackraven.entities.InventoryItem;
import com.goldencrow.android.blackraven.entities.Monster;
import com.goldencrow.android.blackraven.entities.MonsterAttack;
import com.goldencrow.android.blackraven.entities.enums.MonsterType;
import com.goldencrow.android.blackraven.utilities.MonsterAnimationUtility;

import java.util.EnumSet;

public class FightActivity extends AppCompatActivity {

    // views from the opponent monster
    TextView mTvOpponentName;
    TextView mTvOpponentHealth;
    ImageView mIvOpponentImage;

    // views for myself
    TextView mTvSelfName;
    TextView mTvSelfHealth;
    ImageView mIvSelfImage;

    // buttons for attacks
    Button[] mBtnAttacks;

    Monster myself;
    Monster opponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        myself = new Monster(
                "Colorful Colibri",
                R.drawable.ic_colibri_single_color,
                100,
                new MonsterAttack[]{
                        new MonsterAttack("Beak Attack", 10, 10),
                        new MonsterAttack("Wind Blades", 30, 3),
                        new MonsterAttack("Dance of Heaven", 100, 1),
                        new MonsterAttack("Claw Attack", 15, 6)
                },
                EnumSet.of(MonsterType.COLORFUL, MonsterType.BIRD)
        );

        mTvOpponentName = findViewById(R.id.tv_opponent_name);
        mTvOpponentHealth = findViewById(R.id.tv_opponent_health);
        mIvOpponentImage = findViewById(R.id.iv_opponent_image);

        mTvSelfName = findViewById(R.id.tv_self_name);
        mTvSelfHealth = findViewById(R.id.tv_self_health);
        mIvSelfImage = findViewById(R.id.iv_self_image);

        Button btnAttack1 = findViewById(R.id.btn_action01);
        Button btnAttack2 = findViewById(R.id.btn_action02);
        Button btnAttack3 = findViewById(R.id.btn_action03);
        Button btnAttack4 = findViewById(R.id.btn_action04);

        btnAttack1.setTag(0);
        btnAttack2.setTag(1);
        btnAttack3.setTag(2);
        btnAttack4.setTag(3);

        mBtnAttacks = new Button[]{btnAttack1, btnAttack2, btnAttack3, btnAttack4};

        updateAttackButton(0);
        updateAttackButton(1);
        updateAttackButton(2);
        updateAttackButton(3);

        Intent intent = getIntent();
        opponent = intent.getParcelableExtra("Opponent");

        updateUI(myself, opponent);

        editMonster(myself, mIvSelfImage, mTvSelfName);
        editMonster(opponent, mIvOpponentImage, mTvOpponentName);
    }

    /**
     * The user inflicts damage onto the opponent monster.
     *
     * @param view  of the clicked item.
     */
    public void inflictDamage(View view) {
        final int ATTACK_INDEX = (int) view.getTag();
        MonsterAttack attack = myself.getAttacks()[ATTACK_INDEX];

        if (attack.getRemainingUsages() > 0) {
            final int DAMAGE_VALUE = attack.getDamageValue();

            TranslateAnimation anim = MonsterAnimationUtility.getSimpleAttackAnimation();
            anim.setAnimationListener(new Animation.AnimationListener(){
                @Override
                public void onAnimationStart(Animation arg0) {
                }
                @Override
                public void onAnimationRepeat(Animation arg0) {
                }

                // the end of the animation is the end of the attack.
                // and when the attack is finished, damage is taken.
                @Override
                public void onAnimationEnd(Animation arg0) {
                    opponent.takeDamage(DAMAGE_VALUE);
                    myself.decreaseAttackCounter(ATTACK_INDEX);

                    updateMonsterStatus(mTvOpponentHealth, opponent);
                    updateAttackButton(ATTACK_INDEX);

                    if (opponent.getCurrentHealth() == 0) {
                        writeMessage("That's a win for me!", true);
                    }

                    //toggle buttons so that the user has to wait for the opponent monster to finish
                    toggleAttackButtons();
                    // set the animation back to the flying animation
                    mIvSelfImage.setAnimation(MonsterAnimationUtility.getFlyingAnimation());
                    // let the opponent attack.
                    initiateOpponentAttack();
                }
            });
            mIvSelfImage.setAnimation(anim);
        }
        else {
            writeMessage("I don't have the strength for it", false);
        }
    }

    /**
     * let the opponent attack and afterwards give back control to the user.
     */
    public void initiateOpponentAttack() {
        MonsterAttack attack = null;
        boolean noAttackFound = true;

        // find a random attack where some usages are remaining.
        while (noAttackFound) {
            int minimum = 0;
            int maximum = 3;
            int index = minimum + (int) (Math.random() * maximum);
            attack = opponent.getAttacks()[index];

            if (attack.getRemainingUsages() > 0) {
                noAttackFound = false;
                opponent.decreaseAttackCounter(index);
            }
        }

        // get the attackDamage and inflict it after the attack-animation.
        final int damage = attack.getDamageValue();
        AlphaAnimation anim = MonsterAnimationUtility.getGhostAttackAnimation();
        anim.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            // the end of the animation is the end of the attack.
            // and when the attack is finished, damage is taken.
            @Override
            public void onAnimationEnd(Animation arg0) {
                myself.takeDamage(damage);

                updateMonsterStatus(mTvSelfHealth, myself);

                if (myself.getCurrentHealth() == 0) {
                    writeMessage("You LOSE!!", true);
                }
                toggleAttackButtons();
            }
        });
        mIvOpponentImage.setAnimation(anim);
    }

    /**
     * toggles the attackButtons from enable to disable and backwards.
     *
     * This is used when the opponent attacks, so that the user can't
     * attack.
     */
    public void toggleAttackButtons() {
        for (int i = 0; i < 4; i++) {
            Button btn = mBtnAttacks[i];
            btn.setEnabled(!btn.isEnabled());
        }
    }

    /**
     * Display all information about myself and the opponent monster.
     *
     * @param myself     the "myself" monster.
     * @param opponent   the opponent monster.
     */
    private void updateUI(Monster myself, Monster opponent) {
        mTvOpponentName.setText(opponent.getName());
        updateMonsterStatus(mTvOpponentHealth, opponent);
        mIvOpponentImage.setImageResource(opponent.getImage());

        mTvSelfName.setText(myself.getName());
        updateMonsterStatus(mTvSelfHealth, myself);
        mIvSelfImage.setImageResource(myself.getImage());
    }

    /**
     * updates the health status of the passed monster.
     *
     * This is needed when the monster was inflicted with damage.
     *
     * @param healthStatus  the TextView of the monsters health.
     * @param monster       the affected monster.
     */
    private void updateMonsterStatus(TextView healthStatus, Monster monster) {
        healthStatus.setText(
                getString(R.string.health_display,
                        monster.getCurrentHealth(),
                        monster.getMaxHealth()
                )
        );
    }

    /**
     * Update the text of the attack-button.
     *
     * This is needed when an attack was used and the remaining Usages of this
     * attack has changed. This needs to be displayed.
     *
     * @param pos   of the attack in the array.
     */
    private void updateAttackButton(int pos) {
        Button btn = mBtnAttacks[pos];
        MonsterAttack attack = myself.getAttacks()[pos];

        btn.setText(getString(R.string.attack_display,
                attack.getName(),
                attack.getRemainingUsages(),
                attack.getMaxUsages())
        );
    }

    /**
     * if the monster is of the category . . .
     *   . . ghost  -> 50% transparent
     *   . . shadow -> name in black shadows
     *   . . colorful -> name in colorful shadow
     */
    private void editMonster(Monster monster, ImageView image, TextView name) {
        // ghost are nearly invisible.
        if (monster.getTypes().contains(MonsterType.GHOST)) {
            image.startAnimation(MonsterAnimationUtility.getGhostAnimation());
        }
        // shadow monsters have a shadow?
        if (monster.getTypes().contains(MonsterType.SHADOW)) {
            name.setShadowLayer(10, 3, 3, Color.WHITE);
            name.setTextColor(Color.GRAY);
        }
        // colorful means colorful
        if (monster.getTypes().contains(MonsterType.COLORFUL)) {
            name.setShadowLayer(10, 2, 2, Color.YELLOW);
            name.setTextColor(Color.WHITE);
        }
        // Birds fly up and down, up and down, up and down,  . . .
        if (monster.getTypes().contains(MonsterType.BIRD)) {
            image.startAnimation(MonsterAnimationUtility.getFlyingAnimation());
        }
    }

    /**
     * Leave the fight (abort mission!!)
     *
     * @param view  which called it
     */
    public void leave_fight(View view) {
        writeMessage("I better fly away!", true);
    }

    /**
     * Open the inventory (requesting for help!!)
     *
     * @param view  which called it
     */
    public void open_inventory(View view) {
        Intent inventoryIntent = new Intent(this, InventoryActivity.class);
        inventoryIntent.putExtra("result", true);

        startActivityForResult(inventoryIntent, 4412);
    }

    /**
     * writes a message to the screen and if the fight ended -> close activity.
     *
     * This is the case when the user wins/loses or on attack is initiated.
     * In case of an attack, the name of it will be displayed.
     *
     * @param message   to be displayed.
     * @param isEnd     of the activity/fight.
     */
    private void writeMessage(String message, boolean isEnd) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 300);
        toast.show();
        if (isEnd) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 4412) {
            if (resultCode == RESULT_OK) {
                InventoryItem item = data.getParcelableExtra("item");
                if (item != null) {
                    if (item.getName().toLowerCase().equals("small health potion")) {
                        myself.heal(20);
                        updateMonsterStatus(mTvSelfHealth, myself);
                    } else if (item.getName().toLowerCase().equals("medium health potion")) {
                        myself.heal(50);
                        updateMonsterStatus(mTvSelfHealth, myself);
                    }
                    // ELSE: unrecognized item was clicked.
                }
                // ELSE: no item was selected from the inventory
            }
            // ELSE: other activity results here
        }
    }
}
