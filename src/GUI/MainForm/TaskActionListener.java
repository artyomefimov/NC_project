package GUI.MainForm;

public interface TaskActionListener {
    int ADD_TASK = 0;
    int EDIT_TASK = 1;
    int DELETE_TASK = 2;
    void setAction(int action);
}
