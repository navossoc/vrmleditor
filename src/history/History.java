package history;

import gui.EditorMain;
import java.util.Stack;
import shape.Shape;

public class History {

    private final int MAX_UNDO = 15;
    protected Stack<HistoryInfo> redoStack;
    protected Stack<HistoryInfo> undoStack;

    public History() {
        redoStack = new Stack<HistoryInfo>();
        undoStack = new Stack<HistoryInfo>();
    }

    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }

    public void insertRedo(HistoryInfo history) {
        redoStack.push(history);
    }

    public void insertUndo(HistoryInfo history) {
        if (undoStack.size() >= MAX_UNDO) {
            undoStack.remove(0);
        }
        undoStack.push(history);
    }

    public boolean isEmptyRedo() {
        return redoStack.empty();
    }

    public boolean isEmptyUndo() {
        return undoStack.empty();
    }

    public void redo() {
        if (redoStack.empty()) {
            return;
        }

        HistoryInfo info = redoStack.pop();
        switch (info.type) {
            case ADD: {
                EditorMain.instance.addShape(info.shape);
                break;
            }
            case EDIT: {
                Shape shape = EditorMain.instance.editShape(info.shape);
                info.shape = shape;
                break;
            }
            case DELETE: {
                EditorMain.instance.removeShape(info.shape);
                break;
            }
        }
        undoStack.push(info);
    }

    public void undo() {
        if (undoStack.empty()) {
            return;
        }

        HistoryInfo info = undoStack.pop();
        switch (info.type) {
            case ADD: {
                EditorMain.instance.removeShape(info.shape);
                break;
            }
            case EDIT: {
                Shape shape = EditorMain.instance.editShape(info.shape);
                info.shape = shape;
                break;
            }
            case DELETE: {
                EditorMain.instance.addShape(info.shape);
                break;
            }
        }
        redoStack.push(info);
    }
}
