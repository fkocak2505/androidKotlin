package com.example.expandablelistviewwithgrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;

    ListViewAdapter treeViewAdapter;

    public String[] groups = {"Smart Phone", "Android Phones", "Window Phones", "Rim Phone", "Other Phones"};

    public String[][] child = {{""}, {""}, {"", ""}, {""}, {"", "", "", ""}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        treeViewAdapter = new ListViewAdapter(this,
                ListViewAdapter.PaddingLeft >> 1);
        expandableListView = (ExpandableListView) this
                .findViewById(R.id.expandableListView);

        List<ListViewAdapter.TreeNode> treeNode = treeViewAdapter.GetTreeNode();
        for (int i = 0; i < groups.length; i++) {
            ListViewAdapter.TreeNode node = new ListViewAdapter.TreeNode();
            node.parent = groups[i];
            for (int ii = 0; ii < child[i].length; ii++) {
                node.childs.add(child[i][ii]);
            }
            treeNode.add(node);
        }

        treeViewAdapter.UpdateTreeNode(treeNode);
        expandableListView.setAdapter(treeViewAdapter);


    }
}
