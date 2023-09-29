public class RogueThread implements Runnable {

@Override
    public void run() {
        Util util = new Util(); // Instantiate a Util object

        while (true) { // Create an endless loop
            util.sleepRandomTime("Rogue Thread"); // Call sleepRandomTime on util

            String sNewWord = MerkleManager.grabWord(); // Call grabWord similar to MerkleThread

            if (sNewWord != null) {
                // Increase iStrikes static variable on MerkleManager by 1
                MerkleManager.strikes++;

                // Print out to the screen that rogue grabbed a word and mention "STRIKE!"
                System.out.println("Rogue grabbed a word! STRIKE!");
            }
        }
    }
}