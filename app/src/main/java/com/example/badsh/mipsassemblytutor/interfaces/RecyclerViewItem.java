package com.example.badsh.mipsassemblytutor.interfaces;

import android.content.Context;

/**
 * Created by Shabaz Badshah on 9/17/2017.
 */

public interface RecyclerViewItem {

    @Override
    String toString();

    void onClick(Context parentContext);
}
