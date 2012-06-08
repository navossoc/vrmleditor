package history;

import gui.Editor;
import java.util.Stack;
import shape.Shape;

public final class History {

    private final int MAX_UNDO = 15;
    private final Editor editor;
    private final Stack<HistoryInfo> redoStack;
    private final Stack<HistoryInfo> undoStack;
    private boolean fileDirty;

    public History(Editor instance) {
        editor = instance;
        fileDirty = false;
        redoStack = new Stack<HistoryInfo>();
        undoStack = new Stack<HistoryInfo>();
    }

    public void clear() {
        fileDirty = false;
        undoStack.clear();
        redoStack.clear();
    }

    public void insertRedo(HistoryInfo history) {
        redoStack.push(history);
        fileDirty = true;
    }

    public void insertUndo(HistoryInfo history) {
        if (undoStack.size() >= MAX_UNDO) {
            undoStack.remove(0);
        }
        undoStack.push(history);
        fileDirty = true;
    }

    public boolean isEmptyRedo() {
        return redoStack.empty();
    }

    public boolean isEmptyUndo() {
        return undoStack.empty();
    }

    public boolean isFileDirty() {
        return fileDirty;
    }

    public void setFileDirty(boolean fileDirty) {
        this.fileDirty = fileDirty;
    }

    public void redo() {
        if (redoStack.empty()) {
            return;
        }

        HistoryInfo info = redoStack.pop();
        switch (info.type) {
            case ADD: {
                editor.addShape(info.shape);
                break;
            }
            case EDIT: {
                Shape shape = editor.editShape(info.shape);
                info.shape = shape;
                break;
            }
            case DELETE: {
                editor.delShape(info.shape);
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
                editor.delShape(info.shape);
                break;
            }
            case EDIT: {
                Shape shape = editor.editShape(info.shape);
                info.shape = shape;
                break;
            }
            case DELETE: {
                editor.addShape(info.shape);
                break;
            }
        }
        redoStack.push(info);
    }
}
