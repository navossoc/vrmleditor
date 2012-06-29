package history;

import gui.Constants;
import gui.Editor;
import java.util.Stack;
import shape.Shape;

public final class History {

    private final int MAX_UNDO = 15;
    private final Editor editor;
    private final Stack<HistoryInfo> redoStack;
    private final Stack<HistoryInfo> undoStack;
    private boolean fileDirty;

    /**
     * Constructs a new history
     *
     * @param instance
     */
    public History(Editor instance) {
        editor = instance;
        fileDirty = false;
        redoStack = new Stack<HistoryInfo>();
        undoStack = new Stack<HistoryInfo>();
    }

    /**
     * Clear history state
     */
    public void clear() {
        fileDirty = false;
        undoStack.clear();
        redoStack.clear();
        menuUndo(false);
        menuRedo(false);
    }

    /**
     * Insert action into redo stack
     *
     * @param history
     */
    public void insertRedo(HistoryInfo history) {
        redoStack.push(history);
        menuRedo(true);
        fileDirty = true;
    }

    /**
     * Insert action into undo stack
     *
     * @param history
     */
    public void insertUndo(HistoryInfo history) {
        insertUndo(history, true);
    }

    /**
     * Insert action into undo stack
     *
     * @param history
     * @param clearRedo if true, clear redo stack
     */
    public void insertUndo(HistoryInfo history, boolean clearRedo) {
        // limit undo actions
        if (undoStack.size() >= MAX_UNDO) {
            undoStack.remove(0);
        }

        // clear redo stack
        if (clearRedo) {
            redoStack.clear();
            menuRedo(false);
        }

        undoStack.push(history);
        menuUndo(true);
        fileDirty = true;
    }

    /**
     * Check if the project had changes
     *
     * @return
     */
    public boolean isFileDirty() {
        return fileDirty;
    }

    /**
     * Set the project has changed
     *
     * @param fileDirty
     */
    public void setFileDirty(boolean fileDirty) {
        this.fileDirty = fileDirty;
    }

    /**
     * Redo last action
     */
    public void redo() {
        // protection
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
        insertUndo(info, false);

        // check if stack is empty
        if (redoStack.empty()) {
            menuRedo(false);
        }
    }

    /**
     * Undo last action
     */
    public void undo() {
        // protection
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
        insertRedo(info);

        // check if stack is empty
        if (undoStack.empty()) {
            menuUndo(false);
        }
    }

    /**
     * Change redo menu state
     *
     * @param enable true enable, false disable
     */
    private void menuRedo(boolean enable) {
        editor.getJMenuBar().getMenu(Constants.MENU_EDIT).getItem(Constants.MENU_EDIT_REDO).setEnabled(enable);
    }

    /**
     * Change undo menu state
     *
     * @param enable true enable, false disable
     */
    private void menuUndo(boolean enable) {
        editor.getJMenuBar().getMenu(Constants.MENU_EDIT).getItem(Constants.MENU_EDIT_UNDO).setEnabled(enable);
    }
}
