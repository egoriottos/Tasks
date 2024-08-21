public class Main {
    public static void main(String[] args) {
        UndoStringBuilder sb = new UndoStringBuilder();
        sb.append("Egor");
        System.out.println(sb);
        sb.append("io");
        System.out.println(sb);
        sb.undo();
        System.out.println(sb);
    }
}