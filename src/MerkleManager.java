import java.util.ArrayList;
public class MerkleManager {
    public static volatile String userEnteredWord;
    public static String expectedMerkleRoot;
    public static String merkleRoot = null;
    public static int strikes = 0;

    public static synchronized void manage() {
        Util util = new Util();
        expectedMerkleRoot = util.promptUser("Enter the expected Merkle root");

        Thread merkleThread = new Thread(new MerkleThread());
        Thread rogueThread = new Thread(new RogueThread());
        Thread monitorThread = new Thread(new MonitorThread());

        merkleThread.start();
        rogueThread.start();
        monitorThread.start();

        while (true) {
            userEnteredWord = util.promptUser("Enter a word:");
        }
    }

    public static synchronized String grabWord() {
        String tempWord = userEnteredWord;
        userEnteredWord = null;
        return tempWord;
    }

}
