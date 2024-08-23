public class Gallows {
    protected Man man;
    protected char[] frame;

    public Gallows() {
        man = new Man();
        frame = new char[60];
        for (int i = 0; i < frame.length; i++) {
            frame[i] = ' ';
            if ((i + 1) % 10 == 0) {
                frame[i] = '\n';
            }
        }
        makeCenterPost();
        makeBeam();
        makeBase();
        makeRope();
    }

    public void makeCenterPost() {
        frame[12] = '|';
        frame[22] = '|';
        frame[32] = '|';
        frame[42] = '|';
        frame[52] = '|';
    }

    public void makeBeam() {
        frame[3] = '_';
        frame[4] = '_';
        frame[5] = '_';
        frame[6] = '_';
    }

    public void makeBase() {
        frame[50] = '_';
        frame[51] = '_';
        frame[53] = '_';
        frame[54] = '_';
    }

    public void makeRope() {
        frame[17] = '|';
    }

    public void hang() {
        man.hang();
        char[] manArray = man.toString().toCharArray();
        frame[27] = manArray[1];
        frame[37] = manArray[5];
        frame[36] = manArray[4];
        frame[38] = manArray[6];
        frame[46] = manArray[8];
        frame[48] = manArray[10];
    }

    public boolean isAlive() {
        return man.isAlive();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char c : frame) {
            sb.append(c);
        }
        return sb.toString();
    }

    public String getClue() {
        return man.getClue();
    }

    public static void main(String[] args) {
        Gallows g = new Gallows();
        System.out.println(g);
        for(int i=0; i< Man.MAX_INCORRECT; i++) {
            g.hang();
            System.out.println(g);
            System.out.println(g.getClue());
        }
    }
}
