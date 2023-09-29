import static java.lang.Thread.sleep;

public class MonitorThread implements Runnable {

    public void run() {
        // Create an endless loop.
        while (true) {
            // Check if MerkleManager.sMerkleRoot is not null.
            if (MerkleManager.merkleRoot != null) {
                // Check if the calculated Merkle root equals the user-entered Merkle root.
                if (MerkleManager.merkleRoot.equals(MerkleManager.expectedMerkleRoot)) {
                    System.out.println("You win: " + MerkleManager.merkleRoot); // Print win message.
                    System.exit(0); // Exit the app.
                } else {
                    System.out.println("You lost."); // Print loss message.
                    System.exit(0); // Exit the app.
                }
            }

            // Check if the user has accumulated 3 strikes.
            if (MerkleManager.strikes == 3) {
                System.out.println("3 strikes: you lost!"); // Print strike loss message.
                System.exit(0); // Exit the app.
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }


        }
    }
}