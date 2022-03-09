package com.company;

import java.util.Random;

public class Main {
    public static String bossDefenceType;
    public static int bossDamage = 50;
    public static int bossHealth = 700;
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int[] heroesDamage = {25, 20, 15, 0};
    public static int[] heroesHealth = {320, 270, 250, 400};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        bossDefenceType = chooseDefence();
        bossHits();
        heroesHit();
        medicHelpHeroes();
        printStatistics();
    }

    public static void medicHelpHeroes() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 && heroesHealth[3] > 0){
                if (heroesHealth[i] == heroesHealth[3]){
                    continue;

                }else {
                    heroesHealth[i] += 30;
                    System.out.println("Medic hill " + heroesAttackType[i]);
                    break;
                }
            }

        }
    }


    public static String chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0, 1, 2
        System.out.println("Boss chose " + heroesAttackType[randomIndex]);
        return heroesAttackType[randomIndex];
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] + " x "
                            + coeff + " = " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/

        int totalHealth = 0;
        for (int health : heroesHealth) {
            totalHealth += health; // totalHealth = totalHealth + health;
        }
        if (totalHealth <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND _________________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
    }
}

