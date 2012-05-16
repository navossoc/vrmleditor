package history;

import gui.EditorMain;
import java.util.Stack;
import shape.Shape;

public class History {

    private Shape shape;
    private Type type;
    public static Stack<History> undoStack;
    public static Stack<History> redoStack;

    public enum Type {

        ADD, EDIT, DELETE
    }

    public History() {
        undoStack = new Stack<History>();
        redoStack = new Stack<History>();
    }

    public History(Shape shape, Type type) {
        this.shape = shape;
        this.type = type;
    }

    // add 
    // edit
    // delete
    private void undo() {
        switch (type) {
            case ADD: {
                System.out.println("ADD");
                EditorMain.instance.removeShape(shape);
                break;
            }
            case EDIT: {
                System.out.println("EDIT");
                Shape temp = EditorMain.instance.editShape(shape);
                undoStack.peek().shape = temp;
                break;
            }
            case DELETE: {
                System.out.println("DELETE");
                EditorMain.instance.addShape(shape);
                break;
            }
        }

        redoStack.push(undoStack.pop());
    }

    private void redo() {
        switch (type) {
            case ADD: {
                System.out.println("ADD");
                EditorMain.instance.addShape(shape);
                break;
            }
            case EDIT: {
                System.out.println("EDIT");
                Shape temp = EditorMain.instance.editShape(shape);
                redoStack.peek().shape = temp;
                break;
            }
            case DELETE: {
                System.out.println("DELETE");
                EditorMain.instance.removeShape(shape);
                break;
            }
        }

        undoStack.push(redoStack.pop());
    }

    public void popUndo() {
        if (!undoStack.empty()) {
            undoStack.peek().undo();
        }
    }

    public void pushUndo(History history) {
        undoStack.push(history);
    }

    public void popRedo() {
        if (!redoStack.empty()) {
            redoStack.peek().redo();
        }
    }

    public void pushRedo(History history) {
        redoStack.push(history);
    }
}
