import java.util.Stack;

public class UndoStringBuilder {
    private final StringBuilder stringBuilder;
    private final Stack<Snapshot> history;

    public UndoStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.history = new Stack<>();
    }
    private void save(){
        history.push(new Snapshot(stringBuilder.toString()));
    }
    public void undo(){
        if(!history.isEmpty()) {
            Snapshot snapshot = history.pop();
            stringBuilder.setLength(0);
            stringBuilder.append(snapshot.getState());
        }
    }
    public UndoStringBuilder append(String str){
        save();
        stringBuilder.append(str);
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
