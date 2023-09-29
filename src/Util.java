import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.security.SecureRandom;


public class Util {

    public String getMerkleRoot (ArrayList<String> lstItems) {
        MerkleNode Node1 = new MerkleNode();
        MerkleNode Node2 = new MerkleNode();
        MerkleNode Node3 = new MerkleNode();
        MerkleNode Node4 = new MerkleNode();
        MerkleNode Node5 = new MerkleNode();
        MerkleNode Node6 = new MerkleNode();
        MerkleNode Node7 = new MerkleNode();

        Node1.sHash = generateHash(lstItems.get(0));
        Node2.sHash = generateHash(lstItems.get(1));
        Node3.sHash = generateHash(lstItems.get(2));
        Node4.sHash = generateHash(lstItems.get(3));
        Node5.sHash = generateHash(lstItems.get(4));
        Node6.sHash = generateHash(lstItems.get(5));
        Node7.sHash = generateHash(lstItems.get(6));

        populateMerkleNode(Node5, Node1, Node2);
        populateMerkleNode(Node6, Node3, Node4);
        populateMerkleNode(Node7, Node5, Node6);



        return Node7.sHash;


    }

    private void populateMerkleNode (MerkleNode populatedNode, MerkleNode leftNode, MerkleNode rightNode) {
        populatedNode.oLeft = leftNode;
        populatedNode.oRight = rightNode;
        populatedNode.sHash = generateHash(populatedNode.oLeft.sHash + populatedNode.oRight.sHash);

    }

    public synchronized String generateHash(String sOriginal){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] btEncodedhash = digest.digest(
                    sOriginal.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < btEncodedhash.length; i++) {
                sb.append(Integer.toString((btEncodedhash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch(Exception ex){

            System.out.println("Error generating hash: " + ex.getMessage());
            return null;
        }
    }

    public String promptUser(String sQuestion) {
        JOptionPane oQuestion  = new JOptionPane();
        String sAnswer = oQuestion.showInputDialog(sQuestion);
        return sAnswer;
    }

    public void sleepRandomTime(String sThreadName){

        // Gets random number between 0 and 5 and then adds 3, meaning between 3 and 8 now.
        int iSleepTime = new SecureRandom().nextInt(5) + 3;

        System.out.println(sThreadName + " sleeping for " + iSleepTime + " seconds.");
        sleep(iSleepTime);

    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
