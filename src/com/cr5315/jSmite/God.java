package com.cr5315.jSmite;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public class God {
    private Ability ability1;
    private Ability ability2;
    private Ability ability3;
    private Ability ability4;
    private Ability ability5;

    private double attackSpeed;
    private double attackSpeedPerLevel;

    private String cons;

    private double hp5PerLevel;
    private double health;
    private double healthPerFive;
    private double healthPerLevel;

    private int id;

    private String lore;

    private double mp5PerLevel;
    private double magicProtection;
    private double magicProtectionPerLevel;

    private double mana;
    private double manaPerFive;
    private double manaPerLevel;

    private String name;

    private String onFreeRotation;

    private String pantheon;
    private double physicalPower;
    private double physicalPowerPerLevel;
    private double physicalProtection;
    private double physicalProtectionPerLevel;
    private String pros;

    private String roles;

    private double speed;

    private String title;
    private String type;


    public God(JsonObject jsonObject) {
        for (int i = 1; i <= 5; i++) {
            JsonObject abilityJson = jsonObject.get("abilityDescription" + i).asObject().get("itemDescription").asObject();

            JsonArray menuItemsJson = abilityJson.get("menuitems").asArray();
            MenuItem[] menuItems = new MenuItem[menuItemsJson.size()];
            for (int j = 0; j < menuItemsJson.size(); j++) {
                JsonObject item = menuItemsJson.get(j).asObject();
                menuItems[j] = new MenuItem(item.get("description").asString(), item.get("value").asString());
            }

            JsonArray rankItemsJson = abilityJson.get("rankitems").asArray();
            MenuItem[] rankItems = new MenuItem[rankItemsJson.size()];
            for (int k = 0; k < rankItemsJson.size(); k++) {
                JsonObject item = rankItemsJson.get(k).asObject();
                rankItems[k] = new MenuItem(item.get("description").asString(), item.get("value").asString());
            }

            String name = jsonObject.get("Ability" + i).asString();
            int id = jsonObject.get("AbilityId" + i).asInt();
            String cooldown = abilityJson.get("cooldown").asString();
            String cost = abilityJson.get("cost").asString();
            String description = abilityJson.get("description").asString();
            String secondaryDescription = abilityJson.get("secondaryDescription").asString();

            switch (i) {
                case 1:
                    ability1 = new Ability(name, cooldown, cost, description, id, menuItems, rankItems, secondaryDescription);
                    break;
                case 2:
                    ability2 = new Ability(name, cooldown, cost, description, id, menuItems, rankItems, secondaryDescription);
                    break;
                case 3:
                    ability3 = new Ability(name, cooldown, cost, description, id, menuItems, rankItems, secondaryDescription);;
                    break;
                case 4:
                    ability4 = new Ability(name, cooldown, cost, description, id, menuItems, rankItems, secondaryDescription);
                    break;
            }
        }

        attackSpeed = jsonObject.get("AttackSpeed").asDouble();
        attackSpeedPerLevel = jsonObject.get("AttackSpeedPerLevel").asDouble();
        cons = jsonObject.get("Cons").asString();
        hp5PerLevel = jsonObject.get("HP5PerLevel").asDouble();
        health = jsonObject.get("Health").asDouble();
        healthPerFive = jsonObject.get("HealthPerFive").asDouble();
        healthPerLevel = jsonObject.get("HealthPerLevel").asDouble();
        lore = jsonObject.get("Lore").asString();
        mp5PerLevel = jsonObject.get("MP5PerLevel").asDouble();
        magicProtection = jsonObject.get("MagicProtection").asDouble();
        magicProtectionPerLevel = jsonObject.get("MagicProtectionPerLevel").asDouble();
        mana = jsonObject.get("Mana").asDouble();
        manaPerFive = jsonObject.get("ManaPerFive").asDouble();
        manaPerLevel = jsonObject.get("ManaPerLevel").asDouble();
        name = jsonObject.get("Name").asString();
        onFreeRotation = jsonObject.get("OnFreeRotation").asString();
        pantheon = jsonObject.get("Pantheon").asString();
        physicalPower = jsonObject.get("PhysicalPower").asDouble();
        physicalPowerPerLevel = jsonObject.get("PhysicalPowerPerLevel").asDouble();
        physicalProtection = jsonObject.get("PhysicalProtection").asDouble();
        physicalProtectionPerLevel = jsonObject.get("PhysicalProtectionPerLevel").asDouble();
        pros = jsonObject.get("Pros").asString();
        roles = jsonObject.get("Roles").asString();
        speed = jsonObject.get("Speed").asDouble();
        title = jsonObject.get("Title").asString();
        type = jsonObject.get("Type").asString();
    }

    public Ability getAbility1() {
        return ability1;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }

    public Ability getAbility3() {
        return ability3;
    }

    public void setAbility3(Ability ability3) {
        this.ability3 = ability3;
    }

    public Ability getAbility4() {
        return ability4;
    }

    public void setAbility4(Ability ability4) {
        this.ability4 = ability4;
    }

    public Ability getAbility5() {
        return ability5;
    }

    public void setAbility5(Ability ability5) {
        this.ability5 = ability5;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getAttackSpeedPerLevel() {
        return attackSpeedPerLevel;
    }

    public void setAttackSpeedPerLevel(double attackSpeedPerLevel) {
        this.attackSpeedPerLevel = attackSpeedPerLevel;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public double getHp5PerLevel() {
        return hp5PerLevel;
    }

    public void setHp5PerLevel(double hp5PerLevel) {
        this.hp5PerLevel = hp5PerLevel;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealthPerFive() {
        return healthPerFive;
    }

    public void setHealthPerFive(double healthPerFive) {
        this.healthPerFive = healthPerFive;
    }

    public double getHealthPerLevel() {
        return healthPerLevel;
    }

    public void setHealthPerLevel(double healthPerLevel) {
        this.healthPerLevel = healthPerLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public double getMp5PerLevel() {
        return mp5PerLevel;
    }

    public void setMp5PerLevel(double mp5PerLevel) {
        this.mp5PerLevel = mp5PerLevel;
    }

    public double getMagicProtection() {
        return magicProtection;
    }

    public void setMagicProtection(double magicProtection) {
        this.magicProtection = magicProtection;
    }

    public double getMagicProtectionPerLevel() {
        return magicProtectionPerLevel;
    }

    public void setMagicProtectionPerLevel(double magicProtectionPerLevel) {
        this.magicProtectionPerLevel = magicProtectionPerLevel;
    }

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = mana;
    }

    public double getManaPerFive() {
        return manaPerFive;
    }

    public void setManaPerFive(double manaPerFive) {
        this.manaPerFive = manaPerFive;
    }

    public double getManaPerLevel() {
        return manaPerLevel;
    }

    public void setManaPerLevel(double manaPerLevel) {
        this.manaPerLevel = manaPerLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnFreeRotation() {
        return onFreeRotation;
    }

    public void setOnFreeRotation(String onFreeRotation) {
        this.onFreeRotation = onFreeRotation;
    }

    public String getPantheon() {
        return pantheon;
    }

    public void setPantheon(String pantheon) {
        this.pantheon = pantheon;
    }

    public double getPhysicalPower() {
        return physicalPower;
    }

    public void setPhysicalPower(double physicalPower) {
        this.physicalPower = physicalPower;
    }

    public double getPhysicalPowerPerLevel() {
        return physicalPowerPerLevel;
    }

    public void setPhysicalPowerPerLevel(double physicalPowerPerLevel) {
        this.physicalPowerPerLevel = physicalPowerPerLevel;
    }

    public double getPhysicalProtection() {
        return physicalProtection;
    }

    public void setPhysicalProtection(double physicalProtection) {
        this.physicalProtection = physicalProtection;
    }

    public double getPhysicalProtectionPerLevel() {
        return physicalProtectionPerLevel;
    }

    public void setPhysicalProtectionPerLevel(double physicalProtectionPerLevel) {
        this.physicalProtectionPerLevel = physicalProtectionPerLevel;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public class Ability {
        private String name;
        private String cooldown;
        private String cost;
        private String description;
        private int id;
        private MenuItem[] menuItems;
        private MenuItem[] rankItems;
        private String secondaryDescription;

        public Ability(String name, String cooldown, String cost, String description, int id, MenuItem[] menuItems,
                       MenuItem[] rankItems, String secondaryDescription) {
            this.name = name;
            this.cooldown = cooldown;
            this.cost = cost;
            this.description = description;
            this.id = id;
            this.menuItems = menuItems;
            this.rankItems = rankItems;
            this.secondaryDescription = secondaryDescription;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCooldown() {
            return cooldown;
        }

        public void setCooldown(String cooldown) {
            this.cooldown = cooldown;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public MenuItem[] getMenuItems() {
            return menuItems;
        }

        public void setMenuItems(MenuItem[] menuItems) {
            this.menuItems = menuItems;
        }

        public MenuItem[] getRankItems() {
            return rankItems;
        }

        public void setRankItems(MenuItem[] rankItems) {
            this.rankItems = rankItems;
        }

        public String getSecondaryDescription() {
            return secondaryDescription;
        }

        public void setSecondaryDescription(String secondaryDescription) {
            this.secondaryDescription = secondaryDescription;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public class MenuItem {
        private String description;
        private String value;

        public MenuItem(String description, String value) {
            this.description = description;
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public String getValue() {
            return value;
        }
    }
}