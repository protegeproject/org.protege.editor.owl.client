package org.protege.editor.owl.client.diff.ui;

import org.protege.editor.core.Disposable;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.client.diff.model.CommitMetadata;
import org.protege.editor.owl.client.diff.model.LogDiffEvent;
import org.protege.editor.owl.client.diff.model.LogDiffListener;
import org.protege.editor.owl.client.diff.model.LogDiffManager;
import org.protege.editor.owl.model.OWLModelManager;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;

/**
 * @author Rafael Gonçalves <br>
 * Stanford Center for Biomedical Informatics Research
 */
public class CommitPanel extends JPanel implements Disposable {
    private static final long serialVersionUID = 982230736000168376L;
    private LogDiffManager diffManager;
    private JList<CommitMetadata> commitList = new JList<>();

    /**
     * Constructor
     *
     * @param modelManager  OWL model manager
     * @param editorKit OWL editor kit
     */
    public CommitPanel(OWLModelManager modelManager, OWLEditorKit editorKit) {
        diffManager = LogDiffManager.get(modelManager, editorKit);
        diffManager.addListener(diffListener);
        setLayout(new BorderLayout(20, 20));
        setupList();

        JScrollPane scrollPane = new JScrollPane(commitList);
        scrollPane.setBorder(GuiUtils.EMPTY_BORDER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
        listCommits(LogDiffEvent.ONTOLOGY_UPDATED);
    }

    private ListSelectionListener listSelectionListener = e -> {
        CommitMetadata selection = commitList.getSelectedValue();
        if (selection != null && !e.getValueIsAdjusting()) {
            diffManager.setSelectedCommit(selection);
        }
    };

    private LogDiffListener diffListener = event -> {
        if (event.equals(LogDiffEvent.AUTHOR_SELECTION_CHANGED) || event.equals(LogDiffEvent.ONTOLOGY_UPDATED)) {
            diffManager.clearSelectedChanges();
            listCommits(event);
        }
    };

    private void setupList() {
        commitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        commitList.addListSelectionListener(listSelectionListener);
        commitList.setCellRenderer(new CommitListCellRenderer());
        commitList.setFixedCellHeight(45);
        commitList.setFixedCellWidth(this.getWidth());
        commitList.setBorder(GuiUtils.MATTE_BORDER);
    }

    private void listCommits(LogDiffEvent event) {
        if(diffManager.getVersionedOntologyDocument().isPresent()) {
            List<CommitMetadata> commits = diffManager.getCommits(event);
            commitList.setListData(commits.toArray(new CommitMetadata[commits.size()]));
        }
        else {
            commitList.setListData(new CommitMetadata[0]);
        }
    }

    @Override
    public void dispose() {
        commitList.removeListSelectionListener(listSelectionListener);
    }
}
