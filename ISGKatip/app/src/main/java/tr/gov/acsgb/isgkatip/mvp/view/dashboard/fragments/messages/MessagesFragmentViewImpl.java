package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.messages;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.messages.dataModel.MessagesListDataModel;
import tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.messages.adapter.MessagesListViewAdapter;

public class MessagesFragmentViewImpl extends Fragment implements IMessagesFragmentView {

    /// Component
    FloatingActionButton fabNewMessages;
    ConstraintLayout noMessagesLayout;
    ConstraintLayout messagesLayout;
    ListView messagesListView;

    /// Data List
    List<MessagesListDataModel> listOfMessagesData;

    //==============================================================================================
    public MessagesFragmentViewImpl() {
        // Required empty public constructor
    }

    //==============================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //==============================================================================================
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        initMessagesFragmentComponent(view);
        clickFabNewButton();
        fillMessagesListData();
        bindMessagesListData2Adapter();

        randomShowScreen();

        return view;
    }

    //==============================================================================================
    public void randomShowScreen(){
        List<Boolean> deletedAfter = new ArrayList<>();
        deletedAfter.add(true);
        deletedAfter.add(false);

        int index = new Random().nextInt(2);
        boolean val = deletedAfter.get(index);
        if(val){
            noMessagesLayout.setVisibility(View.VISIBLE);
            messagesLayout.setVisibility(View.GONE);
        } else {
            noMessagesLayout.setVisibility(View.GONE);
            messagesLayout.setVisibility(View.VISIBLE);
        }
    }

    //==============================================================================================
    @Override
    public void initMessagesFragmentComponent(View view) {
        fabNewMessages = (FloatingActionButton)view.findViewById(R.id.fab);
        noMessagesLayout = (ConstraintLayout) view.findViewById(R.id.noMessagesLayout);
        messagesLayout = (ConstraintLayout) view.findViewById(R.id.messagesLayout);
        messagesListView = (ListView) view.findViewById(R.id.messagesListView);
    }

    //==============================================================================================
    @Override
    public void clickFabNewButton() {
        fabNewMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Yeni Mesaj oluşturuluyor..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //==============================================================================================
    @Override
    public void fillMessagesListData() {
        listOfMessagesData = new ArrayList<>();
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",true));
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",true));
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",false));
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",false));
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",true));
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",true));
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",true));
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",true));
        listOfMessagesData.add(new MessagesListDataModel(R.drawable.message_tab_new_message,R.mipmap.ic_launcher_round,"Fatih Koçak","Sözleşmeler hakkında mailini bekliyoruz. Lorem İpsum Lorem İpsum Lorem İpsum Lorem İpsum",true));
    }

    //==============================================================================================
    @Override
    public void bindMessagesListData2Adapter() {
        MessagesListViewAdapter messagesListViewAdapter = new MessagesListViewAdapter(getActivity().getApplicationContext(),listOfMessagesData);
        messagesListView.setAdapter(messagesListViewAdapter);
    }
}
