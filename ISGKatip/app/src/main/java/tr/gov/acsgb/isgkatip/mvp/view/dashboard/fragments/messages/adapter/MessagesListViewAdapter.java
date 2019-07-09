package tr.gov.acsgb.isgkatip.mvp.view.dashboard.fragments.messages.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tr.gov.acsgb.isgkatip.R;
import tr.gov.acsgb.isgkatip.mvp.model.dashboard.messages.dataModel.MessagesListDataModel;

public class MessagesListViewAdapter extends BaseAdapter {
    Context mContext;
    List<MessagesListDataModel> listOfMessagesData;
    LayoutInflater inflater;

    //==============================================================================================
    public MessagesListViewAdapter(Context mContext, List<MessagesListDataModel> listOfMessagesData) {
        this.mContext = mContext;
        this.listOfMessagesData = listOfMessagesData;
    }

    //==============================================================================================
    @Override
    public int getCount() {
        return listOfMessagesData.size();
    }

    //==============================================================================================
    @Override
    public Object getItem(int position) {
        return null;
    }

    //==============================================================================================
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //==============================================================================================
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView isNewMessages;
        ImageView personPhoto;
        TextView personNameSurname;
        TextView oneLineMessages;

        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.messages_list_view_row, parent, false);

        isNewMessages = (ImageView) itemView.findViewById(R.id.isNewMessages);
        personPhoto = (ImageView) itemView.findViewById(R.id.personPhoto);
        personNameSurname = (TextView) itemView.findViewById(R.id.personNameSurname);
        oneLineMessages = (TextView) itemView.findViewById(R.id.oneLineMessages);

        if (listOfMessagesData.get(position).isNewMessage())
            isNewMessages.setImageResource(listOfMessagesData.get(position).getNewMessagesIcon());
        personPhoto.setImageResource(listOfMessagesData.get(position).getPersonPhoto());
        personNameSurname.setText(listOfMessagesData.get(position).getPersonNameSurname());
        oneLineMessages.setText(listOfMessagesData.get(position).getOneLineMessages());

        return itemView;
    }
}
