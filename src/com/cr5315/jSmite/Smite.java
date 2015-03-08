package com.cr5315.jSmite;

import com.eclipsesource.json.JsonObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Smite {
    private String developerId;
    private String authKey;
    private String sessionId;
    private long sessionStart;

    public static final String RESPONSE_TYPE_JSON = "json";
    public static final String RESPONSE_TYPE_XML = "xml";
    private String responseFormat = RESPONSE_TYPE_JSON;

    private static final String BASE_URL = "http://api.smitegame.com/smiteapi.svc/";

    public Smite(String developerId, String authKey) {
        this.developerId = developerId;
        this.authKey = authKey;
        sessionStart = 0;
    }

    /**
     * A quick way of validating access to the Hi-Rez API.
     */
    public String ping() {
        return get(BASE_URL + "ping" + responseFormat);
    }

    /**
     * A required step to Authenticate the developerId/signature for further API use.
     * jSmite automatically creates a new session when required.
     */
    private boolean createSession() {
        String result = get(combine(new String[]{
                BASE_URL + "createsessionjson",
                developerId,
                getSignature("createsession"),
                getTimestamp()
        }, "/"));
        if (result == null) return false;

        JsonObject object = JsonObject.readFrom(result);
        if (object.get("ret_msg").asString().equals("Approved")) {
            sessionId = object.getString("session_id", null);
            sessionStart = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    /**
     * A means of validating that a session is established.
     */
    public String testSession() {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "testsession" + responseFormat,
                developerId,
                getSignature("testsession"),
                sessionId,
                getTimestamp()
        }, "/"));
    }

    /**
     * Returns API Developer daily usage limits and the current status against those limits.
     */
    public String getDataUsed() {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getdataused" + responseFormat,
                developerId,
                getSignature("getdataused"),
                sessionId,
                getTimestamp()
        }, "/"));
    }

    /**
     * Returns information regarding a particular match.  Rarely used in lieu of getMatchDetails().
     */
    public String getDemoDetails(String matchId) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getdemodetails" + responseFormat,
                developerId,
                getSignature("getdemodetails"),
                sessionId,
                getTimestamp(),
                matchId
        }, "/"));
    }

    /**
     * Returns the matchup information for each matchup for the current eSports Pro League season.  An important return value is “match_status” which represents a match being scheduled (1), in-progress (2), or complete (3).
     */
    public String getEsportsProLeagueDetails() {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getesportsproleaguedetails" + responseFormat,
                developerId,
                getSignature("getesportsproleaguedetails"),
                sessionId,
                getTimestamp()
        }, "/"));
    }

    /**
     * Returns the Smite User names of each of the player’s friends.
     */
    public String getFriends(String player) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getfriends" + responseFormat,
                developerId,
                getSignature("getfriends"),
                sessionId,
                getTimestamp(),
                player
        }, "/"));
    }

    /**
     * Returns the Rank and Worshippers value for each God a player has played.
     */
    public String getGodRanks(String player) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getgodranks" + responseFormat,
                developerId,
                getSignature("getgodranks"),
                sessionId,
                getTimestamp(),
                player
        }, "/"));
    }

    /**
     * Returns all Gods and their various attributes.
     */
    public String getGods(LanguageCode languageCode) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getgods" + responseFormat,
                developerId,
                getSignature("getgods"),
                sessionId,
                getTimestamp(),
                String.valueOf(languageCode.getValue())
        }, "/"));
    }

    /**
     * Returns the Recommended Items for a particular God.
     */
    public String getGodRecommendedItems(String godId, LanguageCode languageCode) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getgodrecommendeditems" + responseFormat,
                developerId,
                getSignature("getgodrecommendeditems"),
                sessionId,
                getTimestamp(),
                godId,
                String.valueOf(languageCode.getValue())
        }, "/"));
    }

    /**
     * Returns all Items and their various attributes.
     */
    public String getItems(LanguageCode languageCode) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getitems" + responseFormat,
                developerId,
                getSignature("getitems"),
                sessionId,
                getTimestamp(),
                String.valueOf(languageCode.getValue())
        }, "/"));
    }

    /**
     * Returns the statistics for a particular completed match.
     */
    public String getMatchDetails(String matchId) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getmatchdetails" + responseFormat,
                developerId,
                getSignature("getmatchdetails"),
                sessionId,
                getTimestamp(),
                matchId
        }, "/"));
    }

    /**
     * Lists all Match IDs for a particular Match Queue; useful for API developers interested in constructing data by Queue.  To limit the data returned, an {hour} parameter was added (valid values: 0 - 23).  An {hour} parameter of -1 represents the entire day, but be warned that this may be more data than we can return for certain queues.  Also, a returned “active_flag” means that there is no match information/stats for the corresponding match.  Usually due to a match being in-progress, though there could be other reasons.
     */
    public String getMatchIdsByQueue(Queue queue, String date, int hour) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getmatchidsbyqueue" + responseFormat,
                developerId,
                getSignature("getmatchidsbyqueue"),
                sessionId,
                getTimestamp(),
                String.valueOf(queue.getValue()),
                date,
                String.valueOf(hour)
        }, "/"));
    }

    /**
     * Returns the top players for a particular league (as indicated by the queue/tier/season parameters).
     */
    public String getLeagueLeaderboard(Queue queue, Tier tier, int season) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getleagueleaderboard" + responseFormat,
                developerId,
                getSignature("getloeagueleaderboard"),
                sessionId,
                getTimestamp(),
                String.valueOf(queue.getValue()),
                String.valueOf(tier.getValue()),
                String.valueOf(season)
        }, "/"));
    }

    /**
     * Provides a list of seasons (including the single active season) for a match queue.
     */
    public String getLeagueSeasons(Queue queue) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getleagueseasons" + responseFormat,
                developerId,
                getSignature("getleagueseasons"),
                sessionId,
                getTimestamp(),
                String.valueOf(queue.getValue())
        }, "/"));
    }

    /**
     * Gets recent matches and high level match statistics for a particular player.
     */
    public String getMatchHistory(String player) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getmatchhistory" + responseFormat,
                developerId,
                getSignature("getmatchhistory"),
                sessionId,
                getTimestamp(),
                player
        }, "/"));
    }

    /**
     * Returns league and other high level data for a particular player.
     */
    public String getPlayer(String player) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getplayer" + responseFormat,
                developerId,
                getSignature("getplayer"),
                sessionId,
                getTimestamp(),
                player
        }, "/"));
    }

    /**
     * Returns player status as follows:
     * 0 - Offline
     * 1 - In Lobby  (basically anywhere except god selection or in game)
     * 2 - god Selection (player has accepted match and is selecting god before start of game)
     * 3 - In Game (match has started)
     * 4 - Online (player is logged in, but may be blocking broadcast of player state)
     */
    public String getPlayerStatus(String player) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getplayerstatus" + responseFormat,
                developerId,
                getSignature("getplayerstatus"),
                sessionId,
                getTimestamp(),
                player
        }, "/"));
    }

    /**
     * Returns match summary statistics for a (player, queue) combination grouped by gods played.
     */
    public String getQueueStats(String player, Queue queue) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getqueuestats" + responseFormat,
                developerId,
                getSignature("getqueuestats"),
                sessionId,
                getTimestamp(),
                player,
                String.valueOf(queue.getValue())
        }, "/"));
    }

    /**
     * Lists the number of players and other high level details for a particular clan.
     */
    public String getTeamDetails(String clanId) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getteamdetails" + responseFormat,
                developerId,
                getSignature("getteamdetails"),
                sessionId,
                getTimestamp(),
                clanId
        }, "/"));
    }

    /**
     * Gets recent matches and high level match statistics for a particular clan/team.
     */
    public String getTeamMatchHistory(String clanId) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getteammatchhistory" + responseFormat,
                developerId,
                getSignature("getteammatchhistory"),
                sessionId,
                getTimestamp(),
                clanId
        }, "/"));
    }

    /**
     * Lists the players for a particular clan.
     */
    public String getTeamPlayers(String clanId) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "getteamplayers" + responseFormat,
                developerId,
                getSignature("getteamplayers"),
                sessionId,
                getTimestamp(),
                clanId
        }, "/"));
    }

    /**
     * Lists the 50 most watched / most recent recorded matches.
     */
    public String getTopMatches() {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "gettopmatches" + responseFormat,
                developerId,
                getSignature("gettopmatches"),
                sessionId,
                getTimestamp()
        }, "/"));
    }

    /**
     * Returns high level information for Team names containing the “searchTeam” string.
     */
    public String searchTeams(String searchTeam) {
        if (!isSessionValid() && !createSession()) return null;
        return get(combine(new String[] {
                BASE_URL + "searchteams" + responseFormat,
                developerId,
                getSignature("searchteams"),
                sessionId,
                getTimestamp(),
                searchTeam
        }, "/"));
    }

    private String get(String url) {
        return HttpClient.fetch(url);
    }

    private String combine(String[] items, String delimiter) {
        int k = items.length;
        if (k == 0) return null;

        StringBuilder builder = new StringBuilder();
        builder.append(items[0]);

        for (int i = 1; i < items.length; i++) {
            builder.append(delimiter).append(items[i]);
        }

        return builder.toString();
    }

    /**
     * Returns whether the current API session is valid based on the criteria of one session lasting 15 minutes.
     */
    private boolean isSessionValid() {
        return sessionId != null && Math.abs(System.currentTimeMillis() - sessionStart) <= 15 * 60 * 1000;
    }

    /**
     * Get the current UTC timestamp formatted yyyyMMddHHmmss
     */
    public String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(new Date());
    }

    /**
     * Create the signature parameter required for each API call
     */
    private String getSignature(String method) {
        return getMD5(developerId+ method + authKey+ getTimestamp());
    }

    /**
     * Generate an MD5 hash for an input String
     * http://www.mkyong.com/java/java-md5-hashing-example/
     */
    public String getMD5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes());

            byte[] data = messageDigest.digest();

            // Convert from byte to hex
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : data) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) stringBuffer.append("0");
                stringBuffer.append(hex);
            }

            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * Sets the default response format.
     */
    public void setResponseFormat(String responseFormat) {
        if (responseFormat.equals(RESPONSE_TYPE_JSON) || responseFormat.equals(RESPONSE_TYPE_XML))
            this.responseFormat = responseFormat;
    }

    public String getResponseFormat() {
        return responseFormat;
    }

    public enum LanguageCode {
        ENGLISH (1),
        GERMAN (2),
        FRENCH (3),
        SPANISH (7),
        SPANISH_LATIN_AMERICA (9),
        PORTUGUESE (10),
        RUSSIAN (11),
        POLISH (12),
        TURKISH (13);

        private final int value;

        private LanguageCode(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum Queue {
        CONQUEST_5V5 (423),
        NOVICE_QUEUE (424),
        CONQUEST (426),
        PRACTICE (427),
        CONQUEST_CHALLENGE (429),
        CONQUEST_RANKED (430),
        DOMINATION (433),
        MOTD (434),
        ARENA (435),
        ARENA_CHALLENGE (438),
        DOMINATION_CHALLENGE (439),
        JOUST_LEAGUE (440),
        JOUST_CHALLENGE (441),
        ASSAULT (445),
        ASSAULT_CHALLENGE (446),
        JOUST_3V3 (448),
        CONQUEST_LEAGUE (451),
        ARENA_LEAGUE (452);

        private final int value;

        private Queue(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public enum Tier {
        BRONZE_V (1), BRONZE_IV (2), BRONZE_III (3), BRONZE_II (4), BRONZE_I (5),
        SILVER_V (6), SILVER_IV (7), SILVER_III (8), SILVER_II (9), SILVER_I (10),
        GOLD_V (11), GOLD_IV (12), GOLD_III (13), GOLD_II (14), GOLD_I (15),
        PLATINUM_V (16), PLATINUM_IV (17), PLATINUM_III (18), PLATINUM_II (19), PLATINUM_I (20),
        DIAMOND_V (21), DIAMOND_IV (22), DIAMOND_III (23), DIAMOND_II (24), DIAMOND_I (25),
        MASTERS_I (26);

        private final int value;

        private Tier(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }
}