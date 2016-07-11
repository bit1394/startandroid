package ru.bitreslab.p0461_expandablelistevents;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bit on 11.07.2016.
 */
public class AdapterHelper {
    final String ATTR_GROUP_NAME = "groupName";
    final String ATTR_PHONE_NAME = "groupName";

    String[] groups = {"HTC", "Samsung", "LG"};
    String[] phonesHTC = {"Senstaion", "Desire", "Wildfire", "Hero"};
    String[] phonesSam = {"Galaxy S 2", "Galaxy Nexus", "Wave"};
    String[] phonesLG = {"Optimus", "Optimus Link", "Optimus Black"};

    ArrayList<Map<String, String>> groupData;
    ArrayList<Map<String, String>> childDataItem;
    ArrayList<ArrayList<Map<String, String>>> childData;
    Map<String, String> m;
    Context ctx;

    AdapterHelper (Context _ctx) {
        ctx = _ctx;
    }

    SimpleExpandableListAdapter ad;

    SimpleExpandableListAdapter getAd() {
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            m = new HashMap<String, String>();
            m.put(ATTR_GROUP_NAME, group);
            groupData.add(m);
        }
        String groupFrom[] = {ATTR_GROUP_NAME};
        int groupTo[] = {android.R.id.text1};

        childData = new ArrayList<ArrayList<Map<String, String>>>();

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSam) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String childFrom[] = {ATTR_PHONE_NAME};
        int childTo[] = {android.R.id.text1};

        ad = new SimpleExpandableListAdapter(
                ctx,
                groupData, android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo,
                childData, android.R.layout.simple_list_item_1, childFrom, childTo);

        return ad;
    }

    String getGroupText (int groupPos){
        return ((Map<String, String>)(ad.getGroup(groupPos))).get(ATTR_GROUP_NAME);
    }

    String getChildText (int groupPos, int childPos){
        return ((Map<String, String>)(ad.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
    }

    String getGroupChildText (int groupPos, int childPos){
        return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
    }
}
