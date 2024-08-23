public class Man {
    static final int MAX_INCORRECT = 6;
    int numIncorrect;
    char[] body;
    String[] colors;

    public Man() {
        body = new char[] {' ', ' ', ' ', '\n', ' ', ' ', ' ', '\n', ' ', ' ', ' ', '\n'};
        colors = new String[] {"\u001B[0m", "\u001B[0m", "\u001B[0m", "\u001B[0m", "\u001B[0m", "\u001B[0m"};
        numIncorrect = 0;
    }

    public boolean isAlive() {
        return numIncorrect < MAX_INCORRECT;
    }

    public void hang() {
        numIncorrect++;
        switch(numIncorrect){
            case 1:
                body[1] = 'O';
                colors[0] = "\u001B[31m";
                break;
            case 2:
                body[5] = '|';
                colors[1] = "\u001B[32m";
                break;
            case 3:
                body[4] = '\\';
                colors[2] = "\u001B[33m";
                break;
            case 4:
                body[6] = '/';
                colors[3] = "\u001B[34m";
                break;
            case 5:
                body[8]='/';
                colors[4] = "\u001B[35m";
                break;
            case 6:
                body[10] = '\\';
                colors[5] = "\u001B[36m";
                break;  
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int colorIndex = 0;
        for (int i = 0; i < body.length; i++) {
            if (body[i] != ' ' && body[i] != '\n') {
                sb.append(colors[colorIndex]).append(body[i]).append("\u001B[0m");
                colorIndex++;
            } else {
                sb.append(body[i]);
            }
        }
        return sb.toString();
    }

    protected char[] toCharArray() {
        return body;
    }

    public String getClue() {
        if (numIncorrect == MAX_INCORRECT - 1) {
            return "\u001B[31mWarning: One more incorrect guess and you lose!\u001B[0m";
        }
        return "";
    }

    public static void main(String[] args) {
        Man m = new Man();
        for(int i=0; i<Man.MAX_INCORRECT; i++) {
            m.hang();
            System.out.println(m);
            System.out.println(m.getClue());
        }
    }
}