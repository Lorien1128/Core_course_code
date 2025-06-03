import java.util.ArrayList;

public class Practice {
    public int minNumberOfFrogs(String croakOfFrogs) {
        char[] croaks = croakOfFrogs.toCharArray();
        ArrayList<Integer> cPositions = new ArrayList<>();
        ArrayList<Integer> rPositions = new ArrayList<>();
        ArrayList<Integer> oPositions = new ArrayList<>();
        ArrayList<Integer> aPositions = new ArrayList<>();
        ArrayList<Integer> kPositions = new ArrayList<>();
        int frogs = 1;
        for (int i = 0; i < croaks.length; i++) {
            switch (croaks[i]) {
                case 'c' : {
                    cPositions.add(i);
                    if (frogs > 0) {
                        frogs--;
                    }
                    break;
                }
                case 'r' : {
                    rPositions.add(i);
                    break;
                }
                case 'o' : {
                    oPositions.add(i);
                    break;
                }
                case 'a' : {
                    aPositions.add(i);
                    break;
                }
                case 'k' : {
                    kPositions.add(i);
                    frogs++;
                    break;
                }
                default: return -1;
            }
        }
        if (cPositions.size() == 0) {
            if (rPositions.size() == 0 && oPositions.size() == 0 && aPositions.size() == 0
                    && kPositions.size() == 0) {
                return 0;
            }
            else return -1;
        }
        int cPoint = 0;
        int rPoint = 0;
        int oPoint = 0;
        int aPoint = 0;
        int kPoint = 0;
        while (cPoint != cPositions.size()) {
            cPoint++;
            if (rPoint >= rPositions.size() ||
                    cPositions.get(cPoint - 1) > rPositions.get(rPoint)) {
                return -1;
            }
            rPoint++;
            if (oPoint >= oPositions.size() ||
                    rPositions.get(rPoint - 1) > oPositions.get(oPoint)) {
                return -1;
            }
            oPoint++;
            if (aPoint >= aPositions.size() ||
                    oPositions.get(oPoint - 1) > aPositions.get(aPoint)) {
                return -1;
            }
            aPoint++;
            if (kPoint >= kPositions.size() ||
                    aPositions.get(aPoint - 1) > kPositions.get(kPoint)) {
                return -1;
            }
            kPoint++;
        }
        if (rPoint == rPositions.size() && oPoint == oPositions.size()
                && aPoint == aPositions.size() && kPoint == kPositions.size()) {
            return frogs;
        }
        else {
            return -1;
        }
    }
}
