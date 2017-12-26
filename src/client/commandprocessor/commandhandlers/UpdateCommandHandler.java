package client.commandprocessor.commandhandlers;

import client.commandprocessor.Command;
import client.gui.mainform.MainForm;
import client.model.Journal;

public class UpdateCommandHandler implements CommandHandler {
    @Override
    public synchronized void handle(Command command) {
        MainForm mainForm = MainForm.getInstance();
        if (mainForm == null) mainForm = new MainForm();
        mainForm.setJournal((Journal) command.getObject()); // при update приходит журнал
        mainForm.setVisible(true);
    }
}