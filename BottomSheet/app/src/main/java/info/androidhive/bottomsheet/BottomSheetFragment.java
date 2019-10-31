package info.androidhive.bottomsheet;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    View viewP;
    /*private ArrayList<Person> persons;
    private ListView listView;
    private CustomListViewAdapter listViewAdapter;*/


    public List<String> list_parent;
    public ExpandListViewAdapter expand_adapter;
    public HashMap<String, List<String>> list_child;
    public BottomSheetListView expandlist_view;
    public List<String> gs_list;
    public List<String> fb_list;
    public int last_position = -1;

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewP = inflater.inflate(R.layout.fragment_bottom_sheet_dialog, container, false);

        /*initialize();
        fillArrayList(persons);*/

        expandlist_view = (BottomSheetListView) viewP.findViewById(R.id.expand_listview);
        hazırla();

        expand_adapter = new ExpandListViewAdapter(getActivity().getApplicationContext(), list_parent, list_child);
        expandlist_view.setAdapter(expand_adapter);  // oluşturduğumuz adapter sınıfını set ediyoruz
        expandlist_view.setClickable(true);

        expandlist_view.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                String child_name = (String) expand_adapter.getChild(groupPosition, childPosition);
                Toast.makeText(getActivity().getApplicationContext(),"hey" + child_name, Toast.LENGTH_LONG).show();

                return false;
            }
        });
        return viewP;
    }

    public void hazırla() {
        list_parent = new ArrayList<String>();  // başlıklarımızı listemelek için oluşturduk
        list_child = new HashMap<String, List<String>>(); // başlıklara bağlı elemenları tutmak için oluşturduk

        list_parent.add("GALATASARAY");  // ilk başlığı giriyoruz
        list_parent.add("FENERBAHCE");   // ikinci başlığı giriyoruz

        gs_list = new ArrayList<String>();  // ilk başlık için alt elemanları tanımlıyoruz
        gs_list.add("Muslera");
        gs_list.add("Sabri");
        gs_list.add("Chejdou");
        gs_list.add("Semih Kaya");
        gs_list.add("Telles");
        gs_list.add("Selçuk İnan");
        gs_list.add("Felipe Melo");
        gs_list.add("Hamit");
        gs_list.add("Weslej Sneijder");
        gs_list.add("Bruma");
        gs_list.add("Burak Yılmaz");
        gs_list.add("Selçuk İnan");
        gs_list.add("Felipe Melo");
        gs_list.add("Hamit");
        gs_list.add("Weslej Sneijder");
        gs_list.add("Bruma");
        gs_list.add("Burak Yılmaz");

        fb_list = new ArrayList<String>(); // ikinci başlık için alt elemanları tanımlıyoruz
        fb_list.add("Volkan Demirel");
        fb_list.add("Gökhan Gönül");
        fb_list.add("Bekir");
        fb_list.add("Caner Erkin");
        fb_list.add("Mehmet Topal");
        fb_list.add("Emre");
        fb_list.add("Alper Potuk");
        fb_list.add("Mehmet Topuz");
        fb_list.add("Diego");
        fb_list.add("Sow");
        fb_list.add("Emenike");
        fb_list.add("Alper Potuk");
        fb_list.add("Mehmet Topuz");
        fb_list.add("Diego");
        fb_list.add("Sow");
        fb_list.add("Emenike");

        list_child.put(list_parent.get(0), gs_list); // ilk başlığımızı ve onların elemanlarını HashMap sınıfında tutuyoruz
        list_child.put(list_parent.get(1), fb_list); // ikinci başlığımızı ve onların elemanlarını HashMap sınıfında tutuyoruz


    }

    /*private void initialize() {
        persons = new ArrayList<Person>();
        listView = (BottomSheetListView) viewP.findViewById(R.id.person_list_view);
    }

    private void fillArrayList(ArrayList<Person> persons) {
        for (int index = 0; index < 20; index++) {
            Person person = new Person("Mr. Android " + index, "Nowhere", R.drawable.ic_launcher_foreground);
            persons.add(person);
        }

        listViewAdapter = new CustomListViewAdapter(getActivity().getApplicationContext(), persons);
        listView.setAdapter(listViewAdapter);

    }*/
}
