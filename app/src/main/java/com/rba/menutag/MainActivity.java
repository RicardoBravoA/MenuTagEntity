package com.rba.menutag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rba.menutag.control.tag.TagGroup;
import com.rba.menutag.control.tag.listener.TagListener;
import com.rba.menutag.model.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TagListener, View.OnClickListener {

    private TagGroup tagGroup;
    private List<ItemEntity> itemEntityList;
    private Button btnShow;
    private int selectIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagGroup = (TagGroup) findViewById(R.id.tagGroup);
        btnShow = (Button) findViewById(R.id.btnShow);

        itemEntityList = new ArrayList<>();

        tagGroup.addTags(generateList());

        tagGroup.setTagListener(this);
        btnShow.setOnClickListener(this);

    }

    private List<ItemEntity> generateList(){
        for (int i = 0; i < 10; i++){
            itemEntityList.add(new ItemEntity(i, getString(R.string.text_tag, String.valueOf(i))));
        }
        return  itemEntityList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnShow:
                if(selectIndex==-1){
                    Toast.makeText(this, getString(R.string.not_select_tag), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, getString(R.string.select_tag, itemEntityList.get(selectIndex).toString()),
                            Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void onTagSelected(int index) {
        selectIndex = index;
        Log.i("x- selected", ""+index);
    }

    @Override
    public void onTagDeselected(int index) {
        selectIndex = -1;
        Log.i("x- deselected", ""+index);
    }
}
