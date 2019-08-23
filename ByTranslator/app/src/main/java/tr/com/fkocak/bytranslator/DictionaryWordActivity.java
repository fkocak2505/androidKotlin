package tr.com.fkocak.bytranslator;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tr.com.fkocak.bytranslator.model.dictionary.Response4Dictionary;
import tr.com.fkocak.bytranslator.model.dictionary.Result4SpesificWord;
import tr.com.fkocak.bytranslator.model.dictionary.Syllables;
import tr.com.fkocak.bytranslator.service.ApiService;
import tr.com.fkocak.bytranslator.singleton.CommonSingletonClass;
import tr.com.fkocak.bytranslator.singleton.DictionaryResponseSingleton;
import tr.com.fkocak.bytranslator.tree.Dir;
import tr.com.fkocak.bytranslator.tree.DirectoryNodeBinder;
import tr.com.fkocak.bytranslator.tree.File;
import tr.com.fkocak.bytranslator.tree.FileNodeBinder;
import tr.com.fkocak.bytranslator.tree.TreeNode;
import tr.com.fkocak.bytranslator.tree.TreeViewAdapter;

/**
 * Created by fatihkocak on 17.10.2018.
 */

public class DictionaryWordActivity extends AppCompatActivity {

    ApiService apiService;
    Response4Dictionary response4Dictionary;
    private RecyclerView rv;
    private TreeViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_word_activity);

        apiService = new ApiService();
        response4Dictionary = new Gson().fromJson(DictionaryResponseSingleton.getInstance().getResponse(), Response4Dictionary.class);
        setToolbarSetting();

        initView();
        initData(response4Dictionary);


    }

    private void initData(Response4Dictionary response4Dictionary) {
        List<TreeNode> nodes = new ArrayList<>();

        TreeNode<Dir> word = new TreeNode<>(new Dir("Kelime"));
        nodes.add(word);

        word.addChild(
                new TreeNode<>(new File(response4Dictionary.getWord()))
        );

        TreeNode<Dir> result = new TreeNode<>(new Dir("Detaylı Sonuçlar"));
        nodes.add(result);
        List<Result4SpesificWord> resultArr = response4Dictionary.getResults();

        int count = 1;
        for (Result4SpesificWord resultItem : resultArr) {
            TreeNode<Dir> childTree = new TreeNode<>(new Dir("Anlam " + count));
            //// Defination
            if (resultItem.getDefinition() != null) {
                childTree.addChild(
                        new TreeNode<>(new Dir("Tanımlama"))
                                .addChild(new TreeNode<>(new File(resultItem.getDefinition())))
                );
            }


            //// Örnekler
            TreeNode<Dir> example = new TreeNode<>(new Dir("Örnekler"));
            if (resultItem.getExamples() != null) {
                for (String exampleItem : resultItem.getExamples()) {
                    example.addChild(
                            new TreeNode<>(new File(exampleItem))
                    );
                }
                childTree.addChild(example);
            }

            //// Aynı Anlamda Keliemeler
            TreeNode<Dir> synonyms = new TreeNode<>(new Dir("Anlamdaş Kelimeler"));
            if (resultItem.getSynonyms() != null) {
                for (String synonymsItem : resultItem.getSynonyms()) {
                    synonyms.addChild(
                            new TreeNode<>(new File(synonymsItem))
                    );
                }
                childTree.addChild(synonyms);
            }

            //// Zıt Anlamda Keliemeler
            TreeNode<Dir> antonyms = new TreeNode<>(new Dir("Zıt Kelimeler"));
            if (resultItem.getAntonyms() != null) {
                for (String antonymsItem : resultItem.getAntonyms()) {
                    antonyms.addChild(
                            new TreeNode<>(new File(antonymsItem))
                    );
                }
                childTree.addChild(antonyms);
            }

            //// Aynı Anlamda Keliemeler
            TreeNode<Dir> memberOf = new TreeNode<>(new Dir("Bağlı Olduğu Üst Grup"));
            if (resultItem.getMemberOf() != null) {
                for (String memberOfItem : resultItem.getMemberOf()) {
                    memberOf.addChild(
                            new TreeNode<>(new File(memberOfItem))
                    );
                }
                childTree.addChild(memberOf);
            }

            //// Aynı Anlamda Keliemeler
            TreeNode<Dir> smilarTo = new TreeNode<>(new Dir("Benzer Kelimeler"));
            if (resultItem.getSimilarTo() != null) {
                for (String smilarToItem : resultItem.getSimilarTo()) {
                    smilarTo.addChild(
                            new TreeNode<>(new File(smilarToItem))
                    );
                }
                childTree.addChild(smilarTo);
            }

            //// Aynı Anlamda Keliemeler
            TreeNode<Dir> partOf = new TreeNode<>(new Dir("Kelimenin Ait Olduğu Bütün"));
            if (resultItem.getPartOf() != null) {
                for (String partOfItem : resultItem.getPartOf()) {
                    partOf.addChild(
                            new TreeNode<>(new File(partOfItem))
                    );
                }
                childTree.addChild(partOf);
            }

            //// Aynı Anlamda Keliemeler
            TreeNode<Dir> instanceOf = new TreeNode<>(new Dir("Örneği olduğu kelimeler"));
            if (resultItem.getInstanceOf() != null) {
                for (String instanceOfItem : resultItem.getInstanceOf()) {
                    instanceOf.addChild(
                            new TreeNode<>(new File(instanceOfItem))
                    );
                }
                childTree.addChild(instanceOf);
            }

            //// Derivation
            TreeNode<Dir> derivation = new TreeNode<>(new Dir("Kelime Kökeni"));
            if (resultItem.getDerivation() != null) {
                for (String derivationItem : resultItem.getDerivation()) {
                    derivation.addChild(
                            new TreeNode<>(new File(derivationItem))
                    );
                }
                childTree.addChild(derivation);
            }


            ///// Entails
            TreeNode<Dir> entails = new TreeNode<>(new Dir("Kelimenin İma Ettiği Fiiller"));
            if (resultItem.getEntails() != null) {
                for (String entailsItem : resultItem.getEntails()) {
                    entails.addChild(
                            new TreeNode<>(new File(entailsItem))
                    );
                }
                childTree.addChild(entails);
            }

            /// Has Part
            TreeNode<Dir> hasParts = new TreeNode<>(new Dir("Kelimenin Parçası Olan Kelimeler"));
            if (resultItem.getHasParts() != null) {
                for (String hasPartItem : resultItem.getHasParts()) {
                    hasParts.addChild(
                            new TreeNode<>(new File(hasPartItem))
                    );
                }
                childTree.addChild(hasParts);
            }

            /// Part Of Speech
            if (resultItem.getPartOfSpeech() != null) {
                childTree.addChild(
                        new TreeNode<>(new Dir("Kelime Tipi"))
                                .addChild(new TreeNode<>(new File(resultItem.getPartOfSpeech())))
                );
            }


            /// typeOf
            TreeNode<Dir> typeOfs = new TreeNode<>(new Dir("Jenerik Kelimeler"));
            if (resultItem.getTypeOf() != null) {
                for (String typeOfItem : resultItem.getTypeOf()) {
                    typeOfs.addChild(
                            new TreeNode<>(new File(typeOfItem))
                    );
                }
                childTree.addChild(typeOfs);
            }

            /// in Category
            TreeNode<Dir> inCategory = new TreeNode<>(new Dir("Ait Olduğu Alan Kategorisi"));
            if (resultItem.getInCategory() != null) {
                for (String inCategoryItem : resultItem.getInCategory()) {
                    inCategory.addChild(
                            new TreeNode<>(new File(inCategoryItem))
                    );
                }
                childTree.addChild(inCategory);
            }

            /// has Type
            TreeNode<Dir> hasType = new TreeNode<>(new Dir("Daha Spesifik Kelimeler"));
            if (resultItem.getHasTypes() != null) {
                for (String hasTypeItem : resultItem.getHasTypes()) {
                    hasType.addChild(
                            new TreeNode<>(new File(hasTypeItem))
                    );
                }
                childTree.addChild(hasType);
            }

            result.addChild(childTree);
            count++;
        }

        /////// Heceler
        if (response4Dictionary.getSyllables() != null) {
            TreeNode<Dir> syllablesTree = new TreeNode<>(new Dir("Heceler"));
            nodes.add(syllablesTree);

            Syllables syllables = response4Dictionary.getSyllables();
            syllablesTree.addChild(
                    new TreeNode<>(new Dir("Count"))
                            .addChild(
                                    new TreeNode<>(new File(syllables.getCount().toString()))
                            )
            );


            if (syllables.getList() != null) {
                for (String itemOfSyllables : syllables.getList()) {
                    syllablesTree.addChild(new TreeNode<>(new File(itemOfSyllables)));
                }
            }
        }


        ////// Okunuş
        if (response4Dictionary.getPronunciation() != null) {
            TreeNode<Dir> pronunciationTree = new TreeNode<>(new Dir("Okunuş"));
            nodes.add(pronunciationTree);

            pronunciationTree.addChild(
                    new TreeNode<>(new File(response4Dictionary.getPronunciation().getAll()))
            );

        }

        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(nodes, Arrays.asList(new FileNodeBinder(), new DirectoryNodeBinder()));
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    onToggle(!node.isExpand(), holder);
                } else {
                    String leafVal = ((FileNodeBinder.ViewHolder) holder).tvName.getText().toString();
                    translateLeafWord(leafVal);
                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                DirectoryNodeBinder.ViewHolder dirViewHolder = (DirectoryNodeBinder.ViewHolder) holder;
                final ImageView ivArrow = dirViewHolder.getIvArrow();
                int rotateDegree = isExpand ? 90 : -90;
                ivArrow.animate().rotationBy(rotateDegree)
                        .start();
            }
        });

        rv.setAdapter(adapter);
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
    }

    public void translateLeafWord(String leafVal) {


        final Dialog dialog;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
            dialog = new Dialog(DictionaryWordActivity.this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            //builder = new AlertDialog.Builder(context);
            dialog = new Dialog(DictionaryWordActivity.this);
        }

        dialog.setContentView(R.layout.tree_leaf_translate);
        String titleText = leafVal.contains(" ")? "Kelimelerin Anlamı             " : leafVal + " Kelime Anlamı             ";
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.white_yellow));
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);

        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                titleText.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );


        dialog.setTitle(ssBuilder);
        dialog.getWindow().setBackgroundDrawableResource(R.color.viewColor);

        EditText translatedLeafText = (EditText) dialog.findViewById(R.id.translatedText);
        apiService.translate(getApplicationContext(), leafVal, translatedLeafText, null, "en", "tr", false, true);

        dialog.show();


    }

    public void setToolbarSetting() {
        String html = "<font color='#ffed00'> " + response4Dictionary.getWord().substring(0, 1).toUpperCase() + response4Dictionary.getWord().substring(1) + " Sözlüğü" + " </font>";
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dictionary_word);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.mipmap.leftarrow);
        upArrow.setColorFilter(Color.parseColor("#ffed00"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setTitle(Html.fromHtml(html));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#25262f")));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                startActivity(new Intent(DictionaryWordActivity.this, SavedWord.class));
                finish();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startService(new Intent(DictionaryWordActivity.this, SavedWord.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
