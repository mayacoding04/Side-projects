import java.util.ArrayList;
public class MerkleThread implements Runnable{
    public static volatile ArrayList<String> grabbedWords;
    private int iMerkleTreeInputs = 4;
    @Override
    public void run() {
        Util oUtil = new Util();
        grabbedWords = new ArrayList<>();
        while(true){
            oUtil.sleepRandomTime("Merkle Thread");
            String sNewWord = MerkleManager.grabWord();
            if(sNewWord != null){
                System.out.println("[Merkle] grabbed word");
                grabbedWords.add(sNewWord);
                if(grabbedWords.size() == iMerkleTreeInputs) {
                    MerkleManager.merkleRoot = oUtil.getMerkleRoot(grabbedWords);
                }
            }
        }
    }
}